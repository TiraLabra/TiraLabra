

/* This is auto-generated code. Edit at your own peril. */
#include <stdio.h>
#include <stdlib.h>

#include "CuTest.h"


extern void TestAllZeroPermute(CuTest*);
extern void TestAllOneFromFirstBitPermute(CuTest*);
extern void TestExampleInitialPermutationAndReverse(CuTest*);


void RunAllTests(void) 
{
    CuString *output = CuStringNew();
    CuSuite* suite = CuSuiteNew();


    SUITE_ADD_TEST(suite, TestAllZeroPermute);
    SUITE_ADD_TEST(suite, TestAllOneFromFirstBitPermute);
    SUITE_ADD_TEST(suite, TestExampleInitialPermutationAndReverse);

    CuSuiteRun(suite);
    CuSuiteSummary(suite, output);
    CuSuiteDetails(suite, output);
    printf("%s\n", output->buffer);
    CuStringDelete(output);
    CuSuiteDelete(suite);
}

int main(void)
{
    RunAllTests();
}

