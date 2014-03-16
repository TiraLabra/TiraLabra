/* Generated file, do not edit */

#ifndef CXXTEST_RUNNING
#define CXXTEST_RUNNING
#endif

#define _CXXTEST_HAVE_STD
#include <cxxtest/TestListener.h>
#include <cxxtest/TestTracker.h>
#include <cxxtest/TestRunner.h>
#include <cxxtest/RealDescriptions.h>
#include <cxxtest/TestMain.h>
#include <cxxtest/ErrorPrinter.h>

int main( int argc, char *argv[] ) {
 int status;
    CxxTest::ErrorPrinter tmp;
    CxxTest::RealWorldDescription::_worldName = "cxxtest";
    status = CxxTest::Main< CxxTest::ErrorPrinter >( tmp, argc, argv );
    return status;
}
bool suite_SequenceTest_init = false;
#include "c:\Users\paddy\Documents\TiraLabra\test\cmprsr_test_sequence.h"

static SequenceTest suite_SequenceTest;

static CxxTest::List Tests_SequenceTest = { 0, 0 };
CxxTest::StaticSuiteDescription suiteDescription_SequenceTest( "cmprsr_test_sequence.h", 5, "SequenceTest", suite_SequenceTest, Tests_SequenceTest );

static class TestDescription_suite_SequenceTest_testSequence : public CxxTest::RealTestDescription {
public:
 TestDescription_suite_SequenceTest_testSequence() : CxxTest::RealTestDescription( Tests_SequenceTest, suiteDescription_SequenceTest, 8, "testSequence" ) {}
 void runTest() { suite_SequenceTest.testSequence(); }
} testDescription_suite_SequenceTest_testSequence;

#include <cxxtest/Root.cpp>
const char* CxxTest::RealWorldDescription::_worldName = "cxxtest";
