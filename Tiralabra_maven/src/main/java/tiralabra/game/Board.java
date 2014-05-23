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
     * Integer matrix of the board. 0 == Empty, 1 == WHITE, 2 == BLACK.
     */
    private int[][] board;
    public static int WHITE = 1;
    public static int BLACK = 2;

    public Board() {
        this.board = new int[8][8];

        board[3][3] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
        board[4][4] = WHITE;
    }

    /**
     * Checks whether the piece can be placed in the given position, and places
     * it there, flipping all applicable pieces.
     *
     * @param x
     * @param y
     * @param team
     * @return true, if the piece was successfully placed on the board, false if
     * the move was illegal
     */
    public boolean put(int x, int y, int team) {
        if (!checkPointForLegal(x, y, team)) {
            return false;
        }
        board[y][x] = team;
        flipDiagonally(x, y, team);
        flipHorizontally(x, y, team);
        flipVertically(x, y, team);
        return true;
    }

    public boolean put(long point, int team) {
        return put(x(point), y(point), team);
    }

    /**
     * Finds and lists all the legal moves on the board.
     *
     * @param team
     * @return
     */
    public ArrayList<Long> findLegalMoves(int team) {
        ArrayList<Long> legalMoves = new ArrayList<>();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (checkPointForLegal(x, y, team)) {
                    legalMoves.add(point(x, y));
                }
            }
        }

        return legalMoves;
    }

    private boolean checkPointForLegal(int x, int y, int team) {
        return board[y][x] == 0 && (checkDiagonalLegal(x, y, team) || checkHorizontalLegal(x, y, team) || checkVerticalLegal(x, y, team));
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
     * Checks whether the given coordinates contain an opponents piece.
     *
     * @param x
     * @param y
     * @param team
     * @return true if the piece is the opponent's, false if there is no piece
     * or the piece is mine
     */
    private boolean checkForOpponent(int x, int y, int team) {
        if (x < 0 || x > board[0].length - 1 || y < 0 || y > board.length - 1) {
            return false;
        }
        return board[y][x] != 0 && board[y][x] != team;
    }

    /**
     * Checks whether the given coordinates contain my piece, eg. piece of the
     * given team,
     *
     * @param x
     * @param y
     * @param team
     * @return true if the piece is mine, false if there is no piece or the
     * piece is the opponent's
     */
    private boolean checkForMyPiece(int x, int y, int team) {
        if (x < 0 || x > board[0].length - 1 || y < 0 || y > board.length - 1) {
            return false;
        }
        return board[y][x] != 0 && board[y][x] == team;
    }

    /**
     * Checks whether the move is legal when considering only pieces aligned
     * diagonally with this piece.
     *
     * @param x
     * @param y
     * @param team
     * @return true if the move is legal, false if the move can't be determined
     * to be legal when considering pieces aligned diagonally with the given
     * coordinates
     */
    private boolean checkDiagonalLegal(int x, int y, int team) {
        return (checkDirection(x, y, 1, 1, team)
                || checkDirection(x, y, 1, -1, team)
                || checkDirection(x, y, -1, 1, team)
                || checkDirection(x, y, -1, -1, team));
    }

    /**
     * Checks whether the move is legal when considering only pieces in the same
     * column.
     *
     * @param x
     * @param y
     * @param team
     * @return true if the move is legal, false if the move can't be determined
     * to be legal when considering this column.
     */
    private boolean checkHorizontalLegal(int x, int y, int team) {
        return (checkDirection(x, y, 0, 1, team)
                || checkDirection(x, y, 0, -1, team));
    }

    /**
     * Checks whether the move is legal when considering only the pieces in the
     * same row
     *
     * @param x
     * @param y
     * @param team
     * @return true if the move is legal, false if the move can't be determined
     * to be legal when considering this row.
     */
    private boolean checkVerticalLegal(int x, int y, int team) {
        return (checkDirection(x, y, 1, 0, team)
                || checkDirection(x, y, -1, 0, team));
    }

    /**
     * Checks whether the given direction would make the coordinates a legal
     * move.
     *
     * @param x
     * @param y
     * @param dx
     * @param dy
     * @param team
     * @return true if the move is legal, false if the move can't be determined
     * to be legal when considering only this direction from the given piece
     */
    private boolean checkDirection(int x, int y, int dx, int dy, int team) {
        int inBetween = 0;
        while (checkForOpponent(x + dx, y + dy, team)) {
            inBetween++;
            dx = dx > 0 ? dx + 1 : dx < 0 ? dx - 1 : dx;
            dy = dy > 0 ? dy + 1 : dy < 0 ? dy - 1 : dy;
        }
        return inBetween > 0 && checkForMyPiece(x + dx, y + dy, team);
    }

    /**
     * Flips all applicable pieces between the given coordinates and the pieces
     * of this player that are aligned diagonally.
     *
     * @param x
     * @param y
     * @param team
     */
    private void flipDiagonally(int x, int y, int team) {
        flipDirection(x, y, 1, 1, team);
        flipDirection(x, y, 1, -1, team);
        flipDirection(x, y, -1, 1, team);
        flipDirection(x, y, -1, -1, team);
    }

    /**
     * Flips all applicable pieces between the given coordinates and the pieces
     * of this player that are in the same column.
     *
     * @param x
     * @param y
     * @param team
     */
    private void flipHorizontally(int x, int y, int team) {
        flipDirection(x, y, 0, 1, team);
        flipDirection(x, y, 0, -1, team);
    }

    /**
     * Flips all applicable pieces between the given coordinates and the pieces
     * of this player that are in the same row.
     *
     * @param x
     * @param y
     * @param team
     */
    private void flipVertically(int x, int y, int team) {
        flipDirection(x, y, 1, 0, team);
        flipDirection(x, y, -1, 0, team);
    }

    /**
     * Flips all the pieces in a given direction from the given coordinates,
     * when applicable
     *
     * @param x
     * @param y
     * @param dx
     * @param dy
     * @param team
     */
    private void flipDirection(int x, int y, int dx, int dy, int team) {
        if (!checkDirection(x, y, dx, dy, team)) {
            return;
        }
        while (checkForOpponent(x + dx, y + dy, team)) {
            flip(x + dx, y + dy);
            dx = dx > 0 ? dx + 1 : dx < 0 ? dx - 1 : dx;
            dy = dy > 0 ? dy + 1 : dy < 0 ? dy - 1 : dy;
        }
    }

    /**
     * Flips the piece to the opposing color in the given coordinates.
     *
     * @param x
     * @param y
     */
    private void flip(int x, int y) {
        board[y][x] = board[y][x] == WHITE ? BLACK : WHITE;
    }

    /**
     * Sets the board 2d array to the given 2d array. Used for testing purposes.
     *
     * @param board
     */
    public void setBoard(int[][] board) {
        this.board = board;
    }

    /**
     * Gets the board 2d array.
     *
     * @return the board 2d array
     */
    public int[][] getBoard() {
        return board;
    }

    public int getNumberOfPieces(int team) {
        int count = 0;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (board[y][x] == team) {
                    count++;
                }
            }
        }
        return count;
    }
}
