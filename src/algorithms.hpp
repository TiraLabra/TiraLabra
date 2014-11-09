
#pragma once

/**
 * Minimax algorithm according to http://www.wikiwand.com/en/Minimax
 * @param board current state of the game
 * @param depth depth of the search
 * @param max are we searching of max player or min player
 */
int minimax(chessBoard &board, int depth, bool max) {
    if (depth == 0) return board.evaluate(max);
    vector<chessBoard> moves;
    // TODO get possible moves...
    // board.possibleMoves(player)
    if (max) {
        int bestValue = INT_MIN;
        for (chessBoard b : moves)
            bestValue = std::max(bestValue, minimax(b, depth - 1, false));
        return bestValue;
    } else {
        int bestValue = INT_MAX;
        for (chessBoard b : moves)
            bestValue = std::min(bestValue, minimax(b, depth - 1, true));
        return bestValue;
    }
}
