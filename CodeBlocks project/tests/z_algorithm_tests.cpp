#include "z_algorithm_tests.hpp"


TEST(Z_ALGORITHM_TEST,NORMAL_TEST) {
    for (int i = 0; i < (int)multi_query_tests_v.size(); ++i) {
        multi_query_test mqt = multi_query_tests_v[i];
        string haystack = mqt.haystack;
        string needle = mqt.needle;
        vector<int> ans = mqt.ans;
        vector<int> z_ans = z_algo_get_positions(haystack, needle);
        EXPECT_EQ((int)ans.size(), (int)z_ans.size());
        for (int i = 0; i < (int)ans.size();++i) {
            EXPECT_EQ(ans[i], z_ans[i]);
        }
    }
}

TEST(Z_ALGORITHM_TEST,SPECIFIC_TEST_1) {
    string haystack = "babaaaababababa";
    string needle = "ba";
    int ans_arr[] = {0,2,7,9,11,13};
    vector<int> ans(ans_arr, ans_arr+6);
    vector<int> z_ans = z_algo_get_positions(haystack, needle);
    EXPECT_EQ((int)ans.size(), (int)z_ans.size());
    for (int i = 0; i < (int)ans.size();++i) {
        EXPECT_EQ(ans[i], z_ans[i]);
    }
}

TEST(Z_ALGORITHM_TEST, TEST_DYNAMIC_CHAR_ARRAY) {
    string haystack = "varnamaikavarnab";
    string needle = "varna";
    const char * haystack_dyn_arr = haystack.c_str();
    const char * needle_dyn_arr = needle.c_str();
    int ans_arr[] = {0,10};
    vector<int> ans(ans_arr, ans_arr+2);
    vector<int> z_ans = z_algo_get_positions(haystack_dyn_arr, needle_dyn_arr);
    EXPECT_EQ((int)ans.size(), (int)z_ans.size());
    for (int i = 0; i < (int)ans.size();++i) {
        EXPECT_EQ(ans[i], z_ans[i]);
    }
}
