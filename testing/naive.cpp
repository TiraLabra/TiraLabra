#include "naive.hpp"

TEST(NaiveSearch, NormalTest) {
    for (int i = 0; i < (int)one_query_tests_v.size(); ++i) {
        one_query_test oqt = one_query_tests_v[i];
        string haystack = oqt.haystack;
        string needle = oqt.needle;
        int ans = oqt.ans;
        int ans_ns = naive_search(haystack, needle);
        EXPECT_EQ(ans, ans_ns);
    }
}
