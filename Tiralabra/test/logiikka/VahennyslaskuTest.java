package logiikka;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VahennyslaskuTest {

    double[][] A;
    double[][] B;
    double[][] X;
    Vahennyslasku vahennyslasku;

    @Before
    public void setUp() {
        vahennyslasku = new Vahennyslasku();

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
    public void vahennyslaskuToimii() {
        double[][] C = vahennyslasku.vahenna(A, B);
        double[][] D = new double[][]{
            {-1, 0},
            {1, 2},
            {3, 4}
        };
        assertTrue(Arrays.deepEquals(C, D));
    }

    @Test
    public void vahennyslaskuToimii2() {
        double[][] C = vahennyslasku.vahenna(A, B);
        double[][] D = new double[][]{
            {-1, 0},
            {1, 2},
            {3, 99}
        };
        assertFalse(Arrays.deepEquals(C, D));
    }

    @Test
    public void vahennyslaskuToimii3() {
        double[][] C = vahennyslasku.vahenna(A, X);
        double[][] D = new double[][]{
            {1, 2},
            {3, 4},
            {5, 6}
        };
        assertTrue(Arrays.deepEquals(C, D));
    }

    @Test(expected = IllegalArgumentException.class)
    public void poikkeusToimii() {
        double[][] C = new double[4][2];
        vahennyslasku.vahenna(A, C);
    }
}