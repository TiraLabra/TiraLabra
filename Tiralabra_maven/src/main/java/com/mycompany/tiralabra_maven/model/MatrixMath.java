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
     * Takes two matrices and throws NullPointerException if either matrix is null
     * @param matrixA the first matrix
     * @param matrixB the second matrix
     * @throws NullPointerException if either argument is null
     */
    private static void checkIfMatricesAreNull(Matrix matrixA, Matrix matrixB) {
        Objects.requireNonNull(matrixA, "matrixA must not be null");
        Objects.requireNonNull(matrixB, "matrixB must not be null");
    }

    /**
     * Performs the specified matrix operation using the two specified matrices and returns the resulting matrix.
     * @param matrixA the first matrix to be used in the specified operation
     * @param matrixB the second matrix to be used in the specified operation
     * @param operation the specified operation; either addition or subtraction
     * @return the matrix resulting of the operation
     * @throws IllegalArgumentException if the matrices have different sizes
     * @throws NullPointerException if either of the specified matrices is null
     */
    private static Matrix performMatrixOperation(Matrix matrixA, Matrix matrixB, String operation) {
        checkIfMatricesAreNull(matrixA, matrixB);
        if (!sameSize(matrixA, matrixB)){
            throw new IllegalArgumentException("The matrices must have same sizes.");
        }
        Matrix resultingMatrix = new Matrix(matrixA.rows(), matrixA.cols());
        for (int i = 0; i < resultingMatrix.rows(); i++) {
            for (int j = 0; j < resultingMatrix.cols(); j++) {
                resultingMatrix.setElement(computeElement(matrixA.getElement(i, j), matrixB.getElement(i, j), operation), i, j);
            }
        }
        return resultingMatrix;
    }
    
    /**
     * Performs the specified arithmetic operation (addition or subtraction) using the specified terms, and returns the result.
     * @param term1 the first term of the operation
     * @param term2 the second term of the operation
     * @param operation the specified operation; addition or subtraction
     * @return the result of the operation
     * @throws IllegalArgumentException if the matrices are not multipliable
     * @throws NullPointerException if either parameter is null
     */
    private static double computeElement(double term1, double element2, String operation) {
        double newElement;
        if (operation.equals("addition")){
            newElement = term1 + element2;
        }
        else{
            newElement = term1 - element2; 
        }
        return newElement;
    }

    /**
     * Returns the product of two specified matrices.
     * @param matrixA the first matrix of the product
     * @param matrixB the second matrix of the product
     * @return the product of the specified matrices
     */
    public static Matrix multiply(Matrix matrixA, Matrix matrixB) {
        checkIfMatricesAreNull(matrixA, matrixB);
        if (!areMultipliable(matrixA, matrixB)){
            throw new IllegalArgumentException("The inner dimensions must match.");
        }
        double[][] values = new double[matrixA.rows()][matrixB.cols()];
        for (int i = 0; i < values.length; i++){
            for (int j = 0; j < values[0].length; j++){
                for (int k = 0; k < matrixA.cols(); k++){
                    values[i][j] += matrixA.getElement(i, k)*matrixB.getElement(k, j);
                }
            }
        }
        return new Matrix(values);
    }           

    /**
     * Evaluated if the specified matrices are multipliable, that is that their inner dimensions match.
     * @param matrixA the first matrix of the product
     * @param matrixB the second matrix of the product
     * @return true if the matrices are multipliable; otherwise false
     * @throws NullPointerException is either parameter is null
     */
    public static boolean areMultipliable(Matrix matrixA, Matrix matrixB) {
        checkIfMatricesAreNull(matrixA, matrixB);
        return (matrixA.cols() == matrixB.rows());
    }
}
