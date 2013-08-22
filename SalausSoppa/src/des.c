#include <stdint.h>
#include <stdlib.h>

#include "feistel.h"
#include "util.h"
#include "key_schedule.h"

uint64_t des_encrypt(uint64_t input, uint64_t key)
{
    extern size_t IP[64];
    input = permute(input, 64, IP);

    uint64_t left = input & 0xffffffff00000000ul;
    uint64_t right = (input & 0x00000000fffffffful) << 32;

    uint64_t subkeys[SUBKEY_NUM] = 
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    generate_subkeys(key, subkeys);

    for(int i = 0; i < SUBKEY_NUM; i++) {
        uint64_t old_right = right;
        right = left ^ feistel(right, subkeys[i]);
        left = old_right;
    }

    // left and right are now reversed
    input = right ^ (left >> 32);

    extern size_t IP_rev[64];
    return permute(input, 64, IP_rev);
}