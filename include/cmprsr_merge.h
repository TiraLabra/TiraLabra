#ifndef CMPRSR_MERGE_H
#define CMPRSR_MERGE_H

#include <set>

#include "cmprsr_types.h"
#include "cmprsr_sequence.h"

struct merge
{
	/// First sequence in merge.
	sequence* first;
	/// Second sequence in merge.
	sequence* second;
	/// Distance between sequences' values.
	c_uint value_distance;
	/// Range between sequence's values.
	c_uint value_range;

	/// Class constructor.
	/// \param pFirst First sequence.
	/// \param pSecond Second sequence.
	merge(sequence* pFirst, sequence* pSecond)
		: first(pFirst),
		second(pSecond),
		value_distance(pFirst->value_distance(*pSecond)),
		value_range(pFirst->value_range(*pSecond))
	{
	}

	/// Checks the given sequence is in this merge's range.
	/// \param pSequence Sequence to check against.
	/// \return True if sequence is in this merge's range.
	bool inRange(const sequence& pSequence) const
	{
		return (first->offset <= pSequence.offset && second->offset >= pSequence.offset)
			|| (first->offset >= pSequence.offset && second->offset <= pSequence.offset);
	}

	/// Checks if this and the given merge collide.
	/// \param pMerge Merge to check against.
	/// \return True if the merges share a sequence.
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