/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game;

import java.util.ArrayList;

/**
 * The game board.
 *
 * @author atte
 */
public class Board {

    /**
     * Integer matrix of the board. 0 == Empty, 1 == WHITE, 2 == BLACK.
     */
    private int[][] board;

    public Board() {
        this.board = new int[8][8];

        board[3][3] = 1;
        board[3][4] = 2;
        board[4][3] = 2;
        board[4][4] = 1;
    }

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

    /**
     * Finds and lists all the legal moves on the board.
     *
     * @param team
     * @return
     */
    public ArrayList<Long> findLegalMoves(int team) {
        ArrayList<Long> legalMoves = new ArrayList<Long>();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (checkDiagonalLegal(x, y, team) || checkHorizontalLegal(x, y, team) || checkVerticalLegal(x, y, team)) {
                    legalMoves.add(point(x, y));
                }
            }
        }

        return legalMoves;
    }

    private boolean checkPointForLegal(int x, int y, int team) {
        return board[y][x] == 0 && checkDiagonalLegal(x, y, team) || checkHorizontalLegal(x, y, team) || checkVerticalLegal(x, y, team);
    }

    /**
     * Converts coordinates to a single long-type number.
     *
     * @param x
     * @param y
     * @return
     */
    private static long point(int x, int y) {
        return (((long) x) << 32) | y;
    }

    /**
     * Extracts the x coordinate from a point long.
     *
     * @param point
     * @return
     */
    public static int x(long point) {
        return (int) (point >> 32);
    }

    /**
     * Extracts the y coordinate from a point long.
     *
     * @param point
     * @return
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
     * @return
     */
    private boolean checkForOpponent(int x, int y, int team) {
        if (x < 0 || x > board[0].length - 1 || y < 0 || y > board.length - 1) {
            return false;
        }
        return board[y][x] != 0 && board[y][x] != team;
    }

    /**
     * Checks whether the given coordinates contain my piece.
     *
     * @param x
     * @param y
     * @param team
     * @return
     */
    private boolean checkForMyPiece(int x, int y, int team) {
        if ((x >= 0 && x < board[0].length && y >= 0 && y < board.length)) {
            return board[y][x] != 0 && board[y][x] == team;
        }
        return false;
    }

    /**
     * Checks whether the move is legal when considering only diagonal pieces.
     *
     * @param x
     * @param y
     * @param team
     * @return
     */
    private boolean checkDiagonalLegal(int x, int y, int team) {
        return (checkDirection(x, y, 1, 1, team)
                || checkDirection(x, y, 1, -1, team)
                || checkDirection(x, y, -1, 1, team)
                || checkDirection(x, y, -1, -1, team));
    }

    /**
     * Checks whether the move is legal when considering only pieces in the same
     * column
     *
     * @param x
     * @param y
     * @param team
     * @return
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
     * @return
     */
    private boolean checkVerticalLegal(int x, int y, int team) {
        return (checkDirection(x, y, 1, 0, team)
                || checkDirection(x, y, -1, 0, team));
    }

    /**
     * Checks whether the given direction would make the coordinates a legal move.
     * @param x
     * @param y
     * @param dx
     * @param dy
     * @param team
     * @return 
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

    private void flipDiagonally(int x, int y, int team) {
        flipDirection(x, y, 1, 1, team);
        flipDirection(x, y, 1, -1, team);
        flipDirection(x, y, -1, 1, team);
        flipDirection(x, y, -1, -1, team);
    }

    private void flipHorizontally(int x, int y, int team) {
        flipDirection(x, y, 0, 1, team);
        flipDirection(x, y, 0, -1, team);
    }

    private void flipVertically(int x, int y, int team) {
        flipDirection(x, y, 1, 0, team);
        flipDirection(x, y, -1, 0, team);
    }

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

    private void flip(int x, int y) {
        board[y][x] = board[y][x] == 1 ? 2 : 1;
    }

    public int[][] getBoard() {
        return board;
    }
    
    public void setBoard(int[][] board) {
        this.board = board;
    }
}
