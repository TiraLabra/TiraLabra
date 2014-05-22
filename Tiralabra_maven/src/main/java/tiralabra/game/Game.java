/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game;

import tiralabra.game.ai.AI;

/**
 *
 * @author atte
 */
public class Game {

    private byte turn;
    private Board board;
    private boolean gameOver;
    private final AI whiteai;
    private final AI blackai;

    public Game(boolean white, boolean black) {
        this.turn = Board.WHITE;
        this.board = new Board();
        this.gameOver = false;
        this.whiteai = white ? new AI(board, Board.WHITE) : null;
        this.blackai = black ? new AI(board, Board.BLACK) : null;
    }

    public void adjustTurn() {
        turn = opposingTurn();
        if (board.findLegalMoves(turn).isEmpty()) {
            turn = opposingTurn();
        }
    }
    
    private byte opposingTurn() {
        return (turn == Board.WHITE ? Board.BLACK : Board.WHITE);
    }

    public boolean playHumanTurn(long point) {
        if (this.turn == Board.WHITE) {
            boolean success = board.put(point, Board.WHITE);
            adjustTurn();
            return success;
        } else {
            boolean success = board.put(point, Board.BLACK);
            adjustTurn();
            return success;
        }
    }

    public void playAITurn() {
        if (this.turn == Board.WHITE) {
            long point = whiteai.greedyBeginnerMove();
            board.put(Board.x(point), Board.y(point), Board.WHITE);
            adjustTurn();
        } else {
            long point = blackai.greedyBeginnerMove();
            board.put(Board.x(point), Board.y(point), Board.BLACK);
            adjustTurn();
        }
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(byte turn) {
        this.turn = turn;
    }
    
    public byte[][] getBoard() {
        return board.getBoard();
    }

}
