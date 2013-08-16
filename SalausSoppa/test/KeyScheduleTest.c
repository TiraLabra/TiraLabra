#include <stdint.h>

#include "CuTest.h"
#include "key_schedule.h"

void TestKeyScheduleExamples(CuTest* tc)
{
    // from http://orlingrabbe.com/des.htm
    uint64_t subkeys[SUBKEY_NUM];
    generate_subkeys(0x133457799bbcdff1UL, subkeys);

    CuAssertTrue(tc, subkeys[0] == 0x1b02effc70720000UL);
    CuAssertTrue(tc, subkeys[1] == 0x79aed9dbc9e50000UL);
    CuAssertTrue(tc, subkeys[2] == 0x55fc8a42cf990000UL);
    CuAssertTrue(tc, subkeys[3] == 0x72add6db351d0000UL);
    CuAssertTrue(tc, subkeys[4] == 0x7cec07eb53a80000UL);
    CuAssertTrue(tc, subkeys[5] == 0x63a53e507b2f0000UL);
    CuAssertTrue(tc, subkeys[6] == 0xec84b7f618bc0000UL);
    CuAssertTrue(tc, subkeys[7] == 0xf78a3ac13bfb0000UL);
    CuAssertTrue(tc, subkeys[8] == 0xe0dbebede7810000UL);
    CuAssertTrue(tc, subkeys[9] == 0xb1f347ba464f0000UL);
    CuAssertTrue(tc, subkeys[10] == 0x215fd3ded3860000UL);
    CuAssertTrue(tc, subkeys[11] == 0x7571f59467e90000UL);
    CuAssertTrue(tc, subkeys[12] == 0x97c5d1faba410000UL);
    CuAssertTrue(tc, subkeys[13] == 0x5f43b7f2e73a0000UL);
    CuAssertTrue(tc, subkeys[14] == 0xbf918d3d3f0a0000UL);
    CuAssertTrue(tc, subkeys[15] == 0xcb3d8b0e17f50000UL);
}