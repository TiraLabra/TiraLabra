package logiikka;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StrassenTest {
    
    double[][] A;
    double[][] B;
    double[][] X;
    Strassen strassen;
    
    @Before
    public void setUp() {
        strassen = new Strassen(new Yhteenlasku(), new Vahennyslasku());
        
        A = new double[][]{
            {1, 3},
            {3, 4}
        };

        B = new double[][]{
            {1, 1, 1},
            {2, 2, 2},
            {3, 3, 3}
        };
        
        X = new double[][]{
            {2, -1, 2},
            {3, -1, 3},
            {0, 4, 0}
        };
    }
    
    @Test
    public void strassenToimii() {
        double[][] C = strassen.kerro(A, A);
        double[][] D = new double[][]{
            {10, 15},
            {15, 25}
        };        
        assertTrue(Arrays.deepEquals(C, D));
    }

    @Test
    public void strassenToimii2() {
        double[][] C = strassen.kerro(A, A);
        double[][] D = new double[][]{
            {10, 99},
            {99, 25}
        }; 
        assertFalse(Arrays.deepEquals(C, D));
    }

    @Test
    public void strassenToimii3() {
        double[][] C = strassen.kerro(B, B);
        double[][] D = new double[][]{
            {6, 6, 6},
            {12, 12, 12},
            {18, 18, 18}
        };
        assertTrue(Arrays.deepEquals(C, D));
    }
    
    @Test
    public void strassenToimii4() {
        double[][] C = strassen.kerro(B, X);
        double[][] D = new double[][]{
            {5, 2, 5},
            {10, 4, 10},
            {15, 6, 15}
        };
        assertTrue(Arrays.deepEquals(C, D));
    }

    @Test(expected = IllegalArgumentException.class)
    public void poikkeusToimii() {
        strassen.kerro(B, A);
    }  
}