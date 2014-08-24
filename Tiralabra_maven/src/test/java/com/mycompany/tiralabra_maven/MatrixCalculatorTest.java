
package com.mycompany.tiralabra_maven;

import junit.framework.TestCase;

public class MatrixCalculatorTest extends TestCase {
    MatrixCalculator mc;
    Matrix sm1;
    Matrix sm2;
    Matrix sm3;
    
    public MatrixCalculatorTest() {
        mc = new MatrixCalculator();
    }
    
    @Override
    protected void setUp() throws Exception {
        sm1 = new Matrix(2, 2);
        sm2 = new Matrix(2, 2);  
        sm3 = new Matrix(1, 3); 
        sm1.setValue(0, 0, 1.0);
        sm1.setValue(0, 1, 2.0);
        sm1.setValue(1, 0, 3.0);
        sm1.setValue(1, 1, 4.0);
        sm2.setValue(0, 0, 4.0);
        sm2.setValue(0, 1, 3.0);
        sm2.setValue(1, 0, 2.0);
        sm2.setValue(1, 1, 1.0); 
        sm3.setValue(0, 0, -5.0);
        sm3.setValue(0, 1, -4.0);
        sm3.setValue(0, 2, -3.0);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAddition() {
        Matrix result = sm1.add(sm2);        
        assertTrue(result.get(0, 0) == 5.0 && result.get(0, 1) == 5.0 && result.get(1, 0) == 5.0 && result.get(1, 1) == 5.0);
    }
    public void testAdditionWithDifferentSizedMatrices() {
        Matrix sm3 = new Matrix(new double[][] {{1},{2},{3}});
        Matrix result = sm1.add(sm3);
        assertNull(result);
    }
    
    public void testSubstraction() {
        Matrix result = sm1.subtract(sm2);
        assertTrue(result.get(0, 0) == -3.0 && result.get(0, 1) == -1.0 && result.get(1, 0) == 1.0 && result.get(1, 1) == 3.0);
    }
    public void testSubstractionWithDifferentSizedMatrices() {
        Matrix sm3 = new Matrix(new double[][] {{1},{2},{3}});
        Matrix result = sm1.subtract(sm3);
        assertNull(result);
    }
    
    public void testMultiplication() {
        Matrix result = mc.StrassenMultiplication(sm1, sm2);
        assertTrue(result.get(0, 0) == 8.0 && result.get(0, 1) == 5.0 && result.get(1, 0) == 20.0 && result.get(1, 1) == 13.0);
    }
    
    public void testScalarMult() {
       Matrix result = sm1.multiplyByScalar(5.0);
       assertTrue(result.get(0, 0) == 5.0 && result.get(0, 1) == 10.0 && result.get(1, 0) == 15.0 && result.get(1, 1) == 20.0);
    }
    
    public void testScalarMultWithNegativeScalar() {
       Matrix result = sm1.multiplyByScalar(-3.0);
       assertTrue(result.get(0, 0) == -3.0 && result.get(0, 1) == -6.0 && result.get(1, 0) == -9.0 && result.get(1, 1) == -12.0);
    }
    
//    public void testInverse() {
//        double[][] invertibleMatrix = new double[][] {{2,3},{2,2}};
//        Matrix minv = new Matrix(invertibleMatrix);
//        Matrix result = mc.inverse(minv);
//        assertTrue(result.get(0,0) == -1.0 && result.get(0, 1) == 1.5 && result.get(1, 0) == 1.0 && result.get(1, 1) == -1.0);
//    }
    
    public void testTranspose() {
        Matrix result = sm1.transpose();
        assertTrue(result.get(0, 0) == 1.0 && result.get(0, 1) == 3.0 && result.get(1, 0) == 2.0 && result.get(1, 1) == 4.0);
    }    
    
    public void testTransposeWithNonSquareMatrixWithNegativeValues() {
        Matrix result = sm3.transpose();
        assertTrue(result.get(0, 0) == -5.0 && result.get(1, 0) == -4.0 && result.get(2, 0) == -3.0);
    } 
    
    public void testGaussJordan() {
        Matrix result = sm1;
        mc.GaussJordan(result);
        assertTrue(result.get(0, 0) == 1.0 && result.get(0, 1) == 0.0 && result.get(1, 0) == 0.0 && result.get(1, 1) == 1.0);
    }
}
