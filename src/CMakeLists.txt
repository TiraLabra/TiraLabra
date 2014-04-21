set(CMPRSRLIB_HEADER_FILES
	${CMPRSRLIB_INCLUDE_DIR}/cmprsrlib.h
	${CMPRSRLIB_INCLUDE_DIR}/cmprsr_types.h
	${CMPRSRLIB_INCLUDE_DIR}/cmprsr_sequence.h
	${CMPRSRLIB_INCLUDE_DIR}/cmprsr_merge.h
	${CMPRSRLIB_INCLUDE_DIR}/huffman.h)

set(CMPRSRLIB_SOURCE_FILES
	cmprsrlib.cpp
	huffman.cpp)

include_directories(${CMPRSRLIB_INCLUDE_DIR})

add_library(cmprsrlib ${CMPRSRLIB_SOURCE_FILES} ${CMPRSRLIB_HEADER_FILES})
