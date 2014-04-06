package sanapuuro;

import java.util.ArrayList;
import java.util.List;
import sanapuuro.grid.Grid;
import sanapuuro.grid.LetterContainer;
import sanapuuro.letters.LetterPool;
import sanapuuro.words.WordEvaluator.Submission;

/**
 * Keeps track of score and has methods for selecting and adding letters for
 * submission.
 *
 * @author skaipio@cs
 */
public class HumanPlayer implements Player {

    private final Controller controller;
    private final Grid grid;
    private final LetterPool letterPool;    // Pool for picking letters from.
    private final List<LetterContainer> submissionContainers = new ArrayList<>();
    private final List<LetterContainer> selectedContainers = new ArrayList<>(); // Holds selected letters that are permanently in grid.
    private final List<LetterContainer> addedContainers = new ArrayList<>();    // Holds letters that can still be removed from grid.
    private int score = 0;          // Score.
    private final String name;

    public HumanPlayer(Controller controller, LetterPool letterPool, Grid grid, String name) {
        this.controller = controller;
        this.letterPool = letterPool;
        this.grid = grid;
        this.name = name;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    public List<LetterContainer> getSelectedContainers() {
        return new ArrayList<>(this.selectedContainers);
    }

    public List<LetterContainer> getAddedContainers() {
        return new ArrayList<>(this.addedContainers);
    }

    /**
     * Clears selected and added letters
     */
    private void clearSelectionsAndAdditions() {
        this.letterPool.clearLetterPicks();
        this.addedContainers.clear();
        this.selectedContainers.clear();
        this.submissionContainers.clear();
    }

    @Override
    public LetterPool getLetterPool() {
        return this.letterPool;
    }

    @Override
    public Submission getSubmission() {
        this.placeSubmission();
        return new Submission(this.submissionContainers, this.controller.getSubmissionDeltaX(), this.controller.getSubmissionDeltaY());

    }

    @Override
    public String toString() {
        return this.name;
    }

    private void placeSubmission() {
        String submission = this.controller.getSubmission();
        int i = 0;
        int x = this.controller.getSubmissionStartX();
        int y = this.controller.getSubmissionStartY();
        int deltaX = this.controller.getSubmissionDeltaX();
        int deltaY = this.controller.getSubmissionDeltaY();
        while (i < submission.length()) {
            if (!this.grid.isWithinGrid(x, y)) break;
            if (this.grid.hasContainerAt(x, y)) {
                LetterContainer selected = this.grid.getContainerAt(x, y);
                this.selectedContainers.add(selected);
                this.submissionContainers.add(selected);
                x += deltaX;
                y += deltaY;
                if (submission.charAt(i) == ' ') {
                    i++;
                }
            } else if (this.letterPool.hasFreeLetter(submission.charAt(i))) {
                LetterContainer used = this.letterPool.useLetter(submission.charAt(i));
                this.addedContainers.add(used);
                this.submissionContainers.add(used);
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
        this.letterPool.replacePickedLetters();
        this.clearSelectionsAndAdditions();
    }

    @Override
    public void unsuccessfulSubmission() {
        for (LetterContainer container : this.addedContainers) {
            this.grid.removeContainer(container);
        }
        this.clearSelectionsAndAdditions();
    }

    @Override
    public Controller getController() {
        return this.controller;
    }
}
