/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.app.ui;

import tiralabra.game.Board;
import tiralabra.game.Player;
import tiralabra.utilities.ArrayList;
;

/**
 * Transmits moves to the Board.
 *
 * @author atte
 */
public class Runnable extends Thread {

    private Board board;
    /**
     * All moves to be transmitted to the board.
     */
    private ArrayList<Long> moves;

    public Runnable() {
        board = new Board();
        moves = new ArrayList<>();
    }

    /**
     * As long as the game is running, try to commit a move.
     */
    @Override
    public synchronized void run() {
        while (!board.gameOver()) {
            try {
                move();
            } catch (InterruptedException ex) {
            }
        }
    }

    /**
     * Commit the moves added to the vector. While the vector is empty wait for
     * a move to be added there. Additionally, pass a turn, in the case that the
     * player whose turn it is next, can't move.
     *
     * @throws InterruptedException
     */
    public synchronized void move() throws InterruptedException {
        while (moves.isEmpty()) {
            wait();
        }

        long move = moves.get(0);
        moves.remove(move);
        board.placeTile(move);

        if (!board.canMove(inTurn())) {
            board.pass();
        }
    }

    /**
     * Adds a move to the Vector of moves to be committed.
     * @param move
     * @throws InterruptedException 
     */
    public synchronized void addMove(long move) throws InterruptedException {
        moves.add(move);
        notify();
    }

    /**
     * Returns the player whose turn it is.
     * @return 
     */
    public Player inTurn() {
        return board.playerInTurn();
    }

    /**
     * Returns the Board of the current game.
     * @return 
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Returns the status of the game.
     * @return 
     */
    public boolean gameOver() {
        return board.gameOver();
    }

}
