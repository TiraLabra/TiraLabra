#include "knuth_morris_pratt.hpp"
#include <iostream>
using namespace std;
int * build_transition_array(const char * needle, int needle_length) {
    if (needle_length < 0) return NULL;
    int * table = (int *) calloc(needle_length, sizeof(int));
    table[0] = 0;
    int tmp_idx = 0;
    for (int i = 1; i < needle_length;) {
        if (needle[tmp_idx] == needle[i]) {
            table[i] = tmp_idx + 1;
            ++i;
            ++tmp_idx;
        } else {
            if (tmp_idx != 0) {
                tmp_idx = table[tmp_idx-1];
            } else {
                table[i] = 0;
                ++i;
            }
        }
    }
    return table;
}

vector<int> kmp_search(const string & haystack, const string & needle, int upper_limit) {
    return kmp_search(haystack.c_str(), needle.c_str(), haystack.length(), needle.length(), upper_limit);
}

vector<int> kmp_search (const char * haystack, const char * needle,
    int haystack_length, int needle_length, int upper_limit) {
    if (haystack_length == -1) {
        haystack_length = strlen(haystack);
    }
    if (needle_length == -1) {
        needle_length = strlen(needle);
    }
    int * transition_table = build_transition_array(needle, needle_length);
    vector<int> positions;
    if (needle_length > haystack_length) return positions;
    int h_idx = 0, n_idx = 0;
    for (; h_idx < haystack_length && h_idx < haystack_length && upper_limit!=0;) {
        for (; n_idx < needle_length && h_idx < haystack_length &&
            haystack[h_idx] == needle[n_idx]; ++n_idx,++h_idx);
        if (n_idx == needle_length) {
            positions.push_back(h_idx-n_idx);
            --upper_limit;
            n_idx = transition_table[n_idx-1];
        } else {
            if (haystack[h_idx] != needle[n_idx]) {
                if(n_idx!=0) {
                    n_idx = transition_table[n_idx-1];
                } else {
                    ++h_idx;
                }
            }
        }
    }
    if (transition_table!=NULL) free(transition_table);
    return positions;
}
