#ifndef SUFFIX_ARRAY_BUILDER
#define SUFFIX_ARRAY_BUILDER

#include <cstring>
#include <string>
#include "suffix_array.hpp"
#include <cstdlib>
#include <algorithm>
using namespace std;

/*! \file */

/*!
    the \a suffix_array_builder has the purpose to build a \a suffix_array from a given string (haystack)
*/
class suffix_array_builder {
    private:
    /*!
        generated substring
    */
    class substring {
        public:
        /*!
            comparison index of the prefix
        */
        int prefix_index;
        /*!
            comparison index of the suffix
        */
        int suffix_index;
        /*!
            index in the haystack (text)
        */
        int index;
    };
    /*!
        array of generated substrings
    */
    substring * temp_substrings=NULL;
    /*!
        current mapping of the subsrings
    */
    int * current=NULL;
    /*!
        previous mapping of the substrings
    */
    int * prev=NULL;
    /*!
        deletes \a temp_substrings, \a current and \a prev
    */
    void delete_temp_arrays();
    /*!
        swaps the momery addresses of \a prev and \a current
    */
    void swap_temp_arrays();
    /*!
        comparator for substrings. It is used for sorting.
    */
    static bool comparator(const substring &A, const substring &B);
    /*!
        maps the substrings to their appropriate index
    */
    void map_substrings(int length);
    public:
    /*!
        destructor for the \a suffix_array_builder
    */
    ~suffix_array_builder();
    /*!
        builds a \a suffix_array from a given \a haystack and \a length. If length is omitted, then
        it is computed by using strlen.
    */
    suffix_array * build_suffix_array(const char * haystack, int length = -1);
    /*!
        builds a \a suffix_array from a given \a haystack.
    */
    suffix_array * build_suffix_array(const string & haystack);
};

#endif // SUFFIX_ARRAY_BUILDER
