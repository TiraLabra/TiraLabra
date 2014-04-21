/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequencealgorithms;

/**
 * ScoringMatrix keeps track of the path penalties and bonuses.
 *
 * @author Jari Haavisto
 */
public class ScoringMatrix {

    /**
     * The Scoring Matrix.
     */
    private double[][] matrix;
    /**
     * The alphabet used in inputs.
     */
    private char[] alphabet;
    /**
     * The gap penalty.
     */
    private double gapPenalty;
    /**
     * Initializes a new alphabet array that includes the indel character '-' and initializes a new Scoring Matrix.
     * 
     * @param alphabet The alphabet used in inputs.
     */
    public ScoringMatrix(char[] alphabet) {
        this.alphabet = new char[alphabet.length+1];
        this.alphabet[0] = '-';
        for (int i = 0; i<alphabet.length; i++) {
            this.alphabet[i+1] = alphabet[i];
        }
        matrix = new double[this.alphabet.length][this.alphabet.length];
        this.gapPenalty = 0;
    }
    /**
     * Prints the Scoring Matrix
     */
    public void print() {
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    /**
     * Setter for the gap penalty.
     * @param penalty The penalty score
     */
    public void setGapPenalty(double penalty) {
        gapPenalty = penalty;
    }
    
    /**
     * Getter for the gap penalty.
     * @return The penalty score
     */
    public double getGapPenalty() {
        return gapPenalty;
    }

    /**
     * Sets the penalty for insertion and deletion.
     * 
     * @param penalty Non-negative. 
     */
    public void setIndelPenalty(double penalty) {
        for (int i = 1; i < matrix.length; i++) {
            matrix[i][0] = penalty;
            matrix[0][i] = penalty;
        }
    }

    /**
     * Sets the match bonus. 
     * 
     * @param bonus 
     */
    public void setMatchBonus(double bonus) {
        for (int i = 1; i < matrix.length; i++) {
            matrix[i][i] = bonus;
        }
    }

    /**
     * Sets the penalty for a mismatch.
     * 
     * @param penalty Non-negative. 
     */
    public void setMismatchPenalty(double penalty) {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                if (i != j) {
                    matrix[i][j] = penalty;
                }
            }
        }
    }
    
//    /**
//     * Sets up all three 
//     * @param match
//     * @param mismatch
//     * @param indel 
//     */
//    public void setUp(double match, double mismatch, double indel) {
//        setMatchBonus(match);
//        setMismatchPenalty(mismatch);
//        setIndelPenalty(indel);
//    }

    private int indexOf(char c) {
        int i = 0;
        while (i < alphabet.length) {
            if (alphabet[i] == c) {
                return i;
            }
            i++;
        }
        return -1;
    }
    
    public double getScore(char a, char b) {
        return matrix[indexOf(a)][indexOf(b)];
    }

}
