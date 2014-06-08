/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game.ai;

import java.awt.Point;
import tiralabra.utilities.ArrayList;
import tiralabra.game.Board;
import tiralabra.game.Player;

/**
 * The AI which calculates which move the AI Should take in any given situation.
 *
 * @author atte
 */
public class AI {

    /**
     * Used to store the coordinates of each evaluated move and flips caused by
     * that move.
     */
    public class Move {

        public int x;
        public int y;
        public ArrayList<Long> flips;

        public Move(int x, int y, ArrayList<Long> flips) {
            this.x = x;
            this.y = y;
            this.flips = flips;
        }
    }
    
    public enum Strategy {
        MAXIMIZEPOINTS, MAXIMIZEPIECES
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
     *
     * @return move as a long value
     */
    public long move() {
        Move move = new Move(-1, -1, null);

        long start = System.currentTimeMillis();
        
        Strategy strategy = determineStrategy();
        search(move, 8, Integer.MIN_VALUE, Integer.MAX_VALUE, true, strategy);
        
        long end = System.currentTimeMillis();
        System.out.println("Aika: " + (end - start) + "ms");

        return Board.point(move.x, move.y);
    }
    
    private Strategy determineStrategy() {
        int movesLeft = 64 - board.getNumberOfPieces();
        
        if(movesLeft < 20) {
            return Strategy.MAXIMIZEPIECES;
        } else {
            return Strategy.MAXIMIZEPOINTS;
        }
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
    public int search(Move move, int depth, int alpha, int beta, boolean max, Strategy strategy) {
        //Reached maximum depth or end of the game, return value of the board.
        if (depth == 0 || board.gameOver()) {
            return boardValue(strategy);
        }

        ArrayList<Move> moves = getAllPossibleMovesInOrder();

        for (Move child : moves) {
            if (child.flips == null) {
                board.pass();
            } else {
                board.placeTile(child.x, child.y);
            }

            if (max) {
                Move newMove = new Move(-1, -1, null);

                int newAlpha = search(newMove, depth - 1, alpha, beta, !max, strategy);
                if (newAlpha > alpha) {
                    alpha = newAlpha;
                    move.x = child.x;
                    move.y = child.y;
                }
            } else {
                Move newMove = new Move(-1, -1, null);

                int newBeta = search(newMove, depth - 1, alpha, beta, !max, strategy);
                if (newBeta < beta) {
                    beta = newBeta;
                    move.x = child.x;
                    move.y = child.y;
                }
            }
            board.undo();

            if (beta <= alpha) {
                break;
            }
        }

        if (max) {
            return alpha;
        } else {
            return beta;
        }
    }

    /**
     * Returns all moves for the current board in order by most pieces flipped
     * to least flipped.
     *
     * @return moves
     */
    public ArrayList<Move> getAllPossibleMovesInOrder() {
        ArrayList<Move> moves = new ArrayList<>();

        for (int y = 0; y < board.height(); y++) {
            for (int x = 0; x < board.width(); x++) {
                ArrayList<Long> flipsFromPlacingHere = board.tryTile(x, y);
                if (flipsFromPlacingHere.size() > 0) {
                    Move move = new Move(x, y, flipsFromPlacingHere);

                    moves.add(move);
                }
            }
        }
        //No moves found pass.
        if (moves.isEmpty()) {
            Move pass = new Move(-1, -1, null);
            moves.add(pass);
        }

        return moves;
    }

    /**
     * Return the value of a board based on the difference between tiles and the
     * determined value of held pieces.
     *
     * @param strategy
     * @return value of board.
     */
    public int boardValue(Strategy strategy) {
        int pieceDifference = board.blackPieces() - board.whitePieces();
        if (board.getPlayerInTurn() == Player.WHITE) {
            pieceDifference = -pieceDifference;
        }
        
        switch(strategy) {
            case MAXIMIZEPOINTS:
                return pieceDifference + calculateValueOfBoardBasedOnPiecesHeld();
            default:
                return pieceDifference;
        }
    }

    /**
     * Calculates value of the current board based on the pieces the given team
     * and their opponent hold.
     *
     * @return value of a board.
     */
    public int calculateValueOfBoardBasedOnPiecesHeld() {
//        long aikaAlussa = System.currentTimeMillis();

        int heuristic = 0;
        for (int y = 0; y < board.height(); y++) {
            for (int x = 0; x < board.width(); x++) {
                int value = 0;
                if (board.getTile(x, y) != Player.NONE) {
                    value = pieceValues[y][x] + around(x, y);
                }
                if (board.getTile(x, y) == Player.opposing(board.getPlayerInTurn())) {
                    value = -value;
                }

                heuristic += value;
            }
        }
//        long aikaLopussa = System.currentTimeMillis();
//        System.out.println("Aikaa kului: " + (aikaLopussa - aikaAlussa) + " ms");

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
