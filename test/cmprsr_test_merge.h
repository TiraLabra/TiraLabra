#ifndef CMPRSR_MERGE_TEST
#define CMPRSR_MERGE_TEST

#include <cxxtest/TestSuite.h>

#include "cmprsr_sequence.h"
#include "cmprsr_merge.h"

class MergeTest : public CxxTest::TestSuite
{
public:
	void testMerge()
	{
		sequence s1(0, 3, 50, 53);
		sequence s2(3, 3, 54, 55);
		merge m(&s1, &s2);
	}
};

#endif