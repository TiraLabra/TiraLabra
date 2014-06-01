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
    vector<ULL> powers;
    void compute_powers(int maximum_power);
public:
    const static int MOD=101;
    bernstein_hasher(int base = 33,int maximum_needle_length=1000);
    ULL bernstein_hash(const string & str);
    ULL bernstein_hash(const char * str, int length = -1);
    ULL next_bernstein_hash(const char * str, int index, ULL current_hash);
    ULL remove_prefix_from_hash(const char * str, int index, int length, ULL current_hash);
};
