package Matrix;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Types.Impl.Integer;

public class MatrixTest {
    private Matrix matrix;

    @Before
    public void setUp() {
        Integer values[][] =                
            {{Integer.ONE, Integer.ONE},
             {Integer.ONE, Integer.ZERO}};

        matrix = new Matrix(values);
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
    
    private void matrixEquals(int... values) {
        if (values.length != (matrix.M * matrix.N)) {
            throw new IllegalArgumentException();
        }
        
        for (int i = 0; i < matrix.N; i++) {
            for (int j = 0; j < matrix.M; j++) {
                assertEquals(new Integer(values[i*matrix.M + j]),
                        matrix.get(i, j));
            }
        }
    }

    @Test
    public void multiplyScalar() {
        matrix = matrix.multiply(Integer.TEN);
        matrixEquals(10, 10, 10, 0);
    }
    
    @Test
    public void multiplyMatrix() {
        matrix = matrix.multiply(matrix);
        matrixEquals(2, 1, 1, 1);
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
    
    @Test(expected=UnsupportedOperationException.class)
    public void determinantOnNonSquareMatrix() {
        Matrix m = new Matrix(1, 2);
        m.determinant();
    }
    
    @Test
    public void identityMatrix() {
        matrix = Matrix.identity(2, 2, Integer.class);
        matrixEquals(1, 0, 0, 1);
    }
    
    @Test
    public void exponentiationNaive() {
        matrix = matrix.pow_naive(2);
        matrixEquals(2, 1, 1, 1);
    }
    
    @Test
    public void exponentiation() {
        matrix = matrix.pow(2);
        matrixEquals(2, 1, 1, 1);
    }
}
