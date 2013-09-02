#include <stdint.h>
#include <stdio.h>

#include "key_schedule.h"

#include "util.h"

#define MODE_ENCRYPT 1
#define MODE_DECRYPT 2
#define MODE_ECB 4
#define MODE_CBC 8

uint64_t des_encrypt(uint64_t input, uint64_t keytable[SUBKEY_NUM]);
uint64_t des_decrypt(uint64_t input, uint64_t keytable[SUBKEY_NUM]);
void process_file(int mode, uint64_t key, FILE* input, FILE* output);
