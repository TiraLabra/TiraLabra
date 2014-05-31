package com.mycompany.tiralabra_maven.model;

import java.util.Objects;

/**
 * Provides functions for performing mathematical operations on matrices.
 *
 * @author gabriel
 */
public class MatrixMath {

    /**
     * Returns the sum of two specified matrices.
     *
     * @param matrixA the first matrix of the matrices to be added
     * @param matrixB the second matrix of the matrices added
     * @return the sum of the specified matrices.
     * @throws IllegalArgumentException if the matrices have different sizes
     * @throws NullPointerException if either parameter is null
     */
    public static Matrix add(Matrix matrixA, Matrix matrixB) {
        return performMatrixOperation(matrixA, matrixB, "addition");
    }

    /**
     * Returns the difference of two matrices.
     *
     * @param matrixA the matrix subtracted from
     * @param matrixB the matrix subtracted from the first matrix
     * @return the difference of the specified matrices
     * @throws IllegalArgumentException if the matrices have different sizes
     * @throws NullPointerException if either parameter is null
     */
    public static Matrix subtract(Matrix matrixA, Matrix matrixB) {
        return performMatrixOperation(matrixA, matrixB, "subtraction");
    }

    /**
     * Checks if two matrices are of the same size.
     *
     * @param matrixA the first matrix
     * @param matrixB the second matrix
     * @return true if the matrices are of the same size; false otherwise
     * @throws NullPointerException if either parameter is null
     */
    public static boolean sameSize(Matrix matrixA, Matrix matrixB) {
        checkIfMatricesAreNull(matrixA, matrixB);
        return (matrixA.rows() == matrixB.rows() && matrixA.cols() == matrixB.cols());
    }

    /**
     * Takes two matrices and throws NullPointerException if either matrix is
     * null
     *
     * @param matrixA the first matrix
     * @param matrixB the second matrix
     * @throws NullPointerException if either argument is null
     */
    private static void checkIfMatricesAreNull(Matrix matrixA, Matrix matrixB) {
        Objects.requireNonNull(matrixA, "matrixA must not be null");
        Objects.requireNonNull(matrixB, "matrixB must not be null");
    }

    /**
     * Performs the specified matrix operation using the two specified matrices
     * and returns the resulting matrix.
     *
     * @param matrixA the first matrix to be used in the specified operation
     * @param matrixB the second matrix to be used in the specified operation
     * @param operation the specified operation; either addition or subtraction
     * @return the matrix resulting of the operation
     * @throws IllegalArgumentException if the matrices have different sizes
     * @throws NullPointerException if either of the specified matrices is null
     */
    private static Matrix performMatrixOperation(Matrix matrixA, Matrix matrixB, String operation) {
        checkIfMatricesAreNull(matrixA, matrixB);
        if (!sameSize(matrixA, matrixB)) {
            throw new IllegalArgumentException("The matrices must have same sizes.");
        }
        Matrix resultingMatrix = new Matrix(matrixA.rows(), matrixA.cols());
        for (int i = 0; i < resultingMatrix.rows(); i++) {
            for (int j = 0; j < resultingMatrix.cols(); j++) {
                resultingMatrix.setElement(i, j, computeElement(matrixA.getElement(i, j), matrixB.getElement(i, j), operation));
            }
        }
        return resultingMatrix;
    }

    /**
     * Performs the specified arithmetic operation (addition or subtraction)
     * using the specified terms, and returns the result.
     *
     * @param term1 the first term of the operation
     * @param term2 the second term of the operation
     * @param operation the specified operation; addition or subtraction
     * @return the result of the operation
     */
    private static double computeElement(double term1, double element2, String operation) {
        double newElement;
        if (operation.equals("addition")) {
            newElement = term1 + element2;
        } else {
            newElement = term1 - element2;
        }
        return newElement;
    }

    /**
     * Returns the product of two specified matrices.
     *
     * @param matrixA the first matrix of the product
     * @param matrixB the second matrix of the product
     * @return the product of the specified matrices
     * @throws IllegalArgumentException if the matrices are not multipliable
     * @throws NullPointerException if either parameter is null
     */
    public static Matrix multiply(Matrix matrixA, Matrix matrixB) {
        checkIfMatricesAreNull(matrixA, matrixB);
        if (!areMultipliable(matrixA, matrixB)) {
            throw new IllegalArgumentException("The inner dimensions must match.");
        }
        double[][] values = new double[matrixA.rows()][matrixB.cols()];
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
                for (int k = 0; k < matrixA.cols(); k++) {
                    values[i][j] += matrixA.getElement(i, k) * matrixB.getElement(k, j);
                }
            }
        }
        return new Matrix(values);
    }

    /**
     * Evaluated if the specified matrices are multipliable, that is that their
     * inner dimensions match.
     *
     * @param matrixA the first matrix of the product
     * @param matrixB the second matrix of the product
     * @return true if the matrices are multipliable; otherwise false
     * @throws NullPointerException is either parameter is null
     */
    public static boolean areMultipliable(Matrix matrixA, Matrix matrixB) {
        checkIfMatricesAreNull(matrixA, matrixB);
        return (matrixA.cols() == matrixB.rows());
    }

    /**
     * Calculates the determinant of the specified matrix.
     *
     * @param matrix the matrix for which the determinant is to be calculated
     * @return the determinant of the specified matrix
     * @throws IllegalArgumentException if the specified matrix is not a square
     * matrix
     * @throws NullPointerException if the specified matrix is null
     */
    public static double det(Matrix matrix) {
        Objects.requireNonNull(matrix, "matrix must not be null");
        if (!matrix.isSquareMatrix()) {
            throw new IllegalArgumentException("The matrix must be a square matrix.");
        }
        if (matrix.rows() == 1) {
            return matrix.getElement(0, 0);
        }
        double det = 0;
        for (int j = 0; j < matrix.rows(); j++) {
            det += matrix.getElement(0, j) * sign(j) * det(formSubmatrix(matrix, j));
        }
        return det;
    }

    /**
     * Returns the correct sign of the cofactor when expanding along the first
     * row.
     *
     * @param col the column number of the entry
     * @return 1 if the column number is even; -1 otherwise
     */
    private static int sign(int col) {
        return col % 2 == 0 ? 1 : -1;
    }

    /**
     * Returns the submatrix formed by deleting the first row and the specified
     * column of the specified matrix.
     *
     * @param matrix the specified matrix from which the submatrix is formed
     * @param deletedCol the column which is to be deleted from the specified
     * matrix
     * @return the submatrix obtained by deleting the first row and the
     * specified column of the specified matrix
     */
    private static Matrix formSubmatrix(Matrix matrix, int deletedCol) {
        int size = matrix.rows() - 1;
        Matrix submatrix = new Matrix(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int col = j;
                if (col >= deletedCol) {
                    col++;
                }
                submatrix.setElement(i, j, matrix.getElement(i + 1, col));
            }
        }
        return submatrix;
    }

    /**
     * Multiplies the specified square matrices using the Strassen algorithm.
     *
     * @param matrixA the first matrix
     * @param matrixB the second matrix
     * @return the product of the specified matrices
     * @throws IllegalArgumentException if the matrices are not square matrices
     * or if they are of different size
     * @throws NullPointerException if either parameter is null
     */
    public static Matrix strassenMultiply(Matrix matrixA, Matrix matrixB) {
        checkIfMatricesAreNull(matrixA, matrixB);
        if (!matrixA.isSquareMatrix() || !matrixB.isSquareMatrix()) {
            throw new IllegalArgumentException("The matrices must be square matrices");
        }
        if (!sameSize(matrixA, matrixB)) {
            throw new IllegalArgumentException("The matrices must be of same size.");
        }
        int n = matrixA.rows();
        if (n > 2) {
            int newOrder = nextPowerOfTwo(n);
            matrixA = createMatrixAugmentedWithZeros(matrixA, newOrder);
            matrixB = createMatrixAugmentedWithZeros(matrixB, newOrder);
        }
        Matrix matrixC = strassenRecursive(matrixA, matrixB);
        return extractMatrixFromAugmentedMatrix(matrixC, n);
    }

    /**
     * Recursively multiplies the specified matrices using the Strassen
     * algorithm.
     *
     * @param matrixA the first matrix
     * @param matrixB the second matrix
     * @return the product of the specified matrices
     */
    private static Matrix strassenRecursive(Matrix matrixA, Matrix matrixB) {
        int n = matrixA.rows();
        if (n == 1) {
            return multiply(matrixA, matrixB);
        }
        Matrix a11 = new Matrix(n / 2, n / 2);
        Matrix a12 = new Matrix(n / 2, n / 2);
        Matrix a21 = new Matrix(n / 2, n / 2);
        Matrix a22 = new Matrix(n / 2, n / 2);
        Matrix b11 = new Matrix(n / 2, n / 2);
        Matrix b12 = new Matrix(n / 2, n / 2);
        Matrix b21 = new Matrix(n / 2, n / 2);
        Matrix b22 = new Matrix(n / 2, n / 2);
        partition(matrixA, a11, a12, a21, a22);
        partition(matrixB, b11, b12, b21, b22);

        Matrix s1 = subtract(b12, b22);
        Matrix s2 = add(a11, a12);
        Matrix s3 = add(a21, a22);
        Matrix s4 = subtract(b21, b11);
        Matrix s5 = add(a11, a22);
        Matrix s6 = add(b11, b22);
        Matrix s7 = subtract(a12, a22);
        Matrix s8 = add(b21, b22);
        Matrix s9 = subtract(a11, a21);
        Matrix s10 = add(b11, b12);

        Matrix p1 = strassenRecursive(a11, s1);
        Matrix p2 = strassenRecursive(s2, b22);
        Matrix p3 = strassenRecursive(s3, b11);
        Matrix p4 = strassenRecursive(a22, s4);
        Matrix p5 = strassenRecursive(s5, s6);
        Matrix p6 = strassenRecursive(s7, s8);
        Matrix p7 = strassenRecursive(s9, s10);

        // c11 = p5 + p4 - p2 + p6
        Matrix c11 = add(add(p5, p6), subtract(p4, p2));
        Matrix c12 = add(p1, p2);
        Matrix c21 = add(p3, p4);
        // c22 = p5 + p1 -p3 -p7
        Matrix c22 = add(subtract(p5, p3), subtract(p1, p7));

        return joinSubmatrices(c11, c12, c21, c22);
    }

    /**
     * Divides the specified matrix into four specified matrices of equal size.
     *
     * @param matrixA the matrix to be divided
     * @param a11 the matrix corresponding to the upper left quadrant of the
     * matrix to be divided
     * @param a12 the matrix corresponding to the upper right quadrant of the
     * matrix to be divided
     * @param a21 the matrix corresponding to the lower left quadrant of the
     * matrix to be divided
     * @param a22 the matrix corresponding to the lower right quadrant of the
     * matrix to be divided
     */
    private static void partition(Matrix matrixA, Matrix a11, Matrix a12, Matrix a21, Matrix a22) {
        int n = matrixA.rows() / 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a11.setElement(i, j, matrixA.getElement(i, j));
                a12.setElement(i, j, matrixA.getElement(i, j + n));
                a21.setElement(i, j, matrixA.getElement(i + n, j));
                a22.setElement(i, j, matrixA.getElement(i + n, j + n));
            }
        }
    }

    /**
     * Combines four submatrices into one matrix.
     *
     * @param c11 the matrix corresponding to the upper left quadrant of the
     * joint matrix
     * @param c12 the matrix corresponding to the upper right quadrant of the
     * joint matrix
     * @param c21 the matrix corresponding to the lower left quadrant of the
     * joint matrix
     * @param c22 the matrix corresponding to the lower right quadrant of the
     * joint matrix
     * @return the matrix created by combining the four submatrices
     */
    private static Matrix joinSubmatrices(Matrix c11, Matrix c12, Matrix c21, Matrix c22) {
        int n = c11.rows();
        Matrix matrix = new Matrix(2 * n, 2 * n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix.setElement(i, j, c11.getElement(i, j));
                matrix.setElement(i, j + n, c12.getElement(i, j));
                matrix.setElement(i + n, j, c21.getElement(i, j));
                matrix.setElement(i + n, j + n, c22.getElement(i, j));
            }
        }
        return matrix;
    }

    /**
     * Returns the smallest power of two that is greater or equal to the
     * specified number.
     *
     * @param n the number to compare to the next power of two
     * @return the smallest power of two that is greater or equal to the
     * specified number
     */
    private static int nextPowerOfTwo(int n) {
        int powerOfTwo = 2;
        while (true) {
            if (powerOfTwo >= n) {
                return powerOfTwo;
            }
            powerOfTwo *= 2;
        }
    }

    /**
     * Creates an n x n matrix from a specified m * m matrix, where n is a power
     * of two. The specified matrix is augmented with rows and columns of zero
     * to create a matrix of the desired order.
     *
     * @param matrix the matrix which is to be augmented
     * @param n the order of the augmented matrix
     * @return the augmented matrix
     */
    private static Matrix createMatrixAugmentedWithZeros(Matrix matrix, int n) {
        if (matrix.rows() == n) {
            return matrix;
        }
        Matrix augmentedMatrix = new Matrix(n, n);
        for (int i = 0; i < matrix.rows(); i++) {
            for (int j = 0; j < matrix.rows(); j++) {
                augmentedMatrix.setElement(i, j, matrix.getElement(i, j));
            }
        }
        return augmentedMatrix;
    }

    /**
     * Extracts an n x n matrix from the specified matrix.
     *
     * @param augmentedMatrix the matrix from which the n x n matrix is
     * extracted
     * @param n the order of the desired matrix
     * @return the matrix extracted from the specified augmented matrix
     */
    private static Matrix extractMatrixFromAugmentedMatrix(Matrix augmentedMatrix, int n) {
        if (n == augmentedMatrix.rows()) {
            return augmentedMatrix;
        }
        Matrix matrix = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix.setElement(i, j, augmentedMatrix.getElement(i, j));
            }
        }
        return matrix;
    }

    /**
     * Returns the transpose of the specified matrix.
     *
     * @param matrix the matrix for which the transpose is to be formed
     * @return the transpose of the specified matrix
     * @throws NullPointerException if the specified matrix is null
     */
    public static Matrix transpose(Matrix matrix) {
        Objects.requireNonNull(matrix, "The matrix must not be null.");
        Matrix transpose = new Matrix(matrix.cols(), matrix.rows());
        for (int i = 0; i < transpose.rows(); i++) {
            for (int j = 0; j < transpose.cols(); j++) {
                transpose.setElement(i, j, matrix.getElement(j, i));
            }
        }
        return transpose;
    }
    

}
