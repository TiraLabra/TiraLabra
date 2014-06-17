/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.app.ui;

import java.util.Scanner;
import java.util.Vector;
import tiralabra.game.Board;
import tiralabra.game.Player;
import tiralabra.game.ai.AI;
import tiralabra.utilities.BoardUtilities;

/**
 * Terminal UI for the game.
 *
 * @author atte
 */
public class TextUI extends Thread {

    private MoveTransmitter moveTransmitter;
    private Board board;
    private Scanner scanner;
    private AI ai;
    private boolean blackAI;
    private boolean whiteAI;

    public TextUI(Scanner scanner) {
        this.board = new Board();
        this.moveTransmitter = new MoveTransmitter(board);
        moveTransmitter.start();
        this.scanner = scanner;
        this.ai = new AI(board);
        this.blackAI = false;
        this.whiteAI = true;
    }

    /**
     * Transmit moves from the AI to the class that handles placing moves. Print
     * the board at the beginning of each turn.
     */
    @Override
    public synchronized void run() {
        initializeGame();
        while (!board.gameOver()) {
            System.out.println("==" + board.playerInTurn() + "==");
            BoardUtilities.printBoard(board.getBoard());

            if (!board.canMove(board.playerInTurn())) {
                board.pass();
                System.out.println("No moves; pass.");
            }

            long move;
            if (board.playerInTurn() == Player.BLACK) {
                move = blackAI ? ai.move() : humanMove();
            } else {
                move = whiteAI ? ai.move() : humanMove();
            }

            try {
                moveTransmitter.addMove(move);
                sleep(200);
            } catch (InterruptedException ex) {
            }
        }
        BoardUtilities.printBoard(board.getBoard());

        System.out.println("Black: " + board.blackPieces());
        System.out.println("White: " + board.whitePieces());

        boolean playAgain = determineOption("Play again? (y/n)");
        if (playAgain) {
            board.reset();
            run();
        } else {
            moveTransmitter.stop();
        }
    }

    private void initializeGame() {
        System.out.println("");
        System.out.println("======================");
        System.out.println("= WELCOME TO OTHELLO =");
        System.out.println("======================\n");
        System.out.println("OPTIONS:\n========");

        blackAI = determineOption("Is the Black player an AI? (y/n)");
        whiteAI = determineOption("Is the White player an AI? (y/n)");
    }

    /**
     * Determines an option with two possible values with the given question.
     *
     * @param question
     * @return
     */
    private boolean determineOption(String question) {
        boolean value = false;

        input:
        while (true) {
            System.out.println(question);
            String input = scanner.nextLine();

            switch (input.toUpperCase().trim()) {
                case ("Y"):
                    value = true;
                    break input;
                case ("N"):
                    value = false;
                    break input;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }

        return value;
    }

    /**
     * Ask the player for input for the move.
     *
     * @return
     */
    public long humanMove() {
        long move = -1;
        while (true) {
            System.out.println("Move (A-H)(1-8):");
            String input = scanner.nextLine();
            move = parseMove(input);
            System.out.println("");

            if (move != -1) {
                if (board.canPlace(Board.x(move), Board.y(move))) {
                    break;
                } else {
                    System.out.println("Can't place a piece on that tile.");
                }
            } else {
                System.out.println("Invalid input! Give coordinates in form: \'B5\'");
            }
        }
        return move;
    }

    /**
     * Parses a move from the input.
     *
     * @param input
     * @return
     */
    public long parseMove(String input) {
        if (input.length() != 2) {
            return -1;
        }

        input = input.trim().toUpperCase();

        String letters = "ABCDEFGH";

        int x = -1;
        int y = -1;
        for (int i = 0; i < letters.length(); i++) {
            if (letters.charAt(i) == input.charAt(0)) {
                x = i;
            }
        }

        try {
            y = Integer.parseInt("" + input.charAt(1)) - 1;
        } catch (NumberFormatException ex) {
            y = -1;
        }

        if (x == -1 || y == -1 || y < 0 || y > board.height()) {
            return -1;
        }

        return Board.point(x, y);
    }
}
