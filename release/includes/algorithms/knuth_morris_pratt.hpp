#ifndef KNUTH_MORRIS_PRATT
#define KNUTH_MORRIS_PRATT

#include <cstdlib>
#include <cstring>
#include <vector>
#include <string>
using namespace std;

/*! \file */

/*!
    builds a transition array for the kmp algorithm. No checks are done for the length of the needle. The
    functions is module-private.
*/
int * build_transition_array(const char * needle, int needle_length);

/*!
    returns a vector which contains all positions of \a needle in \a haystack. You can add an \a upper_limit
    of how many occurrances you are interested in. If you are interested in the first one only, then you can
    call the function buy \a kmp_search(haystack, needle, 1). \a upper_limit is optional. If not set, the
    function will return all occurrances.
*/
vector<int> kmp_search(const string & haystack, const string & needle, int upper_limit=-1);

/*!
    returns a vector which contains all positions of \a needle in \a haystack. You can add an \a upper_limit
    of how many occurrances you are interested in. If you are interested in the first one only, then you can
    call the function buy \a kmp_search(haystack, needle, 1). \a upper_limit is optional. If not set, the
    function will return all occurrances.
    If \a haystack_length or \a needle_length are not set, then they will be computed by using \a strlen.
*/
vector<int> kmp_search(const char * haystack, const char * needle, int haystack_length=-1,
    int needle_length = -1, int upper_limit = -1);

#endif // KNUTH_MORRIS_PRATT
