#ifndef CMPRSR_SEQUENCE_H
#define CMPRSR_SEQUENCE_H

#include <algorithm>

#include "cmprsr_types.h"

struct sequence
{
	c_uint offset;
	c_uint length;
	c_data first;
	c_data last;
	//TODO: store min, max and ascending/descending

	sequence(const c_uint& pOffset, const c_uint& pLength, const c_data& pFirst, const c_data& pLast)
		: offset(pOffset),
		length(pLength),
		first(pFirst),
		last(pLast)
	{
	}

	bool block_before(const sequence& pSequence) const
	{
		return offset + length <= pSequence.offset;
	}
	
	bool no_offset_overlap(const sequence& pSequence) const
	{
		return ((offset < pSequence.offset) && (offset + length) <= pSequence.offset)
			|| ((pSequence.offset < offset) && (pSequence.offset + pSequence.length) <= offset);
	}

	bool range_before(const sequence& pSequence, const bool& pInclusive) const
	{
		if(pInclusive)
			return first <= pSequence.first && first <= pSequence.last && last <= pSequence.first && last <= pSequence.last;
		else
			return first < pSequence.first && first < pSequence.last && last < pSequence.first && last < pSequence.last;
	}

	bool no_value_overlap(const sequence& pSequence, const bool& pInclusive) const
	{
		if(pInclusive)
			return ((first <= pSequence.first && first <= pSequence.last && last <= pSequence.first && last <= pSequence.last)
				|| (first >= pSequence.first && first >= pSequence.last && last >= pSequence.first && last >= pSequence.last));
		else
			return ((first < pSequence.first && first < pSequence.last && last < pSequence.first && last < pSequence.last)
				|| (first > pSequence.first && first > pSequence.last && last > pSequence.first && last > pSequence.last));
	}

	bool can_contain(const sequence& pSequence, const bool& pInclusive) const
	{
		if(pInclusive)
			return (first <= pSequence.first && first <= pSequence.last && last >= pSequence.first && last >= pSequence.last)
			|| (first >= pSequence.first && first >= pSequence.last && last <= pSequence.first && last <= pSequence.last);
		else
			return (first < pSequence.first && first < pSequence.last && last > pSequence.first && last > pSequence.last)
				|| (first > pSequence.first && first > pSequence.last && last < pSequence.first && last < pSequence.last);
	}

	c_uint value_range(const sequence& pSequence) const
	{
		return std::abs(std::max(std::max(first, last), std::max(pSequence.first, pSequence.last))
			- std::min(std::min(first, last), std::min(pSequence.first, pSequence.last)));
	}

	c_uint value_distance(const sequence& pSequence) const
	{
		return std::min(std::abs(std::max(first, last) - std::min(pSequence.first, pSequence.last)),
			std::abs(std::max(pSequence.first, pSequence.last) - std::min(first, last)));
	}
};

typedef std::pair<sequence*, sequence*> sequence_pair;

struct seq_cmp_length_less
{
	bool operator()(const sequence* pA, const sequence* pB) const
	{
		return pA->length < pB->length;
	}
};

struct seq_cmp_length_greater
{
	bool operator()(const sequence* pA, const sequence* pB) const
	{
		return pA->length > pB->length;
	}
};

struct seq_cmp_offset_less
{
	bool operator()(const sequence* pA, const sequence* pB) const
	{
		return pA->offset < pB->offset;
	}
};

struct seq_cmp_offset_greater
{
	bool operator()(const sequence* pA, const sequence* pB) const
	{
		return pA->offset > pB->offset;
	}
};

struct seq_pair_cmp_distance_less
{
	bool operator()(const sequence_pair& pA, const sequence_pair& pB) const
	{
		return pA.first->value_distance(*pA.second) < pB.first->value_distance(*pB.second);
	}
};

#endif