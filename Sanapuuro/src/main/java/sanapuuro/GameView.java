/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import sanapuuro.grid.Grid;
import sanapuuro.grid.LetterContainer;

/**
 *
 * @author skaipio
 */
public class GameView {

    private Grid grid;
    private ConsoleController controller;
    private Player playerOne;
    private Player playerTwo;

    public GameView(Grid grid, ConsoleController controller, Player playerOne, Player playerTwo) {
        this.grid = grid;
        this.controller = controller;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    void printView() {
        System.out.println();
        this.printRow(0);
        System.out.println(" Player One's letters: " + playerOne.getLetterPool());
        this.printRow(1);
        System.out.println(" Player Two's letters: " + playerTwo.getLetterPool());
        for (int y = 2; y < grid.height - 1; y++) {
            this.printRow(y);
            System.out.println("");
        }
        this.printRow(7);
        System.out.print(" Player One's word: ");
        for(LetterContainer container : playerOne.getContainersForSubmission()){
            System.out.print(container.letter);
        }
        System.out.println();
    }

    private void printRow(int row) {
        for (int x = 0; x < grid.width; x++) {
            if (grid.hasContainerAt(x, row)) {
                if (row == controller.getY() && x == controller.getX()) {
                    System.out.print(grid.getContainerAt(x, row).letter);
                } else {
                    System.out.print(grid.getContainerAt(x, row).letter.character + "");
                }
            } else if (row == controller.getY() && x == controller.getX()) {
                System.out.print("@");
            } else {
                System.out.print(".");
            }
        }
    }
}
