package viidensuora.peli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelilautaTest {

    private Pelilauta pl;
    private final int LEVEYS = 10;
    private final int KORKEUS = 5;

    @Before
    public void setUp() {
        pl = new Pelilauta(KORKEUS, LEVEYS);
    }

    @Test
    public void konstruktoriOK() {
        assertEquals(KORKEUS, pl.getKorkeus());
        assertEquals(LEVEYS, pl.getLeveys());
        for (int i = 0; i < KORKEUS; i++) {
            for (int j = 0; j < LEVEYS; j++) {
                assertTrue(pl.getPelimerkki(i, j) == null);
            }
        }
    }

    @Test
    public void merkinLisaysOnnistuu() {
        Pelimerkki pm = new Nolla(0, 0);
        pl.lisaaMerkki(pm);
        assertTrue(pl.getPelimerkki(0, 0) == pm);
    }

    @Test
    public void merkinPoistoOnnistuu() {
        Pelimerkki pm = new Nolla(0, 0);
        pl.lisaaMerkki(pm);
        pl.poistaMerkki(0, 0);
        assertTrue(pl.ruutuVapaa(0, 0));
    }

    @Test
    public void vapaidenRuutujenLaskuOK() {
        Pelimerkki pm = new Nolla(0, 0);
        pl.lisaaMerkki(pm);
        assertEquals(LEVEYS * KORKEUS - 1, pl.vapaitaRuutuja());
    }

    @Test
    public void onTaynna() {
        pl = new Pelilauta(1, 1);
        Pelimerkki pm = new Nolla(0, 0);
        pl.lisaaMerkki(pm);
        assertTrue(pl.taynna());
    }
    
    @Test
    public void eiTaynna() {
        pl = new Pelilauta(1, 1);
        assertTrue(!pl.taynna());
    }

    @Test
    public void ruutuVapaa() {
        assertTrue(pl.getPelimerkki(0, 0) == null);
    }

    @Test
    public void onRistiTest() {
        pl.asetaRisti(0, 0);
        assertTrue(pl.onRisti(0, 0));
    }

    @Test
    public void onNollaTest() {
        pl.asetaNolla(0, 0);
        assertTrue(pl.onNolla(0, 0));
    }

    @Test
    public void palauttaaOikeanPelimerkin() {
        Pelimerkki pm = new Nolla(0, 0);
        pl.lisaaMerkki(pm);
        assertTrue(pl.getPelimerkki(0, 0) == pm);
    }

}
