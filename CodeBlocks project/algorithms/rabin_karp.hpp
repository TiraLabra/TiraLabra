#pragma once

#include <cstring>
#include <string>
#include <iostream>
#include <cstdlib>
#include "../constants.hpp"
using namespace std;

class rabin_karp_searcher {
    private:
    const static long long ALPHABET_SIZE=256;
    const static long long MOD=6211;
    const static int MAXIMUM_POWER=1000000;
    long long * powers;
    public:
    rabin_karp_searcher();
    ~rabin_karp_searcher();
    void compute_powers(int power);
    int rabin_karp_search(const string & haystack, const string & needle, int start = 0);
    int rabin_karp_search(const char * haystack, const char * needle,
                      int start = 0, int haystack_length = -1, int needle_length = -1);
    bool check_equals(const char * haystack, const char * needle, int index, int needle_length);
};
