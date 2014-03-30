package labyrintti.sovellus;

import labyrintti.osat.Pohja;
import labyrintti.osat.Ruutu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author heidvill@cs
 */
public class MinimikekoTest {

    private Minimikeko keko;
    private Pohja p;
    private Etsija e;

    public MinimikekoTest() {
    }

    @Before
    public void setUp() {
        keko = new Minimikeko(8);
        p = new Pohja();
        p.alustaPohja("src/main/java/labyrintti/osat/kartta2.txt");
        keko.alustaTaulukko(p);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void kekoAlustetaanOikein() {
        Ruutu[] tarkistus = luoTarkistustalukko();
        Ruutu[] ruudut = keko.getRuudut();
        assertEquals(8, keko.getKeonKoko());
        for (int i = 0; i < 8; i++) {
            assertEquals(tarkistus[i].getArvo(), ruudut[i].getArvo());
            assertEquals(tarkistus[i].getX(), ruudut[i].getX());
            assertEquals(tarkistus[i].getY(), ruudut[i].getY());
        }
    }

    @Test
    public void rakentaaKeonOikein() {
        asetaAlkuetaisyydet();
        keko.rakennaKeko();
        assertEquals("(1,3) (1,0) (1,2) (0,3) (0,0) (1,1) (0,2) (0,1)", keko.getAlkiot());
    }

    @Test
    public void palauttaaPienimman() {
        asetaAlkuetaisyydet();
        keko.rakennaKeko();
        for (int i = 7; i >= 0; i--) {
            assertEquals(i + 1, keko.getKeonKoko());
            assertEquals("(" + i / 4 + "," + i % 4 + ")", keko.pollPienin().koordinaatit());
        }
        assertEquals(0, keko.getKeonKoko());

    }

    private Ruutu[] luoTarkistustalukko() {
        Ruutu[] tarkistus = new Ruutu[8];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                tarkistus[i * 4 + j] = p.getRuutu(i, j);
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
