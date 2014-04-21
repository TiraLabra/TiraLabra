package labyrintti.sovellus;

import labyrintti.osat.Pohja;
import labyrintti.osat.Ruutu;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heidvill
 */
public class EtsijaTest {

    /**
     * Testattava etsijä.
     */
    private Etsija etsija;
    /**
     * Testikartta.
     */
    private String esim;

    @Before
    public void setUp() {
        esim = "L11"
                + "111"
                + "11M";
        Pohja pohja = new Pohja(3, 3, esim);
        etsija = new Etsija(pohja);
    }

    @Test
    public void alustusOikein() {
        etsija.alustus();
        assertEquals(9, etsija.getKaymattomat().getKeonKoko());
        Pohja p = etsija.getPohja();
        assertEquals(0, p.getLahto().getArvo());
        assertEquals(0, p.getMaali().getArvo());
    }

    @Test
    public void aStarToimii() {
        etsija.aStar();
        Pohja p = etsija.getPohja();
        assertTrue(p.getRuutu(p.getMaaliX(), p.getMaaliY()).onkoKayty());
        assertEquals(2, etsija.getKaymattomat().getKeonKoko());
    }

    @Test
    public void kekoJarjestaaRuudutOikein() {
        etsija.alustus();
        Minimikeko keko = etsija.getKaymattomat();
        assertEquals("(0,0) (2,2) (1,2) (2,1) (1,1) (0,2) (2,0) (0,1) (1,0)", keko.getAlkiot());
    }

    @Test
    public void palauttaaPienimman() {
        etsija.alustus();
        Minimikeko keko = etsija.getKaymattomat();
        Ruutu pienin = keko.pollPienin();
        assertEquals(0, pienin.getArvo());
        assertEquals(0, pienin.getX());
        assertEquals(0, pienin.getY());
        assertEquals(8, keko.getKeonKoko());
        assertEquals("(2,2) (2,1) (1,2) (1,0) (1,1) (0,2) (2,0) (0,1)", keko.getAlkiot());
    }

    @Test
    public void reittiOikein() {
        etsija.aStar();
        etsija.getReittiMerkkijonona();
        assertEquals("(0,0) (0,1) (0,2) (1,2) (2,2)", etsija.getReittiMerkkijonona());
        etsija.tallennaReittiTaulukkoon();
        assertEquals(5, etsija.getReitinPituus());
        assertTrue(etsija.onkoRuutuReitilla(0, 2));
        assertTrue(!etsija.onkoRuutuReitilla(1, 1));
    }
}