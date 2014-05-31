/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game;

import java.util.ArrayList;

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
     * Holds information of made moves for undoing.
     */
    public class FlipStack {

        /**
         * Array which holds the stack. 1344 is the worst case in which each
         * move flips 20 pieces (impossible)
         */
        private Flip[] stack;
        private int index;

        public FlipStack() {
            stack = new Flip[1344];
            index = 0;
        }

        public void push(int x, int y, Player player) {
            stack[index] = new Flip(x, y, player);
            index++;
        }

        public void push(int movesToClear, Player player) {
            stack[index] = new Flip(movesToClear, player);
            index++;
        }

        public Flip peek() {
            if (index == 0) {
                throw new IndexOutOfBoundsException("Peeked an empty OperationStack.");
            }

            return stack[index - 1];
        }

        public boolean isEmpty() {
            return index == 0;
        }

        public Flip pop() {
            if (index == 0) {
                throw new IndexOutOfBoundsException("Popped an empty OperationStack.");
            }
            index--;
            System.out.println(stack[index].x + ", " + stack[index].y);
            return stack[index];
        }
    }

    /**
     * Player 2d-array of the board.
     */
    private Player[][] board;
    /**
     * ArrayList which holds all the operations done during this game.
     */
    private FlipStack flipStack;
    private Player playerInTurn;

    public int width() {
        return board[0].length;
    }

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

        flipStack = new FlipStack();

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

    public Player getTile(int x, int y) {
        if (!isValidTile(x, y)) {
            return Player.NONE;
        }
        return board[y][x];
    }

    /**
     * Checks whether the piece can be placed in the given position, and places
     * it there, flipping all applicable pieces.
     *
     * @param x
     * @param y
     * @param player
     * @param realMove whether the move should be done, or just tried
     * @return number of flipped pieces
     */
    public int place(int x, int y, Player player, boolean realMove) {
        if (!isValidTile(x, y) || getTile(x, y) != Player.NONE) {
            return 0;
        }

        setTile(x, y, player);

        int nmbOfFlips = 0;
        nmbOfFlips += flipDirection(x, y, 1, 0, player, realMove);
        nmbOfFlips += flipDirection(x, y, -1, 0, player, realMove);
        nmbOfFlips += flipDirection(x, y, 0, 1, player, realMove);
        nmbOfFlips += flipDirection(x, y, 0, -1, player, realMove);
        nmbOfFlips += flipDirection(x, y, 1, 1, player, realMove);
        nmbOfFlips += flipDirection(x, y, 1, -1, player, realMove);
        nmbOfFlips += flipDirection(x, y, -1, 1, player, realMove);
        nmbOfFlips += flipDirection(x, y, -1, -1, player, realMove);
        if (realMove && (nmbOfFlips > 0)) {
            flipStack.push(nmbOfFlips + 1, player);
        }

        return nmbOfFlips;
    }

    public int place(long point, Player player, boolean realMove) {
        return place(x(point), y(point), player, realMove);
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
     * @param realMove
     * @return number of flipped pieces.
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
            for (int i = 0; i <= nmbOfFlips; i++) {
                flip(x + i * dx, y + i * dy, player);
            }
        }

        return nmbOfFlips;
    }

    /**
     * Flips the piece to the opposing color in the given coordinates + adds the
     * flip to the stack of operations done.
     *
     * @param x
     * @param y
     * @param player
     */
    private void flip(int x, int y, Player player) {
        flipStack.push(x, y, Player.opposing(player));
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
     * Undoes the last move and subtracts one from the moveNumber.
     */
    public void undo() {
        if (flipStack.isEmpty()) {
            return;
        }
        
        int flipsToClear = flipStack.pop().flipsToClear;
        System.out.println(flipsToClear);
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
     *
     * @return
     */
    public Player[][] getBoard() {
        return board;
    }

}
