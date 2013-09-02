#include "CuTest.h"
#include "feistel.h"

extern uint8_t sbox_1[SBOX_ROWS][SBOX_COLS], 
    sbox_2[SBOX_ROWS][SBOX_COLS],
    sbox_3[SBOX_ROWS][SBOX_COLS],
    sbox_4[SBOX_ROWS][SBOX_COLS],
    sbox_5[SBOX_ROWS][SBOX_COLS],
    sbox_6[SBOX_ROWS][SBOX_COLS],
    sbox_7[SBOX_ROWS][SBOX_COLS],
    sbox_8[SBOX_ROWS][SBOX_COLS];

void TestSbox(CuTest* tc)
{
    CuAssertTrue(tc, sbox(0x18U, sbox_1) == 0x5);
    CuAssertTrue(tc, sbox(0x11U, sbox_2) == 0xc);
    CuAssertTrue(tc, sbox(0x1eU, sbox_3) == 0x8);
    CuAssertTrue(tc, sbox(0x3aU, sbox_4) == 0x2);
    CuAssertTrue(tc, sbox(0x21U, sbox_5) == 0xb);
    CuAssertTrue(tc, sbox(0x26U, sbox_6) == 0x5);
    CuAssertTrue(tc, sbox(0x14U, sbox_7) == 0x9);
    CuAssertTrue(tc, sbox(0x27U, sbox_8) == 0x7);
}

void TestFeistel(CuTest* tc)
{
    uint64_t halfblock = 0xf0aaf0aa00000000ul;
    uint64_t subkey = 0x1b02effc70720000ul;

    CuAssertTrue(tc, feistel(halfblock, subkey) == 0x234aa9bb00000000ul);
}
