#ifndef SUFFIX_TREE_BUILDER
#define SUFFIX_TREE_BUILDER

#include <cstring>
#include <string>
#include "suffix_tree.hpp"
using namespace std;

class suffix_tree_builder {
    protected:
    static const char stop_char = '\0';
    public:
    virtual suffix_tree * build_tree(const string & haystack) = 0;
    virtual suffix_tree * build_tree(const char * haystack, int length = -1) = 0;
};

#endif // SUFFIX_TREE_BUILDER
