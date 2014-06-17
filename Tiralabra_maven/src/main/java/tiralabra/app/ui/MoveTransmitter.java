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
 * Transmits moves to the Board from the user interface.
 *
 * @author atte
 */
public class MoveTransmitter extends Thread {

    /**
     * Board to transmit moves to.
     */
    private Board board;
    /**
     * All moves to be transmitted to the board.
     */
    private ArrayList<Long> moves;
    
    /**
     * 
     * @param board 
     */
    public MoveTransmitter(Board board) {
        this.board = board;
        moves = new ArrayList<>();
    }

    /**
     * As long as the game is running, try to commit a move.
     */
    @Override
    public synchronized void run() {
        while (true) {
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
    synchronized void move() throws InterruptedException {
        while (moves.isEmpty()) {
            wait();
        }

        long move = moves.get(0);
        moves.remove(move);
        board.placeTile(move);

        if (!board.canMove(board.playerInTurn())) {
            board.pass();
        }
    }

    /**
     * Adds a move to the Vector of moves to be committed.
     * @param move
     * @throws InterruptedException 
     */
    synchronized void addMove(long move) throws InterruptedException {
        moves.add(move);
        notify();
    }
}
