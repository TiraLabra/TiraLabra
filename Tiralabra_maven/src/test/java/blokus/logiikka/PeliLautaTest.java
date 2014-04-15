package blokus.logiikka;

import junit.framework.TestCase;


public class PeliLautaTest extends TestCase{

    PeliLauta lauta;
    Laatta laatta;

    public PeliLautaTest() {
    }


    @Override
    public void setUp() {
        lauta = new PeliLauta();
            int[][]    malli1 = new int[][]{
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 2, 3, 2, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };
        laatta = new Laatta(1, malli1, 1, 1);
        TarkastusLauta tar = new TarkastusLauta(1);
        lauta.lisaaTarkastusLauta(1, tar);
    }

   
    @Override
    public void tearDown() {
    }
 

   
    public void testalustusToimii() {
        int[][] arr = lauta.getLauta();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                assertEquals(0, arr[i][j]);

            }

        }
    }

    public void testonkoLaudallatoimii() {
        assertTrue(lauta.onkoLaudalla(9, 9, 3, 3));
        assertFalse(lauta.onkoLaudalla(0, 0, 2, 3));
        assertFalse(lauta.onkoLaudalla(29, 29, 2, 3));
    }

    public void testlaatanLisaysToimii() {
        assertFalse(lauta.lisaaLaattaLaudalle(1, laatta, -1, 0));
        assertTrue(lauta.lisaaLaattaLaudalle(1, laatta, 0, 0));
    }
}
