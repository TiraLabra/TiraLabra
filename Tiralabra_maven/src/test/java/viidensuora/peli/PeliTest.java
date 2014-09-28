package viidensuora.peli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeliTest {

    private Peli p;

    @Before
    public void setUp() {
        p = new Peli(15, 15, 5);
    }

    @Test
    public void merkinLisaysJaVuoronSiirtoToimii() {
        p.lisaaMerkki(0, 0);
        p.lisaaMerkki(0, 1);
        p.lisaaMerkki(0, 2);
        assertTrue(p.getPelilauta().onRisti(0, 0));        
        assertTrue(p.getPelilauta().onNolla(0, 1));
        assertTrue(p.getPelilauta().onRisti(0, 2));
    }

    @Test
    public void horisontaaliPituusOK() {
        p.getPelilauta().asetaRisti(0, 0);
        p.getPelilauta().asetaRisti(0, 1);
        assertEquals(2, p.pisinSuora(p.getPelilauta().getPelimerkki(0, 0)));
    }

    @Test
    public void vertikaalinenPituusOK() {
        p.getPelilauta().asetaRisti(0, 0);
        p.getPelilauta().asetaRisti(1, 0);
        assertEquals(2, p.pisinSuora(p.getPelilauta().getPelimerkki(0, 0)));
    }

    @Test
    public void diagonaali1PituusOK() {
        p.getPelilauta().asetaRisti(0, 0);
        p.getPelilauta().asetaRisti(1, 1);
        assertEquals(2, p.pisinSuora(p.getPelilauta().getPelimerkki(0, 0)));
    }

    @Test
    public void diagonaali2PituusOK() {
        p.getPelilauta().asetaRisti(1, 0);
        p.getPelilauta().asetaRisti(0, 1);
        assertEquals(2, p.pisinSuora(p.getPelilauta().getPelimerkki(1, 0)));
    }

    @Test
    public void pisinSuoraLoytyy() {
        p.getPelilauta().asetaRisti(0, 0);
        p.getPelilauta().asetaRisti(0, 1);
        p.getPelilauta().asetaRisti(0, 2);
        p.getPelilauta().asetaNolla(0, 3);
        assertEquals(3, p.pisinSuora(p.getPelilauta().getPelimerkki(0, 0)));
        assertEquals(3, p.pisinSuora(p.getPelilauta().getPelimerkki(0, 1)));
        assertEquals(3, p.pisinSuora(p.getPelilauta().getPelimerkki(0, 2)));
    }

    @Test
    public void pisinSuoraLoytyy2() {
        p.getPelilauta().asetaRisti(0, 0);
        p.getPelilauta().asetaRisti(0, 1);
        p.getPelilauta().asetaNolla(1, 0);
        p.getPelilauta().asetaNolla(1, 1);
        p.getPelilauta().asetaRisti(1, 2);
        p.getPelilauta().asetaNolla(2, 0);

        assertEquals(2, p.pisinSuora(p.getPelilauta().getPelimerkki(0, 0)));
        assertEquals(2, p.pisinSuora(p.getPelilauta().getPelimerkki(0, 1)));
        assertEquals(0, p.pisinSuora(p.getPelilauta().getPelimerkki(0, 2)));
        assertEquals(2, p.pisinSuora(p.getPelilauta().getPelimerkki(1, 0)));
    }

    @Test
    public void loytaaVoiton() {
        p.getPelilauta().asetaRisti(0, 0);
        p.getPelilauta().asetaRisti(0, 1);
        p.getPelilauta().asetaRisti(0, 2);
        p.getPelilauta().asetaRisti(0, 3);
        p.getPelilauta().asetaRisti(0, 4);

        assertTrue(p.siirtoVoitti(p.getPelilauta().getPelimerkki(0, 4)));
    }

}
