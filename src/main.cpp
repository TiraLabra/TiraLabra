#include <string.h>

#include <iostream>

#include "cmprsrlib.h"

void printSequence(const c_data* pData, const sequence& pSequence)
{
	std::cout << "Offset: " << pSequence.offset << " Length: " << pSequence.length
		<< " First: " << pSequence.first << " Last: " << pSequence.last << " Data: ";
	for(c_uint i=0; i<pSequence.length; ++i)
		std::cout << pData[pSequence.offset + i];
}

void printSequences(const c_data* pData, const sequence_list& pSequences)
{
	for(c_uint i=0; i<pSequences.size(); ++i)
	{
		std::cout << "Sequence " << i << ": ";
		printSequence(pData, *pSequences[i]);
		std::cout << "\n";
	}
}

int main(int argc, char** argv)
{
	const char* str = "9875678556780912212344332215";
	c_uint length = strlen(str);
	bool inclusive = true;

	FILE* file = fopen("test.txt", "rb");
	fseek(file, 0, SEEK_END);
	long size = ftell(file);
	rewind(file);
	c_uint csize = size / sizeof(c_data);
	c_data* d = new c_data[csize];
	fread(d, csize * sizeof(c_data), 1, file);
	fclose(file);

	std::cout << "Using byte size " << sizeof(c_data) << "\n";

	sequence_list sequences = findSequences(d, csize, 4, 65535);
	
	std::sort(sequences.begin(), sequences.end(), seq_cmp_length_greater());
	std::sort(sequences.begin(), sequences.end(), seq_cmp_value_less());
	
	merge_list concatenates = concatenateSequences(sequences, inclusive);
	//merge_list merges = mergeSequences(sequences, inclusive);

	/*char* data = new char[length + 1];
	memcpy(data, str, length);
	data[length] = 0;*/

	//printSequences(data, sequences);
	//std::cout << "Data: " << data << "\n";

	c_uint dataSize = size;
	c_uint sequenceCount = sequences.size();
	c_uint totalSequenceSize = 0;
	c_uint concatenationCount = concatenates.size();
	c_uint concatenationDeleteIterationCount = 0;
	c_uint concatenationDeleteCount = 0;
	c_uint sequenceMoveIterationCount = 0;
	c_uint sequenceMoveCount = 0;
	c_uint dataMoveCount = 0;

	for(c_uint i=0; i<sequences.size(); ++i)
	{
		totalSequenceSize += sequences[i]->length;
	}

	while(!concatenates.empty())
	{
		merge* m = concatenates.front();
		/*for(c_uint j=concatenates.size()-1; j>0; --j)
		{
			if(m->collides(*concatenates[j]))
			{
				concatenates.erase(concatenates.begin()+j);
				++concatenationDeleteCount;
			}
			++concatenationDeleteIterationCount;
		}*/
		concatenates.erase(concatenates.begin());

		bool forward = m->first->offset < m->second->offset;
		bool found = false;

		c_int length = forward ? -((int)m->first->length) : ((int)m->first->length);

		for(c_uint j=0; j<sequences.size(); ++j)
		{
			++sequenceMoveIterationCount;
			if(sequences[j] != m->first && sequences[j] != m->second && m->inRange(*sequences[j]))
			{
				sequences[j]->offset += length;
				found = true;
				++sequenceMoveCount;
			}else if(found)
			{
				break;
			}
		}

		mergeData(d, *m);

		std::sort(sequences.begin(), sequences.end(), seq_cmp_offset_less());

		++dataMoveCount;

		//printSequences(data, sequences);
		//std::cout << "Data: " << data << "\n";
	}
	
	std::cout << "Data size: " << dataSize << "\n";
	std::cout << "Sequence count: " << sequenceCount << "\n";
	std::cout << "Total sequence size: " << totalSequenceSize << " bytes\n";
	std::cout << "Concatenation count: " << concatenationCount << "\n";
	std::cout << "Concatenation delete count: " << concatenationDeleteCount << "\n";
	std::cout << "Concatenation delete iteration count: " << concatenationDeleteIterationCount << "\n";
	std::cout << "Sequence move count: " << sequenceMoveCount << "\n";
	std::cout << "Sequence move iteration count: " << sequenceMoveIterationCount << "\n";
	std::cout << "Data move count: " << dataMoveCount << "\n";

	return 0;
}