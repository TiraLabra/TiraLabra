#include "naive_suffix_tree_builder.hpp"

/*
void naive_suffix_tree_builder::branch(int index, int start_s, int len, suffix_tree::node * from,
    suffix_tree::node * to, suffix_tree::edge * e) {

}

void naive_suffix_tree_builder::add_substring(const char * str, int str_len,
    int begining, int start_s, int len, suffix_tree::node * c_node) {
    suffix_tree::edge * e = NULL;
    if (c_node->has_edge(str[start_s])) {
        e = c_node->get_edge(str[start_s]);
        int len = e->get_length();
        if (str_s+len >= str_len) {
            for (int k = 0; k < len && start_s+k < str_len; ++k) {
                if (str[k+e->get_start()] != str[start_s+k]) {
                    naive_suffix_tree_builder::branch(k, start_s, len, e->get_from(), e->get_to);
                }
            }
        }

        if (k==len) {
            if (start_s+k == str_len) {
                this->branch(k, start_s, len, e->get_from(), e->get_to);
            } else {
                this->add_substring(str, str_len, begining, start_s+k, str_len-k, e->get_to());
            }
        } else {
            this->branch(k, start_s, len, e->get_from(), e->get_to);
        }
    } else {
        e = new suffix_tree::edge(start_s, len, c_node, new suffix_tree::node(begining));
        c_node->add_edge(e, str[start_s]);
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
*/
