/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequencealgorithms;

/**
 * ScoringMatrix keeps track of the path penalties and bonuses.
 *
 * @author riha
 */
public class ScoringMatrix {

    private double[][] matrix;
    private char[] alphabet;

    public ScoringMatrix(char[] alphabet) {
        this.alphabet = new char[alphabet.length+1];
        this.alphabet[0] = '-';
        for (int i = 0; i<alphabet.length; i++) {
            this.alphabet[i+1] = alphabet[i];
        }
        matrix = new double[this.alphabet.length][this.alphabet.length];
        
    }
    
    public void print() {
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void setIndelPenalty(double penalty) {
        for (int i = 1; i < matrix.length; i++) {
            matrix[i][0] = penalty;
            matrix[0][i] = penalty;
        }
    }

    public void setMatchBonus(double bonus) {
        for (int i = 1; i < matrix.length; i++) {
            matrix[i][i] = bonus;
        }
    }

    public void setMismatchPenalty(double penalty) {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                if (i != j) {
                    matrix[i][j] = penalty;
                }
            }
        }
    }

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
