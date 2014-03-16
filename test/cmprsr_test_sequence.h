#include <cxxtest/TestSuite.h>

#include "cmprsr_sequence.h"

class SequenceTest : public CxxTest::TestSuite
{
public:
	void testSequence()
	{
		sequence s1(2, 5, 10, 12);
		sequence s2(4, 5, 11, 13);

		bool success = true;

		TS_ASSERT(!s1.no_offset_overlap(s2));
		TS_ASSERT(!s1.no_value_overlap(s2, false));
		TS_ASSERT(!s1.can_contain(s2, false));
		TS_ASSERT(s1.value_distance(s2) == 3);

		s1.offset = 9;
		s2.first = 14;

		TS_ASSERT(!s1.no_offset_overlap(s2));
		TS_ASSERT(s1.no_value_overlap(s2, false));
		TS_ASSERT(!s1.can_contain(s2, false));
		TS_ASSERT(s1.value_distance(s2) == 4);
	}
};