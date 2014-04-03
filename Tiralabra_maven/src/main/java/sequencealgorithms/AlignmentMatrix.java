/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequencealgorithms;

/**
 * ScoringMatrix is a n x m matrix which keeps track of the alignment scores.
 *
 * @author riha
 */
public class AlignmentMatrix {

    private double[][] matrix;
    /**
     * The highest alignment score.
     */
    private double bestAlignmentScore;
    /**
     * The coordinates of the best alignment score.
     */
    private int bestScoreX, bestScoreY;
    /**
     * Variable path keeps track of the path that leads to the desired
     * alignment. It uses values 0 = diagonal move 1 = vertical move 2 =
     * horizontal move
     */
    private int[][] path;

    private final double NEGINF = -1E100;

    /**
     * Constructor for the AlignmentMatrix.
     *
     * @param m The length of the first sequence to be aligned.
     * @param n The length of the second sequence to be aligned.
     */
    public AlignmentMatrix(int m, int n) {
        matrix = new double[m + 1][n + 1];
        path = new int[m + 1][n + 1];
        
        bestAlignmentScore = NEGINF;
        bestScoreX = -1;
        bestScoreY = -1;
    }

    /**
     * Sets the alignment score for a single entry. Also updates the best score
     * and its coordinates if necessary.
     *
     * @param m The first coordinate of the entry.
     * @param n The second coordinate of the entry.
     * @param score The alignment score to be set.
     */
    public void setScore(int m, int n, double score) {
        matrix[m][n] = score;
        if (score > bestAlignmentScore) {
            bestAlignmentScore = score;
            bestScoreX = m;
            bestScoreY = n;
        }
    }

    /**
     * Sets the path score for a single entry.
     *
     * @param m The first coordinate of the entry.
     * @param n The second coordinate of the entry.
     * @param previous The int code of the direction to be set (0 = diagonal, 1
     * = vertical, 2 = horizontal)
     */
    public void setPath(int m, int n, int previous) {
        path[m][n] = previous;
    }

    public double get(int m, int n) {
        return matrix[m][n];
    }

    /**
     * Returns a path from where the computation arrived to this entry.
     *
     * @param m
     * @param n
     * @return An integer of following coding: 0 - diagonal 1 - vertical 2 -
     * horizontal
     */
    public int getPath(int m, int n) {
        return path[m][n];
    }

    public double getBestScore() {
        return bestAlignmentScore;
    }

    public int getBestScoreX() {
        return bestScoreX;
    }

    public int getBestScoreY() {
        return bestScoreY;
    }

    public void print() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print("(" + matrix[i][j] + ";" + path[i][j] + ") \t");
            }
            System.out.println("");
        }
    }
}
