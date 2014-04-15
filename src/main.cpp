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
	int length = strlen(str);

	char* s = new char[length];
	memcpy(s, str, length);
	char* d = new char[length * 2];
	memset(d, 0, length * 2);
	char* r = new char[length * 2];
	memset(r, 0, length * 4);

	std::cout << "Original:" << std::endl;
	for(int i = 0; i < length; ++i)
		std::cout << s[i];
	std::cout << std::endl;

	huffmanEncode(s, length, d, length * 2);

	std::cout << "Encoded:" << std::endl;
	for(int i = 0; i < length * 2; ++i)
		std::cout << d[i];
	std::cout << std::endl;

	huffmanDecode(d, length * 2, r, length * 4);

	std::cout << "Decoded:" << std::endl;
	for(int i = 0; i < length * 4; ++i)
		std::cout << r[i];
	std::cout << std::endl;

	return 0;
}
