package Matrix;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Matrix2x2Test {
    private Matrix2x2<Types.Integer> matrix;

    @Before
    public void setUp() {
        Types.Integer values[][] =                
            {{Types.Integer.ONE, Types.Integer.ONE},
             {Types.Integer.ONE, Types.Integer.ZERO}};

        matrix = new Matrix2x2<>(values);
    }

    @Test
    public void determinant() {
        assertEquals(matrix.determinant(), new Types.Integer(-1));
    }
}
