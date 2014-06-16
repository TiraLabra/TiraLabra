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
    double[][] pyoristys;
    double[][] inf;
    double[][] singular;
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
        
        pyoristys = new double[][]{
            {0.111111, 0.333333},
            {0.666666, 0.888888}
        };
        
        inf = new double[][] {
            {Double.MAX_VALUE, 0},
            {0, Double.MAX_VALUE}
        };
        
        singular = new double[][] {
            {7560.0, 9288.0, 11016.0},   
            {17118.0, 21033.0, 24948.0},   
            {26676.0, 32778.0, 38880.0}   
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
    
    @Test
    public void lahesSingulaarinen() {
        assertEquals(0, determinantti.laskeDeterminantti(singular), 0);
    }
    
    @Test
    public void aaretonEiPyorista() {
        assertEquals(Double.POSITIVE_INFINITY, determinantti.laskeDeterminantti(inf), 0);
    }
    
    @Test
    public void pyoristysToimii() {
        assertEquals(-0.12345654, determinantti.laskeDeterminantti(pyoristys),0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void poikkeusToimii() {
        determinantti.laskeDeterminantti(A);
    }
}