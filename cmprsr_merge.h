#ifndef CMPRSR_MERGE_H
#define CMPRSR_MERGE_H

#include "cmprsr_types.h"

struct merge
{
	c_offset first;
	c_offset second;
	c_data distance;

	merge(const c_offset& pFirst, const c_offset& pSecond, const c_data& pDistance)
		: first(pFirst),
		second(pSecond),
		distance(pDistance)
	{
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