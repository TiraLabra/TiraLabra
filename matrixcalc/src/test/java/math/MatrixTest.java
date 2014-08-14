package math;

import junit.framework.TestCase;

public class MatrixTest extends TestCase {
    
    public MatrixTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

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
                assertEquals((double)(i+j), test.getArray()[i][j]);
            }
        }
        assertEquals(3, test.getRowDim());
        assertEquals(3, test.getColumnDim());
    }
    
    public void testZeroMatrixConstructor() {
        Matrix test = new Matrix(3, 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0.0, test.getArray()[i][j]);
            }
        }
        assertEquals(3, test.getRowDim());
        assertEquals(3, test.getColumnDim());
    }
    
    public void testConstantMatrixConstructor() {
        Matrix test = new Matrix (3, 3, 3.14159);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(3.14159, test.getArray()[i][j]);
            }
        }
        assertEquals(3, test.getRowDim());
        assertEquals(3, test.getColumnDim());
    }
    
    public void testMatrixAddition() {
        Matrix first = new Matrix(3, 3, 2.0);
        Matrix second = new Matrix(3, 3, 1.6);
        Matrix sum = first.plus(second);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(3.6, sum.getArray()[i][j]);
            }
        }
        assertEquals(3, sum.getRowDim());
        assertEquals(3, sum.getColumnDim());
    }
    
    public void testMatrixSubstraction() {
        Matrix first = new Matrix(3, 3, 2.0);
        Matrix second = new Matrix(3, 3, 1.5);
        Matrix sum = first.minus(second);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0.5, sum.getArray()[i][j]);
            }
        }
        assertEquals(3, sum.getRowDim());
        assertEquals(3, sum.getColumnDim());
    }
    
    public void testScalarMultiplication() {
        Matrix test = new Matrix(3, 3, 1.5);
        Matrix product = test.times(2.0);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(3.0, product.getArray()[i][j]);
            }
        }
        assertEquals(3, product.getRowDim());
        assertEquals(3, product.getColumnDim());
    }
    
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
                assertEquals((double)(i+j), test.getArray()[i][j]);
            }
        }
    }
    
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
        Matrix result = test.times(test);
        assertEquals((double)3*10, result.getArray()[0][0]);
        assertEquals((double)3*12, result.getArray()[0][1]);
        assertEquals((double)3*14, result.getArray()[0][2]);
        assertEquals((double)3*22, result.getArray()[1][0]);
        assertEquals((double)3*27, result.getArray()[1][1]);
        assertEquals((double)3*32, result.getArray()[1][2]);
        assertEquals((double)3*34, result.getArray()[2][0]);
        assertEquals((double)3*42, result.getArray()[2][1]);
        assertEquals((double)3*50, result.getArray()[2][2]);
    }
    
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
        assertEquals(1.0, test.getArray()[1][0]);
        assertEquals(2.0, test.getArray()[1][1]);
        assertEquals(3.0, test.getArray()[1][2]);
        assertEquals(4.0, test.getArray()[0][0]);
        assertEquals(5.0, test.getArray()[0][1]);
        assertEquals(6.0, test.getArray()[0][2]);
    }
    
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
        assertEquals(2.0, test.getArray()[0][0]);
        assertEquals(4.0, test.getArray()[0][1]);
        assertEquals(6.0, test.getArray()[0][2]);
    }
    
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
        assertEquals(5.0, test.getArray()[0][0]);
        assertEquals(7.0, test.getArray()[0][1]);
        assertEquals(9.0, test.getArray()[0][2]);
    }
    
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
        assertEquals(1.0, test.getArray()[0][0]);
        assertEquals(2.0, test.getArray()[0][1]);
        assertEquals(3.0, test.getArray()[0][2]);
        assertEquals(7.0, test.getArray()[1][0]);
        assertEquals(8.0, test.getArray()[1][1]);
        assertEquals(9.0, test.getArray()[1][2]);
    }
    
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
        assertEquals(1.0, test.getArray()[0][0]);
        assertEquals(3.0, test.getArray()[0][1]);
        assertEquals(4.0, test.getArray()[1][0]);
        assertEquals(6.0, test.getArray()[1][1]);
        assertEquals(7.0, test.getArray()[2][0]);
        assertEquals(9.0, test.getArray()[2][1]);
    }
    
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

