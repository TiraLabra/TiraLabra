#include "suffix_tree.hpp"

/*
    node methods
*/

suffix_tree::node::node(int index) {
    this->index = index;
}

void suffix_tree::node::add_edge(edge * e, char index) {
    this->outgoing_edges[index] = e;
}

bool suffix_tree::node::has_edge(char index) {
    return this->outgoing_edges[index]!=NULL;
}

edge * suffix_tree::node::get_edge(char index) {
    return this->outgoing_edges[index];
}

int suffix_tree::node::get_index() {
    return this->index;
}

/*
    edge methods

*/

suffix_tree::edge::edge(int substr_start, int substr_len, node * from, node * to) {
    this->substr_start = substr_start;
    this->substr_len = substr_len;
    this->from = from;
    this->to = to;
}

/*
    suffix tree methods
*/
