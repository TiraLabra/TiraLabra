cmake_minimum_required(VERSION 2.8.0)

project(cmprsr)

list(APPEND CMAKE_MODULE_PATH ${CMAKE_SOURCE_DIR}/CMake)

set(CMPRSR_BUILD_TESTS 1 CACHE BOOL "Build tests")

set(EXECUTABLE_NAME cmprsr)

set(CMPRSRLIB_INCLUDE_DIR ${CMAKE_SOURCE_DIR}/include)

add_subdirectory(src)

set(CMPRSR_SOURCE_FILES
	main.cpp)

include_directories(${CMPRSRLIB_INCLUDE_DIR})

add_executable(${EXECUTABLE_NAME} ${CMPRSR_SOURCE_FILES})
target_link_libraries(${EXECUTABLE_NAME} cmprsrlib)

if(CMPRSR_BUILD_TESTS)
	enable_testing()
	add_subdirectory(test)
	add_test(NAME cmprsr_test_sequence COMMAND cmprsr_test_sequence)
	add_test(NAME cmprsr_test_merge COMMAND cmprsr_test_merge)
endif()