
package com.mycompany.tiralabra_maven;

import junit.framework.TestCase;
import org.ejml.simple.SimpleMatrix;

public class MatrixCalculatorTest extends TestCase {
    MatrixCalculator mc;
    SimpleMatrix sm1;
    SimpleMatrix sm2;    
    
    public MatrixCalculatorTest() {
        mc = new MatrixCalculator();
    }
    
    @Override
    protected void setUp() throws Exception {
        sm1 = new SimpleMatrix(2, 2);
        sm2 = new SimpleMatrix(2, 2);        
        sm1.set(0, 0, 1.0);
        sm1.set(0, 1, 2.0);
        sm1.set(1, 0, 3.0);
        sm1.set(1, 1, 4.0);
        sm2.set(0, 0, 4.0);
        sm2.set(0, 1, 3.0);
        sm2.set(1, 0, 2.0);
        sm2.set(1, 1, 1.0);        
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAddition() {
        SimpleMatrix result = mc.add(sm1, sm2);        
        assertTrue(result.get(0, 0) == 5.0 && result.get(0, 1) == 5.0 && result.get(1, 0) == 5.0 && result.get(1, 1) == 5.0);
    }
    public void testAdditionWithDifferentSizedMatrices() {
        SimpleMatrix sm3 = new SimpleMatrix(new double[][] {{1},{2},{3}});
        SimpleMatrix result = mc.add(sm1, sm3);
        assertNull(result);
    }
    
    public void testSubstraction() {
        SimpleMatrix result = mc.substract(sm1, sm2);
        assertTrue(result.get(0, 0) == -3.0 && result.get(0, 1) == -1.0 && result.get(1, 0) == 1.0 && result.get(1, 1) == 3.0);
    }
    public void testSubstractionWithDifferentSizedMatrices() {
        SimpleMatrix sm3 = new SimpleMatrix(new double[][] {{1},{2},{3}});
        SimpleMatrix result = mc.substract(sm1, sm3);
        assertNull(result);
    }
    
    public void testMultiplication() {
        SimpleMatrix result = mc.multiply(sm1, sm2);
        assertTrue(result.get(0, 0) == 8.0 && result.get(0, 1) == 5.0 && result.get(1, 0) == 20.0 && result.get(1, 1) == 13.0);
    }
    
    public void testScalarMult() {
        SimpleMatrix result = mc.scalarMultiplication(sm1, 5.0);
        assertTrue(result.get(0, 0) == 5.0 && result.get(0, 1) == 10.0 && result.get(1, 0) == 15.0 && result.get(1, 1) == 20.0);
    }
    
    public void testInverse() {
        double[][] invertibleMatrix = new double[][] {{2,3},{2,2}};
        SimpleMatrix minv = new SimpleMatrix(invertibleMatrix);
        SimpleMatrix result = mc.inverse(minv);
        assertTrue(result.get(0,0) == -1.0 && result.get(0, 1) == 1.5 && result.get(1, 0) == 1.0 && result.get(1, 1) == -1.0);
    }
    
    public void testTranspose() {
        SimpleMatrix result = mc.transpose(sm1);
        assertTrue(result.get(0, 0) == 1.0 && result.get(0, 1) == 3.0 && result.get(1, 0) == 2.0 && result.get(1, 1) == 4.0);
    }    
}
