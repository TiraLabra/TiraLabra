find_package(CxxTest)
if(CXXTEST_FOUND)
	include_directories(${CXXTEST_INCLUDES})
	cxx_test(cmprsr_test_sequence
		${CMAKE_CURRENT_SOURCE_DIR}/cmprsr_test_sequence.h)
	cxx_test(cmprsr_test_huffman
		${CMAKE_CURRENT_SOURCE_DIR}/cmprsr_test_huffman.h)
	target_link_libraries(cmprsr_test_sequence cmprsrlib)
	target_link_libraries(cmprsr_test_huffman cmprsrlib)
endif()
