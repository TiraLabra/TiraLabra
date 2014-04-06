package sanapuuro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import sanapuuro.grid.Grid;
import sanapuuro.grid.LetterContainer;
import sanapuuro.letters.LetterPool;
import sanapuuro.words.WordEvaluator.Submission;
import sanapuuro.words.WordList;

/**
 * Keeps track of score and has methods for selecting and adding letters for
 * submission.
 *
 * @author skaipio@cs
 */
public class AiPlayer implements Player {

    private final ControllerStub controller;
    private final Grid grid;
    private final LetterPool letterPool;    // Pool for picking letters from.
    private final List<LetterContainer> submissionContainers = new ArrayList<>();
    private final List<LetterContainer> selectedContainers = new ArrayList<>(); // Holds selected letters that are permanently in grid.
    private final List<LetterContainer> addedContainers = new ArrayList<>();    // Holds letters that can still be removed from grid.
    private final WordList words;
    private int score = 0;          // Score.
    private final String name;
    private Anagram bestAnagram;

    public AiPlayer(LetterPool letterPool, Grid grid, String name, WordList words) {
        this.letterPool = letterPool;
        this.grid = grid;
        this.name = name;
        this.words = words;
        this.controller = new ControllerStub();
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public Submission getSubmission() {
        this.bestAnagram = null;
        this.tryPermutations(this.letterPool.getLetters(), 0);
        this.placeSubmission();
        return new Submission(this.submissionContainers, this.bestAnagram.deltaX, this.bestAnagram.deltaY);
    }

    @Override
    public LetterPool getLetterPool() {
        return this.letterPool;
    }

    @Override
    public String toString() {
        return this.name;
    }

    private void tryPermutations(LetterContainer[] containers, int k) {
        for (int i = k; i < containers.length; i++) {
            LetterContainer temp = containers[i];
            containers[i] = containers[k];
            containers[k] = temp;
            if (k >= 2) {
                for (int l = 0; l <= k; l++) {
                    this.tryAnagramToGrid(containers, k + 1);
                }
            }
            tryPermutations(containers, k + 1);
            temp = containers[k];
            containers[k] = containers[i];
            containers[i] = temp;
        }
    }
    
     private void tryAnagramToGrid(LetterContainer[] containers, int lettersFromStart) {
        String anagram = this.getStringFromLetterContainers(containers,lettersFromStart);
        if (!this.words.hasWord(anagram)) return;
        for (int x = 0; x < this.grid.width; x++) {
            for (int y = 0; y < this.grid.width; y++) {
                this.tryAnagramAt(x, y, containers, lettersFromStart);
            }
        }
    }

    private void tryAnagramAt(int x, int y, LetterContainer[] containers, int lettersFromStart) {
        if (this.grid.hasContainerAt(x, y)) {
            return;
        }
        this.calculateScoreForPlacement(x, y, 1, 0, containers, lettersFromStart);
        this.calculateScoreForPlacement(x, y, -1, 0, containers, lettersFromStart);
        this.calculateScoreForPlacement(x, y, 0, 1, containers, lettersFromStart);
        this.calculateScoreForPlacement(x, y, 0, -1, containers, lettersFromStart);
    }

    private void calculateScoreForPlacement(int x, int y, int deltaX, int deltaY, LetterContainer[] anagram, int lettersFromStart) {
        if (lineNotOccupied(x, y, deltaX, deltaY, lettersFromStart)) {
            int score = 0;
            for (int i = 0; i < lettersFromStart; i++) {
                score += anagram[i].letter.score;
            }
            if (this.bestAnagram == null || score > this.bestAnagram.score) {
                List<LetterContainer> containers = new ArrayList<>(3);
                for (int i = 0; i < lettersFromStart; i++) {
                    containers.add(anagram[i]);
                }
                this.bestAnagram = new Anagram(x, y, deltaX, deltaY, score, containers);
            }
        }
    }

    private boolean lineNotOccupied(int startX, int startY, int deltaX, int deltaY, int length) {
        if (deltaX != 0) {
            int lastX = startX + deltaX * length;
            if (lastX < -1 || lastX > this.grid.width) {
                return false;
            }
            for (int x = startX, i = 0; i < length; x += deltaX, i++) {
                if (!this.grid.isWithinGrid(x, startY) || this.grid.hasContainerAt(x, startY)) {
                    return false;
                }
            }
        } else if (deltaY != 0) {
            int lastY = startY + deltaY * length;
            if (lastY < -1 || lastY > this.grid.width) {
                return false;
            }
            for (int y = startY, i = 0; i < length; y += startY, i++) {
                if (!this.grid.isWithinGrid(y, startX) || this.grid.hasContainerAt(y, startX)) {
                    return false;
                }
            }
        }
        return true;
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

    /**
     * Clears selected and added letters
     */
    private void clearSelectionsAndAdditions() {
        this.letterPool.clearLetterPicks();
        this.addedContainers.clear();
        this.selectedContainers.clear();
        this.submissionContainers.clear();
    }

   

    private void placeSubmission() {
        if (this.bestAnagram == null) return;
        int i = 0;
        String submission = this.getStringFromLetterContainers(this.bestAnagram.containers);
        int x = this.bestAnagram.x;
        int y = this.bestAnagram.y;
        int deltaX = this.bestAnagram.deltaX;
        int deltaY = this.bestAnagram.deltaY;
        while (i < submission.length()) {
            if (!this.grid.isWithinGrid(x, y)) {
                break;
            }
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
    
    private String getStringFromLetterContainers(LetterContainer[] containers, int lettersFromStart) {
        StringBuilder submission = new StringBuilder();
        for (int i = 0; i < lettersFromStart; i++){
            submission.append(containers[i].letter.character);
        }
        return submission.toString();
    }

    private String getStringFromLetterContainers(List<LetterContainer> containers) {
        StringBuilder submission = new StringBuilder();
        for (LetterContainer container : containers){
            submission.append(container.letter.character);
        }
        return submission.toString();
    }

    private static class Anagram {  
        public final int x;
        public final int y;
        public final int deltaX;
        public final int deltaY;
        public final int score;
        public final List<LetterContainer> containers;

        public Anagram(int x, int y, int deltaX, int deltaY, int score, List<LetterContainer> containers) {
            this.x = x;
            this.y = y;
            this.deltaX = deltaX;
            this.deltaY = deltaY;
            this.score = score;
            this.containers = containers;
        }

        
    }

    public static class ControllerStub implements Controller {

        public ControllerStub() {
        }

        @Override
        public int getSubmissionStartX() {
            return 0;
        }

        @Override
        public int getSubmissionStartY() {
            return 0;
        }

        @Override
        public int getSubmissionDeltaX() {
            return 0;
        }

        @Override
        public int getSubmissionDeltaY() {
            return 0;
        }

        @Override
        public String getSubmission() {
            return "";
        }

    }
}
