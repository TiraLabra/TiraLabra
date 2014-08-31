package math;

import algorithms.real.Strassen;
import algorithms.real.LUDecomposition;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * Contains a matrix as a two-dimensional array of double precision floating point numbers,
 * and includes methods for basic matrix operations.
 * 
 * @author ydna
 */
public class RealMatrix implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Two-dimensional array containing the matrix elements as double values.
     */
    private final double[][] array;
    
    /**
     * The row dimension, or the number of rows in the matrix.
     */
    private final int rows;
    
    /**
     * The column dimension, or the number of columns in the matrix.
     */
    private final int cols;
    
    /**
     * Constructs a matrix from a two-dimensional array.
     * @param array Two-dimensional double array.
     */
    public RealMatrix(double[][] array) {
        this.array = array;
        this.rows = array.length;
        this.cols = array[0].length;
    }
    
    /**
     * Constructs a zero matrix with the given number of rows and columns.
     * @param rows The number of rows.
     * @param cols The number of columns.
     */
    public RealMatrix(int rows, int cols) {
        this.array = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }
    
    /**
     * Constructs a constant matrix with the given number of rows and columns.
     * @param rows The number of rows.
     * @param cols The number of columns.
     * @param val The value of matrix elements.
     */
    public RealMatrix(int rows, int cols, double val) {
        this.array = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.array[i][j] = val;
            }
        }
    }
    
    /**
     * Returns the matrix as a string.
     * @return Matrix as a string.
     */
    @Override
    public String toString() {
        String matrix = "";
        int length = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix += String.format("%16.4f ", array[i][j]);
            }
            matrix += "\n";
        }
        return matrix;
    }
    
    /**
     * Constructs a matrix from the given file.
     * @param file File name.
     * @throws Exception 
     */
    public RealMatrix(String file) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));   
        RealMatrix matrix = (RealMatrix) in.readObject();
        this.array = matrix.getArray();
        this.rows = matrix.getRows();
        this.cols = matrix.getCols();
    }
    
    /**
     * Saves this matrix into the given file.
     * @param file File name.
     * @throws Exception 
     */
    public void saveMatrix(String file) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(this);
    }
    
    /**
     * Accesses the two-dimensional array with the matrix elements.
     * @return Pointer to the array.
     */
    public double[][] getArray() {
        return this.array;
    }
    
    /**
     * Makes a duplicate of the two-dimensional array with the matrix elements.
     * @return Pointer to the copy of the array.
     */
    public double[][] copyArray() {
        double[][] copy = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                copy[i][j] = array[i][j];
            }
        }
        return copy;
    }
    
    /**
     * Returns the row dimension of the matrix.
     * @return Number of rows.
     */
    public int getRows() {
        return this.rows;
    }
    
    /**
     * Returns the column dimension of the matrix.
     * @return Number of columns.
     */
    public int getCols() {
        return this.cols;
    }
    
    /**
     * Returns an element from the given row and column of the matrix.
     * @param i Row number.
     * @param j Column number.
     * @return Matrix element.
     */
    public double get(int i, int j) {
        return this.array[i][j];
    }
    
    /**
     * Adds another matrix to this matrix.
     * @param matrix Another matrix.
     * @return Sum.
     */
    public RealMatrix add(RealMatrix matrix) {
        if (matrix.getRows() != rows || matrix.getCols() != cols) {
            throw new IllegalArgumentException("Matrix dimensions do not match.");
        }
        double[][] temp = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[i][j] = array[i][j] + matrix.array[i][j];
            }
        }
        return new RealMatrix(temp);
    }
    
    /**
     * Subtracts another matrix from this matrix.
     * @param matrix Another matrix.
     * @return Difference.
     */
    public RealMatrix subtract(RealMatrix matrix) {
        if (matrix.getRows() != rows || matrix.getCols() != cols) {
            throw new IllegalArgumentException("Matrix dimensions do not match.");
        }
        double[][] temp = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[i][j] = array[i][j] - matrix.array[i][j];
            }
        }
        return new RealMatrix(temp);
    }
    
    /**
     * Multiplies this matrix with a scalar number.
     * @param number Multiplier.
     * @return Scalar product.
     */
    public RealMatrix multiply(double number) {
        double[][] temp = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[i][j] = number * array[i][j];
            }
        }
        return new RealMatrix(temp);
    }
    
    /**
     * Returns the transpose of this matrix.
     * @return Transpose.
     */
    public RealMatrix transpose() {
        double[][] temp = new double[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                temp[i][j] = array[j][i];
            }
        }
        return new RealMatrix(temp);
    }
    
    /**
     * Multiplies this matrix by another matrix.
     * @param matrix Another matrix.
     * @return Matrix product.
     */
    public RealMatrix multiply(RealMatrix matrix) {
        if (matrix.getRows() != cols) {
            throw new IllegalArgumentException("Matrix inner dimensions do not match.");
        }
        double[][] temp = new double[rows][matrix.getCols()];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                for (int k = 0; k < cols; k++) {
                    temp[i][j] += array[i][k] * matrix.array[k][j];
                }
            }
        }
        return new RealMatrix(temp);
    }
    
    /**
     * Multiplies this matrix by another matrix using Strassen's algorithm.
     * @param matrix Another matrix.
     * @return Matrix product.
     */
    public RealMatrix strassenMultiply(RealMatrix matrix) {
        double[][] temp = Strassen.strassen(this.getArray(), matrix.getArray());
        return new RealMatrix(temp);
    }
    
    /**
     * Calculates the determinant of this matrix.
     * @return Determinant.
     */
    public double determinant() {
        return new LUDecomposition(array).determinant();
    }
    
    /**
     * Calculates the inverse of this matrix.
     * @return Matrix inverse.
     */
    public RealMatrix inverse() {
        double[][] temp = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[i][j] = (i == j ? 1.0 : 0.0);
            }
        }
        return new RealMatrix(new LUDecomposition(array).solve(temp));
    }
    
    /**
     * Generates a matrix with random double elements within the given range.
     * @param rows The number of rows.
     * @param cols The number of columns.
     * @param lower Lower limit.
     * @param upper Upper limit.
     * @return Random matrix.
     */
    public static RealMatrix randomMatrix(int rows, int cols, int lower, int upper) {
        Random rand = new Random();
        double[][] temp = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[i][j] = lower + (upper - lower) * rand.nextDouble();
            }
        }
        return new RealMatrix(temp);
    }
    
}
