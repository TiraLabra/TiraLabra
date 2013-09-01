#include <stdint.h>
#include <stdlib.h>

#include "feistel.h"
#include "util.h"
#include "key_schedule.h"

extern size_t IP[64];
extern size_t IP_rev[64];

/**
 * @brief Encrypts 64-bit block input with key
 */
uint64_t des_encrypt(uint64_t input, uint64_t keytable[SUBKEY_NUM])
{
    input = permute(input, 64, IP);

    uint64_t left = input & 0xffffffff00000000ul;
    uint64_t right = (input & 0x00000000fffffffful) << 32;

    for(int i = 0; i < SUBKEY_NUM; i++) {
        uint64_t old_right = right;
        right = left ^ feistel(right, keytable[i]);
        left = old_right;
    }

    // left and right are now reversed
    input = right ^ (left >> 32);
    return permute(input, 64, IP_rev);
}

/**
 * @brief Decrypts 64-bit block input with key
 */
uint64_t des_decrypt(uint64_t input, uint64_t keytable[SUBKEY_NUM])
{
    input = permute(input, 64, IP);

    uint64_t left = input & 0xffffffff00000000ul;
    uint64_t right = (input & 0x00000000fffffffful) << 32;

    for(int i = SUBKEY_NUM - 1; i >= 0; i--) {
        uint64_t old_right = right;
        right = left ^ feistel(right, keytable[i]);
        left = old_right;
    }

    // left and right are now reversed
    input = right ^ (left >> 32);
    return permute(input, 64, IP_rev);
}
