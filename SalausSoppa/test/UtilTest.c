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

void TestExampleInitialPermutation(CuTest* tc)
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

    CuAssertTrue(tc, permuted == 0xcc00ccfff0aaf0aaUL);
}