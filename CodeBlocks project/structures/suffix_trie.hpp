#ifndef SUFFIX_TRIE
#define SUFFIX_TRIE

#include <cstring>
#include <string>
#include <vector>
#include <map>
#include <iostream>
#include "../constants.hpp"
using namespace std;

class suffix_trie {
    public:
    class node;
    class edge;
    class node {
        private:
        map<char,suffix_trie::edge*> outgoing_edges;
        public:
        bool edge_exists(char index);
        suffix_trie::edge * get_edge(char index);
        void add_edge(char index, suffix_trie::edge * e);
        map<char,suffix_trie::edge*> get_edges();
        ~node();
        friend ostream & operator<<(ostream & stream, node & c_node);
    };
    class edge {
        public:
        vector<int> s_index;
        void add_index(int index);
        suffix_trie::node * from;
        suffix_trie::node * to;
        edge(suffix_trie::node * from, suffix_trie::node * to);
        ~edge();
    };
    private:
    node * root;
    public:
    static const char STOP_CHAR = 127;
    suffix_trie(node * root);
    ~suffix_trie();
    int find_first(const char * needle, int needle_length=-1);
    int find_first(const string & needle);
    vector<int> find_all(const char * needle, int needle_length=-1);
    vector<int> find_all(const string & needle);
    suffix_trie::node * get_root();
    friend ostream & operator<<(ostream & stream, suffix_trie & trie);
};

#endif // SUFFIX_TRIE
