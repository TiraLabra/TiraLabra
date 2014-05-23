package logiikka;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KertolaskuTest {

    double[][] A;
    double[][] B;
    double[][] X;
    Kertolasku kertolasku;

    @Before
    public void setUp() {
        kertolasku = new Kertolasku();

        A = new double[][]{
            {0, 1},
            {2, 3},
            {4, 5}
        };

        B = new double[][]{
            {16, 22},
            {34, 49}
        };

        X = new double[][]{
            {1, 2, 3},
            {4, 5, 6}
        };
    }

    @Test
    public void kertolaskuToimii() {
        double[][] C = kertolasku.kerro(X, A);
        assertTrue(Arrays.deepEquals(C, B));
    }

    @Test
    public void kertolaskuToimii2() {
        double[][] C = kertolasku.kerro(A, X);
        assertFalse(Arrays.deepEquals(C, B));
    }

    @Test
    public void kertolaskuToimii3() {
        double[][] C = kertolasku.kerro(B, B);
        double[][] D = new double[][]{
            {1004, 1430},
            {2210, 3149}
        };
        assertTrue(Arrays.deepEquals(C, D));
    }

    @Test(expected = IllegalArgumentException.class)
    public void poikkeusToimii() {
        kertolasku.kerro(B, A);
    }
}