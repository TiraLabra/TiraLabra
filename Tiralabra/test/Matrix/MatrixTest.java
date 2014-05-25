package Matrix;

import Types.Integer;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MatrixTest {
    private Matrix<Integer> matrix;

    @Before
    public void setUp() {
        Integer values[][] =                
            {{Integer.ONE, Integer.ONE},
             {Integer.ONE, Integer.ZERO}};

        matrix = new Matrix<>(values);
    }

    @Test
    public void getUnder() {
        assertNull(matrix.get(-1, 0));
        assertNull(matrix.get(0, -1));
    }
    
    @Test
    public void getOver() {
        assertNull(matrix.get(3, 0));
        assertNull(matrix.get(0, 3));
    }
    
    @Test
    public void setAndGet() {
        matrix.set(0, 0, Integer.TEN);
        assertEquals(matrix.get(0, 0), Integer.TEN);
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
        
        Integer two = Integer.ONE.add(Integer.ONE);
        
        assertEquals(matrix.get(0, 0), two);
        assertEquals(matrix.get(0, 1), Integer.ONE);
        assertEquals(matrix.get(1, 0), Integer.ONE);
        assertEquals(matrix.get(1, 1), Integer.ONE);
    }
    
    @Test
    public void emptyMatrix() {
        Matrix<Integer> a = new Matrix<>(3, 0);
        Matrix<Integer> b = new Matrix<>(0, 3);
        
        Matrix<Integer> ab = a.multiply(b);
        Matrix<Integer> ba = b.multiply(a);
        
        assertEquals(ab.N, 3);
        assertEquals(ab.M, 3);
        
        assertEquals(ba.N, 0);
        assertEquals(ba.M, 0);
    }
}
