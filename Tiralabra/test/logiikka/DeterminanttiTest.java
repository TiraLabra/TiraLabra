package logiikka;

import apuneuvot.MatriisienKopioija;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeterminanttiTest {

    double[][] sym;
    double[][] nolla;
    double[][] nolla2;
    double[][] nolla3;
    double[][] testi;
    double[][] A;
    Determinantti determinantti;
    
    @Before
    public void setUp() {
        determinantti = new Determinantti(new MatriisienKopioija());
        
        sym = new double[][]{
            {1, 3},
            {3, 4}
        };
        
        nolla = new double[][]{
            {1, 3},
            {2, 6}
        };
        
        nolla2 = new double[][]{
            {0, 3},
            {0, 6}
        };
        
        nolla3 = new double[][]{
            {2, -1, 2},
            {2, -1, 2},
            {1, 4, 3}
        };
        
        testi = new double[][]{
            {3, 2, 1},
            {2, 2, 1},
            {1, 1, 4}
        };
        
        A = new double[][]{
            {0, 1},
            {2, 3},
            {4, 5}
        };
    }
    
    @Test
    public void determinanttiToimii() {
        assertEquals(-5, determinantti.laskeDeterminantti(sym), 0);
    }
    
    @Test
    public void determinanttiToimii2() {
        assertEquals(0, determinantti.laskeDeterminantti(nolla), 0);
    }
    
    @Test
    public void determinanttiToimii3() {
        assertEquals(0, determinantti.laskeDeterminantti(nolla2), 0);
    }
    
    @Test
    public void determinanttiToimii4() {
        assertEquals(0, determinantti.laskeDeterminantti(nolla3), 0);
    }
    
    @Test
    public void determinanttiToimii5() {
        assertEquals(7, determinantti.laskeDeterminantti(testi), 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void poikkeusToimii() {
        determinantti.laskeDeterminantti(A);
    }
}