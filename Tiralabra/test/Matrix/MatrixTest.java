package Matrix;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MatrixTest {
    private Matrix<Types.Integer> matrix;

    @Before
    public void setUp() {
        Types.Integer values[][] =                
            {{Types.Integer.ONE, Types.Integer.ONE},
             {Types.Integer.ONE, Types.Integer.ZERO}};

        matrix = new Matrix<>(values);
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
    public void setAndGet() {
        matrix.set(0, 0, Types.Integer.TEN);
        assertEquals(matrix.get(0, 0), Types.Integer.TEN);
    }
    
    @Test
    public void multiplyScalar() {
        matrix = matrix.multiply(Types.Integer.TEN);
        
        assertEquals(matrix.get(0, 0), Types.Integer.TEN);
        assertEquals(matrix.get(0, 1), Types.Integer.TEN);
        assertEquals(matrix.get(1, 0), Types.Integer.TEN);
        assertEquals(matrix.get(1, 1), Types.Integer.ZERO);
    }
    
    @Test
    public void multiplyMatrix() {
        matrix = matrix.multiply(matrix);

        assertEquals(matrix.get(0, 0), new Types.Integer(2));
        assertEquals(matrix.get(0, 1), Types.Integer.ONE);
        assertEquals(matrix.get(1, 0), Types.Integer.ONE);
        assertEquals(matrix.get(1, 1), Types.Integer.ONE);
    }
    
    @Test
    public void multiplyEmptyMatrix() {
        Matrix<Types.Integer> a = new Matrix<>(3, 0);
        Matrix<Types.Integer> b = new Matrix<>(0, 3);
        
        Matrix<Types.Integer> ab = a.multiply(b);
        Matrix<Types.Integer> ba = b.multiply(a);
        
        assertEquals(ab.N, 3);
        assertEquals(ab.M, 3);
        
        assertEquals(ba.N, 0);
        assertEquals(ba.M, 0);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void determinantOnNonSquareMatrix() {
        Matrix<Types.Integer> m = new Matrix<>(1, 2);
        m.determinant();
    }
}
