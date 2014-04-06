/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.ui;

import sanapuuro.ui.View;
import sanapuuro.ui.Controller;
import sanapuuro.ui.ControllerListener;
import java.util.Collections;
import java.util.List;
import sanapuuro.Player;
import sanapuuro.grid.Grid;
import sanapuuro.letters.Letter;
import sanapuuro.letters.Letters;
import sanapuuro.utils.LetterAlphabeticalComparator;

/**
 *
 * @author skaipio
 */
public class GameView implements View, ControllerListener{

    private final Grid grid;
    private final Player playerOne;
    private final Player playerTwo;
    private final List<Letter> letters;

    public GameView(Grid grid, Player playerOne, Player playerTwo, Letters letters) {
        this.grid = grid;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.letters = letters.getAllLetters();
        Collections.sort(this.letters, new LetterAlphabeticalComparator());
    }

    @Override
    public void updateView(Controller controller) {
        System.out.println();
        this.printLetterScores();
        this.printRow(0, controller);
        System.out.println(" " + playerOne + "'s letters: " + playerOne.getLetterPool());
        this.printRow(1, controller);
        System.out.println(" " + playerTwo + "'s letters: " + playerTwo.getLetterPool());
        this.printRow(2, controller);
        System.out.println();
        this.printRow(3, controller);
        System.out.println(" " + playerOne + "'s score: " + playerOne.getScore());
        this.printRow(4, controller);
        System.out.println(" " + playerTwo + "'s score: " + playerTwo.getScore());
        for (int y = 5; y < grid.height - 1; y++) {
            this.printRow(y, controller);
            System.out.println("");
        }
        this.printRow(7, controller);
        System.out.println();
    }
    
    void printMessage(String msg){
        System.out.print(msg);
    }

    private void printRow(int row, Controller controller) {
        for (int x = 0; x < grid.width; x++) {
            if (grid.hasContainerAt(x, row)) {
                if (row == controller.getSubmissionStartY() && x == controller.getSubmissionStartX()) {
                    System.out.print(grid.getContainerAt(x, row).letter);
                } else {
                    System.out.print(grid.getContainerAt(x, row).letter.character + "");
                }
            } else if (row == controller.getSubmissionStartY() && x == controller.getSubmissionStartX()) {
                System.out.print("@");
            } else {
                System.out.print(".");
            }
        }
    }

    private void printLetterScores() {
        for(int i = 0; i < letters.size() - 1; i++){
            System.out.print(letters.get(i).character + ": " + letters.get(i).score + ", ");
        }
        System.out.print(letters.get(letters.size()-1).character + ": " + letters.get(letters.size()-1).score);
        System.out.println();
    }

    @Override
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public void selectorMoved(Controller controller) {
        this.updateView(controller);
    }
}
