#define __STDC_FORMAT_MACROS

#include <stdio.h>
#include <inttypes.h>
#include <stdlib.h>
#include <string.h>

#include "des.h"
#include "key_schedule.h"
#include "util.h"

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
