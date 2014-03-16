#ifndef CMPRSR_MERGE_H
#define CMPRSR_MERGE_H

#include "cmprsr_types.h"
#include "cmprsr_sequence.h"

struct merge
{
	sequence* first;
	sequence* second;
	c_uint value_distance;
	c_uint value_range;
	bool valid;

	merge(sequence* pFirst, sequence* pSecond)
		: first(pFirst),
		second(pSecond),
		value_distance(pFirst->value_distance(*pSecond)),
		value_range(pFirst->value_range(*pSecond)),
		valid(true)
	{
	}

	bool inRange(const sequence& pSequence) const
	{
		return (first->offset <= pSequence.offset && second->offset >= pSequence.offset)
			|| (first->offset >= pSequence.offset && second->offset <= pSequence.offset);
	}

	bool collides(const merge& pMerge) const
	{
		return first == pMerge.first || first == pMerge.second || second == pMerge.first || second == pMerge.second;
	}
};

struct merge_cmp_value_range_less
{
	bool operator()(const merge* pA, const merge* pB) const
	{
		return pA->value_range < pB->value_range;
	}
};

struct merge_cmp_value_distance_less
{
	bool operator()(const merge* pA, const merge* pB) const
	{
		return pA->value_distance < pB->value_distance;
	}
};

#endif