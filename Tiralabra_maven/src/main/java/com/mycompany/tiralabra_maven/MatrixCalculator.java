
package com.mycompany.tiralabra_maven;

/**
 * Class containing matrix operations.
 */
public class MatrixCalculator {
    
    /**
     * Algorithm for matrix addition.
     * Creates a result matrix c, and then goes through it
     * while setting values determined by addition of the values
     * in matrices a and b (c[i][j] = a[i][j] + b[i][j].
     * @return Resulting matrix
     */
    public Matrix add(Matrix a, Matrix b) {
        if (a.numCols() != b.numCols() || a.numRows() != b.numRows()) {
            return null;
        }
        Matrix c = new Matrix(a.numRows(), a.numCols());
        for (int i=0; i<a.numRows(); i++) {
            for (int j=0; j<a.numCols(); j++) {
                c.setValue(i, j, a.get(i, j) + b.get(i, j));
            }
        }
        return c;
    }
    
    /**
     * Algorithm for matrix substraction to be done.     
     * Creates a result matrix c, and then goes through it
     * while setting values determined by substracting the value
     * in matrix b from the value in matrix a (c[i][j] = a[i][j] - b[i][j].
     * @return Resulting matrix
     */
    public Matrix substract(Matrix a, Matrix b) {
        if (a.numCols() != b.numCols() || a.numRows() != b.numRows()) {
            return null;
        }
        Matrix c = new Matrix(a.numRows(), a.numCols());
        for (int i=0; i<a.numRows(); i++) {
            for (int j=0; j<a.numCols(); j++) {
                c.setValue(i, j, a.get(i, j) - b.get(i, j));
            }
        }
        return c;
    }
    
    /**
     * Algorithm for matrix multiplication to be done.     
     */
    public Matrix multiply(Matrix a, Matrix b) {
        if (a.numCols() != b.numRows()) {
            return null;
        }
        Matrix c = new Matrix(a.numRows(), b.numCols());
        return c;
    }
    
    /**
     * Algorithm for calculating the determinant of a matrix to be done.     
     */
    public Double getDeterminant(Matrix a) {
        return 0.0;
    }
    
    /**
     * Algorithm for inverting a matrix to be done.     
     */
    public Matrix inverse(Matrix a) {
        Matrix c = new Matrix(a.numRows(), a.numCols());
        return c;
    }
    
    /**
     * Algorithm for matrix transposition.
     * A new matrix is created. The rows of matrix A become the columns of the result matrix, 
     * and the columns of matrix A become the rows of the result matrix.
     * @return Transposition of the given matrix
     */
    public Matrix transpose(Matrix a) {
        Matrix c = new Matrix(a.numCols(), a.numRows());
        for (int i=0; i<c.numRows(); i++) {
            for (int j=0; j<c.numCols(); j++) {
                c.setValue(i, j, a.get(j, i));
            }
        }
        return c;
    }
    
    /**
     * Algorithm for multiplying a matrix with a scalar to be done.     
     */
    public Matrix scalarMultiplication(Matrix a, Double b) {
        Matrix c = new Matrix(a.numRows(), a.numCols());
        for (int i=0; i<a.numRows(); i++) {
            for (int j=0; j<a.numCols(); j++) {
                c.setValue(i, j, a.get(i, j) * b);
            }
        }
        return c;
    }    
}
