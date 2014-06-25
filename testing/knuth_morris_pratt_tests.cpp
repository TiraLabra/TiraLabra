#include "knuth_morris_pratt_tests.hpp"
#include <iostream>
using namespace std;

TEST(KMP, SimpleCustom) {
    string haystack = "baramara";
    string needle = "a";
    vector<int> kmp_ans = kmp_search(haystack, needle, 1);
    if (kmp_ans.size() != 1) {
        EXPECT_EQ(kmp_ans.size(), 1);
    } else {
        EXPECT_EQ(haystack.find(needle), kmp_ans[0]);
    }
}
TEST(KMP, NormalTest) {
    for (int i = 0; i < (int)multi_query_tests_v.size(); ++i) {
        multi_query_test mqt = multi_query_tests_v[i];
        string haystack = mqt.haystack;
        string needle = mqt.needle;
        vector<int> ans = mqt.ans;
        vector<int> kmp_ans = kmp_search(haystack, needle);
        EXPECT_EQ((int)ans.size(), (int)kmp_ans.size());
        for (int i = 0; i < (int)ans.size();++i) {
            EXPECT_EQ(ans[i], kmp_ans[i]);
        }
    }
}

