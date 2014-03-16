find_package(CxxTest)
if(CXXTEST_FOUND)
	include_directories(${CXXTEST_INCLUDE_DIR})
	CXXTEST_ADD_TEST(cmprsr_test_sequence cmprsr_test_sequence.cpp
		${CMAKE_CURRENT_SOURCE_DIR}/cmprsr_test_sequence.h)
	#CXXTEST_ADD_TEST(cmprsr_test_merge cmprsr_test_merge.cpp
	#	cmprsr_test_merge.h)
	target_link_libraries(cmprsr_test_sequence cmprsrlib)
	#target_link_libraries(cmprsr_test_merge cmprsrlib)
endif()