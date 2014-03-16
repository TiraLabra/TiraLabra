#include <string.h>

#include <algorithm>
#include <iostream>
#include <map>

#include "cmprsrlib.h"

sequence_list findSequences(const c_data* pData, const c_size& pLength)
{
	sequence_list sequences;

	c_uint count = 1;
	bool found = false;
	bool increasing = true;

	for(c_uint i=1; i<pLength; ++i)
	{
		if(pData[i] != pData[i - 1])
		{
			bool inc = pData[i] > pData[i - 1];
			if(found && inc != increasing)
			{
				sequences.push_back(new sequence(i - count, count, pData[i - count], pData[i - 1]));
				count = 0;
				found = false;
			}else{
				found = true;
			}
			increasing = inc;
		}

		++count;
	}

	if(count > 0)
		sequences.push_back(new sequence(pLength - count, count, pData[pLength - count], pData[pLength - 1]));

	return sequences;
}

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
					merges.push_back(new merge(pSequences[i], pSequences[j]));
				else if(pSequences[j]->range_before(*pSequences[i], pInclusive))
					merges.push_back(new merge(pSequences[j], pSequences[i]));

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
				merges.push_back(new merge(pSequences[i], pSequences[j]));
			else if(pSequences[j]->can_contain(*pSequences[i], pInclusive))
				merges.push_back(new merge(pSequences[j], pSequences[i]));
		}
	}

	return merges;
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
