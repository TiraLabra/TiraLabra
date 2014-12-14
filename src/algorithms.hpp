
#pragma once

#include "ai.hpp"
#include "chess.hpp"
#include <limits.h>

/**
 * Minimax algorithm according to https://en.wikipedia.org/wiki/Minimax 
 * @param board current state of the game
 * @param depth depth of the search
 * @param max are we searching of max player or min player
 */
int minimax(tree< moveNode* > *gameTree, int depth, bool max) {
    linkedList< tree<moveNode*>* > *ch = gameTree->children;
    if (depth == 0)
        return gameTree->item->value;

    if (max) {
        int bestValue = INT_MIN;
        for (int i = 0; i < ch->size; i++) {
            tree<moveNode*>* n = (*ch)[i];
            bestValue = std::max(bestValue, minimax(n, depth - 1, false));
        }

        return bestValue;
    } else {
        int bestValue = INT_MAX;
        for (int i = 0; i < ch->size; i++) {
            tree<moveNode*>* n = (*ch)[i];
            bestValue = std::min(bestValue, minimax(n, depth - 1, true));
        }
        
        return bestValue;
    }
}

/**
 * Minimax algorithm combined with alpha-beta pruning
 * 
 * @param gameTree tree of possible moves and their values
 * @param depth depth of the tree
 * @param a alpha
 * @param b beta
 * @param max maximizing or minimizing player
 * 
 * @return int value of move
 */
int alphabeta(tree< moveNode* > *gameTree, int depth, int a, int b, bool max) {
    linkedList< tree<moveNode*>* > *ch = gameTree->children;
    if (depth <= 0)
        return gameTree->item->value;

    if (max) {
        int bestValue = INT_MIN;
        for (int i = 0; i < ch->size; i++) {
            tree<moveNode*>* n = (*ch)[i];
            a = std::max(bestValue, alphabeta(n, depth - 1, a, b, false));
            if (b <= a)
                return b;
        }
        return a;
    } else {
        int bestValue = INT_MAX;
        for (int i = 0; i < ch->size; i++) {
            tree<moveNode*>* n = (*ch)[i];
            b = std::min(bestValue, alphabeta(n, depth - 1, a, b, true));
            if (b <= a)
                return a;
        }
        
        return b;
    }
}
