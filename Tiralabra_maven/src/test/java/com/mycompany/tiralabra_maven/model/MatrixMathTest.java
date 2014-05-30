

package com.mycompany.tiralabra_maven.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gabriel
 */
public class MatrixMathTest {
    
    private static final double DELTA = 0.001;
    
    private Matrix matrixA;
    private Matrix matrixB;
    
    @Before
    public void setUp() {
        double[][] valuesA = {{1,1},{1,1}};
        double[][] valuesB = {{2,2},{2,2}};
        matrixA = new Matrix(valuesA);
        matrixB = new Matrix(valuesB);
    }
    
    @Test 
    public void additionWorks(){       
        double[][] valuesC = {{3,3},{3,3}};
        Matrix matrixC = new Matrix(valuesC);
        
        assertEquals(matrixC, MatrixMath.add(matrixA, matrixB));
        
    }
    
    @Test (expected = NullPointerException.class)
    public void addingWhenFirstMatrixIsNullLeadsToException(){
        matrixA = null;
        MatrixMath.add(matrixA, matrixB);
    }
    
    @Test (expected = NullPointerException.class)
    public void addingWhenSecondMatrixIsNullLeadsToException(){
        matrixB = null;
        MatrixMath.add(matrixA, matrixB);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void addingMatricesOfDifferentSizeLeadsToException(){
        matrixB = new Matrix(3,2);
        MatrixMath.add(matrixA, matrixB);
    }
    
    @Test
    public void subtractionWorks(){
        double[][] valuesC = {{-1,-1},{-1,-1}};
        Matrix matrixC = new Matrix(valuesC);
        
        assertEquals(matrixC, MatrixMath.subtract(matrixA, matrixB));
    }
    
    @Test (expected = NullPointerException.class)
    public void subtractingWhenFirstMatrixIsNullLeadsToException(){
        matrixA = null;
        MatrixMath.subtract(matrixA, matrixB);
    }
    
    @Test (expected = NullPointerException.class)
    public void subtractingWhenSecondMatrixIsNullLeadsToException(){
        matrixB = null;
        MatrixMath.subtract(matrixA, matrixB);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void subtractingMatricesOfDifferentSizeLeadsToException(){
        matrixB = new Matrix(3,2);
        MatrixMath.subtract(matrixA, matrixB);
    }
    
    @Test
    public void multiplicatonOfSquareMatricesWorks(){
        double[][] values = {{4,4},{4,4}};
        Matrix matrixC = new Matrix(values);
        assertEquals(matrixC, MatrixMath.multiply(matrixA, matrixB));
    }
    
    @Test
    public void multiplicatonOfNonSquareMatricesWorks(){
        double[][] valuesA = {{1,2,3},{4,5,6}};
        double[][] valuesB = {{6},{7},{8}};
        double[][] valuesC = {{44},{107}};
        matrixA = new Matrix(valuesA);
        matrixB = new Matrix(valuesB);
        Matrix matrixC = new Matrix(valuesC);
        
        assertEquals(matrixC, MatrixMath.multiply(matrixA, matrixB));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void multiplyingLeadsToExceptionWhenInnerDimensionsMismatch(){
        matrixA = new Matrix(2,3);
        matrixB = new Matrix(2,3);
        MatrixMath.multiply(matrixA, matrixB);
    }
    
    @Test (expected = NullPointerException.class)
    public void multiplyingWhenFirstMatrixIsNullLeadsToException(){
        matrixA = null;
        MatrixMath.multiply(matrixA, matrixB);
    }
    
    @Test (expected = NullPointerException.class)
    public void multiplyingWhenSecondMatrixIsNullLeadsToException(){
        matrixB = null;
        MatrixMath.multiply(matrixA, matrixB);
    }
    
    @Test
    public void determinantCorrectlyCalculated(){
        double[][] values = {{1,2,3,0},{0,1,0,-1},{0,0,-1,0},{2,1,-4,-1}};
        matrixA = new Matrix(values);
        assertEquals(4, MatrixMath.det(matrixA), DELTA);
    }
    
    @Test 
    public void determinantOfOneByOneMatrixIsCorrect(){
        double[][] values = {{666}};
        matrixA = new Matrix(values);
        assertEquals(666, MatrixMath.det(matrixA), DELTA);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void determinantOfNonSquareMatrixLeadsToException(){
        double[][] values = {{1},{2}};
        matrixA = new Matrix(values);
        MatrixMath.det(matrixA);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void exceptionIsThrownIfTryingToStrassenMultiplyAndFirstMatrixIsNonSquare(){
        double[][] values = {{1,2},{3,4},{5,6}};
        matrixA = new Matrix(values);
        MatrixMath.strassenMultiply(matrixA, matrixB);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void exceptionIsThrownIfTryingToStrassenMultiplyAndSecondMatrixIsNonSquare(){
        double[][] values = {{1,2},{3,4},{5,6}};
        matrixB = new Matrix(values);
        MatrixMath.strassenMultiply(matrixA, matrixB);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void strassenMultiplicationWhenMatricesAreOfUnequlSizeLeadsToException(){
        double[][] values = {{1,2,3},{3,4,5},{5,6,7}};
        matrixB = new Matrix(values);
        MatrixMath.strassenMultiply(matrixA, matrixB);
    }
       
    @Test
    public void strassenMultiplicationProducesTheCorrectResult(){
        double[][] values = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        double[][] valuesExpected = {{90,100,110,120},{202,228,254,280},{314,356,398,440},{426,484,542,600}};
        matrixA = new Matrix(values);
        matrixB = new Matrix(values);
        Matrix expectedMatrix = new Matrix(valuesExpected);
        assertEquals(expectedMatrix, MatrixMath.strassenMultiply(matrixA, matrixB));
    }
    
    @Test
    public void strassenMultiplicationWorksWithMatricesWhereNisNotPowerOfTwo(){
        double[][] valuesA = {{1,5,-2},{2,3,-4},{1,4,2}};
        double[][] valuesB = {{3,-2,2},{5,-1,-2},{5,5,0}};
        double[][] valuesExpected = {{18,-17,-8},{1,-27,-2},{33,4,-6}};
        matrixA = new Matrix(valuesA);
        matrixB = new Matrix(valuesB);
        Matrix expectedMatrix = new Matrix(valuesExpected);
        assertEquals(expectedMatrix, MatrixMath.strassenMultiply(matrixA, matrixB));
    }
}
