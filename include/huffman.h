#ifndef CMPRSR_HUFFMAN_H
#define CMPRSR_HUFFMAN_H

#include <vector>
#include <map>

typedef std::vector<bool> HuffmanCode;
typedef std::map<char, HuffmanCode> HuffmanCodeMap;

/// Huffman tree node class.
class Node
{
public:
	/// Left tree node.
	Node* Left;
	/// Right tree node.
	Node* Right;
	/// Character frequency.
	const int Frequency;
	/// Node character.
	const char Character;

	/// Class constructor.
	/// \param pLeft Left tree node.
	/// \param pRight Right tree node.
	/// \param pFrequency Character frequency.
	/// \param pCharacter Node character.
	Node(Node* pLeft, Node* pRight, const int& pFrequency, const char& pCharacter)
		: Left(pLeft),
		Right(pRight),
		Frequency(pFrequency),
		Character(pCharacter)
	{
	}
	
	/// Class destructor.
	/// Deletes child nodes, if any.
	virtual ~Node()
	{
		if(Left)
			delete Left;
		if(Right)
			delete Right;
	}
};

struct NodeCmp
{
	bool operator()(const Node* pNode1, const Node* pNode2)
	{
		return pNode1->Frequency > pNode2->Frequency;
	}
};

/// Builds a huffman tree.
/// \param pFrequencies List of frequencies.
/// \param pFrequenciesCount Frequencies count.
/// \return Root tree node.
Node* buildTree(const int* pFrequencies, const unsigned int& pFrequenciesCount);

/// Generates huffman codes for each node in the tree.
/// \param pNode Root tree node.
/// \param pPrefix Code prefix.
/// \param pOutCodes Map to output huffman codes to.
void generateCodes(const Node* pNode, const HuffmanCode& pPrefix, HuffmanCodeMap& pOutCodes);

#endif