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
import tiralabra.utilities.ArrayListSorter;

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

        /**
         * X coordinate.
         */
        public int x;
        /**
         * Y coordinate.
         */
        public int y;
        /**
         * Value that is used to sort moves.
         */
        public int value;
        /**
         * Move is a pass.
         */
        public boolean pass;

        /**
         *
         * @param x
         * @param y
         * @param value
         */
        public Move(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.pass = false;
        }

        /**
         *
         * @param pass
         */
        public Move(boolean pass) {
            this.pass = true;
        }

        @Override
        public int compareTo(Move m) {
            return m.value - this.value;
        }
    }

    /**
     * Enumerable for strategy.
     */
    public static enum Strategy {

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
    public static final int[][] PIECEVALUES = new int[][]{{65, -3, 6, 4, 4, 6, -3, 65},
    {-3, -29, 1, 1, 1, 3, -29, -3},
    {6, 3, 5, 3, 3, 5, 3, 6},
    {4, 1, 3, 1, 1, 3, 1, 4},
    {4, 1, 3, 1, 1, 3, 1, 4},
    {6, 3, 5, 3, 3, 5, 3, 6},
    {-3, -29, 3, 1, 1, 3, -29, -3},
    {65, -3, 6, 4, 4, 6, -3, 65}};
    /**
     * Search to what depth.
     */
    private static final int SEARCHDEPTH = 8;

    /**
     *
     * @param board
     */
    public AI(Board board) {
        this.board = board;
    }

    /**
     * Searches for the most optimal move by using alpha-beta-pruning. If the
     * it's the first 2 turns, choose a random move to avoid having the same
     * game play out every time.
     *
     * @return move as a long value
     */
    public long move() {
        if (board.getNumberOfPieces() < 8) {
            return selectRandomMove(board.playerInTurn());
        }

        Move move = new Move(-1, -1, 0);
        search(move, SEARCHDEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, true, determineStrategy(), board.playerInTurn());

        return Board.point(move.x, move.y);
    }

    /**
     * Randomly chooses a move from all the legal moves and returns it.
     *
     * @param turn
     * @return coordinates as long. If the move was a pass return -1 (should not
     * happen normally as this method is only called for the first 2 turns, so
     * this is for testing)
     */
    public long selectRandomMove(Player turn) {
        ArrayList<Move> moves = getLegalMovesInOrder(Strategy.MAXIMIZEPIECES, false, turn);
        Move move = moves.get(new Random().nextInt(moves.size()));
        if (move.pass) {
            return -1;
        }

        return Board.point(move.x, move.y);
    }

    /**
     * Selects which strategy should be used in alpha-beta-pruning based on how
     * far into the game we are.
     *
     * @return strategy for the move
     */
    private Strategy determineStrategy() {
        int movesLeft = 64 - board.getNumberOfPieces();

        if (movesLeft < 12) {
            return Strategy.MAXIMIZEPIECES;
        } else {
            return Strategy.MAXIMIZEVALUE;
        }
    }

    /**
     * Min-max-algorithm with alpha-beta-pruning. Firstly, we find all legal
     * moves in the current position. If the recursion level is zero, the moves
     * are sorted by performing a 2-depth search. Else, they're sorted by the
     * number of pieces they flip, to prevent stack overflow. Then we evaluate
     * each of them, stopping if the branch is plainly worse than a previously
     * evaluated branch. The ideal move is stored in the move - object, which is
     * given as a parameter.
     *
     * @param move keeps track of which is the best move by current evaluation.
     * the 'real' return value in a way as this object holds the optimal move
     * for the original caller.
     * @param depth
     * @param alpha
     * @param beta
     * @param max maximizing player or minimizing player
     * @param strategy
     * @param turn
     * @return the alpha or beta value of this node.
     */
    public int search(Move move, int depth, int alpha, int beta, boolean max, Strategy strategy, Player turn) {
        //Reached maximum depth or end of the game, return value of the board.
        if (depth == 0 || board.gameOver()) {
            return boardValue(strategy, turn);
        }

        ArrayList<Move> moves = getLegalMovesInOrder(strategy, (depth == SEARCHDEPTH), turn);

        return prune(moves, depth, alpha, beta, max, strategy, move, turn);
    }

    /**
     * Go through each of the children of this node, placing them on the board,
     * and maximize alpha or minimize beta accordingly. Stop evaluation if this
     * branch has clearly worse values than a previously evaluated branch.
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
    private int prune(ArrayList<Move> moves, int depth, int alpha, int beta, boolean max, Strategy strategy, Move move, Player turn) {
        for (Move node : moves) {
            placeMove(node);

            if (max) {
                alpha = maximizingPlayer(depth, alpha, beta, max, strategy, move, node, turn);
            } else {
                beta = minimizingPlayer(depth, alpha, beta, max, strategy, move, node, turn);
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
     * Places the given move on the board, or passes, if move is specified as a
     * pass.
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
    private int minimizingPlayer(int depth, int alpha, int beta, boolean max, Strategy strategy, Move move, Move node, Player turn) {
        int newBeta = search(new Move(-1, -1, 0), depth - 1, alpha, beta, !max, strategy, turn);

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
    private int maximizingPlayer(int depth, int alpha, int beta, boolean max, Strategy strategy, Move move, Move node, Player turn) {
        int newAlpha = search(new Move(-1, -1, 0), depth - 1, alpha, beta, !max, strategy, turn);

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
     * @param search Whether moves should be ordered with a 2-depth search or
     * not just ordered by pieces flipped. Prevents stack overflow.
     * @param turn
     * @return moves
     */
    public ArrayList<Move> getLegalMovesInOrder(Strategy strategy, boolean search, Player turn) {
        ArrayList<Move> moves = new ArrayList<>();

        addAllMoves(search, strategy, moves, turn);

        //No moves found, add a pass.
        if (moves.isEmpty()) {
            moves.add(new Move(true));
        }

        ArrayListSorter.sort(moves);

        return moves;
    }

    /**
     * Tries placing a piece at each coordinate in the board, also adding them
     * to the list of moves.
     *
     * @param search
     * @param strategy
     * @param moves
     * @param turn
     */
    private void addAllMoves(boolean search, Strategy strategy, ArrayList<Move> moves, Player turn) {
        for (int y = 0; y < board.height(); y++) {
            for (int x = 0; x < board.width(); x++) {
                int flips = board.tryTile(x, y);
                addMove(search, x, y, strategy, flips, moves, turn);
            }
        }
    }

    /**
     * Adds the move to the list of legal moves if the move flips 1 or more
     * pieces.
     *
     * @param search
     * @param x
     * @param y
     * @param strategy
     * @param flips
     * @param moves
     */
    private void addMove(boolean search, int x, int y, Strategy strategy, int flips, ArrayList<Move> moves, Player turn) {
        if (flips > 0) {
            int value = determineValueOfPlacingMove(search, x, y, strategy, flips, turn);
            Move move = new Move(x, y, value);
            moves.add(move);
        }
    }

    /**
     * Determines the value of a given move by using a 2-depth search to find
     * the best move by looking ahead 2 turns if search is true. Else, just
     * return number of flipped pieces as the value.
     *
     * @param search if false, just return the number of flips as a value.
     * @param x
     * @param y
     * @param strategy
     * @param flips
     * @return
     */
    private int determineValueOfPlacingMove(boolean search, int x, int y, Strategy strategy, int flips, Player turn) {
        int value = 0;

        if (search) {
            board.placeTile(x, y);
            value = search(new Move(0, 0, 0), 2, Integer.MIN_VALUE, Integer.MAX_VALUE, true, strategy, turn);
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
     * @param turn
     * @return value of board.
     */
    public int boardValue(Strategy strategy, Player turn) {
        int pieceDifference = board.blackPieces() - board.whitePieces();
        if (turn == Player.WHITE) {
            pieceDifference = -pieceDifference;
        }

        switch (strategy) {
            case MAXIMIZEVALUE:
                return pieceDifference + calculateValueOfBoardBasedOnPiecesHeld(turn);
            default:
                return pieceDifference;
        }
    }

    /**
     * Calculates value of the current board based on the pieces the given
     * player and their opponent hold.
     *
     * @param turn
     * @return value of a board.
     */
    public int calculateValueOfBoardBasedOnPiecesHeld(Player turn) {
        int heuristic = 0;
        for (int y = 0; y < board.height(); y++) {
            for (int x = 0; x < board.width(); x++) {
                int value = 0;

                if (board.getTile(x, y) != Player.NONE) {
                    value = PIECEVALUES[y][x] + around(x, y);
                }
                if (board.getTile(x, y) == Player.opposing(turn)) {
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
