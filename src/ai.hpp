
#pragma once

#include "chess.hpp"

/**
 * Class representing single node in the move tree, which 
 * contains the value calculated for the current player whose 
 * turn it is, previous move that lead to this position, and 
 * number of moves we have 
 */
class moveNode {
public:
    int value;
    int nmoves;
    moveNode()  {}
    moveNode(int v) : value(v) { }
    // moveNode(int v, cmove m) : value(v), prev_move(m) { }
    // ~moveNode() {}
};
    
/**
 * Class containing the tree of possible moves, and the value of each
 * game state for the given player.
 */
class moveTree {
public:
    tree< moveNode* > *gameTree;
    linkedList<cmove> *moveList;

    moveTree() {
        moveNode *rootNode = new moveNode(0);
        gameTree = new tree< moveNode* >(rootNode);
    }

    void freeNodes(tree< moveNode* > *ptr) {
        for (int i = 0; i < ptr->children->size; i++)
            freeNodes((*ptr->children)[i]);
        delete ptr->item;
    }
    
    ~moveTree() {
        if (gameTree) {
            freeNodes(gameTree);
            delete gameTree;
        }
        if (moveList)
            delete moveList;
    }

    /**
     * Build a tree structure of possible moves and the value of 
     * the game state they lead to. 
     * 
     * @param board initial state of game board
     * @param root root of game tree
     * @param depth depth of tree
     */
    void buildTree(chessBoard &board, tree< moveNode* > *root, int depth, bool first = true) {
        if (depth <= 0) return;
        
        linkedList<cmove> *moves = board.findMoves();
        root->item->nmoves = moves->size;

        // we only need to know what the next move is that we should make,
        // so in order to save memory we only store move list on the first call
        if (first) 
            moveList = moves;

        for (int i = 0; i < moves->size; i++) {
            chessBoard nb = board;
            cmove m = (*moves)[i];
            nb.doMove(m.from.x, m.from.y, m.to.x, m.to.y);

            moveNode *nn = 0;
            // we only use the values calculated at the last nodes, so evaluation is
            // skipped for all other depths
            if (depth == 1)
                // nn = new moveNode(nb.evaluate(nb.currentPlayer));
                nn = new moveNode(nb.evaluate(PC_Black));
            else
                nn = new moveNode(0);
            tree< moveNode* > *newnode = new tree< moveNode* >(nn);
            buildTree(nb, newnode, depth-1, false);
            root->insert(newnode);
        }

        if (!first)
            delete moves;
    }
};


#include "algorithms.hpp"

/**
 * Class representing the chess AI 
 * The intention was to store a state here so that it woulnd't 
 * need to be calculated again each round, but I ran out of 
 * time... 
 */
class chessAI {
public:
    chessAI() {}

#if 0
    int printMoves(tree<moveNode> gt, int depth) {
        if (depth < 0) return 0;
    
        linkedList< tree<moveNode>* > *ch = gt.children;
        for (int i = 0; i < ch->size; i++) {
            if (depth == 0) {
                puts("");
                chessBoard *b = (*ch)[i]->item.board;
            
                b->printFEN();

                for (int y = 7; y >= 0; y--) {
                    for (int x = 0; x < 8; x++) {
                        linkedList<cmove> moves;
                        b->findMoves( moves, b->currentPlayer, x, y);
                        printf("%c%d ", b->gameState[x][y].chtype, moves.size);
                    }
                    puts("");
                }
                // printf("%d\n", (*ch)[i]->item->moves->size);
            }
        }
        return 0;
    }
#endif

    /**
     * Calculate the sum of possible moves we found at given depth 
     * in tree. Mainly used to test that the AI finds the correct 
     * number of possible moves. 
     * 
     * @param gt game tree
     * @param depth depth
     * 
     * @return int number of moves
     */
    int sumMoves(tree<moveNode*> *gt, int depth) {
        if (depth < 0) return 0;
    
        linkedList< tree<moveNode*>* > *ch = gt->children;
        int sum = 0;
        for (int i = 0; i < ch->size; i++) {
            sum += sumMoves((*ch)[i], depth-1);
            if (depth == 0)
                sum += (*ch)[i]->item->nmoves;
        }
        return sum;
    }

    /**
     * Builds a game tree and searches for a good movme in it using 
     * the minmax algorithm combined with alpha-beta pruning. 
     *  
     * @param board current state of the game
     * @param treeDepth depth of the game tree we build
     * @param score_out the score of the best move is returned here
     *  
     * @return cmove move chosen by the AI 
     */
    int findMove(chessBoard &board, int treeDepth, cmove &aimove) {
        moveTree mt;
        clock_t start = clock();
        mt.buildTree(board, mt.gameTree, treeDepth);
        clock_t end = clock();
        printf("Built move tree with depth %d in %f seconds.\n", 
               treeDepth,
               ((float)end - (float)start) / CLOCKS_PER_SEC);

        // printf("current player is %d\n", board.currentPlayer);
        
        linkedList< tree<moveNode*>* > *ch = mt.gameTree->children;
        linkedList< cmove > *moves = mt.moveList;

        if (moves->size != ch->size ) {
            cout << "ERROR: move numbers do not match" << endl;
        }

        // printf("moves depth 1: %d\n", sumMoves(mt.gameTree, 0));
        // printf("moves depth 2: %d\n", sumMoves(mt.gameTree, 1));
        // printf("moves depth 3: %d\n", sumMoves(mt.gameTree, 2));
        
        int bestValue = INT_MIN;
        
        start = clock();
        for (int i = 0; i < ch->size; i++) {
            tree<moveNode*> *n = (*ch)[i];

            // start with min value (bmax = false) because in the situation we
            // are evaluating it is the opponent's turn!
            int value = alphabeta(n, treeDepth-1, INT_MIN, INT_MAX, false);
            // int value = minimax(n, treeDepth-1, false);

#if 0
            cmove move = (*ch)[i]->item->prev_move;
            printf("Move %c%d %c%d, score %d\n",
                   move.from.x + 'a', move.from.y + 1,
                   move.to.x + 'a', move.to.y + 1, value);
#endif

            if (value > bestValue) {
                // aimove = (*ch)[i]->item->prev_move;
                aimove = (*moves)[i];
                bestValue = value;
            }
        }
        end = clock();

        printf("Minimax search with alpha-beta took %f seconds.\n", 
               ((float)end - (float)start) / CLOCKS_PER_SEC);

        return bestValue;
    }
};
