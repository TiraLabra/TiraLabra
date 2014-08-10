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
}
