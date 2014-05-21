

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
    
}
