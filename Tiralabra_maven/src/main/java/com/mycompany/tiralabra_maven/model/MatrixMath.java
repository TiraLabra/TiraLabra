package com.mycompany.tiralabra_maven.model;

import java.util.Objects;

/**
 * Provides functions for performing mathematical operations on matrices.
 *
 * @author gabriel
 */
public class MatrixMath {

    /**
     * Adds two matrices of the same size and returns the resulting matrix.
     *
     * @param matrixA the first matrix of the matrices to be added
     * @param matrixB the second matrix of the matrices added
     * @return the matrix resulting of the addition
     * @throws IllegalArgumentException if the matrices have different sizes
     * @throws NullPointerException if either parameter is null
     */
    public static Matrix add(Matrix matrixA, Matrix matrixB) {
        checkIfMatricesAreNull(matrixA, matrixB);
        if (!sameSize(matrixA, matrixB)){
            throw new IllegalArgumentException("The matrices must have same sizes.");
        }
        Matrix resultingMatrix = new Matrix(matrixA.rows(), matrixA.cols());
        for (int i = 0; i < resultingMatrix.rows(); i++) {
            for (int j = 0; j < resultingMatrix.cols(); j++) {
                resultingMatrix.setElement(matrixA.getElement(i, j) + matrixB.getElement(i, j), i, j);
            }
        }
        return resultingMatrix;
    }    

    /**
     * Checks if two matrices are of the same size.
     * @param matrixA the first matrix
     * @param matrixB the second matrix
     * @return true if the matrices are of the same size; false otherwise
     * @throws NullPointerException if either argument is null
     */
    public static boolean sameSize(Matrix matrixA, Matrix matrixB) {
        checkIfMatricesAreNull(matrixA, matrixB);
        return (matrixA.rows() == matrixB.rows() && matrixA.cols() == matrixB.cols());
    }
    
    /**
     * Takes two matrices and throws NullPointerException if either matrix is null
     * @param matrixA the first matrix
     * @param matrixB the second matrix
     * @throws NullPointerException if either argument is null
     */
    private static void checkIfMatricesAreNull(Matrix matrixA, Matrix matrixB) {
        Objects.requireNonNull(matrixA, "matrixA must not be null");
        Objects.requireNonNull(matrixB, "matrixB must not be null");
    }

}
