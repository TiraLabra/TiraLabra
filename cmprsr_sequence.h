#ifndef CMPRSR_SEQUENCE_H
#define CMPRSR_SEQUENCE_H

#include <algorithm>

#include "cmprsr_types.h"

struct sequence
{
	c_offset offset;
	c_size length;
	c_data first;
	c_data last;

	sequence(const c_offset& pOffset, const c_size& pLength, const c_data& pFirst, const c_data& pLast)
		: offset(pOffset),
		length(pLength),
		first(pFirst),
		last(pLast)
	{
	}
	
	bool no_offset_overlap(const sequence& pSequence) const
	{
		return ((offset < pSequence.offset) && (offset + length) <= pSequence.offset)
			|| ((pSequence.offset < offset) && (pSequence.offset + pSequence.length) <= offset);
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

	c_data value_distance(const sequence& pSequence) const
	{
		return std::max(std::max(first, last), std::max(first, last)) - std::min(std::min(first, last), std::min(pSequence.first, pSequence.last));;
	}
};

struct seq_cmp_length_less
{
	bool operator()(const sequence& pA, const sequence& pB) const
	{
		return pA.length < pB.length;
	}
};

struct seq_cmp_length_greater
{
	bool operator()(const sequence& pA, const sequence& pB) const
	{
		return pA.length > pB.length;
	}
};

struct seq_cmp_offset_less
{
	bool operator()(const sequence& pA, const sequence& pB) const
	{
		return pA.offset < pB.offset;
	}
};

struct seq_cmp_offset_greater
{
	bool operator()(const sequence& pA, const sequence& pB) const
	{
		return pA.offset > pB.offset;
	}
};

#endif