#include "suffix_array_test.hpp"
#include <iostream>
using namespace std;
TEST(SuffixArray, NormalTest) {
    suffix_array_builder * builder = new suffix_array_builder();
    for (int i = 0; i < (int)multi_query_tests_v.size(); ++i) {
        multi_query_test mqt = multi_query_tests_v[i];
        string haystack = mqt.haystack;
        string needle = mqt.needle;
        vector<int> ans = mqt.ans;
        suffix_array * arr = builder->build_suffix_array(haystack);
        vector<int> suf_arr_ans = arr->find_all(needle);
        EXPECT_EQ((int)ans.size(), (int)suf_arr_ans.size());
        for (int i = 0 ; i < (int)suf_arr_ans.size(); ++i) {
            char found = 0;
            for (int j = 0; j < (int)ans.size() && !found; ++j) {
                if (suf_arr_ans[i] == suf_arr_ans[j]) {
                    found = 1;
                }
            }
            EXPECT_EQ(found,1);
        }
    }
}
