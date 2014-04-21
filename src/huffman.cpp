#include <stdlib.h>
#include <string.h>

#include "huffman.h"

int nodeCmpCharacter(const void* pNode1, const void* pNode2)
{
	const HuffmanNode* node1 = (const HuffmanNode*)pNode1;
	const HuffmanNode* node2 = (const HuffmanNode*)pNode2;
	
	return node1->Character > node2->Character ? 1 : -1;
}

int nodeCmpFrequency(const void* pNode1, const void* pNode2)
{
	const HuffmanNode* node1 = (const HuffmanNode*)pNode1;
	const HuffmanNode* node2 = (const HuffmanNode*)pNode2;
	
	if(node1->Frequency == node2->Frequency)
		return 0;

	return node1->Frequency < node2->Frequency ? 1 : -1;
}

void setNodeCode(HuffmanNode* pNode)
{
	HuffmanNode* parent = pNode->Parent;
	while(parent && parent->CodeLength)
	{
		pNode->Code <<= 1;
		pNode->Code |= parent->Code;
		++pNode->CodeLength;
		parent = parent->Parent;
	}
}

HuffmanNode* popNode(HuffmanNode** pNodes, unsigned int pIndex, bool pRight)
{
	HuffmanNode* node = pNodes[pIndex];
	
	node->Code = pRight;
	node->CodeLength = 1;

	return node;
}

unsigned int generateHuffmanTree(HuffmanNode* pNodes)
{
	HuffmanNode* nodes[256];
	HuffmanNode* node;

	unsigned int nodeCount = 0;
	for(unsigned int i = 0; i < 256 && pNodes[i].Frequency; ++i)
	{
		nodes[nodeCount++] = &pNodes[i];
	}

	unsigned int parentNode = nodeCount;
	int backNode = nodeCount - 1;
	while(backNode > 0)
	{
		node = &pNodes[parentNode++];
		
		node->Left = popNode(nodes, backNode--, false);
		node->Left->Parent = node;
		node->Right = popNode(nodes, backNode--, true);
		node->Right->Parent = node;

		node->Frequency = node->Left->Frequency + node->Right->Frequency;

		int i;
		for(i = backNode; i >= 0; --i)
		{
			if(nodes[i]->Frequency >= node->Frequency)
				break;
		}

		memmove(nodes + i + 2, nodes + i + 1, (backNode - i) * sizeof(HuffmanNode*));
		nodes[i + 1] = node;
		++backNode;
	}

	return nodeCount;
}

void setHuffmanNodeCodes(HuffmanNode* pNodes, unsigned int pCount)
{
	for(unsigned int i = 0; i < pCount; ++i)
		setNodeCode(&pNodes[i]);
}

bool huffmanEncode(uint8_t* pSrc, unsigned int pSrcLength, uint8_t*& pDst, unsigned int& pDstLength)
{
	HuffmanNode nodes[512];
	memset(nodes, 0, sizeof(HuffmanNode) * 512);

	for(unsigned int i = 0; i < 256; ++i)
		nodes[i].Character = i;

	for(unsigned int i = 0; i < pSrcLength; ++i)
		++nodes[pSrc[i]].Frequency;

	qsort(nodes, 256, sizeof(HuffmanNode), nodeCmpFrequency);

	unsigned int nodeCount = generateHuffmanTree(nodes);
	unsigned int nodeSize = sizeof(uint32_t) + sizeof(uint8_t);

	pDstLength = pSrcLength + nodeCount * nodeSize;
	pDst = (uint8_t*)malloc(pDstLength);
	memset(pDst, 0, pDstLength);
	
	uint8_t* dstPtr = pDst;
	*(uint32_t*)dstPtr = pSrcLength;
	dstPtr += sizeof(uint32_t);

	*dstPtr = nodeCount - 1;
	dstPtr += sizeof(uint8_t);

	setHuffmanNodeCodes(nodes, nodeCount);
	for(unsigned int i = 0; i < nodeCount; ++i)
	{
		memcpy(dstPtr, &nodes[i], nodeSize);
		dstPtr += nodeSize;
	}

	qsort(nodes, 256, sizeof(HuffmanNode), nodeCmpCharacter);

	unsigned int dstIndex = 0;
	for(unsigned int i = 0; i < pSrcLength; ++i)
	{
		*(uint32_t*)(dstPtr + (dstIndex >> 3)) |= nodes[pSrc[i]].Code << (dstIndex & 7);
		dstIndex += nodes[pSrc[i]].CodeLength;
	}

	pDstLength = (dstPtr - pDst) + (dstIndex + 7) / 8;
	pDst = (uint8_t*)realloc(pDst, pDstLength);

	return true;
}

bool huffmanDecode(uint8_t* pSrc, unsigned int pSrcLength, uint8_t*& pDst, unsigned int& pDstLength)
{
	HuffmanNode nodes[512];
	memset(nodes, 0, sizeof(HuffmanNode) * 512);

	unsigned int nodeCount = *(pSrc + sizeof(uint32_t)) + 1;
	unsigned int nodeSize = sizeof(uint32_t) + sizeof(uint8_t);
	
	unsigned int srcIndex = nodeSize;
	for(unsigned int i = 0; i < nodeCount; ++i)
	{
		memcpy(&nodes[i], pSrc + srcIndex, nodeSize);
		srcIndex += nodeSize;
	}

	generateHuffmanTree(nodes);

	HuffmanNode* root = &nodes[0];
	while(root->Parent)
		root = root->Parent;

	pDstLength = *(uint32_t*)pSrc;
	pDst = (uint8_t*)malloc(pDstLength + 1);

	unsigned int dstIndex = 0;
	srcIndex <<= 3;
	while(dstIndex < pDstLength)
	{
		uint32_t code = (*(uint32_t*)(pSrc + (srcIndex >> 3))) >> (srcIndex & 7);
		HuffmanNode* node = root;
		while(node->Left)
		{
			node = (code & 1) ? node->Right : node->Left;
			code >>= 1;
			++srcIndex;
		}
		pDst[dstIndex++] = node->Character;
	}

	return true;
}