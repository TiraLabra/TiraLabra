#include <iostream>

#include "cmprsrlib.h"

void printSequences(const c_data* pData, const sequence_list& pSequences)
{
	for(c_uint i=0; i<pSequences.size(); ++i)
	{
		std::cout << "Sequence " << i << ": " << "Offset: " << pSequences[i]->offset << " Length: " << pSequences[i]->length
			<< " First: " << (int)pSequences[i]->first << " Last: " << (int)pSequences[i]->last << "\n";
		std::cout << "Sequence " << i << " data: ";
		for(c_uint j=0; j<pSequences[i]->length; ++j)
			std::cout << pData[pSequences[i]->offset + j];
		std::cout << "\n";
	}
}

int main(int argc, char** argv)
{
	const char* str = "5678556780912212344332215";
	c_uint length = strlen(str);
	char* data = (char*)malloc(length);
	memcpy(data, str, length);
	data[length-1] = 0;

	bool inclusive = true;
	sequence_list sequences = findSequences(data, length, inclusive);
	merge_list concatenates = concatenateSequences(sequences, inclusive);
	merge_list merges = mergeSequences(sequences, inclusive);

	std::cout << "Data: " << data << "\n";

	std::cout << "Sequences:\n";
	printSequences(data, sequences);
	std::cout << "\n";

	while(!concatenates.empty())
	{
		merge* m = concatenates.front();
		concatenates.erase(concatenates.begin());

		for(c_uint i=concatenates.size(); i>0; --i)
		{
			if(m->collides(*concatenates[i - 1]))
				concatenates.erase(concatenates.begin() + 1 - 1);
			//if(m->collides(*concatenates[i - 1]))
				//concatenates.erase(concatenates.begin() + i - 1);
		}

		cmprsr_memmove_in_array(data + sequences[m->first]->offset + sequences[m->first]->length, data + sequences[m->second]->offset, sequences[m->second]->length);
		bool forward = sequences[m->first]->offset > sequences[m->second]->offset;
		for(c_uint j=0; j<sequences.size(); ++j)
		{
			if(sequence_in_merge_range(sequences, j, *m))
			{
				if(forward)
				{
					std::cout << "Need to move sequence #" << j << " to: " << sequences[j]->offset << " + " << sequences[m->first]->offset - sequences[m->second]->offset << "\n";
					sequences[j]->offset += sequences[m->first]->offset - sequences[m->second]->offset;
				}
				else
				{
					std::cout << "Need to move sequence #" << j << " to: " << sequences[j]->offset << " - " << sequences[m->second]->offset - sequences[m->first]->offset << "\n";
					sequences[j]->offset -= sequences[m->second]->offset - sequences[m->first]->offset;
				}
			}
			/*if(m->inRange(*sequences[j]))
			{
				if(forward)
				{
					std::cout << "Need to move sequence #" << j << " to: " << sequences[j]->offset << " + " << m->first->offset - m->second->offset << "\n";
					sequences[j]->offset += m->first->offset - m->second->offset;
				}
				else
				{
					std::cout << "Need to move sequence #" << j << " to: " << sequences[j]->offset << " - " << m->second->offset - m->first->offset << "\n";
					sequences[j]->offset -= m->second->offset - m->first->offset;
				}
			}*/
		}

		std::cout << "Sequences:\n";
		printSequences(data, sequences);
		std::cout << "\n";
	}
	
	int i;
	std::cin >> i;

	return 0;
}