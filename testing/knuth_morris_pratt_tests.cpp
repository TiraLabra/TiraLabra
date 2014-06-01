#include "knuth_morris_pratt_tests.hpp"
#include <iostream>
using namespace std;

TEST(KMP, TEST_TRANSITION_ARRAY) {
    string str = "ATTATACA";
    int * arr = precompute_transition(str.c_str(), str.length());
    int v[] = {0,0,0,1,2,1,0,1};
    for (int i = 0; i < str.length(); ++i) {
        cout << arr[i] << endl;
    }
}
