#define __STDC_FORMAT_MACROS

#include <stdio.h>
#include <inttypes.h>
#include <stdlib.h>
#include <string.h>
#include "des.h"
#include "key_schedule.h"

#ifndef __BIG_ENDIAN__
    #define BYTEORDER_CONVERT(x) convert_to_le(x)

    void convert_to_le(uint64_t* x) 
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

#else
    #define BYTEORDER_CONVERT(x) x
#endif

#define MODE_ENCRYPT 1
#define MODE_DECRYPT 2
#define MODE_ECB 4
#define MODE_CBC 8

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

void printUsage(char* cmd)
{
    printf("Usage: \n"
           "  to encrypt, `%s -e --mode keyfile input output`\n"
           "  to decrypt, `%s -d --mode keyfile input output`\n"
           "  valid modes are ecb (electronic codebook) and cbc (cipher block chaining)\n", 
              cmd, cmd);
}

int main(int argc, char *argv[]) 
{
    if(argc != 6) {
        printUsage(argv[0]);
        return 0;
    }

    int mode = 0;
    if(strcmp(argv[1], "-e") == 0) {
        mode |= MODE_ENCRYPT;
    } else if(strcmp(argv[1], "-d") == 0) {
        mode |= MODE_DECRYPT;
    } else {
        printUsage(argv[0]);
        return 0;
    }

    if(strcmp(argv[2], "--ecb") == 0) {
        mode |= MODE_ECB;
    } else if(strcmp(argv[2], "--cbc") == 0) {
        mode |= MODE_CBC;
    } else {
        printUsage(argv[0]);
        return 0;
    } 

    FILE* key_handle = fopen(argv[3], "rb");
    if(key_handle == NULL) {
        printf("Could not open key file\n");
        exit(EXIT_FAILURE);
    }

    FILE* input = fopen(argv[4], "rb");
    if(input == NULL) {
        printf("Could not open input file\n");
        exit(EXIT_FAILURE);
    }
    FILE* output = fopen(argv[5], "wb");

    uint64_t key = read_key(key_handle);

    process_file(mode, key, input, output);


    return 0;
}
