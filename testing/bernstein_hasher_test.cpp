#include "bernstein_hasher_test.hpp"

TEST(BernsteinHasher, TestHashGeneration) {

    bernstein_hasher * hasher = new bernstein_hasher(33, 100);
    string str = "asdbweasd";
    ULL h1 = hasher->bernstein_hash(str);
    ULL h2 = 0;
    for (int i = 0; i < (int)str.length(); ++i) {
        h2 = (h2*33 + str.at(i))%hasher->MOD;
    }
    EXPECT_EQ(h1,h2);

}

TEST(BernsteinHasher, TestHashGenerationBigString) {

    bernstein_hasher * hasher = new bernstein_hasher(33, 100);
    string str = "asdbweaasdasdasdasdasdasdasdasdasdasdasssssssssssssssasdasdasdasdasdasdasdasdasdasdassd";
    ULL h1 = hasher->bernstein_hash(str);
    ULL h2 = 0;
    for (int i = 0; i < (int)str.length(); ++i) {
        h2 = (h2*33 + str.at(i))%hasher->MOD;
    }
    EXPECT_EQ(h1,h2);

}

TEST(BernsteinHasher, TestHashTransitionSimple) {
    bernstein_hasher * hasher = new bernstein_hasher(33, 100);
    string str = "asd";
    string suffix = "sd";
    ULL h1 = hasher->bernstein_hash(str);
    ULL h2 = hasher->bernstein_hash(suffix);
    h2 = hasher->remove_prefix_from_hash(str.c_str(), 0, 3, h2);
    h2 -= str.at(2);
    h2 *= 33;
    h2 %= hasher->MOD;
    h2 += str.at(2);
    cout << h1 << " " << h2 << endl;
    EXPECT_EQ(h1,h2);
}
