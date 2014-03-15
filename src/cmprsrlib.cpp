#include <algorithm>
#include <iostream>
#include <map>

#include "cmprsrlib.h"

// Finds ascending/descending sequences in data.
sequence_list findSequences(const c_data* pData, const c_size& pLength, const bool& pInclusive)
{
	sequence_list sequences;

	c_uint ascCount = 0;
	c_uint descCount = 0;

	c_uint count = 1;
	bool increasing = true;

	for(c_uint i=1; i<pLength; ++i)
	{
		if(pData[i] != pData[i - 1] && (pData[i] > pData[i - 1]) != increasing)
		{
			if(count > 1)
			{
				sequences.push_back(new sequence(i - count, count, pData[i - count], pData[i - 1]));
				count = 0;
			}
			increasing = !increasing;
		}

		++count;
	}

	if(count > 0)
		sequences.push_back(new sequence(pLength - count, count, pData[pLength - count], pData[pLength - 1]));

	return sequences;
}

// Creates a list of sequences that can be concatenated.
merge_list concatenateSequences(const sequence_list& pSequences, const bool& pInclusive)
{
	merge_list merges;

	for(c_uint i=0; i<pSequences.size()-1; ++i)
	{
		bool found = false;
		for(c_uint j=i+1; j<pSequences.size(); ++j)
		{
			if(pSequences[i]->no_offset_overlap(*pSequences[j]))
			{
				if(pSequences[i]->range_before(*pSequences[j], pInclusive))
					merges.push_back(new merge(i, j, pSequences[i]->value_distance(*pSequences[j])));
				else if(pSequences[j]->range_before(*pSequences[i], pInclusive))
					merges.push_back(new merge(j, i, pSequences[j]->value_distance(*pSequences[i])));

				found = true;
			}else if(found)
			{
				break;
			}
		}
	}

	return merges;
}

merge_list mergeSequences(const sequence_list& pSequences, const bool& pInclusive)
{
	merge_list merges;

	for(c_uint i=0; i<pSequences.size()-1; ++i)
	{
		for(c_uint j=i+1; j<pSequences.size(); ++j)
		{
			if(pSequences[i]->can_contain(*pSequences[j], pInclusive))
				merges.push_back(new merge(i, j, pSequences[i]->value_distance(*pSequences[j])));
			else if(pSequences[j]->can_contain(*pSequences[i], pInclusive))
				merges.push_back(new merge(j, i, pSequences[j]->value_distance(*pSequences[i])));
		}
	}

	return merges;
}

bool sequence_in_merge_range(const sequence_list& pSequences, const c_uint& pSequence, const merge& pMerge)
{
		return (pSequences[pMerge.first]->offset <= pSequences[pSequence]->offset && pSequences[pMerge.second]->offset >= pSequences[pSequence]->offset)
			|| (pSequences[pMerge.first]->offset >= pSequences[pSequence]->offset && pSequences[pMerge.second]->offset <= pSequences[pSequence]->offset);
}

void cmprsr_memmove_in_array(void* pDst, void* pSrc, const size_t& pSize)
{
	char* dst = static_cast<char*>(pDst);
	char* src = static_cast<char*>(pSrc);
	c_data* tmp = new c_data[pSize];
	
	memcpy(tmp, pSrc, pSize);
	
	if(pSrc < pDst)
		memmove(pSrc, src + pSize, (size_t)(dst - src));
	else if(pSrc > pDst)
		memmove(dst + pSize, pDst, (size_t)(src - dst));
	
	memcpy(pDst, tmp, pSize);

	delete[] tmp;
}

void reverseSequence(c_data* pData, const sequence& pSequence)
{
	for(c_uint i=0; i<pSequence.length/2; ++i)
	{
		c_data tmp = pData[pSequence.offset+i];
		pData[pSequence.offset+i] = pData[pSequence.offset+pSequence.length-i-1];
		pData[pSequence.offset+pSequence.length-i-1] = tmp;
	}
}