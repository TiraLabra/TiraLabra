/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import java.util.List;
import java.util.Set;
import sanapuuro.letters.LetterContainer;

/**
 * Used for checking if letters form a word and the score for the word.
 * All methods assume that given containers are in correct order
 * for submission.
 * @author skaipio
 */
public class WordEvaluator {

    private final int wordLengthMinimum = 3;    // A minimum length for a word to be evaluated.
    private final Set<String> words;            // Set of all valid words.

    public WordEvaluator(Set<String> words) {
        this.words = words;
    }

    /**
     * Evalutes the letters in the letter containers, checking if they form a
     * valid word. Letters with gaps between them are not accepted.
     * LetterContainers are evaluated in the order they are given.
     *
     * @param submission Submission to evaluate.
     *
     * @return An evaluation result that holds whether the word was valid and a
     * positive score if it was.
     */
    public EvaluationResult evalute(Submission submission) {
        List<LetterContainer> letterContainers = submission.letterContainers;
        int deltaX = submission.deltaX;
        int deltaY = submission.deltaY;

        if (letterContainers == null || letterContainers.isEmpty()) {
            throw new IllegalArgumentException("No letters were given for validation.");
        }
        if (letterContainers.size() < wordLengthMinimum) {
            return new EvaluationResult(false, "Word must be at least " + this.wordLengthMinimum + " characters long");
        }
        if (deltaX == 0 && deltaY == 0) {
            throw new IllegalArgumentException("Either deltaX or deltaY must be other than zero.");
        }

        deltaX = this.getUnit(deltaX);
        deltaY = this.getUnit(deltaY);

        if (this.wordHasGaps(letterContainers, deltaX, deltaY)) {
            return new EvaluationResult(false, "The word must not have gaps.");
        } else if (this.allLettersHaveBeenUsedPreviously(letterContainers)) {
            return new EvaluationResult(false, "Word must have at least one letter not used before.");
        }

        String word = this.getWordFromContainers(letterContainers);

        if (this.words.contains(word)) {
            int score = this.evaluteLetters(letterContainers);
            return new EvaluationResult(true, "Score for word " + word.toUpperCase() + ": " + score, score);
        } else {
            return new EvaluationResult(false, word.toUpperCase() + " is not a valid English word.");
        }
    }

    /**
     * Limits number to -1, 0 or 1.
     *
     * @param n Number to limit.
     * @return 1 if n is a positive number, 0 if n is 0, -1 if n is a negative
     * number.
     */
    private int getUnit(int n) {
        if (n == 0) {
            return n;
        }
        return n > 0 ? 1 : -1;
    }

    /**
     * Evaluates the combined score of the given letters.
     *
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
     * Checks if the given containers have any gaps between them.
     * @param letterContainers Containers to check. Assumes containers are in order.
     * @param deltaX -1 if word is from right to left, 1 if word is from left to right.
     * @param deltaY -1 if word is from bottom to top, 1 if word is from top to bottom.
     * @return 
     */
    private boolean wordHasGaps(List<LetterContainer> letterContainers, int deltaX, int deltaY) {
        for (int i = 0; i < letterContainers.size() - 1; i++) {
            LetterContainer container = letterContainers.get(i);
            LetterContainer nextContainer = letterContainers.get(i + 1);

            if ((deltaX != 0 && container.getX() + deltaX != nextContainer.getX())
                    || (deltaY != 0 && container.getY() + deltaY != nextContainer.getY())) {
                return true;
            }
        }
        return false;
    }

    private String getWordFromContainers(List<LetterContainer> letterContainers) {
        StringBuilder word = new StringBuilder(letterContainers.size());
        for (LetterContainer container : letterContainers) {
            word.append(container.letter.character);
        }
        return word.toString();
    }

    private boolean allLettersHaveBeenUsedPreviously(List<LetterContainer> letterContainers) {
        for (LetterContainer container : letterContainers) {
            if (!container.isPermanent()) {
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

    /**
     * A simple container class to hold the letter containers to be evaluated
     * and the intended reading direction of the submission.
     *
     * @author skaipio
     */
    public static class Submission {

        public final List<LetterContainer> letterContainers;
        public final int deltaX;
        public final int deltaY;

        /**
         * @param letterContainers Containers to evaluate. Evaluated in the
         * order they are given.
         * @param deltaX The horizontal direction of the word. Positive if from
         * left to right, negative if from right to left.
         * @param deltaY The vertical direction of the word. Positive if from
         * left to right, negative if from right to left.
         */
        public Submission(List<LetterContainer> letterContainers, int deltaX, int deltaY) {
            this.letterContainers = letterContainers;
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }
    }
}
