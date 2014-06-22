#ifndef SUFFIX_ARRAY
#define SUFFIX_ARRAY

#include <cstring>
#include <string>
#include <cstdlib>
#include <vector>
using namespace std;

/*! \file */

/*!
    \a suffix_array is a helpful structure for operations on strings like searching, etc
*/
class suffix_array {
    private:
    /*!
        actual suffix array
    */
    int * sorted_suffix_array;
    /*!
        copy of the haystack
    */
    const char * haystack;
    /*!
        length of the haystack
    */
    int haystack_length;
    /*!
        implicit comparator between the haystack substring and the needle. \a haystack_idx is the
        starting position of the substring in the haystack, \a needle is the needle and \a needle_length is the
        length of the needle.
    */
    int compare (int haystack_idx, const char * needle, int needle_length);
    /*!
        returns the index of the first found index of the needle in the suffix array. Binary search is done.
    */
    int find_start_bound_index(const char * needle, int length);
    /*!
        returns the index of the last found index of the needle in the suffix array. Binary search is done.
    */
    int find_end_bound_index(const char * needle, int length);
    public:
    /*!
        stores the \a sorted_suffix_array, the \a haystack and the haystack length
    */
    suffix_array(int * sorted_suffix_array, const char * haystack, int haystack_length);
    ~suffix_array();
    /*!
        returns the suffix array
    */
    int * get_suffix_array();
    /*!
        finds all occurrences of the \a needle in the \a haystack. If length is omitted, then it is computed
        using strlen. If no occurrences are found, then an empty vector will be returned.
    */
    vector<int> find_all(const char * needle, int length = -1);
    /*!
        finds all occurrences of the \a needle in the \a haystack.
        If no occurrences are found, then an empty vector will be returned.
    */
    vector<int> find_all(const string & needle);
};

#endif // SUFFIX_ARRAY

