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
     * Integer 2d array of the board. 0 == Empty, 1 == WHITE, 2 == BLACK.
     */
    private int[][] board;
    public static int WHITE = 1;
    public static int BLACK = 2;
    /**
     * Keeps track of the number of moves made thus far and is used to identify
     * all operations caused by a move.
     */
    private int moveNumber = 0;
    /**
     * ArrayList which holds all the operations done during this game.
     */
    private ArrayList<Operation> operationList;

    public Board() {
        this.board = new int[8][8];

        board[3][3] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
        board[4][4] = WHITE;

        operationList = new ArrayList<>();
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
     * Returns the opposing team.
     *
     * @return the opposing team.
     */
    public static int getOpposingTeam(int team) {
        return (team == Board.WHITE) ? Board.BLACK : Board.WHITE;
    }

    public class Operation {

        int x;
        int y;
        int undoCount;
        int original;

        public Operation(int x, int y, int undoCount, int original) {
            this.x = x;
            this.y = y;
            this.undoCount = undoCount;
            this.original = original;
        }

    }

    /**
     * Checks whether the piece can be placed in the given position, and places
     * it there, flipping all applicable pieces.
     *
     * @param x
     * @param y
     * @param team
     * @return number of flipped pieces
     */
    public int put(int x, int y, int team) {
        if (board[y][x] != 0) {
            return 0;
        }

        board[y][x] = team;
        moveNumber++;
        operationList.add(new Operation(x, y, moveNumber, 0));

        int nmbOfFlips = 0;

        nmbOfFlips += flipDirection(x, y, 1, 0, team);
        nmbOfFlips += flipDirection(x, y, -1, 0, team);
        nmbOfFlips += flipDirection(x, y, 0, 1, team);
        nmbOfFlips += flipDirection(x, y, 0, -1, team);
        nmbOfFlips += flipDirection(x, y, 1, 1, team);
        nmbOfFlips += flipDirection(x, y, 1, -1, team);
        nmbOfFlips += flipDirection(x, y, -1, 1, team);
        nmbOfFlips += flipDirection(x, y, -1, -1, team);

        if (nmbOfFlips == 0) {
            undo();
        }

        return nmbOfFlips;
    }

    public int place(long point, int team) {
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
     * @return number of flipped pieces.
     */
    private int flipDiagonally(int x, int y, int team) {
        int nmbOfFlips = 0;
        nmbOfFlips += flipDirection(x, y, 1, 1, team);
        nmbOfFlips += flipDirection(x, y, 1, -1, team);
        nmbOfFlips += flipDirection(x, y, -1, 1, team);
        nmbOfFlips += flipDirection(x, y, -1, -1, team);

        return nmbOfFlips;
    }

    /**
     * Flips all applicable pieces between the given coordinates and the pieces
     * of this player that are in the same column.
     *
     * @param x
     * @param y
     * @param team
     * @return number of flipped pieces.
     */
    private int flipHorizontally(int x, int y, int team) {
        int nmbOfFlips = 0;
        nmbOfFlips += flipDirection(x, y, 0, 1, team);
        nmbOfFlips += flipDirection(x, y, 0, -1, team);

        return nmbOfFlips;
    }

    /**
     * Flips all applicable pieces between the given coordinates and the pieces
     * of this player that are in the same row.
     *
     * @param x
     * @param y
     * @param team
     * @return number of flipped pieces.
     */
    private int flipVertically(int x, int y, int team) {
        int nmbOfFlips = 0;
        nmbOfFlips += flipDirection(x, y, 1, 0, team);
        nmbOfFlips += flipDirection(x, y, -1, 0, team);
        return nmbOfFlips;
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
     * @return number of flipped pieces.
     */
    private int flipDirection(int x, int y, int dx, int dy, int team) {
        int nmbOfFlips = 0;
        if (!checkDirection(x, y, dx, dy, team)) {
            return 0;
        }
        while (checkForOpponent(x + dx, y + dy, team)) {
            flip(x + dx, y + dy);

            nmbOfFlips++;

            dx = dx > 0 ? dx + 1 : dx < 0 ? dx - 1 : dx;
            dy = dy > 0 ? dy + 1 : dy < 0 ? dy - 1 : dy;
        }
        return nmbOfFlips;
    }

    /**
     * Flips the piece to the opposing color in the given coordinates + adds the
     * flip to the list of operations.
     *
     * @param x
     * @param y
     */
    private void flip(int x, int y) {
        operationList.add(new Operation(x, y, moveNumber, board[y][x]));
        board[y][x] = board[y][x] == WHITE ? BLACK : WHITE;
    }

    /**
     * Undoes the last move and subtracts one from the moveNumber.
     */
    public void undo() {
        for (int j = operationList.size() - 1; j >= 0 && operationList.get(j).undoCount == moveNumber; j--) {
            Operation operation = operationList.get(j);
            board[operation.y][operation.x] = operation.original;

            operationList.remove(j);
        }
        moveNumber--;
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

    /**
     * Returns how many pieces this team has on the board,
     *
     * @param team
     * @return number of pieces
     */
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

    /**
     * Returns how many pieces have been placed on the board.
     *
     * @return number of pieces placed
     */
    public int getPieceNumber() {
        return moveNumber;
    }

    /**
     * Whether the board is full.
     *
     * @return true, if all the pieces have been set on the board.
     */
    public boolean isFull() {
        return moveNumber == 60;
    }
}
