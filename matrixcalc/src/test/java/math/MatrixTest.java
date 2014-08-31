package math;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author ydna
 */
public class MatrixTest {
    
    private final static double EPSILON = 1e-11;
    
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
        assertEquals(3, test.getRows());
        assertEquals(3, test.getCols());
    }
    
    @Test
    public void testZeroMatrixConstructor() {
        Matrix test = new Matrix(3, 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0.0, test.getArray()[i][j], EPSILON);
            }
        }
        assertEquals(3, test.getRows());
        assertEquals(3, test.getCols());
    }
    
    @Test
    public void testConstantMatrixConstructor() {
        Matrix test = new Matrix (3, 3, 3.14159);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(3.14159, test.getArray()[i][j], EPSILON);
            }
        }
        assertEquals(3, test.getRows());
        assertEquals(3, test.getCols());
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
        assertEquals(3, sum.getRows());
        assertEquals(3, sum.getCols());
    }
    
    @Test
    public void testMatrixSubstraction() {
        Matrix first = new Matrix(3, 3, 2.0);
        Matrix second = new Matrix(3, 3, 1.5);
        Matrix sum = first.subtract(second);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0.5, sum.getArray()[i][j], EPSILON);
            }
        }
        assertEquals(3, sum.getRows());
        assertEquals(3, sum.getCols());
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
        assertEquals(3, product.getRows());
        assertEquals(3, product.getCols());
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
    public void testDeterminant() {
        double[][] temp = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = i+j;
            }
        }
        Matrix test = new Matrix(temp);
        double a = temp[0][0];
        double b = temp[0][1];
        double c = temp[0][2];
        double d = temp[1][0];
        double e = temp[1][1];
        double f = temp[1][2];
        double g = temp[2][0];
        double h = temp[2][1];
        double i = temp[2][2];
        assertEquals(a*e*i+b*f*g+c*d*h-c*e*g-b*d*i-a*f*h, test.determinant(), EPSILON);
    }
    
    @Test
    public void testInverse() {
        double[][] temp = {{1,2},{3,4}};
        Matrix test = new Matrix(temp);
        Matrix inverse = test.inverse();
        assertEquals(-2, inverse.get(0, 0), EPSILON);
        assertEquals(1, inverse.getArray()[0][1], EPSILON);
        assertEquals(1.5, inverse.getArray()[1][0], EPSILON);
        assertEquals(-0.5, inverse.getArray()[1][1], EPSILON);
    }
    
}
