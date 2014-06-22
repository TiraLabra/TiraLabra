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
    public:
    suffix_array(int * sorted_suffix_array, const char * haystack, int haystack_length);
    ~suffix_array();
    int * get_suffix_array();
    int find_first(const char * needle, int length = -1);
    int find_first(const string & needle);
    vector<int> find_all(const char * needle, int length = -1);
    vector<int> find_all(const string & needle);
};

#endif // SUFFIX_ARRAY

