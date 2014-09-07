
package com.mycompany.tiralabra_maven;

import java.util.Random;

/**
 * Matrix represented as a 2D array.
 * 
 */
public class Matrix {
    private final double[][] matrix;
    private final int rows;
    private final int columns;
            
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
        this.rows = matrix.length;
        this.columns = matrix[0].length;
    }
    
    public double get(int row, int column) {
        return matrix[row][column];
    }
    
    public double[][] getArray() {
        return matrix;
    }
    
    /**
     * Return a new matrix array that is a copy of the array of this matrix.
     * @return 
     */
    public double[][] getArrayCopy() {
        double[][] copy = new double[rows][columns];
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                copy[i][j] = matrix[i][j];
            }
        }
        return copy;
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
    
    /**
     * Produces a submatrix of a given matrix starting and ending from the given rows and columns.
     * @param startingRow
     * @param endingRow
     * @param startingColumn
     * @param endingColumn
     * @return 
     */
    public Matrix subMatrix(int startingRow, int endingRow, int startingColumn, int endingColumn) {
        Matrix sub = new Matrix(endingRow-startingRow+1, endingColumn-startingColumn+1);
        for(int i=0; i<sub.numRows(); i++) {
            for(int j=0; j<sub.numCols(); j++) {
                sub.setValue(i, j, matrix[i+startingRow][j+startingColumn]);
            }
        }
        return sub;
    }
    
    public Matrix subMatrix(int[] r, int startingColumn, int endingColumn) {
      Matrix sub = new Matrix(r.length, endingColumn - startingColumn+1);      
      for (int i = 0; i < r.length; i++) {
        for (int j = startingColumn; j <= endingColumn; j++) {
          sub.setValue(i, j - startingColumn, matrix[r[i]][j]);
        }
      }      
      return sub;
    }
    
    /**
     * Algorithm for matrix addition.
     * Creates a result matrix c, and then goes through it
     * while setting values determined by addition of the values
     * in matrices a and b (c[i][j] = a[i][j] + b[i][j].
     * @return Resulting matrix
     */
    public Matrix add(Matrix m) {
        if (numCols() != m.numCols() || numRows() != m.numRows()) {
            return null;
        }
        Matrix c = new Matrix(numRows(), numCols());
        for (int i=0; i<numRows(); i++) {
            for (int j=0; j<numCols(); j++) {
                c.setValue(i, j, get(i, j) + m.get(i, j));
            }
        }
        return c;
    }
    
    /**
     * Multiply matrix by a scalar.
     * Multiplies every value in the matrix by b.
     * @param b
     * @return 
     */
    public Matrix multiplyByScalar(Double b) {
        Matrix c = new Matrix(numRows(), numCols());
        for (int i=0; i<numRows(); i++) {
            for (int j=0; j<numCols(); j++) {
                c.setValue(i, j, get(i, j) * b);
            }
        }
        return c;
    }
    
    /**
     * Algorithm for matrix substraction.     
     * Creates a result matrix c, and then goes through it
     * while setting values determined by substracting the value
     * in matrix b from the value in matrix a (c[i][j] = a[i][j] - b[i][j].
     * @return Resulting matrix
     */
    public Matrix subtract(Matrix m) {
        if (numCols() != m.numCols() || numRows() != m.numRows()) {
            return null;
        }
        Matrix c = new Matrix(numRows(), numCols());
        for (int i=0; i<numRows(); i++) {
            for (int j=0; j<numCols(); j++) {
                c.setValue(i, j, get(i, j) - m.get(i, j));
            }
        }
        return c;
    }    
    
    /**
     * Algorithm for matrix transposition.
     * A new matrix is created. The rows of matrix A become the columns of the result matrix, 
     * and the columns of matrix A become the rows of the result matrix.
     * @return Transposition of the given matrix
     */
    public Matrix transpose() {
        Matrix c = new Matrix(numCols(), numRows());
        for (int i=0; i<c.numRows(); i++) {
            for (int j=0; j<c.numCols(); j++) {
                c.setValue(i, j, get(j, i));
            }
        }
        return c;
    }
    
    /**
     * Swaps the places of row1 and row2.
     * @param row1
     * @param row2 
     */
    public void swapRows(Integer row1, Integer row2) {
        for (int i=0; i<numCols(); i++) {
            double temp = get(row1, i);
            setValue(row1, i, get(row2, i));
            setValue(row2, i, temp);
        }
    }
    
    /**
     * Multiplies given row with the given scalar.
     * @param row
     * @param scalar 
     */
    public void multiplyRow(Integer row, double scalar) {
        for (int i=0; i<numCols(); i++) {
            setValue(row, i, get(row, i)*scalar);
        }
    }
    
    /**
     * Divides given row with the given scalar.
     * @param row
     * @param scalar 
     */
    public void divideRow(Integer row, double scalar) {
        for (int i=0; i<numCols(); i++) {
            setValue(row, i, get(row, i)/scalar);
        }
    }
    
    /**
     * Adds a row multiplied by given scalar to another row.
     * @param rowToAddTo
     * @param rowToAdd
     * @param scalar 
     */
    public void addRow(Integer rowToAddTo, Integer rowToAdd, double scalar) {
        for (int i=0; i<numCols(); i++) {
            setValue(rowToAddTo, i, get(rowToAdd, i)*scalar);
        }
    }     
}
