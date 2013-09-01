#include <stdint.h>
#include "key_schedule.h"

uint64_t des_encrypt(uint64_t input, uint64_t keytable[SUBKEY_NUM]);
uint64_t des_decrypt(uint64_t input, uint64_t keytable[SUBKEY_NUM]);
