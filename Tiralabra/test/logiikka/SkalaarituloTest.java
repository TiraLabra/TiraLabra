package logiikka;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SkalaarituloTest {

    double[][] A;
    double[][] D;
    Skalaaritulo skalaaritulo;

    @Before
    public void setUp() {
        skalaaritulo = new Skalaaritulo();

        A = new double[][]{
            {0, 1},
            {2, 3},
            {4, 5}
        };

        D = new double[][]{
            {0, 5},
            {10, 15},
            {20, 25}
        };
    }

    @Test
    public void skalaarituloToimii() {
        double[][] C = skalaaritulo.kerro(A, 5);
        assertTrue(Arrays.deepEquals(C, D));
    }

    @Test
    public void skalaarituloToimii2() {
        double[][] C = skalaaritulo.kerro(A, 5);
        D[2][1] = 99;
        assertFalse(Arrays.deepEquals(C, D));
    }

    @Test
    public void skalaarituloToimii3() {
        double[][] C = skalaaritulo.kerro(A, -1);
        D = new double[][]{
            {0, -1},
            {-2, -3},
            {-4, -5}
        };
        assertTrue(Arrays.deepEquals(C, D));
    }
}