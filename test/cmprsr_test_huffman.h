#include <cxxtest/TestSuite.h>

#include "huffman.h"

void createTestFiles(const char* pFilename1, const char* pFilename2, const char* pFilename3)
{
	const char* testStr = "testasdfqwertymananasdfyeahsupatestapplepie\n";
	unsigned int length = strlen(testStr);

	FILE* f = fopen(pFilename1, "wb");

	for(unsigned int i = 0; i < 100; ++i)
		fwrite(testStr, 1, length, f);
	fclose(f);

	f = fopen(pFilename1, "rb");
	FILE* fe = fopen(pFilename2, "wb");

	huffmanEncodeFile(f, fe);

	fclose(f);
	fclose(fe);

	f = fopen(pFilename3, "wb");
	fe = fopen(pFilename2, "rb");
		
	huffmanDecodeFile(fe, f);

	fclose(f);
	fclose(fe);
}

long int getFileSize(FILE* pFile)
{
	long int position = ftell(pFile);
	fseek(pFile, 0, SEEK_END);
	long int size = ftell(pFile);
	fseek(pFile, position, SEEK_SET);

	return size;
}

class HuffmanTest : public CxxTest::TestSuite
{
public:
	void testHuffmanMemory()
	{
		const char* str = "00112233455556678899955";
		unsigned int length = strlen(str);
		
		uint8_t* a = (uint8_t*)malloc(length);
		memcpy(a, str, length);

		uint8_t* b;
		unsigned int dstLength;
		huffmanEncode(a, length, b, dstLength);

		uint8_t* c;
		unsigned int dlen;
		huffmanDecode(b, dstLength, c, dlen);

		int m = memcmp(a, c, dlen);

		free(a);
		free(b);
		free(c);

		TS_ASSERT(m == 0);
	}

	void testHuffmanFile()
	{
		const char* filename1 = "huftest.txt";
		const char* filename2 = "huftest.txt.huf";
		const char* filename3 = "huftestresult.txt";

		createTestFiles(filename1, filename2, filename3);

		FILE* f = fopen(filename1, "rb");
		FILE* fe = fopen(filename3, "rb");

		long int s1 = getFileSize(f);
		long int s2 = getFileSize(fe);
		if(s1 != s2)
		{
			fclose(f);
			fclose(fe);

			TS_ASSERT(s1 == s2);

			return;
		}

		while(true)
		{
			int c1 = fgetc(f);
			int c2 = fgetc(fe);

			if(c1 == EOF || c2 == EOF)
				break;

			if(c1 != c2)
			{
				fclose(f);
				fclose(fe);
				TS_ASSERT(c1 == c2);
			}
		}

		fclose(f);
		fclose(fe);
	}
};
