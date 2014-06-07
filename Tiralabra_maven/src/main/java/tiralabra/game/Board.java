/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game;

import tiralabra.utilities.ArrayList;
import java.util.Collections;
import tiralabra.utilities.Stack;

/**
 * The game board. Responsible only for holding the pieces and placing them on
 * the board.
 *
 * @author atte
 */
public class Board {

    /**
     * Holds information of each made move, to be stored in the stack.
     * Alternatively, stores the number of flips for each move.
     */
    public class Flip {

        public int x;
        public int y;
        public int flipsToClear;
        public Player player;

        public Flip(int x, int y, Player player) {
            this.x = x;
            this.y = y;
            this.player = player;
        }

        public Flip(int movesToClear, Player player) {
            this.x = -1;
            this.y = -1;
            this.flipsToClear = movesToClear;
            this.player = player;
        }
    }

    /**
     * Player 2d-array of the board.
     */
    private Player[][] board;
    /**
     * Stack which holds all the operations done during this game. For undoing.
     */
    private Stack<Flip> flipStack;
    /**
     * The player whose turn is next.
     */
    private Player playerInTurn;

    /**
     * Width of the board.
     *
     * @return int
     */
    public int width() {
        return board[0].length;
    }

    /**
     * Height of the board.
     *
     * @return int
     */
    public int height() {
        return board.length;
    }

    public Board() {
        this.board = new Player[8][8];

        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                board[y][x] = Player.NONE;
            }
        }

        setTile(3, 3, Player.BLACK);
        setTile(3, 4, Player.WHITE);
        setTile(4, 3, Player.WHITE);
        setTile(4, 4, Player.BLACK);

        flipStack = new Stack();

        playerInTurn = Player.BLACK;
    }

    /**
     * Converts coordinates to a single long-type number.
     *
     * @param x
     * @param y
     * @return x and y coordinates converted to a single long variable
     */
    public static long point(int x, int y) {
        return (((long) x) << 32) | y;
    }

    /**
     * Extracts the x coordinate from a long.
     *
     * @param point
     * @return integer x-coordinate converted from a given long variable.
     */
    public static int x(long point) {
        return (int) (point >> 32);
    }

    /**
     * Extracts the y coordinate from a long.
     *
     * @param point
     * @return integer y-coordinate converted from a given long variable
     */
    public static int y(long point) {
        return (int) point;
    }

    /**
     * Returns how many pieces there are on the board.
     *
     * @return number of pieces
     */
    public int getNumberOfPieces() {
        int count = 0;
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                if (board[y][x] != Player.NONE) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Number of black pieces on the board.
     *
     * @return
     */
    public int blackPieces() {
        return countPieces(Player.BLACK);
    }

    /**
     * Number of white pieces on the board.
     *
     * @return
     */
    public int whitePieces() {
        return countPieces(Player.WHITE);
    }

    /**
     * Whether the given tile is on the board or not.
     *
     * @param x
     * @param y
     * @return
     */
    private boolean isValidTile(int x, int y) {
        return x >= 0 && x < width() && y >= 0 && y < height();
    }

    /**
     * Number of pieces the given player has on the board.
     *
     * @param player
     * @return
     */
    private int countPieces(Player player) {
        int count = 0;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (board[y][x] == player) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Changes the turn to the opposing player from the current one in turn.
     */
    private void changeTurn() {
        playerInTurn = (playerInTurn == Player.BLACK) ? Player.WHITE : Player.BLACK;
    }

    /**
     * The player currently in turn.
     *
     * @return
     */
    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    /**
     * Sets the tile to the desired color.
     *
     * @param x
     * @param y
     * @param player
     */
    public void setTile(int x, int y, Player player) {
        board[y][x] = player;
    }

    /**
     * Returns the color of the given tile.
     *
     * @param x
     * @param y
     * @return
     */
    public Player getTile(int x, int y) {
        if (!isValidTile(x, y)) {
            return Player.NONE;
        }
        return board[y][x];
    }

    public boolean gameOver() {
        return (!canMove(Player.BLACK) && !canMove(Player.WHITE));
    }

    /**
     * Making a move is possible.
     *
     * @param player
     * @return
     */
    public boolean canMove(Player player) {
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                if (canPlace(x, y, player)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Can place a piece on this tile.
     *
     * @param x
     * @param y
     * @param player
     * @return
     */
    public boolean canPlace(int x, int y, Player player) {
        return !(place(x, y, player, false).isEmpty());
    }

    /**
     * Tries to place a tile into the given position and returns all the pieces
     * placing it there would flip, without flipping anything.
     *
     * @param x
     * @param y
     * @return
     */
    public ArrayList<Long> tryTile(int x, int y) {
        ArrayList<Long> flips = place(x, y, playerInTurn, false);

        return flips;
    }

    /**
     * Places a tile in the given position and returns all the flipped tiles.
     *
     * @param x
     * @param y
     * @return
     */
    public ArrayList<Long> placeTile(int x, int y) {
        ArrayList<Long> flips = place(x, y, playerInTurn, true);

        changeTurn();
        return flips;
    }

    /**
     * Places a tile in the given position.
     *
     * @param point
     * @return
     */
    public ArrayList<Long> placeTile(long point) {
        return placeTile(x(point), y(point));
    }

    /**
     * Changes the turn and puts a marker that a turn consisted of no flips to
     * the flipStack, for undoing.
     */
    public void pass() {
        flipStack.push(new Flip(0, playerInTurn));
        playerInTurn = (playerInTurn == Player.BLACK) ? Player.WHITE : Player.BLACK;
    }

    /**
     * Checks whether the piece can be placed in the given position, and places
     * it there, flipping all applicable pieces.
     *
     * @param x
     * @param y
     * @param player
     * @param realMove whether the move should be done or just tried
     * @return number of flipped pieces
     */
    public ArrayList<Long> place(int x, int y, Player player, boolean realMove) {
        if (!isValidTile(x, y) || getTile(x, y) != Player.NONE) {
            return new ArrayList<>();
        }

        ArrayList<Long> flips = new ArrayList<>();

        flipDirection(x, y, 1, 0, player, flips, realMove);
        flipDirection(x, y, -1, 0, player, flips, realMove);
        flipDirection(x, y, 0, 1, player, flips, realMove);
        flipDirection(x, y, 0, -1, player, flips, realMove);
        flipDirection(x, y, 1, 1, player, flips, realMove);
        flipDirection(x, y, 1, -1, player, flips, realMove);
        flipDirection(x, y, -1, 1, player, flips, realMove);
        flipDirection(x, y, -1, -1, player, flips, realMove);
        if (realMove && (!flips.isEmpty())) {
            flipStack.push(new Flip(flips.size(), player));
        }

        return flips;
    }

    /**
     * Flips all the pieces in a given direction from the given coordinates,
     * when applicable.
     *
     * @param x
     * @param y
     * @param dx
     * @param dy
     * @param player
     * @param flips
     * @param realMove whether to apply the effects of the move
     */
    private void flipDirection(int x, int y, int dx, int dy, Player player, ArrayList<Long> flips, boolean realMove) {
        int nmbrOfFlips = 0;

        int xt = x + dx;
        int yt = y + dy;
        while (isValidTile(xt, yt)) {
            if (getTile(xt, yt) != Player.opposing(player)) {
                break;
            }
            nmbrOfFlips++;

            xt += dx;
            yt += dy;
        }

        if (nmbrOfFlips == 0 || !isValidTile(xt, yt) || getTile(xt, yt) != player) {
            return;
        }

        flipPieces(x, y, dx, dy, player, nmbrOfFlips, flips, realMove);
    }

    private void flipPieces(int x, int y, int dx, int dy, Player player, int nmbrOfFlips, ArrayList<Long> flips, boolean realMove) {
        for (int i = 0; i <= nmbrOfFlips; i++) {
            if (realMove) {
                flip(x + (i * dx), y + (i * dy), player);
            }
            flips.add(point(x + (i * dx), y + (i * dy)));
        }
    }

    /**
     * Flips the piece to the opposing color (or places a new piece in a empty
     * tile) in the given coordinates + adds the flip to the stack of operations
     * done.
     *
     * @param x
     * @param y
     * @param player
     */
    private void flip(int x, int y, Player player) {
        flipStack.push(new Flip(x, y, getTile(x, y)));
        setTile(x, y, player);
    }

    /**
     * Checks whether the given tile is empty or off the board.
     *
     * @param x
     * @param y
     * @return boolean
     */
    public boolean isEmpty(int x, int y) {
        if (!isValidTile(x, y) || getTile(x, y) == Player.NONE) {
            return true;
        }

        return false;
    }

    /**
     * Undoes the last move by popping all operations caused by the move from
     * the FlipStack.
     */
    public void undo() {
        if (flipStack.isEmpty()) {
            return;
        }

        int flipsToClear = flipStack.pop().flipsToClear;
        for (int i = 0; i < flipsToClear; i++) {
            Flip flip = flipStack.pop();

            int x = flip.x;
            int y = flip.y;
            Player player = flip.player;
            setTile(x, y, player);
        }

        if (playerInTurn == Player.WHITE) {
            playerInTurn = Player.BLACK;
        } else {
            playerInTurn = Player.WHITE;
        }
    }

    /**
     * Sets the board 2d array to the given 2d array. Used for testing purposes.
     *
     * @param board
     * @param inTurn
     */
    public void setBoard(Player[][] board, Player inTurn) {
        this.board = board;
        this.playerInTurn = inTurn;
    }

    /**
     * Returns the 2d Player-array of this board.
     *
     * @return
     */
    public Player[][] getBoard() {
        return board;
    }

}
