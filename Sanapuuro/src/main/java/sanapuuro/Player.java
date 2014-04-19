/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import java.util.ArrayList;
import java.util.List;
import sanapuuro.letters.LetterContainer;
import sanapuuro.letters.LetterPool;

/**
 *
 * @author skaipio
 */
public class Player implements ControllerListener {

    private final Grid grid;
    private final LetterPool letterPool;    // Pool for picking letters from.
    private final List<LetterContainer> submissionContainers = new ArrayList<>();
    private final List<LetterContainer> selectedContainers = new ArrayList<>(); // Holds selected letters that are permanently in grid.
    private final List<LetterContainer> addedContainers = new ArrayList<>();    // Holds letters that can still be removed from grid.
    private int score = 0;          // Score.
    private final String name;

    public Player(LetterPool letterPool, Grid grid, String name) {
        this.letterPool = letterPool;
        this.grid = grid;
        this.name = name;
    }

    public int getScore() {
        return this.score;
    }

    public List<LetterContainer> getSelectedContainers() {
        return new ArrayList<>(this.selectedContainers);
    }

    public List<LetterContainer> getAddedContainers() {
        return new ArrayList<>(this.addedContainers);
    }

    public boolean addLetterAt(char c, int x, int y) {
        if (!this.grid.isWithinGrid(x, y) || this.grid.hasContainerAt(x, y)) {
            return false;
        }

        LetterContainer container = this.letterPool.useLetter(c);
        if (container != null) {
            this.addedContainers.add(container);
            this.submissionContainers.add(container);
            this.grid.setContainerAt(container, x, y);
            return true;
        }

        return false;
    }

    public boolean selectLetterAt(int x, int y) {
        if (!this.grid.isWithinGrid(x, y) || !this.grid.hasContainerAt(x, y)) {
            return false;
        }

        LetterContainer container = this.grid.getContainerAt(x, y);
        this.selectedContainers.add(container);
        this.submissionContainers.add(container);
        return true;
    }

    public LetterPool getLetterPool() {
        return this.letterPool;
    }

    public void successfulSubmission(int score) {
        this.score += score;
        this.letterPool.replacePickedLetters();
        this.grid.setLettersToGridPermanently(addedContainers);
        this.clearSelectionsAndAdditions();
    }

    public void unsuccessfulSubmission() {
        this.grid.removeContainersFromGrid(this.addedContainers);
        this.clearSelectionsAndAdditions();
    }

    public List<LetterContainer> getSubmission(){
        return this.submissionContainers;
    }
    
    @Override
    public boolean letterAdded(char c, int x, int y) {
        return this.addLetterAt(c, x, y);
    }
    
    @Override
    public boolean letterSelected(int x, int y) {
        return this.selectLetterAt(x, y);
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
    public void clearLettersFromSubmission() {
        this.grid.removeContainersFromGrid(this.addedContainers);
        this.clearSelectionsAndAdditions();
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
