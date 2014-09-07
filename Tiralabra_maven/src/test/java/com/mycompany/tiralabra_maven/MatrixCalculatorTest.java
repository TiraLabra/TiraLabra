
package com.mycompany.tiralabra_maven;

import junit.framework.TestCase;

public class MatrixCalculatorTest extends TestCase {
    MatrixCalculator mc;
    Matrix sm1;
    Matrix sm2;
    Matrix sm3;
    Matrix sm4;
    
    public MatrixCalculatorTest() {
        mc = new MatrixCalculator();
    }
    
    @Override
    protected void setUp() throws Exception {        
        sm1 = new Matrix(2, 2);
        sm2 = new Matrix(2, 2);  
        sm3 = new Matrix(1, 3); 
        sm4 = new Matrix(2, 3); 
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
        sm4.setValue(0, 0, 1.5);
        sm4.setValue(0, 1, -2.0);
        sm4.setValue(0, 2, 7.0);
        sm4.setValue(1, 0, 5.0);
        sm4.setValue(1, 1, -1.0);
        sm4.setValue(1, 2, -7.0);
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
    public void testMultiplicationWithDifferentSizedMatrices() {
        Matrix result = mc.StrassenMultiplication(sm1, sm4);
        assertTrue(result.get(0, 0) == 11.5 && result.get(0, 1) == -4.0 && result.get(0, 2) == -7.0 && result.get(1, 0) == 24.5 && result.get(1, 1) == -10.0 && result.get(1, 2) == -7.0);
    }
    
    public void testScalarMult() {
       Matrix result = sm1.multiplyByScalar(5.0);
       assertTrue(result.get(0, 0) == 5.0 && result.get(0, 1) == 10.0 && result.get(1, 0) == 15.0 && result.get(1, 1) == 20.0);
    }
    public void testScalarMultWithNonSquareMatrix() {
       Matrix result = sm4.multiplyByScalar(5.0);
       assertTrue(result.get(0, 0) == 7.5 && result.get(0, 1) == -10.0 && result.get(0, 2) == 35.0 && result.get(1, 0) == 25.0 && result.get(1, 1) == -5.0 && result.get(1, 2) == -35.0);
    }
    
    public void testScalarMultWithNegativeScalar() {
       Matrix result = sm1.multiplyByScalar(-3.0);
       assertTrue(result.get(0, 0) == -3.0 && result.get(0, 1) == -6.0 && result.get(1, 0) == -9.0 && result.get(1, 1) == -12.0);
    }
    
    public void testInverse() {
        double[][] nonSingularMatrix = new double[][] {{2,3},{2,2}};
        Matrix minv = new Matrix(nonSingularMatrix);
        Matrix result = mc.inverse(minv);
        assertTrue(result.get(0,0) == -1.0 && result.get(0, 1) == 1.5 && result.get(1, 0) == 1.0 && result.get(1, 1) == -1.0);
    }
    
    public void testTranspose() {
        Matrix result = sm1.transpose();
        assertTrue(result.get(0, 0) == 1.0 && result.get(0, 1) == 3.0 && result.get(1, 0) == 2.0 && result.get(1, 1) == 4.0);
    }    
    public void testTransposeWithNonSquareMatrix() {
        Matrix result = sm3.transpose();
        assertTrue(result.get(0, 0) == -5.0 && result.get(1, 0) == -4.0 && result.get(2, 0) == -3.0);
    } 
    
    public void testGaussJordan() {
        Matrix result = sm1;
        mc.GaussJordan(result);
        assertTrue(result.get(0, 0) == 1.0 && result.get(0, 1) == 0.0 && result.get(1, 0) == 0.0 && result.get(1, 1) == 1.0);
    }
    public void testGaussJordanWithNonSquareMatrix() {
        Matrix result = sm3;
        mc.GaussJordan(result);
        assertTrue(result.get(0, 0) == 1.0 && result.get(0, 1) == 0.8 && result.get(0, 2) == 0.6);
    }
    
    public void testDeterminant() {
        double det = mc.determinant(sm2);
        assertTrue(det == -2.0);
    }    
    public void testDeterminantWithLargerMatrix() {
        double[][] matrix = {{1,3,5,7,8,10},{1,1,1,1,1,2},{3.5,1,2,3,4,5},{10,10,20,5,5,5},{-1,-2,-3,-4,-5,-6},{15,-15,1,-1,2,-2}};
        Matrix m = new Matrix(matrix);
        double det = mc.determinant(m);
        assertEquals(-885.0, det, 0.01);
    }
}
