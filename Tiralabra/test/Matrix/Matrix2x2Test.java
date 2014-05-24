package Matrix;

import Types.ASDInteger;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Matrix2x2Test {
    private Matrix2x2<ASDInteger> matrix;

    @Before
    public void setUp() {
        ASDInteger values[][] =                
            {{ASDInteger.ONE, ASDInteger.ONE},
             {ASDInteger.ONE, ASDInteger.ZERO}};

        matrix = new Matrix2x2<>(values);
    }

    @Test
    public void determinant() {
        assertEquals(matrix.determinant(), new ASDInteger(-1));
    }
}
