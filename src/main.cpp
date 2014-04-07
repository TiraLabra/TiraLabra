#include <math.h>
#include <string.h>

#include <iostream>

#include "cmprsrlib.h"

#include "huffman.h"

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
	const char* str = "00112233455556678899955";
	c_uint length = strlen(str);
	bool inclusive = true;

	int frequencies[256] = {0};
	const char* ptr = str;
	while(*ptr != '\0')
		++frequencies[*ptr++];

	Node* root = buildTree(frequencies, 255);

	HuffmanCodeMap codes;
	generateCodes(root, HuffmanCode(), codes);
	
	delete root;

	for(HuffmanCodeMap::const_iterator it = codes.begin(); it != codes.end(); ++it)
	{
		std::cout << it->first << " ";
		for(unsigned int i=0; i<it->second.size(); ++i)
			std::cout << it->second[i];
		std::cout << std::endl;
	}

	return 0;
}
