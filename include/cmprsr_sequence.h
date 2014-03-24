#ifndef CMPRSR_SEQUENCE_H
#define CMPRSR_SEQUENCE_H

#include <algorithm>

#include "cmprsr_types.h"

struct sequence
{
	/// Sequence offset.
	c_uint offset;
	/// Sequence length.
	c_uint length;
	/// First value in sequence.
	c_data first;
	/// Last value in sequence.
	c_data last;

	/// Class constructor.
	/// \param pOffset Sequence start.
	/// \param pLength Sequence length.
	/// \param pFirst First value in sequence.
	/// \param pLast Last value in sequence.
	sequence(const c_uint& pOffset, const c_uint& pLength, const c_data& pFirst, const c_data& pLast)
		: offset(pOffset),
		length(pLength),
		first(pFirst),
		last(pLast)
	{
	}

	/// Checks if this sequence is before the given sequence.
	/// \param pSequence Sequence to check against.
	/// \return True if this sequence is before the given sequence.
	bool block_before(const sequence& pSequence) const
	{
		return offset + length <= pSequence.offset;
	}
	
	/// Checks if the sequence ranges overlap.
	/// \param pSequence Sequence to check against.
	/// \return True if this sequence doesn't overlap with the given sequence.
	bool no_offset_overlap(const sequence& pSequence) const
	{
		return ((offset < pSequence.offset) && (offset + length) <= pSequence.offset)
			|| ((pSequence.offset < offset) && (pSequence.offset + pSequence.length) <= offset);
	}

	/// Checks if this sequence's value range is before the given sequence's.
	/// \param pSequence Sequence to check against.
	/// \param pInclusive Check inclusively or exclusively.
	/// \return True if this sequence's value range is before the given sequence's.
	bool range_before(const sequence& pSequence, const bool& pInclusive) const
	{
		if(pInclusive)
			return first <= pSequence.first && first <= pSequence.last && last <= pSequence.first && last <= pSequence.last;
		else
			return first < pSequence.first && first < pSequence.last && last < pSequence.first && last < pSequence.last;
	}

	/// Checks if the sequences' value ranges don't overlap.
	/// \param pSequence Sequence to check against.
	/// \param pInclusive Check inclusively or exclusively.
	/// \return True if the sequence's value ranges don't overlap.
	bool no_value_overlap(const sequence& pSequence, const bool& pInclusive) const
	{
		if(pInclusive)
			return ((first <= pSequence.first && first <= pSequence.last && last <= pSequence.first && last <= pSequence.last)
				|| (first >= pSequence.first && first >= pSequence.last && last >= pSequence.first && last >= pSequence.last));
		else
			return ((first < pSequence.first && first < pSequence.last && last < pSequence.first && last < pSequence.last)
				|| (first > pSequence.first && first > pSequence.last && last > pSequence.first && last > pSequence.last));
	}

	/// Checks if this sequence can contain the given sequence.
	/// \param pSequence Sequence to check against.
	/// \param pInclusive Check inclusively or exclusively.
	/// \return True if this sequence's value range can contain the given sequence's value range.
	bool can_contain(const sequence& pSequence, const bool& pInclusive) const
	{
		if(pInclusive)
			return (first <= pSequence.first && first <= pSequence.last && last >= pSequence.first && last >= pSequence.last)
			|| (first >= pSequence.first && first >= pSequence.last && last <= pSequence.first && last <= pSequence.last);
		else
			return (first < pSequence.first && first < pSequence.last && last > pSequence.first && last > pSequence.last)
				|| (first > pSequence.first && first > pSequence.last && last < pSequence.first && last < pSequence.last);
	}

	/// Gets the minimum to maximum distance between the sequences' values.
	/// \param pSequence Sequence to check against.
	/// \return Minimum to maximum distance between the sequences' values.
	c_uint value_range(const sequence& pSequence) const
	{
		return std::abs(std::max(std::max(first, last), std::max(pSequence.first, pSequence.last))
			- std::min(std::min(first, last), std::min(pSequence.first, pSequence.last)));
	}

	/// Gets the distance between the sequences' values.
	/// \param pSequence Sequence to check against.
	/// \return Distance between sequences' values.
	c_uint value_distance(const sequence& pSequence) const
	{
		return std::min(std::abs(std::max(first, last) - std::min(pSequence.first, pSequence.last)),
			std::abs(std::max(pSequence.first, pSequence.last) - std::min(first, last)));
	}
};

typedef std::pair<sequence*, sequence*> sequence_pair;

struct seq_cmp_offset_less
{
	bool operator()(const sequence* pA, const sequence* pB) const
	{
		return pA->offset < pB->offset;
	}
};

struct seq_cmp_length_greater
{
	bool operator()(const sequence* pA, const sequence* pB) const
	{
		return pA->length > pB->length;
	}
};

struct seq_cmp_value_less
{
	bool operator()(const sequence* pA, const sequence* pB) const
	{
		return std::min(pA->first, pA->last) < std::min(pB->first, pB->last);
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