#include <stdint.h>
#include "feistel.h"
#include "util.h"

/**
 * @brief Feistel function, takes 32-bit block and 48-bit subkey
 */
uint64_t feistel(uint64_t input, uint64_t subkey)
{
    uint64_t expanded = permute(input, sizeof E / sizeof E[0], E);
    input = expanded ^ subkey;
    uint64_t output = 0;

    // 8 S-boxes
    for(int i = 0; i < 8; i++) {
        uint64_t mask = 0xfc00000000000000ul >> (6 * i);
        uint64_t sbox_input = (input & mask) >> (58 - 6 * i);
        output ^= sbox(sbox_input, sboxes[i]) << (60 - 4 * i);
    }
    return permute(output, sizeof P / sizeof P[0], P);
}

/**
 * @brief S-box, takes 6 bits of input and returns 4
 */
uint64_t sbox(uint64_t input, uint8_t box[SBOX_ROWS][SBOX_COLS])
{
    uint64_t row = (input & 0x1) ^ ((input & 0x20) >> 4);
    uint64_t col = (input & 0x1e) >> 1;
    return box[row][col];
}
