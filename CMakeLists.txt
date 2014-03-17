cmake_minimum_required(VERSION 2.8.0)

project(cmprsr)

set(CMAKE_MODULE_PATH ${CMAKE_CURRENT_SOURCE_DIR}/CMake)
set(CMAKE_PREFIX_PATH ${CMAKE_CURRENT_SOURCE_DIR}/external/cxxtest)

set(CMPRSR_BUILD_TESTS 1 CACHE BOOL "Build tests")

set(EXECUTABLE_NAME cmprsr)

set(CMPRSRLIB_INCLUDE_DIR ${CMAKE_SOURCE_DIR}/include)

add_subdirectory(src)

set(CMPRSR_SOURCE_FILES
	src/main.cpp)

include_directories(${CMPRSRLIB_INCLUDE_DIR})

add_executable(${EXECUTABLE_NAME} ${CMPRSR_SOURCE_FILES})
target_link_libraries(${EXECUTABLE_NAME} cmprsrlib)

if(CMPRSR_BUILD_TESTS)
	enable_testing()
	add_subdirectory(test)
endif()
