package logiikka;

import apuneuvot.MatriisienKopioija;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KaanteismatriisiTest {

    double[][] A;
    double[][] sym;
    double[][] X;
    Kaanteismatriisi kaanteismatriisi;
    
    @Before
    public void setUp() {
        kaanteismatriisi = new Kaanteismatriisi(new MatriisienKopioija());
        
        A = new double[][]{
            {1, 3, -1},
            {1, 2, 0},
            {2, 4, 1}
        };
        
        sym = new double[][]{
            {1, 3},
            {3, 4}  
        };
        
        X = new double[][]{
            {0, 1},
            {2, 3},
            {4, 5}
        };
    }
    
    @Test
    public void invertointiToimii() {
        double[][] B = kaanteismatriisi.invertoi(A);
        double[][] C = new double[][] {
            {-2, 7, -2},
            {1, -3, 1},
            {0, -2, 1}
        };
        assertTrue(Arrays.deepEquals(C, B));
    }

    @Test
    public void invertointiToimii2() {
        double[][] B = kaanteismatriisi.invertoi(A);
        double[][] C = new double[][] {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        assertFalse(Arrays.deepEquals(C, B));
    }
    
    @Test
    public void invertointiToimii4() {
        double[][] B = kaanteismatriisi.invertoi(sym);
        double[][] C = new double[][] {
            {-0.7999999999999999, 0.6},
            {0.6, -0.19999999999999998}
        };
        assertTrue(Arrays.deepEquals(C, B));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void poikkeusToimii() {
        kaanteismatriisi.invertoi(X);
    }
}