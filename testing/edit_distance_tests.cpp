#include "edit_distance_tests.hpp"
#include <iostream>
using namespace std;

TEST(EditDistance, BasicTest) {
    string a = "money";
    string b = "food";
    int ans = 4;
    edit_distance * calculator = new edit_distance(1,1,1);
    int val = calculator->get_levenstein_distance(a,b);
    EXPECT_EQ(val, ans);
}
