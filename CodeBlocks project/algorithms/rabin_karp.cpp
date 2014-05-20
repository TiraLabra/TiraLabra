#include "rabin_karp.hpp"

int rabin_karp_search(const string & haystack, const string & needle, int start) {
    return rabin_karp_search(haystack.c_str(), needle.c_str(), start, haystack.length(), needle.length());
}

int rabin_karp_search(const char * haystack, const char * needle, int start, int haystack_length, int needle_length) {
    if (haystack_length == -1) {
        haystack_length = strlen(haystack);
    }
    if (needle_length == -1) {
        needle_length = strlen(needle);
    }
    if (needle_length == 0) return NOT_FOUND;
    bernstein_hasher * hasher = new bernstein_hasher(9);
    ULL temp_hash = 0;
    ULL needle_hash = hasher->bernstein_hash(needle, needle_length);
    for (int i = start; i < haystack_length; ++i) {
        if (i-start < needle_length) {
            // remove prefix character from hash
            temp_hash = hasher->remove_prefix_from_hash(haystack,i-start,temp_hash);
        }
        temp_hash += bernstein_hash(haystack[i]);
        if (temp_hash == needle_hash) {
            if ()
        }
    }
    return NOT_FOUND;
}
