package sanapuuro;

import java.util.ArrayList;
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
        System.out.print("permutations to handle: ");
        this.tryPermutations(this.letterPool.getLetters(), 0);
        System.out.println("");
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
     * Tries all possible permutations of given containers to grid. Updates this
     * instance's best known anagram with the best permutation.
     *
     * @param containers Containers to use for permutations.
     * @param k The index to start permuting from.
     */
    public void tryPermutations(LetterContainer[] containers, int k) {
        if (this.bestAnagram != null) {
//            for (LetterContainer letterContainer : this.bestAnagram.containers) {
//                System.out.print(letterContainer.letter);
//            }
//            System.out.println("");
            return;
        }
        if (k == containers.length) {
            this.tryPermutationToGrid(containers);
//            this.permutationsHandled++;
//            if (this.permutationsHandled % 2000 == 0) {
//                System.out.print(40000 - permutationsHandled + "... ");
//            }
        }
        for (int i = k; i < containers.length; i++) {
            LetterContainer temp = containers[i];
            containers[i] = containers[k];
            containers[k] = temp;
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
        int i = 0, j = 0;
        int score = 0;
        int lettersAdded = 0;
        // assuming grid is square
        List<LetterContainer> anagramContainers = new ArrayList<>(this.grid.width);
        while (i < this.grid.width) {
            int x_ = x + (deltaX * i);
            int y_ = y + (deltaY * i);
            if (!this.grid.isWithinGrid(x_, y_)) {
                break;
            }
            if (this.grid.hasContainerAt(x_, y_)) {
                anagramContainers.add(this.grid.getContainerAt(x_, y_));
                score += this.grid.getContainerAt(x_, y_).letter.score;
            } else {
                anagramContainers.add(permutation[j]);
                score += permutation[j].letter.score;
                lettersAdded++;
                j++;
            }

            i++;
        }
        i = anagramContainers.size() - 1;
        while (i >= 2 && lettersAdded > 0) {
            String anagram = Util.stringFromLetterContainers(anagramContainers);
            if (this.words.contains(anagram) && (this.bestAnagram == null || score > this.bestAnagram.score)) {
                this.bestAnagram = new Anagram(x, y, deltaX, deltaY, score, anagramContainers);
                break;
            }
            if (!anagramContainers.get(i).isPermanent()) {
                lettersAdded--;
            }
            anagramContainers.remove(i);
            i--;
        }
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
            if (container.isPermanent()) {
                System.out.println("selecting " + container.letter + " at " + "(" + container.getX() + "," + container.getY() + ")");
                this.getControlled().letterSelected(container.getX(), container.getY());
            } else {
                System.out.println("adding " + container.letter + " at " + "(" + x + "," + y + ")");
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
