package logiikka;

import apuneuvot.MatriisienGeneroija;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PotenssiTest {
    
    double[][] A;
    double[][] B;
    Potenssi potenssi;
    
    @Before
    public void setUp() {
        potenssi = new Potenssi(new MatriisienGeneroija(), new Strassen(
                   new Yhteenlasku(), new Vahennyslasku(), new Kertolasku()));
        
        A = new double[][] {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        
        B = new double[][] {
            {1, 1, 1}
        };
    }
    
    @Test
    public void potenssiToimiiNollalla() {
        double[][] C = potenssi.neliomatriisiPotenssiin(A, 0);
        double[][] D = new double[][] {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        assertTrue(Arrays.deepEquals(C, D));
    }
    
    @Test
    public void potenssiToimiiYkkosella() {
        double[][] C = potenssi.neliomatriisiPotenssiin(A, 1);
        assertTrue(Arrays.deepEquals(C, A));
    }
    
    @Test
    public void potenssiToimiiParittomalla() {
        double[][] C = potenssi.neliomatriisiPotenssiin(A, 3);
        double[][] D = new double[][] {
            {9, 9, 9},
            {9, 9, 9},
            {9, 9, 9}
        };
        assertTrue(Arrays.deepEquals(C, D));
    }
    
    @Test
    public void potenssiToimiiParillisella() {
        double[][] C = potenssi.neliomatriisiPotenssiin(A, 4);
        double[][] D = new double[][] {
            {27, 27, 27},
            {27, 27, 27},
            {27, 27, 27}
        };
        assertTrue(Arrays.deepEquals(C, D));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void poikkeusToimii() {
        potenssi.neliomatriisiPotenssiin(B, 2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void poikkeusToimii2() {
        potenssi.neliomatriisiPotenssiin(A, -1);
    }
}