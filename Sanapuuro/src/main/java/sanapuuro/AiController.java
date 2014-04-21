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
    private int cellsHandled = 0;
    private final Grid grid;
    private final LetterPool letterPool;    // Pool for picking letters from.
    private ControllerListener controlled;
    private final Set<String> words;
    private Word bestWord;

    public AiController(LetterPool letterPool, Grid grid, String name, Set<String> words) {
        this.letterPool = letterPool;
        this.grid = grid;
        this.words = words;
    }

    @Override
    public void makeMove() {
        this.cellsHandled = 0;
        this.bestWord = null;
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
     * Tries all possible permutations of indices greater than or equal to k
     * at the given cell coordinate towards the given direction.
     * Updates this instance's best known anagram with the best permutation.
     *
     * @param x X-coordinate of cell to try permutation at.
     * @param y Y-coordinate of cell to try permutation at.
     * @param deltaX The horizontal direction of the word.
     * @param deltaY The vertical direction of the word.
     * @param containers Containers to use for permutations.    
     * @param k The index (level) to start permuting from.
     * @param freeCells The number of free cells to use for letters from permutation.
     */
    public void tryPermutationsAt(int x, int y, int deltaX, int deltaY, LetterContainer[] containers, int k, int freeCells) {
        // Because we try only the first n = (freeCells on row/column from (x,y))
        // letters of the permutation, we don't need to permute the tail indices
        // i.e. indices that are greater than or equal to k when k==freeCells.
        if (k == freeCells || k == containers.length) {
            this.fitPermutationAt(x, y, deltaX, deltaY, containers);
        }      
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

    /**
     * Try all permutations of given containers to all cells towards all
     * four directions.
     * @param containers Containers to permute.
     */
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

    
    /**
     * Fits letters from given permutation to grid and selects letters already
     * in grid until a border is reached. Then checks if the added and selected
     * letters form a word. If not, then drops one letter and checks again if
     * a word is formed and so forth until there are no letters left.
     * @param x X-coordinate of cell to try given permutation at.
     * @param y Y-coordinate of cell to try given permutation at.
     * @param deltaX The horizontal direction of the word.
     * @param deltaY The vertical direction of the word.
     * @param permutation The given permutation to take letters from.
     */
    public void fitPermutationAt(int x, int y, int deltaX, int deltaY, LetterContainer[] permutation) {
        // Get containers that fit to the given coordinates and direction.
        List<LetterContainer> containers = this.getFullFit(x, y, deltaX, deltaY, permutation);
        
        // Potential score of containers that could be fitted.
        int score = this.getScoreFromContainers(containers);
        
        // Number of containers that were added from permutation containers.
        int lettersAdded = this.getCountOfLettersAdded(containers);

        // If the potential score with the given permutation and letters from the grid
        // are less than the current best word's score, then there is no
        // point to check if they form a valid word.
        if (this.bestWord != null && score <= this.bestWord.score) {
            return;
        }

        int i = containers.size() - 1;
        
        // Must have added at least one container from permutation containers
        // since all letters selected only from grid is not accepted
        // i >= 2 because words must be at three letters long
        while (i >= 2 && lettersAdded > 0) {
            String anagram = Util.stringFromLetterContainers(containers);
            // Check if the containers form a valid word and it has higher score than
            // the current best word.
            if (this.words.contains(anagram) && (this.bestWord == null || score > this.bestWord.score)) {
                this.bestWord = new Word(x, y, deltaX, deltaY, score, containers);
                break;
            }
            // If the container was not from grid then it was from the permutation
            // containers, therefore there are less added letters in the list of containers.
            if (!containers.get(i).isPermanent()) {
                lettersAdded--;
            }
            score -= containers.get(i).letter.score;
            containers.remove(i);
            i--;
        }
    }

    /**
     * Gets all letter containers from grid that are in the row or column
     * from the given coordinates towards the given direction and uses
     * containers from the given permutation in case there are cells
     * with no containers.
     * @param startX X-coordinate of cell to start getting containers from.
     * @param startY Y-coordinate of cell to start getting containers from.
     * @param deltaX The horizontal direction of the word.
     * @param deltaY The vertical direction of the word.
     * @param permutation The permutation to use containers from.
     * @return A list of containers that has containers from the grid if there were any and from the permutation.
     */
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

    /**
     * Calculates the total score of the given containers.
     * @param containers Containers to get score from.
     * @return The total score of the given containers.
     */
    public int getScoreFromContainers(List<LetterContainer> containers) {
        int score = 0;
        for (LetterContainer letterContainer : containers) {
            score += letterContainer.letter.score;
        }
        return score;
    }

    /**
     * @param containers Containers to use for calculating added letters.
     * @return The count of letters to be added in given containers.
     */
    public int getCountOfLettersAdded(List<LetterContainer> containers) {
        int lettersAdded = 0;
        for (LetterContainer letterContainer : containers) {
            if (!letterContainer.isPermanent()) {
                lettersAdded++;
            }
        }
        return lettersAdded;
    }

    /**
     * Places the containers of the submission to the grid.
     */
    private void placeSubmission() {
        if (this.bestWord == null) {
            return;
        }
        List<LetterContainer> containers = this.bestWord.containers;
        for (int i = 0; i < containers.size(); i++) {
            int x = this.bestWord.x + i * this.bestWord.deltaX;
            int y = this.bestWord.y + i * this.bestWord.deltaY;
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

    /**
     * Counts the number of cells that have no containers in them.
     * @param x X-coordinate of cell to start counting free cells from.
     * @param y Y-coordinate of cell to start counting free cells from.
     * @param deltaX The horizontal direction of the word.
     * @param deltaY The vertical direction of the word.
     * @return The count of free cells from the given starting position towards the given direction.
     */
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

    /**
     * A helper class for holding details about the best word
     * for submission.
     */
    private static class Word {

        public final int x;
        public final int y;
        public final int deltaX;
        public final int deltaY;
        public final int score;
        public final List<LetterContainer> containers;

        public Word(int x, int y, int deltaX, int deltaY, int score, List<LetterContainer> containers) {
            this.x = x;
            this.y = y;
            this.deltaX = deltaX;
            this.deltaY = deltaY;
            this.score = score;
            this.containers = containers;
        }

    }
}
