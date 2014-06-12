#include "naive_suffix_tree_builder.hpp"

void naive_suffix_tree_builder::add_substring(const char * str,
    int begining, int start_s, int len, suffix_tree::node * c_node) {
    suffix_tree::edge * e = NULL;
    if (c_node->has_edge(str[start_s])) {
        e = c_node->get_edge(str[start_s]);
    } else {
        e = new suffix_tree::edge(start_s, len, c_node, new suffix_tree::node(begining));
        c_node->add_edge(e);
    }
}

suffix_tree * naive_suffix_tree_builder::build_tree(const string & haystack) {
    return this->build_tree(haystack.c_str(), haystack.length());
};

suffix_tree * naive_suffix_tree_builder::build_tree(const char * haystack, int length) {

    if (length == -1) {
        length = strlen(haystack);
    }
    suffix_tree::node * root = new suffix_tree::node(-1);

    for (int i = 0; i < length; ++i) {
        suffix_tree::node * c_node = new suffix_tree::node(i);

    }

    return new suffix_tree(root);
};
