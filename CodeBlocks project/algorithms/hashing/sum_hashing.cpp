#include "sum_hashing.hpp"

unsigned long long sum_hash(const string & str, unsigned long long base) {
    return sum_hash(str.c_str(), base, str.length());
}

unsigned long long sum_hash(const char * str, unsigned long long base, int length) {
    if (length == -1) {
        length = strlen(str);
    }
    unsigned long long h = 0;
    for (int i = 0; i < length; ++i) {
        h = (h+str[i])%base;
    }
    return h;
}
