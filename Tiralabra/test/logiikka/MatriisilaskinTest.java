package logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MatriisilaskinTest {

    double[][] sym;
    double[][] A;
    double[][] antisym;
    double[][] orto;
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
        
        antisym = new double[][]{
            {0, 4, -5},
            {-4, 0, -6},
            {5, 6, 0}
        };
        
        orto = new double[][]{
            {(double)2/3, (double)-2/3, (double)1/3},
            {(double)1/3, (double)2/3, (double)2/3},
            {(double)2/3, (double)1/3, (double)-2/3}
        };
    }

    @Test
    public void symmetrisyysToimii() {
        assertTrue(laskin.onkoSymmetrinen(sym));
    }

    @Test
    public void symmetrisyysToimii2() {
        assertFalse(laskin.onkoSymmetrinen(antisym));
    }

    @Test
    public void neliomatriisiToimii() {
        assertTrue(laskin.onkoNeliomatriisi(sym));
    }

    @Test
    public void neliomatriisiToimii2() {
        assertFalse(laskin.onkoNeliomatriisi(A));
    }
    
    @Test
    public void antisymmetrisyysToimii() {
        assertTrue(laskin.onkoAntisymmetrinen(antisym));
    }
    
    @Test
    public void antisymmetrisyysToimii2() {
        assertFalse(laskin.onkoAntisymmetrinen(sym));
    }
    
    @Test
    public void kaantyvyysToimii() {
        assertTrue(laskin.onkoKaantyva(sym));
    }
    
    @Test
    public void kaantyvyysToimii2() {
        assertFalse(laskin.onkoKaantyva(antisym));
    }
    
    @Test
    public void ortogonaalisuusToimii() {
        assertTrue(laskin.onkoOrtogonaalinen(orto));
    }
    
    @Test
    public void ortogonaalisuusToimii2() {
        assertFalse(laskin.onkoOrtogonaalinen(sym));
    }
}