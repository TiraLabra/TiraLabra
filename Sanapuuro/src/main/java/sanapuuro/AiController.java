package sanapuuro;

import sanapuuro.letters.LetterPool;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import sanapuuro.letters.LetterContainer;

/**
 * Keeps track of score and has methods for selecting and adding letters for
 * submission. An AI player that figures out and submits the word with the best
 * score.
 *
 * @author skaipio@cs
 */
public class AiController implements Controller{
    private final Grid grid;
    private final LetterPool letterPool;    // Pool for picking letters from.
    private ControllerListener controlled;
    private final Set<String> words;
    private Anagram bestAnagram;

    public AiController(LetterPool letterPool, Grid grid, String name, Set<String> words) {
        this.letterPool = letterPool;
        this.grid = grid;
        this.words = words;
    }
    
    @Override
    public void makeMove() {
        this.bestAnagram = null;
        this.tryPermutations(this.letterPool.getLetters(), 0);
        this.placeSubmission();
    }

    @Override
    public ControllerListener getControlled() {
        return this.controlled;
    }

    @Override
    public void setControlled(ControllerListener controlled) {
        this.controlled = controlled;
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
        String anagram = this.getStringFromLetterContainers(containers, lettersFromStart);
        if (!this.words.contains(anagram)) {
            return;
        }
        for (int x = 0; x < this.grid.width; x++) {
            for (int y = 0; y < this.grid.height; y++) {
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

    /**
     * Requires length != 0.
     * @param startX
     * @param startY
     * @param deltaX
     * @param deltaY
     * @param length
     * @return 
     */
    private boolean lineNotOccupied(int startX, int startY, int deltaX, int deltaY, int length) {
        if (deltaX != 0) {
            int lastX = startX + deltaX * (length-1);
            if (lastX < 0 || lastX > this.grid.width) {
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

    private String getStringFromLetterContainers(LetterContainer[] containers, int lettersFromStart) {
        StringBuilder submission = new StringBuilder();
        for (int i = 0; i < lettersFromStart; i++) {
            submission.append(containers[i].letter.character);
        }
        return submission.toString();
    }

    
    
    private void placeSubmission() {
        if (this.bestAnagram == null) {
            return;
        }
        List<LetterContainer> containers = this.bestAnagram.containers;
        for(int i = 0; i < containers.size(); i++){
            int x = this.bestAnagram.x + i*this.bestAnagram.deltaX;
            int y = this.bestAnagram.y + i*this.bestAnagram.deltaY;
            this.getControlled().letterAdded(containers.get(i).letter.character, x, y);
        }
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
}
