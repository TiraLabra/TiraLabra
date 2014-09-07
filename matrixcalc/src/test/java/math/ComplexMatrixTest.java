package math;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ydna
 */
public class ComplexMatrixTest {

    private final static double EPSILON = 1e-8;

    @Test
    public void testArrayConstructor() {
        Complex[][] temp = new Complex[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = new Complex(i + j, i - j);
            }
        }
        ComplexMatrix test = new ComplexMatrix(temp);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals((double) (i + j), test.get(i, j).re(), EPSILON);
                assertEquals((double) (i - j), test.get(i, j).im(), EPSILON);
            }
        }
        assertEquals(3, test.getRows());
        assertEquals(3, test.getCols());
    }

    @Test
    public void testZeroMatrixConstructor() {
        ComplexMatrix test = new ComplexMatrix(3, 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0.0, test.get(i, j).re(), EPSILON);
                assertEquals(0.0, test.get(i, j).im(), EPSILON);
            }
        }
        assertEquals(3, test.getRows());
        assertEquals(3, test.getCols());
    }

    @Test
    public void testConstantMatrixConstructor() {
        ComplexMatrix test = new ComplexMatrix(3, 3, new Complex(3.14, 2.71));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(3.14, test.get(i, j).re(), EPSILON);
                assertEquals(2.71, test.get(i, j).im(), EPSILON);
            }
        }
        assertEquals(3, test.getRows());
        assertEquals(3, test.getCols());
    }

    @Test
    public void testMatrixAddition() {
        ComplexMatrix first = new ComplexMatrix(3, 3, new Complex(2.0, -1.5));
        ComplexMatrix second = new ComplexMatrix(3, 3, new Complex(1.6, 2.4));
        ComplexMatrix sum = first.add(second);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(3.6, sum.get(i, j).re(), EPSILON);
                assertEquals(0.9, sum.get(i, j).im(), EPSILON);
            }
        }
        assertEquals(3, sum.getRows());
        assertEquals(3, sum.getCols());
    }

    @Test
    public void testMatrixSubstraction() {
        ComplexMatrix first = new ComplexMatrix(3, 3, new Complex(2.0, -1.8));
        ComplexMatrix second = new ComplexMatrix(3, 3, new Complex(1.5, 2.7));
        ComplexMatrix sum = first.subtract(second);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0.5, sum.get(i, j).re(), EPSILON);
                assertEquals(-4.5, sum.get(i, j).im(), EPSILON);
            }
        }
        assertEquals(3, sum.getRows());
        assertEquals(3, sum.getCols());
    }

    @Test
    public void testScalarMultiplication() {
        ComplexMatrix test = new ComplexMatrix(3, 3, new Complex(1.2, -0.8));
        ComplexMatrix product = test.multiply(2.0);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(2.4, product.get(i, j).re(), EPSILON);
                assertEquals(-1.6, product.get(i, j).im(), EPSILON);
            }
        }
        assertEquals(3, product.getRows());
        assertEquals(3, product.getCols());
    }

    @Test
    public void testTranspose() {
        Complex[][] temp = new Complex[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = new Complex(i + j, i - j);
            }
        }
        ComplexMatrix test = new ComplexMatrix(temp);
        test = test.transpose();
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                assertEquals((double) (i + j), test.get(i, j).re(), EPSILON);
                assertEquals((double) (j - i), test.get(i, j).im(), EPSILON);
            }
        }
    }

    @Test
    public void testMatrixMultiplication() {
        Complex[][] temp = new Complex[3][3];
        double number = 1.0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = new Complex(number, number);
                number += 1.0;
            }
        }
        ComplexMatrix test = new ComplexMatrix(temp);
        ComplexMatrix result = test.multiply(test);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0.0, result.get(i, j).re(), EPSILON);
            }
        }
        assertEquals((double) 6 * 10, result.getArray()[0][0].im(), EPSILON);
        assertEquals((double) 6 * 12, result.getArray()[0][1].im(), EPSILON);
        assertEquals((double) 6 * 14, result.getArray()[0][2].im(), EPSILON);
        assertEquals((double) 6 * 22, result.getArray()[1][0].im(), EPSILON);
        assertEquals((double) 6 * 27, result.getArray()[1][1].im(), EPSILON);
        assertEquals((double) 6 * 32, result.getArray()[1][2].im(), EPSILON);
        assertEquals((double) 6 * 34, result.getArray()[2][0].im(), EPSILON);
        assertEquals((double) 6 * 42, result.getArray()[2][1].im(), EPSILON);
        assertEquals((double) 6 * 50, result.getArray()[2][2].im(), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdditionExceptionThrown() {
        new ComplexMatrix(1, 2).add(new ComplexMatrix(3, 4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtractionExceptionThrown() {
        new ComplexMatrix(1, 2).subtract(new ComplexMatrix(3, 4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplicationExceptionThrown() {
        new ComplexMatrix(1, 2).multiply(new ComplexMatrix(3, 4));
    }

    @Test
    public void testDeterminant() {
        Complex[][] temp = new Complex[3][3];
        double number = 1.0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (number % 2 == 0) {
                    temp[i][j] = new Complex(number, number);
                } else {
                    temp[i][j] = new Complex(number, -number);
                }
                number += 1.0;
            }
        }
        ComplexMatrix test = new ComplexMatrix(temp);
        assertEquals(240.0, test.determinant().re(), EPSILON);
        assertEquals(240.0, test.determinant().im(), EPSILON);
        ComplexMatrix singular = new ComplexMatrix(3, 3);
        assertEquals(0.0, singular.determinant().re(), EPSILON);
        assertEquals(0.0, singular.determinant().im(), EPSILON);
    }

    @Test
    public void testInverse() {
        Complex[][] temp = {{new Complex(1, -1), new Complex(2, 2)}, {new Complex(3, 3), new Complex(4, -4)}};
        ComplexMatrix test = new ComplexMatrix(temp);
        ComplexMatrix inverse = test.inverse();
        assertEquals(0.2, inverse.get(0, 0).re(), EPSILON);
        assertEquals(0.2, inverse.get(0, 0).im(), EPSILON);
        assertEquals(0.1, inverse.get(0, 1).re(), EPSILON);
        assertEquals(-0.1, inverse.get(0, 1).im(), EPSILON);
        assertEquals(0.15, inverse.get(1, 0).re(), EPSILON);
        assertEquals(-0.15, inverse.get(1, 0).im(), EPSILON);
        assertEquals(0.05, inverse.get(1, 1).re(), EPSILON);
        assertEquals(0.05, inverse.get(1, 1).im(), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotSquareExceptionThrown() {
        ComplexMatrix notSquare = new ComplexMatrix(3, 2);
        notSquare.inverse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSingularExceptionThrown() {
        ComplexMatrix singular = new ComplexMatrix(3, 3);
        singular.inverse();
    }

    @Test
    public void testRandom() {
        ComplexMatrix matrix = ComplexMatrix.randomMatrix(10, 10, 0, 10);
        assertEquals(matrix.getRows(), 10);
        assertEquals(matrix.getCols(), 10);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertTrue(matrix.get(i, j).re() >= 0);
                assertTrue(matrix.get(i, j).re() <= 10);
                assertTrue(matrix.get(i, j).im() >= 0);
                assertTrue(matrix.get(i, j).im() <= 10);
            }
        }
    }

}
