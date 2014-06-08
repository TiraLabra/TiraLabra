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
        public boolean pass;

        public Move(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.pass = false;
        }

        public Move(boolean pass) {
            this.pass = true;
        }

        @Override
        public int compareTo(Move m) {
            return m.value - this.value;
        }
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
     * Searches for the most optimal move by using alpha-beta-pruning. If the
     * game is in the beginning, choose a random move to avoid having the same
     * game play out every time.
     *
     * @return move as a long value
     */
    public long move() {
        if (board.getNumberOfPieces() < 8) {
            Move random = selectRandomMove();
            return Board.point(random.x, random.y);
        }

        Move move = new Move(-1, -1, 0);
        search(move, 8, Integer.MIN_VALUE, Integer.MAX_VALUE, true, determineStrategy(), true);

        return Board.point(move.x, move.y);
    }

    /**
     * Randomly chooses a move from all the available moves and returns it.
     *
     * @return
     */
    private Move selectRandomMove() {
        ArrayList<Move> moves = getAllLegalMovesInOrder(Strategy.MAXIMIZEPIECES, false);
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
     * @param move keeps track of which is the best move by current evaluation.
     * the 'real' return value in a way as this object holds the optimal move
     * for the original caller.
     * @param depth
     * @param alpha
     * @param beta
     * @param max maximizing player or minimizing player
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

        ArrayList<Move> moves = getAllLegalMovesInOrder(strategy, orderMoves);

        return prune(moves, depth, alpha, beta, max, strategy, move);
    }

    /**
     * Go through each of the children of this node and maximize alpha or
     * minimize beta accordingly.
     *
     * @param moves
     * @param depth
     * @param alpha
     * @param beta
     * @param max
     * @param strategy
     * @param move
     * @return
     */
    private int prune(ArrayList<Move> moves, int depth, int alpha, int beta, boolean max, Strategy strategy, Move move) {
        for (Move node : moves) {
            placeMove(node);

            if (max) {
                alpha = maximizingPlayer(depth, alpha, beta, max, strategy, move, node);
            } else {
                beta = minimizingPlayer(depth, alpha, beta, max, strategy, move, node);
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
     * Places the given move on the board, or passes, if the value is zero.
     *
     * @param node
     */
    private void placeMove(Move node) {
        if (node.pass) {
            board.pass();
        } else {
            board.placeTile(node.x, node.y);
        }
    }

    /**
     * Minimize the value of beta. Changes the values of move each time a
     * smaller beta is found.
     *
     * @param depth
     * @param alpha
     * @param beta
     * @param max
     * @param strategy
     * @param move
     * @param child
     * @return
     */
    private int minimizingPlayer(int depth, int alpha, int beta, boolean max, Strategy strategy, Move move, Move node) {
        int newBeta = search(new Move(-1, -1, 0), depth - 1, alpha, beta, !max, strategy, false);

        if (newBeta < beta) {
            beta = newBeta;
            move.x = node.x;
            move.y = node.y;
        }
        return beta;
    }

    /**
     * Maximize the value of alpha. Changes the values of move each time a
     * larger beta is found.
     *
     * @param depth
     * @param alpha
     * @param beta
     * @param max
     * @param strategy
     * @param move
     * @param node
     * @return
     */
    private int maximizingPlayer(int depth, int alpha, int beta, boolean max, Strategy strategy, Move move, Move node) {
        int newAlpha = search(new Move(-1, -1, 0), depth - 1, alpha, beta, !max, strategy, false);

        if (newAlpha > alpha) {
            alpha = newAlpha;
            move.x = node.x;
            move.y = node.y;
        }
        return alpha;
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
    public ArrayList<Move> getAllLegalMovesInOrder(Strategy strategy, boolean orderMoves) {
        ArrayList<Move> moves = new ArrayList<>();

        for (int y = 0; y < board.height(); y++) {
            for (int x = 0; x < board.width(); x++) {
                ArrayList<Long> flips = board.tryTile(x, y);
                addMove(orderMoves, x, y, strategy, flips, moves);
            }
        }

        if (moves.isEmpty()) {
            moves.add(new Move(true));
        }

        Collections.sort(moves);

        return moves;
    }

    /**
     * Adds the move to the list of legal moves if the move flips 1 or more
     * pieces.
     *
     * @param orderMoves
     * @param x
     * @param y
     * @param strategy
     * @param flips
     * @param moves
     */
    private void addMove(boolean orderMoves, int x, int y, Strategy strategy, ArrayList<Long> flips, ArrayList<Move> moves) {
        if (flips.size() > 0) {
            int value = determineValue(orderMoves, x, y, strategy, flips.size());
            Move move = new Move(x, y, value);
            moves.add(move);
        }
    }

    /**
     * Determines the value of a given move by using a 2-depth search to find
     * the best move by looking ahead 2 turns.
     *
     * @param orderMoves if false, just return the number of flips as a value.
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
        if (board.playerInTurn() == Player.WHITE) {
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
     * Calculates value of the current board based on the pieces the given
     * player and their opponent hold.
     *
     * @return value of a board.
     */
    public int calculateValueOfBoardBasedOnPiecesHeld() {
        int heuristic = 0;
        for (int y = 0; y < board.height(); y++) {
            for (int x = 0; x < board.width(); x++) {
                int value = 0;

                if (board.getTile(x, y) != Player.NONE) {
                    value = pieceValues[y][x] + around(x, y);
                }
                if (board.getTile(x, y) == Player.opposing(board.playerInTurn())) {
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
