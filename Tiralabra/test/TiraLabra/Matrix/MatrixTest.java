package TiraLabra.Matrix;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import TiraLabra.Number.Real;
import TiraLabra.Number.Integer;
import TiraLabra.Number.Number;

public class MatrixTest {
    private Matrix matrix;

    @Before
    public void setUp() {
        Integer values[][] =                
            {{Integer.ONE, Integer.ONE},
             {Integer.ONE, Integer.ZERO}};

        matrix = new Matrix(values, Integer.class);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void getUnderI() {
        matrix.get(-1, 0);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void getUnderJ() {
        matrix.get(0, -1);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void getOverI() {
        matrix.get(3, 0);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void getOverJ() {
        matrix.get(0, 3);
    }
    
    private Matrix makeMatrix(Class<? extends Number> type,
            int n, int m, int... values) {
        Number elements[][] = new Number[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                elements[i][j] = Number.make(type, values[i*m + j]);
            }
        }
        
        return new Matrix(elements, type);
    }
    
    private void matrixEquals(Class<? extends Number> type, int... values) {
        assertEquals(values.length, matrix.M * matrix.N);
        
        for (int i = 0; i < matrix.N; i++) {
            for (int j = 0; j < matrix.M; j++) {
                assertEquals(Number.make(type, values[i*matrix.M + j]),
                        matrix.get(i, j));
            }
        }
    }

    @Test
    public void multiplyScalar() {
        matrix = matrix.multiply(Integer.TEN);
        matrixEquals(Integer.class, 10, 10, 10, 0);
    }
    
    @Test
    public void multiplyMatrix() {
        matrix = matrix.multiply(matrix);
        matrixEquals(Integer.class, 2, 1, 1, 1);
    }
    
    @Test
    public void multiplyEmptyMatrix() {
        Matrix a = new Matrix(3, 0);
        Matrix b = new Matrix(0, 3);
        
        Matrix ab = a.multiply(b);
        Matrix ba = b.multiply(a);
        
        assertEquals(ab.N, 3);
        assertEquals(ab.M, 3);
        
        assertEquals(ba.N, 0);
        assertEquals(ba.M, 0);
    }

    @Test
    public void identityMatrix() {
        matrix = Matrix.identity(2, Integer.class);
        matrixEquals(Integer.class, 1, 0, 0, 1);
    }
    
    @Test
    public void exponentiationNaive() {
        matrix = matrix.pow_naive(2);
        matrixEquals(Integer.class, 2, 1, 1, 1);
    }
    
    @Test
    public void exponentiation() {
        matrix = matrix.pow(2);
        matrixEquals(Integer.class, 2, 1, 1, 1);
    }
    
    @Test
    public void decimalExponentiation() {
        matrix = makeMatrix(Real.class, 2, 2, 1, 1, 1, 0).pow(2);
        matrixEquals(Real.class, 2, 1, 1, 1);
    }
    
    @Test
    public void submatrix() {
        matrix = matrix.submatrix(0, 0);
        matrixEquals(Integer.class, 0);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void determinantOnNonSquareMatrix() {
        Matrix m = new Matrix(1, 2);
        m.determinant();
    }
    
    @Test
    public void determinantOn1x1Matrix() {
        Matrix m = makeMatrix(Integer.class, 1, 1, 1);
        assertEquals(Integer.ONE, m.determinant());
    }
    
    @Test
    public void determinantOn2x2Matrix() {
        assertEquals(new Integer(-1), matrix.determinant());
    }
    
    @Test
    public void determinantOn4x4Matrix() {
        Matrix m = makeMatrix(Integer.class, 4, 4,
                1,2,3,4, 1,2,3,4, 1,2,3,4, 1,2,3,4);
        assertEquals(Integer.ZERO, m.determinant());
    }
    
    @Test
    public void addMatrices() {
        matrix = matrix.add(matrix);
        matrixEquals(Integer.class, 2, 2, 2, 0);
    }
    
    @Test
    public void transposeSquare() {
        matrix = matrix.transpose();
        matrixEquals(Integer.class, 1, 1, 1, 0);
    }
    
    @Test
    public void transposeNonSquare() {
        matrix = makeMatrix(Integer.class, 2, 3,
                1,2,3, 0,-6,7).transpose();
        matrixEquals(Integer.class, 1, 0, 2, -6, 3, 7);
    }
}
