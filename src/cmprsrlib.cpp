#include <string.h>

#include <algorithm>
#include <iostream>
#include <map>

#include "cmprsrlib.h"

sequence_list findSequences(const c_data* pData, const c_size& pLength, const c_uint& pMinSequence, const c_uint& pMaxDifference)
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
				if(count >= pMinSequence)
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
	sequence_list sequences = pSequences;

	c_uint iterationCount = 0;
	
	for(c_uint i=0; i<sequences.size() - 1; ++i)
	{
		for(c_uint j=i+1; j<sequences.size(); ++j)
		{
			++iterationCount;
			if(sequences[i]->no_offset_overlap(*sequences[j]) && sequences[i]->no_value_overlap(*sequences[j], true))
			{
				if(sequences[i]->block_before(*sequences[j]))
					merges.push_back(new merge(sequences[i], sequences[j]));
				else
					merges.push_back(new merge(sequences[j], sequences[i]));
				sequences.erase(sequences.begin() + j);
				break;
			}
		}
	}

	std::cout << "Iteration count: " << iterationCount << "\n";

	return merges;
}

merge_list concatenateSequencesSlow(const sequence_list& pSequences, const bool& pInclusive)
{
	merge_list merges;
	std::map<sequence*, merge*> sequenceMerges;

	for(c_uint i=0; i<pSequences.size()-1; ++i)
	{
		bool found = false;
		for(c_uint j=i+1; j<pSequences.size(); ++j)
		{
			if(pSequences[i]->no_offset_overlap(*pSequences[j]))
			{
				if(pSequences[i]->range_before(*pSequences[j], pInclusive))
				{
					merge* m = sequenceMerges[pSequences[i]];
					if(m)
					{
						if(m->value_distance > pSequences[i]->value_distance(*pSequences[j]))
							m->second = pSequences[j];
					}else{
						sequenceMerges[pSequences[i]] = new merge(pSequences[i], pSequences[j]);
					}
				}else if(pSequences[j]->range_before(*pSequences[i], pInclusive))
				{
					merge* m = sequenceMerges[pSequences[j]];
					if(m)
					{
						if(m->value_distance > pSequences[j]->value_distance(*pSequences[i]))
							m->second = pSequences[j];
					}else{
						sequenceMerges[pSequences[j]] = new merge(pSequences[j], pSequences[i]);
					}
				}

				found = true;
			}else if(found)
			{
				break;
			}
		}
	}

	std::map<sequence*, merge*>::iterator it;
	for(it = sequenceMerges.begin(); it != sequenceMerges.end(); ++it)
		merges.push_back(it->second);

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

void memmove_in_array(void* pDst, void* pSrc, const size_t& pSize)
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

void mergeData(c_data* pData, merge& pMerge)
{
	memmove_in_array(pData + pMerge.second->offset, pData + pMerge.first->offset, pMerge.first->length);
	pMerge.first->offset = pMerge.second->offset;
	pMerge.second->offset += pMerge.first->length;
}