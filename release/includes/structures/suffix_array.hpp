#ifndef SUFFIX_ARRAY
#define SUFFIX_ARRAY

#include <cstring>
#include <string>
#include <cstdlib>
#include <vector>
using namespace std;

class suffix_array {
    private:
    int * sorted_suffix_array;
    const char * haystack;
    int haystack_length;
    int compare (int haystack_idx, const char * needle, int needle_length);
    int find_start_bound_index(const char * needle, int length);
    int find_end_bound_index(const char * needle, int length);
    public:
    suffix_array(int * sorted_suffix_array, const char * haystack, int haystack_length);
    ~suffix_array();
    int * get_suffix_array();
    vector<int> find_all(const char * needle, int length = -1);
    vector<int> find_all(const string & needle);
};

#endif // SUFFIX_ARRAY

