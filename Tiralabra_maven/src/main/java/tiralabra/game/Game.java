/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game;

import java.util.Vector;

/**
 *
 * @author atte
 */
public class Game extends Thread{

    private Board board;
    private Vector<Long> moves;

    public Game() {
        board = new Board();
        moves = new Vector<>();
    }
    
    @Override
    public synchronized void run() {
        while(!board.gameOver()) {
            try {
                move();
            } catch (InterruptedException ex) { }
        }
    }

    public synchronized void move() throws InterruptedException {
        while (moves.isEmpty()) {
            wait();
        }
        long move = moves.firstElement();
        moves.removeElement( move );
        board.placeTile(move);
        if (!board.canMove(inTurn())) board.pass();
    }
    
    public synchronized void addMove(long move) throws InterruptedException {
        moves.add(move);
        notify();
    }

    public Player inTurn() {
        return board.getPlayerInTurn();
    }
    
    public Board getBoard() {
        return board;
    }
    
    public boolean gameOver() {
        return board.gameOver();
    }

}
