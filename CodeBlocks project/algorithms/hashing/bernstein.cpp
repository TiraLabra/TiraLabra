
#include "bernstein.hpp"
#include <iostream>
using namespace std;
bernstein_hasher::bernstein_hasher(int base, int maximum_needle_length) {
    this->base = base;
    this->compute_powers(maximum_needle_length);
}

void bernstein_hasher::compute_powers(int maximum_power) {
    ULL power = base;
    powers.push_back(1);
    while(maximum_power != 0) {
        powers.push_back(power);
        power = (power*base)%this->MOD;
        --maximum_power;
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
    ULL h = (current_hash*this->base + str[index])%this->MOD;
    if (h < 0) {
        h+=this->MOD;
    }
    return h;
}

ULL bernstein_hasher::remove_prefix_from_hash(const char * str, int index, int length, ULL current_hash) {
    if (length > (int)powers.size()) return NO_HASH;
    if (length < 1) return NO_HASH;
    ULL h_remove_first = current_hash - str[index]*powers[length-1];
    return h_remove_first;
}
