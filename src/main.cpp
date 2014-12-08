
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

int sumMoves(tree<moveNode*> *gt, int depth) {
    if (depth < 0) return 0;
    
    linkedList< tree<moveNode*>* > *ch = gt->children;
    int sum = 0;
    for (int i = 0; i < ch->size; i++) {
        sum += sumMoves((*ch)[i], depth-1);

        if (depth == 0) {
            chessBoard &b = (*ch)[i]->item->board;
            linkedList< cmove > *moves = b.findMoves(b.currentPlayer);
            sum += moves->size;
            delete moves;
        }
        // if (depth == 0) sum += (*ch)[i]->item->moves->size;
    }
    return sum;
}

int printMoves(tree<moveNode> gt, int depth) {
    if (depth < 0) return 0;
    
    linkedList< tree<moveNode>* > *ch = gt.children;
    for (int i = 0; i < ch->size; i++) {
        if (depth == 0) {
            puts("");
            chessBoard b = (*ch)[i]->item.board;
            
            b.printFEN();

            for (int y = 7; y >= 0; y--) {
                for (int x = 0; x < 8; x++) {
                    linkedList<cmove> moves;
                    b.findMoves( moves, b.currentPlayer, x, y);
                    printf("%c%d ", b.gameState[x][y].chtype, moves.size);
                }
                puts("");
            }
            // printf("%d\n", (*ch)[i]->item->moves->size);
        }
    }
    return 0;
}

int main(int argc, char *argv[]) {
    chessBoard board;

    while (true) {
        board.print();
        board.readMove();

        moveTree mt(board, board.currentPlayer);
        mt.buildTree(mt.gameTree, 4);

        linkedList< tree<moveNode*>* > *ch = mt.gameTree->children;
        printf("moves depth 1: %d\n", sumMoves(mt.gameTree, 0));
        printf("moves depth 2: %d\n", sumMoves(mt.gameTree, 1));
        printf("moves depth 3: %d\n", sumMoves(mt.gameTree, 2));
        
        int bestValue = INT_MIN;
        cmove aimove;
        for (int i = 0; i < ch->size; i++) {
            tree<moveNode*> *n = (*ch)[i];
            int value = minimax(n, 3, true);

            cmove move = (*ch)[i]->item->prev_move;
            printf("Move %c%d %c%d, score %d\n",
                   move.from.x + 'a', move.from.y + 1,
                   move.to.x + 'a', move.to.y + 1, value);

            if (value > bestValue) {
                // aimove = (*ch)[i]->item->prev_move;
                aimove = move;
                bestValue = value;
            }
        }

        printf("AI chose move %c%d %c%d with score %d\n",
               aimove.from.x + 'a', aimove.from.y + 1,
               aimove.to.x + 'a', aimove.to.y + 1, bestValue);

        if (!board.doMove(aimove.from.x, aimove.from.y, aimove.to.x, aimove.to.y)) {
            printf("ERROR: AI selected invalid move: [ %d %d ] -> [ %d %d ]",
                   aimove.from.x, aimove.from.y, aimove.to.x, aimove.to.y);
            exit(0);
        }
        
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

    for (int i = 0; i < 8; i++) {
        linkedList< cmove > wmoves, bmoves;
        board.findMoves(wmoves, PC_White, i, 0);
        board.findMoves(bmoves, PC_Black, i, 7);
        
        printf("%d: %d, %d\n", i, wmoves.size, bmoves.size);
    }
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
    chessBoard b;
    puts("build tree");
    moveTree mt(b, PC_White);

    puts("done");
    linkedList< tree<moveNode*>* > *ch = mt.gameTree->children;
    
    for (int i = 0; i < ch->size; i++) {
        printf("%d\n", (*ch)[i]->item->moves->size);
        
    }
    
    // b.currentPlayer = PC_Black;
    // linkedList<cmove> m = b.findMoves(4, 7);
    //printf("yep %d\n", m.size);
    

    // test_moves();
    test_structures();

    // chessBoard b;
    // moveAI m(b, PC_White);
    
    
    return 0;
}

#endif // RUN_TESTS
