/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.ui;

import sanapuuro.Controller;
import java.util.Collections;
import java.util.List;
import sanapuuro.AiController;
import sanapuuro.ControllerListener;
import sanapuuro.GameListener;
import sanapuuro.Grid;
import sanapuuro.Player;
import sanapuuro.letters.Letter;
import sanapuuro.letters.Letters;
import sanapuuro.utils.LetterAlphabeticalComparator;

/**
 *
 * @author skaipio
 */
public class ConsoleView implements ConsoleControllerListener, GameListener {

    private final Grid grid;
    private final Controller controllerOne;
    private final Controller controllerTwo;
    private final List<Letter> letters;

    public ConsoleView(Grid grid, Controller playerOne, Controller playerTwo, Letters letters) {
        this.grid = grid;
        this.controllerOne = playerOne;
        this.controllerTwo = playerTwo;
        this.letters = letters.getAllLetters();
        Collections.sort(this.letters, new LetterAlphabeticalComparator());
    }

    public void updateView(Controller controllerWithTurn) {
        Player playerOne = (Player) controllerOne.getControlled();
        Player playerTwo = (Player) controllerTwo.getControlled();
        System.out.println();
        this.printLetterScores();
        this.printRow(0, controllerWithTurn);
        System.out.println(" " + playerOne + "'s letters: " + playerOne.getLetterPool());
        this.printRow(1, controllerWithTurn);
        System.out.println(" " + playerTwo + "'s letters: " + playerTwo.getLetterPool());
        this.printRow(2, controllerWithTurn);
        System.out.println();
        this.printRow(3, controllerWithTurn);
        System.out.println(" " + playerOne + "'s score: " + playerOne.getScore());
        this.printRow(4, controllerWithTurn);
        System.out.println(" " + playerTwo + "'s score: " + playerTwo.getScore());
        for (int y = 5; y < grid.height - 1; y++) {
            this.printRow(y, controllerWithTurn);
            System.out.println("");
        }
        this.printRow(7, controllerWithTurn);
        System.out.println();
    }

    void printMessage(String msg) {
        System.out.print(msg);
    }

    private void printRow(int y, Controller controller) {
        for (int x = 0; x < grid.width; x++) {
            HumanConsoleController humanController = null;
            if (controller instanceof HumanConsoleController)
                humanController = (HumanConsoleController) controller;
            boolean isStart = humanController != null && y == humanController.getY() && x == humanController.getX();
            if (grid.hasContainerAt(x, y)) {
                if (isStart) {
                    System.out.print(grid.getContainerAt(x, y).letter);
                } else {
                    System.out.print(grid.getContainerAt(x, y).letter.character + "");
                }
            } else if (isStart) {
                System.out.print("@");
            } else {
                System.out.print(".");
            }
        }
    }

    private void printLetterScores() {
        for (int i = 0; i < letters.size() - 1; i++) {
            System.out.print(letters.get(i).character + ": " + letters.get(i).score + ", ");
        }
        System.out.print(letters.get(letters.size() - 1).character + ": " + letters.get(letters.size() - 1).score);
        System.out.println();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public void selectorMoved(Controller controller) {
        this.updateView(controller);
    }

    @Override
    public void turnStarted(Controller controllerWithTurn) {
        if (controllerWithTurn instanceof AiController)
            this.updateView(controllerWithTurn);
    }

    @Override
    public void wordSubmitted(Player playerWithTurn, String submissionStr, boolean succeeded, String reason, int score) {
        System.out.println(playerWithTurn + " submitted the word " + submissionStr.toUpperCase());
        if (succeeded) {
            System.out.println(playerWithTurn + " was awarded " + score + " points");
        } else {
            System.out.println(submissionStr + " is not a valid word");
        }
    }

    @Override
    public void playerSkipsTurn(Player playerWithTurn) {
        System.out.println(playerWithTurn + " skips their turn");
    }

    @Override
    public void failedToPlaceLetters() {
        System.out.println("Could not place letters, please try again or submit an empty string to skip turn.");
    }

    @Override
    public void makingMove(Controller controller) {
        this.updateView(controller);
    }
}
