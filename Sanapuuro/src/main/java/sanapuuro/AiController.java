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
    private int cellsHandled = 0;
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
        this.cellsHandled = 0;
        this.bestAnagram = null;
        this.tryPermutationsToGrid(this.letterPool.getLetters());
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
    public void tryPermutationsAt(int x, int y, int deltaX, int deltaY, LetterContainer[] containers, int k, int freeCells) {
        if (k == freeCells || k == containers.length) {
            this.fitPermutationAt(x, y, deltaX, deltaY, containers);
//            this.permutationsHandled++;
//            if (this.permutationsHandled % 4000 == 0) {
//                System.out.print(40000 - permutationsHandled + "... ");
//            }
        }
        // Because we try only the first n (= freeCells on row/column from (x,y))
        // letters, we don't need to permute the tail after n.
        for (int i = k; i < containers.length; i++) {
            LetterContainer temp = containers[i];
            containers[i] = containers[k];
            containers[k] = temp;
            tryPermutationsAt(x, y, deltaX, deltaY, containers, k + 1, freeCells);
            temp = containers[k];
            containers[k] = containers[i];
            containers[i] = temp;

        }
    }

    private void tryPermutationsToGrid(LetterContainer[] containers) {
        System.out.println("Cells handled: ");
        for (int x = 0; x < this.grid.width; x++) {
            for (int y = 0; y < this.grid.height; y++) {
                this.tryPermutationsAt(x, y, 1, 0, containers, 0, this.countOfFreeCellsFrom(x, y, 1, 0));
                this.tryPermutationsAt(x, y, -1, 0, containers, 0, this.countOfFreeCellsFrom(x, y, -1, 0));
                this.tryPermutationsAt(x, y, 0, 1, containers, 0, this.countOfFreeCellsFrom(x, y, 0, 1));
                this.tryPermutationsAt(x, y, 0, -1, containers, 0, this.countOfFreeCellsFrom(x, y, 0, -1));
                this.cellsHandled++;     
                System.out.print(this.cellsHandled + "... ");
            }
            System.out.println("");
        }      
    }

    public void fitPermutationAt(int x, int y, int deltaX, int deltaY, LetterContainer[] permutation) {
        List<LetterContainer> containers = this.getFullFit(x, y, deltaX, deltaY, permutation);
        int score = this.getScoreFromContainers(containers);
        int lettersAdded = this.getCountOfLettersAdded(containers);

        if (this.bestAnagram != null && score <= this.bestAnagram.score) {
            return;
        }

        int i = containers.size() - 1;
        // must have added at least one letter since all letters only from grid is not accepted
        // i >= 2 because words must be at three letters long
        while (i >= 2 && lettersAdded > 0) {
            String anagram = Util.stringFromLetterContainers(containers);
            if (this.words.contains(anagram) && (this.bestAnagram == null || score > this.bestAnagram.score)) {
                this.bestAnagram = new Anagram(x, y, deltaX, deltaY, score, containers);
                break;
            }
            if (!containers.get(i).isPermanent()) {
                lettersAdded--;
            }
            score -= containers.get(i).letter.score;
            containers.remove(i);
            i--;
        }
    }

    public List<LetterContainer> getFullFit(int startX, int startY, int deltaX, int deltaY, LetterContainer[] permutation) {
        int i = 0, j = 0;
        List<LetterContainer> containers = new ArrayList<>(this.grid.width);
        while (true) {
            int x = startX + (deltaX * i);
            int y = startY + (deltaY * i);
            if (!this.grid.isWithinGrid(x, y)) {
                break;
            }
            if (this.grid.hasContainerAt(x, y)) {
                containers.add(this.grid.getContainerAt(x, y));
            } else {
                containers.add(permutation[j]);
                j++;
            }
            i++;
        }
        return containers;
    }

    public int getScoreFromContainers(List<LetterContainer> containers) {
        int score = 0;
        for (LetterContainer letterContainer : containers) {
            score += letterContainer.letter.score;
        }
        return score;
    }

    public int getCountOfLettersAdded(List<LetterContainer> containers) {
        int lettersAdded = 0;
        for (LetterContainer letterContainer : containers) {
            if (!letterContainer.isPermanent()) {
                lettersAdded++;
            }
        }
        return lettersAdded;
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
                //System.out.println("selecting " + container.letter + " at " + "(" + container.getX() + "," + container.getY() + ")");
                this.getControlled().letterSelected(container.getX(), container.getY());
            } else {
                //System.out.println("adding " + container.letter + " at " + "(" + x + "," + y + ")");
                this.getControlled().letterAdded(container.letter.character, x, y);
            }
        }
    }

    private int countOfFreeCellsFrom(int x, int y, int deltaX, int deltaY) {
        int freeCells = 0;
        while (true) {
            if (!this.grid.isWithinGrid(x, y)) {
                break;
            }
            if (!this.grid.hasContainerAt(x, y)) {
                freeCells++;
            }
            x += deltaX;
            y += deltaY;
        }
        return freeCells;
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
