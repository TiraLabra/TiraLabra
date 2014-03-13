#include <algorithm>
#include <iostream>
#include <map>
#include <vector>

#include "cmprsr_types.h"

#include "cmprsr_sequence.h"
#include "cmprsr_merge.h"

typedef std::vector<sequence> sequence_list;
typedef std::vector<merge> merge_list;

// Finds ascending/descending sequences in data.
sequence_list findSequences(const c_data* pData, const c_size& pLength, const bool& pInclusive)
{
	// For debug purposes, to be removed
	c_uint* ascending = new c_uint[pLength];
	c_uint* descending = new c_uint[pLength];
	c_int* delta = new c_int[pLength];

	sequence_list sequences;

	c_uint ascCount = 1;
	c_uint descCount = 1;

	for(c_uint i=1; i<pLength; ++i)
	{
		if(pData[i] > pData[i-1])
		{
			++ascCount;
			for(c_uint j=0; j<descCount; ++j)
			{
				descending[i-j-1] = j;
				delta[i-j-1] = -((c_int)j);
			}

			if(descCount > 1)
				sequences.push_back(sequence(i - descCount, descCount, pData[i - descCount], pData[i - 1]));

			descCount = 1;

		}else if(pData[i] < pData[i-1])
		{
			++descCount;
			for(c_uint j=0; j<ascCount; ++j)
			{
				ascending[i-j-1] = j;
				delta[i-j-1] = j;
			}

			if(ascCount > 1)
			{
				sequences.push_back(sequence(i - ascCount, ascCount, pData[i - ascCount], pData[i - 1]));
			}

			ascCount = 1;
		}else{
			if(pInclusive)
			{
				++ascCount;
				++descCount;
			}else{
				for(c_uint j=0; j<ascCount; ++j)
				{
					ascending[i-j-1] = j;
					delta[i-j-1] = j;
				}

				if(ascCount > 1)
					sequences.push_back(sequence(i - ascCount, ascCount, pData[i - ascCount], pData[i - 1]));

				ascCount = 1;

				for(c_uint j=0; j<descCount; ++j)
				{
					descending[i-j-1] = j;
					delta[i-j-1] = -((c_int)j);
				}

				if(descCount > 1)
					sequences.push_back(sequence(i - descCount, descCount, pData[i - descCount], pData[i - 1]));

				descCount = 1;
			}
		}
	}

	for(c_uint i=0; i<ascCount; ++i)
	{
		ascending[pLength-i-1] = i;
		delta[pLength-i-1] = i;
	}

	if(ascCount > 1)
		sequences.push_back(sequence(pLength - ascCount, ascCount, pData[pLength - ascCount], pData[pLength - 1]));

	for(c_uint i=0; i<descCount; ++i)
	{
		descending[pLength-i-1] = i;
		delta[pLength-i-1] = -((c_int)i);
	}

	if(descCount > 1)
		sequences.push_back(sequence(pLength - descCount, descCount, pData[pLength - descCount], pData[pLength - 1]));

	std::sort(sequences.begin(), sequences.end(), seq_cmp_offset_less());

	
	std::cout << "Data:";
	for(c_uint i=0; i<pLength; ++i)
		std::cout << " " << (c_uint)pData[i];
	std::cout << "\n";

	std::cout << "Ascending: ";
	for(c_uint i=0; i<pLength; ++i)
	{
		std::cout.width(3);
		std::cout << ascending[i];
	}
	std::cout << "\n";

	std::cout << "Descending:";
	for(c_uint i=0; i<pLength; ++i)
	{
		std::cout.width(3);
		std::cout << descending[i];
	}
	std::cout << "\n";

	std::cout << "Delta:     ";
	for(c_uint i=0; i<pLength; ++i)
	{
		std::cout.width(3);
		std::cout << delta[i];
	}
	std::cout << "\n\n";

	std::cout << "Sequences:\n";
	for(c_uint i=0; i<sequences.size(); ++i)
		std::cout << "Sequence #" << i << ": " << "Offset: " << sequences[i].offset << " Length: " << sequences[i].length
			<< " First: " << (int)sequences[i].first << " Last: " << (int)sequences[i].last << "\n";
	std::cout << "\n";

	return sequences;
}

// Creates a list of sequences that can be merged (concatenated)
merge_list mergeSequences(const sequence_list& pSequences, const bool& pInclusive)
{
	merge_list merges;
	for(c_uint i=0; i<pSequences.size()-1; ++i)
	{
		bool possibleMergeFound = false;
		for(c_uint j=i+1; j<pSequences.size(); ++j)
		{
			if(pSequences[i].no_offset_overlap(pSequences[j]))
			{
				if(pSequences[i].no_value_overlap(pSequences[j], pInclusive))
					merges.push_back(merge(i, j, pSequences[i].value_distance(pSequences[j])));
				possibleMergeFound = true;
			}else if(possibleMergeFound)
			{
				break;
			}
		}
	}

	std::sort(merges.begin(), merges.end(), merge_cmp_distance_less());

	std::cout << "Merges:\n";
	for(c_uint i=0; i<merges.size(); ++i)
	{
		std::cout << "Merge #" << i << ": " << "First: " << merges[i].first << " Second: " << merges[i].second
			<< " Distance: " << (int)merges[i].distance << "\n";
	}

	return merges;
}

int main(int argc, char** argv)
{
	std::string str = "46780123";
	mergeSequences(findSequences(str.c_str(), str.size(), true), true);

	int i;
	std::cin >> i;

	return 0;
}