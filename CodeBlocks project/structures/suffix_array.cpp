#include "suffix_array.hpp"

suffix_array::suffix_array(int * sorted_suffix_array, const char * haystack, int haystack_length) {
    this->sorted_suffix_array = sorted_suffix_array;
    this->haystack = haystack;
    this->haystack_length = haystack_length;
}

int * suffix_array::get_suffix_array() {
    return this->sorted_suffix_array;
}
