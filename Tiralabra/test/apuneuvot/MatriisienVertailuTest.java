package apuneuvot;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MatriisienVertailuTest {

    double[][] sym;
    double[][] eisym;
    double[][] A;
    MatriisienVertailu vertailu;
    
    @Before
    public void setUp() {
        vertailu = new MatriisienVertailu();
        
        sym = new double[][]{
            {1, 3},
            {3, 4}
        };
        
        eisym = new double[][]{
            {1, 2},
            {3, 4}
        };

        A = new double[][]{
            {0, 1},
            {2, 3},
            {4, 5}
        };
    }
    
    @Test
    public void vertailuToimii() {
        assertTrue(vertailu.vertaile(sym, sym));
    }
    
    @Test
    public void vertailuToimii2() {
        assertFalse(vertailu.vertaile(sym, A));
    }
    
    @Test
    public void vertailuToimii3() {
        assertFalse(vertailu.vertaile(sym, eisym));
    }
}