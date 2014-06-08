package logiikka;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class YhteenlaskuTest {

    double[][] A;
    double[][] B;
    double[][] X;
    Yhteenlasku yhteenlasku;

    @Before
    public void setUp() {
        yhteenlasku = new Yhteenlasku();

        A = new double[][]{
            {0, 1},
            {2, 3},
            {4, 5}
        };

        B = new double[][]{
            {1, 1},
            {1, 1},
            {1, 1}
        };

        X = new double[][]{
            {-1, -1},
            {-1, -1},
            {-1, -1}
        };
    }

    @Test
    public void yhteenlaskuToimii() {
        double[][] C = yhteenlasku.summaa(A, B);
        double[][] D = new double[][]{
            {1, 2},
            {3, 4},
            {5, 6}
        };
        assertTrue(Arrays.deepEquals(C, D));
    }

    @Test
    public void yhteenlaskuToimii2() {
        double[][] C = yhteenlasku.summaa(A, B);
        double[][] D = new double[][]{
            {1, 2},
            {3, 4},
            {5, 99}
        };
        assertFalse(Arrays.deepEquals(C, D));
    }

    @Test
    public void yhteenlaskuToimii3() {
        double[][] C = yhteenlasku.summaa(A, X);
        double[][] D = new double[][]{
            {-1, 0},
            {1, 2},
            {3, 4}
        };
        assertTrue(Arrays.deepEquals(C, D));
    }

    @Test(expected = IllegalArgumentException.class)
    public void poikkeusToimii() {
        double[][] C = new double[4][2];
        yhteenlasku.summaa(A, C);
    }
}