#ifndef CMPRSR_MERGE_H
#define CMPRSR_MERGE_H

#include "cmprsr_types.h"
#include "cmprsr_sequence.h"

struct merge
{
	//sequence* first;
	//sequence* second;
	//c_data min;
	//c_data max;
	c_uint first;
	c_uint second;
	c_uint distance;

	merge(const c_uint& pFirst, const c_uint& pSecond, const c_uint& pDistance)
		: first(pFirst),
		second(pSecond),
		distance(pDistance)
	{
	}

	/*merge(sequence* pFirst, sequence* pSecond)
		: first(pFirst),
		second(pSecond),
		min(std::min(std::min(first->first, first->last), std::min(second->first, second->last))),
		max(std::max(std::max(first->first, first->last), std::min(second->first, second->last))),
		distance(max - min)
	{
	}*/

	/*bool inRange(const sequence& pSequence) const
	{
		return (first->offset <= pSequence.offset && second->offset >= pSequence.offset)
			|| (first->offset >= pSequence.offset && second->offset <= pSequence.offset);
	}*/

	bool collides(const merge& pMerge) const
	{
		return first == pMerge.first || first == pMerge.second || second == pMerge.first || second == pMerge.second;
	}
};

struct merge_cmp_distance_less
{
	bool operator()(const merge& pA, const merge& pB) const
	{
		return pA.distance < pB.distance;
	}
};

struct merge_cmp_distance_greater
{
	bool operator()(const merge& pA, const merge& pB) const
	{
		return pA.distance > pB.distance;
	}
};

#endif