package math;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ydna
 */
public class RealMatrixTest {

    private final static double EPSILON = 1e-8;

    @Test
    public void testArrayConstructor() {
        double[][] temp = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = i + j;
            }
        }
        RealMatrix test = new RealMatrix(temp);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals((double) (i + j), test.get(i, j), EPSILON);
            }
        }
        assertEquals(3, test.getRows());
        assertEquals(3, test.getCols());
    }

    @Test
    public void testZeroMatrixConstructor() {
        RealMatrix test = new RealMatrix(3, 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0.0, test.get(i, j), EPSILON);
            }
        }
        assertEquals(3, test.getRows());
        assertEquals(3, test.getCols());
    }

    @Test
    public void testConstantMatrixConstructor() {
        RealMatrix test = new RealMatrix(3, 3, 3.14159);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(3.14159, test.get(i, j), EPSILON);
            }
        }
        assertEquals(3, test.getRows());
        assertEquals(3, test.getCols());
    }

    @Test
    public void testMatrixAddition() {
        RealMatrix first = new RealMatrix(3, 3, 2.0);
        RealMatrix second = new RealMatrix(3, 3, 1.6);
        RealMatrix sum = first.add(second);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(3.6, sum.get(i, j), EPSILON);
            }
        }
        assertEquals(3, sum.getRows());
        assertEquals(3, sum.getCols());
    }

    @Test
    public void testMatrixSubstraction() {
        RealMatrix first = new RealMatrix(3, 3, 2.0);
        RealMatrix second = new RealMatrix(3, 3, 1.5);
        RealMatrix sum = first.subtract(second);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0.5, sum.get(i, j), EPSILON);
            }
        }
        assertEquals(3, sum.getRows());
        assertEquals(3, sum.getCols());
    }

    @Test
    public void testScalarMultiplication() {
        RealMatrix test = new RealMatrix(3, 3, 1.5);
        RealMatrix product = test.multiply(2.0);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(3.0, product.get(i, j), EPSILON);
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
                temp[i][j] = i + j;
            }
        }
        RealMatrix test = new RealMatrix(temp);
        test = test.transpose();
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                assertEquals((double) (i + j), test.get(i, j), EPSILON);
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
        RealMatrix test = new RealMatrix(temp);
        RealMatrix result = test.multiply(test);
        assertEquals((double) 3 * 10, result.getArray()[0][0], EPSILON);
        assertEquals((double) 3 * 12, result.getArray()[0][1], EPSILON);
        assertEquals((double) 3 * 14, result.getArray()[0][2], EPSILON);
        assertEquals((double) 3 * 22, result.getArray()[1][0], EPSILON);
        assertEquals((double) 3 * 27, result.getArray()[1][1], EPSILON);
        assertEquals((double) 3 * 32, result.getArray()[1][2], EPSILON);
        assertEquals((double) 3 * 34, result.getArray()[2][0], EPSILON);
        assertEquals((double) 3 * 42, result.getArray()[2][1], EPSILON);
        assertEquals((double) 3 * 50, result.getArray()[2][2], EPSILON);
    }

    @Test
    public void testStrassen() {
        RealMatrix first = RealMatrix.randomMatrix(100, 100, -100, 100);
        RealMatrix second = RealMatrix.randomMatrix(100, 100, -100, 100);
        RealMatrix product = first.strassenMultiply(second);
        RealMatrix verify = first.multiply(second);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                assertEquals(verify.get(i, j), product.get(i, j), EPSILON);
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdditionExceptionThrown() {
        new RealMatrix(1, 2).add(new RealMatrix(3, 4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtractionExceptionThrown() {
        new RealMatrix(1, 2).subtract(new RealMatrix(3, 4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplicationExceptionThrown() {
        new RealMatrix(1, 2).multiply(new RealMatrix(3, 4));
    }

    @Test
    public void testDeterminant() {
        RealMatrix temp = RealMatrix.randomMatrix(3, 3, 0, 10);
        double a = temp.get(0, 0);
        double b = temp.get(0, 1);
        double c = temp.get(0, 2);
        double d = temp.get(1, 0);
        double e = temp.get(1, 1);
        double f = temp.get(1, 2);
        double g = temp.get(2, 0);
        double h = temp.get(2, 1);
        double i = temp.get(2, 2);
        assertEquals(a * e * i + b * f * g + c * d * h - c * e * g - b * d * i - a * f * h, temp.determinant(), EPSILON);
        RealMatrix singular = new RealMatrix(3, 3);
        assertEquals(0, singular.determinant(), EPSILON);
    }

    @Test
    public void testInverse() {
        double[][] temp = {{1, 2}, {3, 4}};
        RealMatrix test = new RealMatrix(temp);
        RealMatrix inverse = test.inverse();
        assertEquals(-2, inverse.get(0, 0), EPSILON);
        assertEquals(1, inverse.get(0, 1), EPSILON);
        assertEquals(1.5, inverse.get(1, 0), EPSILON);
        assertEquals(-0.5, inverse.get(1, 1), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotSquareExceptionThrown() {
        RealMatrix notSquare = new RealMatrix(3, 2);
        notSquare.inverse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSingularExceptionThrown() {
        RealMatrix singular = new RealMatrix(3, 3);
        singular.inverse();
    }

    @Test
    public void testRandom() {
        RealMatrix matrix = RealMatrix.randomMatrix(10, 10, 0, 10);
        assertEquals(matrix.getRows(), 10);
        assertEquals(matrix.getCols(), 10);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertTrue(matrix.get(i, j) >= 0);
                assertTrue(matrix.get(i, j) <= 10);
            }
        }
    }

}
