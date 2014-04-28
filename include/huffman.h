#ifndef CMPRSR_HUFFMAN_H
#define CMPRSR_HUFFMAN_H

#include <stdint.h>
#include <stdio.h>

/// Huffman encodes data in memory.
/// \param pSrc Source data to encode.
/// \param pSrcLength Length of data to encode.
/// \param pDst Encoded data destination.
/// \param pDstLength Encoded data length.
/// \return True on success, false on failure.
bool huffmanEncode(uint8_t* pSrc, unsigned int pSrcLength, uint8_t*& pDst, unsigned int& pDstLength);

/// Decodes huffman encoded data in memory.
/// \param pSrc Source data to decode.
/// \param pSrcLength Length of data to decode.
/// \param pDst Decoded data destination.
/// \param pDstLength Decoded data length.
/// \return True on success, false on failure.
bool huffmanDecode(uint8_t* pSrc, unsigned int pSrcLength, uint8_t*& pDst, unsigned int& pDstLength);

/// Huffman encodes data in file.
/// \param pSrc Source file to encode.
/// \param pDst Destination file.
/// \return True on success, false on failure.
bool huffmanEncodeFile(FILE* pSrc, FILE* pDst);

/// Decodes huffman encoded data in file.
/// \param pSrc Source file to decode.
/// \param pDst Destination file.
/// \return True on success, false on failure.
bool huffmanDecodeFile(FILE* pSrc, FILE* pDst);

#endif