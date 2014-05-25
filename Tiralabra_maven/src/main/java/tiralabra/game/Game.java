/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game;

import tiralabra.game.ai.AI;

/**
 * Responsible for user input, communication with the board and calling the AI.
 *
 * @author atte
 */
public class Game {

    /**
     * Which team is in turn. Defined in Board's static variables. 1 == WHITE, 2
     * == BLACK
     */
    private int turn;
    /**
     * The board, which contains the pieces and is responsible for flipping them
     * and placing them.
     */
    private Board board;
    /**
     * Whether the game is over.
     */
    private boolean gameOver;
    /**
     * The AI which is responsible for deciding which moves the computer players
     * should make.
     */
    private final AI ai;
    /**
     * Whether the white player is a computer player.
     */
    private boolean whiteai;
    /**
     * Whether the black player is a computer player.
     */
    private boolean blackai;
    /**
     * Whether a human player is in turn.
     */
    private boolean waiting;

    public Game(boolean white, boolean black) {
        this.turn = Board.WHITE;
        this.board = new Board();
        this.gameOver = false;
        this.ai = new AI(board);
        this.whiteai = white;
        this.blackai = black;
        this.waiting = false;
    }

    class Flip {

        int x;
        int y;
        int undoIndex;
    };

    /**
     * Changes the turn. Further changes the turn if the player in turn can't
     * make any move. If neither player can make a move, the game is set to be
     * over.
     */
    public void adjustTurn() {
        turn = Board.getOpposingTeam(turn);
        if (board.findLegalMoves(turn).isEmpty()) {
            turn = Board.getOpposingTeam(turn);
            if (board.findLegalMoves(turn).isEmpty()) {
                gameOver = true;
            }
        }
    }

    /**
     * Places a human player's piece on the board.
     *
     * @param point
     */
    public void playHumanTurn(long point) {
        board.place(point, turn);
        waiting = false;
        adjustTurn();
    }

    /**
     * Plays an AI's turn. Calls the AI and returns the move determined best,
     * placing it on the board.
     */
    public void playAITurn() {
        long point = ai.search(turn);
        board.place(point, turn);
        adjustTurn();
    }

    /**
     * If it's a human player's turn, set the waiting variable to true and
     * return, else play the next AI turn.
     */
    public void nextTurn() {
        if (gameOver || waiting) {
            return;
        }

        if (!isAITurn()) {
            waiting = true;
            return;
        }

        playAITurn();
    }

    /**
     * Check whether it's an AI's turn
     *
     * @return boolean on whether it's an AI's turn.
     */
    private boolean isAITurn() {
        return (turn == Board.WHITE && whiteai) || (turn == Board.BLACK && blackai);
    }

    /**
     * Get the team which is currently in play.
     *
     * @return which team's turn.
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Sets the turn to given value.
     *
     * @param turn
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * Gets the board integer 2d array.
     *
     * @return 2d array of integers containing the pieces.
     */
    public int[][] getBoard() {
        return board.getBoard();
    }

    /**
     * Gets the game's status concerning user input.
     *
     * @return Whether the game is waiting for user to make his turn.
     */
    public boolean isWaiting() {
        return waiting;
    }

    /**
     * Gets the game's status on whether the game is over or not.
     *
     * @return
     */
    public boolean gameOver() {
        return gameOver;
    }

}
