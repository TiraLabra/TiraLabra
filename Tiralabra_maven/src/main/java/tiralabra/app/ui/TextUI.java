/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.app.ui;

import java.util.Scanner;
import java.util.Vector;
import tiralabra.game.Board;
import tiralabra.game.Game;
import tiralabra.game.Player;
import tiralabra.game.ai.AI;
import tiralabra.utilities.Utilities;

/**
 * Terminal UI for the game.
 *
 * @author atte
 */
public class TextUI extends Thread {

    private Game game;
    private Board board;
    private Scanner scanner;
    private AI ai;
    private Vector vector;

    public TextUI(Game game, Scanner scanner) {
        this.game = game;
        this.board = game.getBoard();
        this.scanner = scanner;
        this.ai = new AI(board);
    }

    /**
     * Transmit moves from the AI to the game. Print the board at the beginning
     * of each turn.
     */
    @Override
    public synchronized void run() {
        while (!game.gameOver()) {
            Utilities.printBoard(board.getBoard());

            long move;
            if (game.inTurn() == Player.BLACK) {
                move = ai.move();
            } else {
                move = ai.move();
            }

            try {
                game.addMove(move);
            } catch (InterruptedException ex) {
            }
        }
    }

}
