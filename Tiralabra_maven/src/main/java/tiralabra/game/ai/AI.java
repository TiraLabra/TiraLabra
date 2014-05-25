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
import tiralabra.utilities.Utilities;
import tiralabra.game.Board;

/**
 *
 * @author atte
 */
public class AI {

    /**
     * Comparator that compares moves based on how many pieces they flip.
     */
    public static class MoveSorter implements Comparator<Long> {

        private final HashMap<Long, Integer> values;

        public MoveSorter(int team, HashMap<Long, Integer> pieces) {
            this.values = pieces;
        }

        @Override
        public int compare(Long o1, Long o2) {
            return values.get(o2) - values.get(o1);
        }

    }

    private final Board board;
    /**
     * A table that is used to store the hypothetical value of holding any one
     * place.
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

    /**
     * The way a beginner would play. Always choose the move which brings the
     * most new pieces.
     *
     * @param team
     * @return the move which brings the most new pieces, stores a long
     * variable.
     */
    public long greedyBeginnerMove(int team) {
        ArrayList<Long> moves = board.findLegalMoves(team);
        HashMap<Long, Integer> piecesOwned = new HashMap<>();

        for (long move : moves) {
            int nmbOfFlips = board.place(move, team);

            piecesOwned.put(move, nmbOfFlips);

            board.undo();
        }
        Collections.sort(moves, new MoveSorter(team, piecesOwned));

        return moves.get(0);
    }

    public long search(int team) {
        ArrayList<Long> moves = getAllPossibleMovesInOrder(team);

        int alpha = Integer.MIN_VALUE;
        long best = moves.get(0);
        for (Long move : moves) {
            int compare = search(move, 8, alpha, Integer.MAX_VALUE, Board.getOpposingTeam(team), false);

            if (compare > alpha) {
                best = move;
                alpha = compare;
            }
        }

        return best;
    }

    public int search(long move, int depth, int alpha, int beta, int team, boolean max) {
        board.place(move, Board.getOpposingTeam(team));

        if (depth == 0 || board.isFull()) {
            int heuristic = calculateHeuristic(team);
            board.undo();
            return heuristic;
        }

        ArrayList<Long> moves = getAllPossibleMovesInOrder(team);

        if (moves.isEmpty()) {
            return search(move, depth - 1, alpha, beta, Board.getOpposingTeam(team), !max);
        }

        if (max) {
            for (Long child : moves) {
                alpha = Math.max(alpha, search(child, depth - 1, alpha, beta, Board.getOpposingTeam(team), false));

                if (beta <= alpha) {
                    break;
                }
            }
            board.undo();
            return alpha;
        } else {
            for (Long child : moves) {
                beta = Math.min(beta, search(child, depth - 1, alpha, beta, Board.getOpposingTeam(team), true));

                if (beta <= alpha) {
                    break;
                }
            }
            board.undo();
            return beta;
        }
    }

    public ArrayList<Long> getAllPossibleMovesInOrder(int team) {
        ArrayList<Long> moves = new ArrayList<>();
        HashMap<Long, Integer> piecesOwned = new HashMap<>();

        for (int y = 0; y < board.getBoard().length; y++) {
            for (int x = 0; x < board.getBoard()[0].length; x++) {
                int flipped = board.put(x, y, team);
                
                if (flipped > 0) {
                    long move = Board.point(x, y);
                    moves.add(move);
                    piecesOwned.put(move, flipped);
                    board.undo();
                }
            }
        }
        
        
        Collections.sort(moves, new MoveSorter(team, piecesOwned));
        return moves;
    }

    public int calculateHeuristic(int team) {
        int heuristic = 0;
        for (int y = 0; y < board.getBoard().length; y++) {
            for (int x = 0; x < board.getBoard()[0].length; x++) {
                int value = 0;
                if (board.getBoard()[y][x] == team) {
                    value = pieceValues[y][x];
                } else if (board.getBoard()[y][x] == Board.getOpposingTeam(team)) {
                    value = -pieceValues[y][x];
                }
                heuristic += value;
            }
        }

        return heuristic;
    }
}
