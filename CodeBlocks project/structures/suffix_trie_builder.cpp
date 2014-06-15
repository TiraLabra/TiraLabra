#include "suffix_trie_builder.hpp"

suffix_trie * suffix_trie_builder::build_trie(const string & str) {
    return this->build_trie(str.c_str(), str.length());
}

suffix_trie * suffix_trie_builder::build_trie(const char * str, int length) {
    if (length == -1) {
        length = strlen(str);
    }
    suffix_trie::node * root = new suffix_trie::node();
    suffix_trie * trie = new suffix_trie(root);
    for (int i = length-1; i >= 0; --i) {
        suffix_trie::node * tmp_node = root;
        for (int j = i; j < length; ++j) {
            suffix_trie::edge * c_edge;
            if (tmp_node->edge_exists( str[j] )) {
                c_edge = tmp_node->get_edge(str[j]);
            } else {
                c_edge = this->build_edge(tmp_node, new suffix_trie::node(), str[j]);
            }
            tmp_node = c_edge->to;
            c_edge->add_index(i);
        }
        suffix_trie::edge * e =
            this->build_edge(tmp_node, new suffix_trie::node(), suffix_trie::STOP_CHAR);
        e->add_index(i);
    }
    return trie;
}

suffix_trie::edge * suffix_trie_builder::build_edge(suffix_trie::node * from, suffix_trie::node * to, char index) {
    suffix_trie::edge * e = new suffix_trie::edge(from, to);
    from->add_edge(index,e);
    return e;
}
