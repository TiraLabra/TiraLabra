/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author skaipio
 */
public class ConsoleController implements Controller {

    private char direction;
    private boolean skip = false;
    private final Scanner scanner;
    private final int gridHeight, gridWidth;
    private int x, y;
    private final List<ControllerListener> listeners = new ArrayList<>();

    public ConsoleController(Scanner scanner, int gridHeight, int gridWidth) {
        this.scanner = scanner;
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
    }
    
    public void addListener(ControllerListener listener){
        this.listeners.add(listener);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean setLocation(int x, int y) {
        if (canMoveTo(x, y)) {
            this.x = x;
            this.y = y;
            for(ControllerListener listener : this.listeners){
                listener.selectorMoved(this);
            }
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

    public char getDirection() {
        return this.direction;
    }

    public boolean canMoveTo(int x, int y) {
        return x >= 0 && x < gridWidth && y >= 0 && y < gridHeight;
    }

    @Override
    public String getSubmission() {
        String submission = ""; 
        this.skip = false;
        while (submission.isEmpty() && !this.skip) {
            submission = this.makeMove();
        }
        return submission;
    }

    private String makeMove() {
        String input = scanner.nextLine();
        switch (input) {
            case "a":
                this.moveLeft();
                return "";
            case "d":
                this.moveRight();
                return "";
            case "w":
                this.moveUp();
                return "";
            case "s":
                this.moveDown();
                return "";
            case "q":
                System.out.print("Give direction to add letters (wasd): ");
                input = scanner.nextLine();
                while (input.isEmpty() || !"wasd".contains(input.charAt(0) + "")) {
                    System.out.print("Give direction to add letters (wasd): ");
                    input = scanner.nextLine();
                }
                this.direction = input.charAt(0);
                System.out.print("Give characters to add or space to select: ");
                input = scanner.nextLine();
                if (input.isEmpty()){
                    this.skip = true;
                }
                return input;
            default:
                return "";
        }
    }

    @Override
    public int getSubmissionStartX() {
        return this.x;
    }

    @Override
    public int getSubmissionStartY() {
        return this.y;
    }

    @Override
    public int getSubmissionDeltaX() {
        if (this.direction == 'a') return -1;
        else if (this.direction == 'd') return 1;
        return 0;
    }

    @Override
    public int getSubmissionDeltaY() {
        if (this.direction == 'w') return -1;
        else if (this.direction == 's') return 1;
        return 0;
    }
}
