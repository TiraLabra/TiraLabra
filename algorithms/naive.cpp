#include "naive.hpp"

int naive_search(const string & haystack, const string & needle, int start) {
    return naive_search(haystack.c_str(), needle.c_str(), start, haystack.length(), needle.length());
}

int naive_search(const char * haystack, const char * needle, int start, int haystack_length, int needle_length) {
    if (haystack_length == -1) {
        haystack_length = strlen(haystack);
    }
    if (needle_length == -1) {
        needle_length = strlen(needle);
    }
    if (needle_length == 0) return NOT_FOUND;
    char flag;
    for (int i = start; i < haystack_length; ++i) {
        flag = 1;
        for (int j = 0; j < needle_length && flag == 1; ++j) {
            if (haystack[i+j] != needle[j]) flag = 0;
        }
        if (flag == 1) {
            return i;
        }
    }
    return NOT_FOUND;
}
