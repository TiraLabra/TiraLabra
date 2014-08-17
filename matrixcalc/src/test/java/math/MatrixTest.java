package math;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author ydna
 */
public class MatrixTest {
    
    private final static double EPSILON = Double.MIN_VALUE;
    
    @Test
    public void testArrayConstructor() {
        double[][] temp = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = i+j;
            }
        }
        Matrix test = new Matrix(temp);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals((double)(i+j), test.getArray()[i][j], EPSILON);
            }
        }
        assertEquals(3, test.getRowDim());
        assertEquals(3, test.getColumnDim());
    }
    
    @Test
    public void testZeroMatrixConstructor() {
        Matrix test = new Matrix(3, 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0.0, test.getArray()[i][j], EPSILON);
            }
        }
        assertEquals(3, test.getRowDim());
        assertEquals(3, test.getColumnDim());
    }
    
    @Test
    public void testConstantMatrixConstructor() {
        Matrix test = new Matrix (3, 3, 3.14159);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(3.14159, test.getArray()[i][j], EPSILON);
            }
        }
        assertEquals(3, test.getRowDim());
        assertEquals(3, test.getColumnDim());
    }
    
    @Test
    public void testMatrixAddition() {
        Matrix first = new Matrix(3, 3, 2.0);
        Matrix second = new Matrix(3, 3, 1.6);
        Matrix sum = first.add(second);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(3.6, sum.getArray()[i][j], EPSILON);
            }
        }
        assertEquals(3, sum.getRowDim());
        assertEquals(3, sum.getColumnDim());
    }
    
    @Test
    public void testMatrixSubstraction() {
        Matrix first = new Matrix(3, 3, 2.0);
        Matrix second = new Matrix(3, 3, 1.5);
        Matrix sum = first.substract(second);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0.5, sum.getArray()[i][j], EPSILON);
            }
        }
        assertEquals(3, sum.getRowDim());
        assertEquals(3, sum.getColumnDim());
    }
    
    @Test
    public void testScalarMultiplication() {
        Matrix test = new Matrix(3, 3, 1.5);
        Matrix product = test.multiply(2.0);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(3.0, product.getArray()[i][j], EPSILON);
            }
        }
        assertEquals(3, product.getRowDim());
        assertEquals(3, product.getColumnDim());
    }
    
    @Test
    public void testTranspose() {
        double[][] temp = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = i+j;
            }
        }
        Matrix test = new Matrix(temp);
        test = test.transpose();
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                assertEquals((double)(i+j), test.getArray()[i][j], EPSILON);
            }
        }
    }
    
    @Test
    public void testMatrixMultiplication() {
        double[][] temp = new double[3][3];
        double number = 1.0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = number;
                number += 1.0;
            }
        }
        Matrix test = new Matrix(temp);
        Matrix result = test.multiply(test);
        assertEquals((double)3*10, result.getArray()[0][0], EPSILON);
        assertEquals((double)3*12, result.getArray()[0][1], EPSILON);
        assertEquals((double)3*14, result.getArray()[0][2], EPSILON);
        assertEquals((double)3*22, result.getArray()[1][0], EPSILON);
        assertEquals((double)3*27, result.getArray()[1][1], EPSILON);
        assertEquals((double)3*32, result.getArray()[1][2], EPSILON);
        assertEquals((double)3*34, result.getArray()[2][0], EPSILON);
        assertEquals((double)3*42, result.getArray()[2][1], EPSILON);
        assertEquals((double)3*50, result.getArray()[2][2], EPSILON);
    }
    
    @Test
    public void testStrassen() {
        double[][] temp = new double[3][3];
        double number = 1.0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = number;
                number += 1.0;
            }
        }
        Matrix test = new Matrix(temp);
        Matrix result = test.strassenMultiply(test);
        assertEquals((double)3*10, result.getArray()[0][0], EPSILON);
        assertEquals((double)3*12, result.getArray()[0][1], EPSILON);
        assertEquals((double)3*14, result.getArray()[0][2], EPSILON);
        assertEquals((double)3*22, result.getArray()[1][0], EPSILON);
        assertEquals((double)3*27, result.getArray()[1][1], EPSILON);
        assertEquals((double)3*32, result.getArray()[1][2], EPSILON);
        assertEquals((double)3*34, result.getArray()[2][0], EPSILON);
        assertEquals((double)3*42, result.getArray()[2][1], EPSILON);
        assertEquals((double)3*50, result.getArray()[2][2], EPSILON);
    }
    
    @Test
    public void testRowSwitching() {
        double[][] temp = new double[3][3];
        double number = 1.0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = number;
                number += 1.0;
            }
        }
        Matrix test = new Matrix(temp);
        test.rowSwitch(0, 1);
        assertEquals(1.0, test.getArray()[1][0], EPSILON);
        assertEquals(2.0, test.getArray()[1][1], EPSILON);
        assertEquals(3.0, test.getArray()[1][2], EPSILON);
        assertEquals(4.0, test.getArray()[0][0], EPSILON);
        assertEquals(5.0, test.getArray()[0][1], EPSILON);
        assertEquals(6.0, test.getArray()[0][2], EPSILON);
    }
    
    @Test
    public void testRowMultiplication() {
        double[][] temp = new double[3][3];
        double number = 1.0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = number;
                number += 1.0;
            }
        }
        Matrix test = new Matrix(temp);
        test.rowMultiply(0, 2.0);
        assertEquals(2.0, test.getArray()[0][0], EPSILON);
        assertEquals(4.0, test.getArray()[0][1], EPSILON);
        assertEquals(6.0, test.getArray()[0][2], EPSILON);
    }
    
    @Test
    public void testRowAddition() {
        double[][] temp = new double[3][3];
        double number = 1.0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = number;
                number += 1.0;
            }
        }
        Matrix test = new Matrix(temp);
        test.rowAdd(0, 1);
        assertEquals(5.0, test.getArray()[0][0], EPSILON);
        assertEquals(7.0, test.getArray()[0][1], EPSILON);
        assertEquals(9.0, test.getArray()[0][2], EPSILON);
    }
    
    @Test
    public void testRowDelete() {
        double[][] temp = new double[3][3];
        double number = 1.0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = number;
                number += 1.0;
            }
        }
        Matrix test = new Matrix(temp);
        test = test.deleteRow(1);
        assertEquals(2, test.getRowDim());
        assertEquals(3, test.getColumnDim());
        assertEquals(1.0, test.getArray()[0][0], EPSILON);
        assertEquals(2.0, test.getArray()[0][1], EPSILON);
        assertEquals(3.0, test.getArray()[0][2], EPSILON);
        assertEquals(7.0, test.getArray()[1][0], EPSILON);
        assertEquals(8.0, test.getArray()[1][1], EPSILON);
        assertEquals(9.0, test.getArray()[1][2], EPSILON);
    }
    
    @Test
    public void testColumnDelete() {
        double[][] temp = new double[3][3];
        double number = 1.0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = number;
                number += 1.0;
            }
        }
        Matrix test = new Matrix(temp);
        test = test.deleteColumn(1);
        assertEquals(3, test.getRowDim());
        assertEquals(2, test.getColumnDim());
        assertEquals(1.0, test.getArray()[0][0], EPSILON);
        assertEquals(3.0, test.getArray()[0][1], EPSILON);
        assertEquals(4.0, test.getArray()[1][0], EPSILON);
        assertEquals(6.0, test.getArray()[1][1], EPSILON);
        assertEquals(7.0, test.getArray()[2][0], EPSILON);
        assertEquals(9.0, test.getArray()[2][1], EPSILON);
    }
    
    @Test
    public void testSubmatrix() {
        double[][] temp = new double[3][3];
        double number = 1.0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = number;
                number += 1.0;
            }
        }
        Matrix test = new Matrix(temp);
        boolean[] rows = {false, true, false};
        boolean[] cols = {false, true, false};
        test = test.submatrix(rows, cols);
        assertEquals(2, test.getRowDim());
        assertEquals(2, test.getColumnDim());
    }
}

