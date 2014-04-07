package blokus.logiikka;

import junit.framework.TestCase;


public class TarkastusLautaTest extends TestCase{

    TarkastusLauta testi;
    Laatta laatta;

    public TarkastusLautaTest() {
    }


    @Override
    public void setUp() {
        testi = new TarkastusLauta(1);
        int[][] malli2 = new int[][]{
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 2, 1, 0, 0},
            {0, 2, 3, 3, 2, 0, 0},
            {0, 1, 2, 2, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };
        laatta = new Laatta(2, malli2, 2, 1);
    }


    @Override
    public void tearDown() {
    }


    
    public void testalustusToimii() {
        assertEquals(1, testi.tLauta[0][0]);
        assertEquals(0, testi.tLauta[0][19]);
        assertEquals(0, testi.tLauta[19][0]);
        assertEquals(0, testi.tLauta[19][19]);
    }

    public void testlaudanOhiEiVoiLisata() {
        assertFalse(testi.tarkistaVoikoLisata(laatta, 0, -1).isEmpty());
        assertFalse(testi.tarkistaVoikoLisata(laatta, 0, 0).isEmpty());
        assertFalse(testi.tarkistaVoikoLisata(laatta, -1, 3).isEmpty());
        assertFalse(testi.tarkistaVoikoLisata(laatta, 20, 20).isEmpty());
        assertFalse(testi.tarkistaVoikoLisata(laatta, 0, 20).isEmpty());
    }

    public void testminneSattuuEiVoiLisata() {
        assertFalse(testi.tarkistaVoikoLisata(laatta, 13, 13).isEmpty());
        assertFalse(testi.tarkistaVoikoLisata(laatta, 0, 19).isEmpty());
        assertFalse(testi.tarkistaVoikoLisata(laatta, 19, 1).isEmpty());
        assertFalse(testi.tarkistaVoikoLisata(laatta, 19, 19).isEmpty());
        assertFalse(testi.tarkistaVoikoLisata(laatta, 7, 13).isEmpty());
    }

    public void testlaudalleVoiLisata() {
        assertTrue(testi.tarkistaVoikoLisata(laatta, 0, 1).isEmpty());
        testi.lisaaLaatta(1, 9, 9);
        assertTrue(testi.tarkistaVoikoLisata(laatta, 9, 9).isEmpty());
        testi.lisaaLaatta(1, 7, 7);
        testi.lisaaLaatta(1, 7, 6);
        assertTrue(testi.tarkistaVoikoLisata(laatta, 7, 7).isEmpty());
    }
    
    
}
