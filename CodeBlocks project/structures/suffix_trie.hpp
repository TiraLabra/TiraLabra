#ifndef SUFFIX_TRIE
#define SUFFIX_TRIE

#include <cstring>
#include <string>
#include <vector>
#include <map>
#include <iostream>
#include "../constants.hpp"
using namespace std;

/*! \file */

/*!
    \a suffix_trie is a tree structure which contains information about a given text. It supports
    searching in the text.
*/
class suffix_trie {
    public:
    class node;
    class edge;
    /*!
        \a node is the node in the tree
    */
    class node {
        private:
        /*!
            a map which stores all of the outgoing edges of the given node.
        */
        map<char,suffix_trie::edge*> outgoing_edges;
        public:
        /*!
            return true if the edge is stored in the map.
        */
        bool edge_exists(char index);
        /*!
            return a pointer to a \a suffix_trie::edge with a given index(key)
        */
        suffix_trie::edge * get_edge(char index);
        /*!
            adds an \a edge \a e with a given index.
        */
        void add_edge(char index, suffix_trie::edge * e);
        /*!
            returns all outgoing edges
        */
        map<char,suffix_trie::edge*> get_edges();
        /*!
            destroys the node. It will also destroy recursively all edges and nodes.
        */
        ~node();
        /*!
            overrides the << operator for printing the node
        */
        friend ostream & operator<<(ostream & stream, node & c_node);
    };
    /*!
        \a suffix_trie::edge is an edge in the trie.
    */
    class edge {
        public:
        /*!
            stored indeces of all starting points in the text
        */
        vector<int> s_index;
        /*!
            adds a new \a index to \a s_index
        */
        void add_index(int index);
        /*!
            starting \a suffix_trie::node
        */
        suffix_trie::node * from;
        /*!
            ending \a suffix_trie::node;
        */
        suffix_trie::node * to;
        /*!
            creates a \a suffix_trie::edge with a given starting \a node and ending \a node
        */
        edge(suffix_trie::node * from, suffix_trie::node * to);
        /*!
            destroys the edge and the outgoing node recursively
        */
        ~edge();
    };
    private:
    /*!
        root of the tree
    */
    node * root;
    public:
    /*!
        stop character which is added to the end of the string
    */
    static const char STOP_CHAR = 127;
    /*!
        creates a \a suffix_trie with a given \a suffix_trie::node root pointer
    */
    suffix_trie(node * root);
    /*!
        destroys the suffix trie and all of its nodes and edges recursively
    */
    ~suffix_trie();
    /*!
        returns the first occurrence of the \a needle in the preprocessed text.
        If \a needle_length is ommited, then the length is computed by using strlen().
    */
    int find_first(const char * needle, int needle_length=-1);
    /*!
        returns the first occurrence of the \a needle in the preprocessed text.
    */
    int find_first(const string & needle);
    /*!
        returns all occurrences of the \a needle in the preprocessed text.
        If \a needle_length is ommited, then the length is computed by using strlen().
    */
    vector<int> find_all(const char * needle, int needle_length=-1);
    /*!
        returns all occurrences of the \a needle in the preprocessed text.
    */
    vector<int> find_all(const string & needle);
    /*!
        returns the root of the trie
    */
    suffix_trie::node * get_root();
    /*!
        overrides the << operator to print the \a suffix_trie
    */
    friend ostream & operator<<(ostream & stream, suffix_trie & trie);
};

#endif // SUFFIX_TRIE
