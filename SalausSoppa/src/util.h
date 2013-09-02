#include <stddef.h>
#include <stdint.h>
#include <stdio.h>

#ifndef __BIG_ENDIAN__
    #define BYTEORDER_CONVERT(x) reverse_endianness(x)
#else
    #define BYTEORDER_CONVERT(x) x
#endif

uint64_t permute(uint64_t input, size_t len, size_t permutation[len]);
uint64_t get_bit(uint64_t input, size_t n);
void reverse_endianness(uint64_t* x);
uint64_t read_key(FILE* keyfile);
