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

void printBytes(const c_uint& pBytes, const bool& pSi)
{
	c_uint unit = pSi ? 1000 : 1024;
	if(pBytes < unit)
	{
		std::cout << pBytes << " B";
		return;
	}

	c_uint exp = (c_uint)(log((float)pBytes) / log((float)unit));
	std::string pre = (pSi ? "kMGTPE" : "KMGTPE");
	pre = pre[exp - 1];
	pre += (pSi ? "B" : "iB");
	std::cout << (pBytes / pow((float)unit, (float)exp)) << " " << pre.c_str();
}

int main(int argc, char** argv)
{
	const char* str = "001122334555566788999";
	c_uint length = strlen(str);
	bool inclusive = true;

	c_uint index = 0;
	sequence_list s = encodeRLE(str, length);

	sequence_list::iterator it = s.begin();
	for(c_uint i=0; i<length; ++i)
	{
		if(it != s.end())
		{
			if(i >= (*it)->offset + (*it)->length)
			{
				if(++it != s.end())
					std::cout << (char)(*it)->length;
			}
		}
		if(it != s.end())
		{
			if(i < (*it)->offset)
				std::cout  << str[i];
		}else{
			std::cout << str[i];
		}
	}

	return 0;
}