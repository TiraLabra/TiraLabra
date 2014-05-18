package logiikka;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SkalaarituloTest {

    double[][] A = new double[3][2];
    Matriisilaskin laskin;

    @Before
    public void setUp() {
        laskin = new Matriisilaskin();
        A[0][0] = 0;
        A[0][1] = 1;
        A[1][0] = 2;
        A[1][1] = 3;
        A[2][0] = 4;
        A[2][1] = 5;
    }

    @Test
    public void skalaarituloToimii() {
        double[][] C = laskin.kerro(A, 5);
        double[][] D = new double[3][2];

        D[0][0] = 0;
        D[0][1] = 5;
        D[1][0] = 10;
        D[1][1] = 15;
        D[2][0] = 20;
        D[2][1] = 25;
        assertTrue(Arrays.deepEquals(C, D));
    }

    @Test
    public void skalaarituloToimii2() {
        double[][] C = laskin.kerro(A, 5);
        double[][] D = new double[3][2];

        D[0][0] = 0;
        D[0][1] = 5;
        D[1][0] = 10;
        D[1][1] = 15;
        D[2][0] = 20;
        D[2][1] = 99;
        assertFalse(Arrays.deepEquals(C, D));
    }
    
    @Test
    public void skalaarituloToimii3() {
        double[][] C = laskin.kerro(A, -1);
        double[][] D = new double[3][2];

        D[0][0] = 0;
        D[0][1] = -1;
        D[1][0] = -2;
        D[1][1] = -3;
        D[2][0] = -4;
        D[2][1] = -5;
        assertTrue(Arrays.deepEquals(C, D));
    }
}
