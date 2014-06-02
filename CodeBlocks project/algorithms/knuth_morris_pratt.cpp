#include "knuth_morris_pratt.hpp"

int * precompute_transition(const char * needle, int needle_length) {
    int * transition_array = (int*) calloc(needle_length, sizeof(int));
    transition_array[0] = 0;
    for (int i = 0; i < needle_length-1; ++i) {
        char c = needle[i+1];
        int v = transition_array[i];
        while (needle[v+1]!=c && v!=0) v = transition_array[v];
        if (needle[v+1]==c) {
            transition_array[i+1] = v+1;
        } else {
            transition_array[i+1] = 0;
        }
    }
    return transition_array;
}
