#include "huffman.h"

#include <queue>

typedef std::vector<bool> HuffmanCode;
typedef std::map<char, HuffmanCode> HuffmanCodeMap;

Node* buildTree(const int* pFrequencies, const unsigned int& pFrequenciesCount)
{
	std::priority_queue<Node*, std::vector<Node*>, NodeCmp> trees;

	for(unsigned int i=0; i<pFrequenciesCount; ++i)
	{
		if(pFrequencies[i] != 0)
			trees.push(new Node(0, 0, pFrequencies[i], (char)i));
	}

	while(trees.size() > 1)
	{
		Node* childL = trees.top();
		trees.pop();
	
		Node* childR = trees.top();
		trees.pop();

		Node* parent = new Node(childL, childR, childL->Frequency + childR->Frequency, 0);
		trees.push(parent);
	}

	return trees.top();
}

void generateCodes(const Node* pNode, const HuffmanCode& pPrefix, HuffmanCodeMap& pOutCodes)
{
	if(pNode->Left == 0 && pNode->Right == 0)
	{
		pOutCodes[pNode->Character] = pPrefix;
	}else{
		HuffmanCode leftPrefix = pPrefix;
		leftPrefix.push_back(false);
		generateCodes(pNode->Left, leftPrefix, pOutCodes);

		HuffmanCode rightPrefix = pPrefix;
		rightPrefix.push_back(true);
		generateCodes(pNode->Right, rightPrefix, pOutCodes);
	}
}