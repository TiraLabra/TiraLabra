package TiraLabra.Matrix;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import TiraLabra.Number.Integer;

public class Matrix2x2Test {
    private Matrix2x2 matrix;

    @Before
    public void setUp() {
        Integer values[][] =                
            {{Integer.ONE, Integer.ONE},
             {Integer.ONE, Integer.ZERO}};

        matrix = new Matrix2x2(values, Integer.class);
    }

    @Test
    public void determinant() {
        assertEquals(matrix.determinant(), new Integer(-1));
    }
}
