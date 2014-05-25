#include "z_algorithm_tests.hpp"
#include <iostream>
using namespace std;

TEST(Z_ALGORITHM_TEST,NORMAL_TEST) {
    for (int i = 0; i < (int)multi_query_tests_v.size(); ++i) {
        multi_query_test mqt = multi_query_tests_v[i];
        string haystack = mqt.haystack;
        string needle = mqt.needle;
        vector<int> ans = mqt.ans;
        vector<int> z_ans = z_algo_get_positions(haystack, needle);
        EXPECT_EQ((int)ans.size(), (int)z_ans.size());
    }

}
