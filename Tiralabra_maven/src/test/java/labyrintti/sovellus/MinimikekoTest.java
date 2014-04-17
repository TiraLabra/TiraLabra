package labyrintti.sovellus;

import labyrintti.osat.Pohja;
import labyrintti.osat.Ruutu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heidvill
 */
public class MinimikekoTest {

    /**
     * Testattava minimikeko.
     */
    private Minimikeko keko;
    /**
     * Testipohja.
     */
    private Pohja p;
    /**
     * Testikartta.
     */
    private String esim;

    @Before
    public void setUp() {
        keko = new Minimikeko(9);
        esim = "L11"
                + "111"
                + "11M";
        p = new Pohja(3, 3, esim);
        keko.alustaTaulukko(p);
    }

    @Test
    public void kekoAlustetaanOikein() {
        Ruutu[] tarkistus = luoTarkistustalukko();
        Ruutu[] ruudut = keko.getRuudut();
        assertEquals(9, keko.getKeonKoko());
        for (int i = 0; i < 9; i++) {
            if (i == 0 || i == 8) {
                assertEquals(0, ruudut[i].getArvo());
            } else {
                assertEquals(1, ruudut[i].getArvo());
            }
            assertEquals(tarkistus[i].getX(), ruudut[i].getX());
            assertEquals(tarkistus[i].getY(), ruudut[i].getY());
        }
    }

    @Test
    public void rakentaaKeonOikein() {
        asetaAlkuetaisyydet();
        keko.rakennaKeko();
        assertEquals("(2,2) (2,1) (2,0) (1,0) (1,1) (1,2) (0,2) (0,1) (0,0)", keko.getAlkiot());
    }

    @Test
    public void palauttaaPienimman() {
        asetaAlkuetaisyydet();
        keko.rakennaKeko();
        for (int i = 8; i >= 0; i--) {
            assertEquals(i + 1, keko.getKeonKoko());
            assertEquals("(" + i / 3 + "," + i % 3 + ")", keko.pollPienin().koordinaatit());
        }
        assertEquals(0, keko.getKeonKoko());

    }

    private Ruutu[] luoTarkistustalukko() {
        Ruutu[] tarkistus = new Ruutu[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tarkistus[i * 3 + j] = p.getRuutu(i, j);
            }
        }
        return tarkistus;
    }

    private void asetaAlkuetaisyydet() {
        for (int i = 0; i < keko.getKeonKoko(); i++) {
            keko.getRuudut()[i].setEtaisyysAlkuun(keko.getKeonKoko() - i);
        }
    }
}
