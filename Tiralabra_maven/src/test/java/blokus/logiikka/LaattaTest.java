package blokus.logiikka;

import java.util.Arrays;
import junit.framework.TestCase;

public class LaattaTest extends TestCase {

    Laatta laatta1;
    Laatta laatta21;
    int[][] malli1;
    int[][] malli21;

    public LaattaTest() {
    }

    @Override
    public void setUp() {
        malli1 = new int[][]{
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 2, 3, 2, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };
        laatta1 = new Laatta(1, malli1, 1, 1);

        malli21 = new int[][]{
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 1, 0, 0, 0},
            {1, 2, 3, 2, 2, 1, 0},
            {2, 3, 3, 3, 3, 2, 0},
            {1, 2, 2, 2, 2, 1, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };
        laatta21 = new Laatta(1, malli21, 1, 2);
    }

    @Override
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    public void testYksinkertainenLaattaKaantoOikeaanTest() {
        laatta1.kaannaOikeaan();
        assertTrue(laatatSamat(malli1, laatta1.getMuoto()));
        assertEquals(2, laatta1.getAsento());


    }

    public void testYksinkertainenLaattaKaantoVasempaanTest() {
        laatta1.kaannaVasempaan();
        assertTrue(laatatSamat(malli1, laatta1.getMuoto()));
        assertEquals(4, laatta1.getAsento());
    }

    public void testYksinkertainenLaattaKaantoYmpariTest() {
        laatta1.kaannaYmpari();
        assertTrue(laatatSamat(malli1, laatta1.getMuoto()));
        assertEquals(1, laatta1.getYmpari());

    }

    public void testMonimutkainenLaattaKaantoOikeaanTest() {
        laatta21.kaannaOikeaan();
        int[][] kaannos = new int[][]{
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 2, 3, 2, 1, 0},
            {0, 0, 2, 3, 3, 2, 0},
            {0, 0, 2, 3, 2, 1, 0},
            {0, 0, 2, 3, 2, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };
        assertTrue(laatatSamat(kaannos, laatta21.getMuoto()));
        assertEquals(2, laatta21.getAsento());
    }

    public void testMonimutkainenLaattaKaantoVasempaanTest() {
        laatta21.kaannaVasempaan();
        int[][] kaannos = new int[][]{
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 2, 3, 2, 0, 0},
            {0, 1, 2, 3, 2, 0, 0},
            {0, 2, 3, 3, 2, 0, 0},
            {0, 1, 2, 3, 2, 0, 0},
            {0, 0, 1, 2, 1, 0, 0}
        };
        assertTrue(laatatSamat(kaannos, laatta21.getMuoto()));
        assertEquals(4, laatta21.getAsento());
    }

    public void testMonimutkainenLaattaKaantoYmpariTest() {
        laatta21.kaannaYmpari();
        int[][] kaannos = new int[][]{
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 2, 1, 0},
            {0, 1, 2, 2, 3, 2, 1},
            {0, 2, 3, 3, 3, 3, 2},
            {0, 1, 2, 2, 2, 2, 1},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };
        assertTrue(laatatSamat(kaannos, laatta21.getMuoto()));
        assertEquals(1, laatta21.getAsento());

    }
    
    public void testKaannoksia() {
        laatta1.kaannaVasempaan();
        laatta1.kaannaOikeaan();
        assertEquals(1, laatta1.getAsento());
        laatta1.kaannaYmpari();
        assertEquals(1, laatta1.getYmpari());
    }
    
    public void testKaantaja() {
        laatta21.kaannaTiettyynAsentoon(1, 1);
                int[][] kaannos = new int[][]{
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 2, 1, 0},
            {0, 1, 2, 2, 3, 2, 1},
            {0, 2, 3, 3, 3, 3, 2},
            {0, 1, 2, 2, 2, 2, 1},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };
        assertTrue(laatatSamat(kaannos, laatta21.getMuoto()));
        assertEquals(1, laatta21.getAsento());
    }
    
    
    

    private boolean laatatSamat(int[][] a, int[][] b) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }

            }

        }
        return true;
    }
}
