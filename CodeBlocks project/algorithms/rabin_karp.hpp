#pragma once

#include <cstring>
#include <string>
#include <iostream>
#include <cstdlib>
#include "../constants.hpp"
using namespace std;

/*! \file */

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
    /*!
    return found index of \a needle in \a haystack where the search starts at index \a start. By default,
    \a start is 0.
    */
    int rabin_karp_search(const string & haystack, const string & needle, int start = 0);
    /*!
    return found index of \a needle in \a haystack where the search starts at index \a start. By default,
    \a start is 0. \a haystack_length is the length of the \a haystack and \a needle_length is the length of
    \a needle. If \a haystack_length and \a needle_length are equal, then they are computed.
    */
    int rabin_karp_search(const char * haystack, const char * needle,
                      int start = 0, int haystack_length = -1, int needle_length = -1);

    /*!
    checks whether the substring starting at \a index of \a haystack, of length \a needle_length is equal
    to \a needle.
    */
    bool check_equals(const char * haystack, const char * needle, int index, int needle_length);
};
