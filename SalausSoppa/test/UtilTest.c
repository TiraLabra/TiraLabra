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

    extern size_t IP[64];
    size_t IP_len = sizeof IP / sizeof IP[0];
    uint64_t permuted = permute(initial, IP_len, IP);

    extern size_t IP_rev[64];
    size_t IP_rev_len = sizeof IP_rev / sizeof IP_rev[0];
    uint64_t permuted_reverse = permute(permuted, IP_rev_len, IP_rev);

    CuAssertTrue(tc, permuted == 0xcc00ccfff0aaf0aaUL);
    CuAssertTrue(tc, permuted_reverse == initial);
}
