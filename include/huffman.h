#ifndef CMPRSR_HUFFMAN_H
#define CMPRSR_HUFFMAN_H

#include <stdint.h>

struct HuffmanNode
{
	uint32_t Frequency;
	uint8_t Character;
	uint32_t Code;
	uint32_t CodeLength;
	HuffmanNode* Parent;
	HuffmanNode* Left;
	HuffmanNode* Right;
};

int nodeCmpCharacter(const void* pNode1, const void* pNode2);
int nodeCmpFrequency(const void* pNode1, const void* pNode2);

void setNodeCode(HuffmanNode* pNode);
HuffmanNode* popNode(HuffmanNode** pNodes, unsigned int pIndex, bool pRight);

unsigned int generateHuffmanTree(HuffmanNode* pNodes);
void setHuffmanNodeCodes(HuffmanNode* pNodes, unsigned int pCount);

bool huffmanEncode(uint8_t* pSrc, unsigned int pSrcLength, uint8_t*& pDst, unsigned int& pDstLength);
bool huffmanDecode(uint8_t* pSrc, unsigned int pSrcLength, uint8_t*& pDst, unsigned int& pDstLength);

#endif