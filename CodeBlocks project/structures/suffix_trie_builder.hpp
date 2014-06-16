#ifndef SUFFIX_TRIE_BUILDER
#define SUFFIX_TRIE_BUILDER

#include <cstring>
#include <string>
#include "suffix_trie.hpp"
using namespace std;

/*! \file */

/*! \a suffix_trie_builder has the purpose to build a suffix trie from a given string */
class suffix_trie_builder {
    private:
    /*! build_edge creates a \a suffix_trie::edge with given both its end nodes -
    \a suffix_trie::node * from and \a suffix_trie::node * to. \a index is the value (weight)
    of the edge meaning that the pointer to the edge will be stored in a map with a key \a index in
    the \a from node.
    suffix_trie::edge * build_edge(suffix_trie::node * from, suffix_trie::node * to, char index);
    public:
    /*!
    returns a pointer to a \a suffix_trie. \a str is the text which will be processed and
    \a length is the length of it. If \a length is ommited, then \a strlen is used to compute it.
    */
    suffix_trie * build_trie(const char * str, int length=-1);
    /*!
    build_trie return a pointer to a \a suffix_trie.
    */
    suffix_trie * build_trie(const string & str);
};

#endif // SUFFIX_TRIE_BUILDER
