package logiikka;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class YhteenlaskuTest {

    double[][] A = new double[3][2];
    double[][] B = new double[3][2];
    double[][] X = new double[3][2];
    Matriisilaskin laskin;

    @Before
    public void setUp() {
        laskin = new Matriisilaskin();
        
        A[0][0] = 0;
        A[0][1] = 1;
        A[1][0] = 2;
        A[1][1] = 3;
        A[2][0] = 4;
        A[2][1] = 5;

        B[0][0] = 1;
        B[0][1] = 1;
        B[1][0] = 1;
        B[1][1] = 1;
        B[2][0] = 1;
        B[2][1] = 1;

        X[0][0] = -1;
        X[0][1] = -1;
        X[1][0] = -1;
        X[1][1] = -1;
        X[2][0] = -1;
        X[2][1] = -1;
    }

    @Test
    public void yhteenlaskuToimii() {
        double[][] C = laskin.summaa(A, B);
        double[][] D = new double[3][2];

        D[0][0] = 1;
        D[0][1] = 2;
        D[1][0] = 3;
        D[1][1] = 4;
        D[2][0] = 5;
        D[2][1] = 6;
        assertTrue(Arrays.deepEquals(C, D));
    }

    @Test
    public void yhteenlaskuToimii2() {
        double[][] C = laskin.summaa(A, B);
        double[][] D = new double[3][2];

        D[0][0] = 1;
        D[0][1] = 2;
        D[1][0] = 3;
        D[1][1] = 4;
        D[2][0] = 5;
        D[2][1] = 99;
        assertFalse(Arrays.deepEquals(C, D));
    }
    
    @Test
    public void yhteenlaskuToimii3() {  
        double[][] C = laskin.summaa(A, X);
        double[][] D = new double[3][2];

        D[0][0] = -1;
        D[0][1] = 0;
        D[1][0] = 1;
        D[1][1] = 2;
        D[2][0] = 3;
        D[2][1] = 4;        
        
        assertTrue(Arrays.deepEquals(C, D));
    }

    @Test(expected = IllegalArgumentException.class)
    public void poikkeusToimii() {
        double[][] C = new double[4][2];
        laskin.summaa(A, C);
    }
}
