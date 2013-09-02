#include <stdint.h>
#include <stdlib.h>
#include <inttypes.h>
#include <stdio.h>

#include "feistel.h"
#include "util.h"
#include "key_schedule.h"
#include "des.h"

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

/* Encrypt/decrypt file and write to output */
void process_file(int mode, uint64_t key, FILE* input, FILE* output)
{
    uint64_t* keys = malloc(sizeof (uint64_t) * SUBKEY_NUM);
    generate_subkeys(key, keys);

    uint64_t buffer = 0UL;
    uint64_t last = 0UL;
    uint8_t lastblock_length = 0;
    uint64_t total_blocks = 0UL;

    // read number of blocks and last block length or leave room to write them
    if(mode & MODE_DECRYPT) {
        fread(&total_blocks, sizeof (uint64_t), 1, input);
        BYTEORDER_CONVERT(&total_blocks);
        printf("Total blocks: %" PRId64 "\n", total_blocks);
        fread(&lastblock_length, sizeof (uint8_t), 1, input);
        printf("Last block length (bytes): %" PRId8 "\n", lastblock_length);
    } else {
        fseek(output, sizeof (uint8_t) + sizeof (uint64_t), SEEK_SET);
    }

    // read or generate Initialization Vector for CBC mode
    if(mode & MODE_CBC) {
        if(mode & MODE_DECRYPT) {
            fread(&last, sizeof (uint64_t), 1, input);
            BYTEORDER_CONVERT(&last);
            printf("IV: 0x%" PRIx64 "\n", last);
        } else {
            last = 0x43843277733UL;
            printf("Decrypting with IV: 0x%" PRIx64 "\n", last);
            BYTEORDER_CONVERT(&last);
            fwrite(&last, sizeof (uint64_t), 1, output);
            // back to original for encrypting first block
            BYTEORDER_CONVERT(&last);
        }
    }

    // encryption loop
    uint8_t read = 0;
    uint64_t block = 0;
    while((read = fread(&buffer, sizeof (uint8_t), 8, input)) > 0) {
        block++;
        BYTEORDER_CONVERT(&buffer);
        if(mode & MODE_ENCRYPT) {
            if(mode & MODE_CBC) {
                buffer ^= last;
            }
            buffer = des_encrypt(buffer, keys);
            last = buffer;
            lastblock_length = read;
        } else {
            uint64_t oldcipher = buffer; // save ciphertext of current block for CBC 
                                         // to possibly use in next block
            buffer = des_decrypt(buffer, keys);
            if(mode & MODE_CBC) {
                buffer ^= last;
                last = oldcipher;
            }           
        }

        BYTEORDER_CONVERT(&buffer);
        size_t bytes = 8;
        // if we are decrypting, we might have to write last block only partially
        if(block == total_blocks && mode & MODE_DECRYPT) {
            bytes = lastblock_length;
        }
        fwrite(&buffer, sizeof (uint8_t), bytes, output);
        buffer = 0UL;
    }

    // if encrypting, write block count and length of last block to the beginning of the file
    if(mode & MODE_ENCRYPT) {
        fseek(output, 0, SEEK_SET);
        printf("Total blocks written: %" PRId64 "\n", block);
        BYTEORDER_CONVERT(&block);
        fwrite(&block, sizeof (uint64_t), 1, output);
        fwrite(&lastblock_length, sizeof (uint8_t), 1 , output);
        printf("Last block length (bytes): %" PRId8 "\n", lastblock_length);
    }
}
