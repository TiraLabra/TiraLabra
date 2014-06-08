#pragma once
#include <string>
#include <cstring>
#include <vector>
#include <climits>
#include "../../constants.hpp"
using namespace std;

class bernstein_hasher {
private:
    int base;
    vector<long long> powers;
    void compute_powers(int maximum_power);
public:
    const static int MOD=101;
    bernstein_hasher(int base = 33,int maximum_needle_length=1000);
    long long bernstein_hash(const string & str);
    long long bernstein_hash(const char * str, int length = -1);
    long long next_bernstein_hash(const char * str, int index, long long current_hash);
    long long remove_prefix_from_hash(const char * str, int index, int length, long long current_hash);
};
