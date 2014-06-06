#pragma once

#include <cstring>
#include <string>
#include <iostream>
#include <cstdlib>
#include "../constants.hpp"
using namespace std;


/*!
    \a rabin_karp_searcher creates a searcher with precomputed powers of a given number.
    The powers of the given number depend on the length of the \a needle. This is in order to
    improve computation for smaller or even needles at a later time.
*/
class rabin_karp_searcher {
    private:
    /// alphabet size of the given string. Since char 256 values, therefore this is also the number.
    const static long long ALPHABET_SIZE=256;
    /*! mod value to multiply against the given hash to prevent a number overflow. Also this number must be a
    a primer
    */
    const static long long MOD=6211;
    /// biggest precomputed power
    const static int MAXIMUM_POWER=1000000;
    /// powers array
    long long * powers;
    public:
    /*!
    constructor for the class. it initialized the \a powers array.
    */
    rabin_karp_searcher();
    /*!
    destructor for the class. It destroys the \a powers array.
    */
    ~rabin_karp_searcher();
    /*!
    precomputes the powers of the array to a given power given \a power.
    */
    void compute_powers(int power);
    int rabin_karp_search(const string & haystack, const string & needle, int start = 0);
    int rabin_karp_search(const char * haystack, const char * needle,
                      int start = 0, int haystack_length = -1, int needle_length = -1);
    bool check_equals(const char * haystack, const char * needle, int index, int needle_length);
};
