#include "test_suite.hpp"

#define ONE_Q_TEST_FILE_NAME test_files/tests1q
#define MULTI_Q_TEST_FILE_NAME test_files/testsmq

vector<one_query_test> one_query_tests_v;
vector<multi_query_test> multi_query_tests_v;

one_query_test make_oqt(const string & haystack, const string & needle, int ans) {
    one_query_test oqt;
    oqt.haystack = haystack;
    oqt.needle = needle;
    oqt.ans = ans;
    return oqt;
}

multi_query_test make_mqt(const string & haystack, const string & needle, vector<int> ans) {
    multi_query_test mqt;
    mqt.haystack = haystack;
    mqt.needle = needle;
    mqt.ans = ans;
    return mqt;
}

vector<one_query_test> load_one_q_tests() {
    vector<one_query_test> tests;
    ifstream in("tests/test_files/tests1q.in");
    ifstream ans("tests/test_files/tests1q.out");
    int n;
    in >> n;
    for (int i = 0; i < n; ++i) {
        string haystack, needle;
        int res;
        in >> haystack >> needle;
        ans >> res;
        tests.push_back(make_oqt(haystack, needle, res));
    }
    in.close();
    ans.close();
    return tests;
}

vector<multi_query_test> load_multi_q_tests() {
    vector<multi_query_test> tests;
    ifstream in("tests/test_files/testsmq.in");
    ifstream ans("tests/test_files/testsmq.out");
    int n;
    in >> n;
    for (int i = 0; i < n; ++i) {
        string haystack, needle;
        vector<int> res;
        int cnt;
        in >> haystack >> needle;
        ans >> cnt;
        for (int p = 0; p < cnt; ++p) {
            int a;
            ans >> a;
            res.push_back(a);
        }
        tests.push_back(make_mqt(haystack, needle, res));
    }
    in.close();
    ans.close();
    return tests;
}

int main(int argc, char **argv) {
    one_query_tests_v = load_one_q_tests();
    multi_query_tests_v = load_multi_q_tests();
    testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
