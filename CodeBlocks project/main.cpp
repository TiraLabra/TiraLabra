#include <iostream>
#include <cstdio>
#include "string_slib.hpp"
using namespace std;

int main()
{
    string s = "varna maika vars";
    string se = "ar";
    vector<int> pos = z_algo_get_positions(s,se);
    for (int i = 0; i < (int)pos.size(); ++i) {
        cout << pos[i] << " ";
    }
    return 0;
}
