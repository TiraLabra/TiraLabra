#include "cmprsrlib.h"

int main(int argc, char** argv)
{
	std::string str = "46780123";
	mergeSequences(findSequences(str.c_str(), str.size(), true), true);

	return 0;
}