#include <stdint.h>

#include "CuTest.h"
#include "des.h"

void TestDesEncrypt(CuTest *tc)
{
    uint64_t input = 0x8787878787878787ul;
    uint64_t key = 0x0E329232EA6D0D73ul;
    CuAssertTrue(tc, des_encrypt(input, key) == 0);

    input = 0x596F7572206C6970ul;
    key = 0x0E329232EA6D0D73ul;
    CuAssertTrue(tc, des_encrypt(input, key) == 0xC0999FDDE378D7EDul);  
}

void TestDesDecrypt(CuTest *tc)
{
    uint64_t input = 0;
    uint64_t key = 0x0E329232EA6D0D73ul;
    CuAssertTrue(tc, des_decrypt(input, key) == 0x8787878787878787ul);
}

// http://people.csail.mit.edu/rivest/pubs/Riv85.txt
void TestDesDefinitively(CuTest *tc)
{
    uint64_t current = 0x9474B8E8C73BCA7Dul;

    for(int i = 0; i < 16; i++) {
        if(i % 2 != 0) {
            current = des_decrypt(current, current);
        } else {
            current = des_encrypt(current, current);
        }
    }
    CuAssertTrue(tc, current == 0x1B1A2DDB4C642438ul);
}