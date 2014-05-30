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

    @Test
    public void multiplyScalar() {
        matrix = matrix.multiply(Integer.TEN);
        
        assertEquals(matrix.get(0, 0), Integer.TEN);
        assertEquals(matrix.get(0, 1), Integer.TEN);
        assertEquals(matrix.get(1, 0), Integer.TEN);
        assertEquals(matrix.get(1, 1), Integer.ZERO);
    }
    
    @Test
    public void multiplyMatrix() {
        matrix = matrix.multiply(matrix);

        assertEquals(matrix.get(0, 0), new Integer(2));
        assertEquals(matrix.get(0, 1), Integer.ONE);
        assertEquals(matrix.get(1, 0), Integer.ONE);
        assertEquals(matrix.get(1, 1), Integer.ONE);
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
        Matrix m = Matrix.identity(2, 2, Integer.class);
        
        assertEquals(m.get(0, 0), Integer.ONE);
        assertEquals(m.get(0, 1), Integer.ZERO);
        assertEquals(m.get(1, 0), Integer.ZERO);
        assertEquals(m.get(1, 1), Integer.ONE);
    }
    
    @Test
    public void exponentiation() {
        matrix = matrix.pow(2);
        
        assertEquals(matrix.get(0, 0), new Integer(2));
        assertEquals(matrix.get(0, 1), Integer.ONE);
        assertEquals(matrix.get(1, 0), Integer.ONE);
        assertEquals(matrix.get(1, 1), Integer.ONE);
    }
}
