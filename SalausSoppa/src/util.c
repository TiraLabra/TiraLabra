#include <stddef.h>
#include <stdint.h>
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
