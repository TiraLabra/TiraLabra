/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import tiralabra.game.Board;
import tiralabra.game.Player;

/**
 * The AI which calculates which move the AI Should take in any given situation.
 *
 * @author atte
 */
public class AI {

    /**
     * Comparator that compares moves based on values given in a HashMap.
     */
    public static class MoveSorter implements Comparator<Long> {

        private final HashMap<Long, Integer> values;

        public MoveSorter(HashMap<Long, Integer> pieces) {
            this.values = pieces;
        }

        @Override
        public int compare(Long o1, Long o2) {
            return values.get(o2) - values.get(o1);
        }

    }

    /**
     * The board used for this AI.
     */
    private final Board board;
    /**
     * A table that is used to store the hypothetical value of holding any one
     * point.
     */
    private static final int[][] pieceValues = new int[][]{{65, -3, 6, 4, 4, 6, -3, 65},
    {-3, -29, 1, 1, 1, 3, -29, -3},
    {6, 3, 5, 3, 3, 5, 3, 6},
    {4, 1, 3, 1, 1, 3, 1, 4},
    {4, 1, 3, 1, 1, 3, 1, 4},
    {6, 3, 5, 3, 3, 5, 3, 6},
    {-3, -29, 3, 1, 1, 3, -29, -3},
    {65, -3, 6, 4, 4, 6, -3, 65}};

    public AI(Board board) {
        this.board = board;
    }

//    /**
//     * The way a beginner would play. Always choose the move which brings the
//     * most new pieces.
//     *
//     * @param team
//     * @return the move which brings the most new pieces, stores a long
//     * variable.
//     */
//    public long greedyBeginnerMove(int team) {
//        ArrayList<Long> moves = board.findLegalMoves(team);
//        HashMap<Long, Integer> piecesOwned = new HashMap<>();
//
//        for (long move : moves) {
//            int nmbOfFlips = board.place(move, team);
//
//            piecesOwned.put(move, nmbOfFlips);
//
//            board.undo();
//        }
//        Collections.sort(moves, new MoveSorter(team, piecesOwned));
//
//        return moves.get(0);
//    }
    /**
     * Searches for the most optimal move by using alpha-beta-pruning.
     * @return
     */
    public long search() {
        ArrayList<Long> moves = getAllPossibleMovesInOrder();

        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        long best = moves.get(0);
        for (Long move : moves) {

            int compare = search(move, 8, alpha, beta, false);

            if (compare > alpha) {
                best = move;
                alpha = compare;
            }
        }

        return best;
    }

    /**
     * Min-max-algorithm with alpha-beta-pruning. First the given move of this
     * node is placed on the board, which is undone when we return from this
     * node. Then, using alpha-beta-pruning, we prune the obviously worse nodes.
     * The value of a given board is calculated with the boardValue -method.
     *
     * @param move
     * @param depth
     * @param alpha
     * @param beta
     * @param max
     * @return the alpha or beta value of this node.
     */
    public int search(long move, int depth, int alpha, int beta, boolean max) {
        if (move == -1) {
            board.pass();
        } else {
            board.placeTile(move);
        }

        //Reached maximum depth or end of the game, return value of the board.
        if (depth == 0 || board.gameOver()) {
            int heuristic = boardValue();
            board.undo();
            return heuristic;
        }

        ArrayList<Long> moves = getAllPossibleMovesInOrder();

        //No moves found, pass. If the other player has no moves either, return the tile difference.
        if (moves.isEmpty()) {
            moves.add((long) -1);
        }

        if (max) {
            for (Long child : moves) {
                alpha = Math.max(alpha, search(child, depth - 1, alpha, beta, !max));

                if (beta <= alpha) {
                    break;
                }
            }
            board.undo();
            return alpha;
        } else {
            for (Long child : moves) {
                beta = Math.min(beta, search(child, depth - 1, alpha, beta, !max));

                if (beta <= alpha) {
                    break;
                }
            }
            board.undo();
            return beta;
        }
    }

    /**
     * Returns all moves for the current board in order by most pieces flipped
     * to least flipped.
     *
     * @return moves
     */
    public ArrayList<Long> getAllPossibleMovesInOrder() {
        ArrayList<Long> moves = new ArrayList<>();

        for (int y = 0; y < board.height(); y++) {
            for (int x = 0; x < board.width(); x++) {
                if (board.canPlace(x, y, board.getPlayerInTurn())) {
                    moves.add(Board.point(x, y));
                }
            }
        }

        return moves;
    }

    /**
     * Return the value of a board based on the difference between tiles and the
     * presumed value of held pieces.
     *
     * @return value of board.
     */
    public int boardValue() {
        int pieceDifference = board.blackPieces() - board.whitePieces();
        if (board.getPlayerInTurn() == Player.WHITE) {
            pieceDifference = -pieceDifference;
        }

        return pieceDifference + calculateValueOfBoardBasedOnPiecesHeld();
    }

    /**
     * Calculates value of the current board based on the pieces the given team
     * and their opponent hold.
     *
     * @return value of a board.
     */
    public int calculateValueOfBoardBasedOnPiecesHeld() {
        int heuristic = 0;
        for (int y = 0; y < board.getBoard().length; y++) {
            for (int x = 0; x < board.getBoard()[0].length; x++) {
                int value = 0;
                if (board.getBoard()[y][x] != Player.NONE) {
                    value = pieceValues[y][x] + around(x, y);
                }
                if (board.getBoard()[y][x] == Player.opposing(board.getPlayerInTurn())) {
                    value = -value;
                }

                heuristic += value;
            }
        }

        return heuristic;
    }

    /**
     * Gives a piece points based on how many tiles it is surrounded by.
     *
     * @param x
     * @param y
     * @return the calculated value.
     */
    public int around(int x, int y) {
        int value = 0;

        //A neighboring square is empty or off the board, give one point
        value += board.isEmpty(x, y - 1) ? 1 : 0;
        value += board.isEmpty(x + 1, y - 1) ? 1 : 0;
        value += board.isEmpty(x + 1, y) ? 1 : 0;
        value += board.isEmpty(x + 1, y + 1) ? 1 : 0;
        value += board.isEmpty(x, y + 1) ? 1 : 0;
        value += board.isEmpty(x - 1, y + 1) ? 1 : 0;
        value += board.isEmpty(x - 1, y) ? 1 : 0;
        value += board.isEmpty(x - 1, y - 1) ? 1 : 0;

        //Completely surrounded, that's great, give points.
        if (value == 0) {
            value = 2;
        }

        return value;
    }

}
