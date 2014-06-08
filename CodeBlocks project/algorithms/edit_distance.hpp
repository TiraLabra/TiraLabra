#ifndef EDIT_DISTANCE_HEADER
#define EDIT_DISTANCE_HEADER

#include <cstring>
#include <string>
#include <cstdlib>
using namespace std;

/*! \file */

/*!
    The \a edit_dinstance class keeps costs for adding, removing and replacing a character for the
    edit distance algorithm. It also stores a dynamic array which is used to keep best values for a
    a smaller solution space.
*/
class edit_distance {
    private:
    /// cost for adding a character
    int add_cost;

    /// cost for removing a character
    int remove_cost;

    /// cost for replacing a character
    int replace_cost;

    /// dynamic array to keep smaller solution space
    int ** dynamic_programming_array;
    public:

    /*!
        it intiliazes the \a edit_distance class with a given \a add_cost, \a remove_cost and
        \a replace cost
    */
    edit_distance(int add_cost, int remove_cost, int replace_cost);

    /*!
        destroys the object by destroying the dp array
    */
    ~edit_distance();

    /*!
        returns the levenstein distance between string \a str_a and \a str_b
    */
    int get_levenstein_distance(const string & str_a, const string & str_b);

    /*!
        returns the levenstein distance between string \a str_a having length
        \a str_a_len and \a str_b having length \a str_b_len.
        If \a str_a_len and \a str_b_len are not set, then they are computed.
    */
    int get_levenstein_distance(const char * str_a, const char * str_b, int str_a_len=-1, int str_b_len=-1);
};

#endif // EDIT_DISTANCE_HEADER
