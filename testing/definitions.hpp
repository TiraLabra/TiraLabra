#ifndef DEFINITIONS
#define DEFINITIONS
#include <string>
#include <vector>
using namespace std;

// declare test structures

struct one_query_test {
    string haystack;
    string needle;
    int ans;
};

struct multi_query_test {
    string haystack;
    string needle;
    vector<int> ans;
};

// set test contairns as extern. this will tell the compiler that they exist, but their
// definition is somewhere else. their 'definition' is in the test_suite.cpp
// this is a walk-around behing the multiple declaration error
extern vector<one_query_test> one_query_tests_v;
extern vector<multi_query_test> multi_query_tests_v;
#endif // DEFINITIONS
