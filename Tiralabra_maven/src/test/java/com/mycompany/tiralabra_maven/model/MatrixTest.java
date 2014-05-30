/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gabriel
 */
public class MatrixTest {
    
    private Matrix matrix;
    
    @Before
    public void setUp() {
        double[][] values = {{1,2,3},{4,5,6}};
        matrix = new Matrix(values);
    }
    
    @Test
    public void correctNumberOfRowsReturned(){
        
        assertEquals("Rows: ", 2, matrix.rows());
    }   
    
    @Test
    public void correctNumberOfColumnsReturned(){
        assertEquals("Columns: ", 3, matrix.cols());
    }
    
    @Test
    public void correctNumberOfRowsReturnerAfterCreatingZeroMatrix(){
        matrix = new Matrix(7, 9);
        assertEquals("Rows: ", 7, matrix.rows());
    }
    
    @Test
    public void correctNumberOfColumnsReturnedAfterCreatingZeroMatrix(){
        matrix = new Matrix(7, 9);
        assertEquals("Columns: ", 9, matrix.cols());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void tryingToCreateMatrixWithNegativeNumberOfRowsCausesException(){
        matrix = new Matrix(-5, 2);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void tryingToCreateMatrixWithNegativeNumberOfColumnsCausesException(){
        matrix = new Matrix(5, -2);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void tryingToCreateMatrixWithZeroRowsCausesException(){
        matrix = new Matrix(0, 2);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void tryingToCreateMatrixWithZeroColumnsCausesException(){
        matrix = new Matrix(5, 0);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void insertingAtNegativeRowIndexCausesException(){
        matrix.setElement(-1, 0, 10);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void insertingAtRowIndexEqualToRowLengthCausesException(){
        matrix.setElement(matrix.rows(), 0, 10);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void insertingAtNegativeColumnIndexCausesException(){
        matrix.setElement(0, -1, 10);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void insertingAtColumnIndexEqualToColumnLengthCausesException(){
        matrix.setElement(0, matrix.cols(), 10);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void gettingElementAtNegativeRowIndexCausesException(){
        matrix.getElement(-1, 0);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void gettingElementAtRowIndexEqualToRowLengthCausesException(){
        matrix.getElement(matrix.rows(), 0);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void gettingElementAtNegativeColumnIndexCausesException(){
        matrix.getElement(0, -1);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void gettingElementAtColumnIndexEqualToColumnLengthCausesException(){
        matrix.getElement(0, matrix.cols());
    }
    
    @Test
    public void toStringIsCorrect(){
        String expected = "1\t2\t3\n4\t5\t6";
        assertEquals(expected, matrix.toString());
    }
    
    @Test
    public void twoMatricesWithTheSameElementsAreEqual(){
        double[][] values = {{1,2,3},{4,5,6}};
        Matrix matrixB = new Matrix(values);
        assertEquals(matrix, matrixB);
    }
    
    @Test
    public void isSquareMatrixReturnsTrueForSquareMatrix(){
        double[][] values = {{1,2},{3,4}};
        matrix = new Matrix(values);
        assertTrue(matrix.isSquareMatrix());
    }
    
    @Test
    public void isSquareMatrixReturnsFalseForNonSquareMatrix(){
        assertFalse(matrix.isSquareMatrix());
    }
}
