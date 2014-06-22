#ifndef SUFFIX_ARRAY_BUILDER
#define SUFFIX_ARRAY_BUILDER

#include <cstring>
#include <string>
#include "suffix_array.hpp"
#include <cstdlib>
#include <algorithm>
using namespace std;

class suffix_array_builder {
    private:
    class substring {
        public:
        int prefix_index;
        int suffix_index;
        int index;
    };
    substring * temp_substrings=NULL;
    int * current=NULL;
    int * prev=NULL;
    void delete_temp_arrays();
    void swap_temp_arrays();
    static bool comparator(const substring &A, const substring &B);
    void map_substrings(int length);
    public:
    ~suffix_array_builder();
    suffix_array * build_suffix_array(const char * haystack, int length = -1);
    suffix_array * build_suffix_array(const string & haystack);
};

#endif // SUFFIX_ARRAY_BUILDER
