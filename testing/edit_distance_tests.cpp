#include "edit_distance_tests.hpp"
#include <iostream>
using namespace std;

TEST(EditDistance, BasicTest) {

    edit_distance * calculator = new edit_distance(1,1,1);
    string a = "money";
    string b = "food";
    int ans = 4;
    int val = calculator->get_levenstein_distance(a,b);

    EXPECT_EQ(val, ans);
}

TEST(EditDistance, RandomOnlineTest) {
    edit_distance * calculator = new edit_distance(1,1,2);
    string a = "LOCKSMITH";
    string b = "BLACKSMITH";
    int ans = 3;
    int val = calculator->get_levenstein_distance(a,b);
    EXPECT_EQ(val, ans);
}

TEST(EditDistance, BigCostsSmallCosts) {
    edit_distance * calculator = new edit_distance(1,100000,2);
    // adding 1 is cheap
    string a = "YABADABADUU";
    string b = "YABADABADU";
    int ans = 1;
    int val = calculator->get_levenstein_distance(a,b);
    EXPECT_EQ(val, ans);

    // adding 11 from an empty string is cheaper
    a = "YABADABADU";
    b = "YABADABADUU";
    ans = 11;
    val = calculator->get_levenstein_distance(a,b);
    EXPECT_EQ(val, ans);
}
