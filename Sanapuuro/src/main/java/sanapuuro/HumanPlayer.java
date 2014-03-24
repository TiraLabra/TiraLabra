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
public class HumanPlayer implements Player, ControllerListener {

    private final ConsoleController controller;
    private final Grid grid;
    private final LetterPool letterPool;    // Pool for picking letters from.
    private final List<LetterContainer> containersForSubmission = new ArrayList<>();
    private final List<LetterContainer> selectedContainers = new ArrayList<>(); // Holds selected letters that are permanently in grid.
    private final List<LetterContainer> addedContainers = new ArrayList<>();    // Holds letters that can still be removed from grid.
    private int score = 0;          // Score.

    public HumanPlayer(ConsoleController controller, LetterPool letterPool, Grid grid) {
        this.controller = controller;
        this.letterPool = letterPool;
        this.grid = grid;
        controller.setListener(this);
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public List<LetterContainer> getContainersForSubmission() {
        return new ArrayList<>(this.containersForSubmission);
    }
    
    public List<LetterContainer> getSelectedContainers() {
        return new ArrayList<>(this.selectedContainers);
    }

    public List<LetterContainer> getAddedContainers() {
        return new ArrayList<>(this.addedContainers);
    }

    /**
     * Gets player's latest selection.
     *
     * @return Latest selected letter container, null if none has been selected.
     */
    public LetterContainer getLastSelection() {
        if (!this.selectedContainers.isEmpty()) {
            return this.selectedContainers.get(this.selectedContainers.size() - 1);
        }
        return null;
    }

    /**
     * Gets player's first selection.
     *
     * @return First selected letter container, null if none has been selected.
     */
    public LetterContainer getFirstSelection() {
        if (!this.selectedContainers.isEmpty()) {
            return this.selectedContainers.get(0);
        }
        return null;
    }

//    public boolean clearSelections() {
//        if (this.controlsEnabled) {
//            this.returnAllAddedLettersBackToLetterPool();
//            this.addedContainers.clear();
//            this.selectedContainers.clear();
//        }
//        return false;
//    }
    /**
     * Adds a letter container from the letter pool on the given location
     * location if there is no other container already present and adds it to
     * added containers.
     *
     * @param x Coordinate to set to.
     * @param y Coordinate to set to.
     */
    private void addLetterTo(int x, int y) {
        LetterContainer container = this.letterPool.useLetter();
        if (container != null) {
            boolean containerSet = this.grid.setContainerAt(container, x, y);
            if (!containerSet) {
                this.letterPool.unpickLetterAtIndex(container.letterPoolIndex());
            } else {
                this.addedContainers.add(container);
                this.containersForSubmission.add(container);
            }
        }
    }

    /**
     * Selects the letter from the given location and adds to selected letter
     * containers if there is any at the location.
     *
     * @param x Coordinate to set to.
     * @param y Coordinate to set to.
     * @return True if there was a container to select, false otherwise.
     */
    public boolean selectLetterAt(int x, int y) {
//        if (this.controlsEnabled && this.grid.hasContainerAt(x, y)) {
//            LetterContainer selection = this.grid.getContainerAt(x, y);
//            if (!this.selectedContainers.contains(selection)) {
//                this.selectedContainers.add(selection);
//                return true;
//            }
//        }
//        return false;
        return true;
    }

    /**
     * Removes the latest selection from player's selected letter containers if
     * there is any.
     *
     * @return True if controls are enabled and there were selected containers,
     * false otherwise.
     */
    public boolean removeLastSelection() {
//        if (this.controlsEnabled && !this.selectedContainers.isEmpty()) {
//            int index = this.selectedContainers.size() - 1;
//            LetterContainer container = this.selectedContainers.get(index);
//            if (this.addedContainers.contains(container)) {
//                this.letterPool.unpickLetterAtIndex(container.letterPoolIndex());
//                this.grid.removeContainerAt(container.getX(), container.getY());
//                this.addedContainers.remove(container);
//            }
//            this.selectedContainers.remove(index);
//            return true;
//        }
//        return false;
        return true;
    }

    /**
     * Evaluates currently selected letters and raises the score if evaluation
     * was successful.
     *
     * @return True if letters formed a valid word, false otherwise.
     */
    public boolean submitSelectedLetters() {
//        if (!this.controlsEnabled) {
//            return false;
//        }
//        if (this.selectedContainers.isEmpty()) {
//            this.status = "No letters have been selected!";
//            return false;
//        }
//        EvaluationResult result = this.evaluation.submitWord(this, this.selectedContainers);
//        this.status = result.reason;
//
//        if (result.succeeded) {
//            this.score += result.getScore();
//
//            this.setSelectedLettersToGridPermanently();
//            this.letterPool.replacePickedLetters();
//
//            this.clearSelectionsAndAdditions();
//            return true;
//        } else {
//            this.returnAllAddedLettersBackToLetterPool();
//            this.clearSelectionsAndAdditions();
//        }
//        return result.succeeded;
        return true;
    }

    /**
     * Sets this instance's selected letters to the letter grid permanently.
     */
    private void setSelectedLettersToGridPermanently() {
//        this.grid.setLettersToGridPermanently(this.selectedContainers);
//        this.letterPool.replacePickedLetters();
    }

    /**
     * Clears selected and added letters (NOTE: does not return added letters
     * back to pool).
     */
    private void clearSelectionsAndAdditions() {
        this.addedContainers.clear();
        this.selectedContainers.clear();
    }

    /**
     * Returns all added letters back to pool but does not clear them.
     */
    private void returnAllAddedLettersBackToLetterPool() {
//        this.grid.removeContainersFromGrid(this.addedContainers);
//        this.letterPool.clearLetterPicks();
    }

    @Override
    public void letterAddedFromPoolIndex(int index, int gridX, int gridY) {
        this.letterPool.setCurrentSelection(index);
        this.addLetterTo(gridX, gridY);
    }

    @Override
    public void letterSelectedAt(int gridX, int gridY) {
    }

    @Override
    public void wordSubmission(int score) {
        this.score += score;
    }

    @Override
    public LetterPool getLetterPool() {
        return this.letterPool;
    }

    @Override
    public ConsoleController getController() {
        return this.controller;
    }
}
