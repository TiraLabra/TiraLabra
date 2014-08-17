
package com.mycompany.tiralabra_maven;

import java.util.Random;

/**
 * Matrix represented as a 2D array.
 * 
 */
public class Matrix {
    private double[][] matrix;
    private int rows;
    private int columns;
            
    public Matrix(int rows, int columns) {
        matrix = new double[rows][columns];
        this.columns = columns;
        this.rows = rows;
    }
    
    /**
     * Gives random values for the matrix.
     * @param rows Number of rows
     * @param columns Number of columns
     * @param lowestValue Lowest value for random value generation
     * @param highestValue Highest value for random value generation
     */
    public Matrix(int rows, int columns, int lowestValue, int highestValue) {
        matrix = new double[rows][columns];
        this.columns = columns;
        this.rows = rows;
        Random r = new Random();
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                matrix[i][j] = lowestValue + r.nextInt(highestValue-lowestValue-1) + r.nextDouble();
            }
        }
    }
    
    public Matrix (double[][] matrix) {
        this.matrix = matrix;
    }
    
    public double get(int row, int column) {
        return matrix[row][column];
    }
    
    public void setValue(int row, int column, double value) {
        matrix[row][column] = value;
    }
    
    public int numRows() {
        return rows;
    }
    
    public int numCols() {
        return columns;
    }
    
    public void print() {
        for(int i=0; i<rows; i++) {
            System.out.print("|");
            for (int j=0; j<columns; j++) {
                System.out.printf("%.2f" + " ", matrix[i][j]);
            }
            System.out.println("|");
        }
    }   
}
