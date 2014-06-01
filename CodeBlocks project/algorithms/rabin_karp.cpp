#include "rabin_karp.hpp"

rabin_karp_searcher::rabin_karp_searcher() {
    powers = (long long *)calloc(this->MAXIMUM_POWER, sizeof(long long));
}

rabin_karp_searcher::~rabin_karp_searcher() {
    free(powers);
}

int rabin_karp_searcher::rabin_karp_search(const string & haystack, const string & needle, int start) {
    return rabin_karp_search(haystack.c_str(), needle.c_str(), start, haystack.length(), needle.length());
}

void rabin_karp_searcher::compute_powers(int len) {
    this->powers[0]=1;
    for (int i = 1; i<=len; ++i) {
        this->powers[i] = (this->powers[i-1]*this->ALPHABET_SIZE)%this->MOD;
    }
}

int rabin_karp_searcher::rabin_karp_search(const char * haystack, const char * needle, int start, int haystack_length, int needle_length) {
    if (haystack_length == -1) {
        haystack_length = strlen(haystack);
    }
    if (needle_length == -1) {
        needle_length = strlen(needle);
    }
    if (needle_length == 0) return NOT_FOUND;
    if (needle_length+start > haystack_length) return NOT_FOUND;
    // precompute hashes
    if (this->powers[needle_length-1]==0) {
        this->compute_powers(needle_length-1);
    }
    long long temp_hash = 0;
    long long needle_hash = 0;
    for (int i = 0; i < needle_length; ++i) {
        temp_hash = (temp_hash*this->ALPHABET_SIZE + haystack[i+start])%this->MOD;
        needle_hash = (needle_hash*this->ALPHABET_SIZE + needle[i])%this->MOD;
    }
    for (int i = start+needle_length; i < haystack_length+1; ++i) {
        if (temp_hash == needle_hash
            && this->check_equals(haystack, needle, i-needle_length, needle_length)) {
            return i-needle_length;
        }
        if (i==haystack_length) break;
        temp_hash = temp_hash - haystack[i-needle_length]*powers[needle_length-1];
        temp_hash = (this->ALPHABET_SIZE*temp_hash + haystack[i])%this->MOD;
        if (temp_hash < 0) {
            temp_hash += this->MOD;
        }
    }
    return NOT_FOUND;
}

bool rabin_karp_searcher::check_equals(const char * haystack, const char * needle, int index, int needle_length) {
    for (int i = 0; i < needle_length; ++i) {
        if (haystack[i+index] != needle[i]) return false;
    }
    return true;
}
