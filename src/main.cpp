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
	unsigned int length = strlen(str);

	uint8_t* a = new uint8_t[length];
	memcpy(a, str, length);

	std::cout << "Original:" << std::endl;
	for(unsigned int i = 0; i < length; ++i)
		std::cout << a[i];
	std::cout << std::endl;

	uint8_t* b;
	unsigned int dstLength;
	huffmanEncode(a, length, b, dstLength);

	std::cout << "Encoded:" << std::endl;
	for(unsigned int i = 0; i < dstLength; ++i)
		std::cout << b[i];
	std::cout << std::endl;

	uint8_t* c;
	unsigned int dlen;
	huffmanDecode(b, dstLength, c, dlen);

	std::cout << "Decoded:" << std::endl;
	for(unsigned int i = 0; i < dlen; ++i)
		std::cout << c[i];
	std::cout << std::endl;


	return 0;
}
