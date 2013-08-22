#include "CuTest.h"
#include "des.h"

void DesEncryptTest(CuTest *tc)
{
	uint64_t input = 0x8787878787878787ul;
	uint64_t key = 0x0E329232EA6D0D73ul;
	CuAssertTrue(tc, des_encrypt(input, key) == 0);

	input = 0x596F7572206C6970ul;
	key = 0x0E329232EA6D0D73ul;
	CuAssertTrue(tc, des_encrypt(input, key) == 0xC0999FDDE378D7EDul);	
}