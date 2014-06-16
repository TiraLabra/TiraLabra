package logiikka;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TranspoosiTest {

    double[][] A;
    double[][] B;
    Transpoosi transpoosi;

    @Before
    public void setUp() {
        transpoosi = new Transpoosi();

        A = new double[][]{
            {0, 1},
            {2, 3},
            {4, 5}
        };

        B = new double[][]{
            {16, 22},
            {34, 49}
        };
    }

    @Test
    public void transpoosiToimii() {
        double[][] C = transpoosi.transpoosaa(A);
        double[][] D = new double[][]{
            {0, 2, 4},
            {1, 3, 5}
        };
        assertTrue(Arrays.deepEquals(C, D));
    }

    @Test
    public void transpoosiToimii2() {
        double[][] C = transpoosi.transpoosaa(A);
        double[][] D = new double[][]{
            {0, 2, 4},
            {1, 3, 99}
        };
        assertFalse(Arrays.deepEquals(C, D));
    }

    @Test
    public void transpoosiToimii3() {
        double[][] C = transpoosi.transpoosaa(B);
        double[][] D = new double[][]{
            {16, 34},
            {22, 49}
        };
        assertTrue(Arrays.deepEquals(C, D));
    }

    @Test
    public void transpoosiToimii4() {
        double[][] C = transpoosi.transpoosaa(A);
        double[][] D = transpoosi.transpoosaa(C);
        assertTrue(Arrays.deepEquals(A, D));
    }
}