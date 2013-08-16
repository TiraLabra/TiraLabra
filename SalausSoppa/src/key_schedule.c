#include <stdint.h>
#include <string.h>

#include "util.h"
#include "key_schedule.h"

/* Initial permutation in the key schedule */
size_t PC1[] = {57,   49,    41,   33,    25,    17,    9,
                 1,   58,    50,   42,    34,    26,   18,
                10,    2,    59,   51,    43,    35,   27,
                19,   11,     3,   60,    52,    44,   36,
                63,   55,    47,   39,    31,    23,   15,
                 7,   62,    54,   46,    38,    30,   22,
                14,    6,    61,   53,    45,    37,   29,
                21,   13,     5,   28,    20,    12,    4};
size_t PC1_LEN = sizeof PC1 / sizeof PC1[0];

/* Final permutation in the key schedule */
size_t PC2[] = {14,    17,   11,    24,     1,    5,
                 3,    28,   15,     6,    21,   10,
                23,    19,   12,     4,    26,    8,
                16,     7,   27,    20,    13,    2,
                41,    52,   31,    37,    47,   55,
                30,    40,   51,    45,    33,   48,
                44,    49,   39,    56,    34,   53,
                46,    42,   50,    36,    29,   32};
size_t PC2_LEN = sizeof PC2 / sizeof PC2[0];

/* Number of rotations for given subkey */
int rotations[] = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

/**
 * @brief Shifts the target one bit to the left, wrapping around
 *
 * The function "wraps around", moving the leftmost bit to the beginning.
 * This function works with the 28 leftmost bits (as is used in the key 
 * schedule algorithm).
 */
void rotate_one(uint64_t* target)
{
	*target = (*target << 1) ^ ((*target & 0x8000000000000000UL) >> 27);
}

/**
 * @brief Rotaes both subkeys a specified amount to the left
 */
void left_rotate(uint64_t subkey[2], int rotations)
{
	for(int i = 0; i < rotations; i++) {
		rotate_one(&subkey[0]);
		rotate_one(&subkey[1]);
	}
}

/**
 * @brief DES key schedule algorithm
 */
void generate_subkeys(uint64_t key, uint64_t subkeys[SUBKEY_NUM])
{
	uint64_t permuted_key = permute(key, PC1_LEN, PC1);
	uint64_t left = permuted_key & 0xfffffff000000000UL;
	uint64_t right = (permuted_key & 0x0000000fffffff00UL) << 28;

	uint64_t temp_subkeys[SUBKEY_NUM][2];
	temp_subkeys[0][0] = left;
	temp_subkeys[0][1] = right;
	left_rotate(temp_subkeys[0], rotations[0]);

	for(int i = 1; i < SUBKEY_NUM; i++) {
		memcpy(temp_subkeys[i], temp_subkeys[i-1], sizeof temp_subkeys[i-1]);
		left_rotate(temp_subkeys[i], rotations[i]);
	}

	for(int i = 0; i < SUBKEY_NUM; i++) {
		uint64_t temp_left = temp_subkeys[i][0];
		uint64_t temp_right = temp_subkeys[i][1] >> 28;
		subkeys[i] = permute(temp_left ^ temp_right, PC2_LEN, PC2);
	}
}