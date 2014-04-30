package sanapuuro.ui;

import sanapuuro.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import sanapuuro.ControllerListener;
import sanapuuro.Player;

/**
 * Keeps track of score and has methods for selecting and adding letters for
 * submission.
 *
 * @author skaipio@cs
 */
public class HumanConsoleController implements Controller {

    private char direction;
    private final Scanner scanner;
    private final int gridHeight, gridWidth;
    private int x, y;
    private final List<ConsoleControllerListener> listeners = new ArrayList<>();
    private ControllerListener controlled;

    public HumanConsoleController(Scanner scanner, int gridHeight, int gridWidth) {
        this.scanner = scanner;
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public ControllerListener getControlled() {
        return this.controlled;
    }

    @Override
    public void setControlled(ControllerListener controlled) {
        this.controlled = controlled;
    }

    public void addConsoleListener(ConsoleControllerListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void makeMove() {
        Player player = (Player) this.getControlled();
        this.notifyMakingMove();
        System.out.print(player + "'s turn: ");
        String input = scanner.nextLine();
        switch (input) {
            case "a":
                this.moveLeft();
                this.makeMove();
                break;
            case "d":
                this.moveRight();
                this.makeMove();
                break;
            case "w":
                this.moveUp();
                this.makeMove();
                break;
            case "s":
                this.moveDown();
                this.makeMove();
                break;
            case "q":
                boolean lettersQueried = this.queryLetters();
                while (!lettersQueried) {
                    lettersQueried = this.queryLetters();
                }
                break;
            default:
        }
    }

    private void notifyFailedToPlaceLetters() {
        for (ConsoleControllerListener listener : this.listeners) {
            listener.failedToPlaceLetters();
        }
    }

    private void notifyMakingMove() {
        for (ConsoleControllerListener listener : this.listeners) {
            listener.makingMove(this);
        }
    }

    /**
     * Attempts to place the given letters on the grid.
     *
     * @param letters
     */
    private boolean placeLetters(String letters) {
        int x_ = this.x;
        int y_ = this.y;
        int deltaX = 0;
        if (this.direction == 'd') {
            deltaX = 1;
        }
        if (this.direction == 'a') {
            deltaX = -1;
        }
        int deltaY = 0;
        if (this.direction == 's') {
            deltaY = 1;
        }
        if (this.direction == 'w') {
            deltaY = -1;
        }

        for (int i = 0; i < letters.length(); i++) {
            char c = letters.charAt(i);
            if (c != ' ') {
                if (!this.controlled.letterAdded(c, x_, y_)) {
                    this.controlled.clearLettersFromSubmission();
                    return false;
                }
            } else {
                if (!this.controlled.letterSelected(x_, y_)) {
                    this.controlled.clearLettersFromSubmission();
                    return false;
                }
            }
            x_ += deltaX;
            y_ += deltaY;
        }
        return true;
    }

    private boolean canMoveTo(int x, int y) {
        return x >= 0 && x < gridWidth && y >= 0 && y < gridHeight;
    }

    private boolean setLocation(int x, int y) {
        if (canMoveTo(x, y)) {
            this.x = x;
            this.y = y;
            for (ConsoleControllerListener listener : this.listeners) {
                listener.selectorMoved(this);
            }
        }
        return true;
    }

    private boolean moveUp() {
        return this.setLocation(x, this.y - 1);
    }

    private boolean moveDown() {
        return this.setLocation(x, this.y + 1);
    }

    private boolean moveLeft() {
        return this.setLocation(this.x - 1, y);
    }

    private boolean moveRight() {
        return this.setLocation(this.x + 1, y);

    }

    private boolean queryLetters() {
        System.out.print("Give direction to add letters (wasd): ");
        String input = scanner.nextLine();
        while (input.isEmpty() || !"wasd".contains(input.charAt(0) + "")) {
            System.out.print("Give direction to add letters (wasd): ");
            input = scanner.nextLine();
        }
        this.direction = input.charAt(0);
        System.out.print("Give characters to add or space to select: ");
        input = scanner.nextLine();
        if (!this.placeLetters(input)) {
            this.notifyFailedToPlaceLetters();
            return false;
        }
        return true;
    }

}
