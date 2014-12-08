
#pragma once

#include <limits.h>

/**
 * Minimax algorithm according to http://www.wikiwand.com/en/Minimax
 * @param board current state of the game
 * @param depth depth of the search
 * @param max are we searching of max player or min player
 */
int minimax(tree< moveNode* > *gameTree, int depth, bool max) {
    linkedList< tree<moveNode*>* > *ch = gameTree->children;
    if (depth == 0 || ch->size == 0)
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
