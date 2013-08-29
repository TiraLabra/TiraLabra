#define __STDC_FORMAT_MACROS

#include <stdio.h>
#include <inttypes.h>
#include <stdlib.h>
#include <string.h>
#include "des.h"

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

void process_file(int mode, char* keyfile, char* inputfile, char* outputfile)
{
    FILE* key_handle = fopen(keyfile, "rb");
    if(key_handle == NULL) {
        printf("Could not open key file\n");
        exit(EXIT_FAILURE);
    }

    FILE* input = fopen(inputfile, "rb");
    if(input == NULL) {
        printf("Could not open input file\n");
        exit(EXIT_FAILURE);
    }
    FILE* output = fopen(outputfile, "wb");

    uint64_t key = read_key(key_handle);

    uint64_t buffer = 0UL;
    uint64_t last = 0UL;

    if(mode & MODE_CBC) {
        if(mode & MODE_DECRYPT) {
            fread(&last, sizeof (uint64_t), 1, input);
            BYTEORDER_CONVERT(&last);
            printf("IV: %" PRIx64 "\n", last);
        } else {
            last = 0x43843277733UL;
            BYTEORDER_CONVERT(&last);
            fwrite(&last, sizeof (uint64_t), 1, output);
            // back to original for encrypting first block
            BYTEORDER_CONVERT(&last);
        }
    }

    size_t read = 0;
    while((read = fread(&buffer, sizeof (uint8_t), 8, input)) > 0) {
        BYTEORDER_CONVERT(&buffer);
        if(mode & MODE_ENCRYPT) {
            if(mode & MODE_CBC) {
                buffer ^= last;
            }
            buffer = des_encrypt(buffer, key);
            last = buffer;
        } else {
            uint64_t oldcipher = buffer;
            buffer = des_decrypt(buffer, key);
            if(mode & MODE_CBC) {
                buffer ^= last;
                last = oldcipher;
            }           
        }

        BYTEORDER_CONVERT(&buffer);
        fwrite(&buffer, sizeof (uint8_t), read, output);
        buffer = 0UL;
    }
}

void printUsage(char* cmd)
{
    printf("Usage: \n"
           "  to encrypt, `%s -e --mode=... keyfile input output`\n"
           "  to decrypt, `%s -d --mode=... keyfile input output`\n"
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

    process_file(mode, argv[3], argv[4], argv[5]);


    return 0;
}
