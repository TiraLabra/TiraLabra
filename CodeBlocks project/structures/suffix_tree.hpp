#ifndef SUFFIX_TREE
#define SUFFIX_TREE

#include <cstdlib>
#include <cstring>
#include <string>
#include <vector>
using namespace std;


class suffix_tree{
    public:
    class node;
    class edge;
    class node {
        private:
        int index;
        edge * outgoing_edges[256];
        public:
        node(int index);
        int get_index();
        void add_edge(edge * e, char index);
        edge * get_edge(char index);
        bool has_edge(char index);
    };
    class edge {
        private:
        int substr_start;
        int substr_len;
        node * from;
        node * to;
        public:
        edge(int substr_start, int substr_len, node * from,
        node * to);
        int get_start();
        int get_length();
        node * get_from();
        node * get_to();
        //void branch();
    };
    suffix_tree(node * root);
    ~suffix_tree();
    int get_first_occurrence(const string & needle);
    int get_first_occurrence(const char * needle, int length = -1);
    vector<int> get_all_occurrences(const string & needle);
    vector<int> get_all_occurrences(const char * needle, int length = -1);
    private:
    node * root;
};

#endif
