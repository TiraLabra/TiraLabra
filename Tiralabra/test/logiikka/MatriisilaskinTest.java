package logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MatriisilaskinTest {

    double[][] sym;
    double[][] A;
    Matriisilaskin laskin;

    @Before
    public void setUp() {
        laskin = new Matriisilaskin();

        sym = new double[][]{
            {1, 3},
            {3, 4}
        };

        A = new double[][]{
            {0, 1},
            {2, 3},
            {4, 5}
        };
    }

    @Test
    public void symmetrisyysToimii() {
        assertTrue(laskin.onkoSymmetrinen(sym));
    }

    @Test
    public void symmetrisyysToimii2() {
        sym[0][1] = 2;
        assertFalse(laskin.onkoSymmetrinen(sym));
    }

    @Test
    public void neliomatriisiToimii() {
        assertTrue(laskin.onkoNeliomatriisi(sym));
    }

    @Test
    public void neliomatriisiToimii2() {
        assertFalse(laskin.onkoNeliomatriisi(A));
    }
}