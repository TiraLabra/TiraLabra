#include "suffix_trie_tests.hpp"
#include <iostream>
using namespace std;
TEST(SuffixTrie,BasicTest) {
    suffix_trie_builder * builder = new suffix_trie_builder();
        string haystack = "banana";
        string needle = "na";
        suffix_trie * trie = builder->build_trie(haystack);
        int ans = haystack.find(needle);
        int rk_ans = trie->find_first(needle);
        EXPECT_EQ(ans, rk_ans);
        delete trie;
}

TEST(SuffixTrie,NFoundTest) {
    suffix_trie_builder * builder = new suffix_trie_builder();
    string haystack = "missisippi";
    string needle = "basa";
    suffix_trie * trie = builder->build_trie(haystack);
    int ans = -1;
    int rk_ans = trie->find_first(needle);
    EXPECT_EQ(ans, rk_ans);
    delete trie;
}

TEST(SuffixTrie, BiggerNeedleTest) {
    suffix_trie_builder * builder = new suffix_trie_builder();
    string haystack = "basa";
    string needle = "basapa";
    suffix_trie * trie = builder->build_trie(haystack);
    int ans = -1;
    int rk_ans = trie->find_first(needle);
    EXPECT_EQ(ans, rk_ans);
    delete trie;
}

TEST(SuffixTrie, NormalTest) {
    suffix_trie_builder * builder = new suffix_trie_builder();
    for (int i = 0; i < (int)one_query_tests_v.size(); ++i) {
        one_query_test oqt = one_query_tests_v[i];
        string haystack = oqt.haystack;
        if (haystack.length() > 800) {
            // skip big tests since to build the trie it takes O(n^2) steps
            continue;
        }
        suffix_trie * trie = builder->build_trie(haystack);
        string needle = oqt.needle;
        int ans = haystack.find(needle);
        int rk_ans = trie->find_first(needle);
        EXPECT_EQ(ans, rk_ans);
        delete trie;
    }
}

TEST(SuffixTrie, MultiTest) {
    suffix_trie_builder * builder = new suffix_trie_builder();
    for (int i = 0; i < (int)multi_query_tests_v.size(); ++i) {
        multi_query_test mqt = multi_query_tests_v[i];
        string haystack = mqt.haystack;
        string needle = mqt.needle;
        suffix_trie * trie = builder->build_trie(haystack);
        vector<int> ans = mqt.ans;
        vector<int> t_ans = trie->find_all(needle);
        EXPECT_EQ((int)ans.size(), (int)t_ans.size());
        for (int i = 0; i < (int)ans.size();++i) {
            EXPECT_EQ(ans[i], t_ans[ans.size()-1-i]);
        }
        delete trie;
    }
}

