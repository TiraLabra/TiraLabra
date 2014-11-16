
#include <iostream>
#include <cstdio>
#include <algorithm>
#include <list>
#include <vector>
#include <map>
using namespace std;

#include "chess.hpp"
#include "ai.hpp"
#include "algorithms.hpp"

#ifndef RUN_TESTS

int main(int argc, char *argv[]) {
    chessBoard board;
    board.print();
    return 0;
}


#else // RUN_TESTS

#include <boost/test/minimal.hpp>

int test_main( int, char *[] )
{
    chessBoard board;

    // test some simple moves
    BOOST_REQUIRE( board.moveLegal(1, 2, 1, 3) == true );
    BOOST_REQUIRE( board.moveLegal(1, 2, 2, 3) == false );

    return 0;
}

#endif // RUN_TESTS
