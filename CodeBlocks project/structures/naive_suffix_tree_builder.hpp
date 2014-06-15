#ifndef NAIVE_SUFFIX_TREE_BUILDER
#define NAIVE_SUFFIX_TREE_BUILDER

#include <cstdlib>
#include <cstring>
#include <string>
#include "suffix_tree_builder.hpp"
#include "suffix_tree.hpp"
using namespace std;

class naive_suffix_tree_builder : public suffix_tree_builder {
    private:
    void add_substring(const char * str, int str_len,
     int begining, int start_s, int len, suffix_tree::node * c_node);
    public:
    suffix_tree * build_tree(const string & haystack);
    suffix_tree * build_tree(const char * haystack, int length = -1);
    void branch(int index, int start_s, int end_s, suffix_tree::node * from, suffix_tree::node * to, suffix_tree::edge * e);
};

#endif
