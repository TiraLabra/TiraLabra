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

/**
 * The AI which calculates which move the AI Should take in any given situation.
 *
 * @author atte
 */
public class AI {

//    /**
//     * Comparator that compares moves based on values given in a HashMap.
//     */
//    public static class MoveSorter implements Comparator<Long> {
//
//        private final HashMap<Long, Integer> values;
//
//        public MoveSorter(int team, HashMap<Long, Integer> pieces) {
//            this.values = pieces;
//        }
//
//        @Override
//        public int compare(Long o1, Long o2) {
//            return values.get(o2) - values.get(o1);
//        }
//
//    }
//
//    /**
//     * The board used for this ai.
//     */
//    private final Board board;
//    /**
//     * A table that is used to store the hypothetical value of holding any one
//     * point.
//     */
//    private static final int[][] pieceValues = new int[][]{{65, -3, 6, 4, 4, 6, -3, 65},
//    {-3, -29, 1, 1, 1, 3, -29, -3},
//    {6, 3, 5, 3, 3, 5, 3, 6},
//    {4, 1, 3, 1, 1, 3, 1, 4},
//    {4, 1, 3, 1, 1, 3, 1, 4},
//    {6, 3, 5, 3, 3, 5, 3, 6},
//    {-3, -29, 3, 1, 1, 3, -29, -3},
//    {65, -3, 6, 4, 4, 6, -3, 65}};
//
//    public AI(Board board) {
//        this.board = board;
//    }
//
////    /**
////     * The way a beginner would play. Always choose the move which brings the
////     * most new pieces.
////     *
////     * @param team
////     * @return the move which brings the most new pieces, stores a long
////     * variable.
////     */
////    public long greedyBeginnerMove(int team) {
////        ArrayList<Long> moves = board.findLegalMoves(team);
////        HashMap<Long, Integer> piecesOwned = new HashMap<>();
////
////        for (long move : moves) {
////            int nmbOfFlips = board.place(move, team);
////
////            piecesOwned.put(move, nmbOfFlips);
////
////            board.undo();
////        }
////        Collections.sort(moves, new MoveSorter(team, piecesOwned));
////
////        return moves.get(0);
////    }
//    /**
//     * The starting node where the
//     *
//     * @param player
//     * @return
//     */
//    public long search(int player) {
//        ArrayList<Long> moves = board.findLegalMoves(player);
//
//        int alpha = Integer.MIN_VALUE;
//        long best = moves.get(0);
//        long startTime = System.currentTimeMillis();
//        for (Long move : moves) {
//
//            int compare = search(move, 8, alpha, Integer.MAX_VALUE, Board.opposingPlayer(player), false);
//
//            if (compare > alpha) {
//                best = move;
//                alpha = compare;
//            }
//        }
//        long time = System.currentTimeMillis() - startTime;
//        System.out.println("time: " + time);
//
//        return best;
//    }
//
//    /**
//     * Min-max-algorithm with alpha-beta-pruning. First the given move of this
//     * node is placed on the board, which is undone when we return from this
//     * node. Then, using alpha-beta-pruning, we prune the obviously worse nodes.
//     * The value of a given board is calculated with the boardValue -method.
//     *
//     * @param move
//     * @param depth
//     * @param alpha
//     * @param beta
//     * @param player
//     * @param max
//     * @return the alpha or beta value of this node.
//     */
//    public int search(long move, int depth, int alpha, int beta, int player, boolean max) {
//        board.place(move, Board.opposingPlayer(player));
//
//        //Reached maximum depth or end of the game, return value of the board.
//        if (depth == 0 || board.isFull()) {
//            int heuristic = boardValue(player);
//            board.undo();
////            System.out.println("heuristic: " + heuristic);
//            return heuristic;
//        }
//
//        ArrayList<Long> moves = board.findLegalMoves(player);
//
//        //No moves found, skip this turn. If the other player has no moves either, return the tile difference.
//        if (moves.isEmpty()) {
//            if (board.findLegalMoves(Board.opposingPlayer(player)).isEmpty()) {
//                int tileDifference = board.getBlackPieces() - board.getWhitePieces();
//                if (player == Board.WHITE) tileDifference = -tileDifference;
//                board.undo();
//                return tileDifference;
//            }
//            
//            return search(move, depth - 1, alpha, beta, Board.opposingPlayer(player), !max);
//        }
//
//        if (max) {
//            for (Long child : moves) {
//                alpha = Math.max(alpha, search(child, depth - 1, alpha, beta, Board.opposingPlayer(player), !max));
//
//                if (beta <= alpha) {
//                    break;
//                }
//            }
//            board.undo();
//            return alpha;
//        } else {
//            for (Long child : moves) {
//                beta = Math.min(beta, search(child, depth - 1, alpha, beta, Board.opposingPlayer(player), !max));
//
//                if (beta <= alpha) {
//                    break;
//                }
//            }
//            board.undo();
//            return beta;
//        }
//    }
//
//    /**
//     * Returns all moves for the current board in order by most pieces flipped
//     * to least flipped.
//     *
//     * @param player
//     * @return moves
//     */
//    public ArrayList<Long> getAllPossibleMovesInOrder(int player) {
//        ArrayList<Long> moves = new ArrayList<>();
//        HashMap<Long, Integer> piecesOwned = new HashMap<>();
//
//        for (int y = 0; y < board.getBoard().length; y++) {
//            for (int x = 0; x < board.getBoard()[0].length; x++) {
//                int flipped = board.place(x, y, player);
//
//                if (flipped > 0) {
//                    long move = Board.point(x, y);
//                    moves.add(move);
//                    piecesOwned.put(move, flipped);
//                    board.undo();
//                }
//            }
//        }
//
//        Collections.sort(moves, new MoveSorter(player, piecesOwned));
//        return moves;
//    }
//
//    /**
//     * Return the value of a board based on the difference between tiles and the
//     * presumed value of held pieces.
//     *
//     * @param player
//     * @return value of board.
//     */
//    public int boardValue(int player) {
//        int pieceDifference = board.getWhitePieces() - board.getBlackPieces();
//        if (player == Board.BLACK) {
//            pieceDifference = -pieceDifference;
//        }
//
//        return pieceDifference + calculateValueOfBoardBasedOnPiecesHeld(player);
//    }
//
//    /**
//     * Calculates value of the current board based on the pieces the given team
//     * and their opponent hold.
//     *
//     * @param player
//     * @return value of a board.
//     */
//    public int calculateValueOfBoardBasedOnPiecesHeld(int player) {
//        int heuristic = 0;
//        for (int y = 0; y < board.getBoard().length; y++) {
//            for (int x = 0; x < board.getBoard()[0].length; x++) {
//                int value = 0;
//                if (board.getBoard()[y][x] == player) {
//                    value = pieceValues[y][x]
//                            + around(x, y, player);
//                } else if (board.getBoard()[y][x] == Board.opposingPlayer(player)) {
//                    value = -pieceValues[y][x]
//                            - around(x, y, player);
//                }
//
//                heuristic += value;
//            }
//        }
//
//        return heuristic;
//    }
//
//    /**
//     * Gives a piece points based on how many tiles it is surrounded by.
//     *
//     * @param x
//     * @param y
//     * @return the calculated value.
//     */
//    public int around(int x, int y, int player) {
//        int value = 0;
//
//        //A neighboring square is empty or off the board, give one point
//        value += board.isEmpty(x, y - 1) ? 1 : 0;
//        value += board.isEmpty(x + 1, y - 1) ? 1 : 0;
//        value += board.isEmpty(x + 1, y) ? 1 : 0;
//        value += board.isEmpty(x + 1, y + 1) ? 1 : 0;
//        value += board.isEmpty(x, y + 1) ? 1 : 0;
//        value += board.isEmpty(x - 1, y + 1) ? 1 : 0;
//        value += board.isEmpty(x - 1, y) ? 1 : 0;
//        value += board.isEmpty(x - 1, y - 1) ? 1 : 0;
//
//        //Completely surrounded, that's great, give points.
//        if (value == 0) {
//            value = 2;
//        }
//
//        return value;
//    }

}
