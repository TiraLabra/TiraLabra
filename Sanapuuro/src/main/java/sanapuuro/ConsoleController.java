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
    private char direction;
    private String submission;
    private final Scanner scanner;
    private final int gridHeight, gridWidth;
    private int x, y;
    private ControllerListener listener;

    public ConsoleController(Scanner scanner, int gridHeight, int gridWidth) {
        this.scanner = scanner;
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
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
    
    public char getDirection(){
        return this.direction;
    }

    public boolean canMoveTo(int x, int y) {
        return x >= 0 && x < gridWidth && y >= 0 && y < gridHeight;
    }
    
    public String getSubmission(){
        return this.submission;
    }

    public Move getMove() {
        String input = scanner.nextLine();
        switch (input) {
            case "a":
                this.moveLeft();
                return Move.CursorMoved;
            case "d":
                this.moveRight();
                return Move.CursorMoved;
            case "w":
                this.moveUp();
                return Move.CursorMoved;
            case "s":
                this.moveDown();
                return Move.CursorMoved;
            case "q":
                System.out.print("Give direction to add letters (wasd): ");
                input = scanner.nextLine();
                while(input.isEmpty() || !"wasd".contains(input.charAt(0) + "")){
                    System.out.print("Give direction to add letters (wasd): ");
                    input = scanner.nextLine();
                }
                this.direction = input.charAt(0);
                System.out.print("Give characters to add or space to select: ");
                this.submission = scanner.nextLine();
                return Move.Submitted;
                //return this.addLetter(this.readIndexToAdd());
        }
        return Move.Skip;
    }
}
