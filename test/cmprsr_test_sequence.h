#include <cxxtest/TestSuite.h>

#include "cmprsr_sequence.h"

class SequenceTest : public CxxTest::TestSuite
{
public:
	void testSequenceOverlap()
	{
		sequence s1(1, 3, 5, 7);
		sequence s2(4, 8, 8, 9);

		TS_ASSERT(s1.no_offset_overlap(s2));
		TS_ASSERT(s1.no_value_overlap(s2, true));
		
		s1.offset += 1;
		s1.last += 2;

		TS_ASSERT(!s1.no_offset_overlap(s2));
		TS_ASSERT(!s1.no_offset_overlap(s2));
		TS_ASSERT(!s2.range_before(s1, true));
	}

	void testSequenceBefore()
	{
		sequence s1(1, 3, 5, 7);
		sequence s2(4, 2, 8, 9);

		TS_ASSERT(s1.block_before(s2));
		TS_ASSERT(!s2.block_before(s1));
		TS_ASSERT(s1.range_before(s2, true));
		
		s1.offset += 7;
		s1.last += 2;

		TS_ASSERT(!s1.block_before(s2));
		TS_ASSERT(s2.block_before(s1));
		TS_ASSERT(!s2.range_before(s1, true));
	}

	void testSequenceContain()
	{
		sequence s1(0, 10, 2, 5);
		sequence s2(10, 2, 3, 4);

		TS_ASSERT(s1.can_contain(s2, true));
		TS_ASSERT(!s2.can_contain(s1, true));

		s2.first = s1.first;
		s2.last = s1.last;

		TS_ASSERT(s1.can_contain(s2, true));
		TS_ASSERT(s1.can_contain(s2, true));

		s2.last += 5;

		TS_ASSERT(!s1.can_contain(s2, true));
		TS_ASSERT(s2.can_contain(s1, true));
	}

	void testSequenceValueRangeDistance()
	{
		sequence s1(5, 6, 7, 8);
		sequence s2(13, 100, 8, 9);

		TS_ASSERT_EQUALS(s1.value_range(s2), 2);
		TS_ASSERT_EQUALS(s1.value_distance(s2), 0);

		s1.first = 5;
		s1.last = 1;

		TS_ASSERT(s2.value_range(s1) == 8);
		TS_ASSERT(s2.value_distance(s1) == 3);
	}
};