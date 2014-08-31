package com.mycompany.tiralabra_maven;

import junit.framework.TestCase;

public class PerformanceTest extends TestCase {
    MatrixCalculator mc;
    Matrix sm1;
    Matrix sm2;
    Matrix sm3;
    Matrix sm4;
    Matrix sm5;
    Matrix sm6;
    
    public PerformanceTest() {        
        mc = new MatrixCalculator();       
    }
    
    @Override
    protected void setUp() throws Exception {
        sm1 = new Matrix(50,50,0,10);
        sm2 = new Matrix(50,50,0,10);
        sm3 = new Matrix(200,200,0,10);
        sm4 = new Matrix(200,200,0,10);
        sm5 = new Matrix(2000,2000,0,10);
        sm6 = new Matrix(2000,2000,0,10);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testAdditionSpeedDecreasesWithMatrixSize() {
        long startTime1 = System.currentTimeMillis();
        sm1.add(sm2);
        long endTime1 = System.currentTimeMillis(); 
        long startTime2 = System.currentTimeMillis(); 
        sm5.add(sm6);
        long endTime2 = System.currentTimeMillis();
        System.out.println("Operaatioon 1 kului aikaa: " + (endTime1 - startTime1) + "ms."); 
        System.out.println("Operaatioon 2 kului aikaa: " + (endTime1 - startTime1) + "ms."); 
        long a = endTime1 - startTime1;
        long b = endTime2 - startTime2;
        assertTrue(a<b);
    }
    
    public void testMultiplicationSpeedDecreasesWithMatrixSize() {
        long startTime1 = System.currentTimeMillis();
        mc.StrassenMultiplication(sm1, sm2);
        long endTime1 = System.currentTimeMillis(); 
        long startTime2 = System.currentTimeMillis(); 
        mc.StrassenMultiplication(sm3, sm4);
        long endTime2 = System.currentTimeMillis();
        System.out.println("Operaatioon 1 kului aikaa: " + (endTime1 - startTime1) + "ms."); 
        System.out.println("Operaatioon 2 kului aikaa: " + (endTime2 - startTime2) + "ms."); 
        long a = endTime1 - startTime1;
        long b = endTime2 - startTime2;
        assertTrue(a<b);
    }
    
    public void testInversionSpeedDecreasesWithMatrixSize() {
        long startTime1 = System.currentTimeMillis();
        mc.inverse(sm1);
        long endTime1 = System.currentTimeMillis(); 
        long startTime2 = System.currentTimeMillis(); 
        mc.inverse(sm3);
        long endTime2 = System.currentTimeMillis();
        System.out.println("Operaatioon 1 kului aikaa: " + (endTime1 - startTime1) + "ms."); 
        System.out.println("Operaatioon 2 kului aikaa: " + (endTime2 - startTime2) + "ms."); 
        long a = endTime1 - startTime1;
        long b = endTime2 - startTime2;
        assertTrue(a<b);
    }
    
    public void testDeterminantSpeedDecreasesWithMatrixSize() {
        long startTime1 = System.currentTimeMillis();
        mc.determinant(sm1);
        long endTime1 = System.currentTimeMillis(); 
        long startTime2 = System.currentTimeMillis(); 
        mc.determinant(sm3);
        long endTime2 = System.currentTimeMillis();
        System.out.println("Operaatioon 1 kului aikaa: " + (endTime1 - startTime1) + "ms."); 
        System.out.println("Operaatioon 2 kului aikaa: " + (endTime2 - startTime2) + "ms."); 
        long a = endTime1 - startTime1;
        long b = endTime2 - startTime2;
        assertTrue(a<b);
    }
    
    public void testInversionTimeIsNotTooLongWithBiggerMatrix() {
        long startTime1 = System.currentTimeMillis();
        mc.inverse(sm1);
        long endTime1 = System.currentTimeMillis(); 
        long startTime2 = System.currentTimeMillis(); 
        mc.inverse(sm3);
        long endTime2 = System.currentTimeMillis();
        System.out.println("Operaatioon 1 kului aikaa: " + (endTime1 - startTime1) + "ms."); 
        System.out.println("Operaatioon 2 kului aikaa: " + (endTime2 - startTime2) + "ms."); 
        long a = endTime1 - startTime1;
        long b = endTime2 - startTime2;
        assertTrue((a*1000)>b || a==0);
    }
}
