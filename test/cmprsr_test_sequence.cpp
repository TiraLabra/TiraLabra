#include <cassert>

#include <string>

#include "cmprsr_sequence.h"

int main(int argc, char** argv)
{
	sequence s1(2, 5, 10, 12);
	sequence s2(4, 5, 11, 13);

	bool success = true;

	success &= !s1.no_offset_overlap(s2);
	success &= !s1.no_value_overlap(s2, false);
	success &= !s1.can_contain(s2, false);
	success &= s1.value_distance(s2) == 3;

	s1.offset = 9;
	s2.first = 14;
	success &= s1.no_offset_overlap(s2);
	success &= s1.no_value_overlap(s2, false);
	success &= !s1.can_contain(s2, false);
	success &= s1.value_distance(s2) == 4;

	return success ? EXIT_SUCCESS : EXIT_FAILURE;
}