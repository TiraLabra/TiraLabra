#include "z_algorithm.hpp"
#include <iostream>
using namespace std;

bool z_algo_compare(const char * haystack, const char * needle, int index_a,
int index_b, int haystack_length, int needle_length, int start) {
    char a,b;
    if (index_a >= needle_length) {
        a = haystack[index_a - needle_length];
    } else {
        a = needle[index_a];
    }
    if (index_b >= needle_length) {
        b = haystack[index_b+start - needle_length];
    } else {
        b = needle[index_b];
    }
    return (a == b);
}

vector<int> z_algo_get_positions(const string & haystack, const string & needle, int start,
int upper_bound_cnt) {
    return z_algo_get_positions(haystack.c_str(), needle.c_str(), start,haystack.length(),
    needle.length(), upper_bound_cnt);
}

vector<int> z_algo_get_positions(const char * haystack, const char * needle,
 int start, int haystack_length, int needle_length, int upper_bound_cnt) {
    vector<int> positions;
    if (haystack_length == -1) {
        haystack_length = strlen(haystack);
    }
    if (needle_length == -1) {
        needle_length = strlen(needle);
    }
    if (needle_length == 0) return positions;
    if (needle_length > haystack_length) return positions;
    int total_length = haystack_length + needle_length-start;
    int * z_array = (int *) calloc(total_length, sizeof(int));
    int left=0, right=0;
    for (int i = 0; i < total_length && upper_bound_cnt!=0; ++i) {
        if (right < i) {
            left = right = i;
            extend_right(
                    right, left, total_length, haystack, needle, haystack_length, needle_length,start);
            z_array[i] = right-left;
            --right;
        } else {
            int k = i-left;
            if (z_array[k] < right-i+1) {
                z_array[i] = z_array[k];
            } else {
                left = i;
                extend_right(
                    right, left, total_length, haystack, needle, haystack_length, needle_length,start);
                z_array[i] = right-left;
                --right;
            }
        }
        if (z_array[i]>=needle_length && i>=needle_length) {
            positions.push_back(i+start-needle_length);
            --upper_bound_cnt;
        }
    }
    free(z_array);
    return positions;
 }

void extend_right(int &right, int left, int total_length, const char * haystack, const char * needle,
int haystack_length, int needle_length, int start) {
    for (; right < total_length &&
                z_algo_compare(haystack, needle,
                    right-left, right, haystack_length, needle_length, start); ++right);
}


