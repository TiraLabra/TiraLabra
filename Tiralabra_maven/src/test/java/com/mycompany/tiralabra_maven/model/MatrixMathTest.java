

package com.mycompany.tiralabra_maven.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gabriel
 */
public class MatrixMathTest {
    
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
    
}
