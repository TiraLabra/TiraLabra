#include <stdint.h>
#include <stddef.h>

#define SBOX_ROWS 4
#define SBOX_COLS 16

extern uint8_t sboxes[8][SBOX_ROWS][SBOX_COLS];
extern size_t P[32];
extern size_t E[48];

uint64_t feistel(uint64_t input, uint64_t subkey);
uint64_t sbox(uint64_t input, uint8_t box[SBOX_ROWS][SBOX_COLS]);