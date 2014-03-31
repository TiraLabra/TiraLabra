#ifndef CMPRSR_CMPRSRLIB_H
#define CMPRSR_CMPRSRLIB_H

#include <vector>

#include "cmprsr_sequence.h"
#include "cmprsr_merge.h"

typedef std::vector<sequence*> sequence_list;
typedef std::vector<sequence_pair> sequence_pair_list;
typedef std::vector<merge*> merge_list;

sequence_list encodeRLE(const c_data* pData, const c_uint& pLength);
void decodeRLE(FILE* pInput, FILE* pOutput);

sequence_list findBitSequences(const c_data* pData, const c_size& pLength, const c_uint& pMinSequence, const c_uint& pMinTrailing);

/// Finds increasing/decreasing sequences in data.
/// \param pData Data to process.
/// \param pLength Length of data.
/// \param pMinSequence Minimum sequence length.
/// \param pMaxDifference Maximum difference between values in a sequence.
/// \return A list of found sequences in increasing offset order.
sequence_list findSequences(const c_data* pData, const c_size& pLength, const c_uint& pMinSequence, const c_uint& pMaxDifference);

/// Creates a list of sequences that can be concatenated.
/// \param pSequences List of sequences.
/// \param pInclusive Check inclusively or exclusively.
/// \return A list of concatenateable sequences.
merge_list concatenateSequences(const sequence_list& pSequences, const bool& pInclusive);

/// Creates a list of sequences that can be concatenated.
/// \param pSequences List of sequences.
/// \param pInclusive Check inclusively or exclusively.
/// \return A list of sequences that can be concatenated.
merge_list concatenateSequencesSlow(const sequence_list& pSequences, const bool& pInclusive);

/// Creates a list of sequences that can be merged.
/// \param pSequences List of sequences.
/// \param pInclusive Check inclusively or exclusively.
/// \return A list of sequences that can be merged.
merge_list mergeSequences(const sequence_list& pSequences, const bool& pInclusive);

/// Moves memory in array, allows overlapping.
/// \param pDst Destination memory pointer.
/// \param pSrc Source memory pointer.
/// \param pSize Element size in bytes.
void memmove_in_array(void* pDst, void* pSrc, const size_t& pSize);

/// Reverses data in sequence.
/// \param pData Data to process.
/// \param pSequence Sequence to reverse.
void reverseSequenceData(c_data* pData, const sequence& pSequence);

/// Moves data to create a longer sequence.
/// \param pData Data to process.
/// \param pMerge Merge to process.
void mergeData(c_data* pData, merge& pMerge);

#endif