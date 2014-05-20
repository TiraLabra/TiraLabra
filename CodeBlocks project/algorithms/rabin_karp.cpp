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
    bernstein_hasher * hasher = new bernstein_hasher(33);
    ULL temp_hash = 0;
    ULL needle_hash = hasher->bernstein_hash((const char *)needle, needle_length);
    for (int i = start; i < haystack_length; ++i) {
        if (i-start >= needle_length) {
            // remove prefix character from hash
            temp_hash = hasher->remove_prefix_from_hash((const char *)haystack,i-needle_length,needle_length,temp_hash);
        }
        temp_hash = hasher->next_bernstein_hash((const char *)haystack,i,temp_hash);
        if (temp_hash == needle_hash) {
            return i+1-needle_length;
        }
    }
    return NOT_FOUND;
}
