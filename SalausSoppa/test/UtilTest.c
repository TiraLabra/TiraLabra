#include <stdint.h>

#include "util.h"
#include "CuTest.h"

size_t only_first_bit[] = {1, 1, 1, 1, 1, 1, 1, 1,
                           1, 1, 1, 1, 1, 1, 1, 1,
                           1, 1, 1, 1, 1, 1, 1, 1,
                           1, 1, 1, 1, 1, 1, 1, 1,
                           1, 1, 1, 1, 1, 1, 1, 1,
                           1, 1, 1, 1, 1, 1, 1, 1,
                           1, 1, 1, 1, 1, 1, 1, 1,
                           1, 1, 1, 1, 1, 1, 1, 1};

#define only_first_bit_len (sizeof only_first_bit / sizeof only_first_bit[0])

void TestAllZeroPermute(CuTest* tc)
{
    uint64_t initial = 0UL;
    uint64_t permuted = permute(initial, only_first_bit_len, only_first_bit);

    CuAssertTrue(tc, permuted == 0UL);
}

void TestAllOneFromFirstBitPermute(CuTest* tc)
{
    uint64_t initial = 1UL << 63;
    uint64_t permuted = permute(initial, only_first_bit_len, only_first_bit);

    CuAssertTrue(tc, permuted == 0xffffffffffffffffUL);
}

void TestExampleInitialPermutationAndReverse(CuTest* tc)
{
    // from http://orlingrabbe.com/des.htm
    uint64_t initial = 0x123456789abcdefUL;

    size_t IP[] = {58, 50, 42, 34, 26, 18, 10, 2,
               60, 52, 44, 36, 28, 20, 12, 4,
               62, 54, 46, 38, 30, 22, 14, 6,
               64, 56, 48, 40, 32, 24, 16, 8,
               57, 49, 41, 33, 25, 17,  9, 1,
               59, 51, 43, 35, 27, 19, 11, 3,
               61, 53, 45, 37, 29, 21, 13, 5,
               63, 55, 47, 39, 31, 23, 15, 7};
    size_t IP_len = sizeof IP / sizeof IP[0];
    uint64_t permuted = permute(initial, IP_len, IP);

    size_t IP_rev[] = {40, 8, 48, 16, 56, 24, 64, 32,
                       39, 7, 47, 15, 55, 23, 63, 31,
                       38, 6, 46, 14, 54, 22, 62, 30,
                       37, 5, 45, 13, 53, 21, 61, 29,
                       36, 4, 44, 12, 52, 20, 60, 28,
                       35, 3, 43, 11, 51, 19, 59, 27,
                       34, 2, 42, 10, 50, 18, 58, 26,
                       33, 1, 41,  9, 49, 17, 57, 25};
    size_t IP_rev_len = sizeof IP_rev / sizeof IP_rev[0];
    uint64_t permuted_reverse = permute(permuted, IP_rev_len, IP_rev);

    CuAssertTrue(tc, permuted == 0xcc00ccfff0aaf0aaUL);
    CuAssertTrue(tc, permuted_reverse == initial);
}
