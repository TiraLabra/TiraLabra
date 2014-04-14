package sanapuuro;

import sanapuuro.letters.LetterPool;
import java.util.List;
import java.util.Set;
import sanapuuro.letters.LetterContainer;
import sanapuuro.utils.Util;

/**
 * Keeps track of score and has methods for selecting and adding letters for
 * submission. An AI player that figures out and submits the word with the best
 * score.
 *
 * @author skaipio@cs
 */
public class AiController implements Controller {
    private int permutationsHandled = 0;
    private long totalMillisTaken = 0;
    private LetterContainer[] fitting = new LetterContainer[8];
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
        this.permutationsHandled = 0;
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

    /**
     * Tries all possible permutations of given containers to grid. Updates
     * this instance's best known anagram with the best permutation.
     * @param containers Containers to use for permutations.
     * @param k The index to start permuting from.
     */
    public void tryPermutations(LetterContainer[] containers, int k) {
        for (int i = k; i < containers.length; i++) {
            LetterContainer temp = containers[i];
            containers[i] = containers[k];
            containers[k] = temp;
            this.permutationsHandled++;
            if (this.permutationsHandled % 100 == 0){
                System.out.println("permutations handled: " + permutationsHandled);
                System.out.println("millis taken by fitting: " + this.totalMillisTaken);
            }
            this.tryPermutationToGrid(containers);
            tryPermutations(containers, k + 1);
            temp = containers[k];
            containers[k] = containers[i];
            containers[i] = temp;
        }
    }

    private void tryPermutationToGrid(LetterContainer[] containers) {
        for (int x = 0; x < this.grid.width; x++) {
            for (int y = 0; y < this.grid.height; y++) {
                this.tryPermutationAt(x, y, containers);
            }
        }
    }

    private void tryPermutationAt(int x, int y, LetterContainer[] permutation) {
        this.calculateBestScoreForPlacement(x, y, 1, 0, permutation);
        this.calculateBestScoreForPlacement(x, y, -1, 0, permutation);
        this.calculateBestScoreForPlacement(x, y, 0, 1, permutation);
        this.calculateBestScoreForPlacement(x, y, 0, -1, permutation);
    }

    private void calculateBestScoreForPlacement(int x, int y, int deltaX, int deltaY, LetterContainer[] permutation) {
        long before = System.currentTimeMillis();
        this.getPermutationThatFitsAt(x, y, deltaX, deltaY, permutation);
        long after = System.currentTimeMillis();
        this.totalMillisTaken += (after-before);
        if (fitting[2] == null) return;
        int i = 2;
        while (i < fitting.length && fitting[i] != null) {          
            String anagram = Util.getStringFromFirstNLetterContainers(fitting, i);
            if (this.words.contains(anagram)) {
                int score = 0;
                for (int j = 0; j < i; j++) {
                    score += fitting[j].letter.score;
                }
                if (this.bestAnagram == null || score > this.bestAnagram.score) {
                    //this.bestAnagram = new Anagram(x, y, deltaX, deltaY, score, containers.subList(0, i+1));
                }
            }
            i++;
        }

    }

    private void getPermutationThatFitsAt(int x, int y, int deltaX, int deltaY, LetterContainer[] permutation) {
        int i = 0, j = 0;
        int x_ = x, y_ = y;
        while (this.grid.isWithinGrid(x_, y_)) {
            if (this.grid.hasContainerAt(x_, y_)) {
                fitting[j] = this.grid.getContainerAt(x_, y_);
            } else {
                fitting[j] = permutation[i];
                i++;
            }
            x_ += deltaX;
            y_ += deltaY;
            j++;
        }
        if (j < fitting.length - 1){
            fitting[j+1] = null;
        }
    }

    /**
     * Requires length != 0.
     *
     * @param startX
     * @param startY
     * @param deltaX
     * @param deltaY
     * @param length
     * @return
     */
    private boolean lineNotOccupied(int startX, int startY, int deltaX, int deltaY, int length) {
        if (deltaX != 0) {
            int lastX = startX + deltaX * (length - 1);
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

    private void placeSubmission() {
        if (this.bestAnagram == null) {
            return;
        }
        List<LetterContainer> containers = this.bestAnagram.containers;
        for (int i = 0; i < containers.size(); i++) {
            int x = this.bestAnagram.x + i * this.bestAnagram.deltaX;
            int y = this.bestAnagram.y + i * this.bestAnagram.deltaY;
            LetterContainer container = containers.get(i);
            if(container.isPermanent()){
                this.getControlled().letterSelected(container.getX(), container.getY());
            }
            else{
                this.getControlled().letterAdded(container.letter.character, x, y);
            }            
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
