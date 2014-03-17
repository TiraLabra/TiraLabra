/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sequencealgorithms;

/** ScoringMatrix is a n x m matrix which keeps track of the alignment scores.
 *
 * @author riha
 */
public class AlignmentMatrix {
    
    private double[][] matrix;
    /**
     * Variable path keeps track of the path that leads to the desired alignment. It uses values 
     * 0 = diagonal move
     * 1 = vertical move
     * 2 = horizontal move
     */
    private int[][] path;
    
    public AlignmentMatrix(int m, int n) {
        matrix = new double[m+1][n+1];
        path = new int[m+1][n+1];
    }
    
    public void setScore(int m, int n, double score) {
        matrix[m][n] = score;
    }
    
    public void setPath(int m, int n, int previous) {
        path[m][n] = previous;
    }
    
    public double get(int m, int n) {
        return matrix[m][n];
    }
    
    public int getPath(int m, int n) {
        return path[m][n];
    }
    
    public void print() {
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
