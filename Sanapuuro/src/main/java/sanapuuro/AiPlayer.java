package sanapuuro;

import java.util.ArrayList;
import java.util.List;
import sanapuuro.grid.Grid;
import sanapuuro.grid.LetterContainer;
import sanapuuro.letters.LetterPool;

/**
 * Keeps track of score and has methods for selecting and adding letters for
 * submission.
 *
 * @author skaipio@cs
 */
public class AiPlayer implements Player {

    private final ConsoleController controller;
    private final Grid grid;
    private final LetterPool letterPool;    // Pool for picking letters from.
    private final List<LetterContainer> containersForSubmission = new ArrayList<>();
    private int score = 0;          // Score.
    private final String name;

    public AiPlayer(ConsoleController controller, LetterPool letterPool, Grid grid, String name) {
        this.controller = controller;
        this.letterPool = letterPool;
        this.grid = grid;
        this.name = name;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public List<LetterContainer> getSubmission() {
        return new ArrayList<>(this.containersForSubmission);
    }

    @Override
    public LetterPool getLetterPool() {
        return this.letterPool;
    }

    @Override
    public String toString() {
        return this.name;
    }

    private void trySubmission(String submission) {
        char direction = this.controller.getDirection();
        switch (direction) {
            case 'a':
                this.placeSubmission(submission, -1, 0);
                break;
            case 'd':
                this.placeSubmission(submission, 1, 0);
                break;
            case 'w':
                this.placeSubmission(submission, 0, -1);
                break;
            case 's':
                this.placeSubmission(submission, 0, 1);
                break;
        }
    }

    private void placeSubmission(String submission, int deltaX, int deltaY) {
        int i = 0;
        int x = this.controller.getX();
        int y = this.controller.getY();
        while (i < submission.length()) {
            if (this.grid.isWithinGrid(x, y) && this.grid.hasContainerAt(x, y)) {
                LetterContainer selected = this.grid.getContainerAt(x, y);
                this.containersForSubmission.add(selected);
                x += deltaX;
                y += deltaY;
                if (submission.charAt(i) == ' ') {
                    i++;
                }
            } else if (this.letterPool.hasFreeLetter(submission.charAt(i))) {
                LetterContainer used = this.letterPool.useLetter(submission.charAt(i));
                this.containersForSubmission.add(used);
                this.grid.setContainerAt(used, x, y);
                x += deltaX;
                y += deltaY;
                i++;
            } else {
                i++;
            }
        }
    }

    @Override
    public void successfulSubmission(int score) {
        this.score += score;
        this.containersForSubmission.clear();
        this.letterPool.replacePickedLetters();
    }

    @Override
    public void unsuccessfulSubmission() {
        this.containersForSubmission.clear();
    }

    @Override
    public ConsoleController getController() {
        return this.controller;
    }
}
