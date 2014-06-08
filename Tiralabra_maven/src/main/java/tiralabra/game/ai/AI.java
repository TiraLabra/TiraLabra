/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game.ai;

import java.util.Random;
import tiralabra.game.Board;
import tiralabra.game.Player;
import tiralabra.utilities.ArrayList;
import tiralabra.utilities.Collections;

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
    public class Move implements Comparable<Move> {

        public int x;
        public int y;
        public int value;

        public Move(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public int compareTo(Move m) {
            return m.value - this.value;
        }
    }
    
    /**
     * Used to store information of an evaluated node in the algorithm.
     */
    public class BoardStatistic {
        private int value;
        private int depth;
    }

    public enum Strategy {
        MAXIMIZEVALUE, MAXIMIZEPIECES
    }

    /**
     * The board used for this AI.
     */
    private Board board;
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

    /**
     * 
     * @param board 
     */
    public AI(Board board) {
        this.board = board;
    }

    /**
     * Searches for the most optimal move by using alpha-beta-pruning.
     *
     * @return move as a long value
     */
    public long move() {
        if (board.getNumberOfPieces() < 8) {
            Move random = selectRandomMove();
            return Board.point(random.x, random.y);
        }

        Move move = new Move(-1, -1, 0);

        long start = System.currentTimeMillis();

        Strategy strategy = determineStrategy();
        search(move, 8, Integer.MIN_VALUE, Integer.MAX_VALUE, true, strategy, true);

        long end = System.currentTimeMillis();
        System.out.println("Aika: " + (end - start) + "ms");

        return Board.point(move.x, move.y);
    }

    /**
     * Randomly chooses a move from all the available moves and returns it.
     *
     * @return
     */
    private Move selectRandomMove() {
        ArrayList<Move> moves = getAllPossibleMovesInOrder(Strategy.MAXIMIZEPIECES, false);
        return moves.get(new Random().nextInt(moves.size()));
    }

    /**
     * Selects which strategy should be used in alpha-beta-pruning based on how
     * far into the game we are.
     *
     * @return strategy for the move
     */
    private Strategy determineStrategy() {
        int movesLeft = 64 - board.getNumberOfPieces();

        if (movesLeft < 20) {
            return Strategy.MAXIMIZEPIECES;
        } else {
            return Strategy.MAXIMIZEVALUE;
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
     * @param strategy
     * @param orderMoves Whether moves should be ordered by conducting a 2-depth
     * search. Used to avoid stack overflow.
     * @return the alpha or beta value of this node.
     */
    public int search(Move move, int depth, int alpha, int beta, boolean max, Strategy strategy, boolean orderMoves) {
        //Reached maximum depth or end of the game, return value of the board.
        if (depth == 0 || board.gameOver()) {
            return boardValue(strategy);
        }
                
        ArrayList<Move> moves = getAllPossibleMovesInOrder(strategy, orderMoves);

        for (Move child : moves) {
            if (child.value == 0) {
                board.pass();
            } else {
                board.placeTile(child.x, child.y);
            }
            
            //Maximizing player
            if (max) {
                Move newMove = new Move(-1, -1, 0);

                int newAlpha = search(newMove, depth - 1, alpha, beta, !max, strategy, false);
                if (newAlpha > alpha) {
                    alpha = newAlpha;
                    move.x = child.x;
                    move.y = child.y;
                }
            } 
            //Minimizing player
            else {
                Move newMove = new Move(-1, -1, 0);

                int newBeta = search(newMove, depth - 1, alpha, beta, !max, strategy, false);
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
     * Returns all moves for the current board in order by value after a 2-depth
     * search.
     *
     * @param strategy
     * @param orderMoves Whether moves should be ordered with a 2-depth search
     * or not just ordered by pieces flipped. Prevents stack overflow.
     * @return moves
     */
    public ArrayList<Move> getAllPossibleMovesInOrder(Strategy strategy, boolean orderMoves) {
        ArrayList<Move> moves = new ArrayList<>();

        for (int y = 0; y < board.height(); y++) {
            for (int x = 0; x < board.width(); x++) {
                ArrayList<Long> flips = board.tryTile(x, y);
                if (flips.size() > 0) {
                    int value = determineValue(orderMoves, x, y, strategy, flips.size());
                    Move move = new Move(x, y, value);
                    moves.add(move);
                }
            }
        }
        //No moves found, pass.
        if (moves.isEmpty()) {
            Move pass = new Move(-1, -1, 0);
            moves.add(pass);
        }

        Collections.sort(moves);

        return moves;
    }

    /**
     * Determines the value of a given move by using a 2-depth search to find
     * the best move by looking ahead 2 turns.
     *
     * @param orderMoves if false, just return the number of flips as a
     * value.
     * @param x
     * @param y
     * @param strategy
     * @param flips
     * @return
     */
    private int determineValue(boolean orderMoves, int x, int y, Strategy strategy, int flips) {
        int value;
        if (orderMoves) {
            board.placeTile(x, y);
            value = search(new Move(0, 0, 0), 2, Integer.MIN_VALUE, Integer.MAX_VALUE, true, strategy, false);
            board.undo();
        } else {
            value = flips;
        }
        return value;
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

        switch (strategy) {
            case MAXIMIZEVALUE:
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
