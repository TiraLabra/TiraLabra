package labyrintti.sovellus;

import labyrintti.osat.Pohja;
import labyrintti.osat.Ruutu;
import org.junit.After;
import org.junit.Assert;
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
    
//    @Test
//    public void rakentaaKeonOikein(){
//        Ruutu[] tarkistus = luoTarkistustalukko();
//        for (int i = 0; i < 8; i++) {
//            tarkistus[i].setEtaisyysAlkuun(8-1);
//        }
//        p.tulostaPohja();
//        keko.rakennaKeko();
//        keko.tulosta();
//        for (int i = 0; i < 8; i++) {
//            assertEquals(tarkistus[i].getX(), keko.getRuudut()[i].getX());
//            assertEquals(tarkistus[i].getY(), keko.getRuudut()[i].getY());
//        }
//    }
//    
//    @Test
//    public void palauttaapienimman(){
//        keko.rakennaKeko();
//        Ruutu pienin = keko.pollPienin();
//        Ruutu tarkistus = new Ruutu(0, 1, 0);
//        assertEquals(tarkistus.getArvo(), pienin.getArvo());
//    }
    
    private Ruutu[] luoTarkistustalukko(){
        Ruutu[] tarkistus = new Ruutu[8];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                tarkistus[i * 4 + j] = p.getRuutu(i, j);
            }
        }
        return tarkistus;
    }
}
