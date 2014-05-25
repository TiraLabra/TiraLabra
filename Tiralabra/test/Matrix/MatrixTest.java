package Matrix;

import Types.ASDInteger;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MatrixTest {
    private Matrix<ASDInteger> matrix;

    @Before
    public void setUp() {
        ASDInteger values[][] =                
            {{ASDInteger.ONE, ASDInteger.ONE},
             {ASDInteger.ONE, ASDInteger.ZERO}};

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
        matrix.set(0, 0, ASDInteger.TEN);
        assertEquals(matrix.get(0, 0), ASDInteger.TEN);
    }
    
    @Test
    public void multiplyScalar() {
        matrix = matrix.multiply(ASDInteger.TEN);
        
        assertEquals(matrix.get(0, 0), ASDInteger.TEN);
        assertEquals(matrix.get(0, 1), ASDInteger.TEN);
        assertEquals(matrix.get(1, 0), ASDInteger.TEN);
        assertEquals(matrix.get(1, 1), ASDInteger.ZERO);
    }
    
    @Test
    public void multiplyMatrix() {
        matrix = matrix.multiply(matrix);
        
        ASDInteger two = ASDInteger.ONE.add(ASDInteger.ONE);
        
        assertEquals(matrix.get(0, 0), two);
        assertEquals(matrix.get(0, 1), ASDInteger.ONE);
        assertEquals(matrix.get(1, 0), ASDInteger.ONE);
        assertEquals(matrix.get(1, 1), ASDInteger.ONE);
    }
    
    @Test
    public void emptyMatrix() {
        Matrix<ASDInteger> a = new Matrix<>(3, 0);
        Matrix<ASDInteger> b = new Matrix<>(0, 3);
        
        Matrix<ASDInteger> ab = a.multiply(b);
        Matrix<ASDInteger> ba = b.multiply(a);
        
        assertEquals(ab.N, 3);
        assertEquals(ab.M, 3);
        
        assertEquals(ba.N, 0);
        assertEquals(ba.M, 0);
    }
}
