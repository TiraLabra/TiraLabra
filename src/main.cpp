
#include <iostream>
#include <cstdio>
#include <ctime>
using namespace std;

#include "chess.hpp"
#include "ai.hpp"
#include "algorithms.hpp"
#include "structures.hpp"

#ifndef RUN_TESTS

int main(int argc, char *argv[]) {
    chessBoard board;
    chessAI ai;

    int depth = 4;
    if (argc >= 2) {
        depth = atoi(argv[1]);
    }
    
    cout << "Using " << depth << " for game tree depth." << endl;
    while (true) {
        board.print();
        board.readMove();

        int score=0;
        clock_t start = clock();
        cmove aimove = ai.findMove(board, depth, &score);
        clock_t end = clock();

        printf("\nFound a move in %f seconds.\n", ((float)end - start) / CLOCKS_PER_SEC);

        printf("AI chose move %c%d %c%d with score %d\n",
               aimove.from.x + 'a', aimove.from.y + 1,
               aimove.to.x + 'a', aimove.to.y + 1, score);

        if (!board.doMove(aimove.from.x, aimove.from.y, aimove.to.x, aimove.to.y)) {
            printf("ERROR: AI selected invalid move: [ %d %d ] -> [ %d %d ]",
                   aimove.from.x, aimove.from.y, aimove.to.x, aimove.to.y);
            exit(0);
        }
        
        // printf("board value for AI: %d", board.evaluate(PC_Black));
        
        if (board.checkWin()) break;
    }
    
}


#else // RUN_TESTS

#include <boost/test/minimal.hpp>

void test_moves()
{
    chessBoard board;

    // Verify that AI finds corrects moves for pawns on white side starting position
    for (int x = 0; x < 8; x++) {
        linkedList<cmove> m =  * board.findMoves(x, 1);
        BOOST_REQUIRE( m.size == 2 );
        BOOST_REQUIRE( m[0].to.x == x && m[0].to.y == 3 );
        BOOST_REQUIRE( m[1].to.x == x && m[1].to.y == 2 );
    }

    // Verify that AI finds corrects moves for pawns on black side starting position
    board.currentPlayer = PC_Black;
    for (int x = 0; x < 8; x++) {
        linkedList<cmove> m =  * board.findMoves(x, 6);
        BOOST_REQUIRE( m.size == 2 );
        BOOST_REQUIRE( m[0].to.x == x && m[0].to.y == 4 );
        BOOST_REQUIRE( m[1].to.x == x && m[1].to.y == 5 );
    }

    board.currentPlayer = PC_White;
    linkedList<cmove> knight_moves = * board.findMoves(1, 0);
    BOOST_REQUIRE( knight_moves.size == 2 );
    BOOST_REQUIRE( knight_moves[0].to.x == 2 && knight_moves[0].to.y == 2 );
    BOOST_REQUIRE( knight_moves[1].to.x == 0 && knight_moves[1].to.y == 2 );

    // number of moves for white at starting position in each depth
    // 1  20
    // 2  400
    // 3  8902
    // 4  197281
    // 5  4865609
    // 6  119060324
    // 7  3195901860
    // 8  84998978956
    // 9  2439530234167
    // 10 69352859712417
    // http://mediocrechess.blogspot.fi/2007/01/guide-perft-scores.html

    moveTree mt;
    chessAI ai;
    
    mt.buildTree(board, mt.gameTree, 4);

    BOOST_REQUIRE( mt.gameTree->item->nmoves == 20 );
    BOOST_REQUIRE( ai.sumMoves(mt.gameTree, 0) == 400 );
    BOOST_REQUIRE( ai.sumMoves(mt.gameTree, 1) == 8902 );
    BOOST_REQUIRE( ai.sumMoves(mt.gameTree, 2) == 197281 );
    // BOOST_REQUIRE( ai.sumMoves(mt.gameTree, 3) == 4865609 );
}

void test_structures()
{
    // tests for linked list structure
    linkedList<int> *testList = new linkedList<int>;
    testList->add(1);
    testList->add(2);
    testList->add(3);
    BOOST_REQUIRE( testList->size == 3 );
    delete testList;
    

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
}

int test_main( int, char *[] )
{
    clock_t start = clock();
    test_moves();
    test_structures();
    clock_t end = clock();
    printf("Finished running tests in %f seconds.", ((float)end - start) / CLOCKS_PER_SEC);
    

    return 0;
}

#endif // RUN_TESTS
