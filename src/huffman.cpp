#include "huffman.h"

#include <queue>

typedef std::vector<bool> HuffmanCode;
typedef std::map<char, HuffmanCode> HuffmanCodeMap;

Node* buildTree(const int* pFrequencies, const unsigned int& pFrequenciesCount)
{
	std::priority_queue<Node*, std::vector<Node*>, NodeCmp> trees;

	for(unsigned int i=0; i<pFrequenciesCount; ++i)
	{
		if(pFrequencies[i] != 0)
			trees.push(new Node(0, 0, pFrequencies[i], (char)i));
	}

	while(trees.size() > 1)
	{
		Node* childL = trees.top();
		trees.pop();
	
		Node* childR = trees.top();
		trees.pop();

		Node* parent = new Node(childL, childR, childL->Frequency + childR->Frequency, 0);
		trees.push(parent);
	}

	return trees.top();
}

void generateCodes(const Node* pNode, const HuffmanCode& pPrefix, HuffmanCodeMap& pOutCodes)
{
	if(pNode->Left == 0 && pNode->Right == 0)
	{
		pOutCodes[pNode->Character] = pPrefix;
	}else{
		HuffmanCode leftPrefix = pPrefix;
		leftPrefix.push_back(false);
		generateCodes(pNode->Left, leftPrefix, pOutCodes);

		HuffmanCode rightPrefix = pPrefix;
		rightPrefix.push_back(true);
		generateCodes(pNode->Right, rightPrefix, pOutCodes);
	}
}

void writeTreeToFile(FILE* pFile, const HuffmanCodeMap& pCodes)
{
	unsigned char size = pCodes.size();
	
	fwrite(&size, 1, 1, pFile);
	
	for(HuffmanCodeMap::const_iterator it = pCodes.begin(); it != pCodes.end(); ++it)
	{
		fwrite(&it->first, 1, 1, pFile);
		size = it->second.size();
		fwrite(&size, 1, 1, pFile);

		unsigned char code = 0;
		for(unsigned int i=0; i<it->second.size(); ++i)
		{
			if(it->second[i])
				code |= (1 << i);
		}

		fwrite(&code, 1, 1, pFile);
	}
}

void decodeTree(FILE* pFile, HuffmanCodeMap& pCodes)
{
	unsigned char size;
	
	fread(&size, 1, 1, pFile);
	
	for(unsigned int i=0; i<size; ++i)
	{
		char character;
		unsigned char bits;
		unsigned char code;

		fread(&character, 1, 1, pFile);
		fread(&bits, 1, 1, pFile);
		fread(&code, 1, 1, pFile);

		HuffmanCode huffmanCode;
		for(int j=0; j<bits; ++j)
		{
			if(code & (1 << j))
				huffmanCode.push_back(true);
			else
				huffmanCode.push_back(false);
		}

		pCodes[character] = huffmanCode;
	}
}


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

unsigned int getHuffmanTree(HuffmanNode* pNodes, bool pSetCodes)
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

		int i = backNode;
		for(; i >= 0; --i)
		{
			if(nodes[i]->Frequency >= node->Frequency)
				break;
		}

		memmove(nodes + i + 2, nodes + i + 1, (backNode - i) * sizeof(unsigned int));
		nodes[i + 1] = node;
		++backNode;
	}

	if(pSetCodes)
	{
		for(unsigned int i = 0; i < nodeCount; ++i)
		{
			setNodeCode(&pNodes[i]);
		}
	}

	return nodeCount;
}

bool huffmanEncode(char* pSrc, unsigned int pSrcLength, char* pDst, unsigned int pDstLength)
{
	HuffmanNode nodes[512];
	memset(nodes, 0, 512 * sizeof(HuffmanNode));

	for(unsigned int i = 0; i < 256; ++i)
	{
		nodes[i].Character = i;
	}

	//int c;
	for(unsigned int i = 0; i < pSrcLength; ++i)
	{
		++nodes[pSrc[i]].Frequency;
	}

	/*while((c = fgetc(pSrc)) != EOF)
	{
		++nodes[c].Frequency;
	}*/

	//rewind(pSrc);

	qsort(nodes, 256, sizeof(HuffmanNode), nodeCmpFrequency);

	unsigned int nodeCount = getHuffmanTree(nodes, true);
	//int nodeSize = sizeof(int) + sizeof(char);
	unsigned int nodeSize = sizeof(unsigned int) + sizeof(unsigned char);

	memset(pDst, 0, pDstLength);

	*((unsigned int*)pDst) = pSrcLength;
	pDst += sizeof(unsigned int);
	*pDst = nodeCount;
	pDst += sizeof(unsigned char);
	//fwrite(&nodeCount, 1, 1, pDst);

	for(unsigned int i = 0; i < nodeCount; ++i)
	{
		memcpy(pDst, &nodes[i], nodeSize);
		pDst += nodeSize;
		//fwrite(&nodes[i], sizeof(HuffmanNode), 1, pDst);
	}

	qsort(nodes, 256, sizeof(HuffmanNode), nodeCmpCharacter);

	unsigned int code = 0;
	unsigned int dstIndex = 0;
	//while((c = fgetc(pSrc)) != EOF)
	for(unsigned int i = 0; i < pSrcLength; ++i)
	{
		*(unsigned int*)(pDst + (dstIndex >> 3)) |= nodes[pSrc[i]].Code << (dstIndex & 7);
		dstIndex += nodes[pSrc[i]].CodeLength;
		//code |= nodes[c].Code << (dstIndex & 7);
		//dstIndex += nodes[c].CodeLength;
		
		/*if(dstIndex >> 3 > 0)
		{
			fwrite(&code, sizeof(int), 1, pDst);
			code = 0;
			dstIndex >>= 4;
		}*/
	}

	/*if(code)
	{
		fwrite(&code, sizeof(int), 1, pDst);
	}*/

	return true;
}

bool huffmanDecode(char* pSrc, unsigned int pSrcLength, char* pDst, unsigned int pDstLength)
{
	HuffmanNode nodes[512];
	memset(nodes, 0, 512 * sizeof(HuffmanNode));
	
	unsigned int dstLength = *(unsigned int*)pSrc;
	unsigned int nodeCount = *(pSrc + sizeof(unsigned int));
	//int nodeCount = fgetc(pSrc);
	//int nodeSize = sizeof(int) + sizeof(char);
	unsigned int nodeSize = sizeof(unsigned int) + sizeof(unsigned char);

	unsigned int srcIndex = nodeSize;
	for(unsigned int i = 0; i < nodeCount; ++i)
	{
		memcpy(&nodes[i], pSrc + srcIndex, nodeSize);
		srcIndex += nodeSize;

		//fread(&nodes[i], sizeof(HuffmanNode), 1, pSrc);
	} 

	getHuffmanTree(nodes, false);

	HuffmanNode* root = &nodes[0];
	while(root->Parent)
		root = root->Parent;

	unsigned int code;
	//int c;
	srcIndex <<= 3;
	unsigned int dstIndex = 0;
	//while(!feof(pSrc))
	while(dstIndex < pDstLength)
	{
		//fread(&c, sizeof(int), 1, pSrc);
		//code = c >> (srcIndex & 7);

		code = (*(unsigned int*)(pSrc + (srcIndex >> 3))) >> (srcIndex & 7);
		
 		HuffmanNode* node = root;
		while(node->Left)
		{
			node = (code & 1) ? node->Right : node->Left;
			code >>= 1;
			++srcIndex;
		}

		pDst[dstIndex++] = node->Character;
		//fputc(node->Character, pDst);
	}

	return true;
}