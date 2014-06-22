#include "suffix_array_builder.hpp"

void suffix_array_builder::delete_temp_arrays() {
    if (this->prev != NULL) {
        delete this->prev;
    }
    if (this->current != NULL) {
        delete this->current;
    }
    if (this->temp_substrings != NULL) {
        delete[] this->temp_substrings;
    }
}

void suffix_array_builder::swap_temp_arrays() {
    int * tmp = this->prev;
    this->prev = this->current;
    this->current = tmp;
}

suffix_array_builder::~suffix_array_builder() {
    this->delete_temp_arrays();
}

suffix_array * suffix_array_builder::build_suffix_array(const string & haystack) {
    return this->build_suffix_array(haystack.c_str(), haystack.length());
}

bool suffix_array_builder::comparator(const substring & A, const substring & B) {
    if (A.prefix_index != B.prefix_index) return A.prefix_index < B.prefix_index;
    return A.suffix_index < B.suffix_index;
}

void suffix_array_builder::map_substrings(int length) {
    int mapped_index;
    int index_to_store;
    for (int idx = 0; idx < length; ++idx) {
        mapped_index = this->temp_substrings[idx].index;
        if (idx == 0) {
            index_to_store = idx;
        } else {
            if (this->temp_substrings[idx].prefix_index == this->temp_substrings[idx-1].prefix_index &&
                this->temp_substrings[idx].suffix_index == this->temp_substrings[idx-1].suffix_index) {
                // do nothing. index_to_store is the same
            } else {
                mapped_index = this->temp_substrings[idx].index;
                index_to_store = idx;
            }
        }
        this->current[mapped_index] = index_to_store;
    }
}

suffix_array * suffix_array_builder::build_suffix_array(const char * haystack, int length) {
    if (length == -1) {
        length = strlen(haystack);
    }
    this->delete_temp_arrays();
    this->prev = new int[length];
    this->current = new int[length];
    this->temp_substrings = new suffix_array_builder::substring[length];
    for (int i = 0; i < length; ++i) {
        this->prev[i] = haystack[i];
    }
    // power of 2
    for (int power = 1; power < length; power <<= 1) {
        for (int idx=0; idx < length; ++idx) {
            this->temp_substrings[idx].prefix_index = this->prev[idx];
            if (idx + power >= length) {
                this->temp_substrings[idx].suffix_index = -1;
            } else {
                this->temp_substrings[idx].suffix_index = this->prev[idx+power];
            }
            this->temp_substrings[idx].index = idx;
        }
        sort(this->temp_substrings, this->temp_substrings + length, this->comparator);
        this->map_substrings(length);
        this->swap_temp_arrays();
    }
    // copy the suffix array since the prev array will be deleted eventually
    int * suf_array = new int[length];
    for (int i = 0; i < length; ++i) {
        suf_array[i] = this->prev[i];
    }
    return new suffix_array(suf_array, haystack, length);
}
