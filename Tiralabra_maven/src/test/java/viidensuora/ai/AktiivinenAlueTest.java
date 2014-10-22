package viidensuora.ai;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import viidensuora.logiikka.Ristinolla;

public class AktiivinenAlueTest {

    private AktiivinenAlue alue;
    private Ristinolla rn;

    @Before
    public void setUp() {
        rn = new Ristinolla(15, 15, 5);
        alue = new AktiivinenAlue(rn);
    }

    @Test
    public void alustaaTyhjanLaudanOikein() {
        alue.alusta(0);
        assertTrue(alue.onAktiivinenTyhja(7, 7));
    }

    @Test
    public void alustaaLaudanOikeinEtaisyydella1() {
        rn.lisaaRisti(7, 7);
        alue.alusta(1);

        assertTrue(alue.onAktiivinenTyhja(6, 6));
        assertTrue(alue.onAktiivinenTyhja(7, 6));
        assertTrue(alue.onAktiivinenTyhja(8, 6));

        assertTrue(alue.onAktiivinenTyhja(6, 7));
        assertTrue(!alue.onAktiivinenTyhja(7, 7));
        assertTrue(alue.onAktiivinenTyhja(8, 7));

        assertTrue(alue.onAktiivinenTyhja(6, 8));
        assertTrue(alue.onAktiivinenTyhja(7, 8));
        assertTrue(alue.onAktiivinenTyhja(8, 8));
    }

    @Test
    public void alustaaLaudanOikeinEtaisyydella2() {
        rn.lisaaRisti(7, 7);
        alue.alusta(2);

        assertTrue(alue.onAktiivinenTyhja(5, 5));
        assertTrue(!alue.onAktiivinenTyhja(6, 5));
        assertTrue(alue.onAktiivinenTyhja(7, 5));
        assertTrue(!alue.onAktiivinenTyhja(8, 5));
        assertTrue(alue.onAktiivinenTyhja(9, 5));

        assertTrue(!alue.onAktiivinenTyhja(5, 6));
        assertTrue(alue.onAktiivinenTyhja(6, 6));
        assertTrue(alue.onAktiivinenTyhja(7, 6));
        assertTrue(alue.onAktiivinenTyhja(8, 6));
        assertTrue(!alue.onAktiivinenTyhja(9, 6));

        assertTrue(alue.onAktiivinenTyhja(5, 7));
        assertTrue(alue.onAktiivinenTyhja(6, 7));
        assertTrue(!alue.onAktiivinenTyhja(7, 7));
        assertTrue(alue.onAktiivinenTyhja(8, 7));
        assertTrue(alue.onAktiivinenTyhja(9, 7));

        assertTrue(!alue.onAktiivinenTyhja(5, 8));
        assertTrue(alue.onAktiivinenTyhja(6, 8));
        assertTrue(alue.onAktiivinenTyhja(7, 8));
        assertTrue(alue.onAktiivinenTyhja(8, 8));
        assertTrue(!alue.onAktiivinenTyhja(9, 8));

        assertTrue(alue.onAktiivinenTyhja(5, 9));
        assertTrue(!alue.onAktiivinenTyhja(6, 9));
        assertTrue(alue.onAktiivinenTyhja(7, 9));
        assertTrue(!alue.onAktiivinenTyhja(8, 9));
        assertTrue(alue.onAktiivinenTyhja(9, 9));
    }

    @Test
    public void lisaavaPaivitysOnnistuu() {
        alue.alusta(0);
        assertTrue(alue.onAktiivinenTyhja(7, 7));
        assertTrue(!alue.onAktiivinenTyhja(6, 6));
        assertTrue(!alue.onAktiivinenTyhja(8, 8));

        rn.lisaaNolla(7, 7);
        alue.paivita(7, 7, 1, 1);
        assertTrue(!alue.onAktiivinenTyhja(7, 7));
        assertTrue(alue.onAktiivinenTyhja(6, 6));
        assertTrue(alue.onAktiivinenTyhja(8, 8));
    }

    @Test
    public void poistavaPaivitysOnnistuu() {
        alue.alusta(0);
        
        rn.lisaaNolla(7, 7);
        alue.paivita(7, 7, 1, 1);
        assertTrue(!alue.onAktiivinenTyhja(7, 7));
        assertTrue(alue.onAktiivinenTyhja(6, 6));
        assertTrue(alue.onAktiivinenTyhja(8, 8));

        rn.poistaMerkki(7, 7);
        alue.paivita(7, 7, 1, -1);
        assertTrue(alue.onAktiivinenTyhja(7, 7));
        assertTrue(!alue.onAktiivinenTyhja(6, 6));
        assertTrue(!alue.onAktiivinenTyhja(8, 8));
    }
}
