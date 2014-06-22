#include "suffix_array_test.hpp"
#include <iostream>
using namespace std;
TEST(SuffixArray, NormalTest) {
    suffix_array_builder * builder = new suffix_array_builder();
    suffix_array * suf_arr = builder->build_suffix_array("aaaaaaa");
    int * arr = suf_arr->get_suffix_array();
    for (int i = 0; i < 7; ++i) {
        cout << arr[i] << " ";
    }
    cout << endl;
}
