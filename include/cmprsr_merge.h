#ifndef CMPRSR_MERGE_H
#define CMPRSR_MERGE_H

#include "cmprsr_types.h"
#include "cmprsr_sequence.h"

struct merge
{
	sequence* first;
	sequence* second;
	c_uint distance;
	bool valid;

	merge(sequence* pFirst, sequence* pSecond)
		: first(pFirst),
		second(pSecond),
		distance(pFirst->value_distance(*pSecond)),
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

struct merge_cmp_distance_less
{
	bool operator()(const merge* pA, const merge* pB) const
	{
		return pA->distance < pB->distance;
	}
};

struct merge_cmp_distance_greater
{
	bool operator()(const merge* pA, const merge* pB) const
	{
		return pA->distance > pB->distance;
	}
};

#endif