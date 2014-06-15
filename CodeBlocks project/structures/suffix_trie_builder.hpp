#ifndef SUFFIX_TRIE_BUILDER
#define SUFFIX_TRIE_BUILDER

#include <cstring>
#include <string>
#include "suffix_trie.hpp"
using namespace std;

class suffix_trie_builder {
    private:
    suffix_trie::edge * build_edge(suffix_trie::node * from, suffix_trie::node * to, char index);
    public:
    suffix_trie * build_trie(const char * str, int length=-1);
    suffix_trie * build_trie(const string & str);
};

#endif // SUFFIX_TRIE_BUILDER
