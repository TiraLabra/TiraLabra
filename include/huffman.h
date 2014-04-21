#ifndef CMPRSR_HUFFMAN_H
#define CMPRSR_HUFFMAN_H

#include <stdint.h>
#include <stdio.h>

bool huffmanEncode(uint8_t* pSrc, unsigned int pSrcLength, uint8_t*& pDst, unsigned int& pDstLength);
bool huffmanDecode(uint8_t* pSrc, unsigned int pSrcLength, uint8_t*& pDst, unsigned int& pDstLength);

bool huffmanEncodeFile(FILE* pSrc, FILE* pDst);
bool huffmanDecodeFile(FILE* pSrc, FILE* pDst);

#endif