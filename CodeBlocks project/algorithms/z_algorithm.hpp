#pragma once

#include <cstring>
#include <string>
#include <vector>
#include <cstdlib>
using namespace std;

/*! \file */

/*!
    Compares the characters in z array at index \a index_a and \a index_b.
    The z array is not generated, but rather using offset to choose which character in
    \a haystack and \a needle to use. The \a haystack_length and \a the needle_length
    represent the length of the \a haystack and the \a needle.
*/
static bool z_algo_compare(const char * haystack, const char * needle, int index_a,
int index_b, int haystack_length, int needle_length, int start);

/*!
    The function returns the found positions of the string \a needle in \a haystack, starting with an offset
    \a start (by default 0) and counting at most \a upper_bound_cnt (default -1) positions. If \a
    upper_bound_cnt is smaller than 0, then it will find all positions of the \a needle.
*/

vector<int> z_algo_get_positions(const string & haystack, const string & needle, int start=0,
int upper_bound_cnt=-1);

/*!
    The function returns the found positions of the string \a needle in \a haystack, starting with an offset
    \a start (by default 0) and counting at most \a upper_bound_cnt (default -1) positions. If \a
    upper_bound_cnt is smaller than 0, then it will find all positions of the \a needle.
*/
vector<int> z_algo_get_positions(const char * haystack, const char * needle,
 int start=0, int haystack_length=-1, int needle_length=-1, int upper_bound_cnt=-1);

/*!
    Since the main point of the algorithm is to keep an invariant-bound [left;right], this method extends the
    bound to the right. Accordingly \a total_length is the length of the z array, \a haystack is the text we
    are searching in, \a needle is the string we are searching for, \a haystack_length is the length of the
    \a haystack, \a needle_length is the length of the \a needle, \a start is the the offset index we start the search
    from. The \a start is needed since the z array is dependent on it.
*/
static void extend_right(int &right, int left, int total_length, const char * haystack, const char * needle,
int haystack_length, int needle_length, int start);
