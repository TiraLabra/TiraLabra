
#include "bernstein.hpp"

bernstein_hasher::bernstein_hasher(int base) {
    this->base = base;
    this->compute_powers();
}

void bernstein_hasher::compute_powers() {
    ULL power = base;
    ULL top = ULLONG_MAX / base;
    powers.push_back(1);
    while(1) {
        powers.push_back(power);
        if (power > top) break;
        power *= base;
    }
}

ULL bernstein_hasher::bernstein_hash(const string & str) {
    return bernstein_hash(str.c_str(), str.length());
}

ULL bernstein_hasher::bernstein_hash(const char * str, int length) {
    if (length == -1) {
        length = strlen(str);
    }
    ULL h = 0;
    for (int i = 0; i < length; ++i) {
        h = this->next_bernstein_hash(str, i, h);
    }
    return h;
}

ULL bernstein_hasher::next_bernstein_hash(const char * str, int index, ULL current_hash) {
    return current_hash*this->base + str[index];
}

ULL bernstein_hasher::remove_prefix_from_hash(const char * str, int index, int length, ULL current_hash) {
    if (length > (int)powers.size()) return NO_HASH;
    if (length < 1) return NO_HASH;
    return (current_hash - str[index]*powers[length-1]);
}
