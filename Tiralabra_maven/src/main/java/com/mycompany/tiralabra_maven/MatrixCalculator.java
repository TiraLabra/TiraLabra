
package com.mycompany.tiralabra_maven;

import org.ejml.simple.SimpleMatrix;

public class MatrixCalculator {
    
    /**
     * Algorithm for matrix addition to be done.     
     */
    public SimpleMatrix add(SimpleMatrix a, SimpleMatrix b) {
        if (a.numCols() != b.numCols() || a.numRows() != b.numRows()) {
            return null;
        }
        SimpleMatrix c = a.plus(b);
        return c;
    }
    
    /**
     * Algorithm for matrix substraction to be done.     
     */
    public SimpleMatrix substract(SimpleMatrix a, SimpleMatrix b) {
        if (a.numCols() != b.numCols() || a.numRows() != b.numRows()) {
            return null;
        }
        SimpleMatrix c = a.minus(b);
        return c;
    }
    
    /**
     * Algorithm for matrix multiplication to be done.     
     */
    public SimpleMatrix multiply(SimpleMatrix a, SimpleMatrix b) {
        if (a.numCols() != b.numRows()) {
            return null;
        }
        SimpleMatrix c = a.mult(b);
        return c;
    }
    
    /**
     * Algorithm for calculating the determinant of a matrix to be done.     
     */
    public Double getDeterminant(SimpleMatrix a) {
        return a.determinant();
    }
    
    /**
     * Algorithm for inverting a matrix to be done.     
     */
    public SimpleMatrix inverse(SimpleMatrix a) {
        SimpleMatrix c = a.invert();
        return c;
    }
    
    /**
     * Algorithm for matrix transposition to be done.     
     */
    public SimpleMatrix transpose(SimpleMatrix a) {
        SimpleMatrix c = a.transpose();
        return c;
    }
    
    /**
     * Algorithm for multiplying a matrix with a scalar to be done.     
     */
    public SimpleMatrix scalarMultiplication(SimpleMatrix a, Double b) {
        SimpleMatrix c = a.scale(b);
        return c;
    }    
}
