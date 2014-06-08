package TiraLabra.Matrix;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import TiraLabra.Number.Number;
import TiraLabra.Number.Integer;
import TiraLabra.Number.Integer32;

public class Matrix2x2Test {
    private Matrix2x2<Integer> matrix;
    
    private static <T extends Number<T>> Matrix2x2<T> makeMatrix(Class<T> type,
            int... values) {
        Number elements[][] = new Number[2][2];
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                elements[i][j] = Number.make(type, values[i*2 + j]);
            }
        }
        
        return new Matrix2x2(elements, type);
    }
    
    @Before
    public void setUp() {
        matrix = makeMatrix(Integer.class, 1, 1, 1, 0);
    }

    @Test
    public void determinant() {
        assertEquals(new Integer(-1), matrix.determinant());
    }
    
    @Test
    public void eigenvalues() {
        Matrix2x2<Integer32> m = makeMatrix(Integer32.class, 4, 1, 6, 3);
        Number eigenvalues[] = m.eigenvalues();
        assertEquals(new Integer32(1), eigenvalues[1]);
        assertEquals(new Integer32(6), eigenvalues[0]);
    }
}
