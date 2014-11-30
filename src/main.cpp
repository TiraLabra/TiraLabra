
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
#include "structures.hpp"

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


	linkedList<int> testList;
	testList.add(1);
	testList.add(2);
	testList.add(3);
    BOOST_REQUIRE( testList.size == 3 );

	linkedList<int> testList2;
	listItem<int> *item1 = new listItem<int>(1);
	listItem<int> *item2 = new listItem<int>(2);
	listItem<int> *item3 = new listItem<int>(3);

	testList2.add(item1);
	testList2.add(item2);
	testList2.add(item3);

    BOOST_REQUIRE( item1->prev == 0     && item1->next == item2 );
    BOOST_REQUIRE( item2->prev == item1 && item2->next == item3 );
    BOOST_REQUIRE( item3->prev == item2 && item3->next == 0 );

	testList2.del(item2);
    BOOST_REQUIRE( item1->prev == 0     && item1->next == item3 );
    BOOST_REQUIRE( item3->prev == item1 && item3->next == 0 );

    return 0;
}

#endif // RUN_TESTS
