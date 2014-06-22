#include "suffix_array.hpp"
#include <iostream>
using namespace std;
suffix_array::suffix_array(int * sorted_suffix_array, const char * haystack, int haystack_length) {
    this->sorted_suffix_array = sorted_suffix_array;
    this->haystack = haystack;
    this->haystack_length = haystack_length;
}

int * suffix_array::get_suffix_array() {
    return this->sorted_suffix_array;
}

vector<int> suffix_array::find_all(const string & needle) {
    return this->find_all(needle.c_str(), needle.length());
}

// negative -> haystack smaller, positive needle smaller
int suffix_array::compare (int haystack_idx, const char * needle, int needle_length) {
    int i = 0;
    for (; i < needle_length; ++i) {
        if (i+haystack_idx >= this->haystack_length) return -1;
        if (this->haystack[i+haystack_idx] != needle[i]) {
            return this->haystack[i+haystack_idx]-needle[i];
        }
    }
    return 0;
}

int suffix_array::find_start_bound_index(const char * needle, int length) {
    int left = 0, right = this->haystack_length, mid;
    while (left < right) {
        mid = (left + right) / 2;
        int index = this->sorted_suffix_array[mid];
        int comp = this->compare(index, needle, length);
        if (comp < 0) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return left;
}

int suffix_array::find_end_bound_index(const char * needle, int length) {
    int left = 0, right = this->haystack_length, mid;
    while (left < right) {
        mid = (left + right) / 2;
        int index = this->sorted_suffix_array[mid];
        int comp = this->compare(index, needle, length);
        if (comp > 0) {
            right = mid;
        } else {
            left = mid+1;
        }
    }
    return right-1;
}

vector<int> suffix_array::find_all(const char * needle, int length) {
    if (length == -1) {
        length = strlen(needle);
    }
    vector<int> positions;
    if (length > this->haystack_length) return positions;
    // find bounds;
    int start_bound = this->find_start_bound_index(needle, length);
    int end_bound = this->find_end_bound_index(needle, length);
    if (end_bound < start_bound || end_bound<0 || start_bound < 0 ||
        end_bound>=this->haystack_length) return positions;
    for (int i = start_bound; i <= end_bound; ++i) {
        positions.push_back(this->sorted_suffix_array[i]);
    }
    return positions;
}
