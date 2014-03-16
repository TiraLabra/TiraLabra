#ifndef CMPRSR_CMPRSRLIB_H
#define CMPRSR_CMPRSRLIB_H

#include <vector>

#include "cmprsr_sequence.h"
#include "cmprsr_merge.h"

typedef std::vector<sequence*> sequence_list;
typedef std::vector<sequence_pair> sequence_pair_list;
typedef std::vector<merge*> merge_list;

// Finds increasing/decreasing sequences in data.
// Returns list of found sequences in increasing offset order.
sequence_list findSequences(const c_data* pData, const c_size& pLength);

// Creates a list of sequences that can be concatenated.
merge_list concatenateSequences(const sequence_list& pSequences, const bool& pInclusive);

// Creates a list of sequences that can possibly be merged.
merge_list mergeSequences(const sequence_list& pSequences, const bool& pInclusive);

// Moves memory in array, allows overlapping.
void cmprsr_memmove_in_array(void* pDst, void* pSrc, const size_t& pSize);

// Reverses data in sequence.
void reverseSequence(c_data* pData, const sequence& pSequence);

#endif