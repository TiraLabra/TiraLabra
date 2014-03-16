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

	sequence_list sequences = findSequences(str, length);
	merge_list concatenates = concatenateSequences(sequences, inclusive);
	merge_list merges = mergeSequences(sequences, inclusive);

	std::cout << "Data: " << str << "\n";

	std::cout << "Sequences:\n";
	printSequences(str, sequences);
	
	int i;
	std::cin >> i;

	return 0;
}