#pragma once

#include "../constants.hpp"
#include <cstring>
#include <string>

using namespace std;

/*! \file */

/*!
    returns a found index of \a needle in \a haystack after an index \a start. By default \a start
    has value 0.
*/
int naive_search(const string & haystack, const string & needle, int start = 0);

/*!
    returns a found index of \a needle in \a haystack after an index \a start, where \a needle has length
    \a haystack_length and \a needle has length \a needle_length.
*/
int naive_search(const char * haystack, const char * needle, int start = 0, int haystack_length = -1, int needle_length = -1);
