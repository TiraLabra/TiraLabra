package apuneuvot;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MatriisienGeneroijaTest {
    
    double[][] I;
    double[][] ykkoset;
    MatriisienGeneroija generoija;
    
    @Before
    public void setUp() {
        generoija = new MatriisienGeneroija();
        
        I = new double[][]{
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        
        ykkoset = new double[][]{
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
    }
    
    @Test
    public void yksikkomatriisinLuontiToimii() {
        double[][] I2 = generoija.luoYksikkomatriisi(3);
        assertTrue(Arrays.deepEquals(I, I2));
    }
    
    @Test
    public void yksikkomatriisinLuontiToimii2() {
        double[][] I2 = generoija.luoYksikkomatriisi(3);
        I[0][0] = 0;
        assertFalse(Arrays.deepEquals(I, I2));
    }
    
    @Test
    public void ykkosmatriisinLuontiToimii() {
        double[][] ykkoset2 = generoija.luoYkkosillaTaytettyMatriisi(3, 3);
        assertTrue(Arrays.deepEquals(ykkoset, ykkoset2));
    }
    
    @Test
    public void ykkosmatriisinLuontiToimii2() {
        double[][] ykkoset2 = generoija.luoYkkosillaTaytettyMatriisi(3, 3);
        ykkoset[0][0] = 0;
        assertFalse(Arrays.deepEquals(ykkoset, ykkoset2));
    }
}