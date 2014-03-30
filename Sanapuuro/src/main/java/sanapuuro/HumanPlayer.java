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
public class HumanPlayer implements Player {

    private final ConsoleController controller;
    private final Grid grid;
    private final LetterPool letterPool;    // Pool for picking letters from.
    private final List<LetterContainer> containersForSubmission = new ArrayList<>();
    private final List<LetterContainer> selectedContainers = new ArrayList<>(); // Holds selected letters that are permanently in grid.
    private final List<LetterContainer> addedContainers = new ArrayList<>();    // Holds letters that can still be removed from grid.
    private int score = 0;          // Score.
    private boolean isSkipping = false;
    private boolean hasMadeASubmission = false;
    private final String name;

    public HumanPlayer(ConsoleController controller, LetterPool letterPool, Grid grid, String name) {
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

    /**
     * Clears selected and added letters (NOTE: does not return added letters
     * back to pool).
     */
    private void clearSelectionsAndAdditions() {
        this.addedContainers.clear();
        this.selectedContainers.clear();
    }

    @Override
    public LetterPool getLetterPool() {
        return this.letterPool;
    }

    @Override
    public ConsoleController getController() {
        return this.controller;
    }

    @Override
    public void addScore(int score) {
        this.score += score;
    }

    @Override
    public boolean hasMadeASubmission() {
        return this.hasMadeASubmission;
    }

    @Override
    public boolean isSkipping() {
        return this.isSkipping;
    }

    @Override
    public void makeMove() {
        this.isSkipping = false;
        this.hasMadeASubmission = false;
        Move move = this.controller.getMove();
        switch (move){
            case CursorMoved:
                break;
            case Skip:
                this.isSkipping = true;
                break;
            case Submitted:
                this.hasMadeASubmission = true;
                String submission = this.controller.getSubmission();
                this.trySubmission(submission);
                break;
        }
    }
    
    @Override
    public String toString(){
        return this.name;
    }
    
    private void trySubmission(String submission){
        char direction = this.controller.getDirection();
        switch (direction){
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
    
    private void placeSubmission(String submission, int deltaX, int deltaY){
        int i = 0;
        int x = this.controller.getX();
        int y = this.controller.getY();
        while (i < submission.length()){
            if (this.grid.isWithinGrid(x, y) && this.grid.hasContainerAt(x, y)){
                LetterContainer selected = this.grid.getContainerAt(x, y);
                this.selectedContainers.add(selected);
                this.containersForSubmission.add(selected);
                x+=deltaX;
                y+=deltaY;
                if (submission.charAt(i) == ' '){
                    i++;
                }
            }else if (this.letterPool.hasFreeLetter(submission.charAt(i))){
                LetterContainer used = this.letterPool.useLetter(submission.charAt(i));
                this.addedContainers.add(used);
                this.containersForSubmission.add(used);
                this.grid.setContainerAt(used, x, y);
                x+=deltaX;
                y+=deltaY;
                i++;
            }else{
                i++;
            }  
        }
    }

    @Override
    public void successfulSubmission() {
        this.addedContainers.clear();
        this.selectedContainers.clear();
        this.containersForSubmission.clear();
        this.letterPool.replacePickedLetters();
    }

    @Override
    public void unsuccessfulSubmission() {
        for(LetterContainer container : this.addedContainers){
            this.grid.removeContainer(container);
        }
        this.addedContainers.clear();
        this.selectedContainers.clear();
        this.containersForSubmission.clear();
    }
}
