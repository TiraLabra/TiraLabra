#include <stdio.h>
#include <stdint.h>

#include "CuTest.h"
#include "des.h"
#include "key_schedule.h"

void TestDesFileEncryptDecrypt(CuTest *tc)
{
	FILE* input = fopen("test/test_data", "rb");
	FILE* output = fopen("test/test_data_encrypted", "wb");
	FILE* keyfile = fopen("test/test_key", "rb");

	uint64_t key = read_key(keyfile);
	fclose(keyfile);

	int mode = MODE_ENCRYPT;
	mode |= MODE_CBC;
    process_file(mode, key, input, output);

    fclose(input);
    fclose(output);

	input = fopen("test/test_data_encrypted", "rb");
	output = fopen("test/test_data_decrypted", "wb");
	mode = MODE_DECRYPT;
	mode |= MODE_CBC;
    process_file(mode, key, input, output);

    fclose(input);
    fclose(output);

	input = fopen("test/test_data", "rb");
	output = fopen("test/test_data_decrypted", "rb");

	uint8_t read = 0;
	while(fread(&read, sizeof (uint8_t), 1, input) > 0) {
		uint8_t other = 0;
		fread(&other, sizeof (uint8_t), 1, output);
		if(read != other) {
			CuFail(tc, "Files did not match");
		}
	}
}
