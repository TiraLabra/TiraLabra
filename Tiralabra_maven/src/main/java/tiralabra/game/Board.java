/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game;

import java.math.BigInteger;
import java.util.Collections;
import tiralabra.utilities.ArrayList;
import tiralabra.utilities.Stack;
import tiralabra.utilities.ZobristHash;

/**
 * The game board. Responsible only for holding the pieces and placing them on
 * the board.
 *
 * @author atte
 */
public class Board {

    /**
     * Holds information of each made move, to be stored in the stack.
     */
    public class Flip {

        /**
         * X coordinate of the operation.
         */
        public int x;
        /**
         * Y coordinate of the operation.
         */
        public int y;
        /**
         * The color prior to the operation.
         */
        public Player player;

        /**
         *
         * @param x
         * @param y
         * @param player
         */
        public Flip(int x, int y, Player player) {
            this.x = x;
            this.y = y;
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
     *
     */
    private Stack<Integer> flipsPerMoveStack;
    /**
     * The player whose turn is next.
     */
    private Player playerInTurn;
    /**
     * Capable of hashing the board to an unique 64-bit value.
     */
    private ZobristHash hasher;
    /**
     * Unique hash of the current board.
     */
    private BigInteger hash;

    /**
     * Width of the board.
     *
     * @return int
     */
    public final int width() {
        return board[0].length;
    }

    /**
     * Height of the board.
     *
     * @return int
     */
    public final int height() {
        return board.length;
    }

    public Board() {
        initalizeBoard();
    }

    private void initalizeBoard() {
        this.board = new Player[8][8];
        
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                board[y][x] = Player.NONE;
            }
        }

        board[3][3] = Player.BLACK;
        board[3][4] = Player.WHITE;
        board[4][3] = Player.WHITE;
        board[4][4] = Player.BLACK;

        flipStack = new Stack();
        flipsPerMoveStack = new Stack();

        playerInTurn = Player.BLACK;

        hasher = new ZobristHash(8, 8);
        hash = hasher.hash(board);
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
        playerInTurn = Player.opposing(playerInTurn);
    }

    /**
     * The player currently in turn.
     *
     * @return
     */
    public Player playerInTurn() {
        return playerInTurn;
    }

    /**
     * Sets the tile to the desired color.
     *
     * @param x
     * @param y
     * @param player
     * @param undoable whether the change is added to the stack of operations
     * for undoing.
     */
    public void setTile(int x, int y, Player player, boolean undoable) {
        if (getTile(x, y) == player) {
            return;
        }

        if (undoable) {
            flipStack.push(new Flip(x, y, getTile(x, y)));
        }

        updateHashCoordinates(x, y, player);

        board[y][x] = player;
    }

    /**
     * Updates changes to the hash when the color of a tile changes.
     *
     * @param x
     * @param y
     * @param player
     */
    private void updateHashCoordinates(int x, int y, Player player) {
        if (getTile(x, y) != Player.NONE) {
            updateHash(x, y, getTile(x, y));
        }

        if (player != Player.NONE) {
            updateHash(x, y, player);
        }
    }

    /**
     * Updates the hash of the board by either XORing the wanted piece into the
     * hash or XORing an existing piece out.
     *
     * @param x
     * @param y
     * @param player
     */
    private void updateHash(int x, int y, Player player) {
        hash = hasher.xorFlip(x, y, player, hash);
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

    /**
     * Whether the game is over.
     *
     * @return
     */
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
        return place(x, y, player, false) != 0;
    }

    /**
     * Tries to place a tile into the given position and returns all the pieces
     * placing it there would flip, without flipping anything.
     *
     * @param x
     * @param y
     * @return
     */
    public int tryTile(int x, int y) {
        return place(x, y, playerInTurn, false);
    }

    /**
     * Places a tile in the given position and returns all the flipped tiles.
     *
     * @param x
     * @param y
     * @return
     */
    public int placeTile(int x, int y) {
        int nmbOfFlips = place(x, y, playerInTurn, true);

        changeTurn();
        return nmbOfFlips;
    }

    /**
     * Places a tile in the given position.
     *
     * @param point
     * @return
     */
    public int placeTile(long point) {
        return placeTile(x(point), y(point));
    }

    /**
     * Changes the turn and puts a marker that a turn consisted of no flips to
     * the flipStack, for undoing.
     */
    public void pass() {
        flipsPerMoveStack.push(0);
        changeTurn();
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
    public int place(int x, int y, Player player, boolean realMove) {
        if (!isValidTile(x, y) || getTile(x, y) != Player.NONE) {
            return 0;
        }

        int nmbOfFlips = 0;

        nmbOfFlips += flipDirection(x, y, 1, 0, player, realMove);
        nmbOfFlips += flipDirection(x, y, -1, 0, player, realMove);
        nmbOfFlips += flipDirection(x, y, 0, 1, player, realMove);
        nmbOfFlips += flipDirection(x, y, 0, -1, player, realMove);
        nmbOfFlips += flipDirection(x, y, 1, 1, player, realMove);
        nmbOfFlips += flipDirection(x, y, 1, -1, player, realMove);
        nmbOfFlips += flipDirection(x, y, -1, 1, player, realMove);
        nmbOfFlips += flipDirection(x, y, -1, -1, player, realMove);

        if (realMove && nmbOfFlips != 0) {
            setTile(x, y, player, true);
            nmbOfFlips++;
            flipsPerMoveStack.push(nmbOfFlips);
        }

        return nmbOfFlips;
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
    private int flipDirection(int x, int y, int dx, int dy, Player player, boolean realMove) {
        int nmbOfFlips = 0;

        int xt = x + dx;
        int yt = y + dy;
        while (isValidTile(xt, yt)) {
            if (getTile(xt, yt) != Player.opposing(player)) {
                break;
            }

            nmbOfFlips++;

            xt += dx;
            yt += dy;
        }

        if (nmbOfFlips == 0 || !isValidTile(xt, yt) || getTile(xt, yt) != player) {
            return 0;
        }

        if (realMove) {
            flipPieces(x, y, dx, dy, player, nmbOfFlips);
        }

        return nmbOfFlips;
    }

    /**
     * Flips the pieces and updates the hash for each piece flipped.
     *
     * @param x
     * @param y
     * @param dx
     * @param dy
     * @param player
     * @param nmbrOfFlips
     * @param flips
     * @param realMove
     */
    private void flipPieces(int x, int y, int dx, int dy, Player player, int nmbrOfFlips) {
        for (int i = 0; i <= nmbrOfFlips; i++) {
            flip(x + (i * dx), y + (i * dy), player);
        }
    }

    /**
     * Flips the piece to the opposing color (or places a new piece in an empty
     * tile) in the given coordinates + adds the flip to the stack of operations
     * done.
     *
     * @param x
     * @param y
     * @param player
     */
    private void flip(int x, int y, Player player) {
        setTile(x, y, player, true);
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
     * the FlipStack and placing them on the board.
     */
    public void undo() {
        if (flipStack.isEmpty()) {
            return;
        }

        int flipsToClear = flipsPerMoveStack.pop();
        for (int i = 0; i < flipsToClear; i++) {
            Flip flip = flipStack.pop();

            setTile(flip.x, flip.y, flip.player, false);
        }

        changeTurn();
    }

    /**
     * Returns the hasher used in hashing this board.
     *
     * @return
     */
    public ZobristHash getHasher() {
        return hasher;
    }

    /**
     * Returns the current hash of the board.
     *
     * @return
     */
    public BigInteger getHash() {
        return hash;
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
     * Returns the 2d Player array of this board.
     *
     * @return
     */
    public Player[][] getBoard() {
        return board;
    }

    public void reset() {
        initalizeBoard();
    }

}
