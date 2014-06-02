#include "rabin_karp_test.hpp"
#include <iostream>
using namespace std;

TEST(RabinKarpTest, SimpleTest) {
    rabin_karp_searcher * searcher = new rabin_karp_searcher();
    string haystack = "buba,maraaaaaaakakakakka";
    string needle = "raaaaaaak";
    int rk_ans = searcher->rabin_karp_search(haystack, needle);
    size_t found = haystack.find(needle);
    EXPECT_EQ((int)found, rk_ans);
}


TEST(RabinKarpTest,NormalTest) {
    rabin_karp_searcher * searcher = new rabin_karp_searcher();
    for (int i = 0; i < (int)one_query_tests_v.size(); ++i) {
        one_query_test oqt = one_query_tests_v[i];
        string haystack = oqt.haystack;
        string needle = oqt.needle;
        int ans = haystack.find(needle);
        int rk_ans = searcher->rabin_karp_search(haystack, needle);
        EXPECT_EQ(ans, rk_ans);
    }
}

