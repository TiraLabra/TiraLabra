package apuneuvot;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MatriisienKopioijaTest {

    double[][] sym;
    double[][] A;
    MatriisienKopioija kopioija;
    
    @Before
    public void setUp() {
        kopioija = new MatriisienKopioija();
        
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
    public void kopiointiToimii() {
        double[][] kopio = kopioija.kopioiNeliomatriisi(sym);
        assertTrue(Arrays.deepEquals(sym, kopio));
    }
    
    @Test
    public void kopiointiToimii2() {
        double[][] kopio = kopioija.kopioiNeliomatriisi(sym);
        kopio[0][0] = 99;
        assertFalse(Arrays.deepEquals(sym, kopio));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void poikkeusToimii() {
        kopioija.kopioiNeliomatriisi(A);
    }
}