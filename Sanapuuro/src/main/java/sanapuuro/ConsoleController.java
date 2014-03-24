/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import java.util.Scanner;

/**
 *
 * @author skaipio
 */
public class ConsoleController {

    private boolean selectionMode = false;
    private boolean horizontal = false;
    private final Scanner scanner;
    private final int gridHeight, gridWidth;
    private int x, y;
    private ControllerListener listener;

    public ConsoleController(Scanner scanner, int gridHeight, int gridWidth) {
        this.scanner = scanner;
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
    }

    boolean makeMove() {
        String input = scanner.nextLine();
        switch (input) {
            case "a":
                return this.moveLeft();
            case "d":
                return this.moveRight();
            case "w":
                return this.moveUp();
            case "s":
                return this.moveDown();
            case "q":
                this.selectionMode = true;            
                return this.addLetter(this.readIndexToAdd());
        }
        return false;
    }

    public void setListener(ControllerListener listener) {
        this.listener = listener;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean setLocation(int x, int y) {
        if (canMoveTo(x, y)){
            this.x = x;
            this.y = y;
        }
        return true;
    }

    public boolean moveUp() {
        return this.setLocation(x, this.y - 1);
    }

    public boolean moveDown() {
        return this.setLocation(x, this.y + 1);
    }

    public boolean moveLeft() {
        return this.setLocation(this.x - 1, y);
    }

    public boolean moveRight() {
        return this.setLocation(this.x + 1, y);

    }

    public boolean canMoveTo(int x, int y) {
        return x >= 0 && x < gridWidth && y >= 0 && y < gridHeight;
    }

    private int readIndexToAdd() {
        System.out.print("Letter index to add: ");
        return scanner.nextInt();
    }

    private boolean addLetter(int indexToAdd) {
        this.listener.letterAddedFromPoolIndex(indexToAdd, this.x, this.y);
        return true;
    }

}
