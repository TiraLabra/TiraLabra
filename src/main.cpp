#include <math.h>
#include <string.h>

#include <iostream>

#include <direct.h>

#include "cmprsrlib.h"

#include "huffman.h"

long getFileSize(FILE* pFile)
{
	long position = ftell(pFile);
	fseek(pFile, 0, SEEK_END);
	long size = ftell(pFile);
	fseek(pFile, position, SEEK_SET);

	return size;
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

void printUsage()
{
	printf("Usage: cmprsr (command) (archive_name) (file_name)\n");
}

int main(int argc, char** argv)
{
	if(argc < 4)
	{
		printUsage();
		return 0;
	}

	char archivePath[256];
	char filePath[256];

	_getcwd(archivePath, 256);
	_getcwd(filePath, 256);

	strcat(archivePath, "\\");
	strcat(archivePath, argv[2]);

	strcat(filePath, "\\");
	strcat(filePath, argv[3]);

	if(strcmp(argv[1], "a") == 0)
	{
		FILE* archive = fopen(archivePath, "wb");
		FILE* file = fopen(filePath, "rb");

		if(archive == 0)
		{
			printf("Error opening archive\n");
			return 0;
		}

		if(file == 0)
		{
			printf("Error opening file\n");
			return 0;
		}

		huffmanEncodeFile(file, archive);

		fclose(archive);
		
		long fileSize = getFileSize(file);
		fclose(file);
		
		archive = fopen(archivePath, "rb");
		
		long archiveSize = getFileSize(archive);
		fclose(archive);

		printf("Packed ");
		printBytes(fileSize, false);
		printf(" to ");
		printBytes(archiveSize, false);
		printf("\n");
	}else if(strcmp(argv[1], "e") == 0)
	{
		FILE* archive = fopen(archivePath, "rb");
		FILE* file = fopen(filePath, "wb");

		if(archive == 0)
		{
			printf("Error opening archive\n");
			return 0;
		}

		if(file == 0)
		{
			printf("Error opening file\n");
			return 0;
		}

		huffmanDecodeFile(archive, file);

		fclose(file);

		long archiveSize = getFileSize(archive);
		fclose(archive);

		file = fopen(filePath, "rb");
		long fileSize = getFileSize(file);
		fclose(file);

		printf("Unpacked ");
		printBytes(archiveSize, false);
		printf(" to ");
		printBytes(fileSize, false);
		printf("\n");
	}else{
		printUsage();
	}

	return 0;
}
