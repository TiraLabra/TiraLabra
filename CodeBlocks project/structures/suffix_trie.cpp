#include "suffix_trie.hpp"
#include <iostream>
using namespace std;
// node methods

bool suffix_trie::node::edge_exists(char index) {
    return this->outgoing_edges.find(index) != this->outgoing_edges.end();
}

suffix_trie::edge * suffix_trie::node::get_edge(char index) {
    return this->outgoing_edges.find(index)->second;
}

void suffix_trie::node::add_edge(char index, suffix_trie::edge * e) {
    this->outgoing_edges.insert(make_pair(index,e));
}

suffix_trie::node::~node() {
    map<char, suffix_trie::edge*>::iterator it = this->outgoing_edges.begin();
    while (it!=this->outgoing_edges.end()) {
        delete it->second;
        ++it;
    }
}

map<char,suffix_trie::edge*> suffix_trie::node::get_edges() {
    return this->outgoing_edges;
}

// edge methods

void suffix_trie::edge::add_index(int index) {
    this->s_index.push_back(index);
}

suffix_trie::edge::edge(suffix_trie::node * from, suffix_trie::node * to) {
    this->from = from;
    this->to = to;
}
suffix_trie::edge::~edge() {
    delete to;
}

// trie

suffix_trie::suffix_trie(node * root) {
    this->root = root;
}

suffix_trie::~suffix_trie() {
    delete this->root;
}

int suffix_trie::find_first(const char * needle, int needle_length) {
    if (needle_length==-1) {
        needle_length = strlen(needle);
    }
    int index = NOT_FOUND;
    suffix_trie::node * temp_node = this->root;
    for (int i = 0; i < needle_length; ++i) {
        if (!temp_node->edge_exists(needle[i])) {
            index = NOT_FOUND;
            break;
        } else {
            suffix_trie::edge * c_edge = temp_node->get_edge(needle[i]);
            index = c_edge->s_index[c_edge->s_index.size()-1];
            temp_node = c_edge->to;
        }
    }
    return index;
}

ostream & operator <<(ostream & stream, suffix_trie::node & c_node){
    map<char, suffix_trie::edge*> edges = c_node.get_edges();
    if (edges.size() == 0) return stream;
    map<char, suffix_trie::edge*>::iterator it = edges.begin();
    for(; it!=edges.end(); ++it) {
        char val = it->first;
        stream << val << endl;
        stream << *(it->second->to) << endl;
    }
    return stream;
}

ostream & operator <<(ostream & stream, suffix_trie & trie) {
    stream << *trie.get_root();
    return stream;
}

int suffix_trie::find_first(const string & needle) {
    return this->find_first(needle.c_str(), needle.length());
}

suffix_trie::node * suffix_trie::get_root() {
    return this->root;
}
