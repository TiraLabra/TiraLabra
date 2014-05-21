
package com.mycompany.tiralabra_maven.model;

import java.util.Objects;

/**
 * Provides functions for performing mathematical operations on matrices.
 * @author gabriel
 */
public class MatrixMath {
    
    /**
     * Adds two matrices of the same dimensions and returns the resulting matrix.
     * @param matrixA the first matrix of the matrices to be added
     * @param matrixB the second matrix of the matrices added
     * @return the matrix resulting of the addition
     * @throws IllegalStateException if either parameter is null
     */
    public static Matrix add(Matrix matrixA, Matrix matrixB){
        requireNonNull(matrixB);
        requireNonNull(matrixB);
        Matrix resultingMatrix = new Matrix(matrixA.rows(), matrixA.cols());
        for (int i = 0; i < resultingMatrix.rows(); i++){
            for (int j = 0; j < resultingMatrix.cols(); j++){
                resultingMatrix.setElement(matrixA.getElement(i, j)+matrixB.getElement(i, j), i, j);
            }
        }
        return resultingMatrix;
    }
    
    /**
     * Throws NullPointerException if the specified object is null.
     * @param arg the object to be tested
     */
    private static void requireNonNull(Object arg) {
      if (arg == null) throw new NullPointerException("Argument must not be null.");
};

}
