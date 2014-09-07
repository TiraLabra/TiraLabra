package math;

import algorithms.complex.LUDecomposition;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * Contains a complex matrix as a two-dimensional array of complex numbers, and
 * includes methods for basic complex matrix operations.
 *
 * @author ydna
 */
public class ComplexMatrix implements Serializable {

    /**
     * Two-dimensional array containing the matrix elements as complex values.
     */
    private final Complex[][] array;

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
     *
     * @param array Two-dimensional double array.
     */
    public ComplexMatrix(Complex[][] array) {
        this.array = array;
        this.rows = array.length;
        this.cols = array[0].length;
    }

    /**
     * Constructs a zero matrix with the given number of rows and columns.
     *
     * @param rows The number of rows.
     * @param cols The number of columns.
     */
    public ComplexMatrix(int rows, int cols) {
        this.array = new Complex[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.array[i][j] = new Complex(0);
            }
        }
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * Constructs a constant matrix with the given number of rows and columns.
     *
     * @param rows The number of rows.
     * @param cols The number of columns.
     * @param val The value of matrix elements.
     */
    public ComplexMatrix(int rows, int cols, Complex val) {
        this.array = new Complex[rows][cols];
        this.rows = rows;
        this.cols = cols;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.array[i][j] = new Complex(val.re(), val.im());
            }
        }
    }

    @Override
    public String toString() {
        String matrix = "";
        int length = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix += String.format("%16.4f", array[i][j].re());
                if (array[i][j].im() >= 0) {
                    matrix += "+";
                }
                matrix += String.format("%.4fi", array[i][j].im());
            }
            matrix += "\n";
        }
        return matrix;
    }

    /**
     * Constructs a matrix from the given file.
     *
     * @param file File name.
     * @throws Exception In case of error.
     */
    public ComplexMatrix(String file) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        ComplexMatrix matrix = (ComplexMatrix) in.readObject();
        this.array = matrix.getArray();
        this.rows = matrix.getRows();
        this.cols = matrix.getCols();
    }

    /**
     * Saves this matrix into the given file.
     * 
     * @param file File name.
     * @throws Exception In case of error.
     */
    public void saveMatrix(String file) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(this);
    }

    /**
     * Accesses the two-dimensional array with the matrix elements.
     *
     * @return Pointer to the array.
     */
    public Complex[][] getArray() {
        return this.array;
    }

    /**
     * Returns the row dimension of the matrix.
     *
     * @return Number of rows.
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * Returns the column dimension of the matrix.
     *
     * @return Number of columns.
     */
    public int getCols() {
        return this.cols;
    }

    /**
     * Returns an element from the given row and column of the matrix.
     *
     * @param i Row number.
     * @param j Column number.
     * @return Matrix element.
     */
    public Complex get(int i, int j) {
        return this.array[i][j];
    }

    /**
     * Adds another matrix to this matrix.
     *
     * @param matrix Another matrix.
     * @return Sum.
     */
    public ComplexMatrix add(ComplexMatrix matrix) {
        if (matrix.getRows() != rows || matrix.getCols() != cols) {
            throw new IllegalArgumentException("Matrix dimensions do not match.");
        }
        Complex[][] temp = new Complex[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[i][j] = array[i][j].add(matrix.array[i][j]);
            }
        }
        return new ComplexMatrix(temp);
    }

    /**
     * Subtracts another matrix from this matrix.
     *
     * @param matrix Another matrix.
     * @return Difference.
     */
    public ComplexMatrix subtract(ComplexMatrix matrix) {
        if (matrix.getRows() != rows || matrix.getCols() != cols) {
            throw new IllegalArgumentException("Matrix dimensions do not match.");
        }
        Complex[][] temp = new Complex[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[i][j] = array[i][j].subtract(matrix.array[i][j]);
            }
        }
        return new ComplexMatrix(temp);
    }

    /**
     * Multiplies this matrix with a scalar number.
     *
     * @param number Multiplier.
     * @return Scalar product.
     */
    public ComplexMatrix multiply(double number) {
        Complex[][] temp = new Complex[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[i][j] = array[i][j].multiply(number);
            }
        }
        return new ComplexMatrix(temp);
    }

    /**
     * Returns the transpose of this matrix.
     *
     * @return Transpose.
     */
    public ComplexMatrix transpose() {
        Complex[][] temp = new Complex[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                temp[i][j] = new Complex(array[j][i].re(), array[j][i].im());
            }
        }
        return new ComplexMatrix(temp);
    }

    /**
     * Multiplies this matrix by another matrix.
     *
     * @param matrix Another matrix.
     * @return Matrix product.
     */
    public ComplexMatrix multiply(ComplexMatrix matrix) {
        if (matrix.getRows() != cols) {
            throw new IllegalArgumentException("Matrix inner dimensions do not match.");
        }
        Complex[][] temp = new Complex[rows][matrix.getCols()];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                for (int k = 0; k < cols; k++) {
                    if (temp[i][j] == null) {
                        temp[i][j] = new Complex(0);
                    }
                    temp[i][j] = temp[i][j].add(array[i][k].multiply(matrix.get(k, j)));
                }
            }
        }
        return new ComplexMatrix(temp);
    }

    /**
     * Calculates the determinant of this matrix.
     *
     * @return Determinant.
     */
    public Complex determinant() {
        return new LUDecomposition(array).determinant();
    }

    /**
     * Calculates the inverse of this matrix.
     *
     * @return Matrix inverse.
     */
    public ComplexMatrix inverse() {
        Complex[][] temp = new Complex[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[i][j] = new Complex(i == j ? 1.0 : 0.0);
            }
        }
        return new ComplexMatrix(new LUDecomposition(array).solve(temp));
    }

    /**
     * Generates a complex matrix with random real and imaginary parts within
     * the given range.
     *
     * @param rows The number of rows.
     * @param cols The number of columns.
     * @param lower Lower limit.
     * @param upper Upper limit.
     * @return Random complex matrix.
     */
    public static ComplexMatrix randomMatrix(int rows, int cols, double lower, double upper) {
        Random rand = new Random();
        Complex[][] temp = new Complex[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[i][j] = new Complex(lower + (upper - lower) * rand.nextDouble(), lower + (upper - lower) * rand.nextDouble());
            }
        }
        return new ComplexMatrix(temp);
    }

}
