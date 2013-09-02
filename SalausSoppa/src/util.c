#include <stddef.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

#include "util.h"

/** 
 * @brief Perform a permutation on an uint64_t
 *
 * Returns a permutation of the bits in input as defined in
 * array p. The indexes defined in p start from 1, with 1 being
 * the leftmost bit (as presented in DES specification).
 */
uint64_t permute(uint64_t input, size_t plen, size_t p[plen]) 
{
    uint64_t output = 0ULL;
    for(int i = 0; i < plen; i++) {
        output ^= get_bit(input, p[i]) << (63 - i);
    }
    return output;
}

/**
 * @brief Gets bit at position n, moved to the rightmost position
 */
uint64_t get_bit(uint64_t input, size_t n)
{
    return (input & (1ULL << (64 - n))) >> (64 - n);
}

/**
 * @brief Reverses endianness of an uint64_t
 */
void reverse_endianness(uint64_t* x) 
{
    *x = ((0x00000000000000ffUL & *x) << 56) |
         ((0x000000000000ff00UL & *x) << 40) |
         ((0x0000000000ff0000UL & *x) << 24) |
         ((0x00000000ff000000UL & *x) << 8) |
         ((0x000000ff00000000UL & *x) >> 8) |
         ((0x0000ff0000000000UL & *x) >> 24) |
         ((0x00ff000000000000UL & *x) >> 40) |
         ((0xff00000000000000UL & *x) >> 56);
}

/* Read a 64-bit DES key from file */
uint64_t read_key(FILE* keyfile) 
{
    uint64_t key = 0UL;
    size_t read = fread(&key, sizeof key, 1, keyfile);

    if(read != 1) {
        printf("Invalid key length\n");
        exit(EXIT_FAILURE);
    }

    BYTEORDER_CONVERT(&key);
    return key;
}
