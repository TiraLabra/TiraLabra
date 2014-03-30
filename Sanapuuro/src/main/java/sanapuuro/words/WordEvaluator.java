/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.words;

import sanapuuro.fileio.WordReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sanapuuro.grid.LetterContainer;
import sanapuuro.utils.LetterContainerCoordinateComparator;

/**
 * Used for checking if letters form a word and the score for the word.
 * @author skaipio
 */
public class WordEvaluator {

    private final int wordLengthMinimum = 3;    // A minimum length for a word to be evaluated.
    private final WordList wordValidator = new WordReader();    // Checks if the given word is an actual word.

    /**
     * Evalutes the letters in the letter containers, checking if they form a word.
     * @param letterContainers Containers to evaluate.
     * @return An evaluation result that holds whether the word was valid and a positive score if it was.
     */
    public EvaluationResult evalute(List<LetterContainer> letterContainers) {
        if (letterContainers == null || letterContainers.isEmpty()) {
            throw new IllegalArgumentException("No letters were given for validation.");
        }
        if (letterContainers.size() < wordLengthMinimum) {
            return new EvaluationResult(false, "Word must be at least " + this.wordLengthMinimum + " characters long");
        }

        List<LetterContainer> containerCopy = new ArrayList(letterContainers);
        if (allContainersOnSameColumnWithoutGaps(containerCopy) || allContainersOnSameRowWithoutGaps(containerCopy)) {
            StringBuilder word = new StringBuilder(letterContainers.size());
            boolean allUsed = true;
            for (LetterContainer container : letterContainers) {
                if (!container.isPermanent()) {
                    allUsed = false;
                }
                word.append(container.letter.character);
            }
            if (!allUsed) {
                if (wordValidator.hasWord(word.toString())) {
                    int score = this.evaluteLetters(letterContainers);
                    return new EvaluationResult(true, "Score for word " + word.toString().toUpperCase() + ": " + score, score);
                }else{
                    return new EvaluationResult(false, word.toString().toUpperCase() + " is not a valid English word.");
                }
            } else {
                return new EvaluationResult(false, "Word must have at least one letter not used before.");
            }
        }
        return new EvaluationResult(false, "Word must be on the same column or row and should not have gaps.");
    }

    /**
     * Evaluates the combined score of the given letters.
     * @param letterContainers Letter containers with letters to evaluate.
     * @return The combined score of the letters in the containers.
     */
    private int evaluteLetters(List<LetterContainer> letterContainers) {
        int score = 0;
        for (LetterContainer container : letterContainers) {
            score += container.letter.score;
        }
        return score;
    }

    /**
     * Checks that all the containers are on the same row and don't have gaps between them.
     * @param letterContainers
     * @return 
     */
    private boolean allContainersOnSameRowWithoutGaps(List<LetterContainer> letterContainers) {
        Collections.sort(letterContainers, new LetterContainerCoordinateComparator(false));
        for (int i = 1; i < letterContainers.size(); i++) {
            LetterContainer previous = letterContainers.get(i - 1);
            LetterContainer current = letterContainers.get(i);
            if (current.getY() != previous.getY() || (current.getX() - previous.getX()) > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks that all the containers are on the same row and don't have gaps between them.
     * @param letterContainers
     * @return 
     */
    private boolean allContainersOnSameColumnWithoutGaps(List<LetterContainer> letterContainers) {
        Collections.sort(letterContainers, new LetterContainerCoordinateComparator(true));
        for (int i = 1; i < letterContainers.size(); i++) {
            LetterContainer previous = letterContainers.get(i - 1);
            LetterContainer current = letterContainers.get(i);
            if (current.getX() != previous.getX() || (current.getY() - previous.getY()) > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * A class to hold the evaluation result from word evaluation.
     */
    public static class EvaluationResult {

        public final boolean succeeded;
        public final String reason;
        private int score;

        private EvaluationResult(boolean succeeded, String reason, int score) {
            this(succeeded, reason);
            this.score = score;
        }

        private EvaluationResult(boolean succeeded, String reason) {
            this.succeeded = succeeded;
            this.reason = reason;
        }

        public int getScore() {
            return this.score;
        }
    }
}
