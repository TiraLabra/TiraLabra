
package labyrintti.sovellus;

import labyrintti.osat.Pohja;
import labyrintti.osat.Ruutu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Asus
 */
public class EtsijaTest {
    
    private Etsija etsija;
    
    public EtsijaTest() {
    }
    
    @Before
    public void setUp() {
        Pohja pohja = new Pohja();
        pohja.alustaPohja("src/main/java/labyrintti/osat/kartta2.txt");
        etsija = new Etsija(pohja);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void alustusOikein() {
        etsija.alustus();
        assertEquals(8, etsija.getKaymattomat().getKeonKoko());
        Pohja p = etsija.getPohja();
        assertEquals(0, p.getRuutu(p.getLahtoX(), p.getLahtoY()).getArvo());
        assertEquals(0, p.getRuutu(p.getMaaliX(), p.getMaaliY()).getArvo());
    }
    
    @Test
    public void aStarToimii(){
        etsija.aStar();
        Pohja p = etsija.getPohja();
        assertTrue(p.getRuutu(p.getMaaliX(), p.getMaaliY()).onkoKayty());
        assertEquals(3, etsija.getKaymattomat().getKeonKoko());
    }
    
    @Test
    public void kekoJarjestaaRuudutOikein(){
        etsija.alustus();
        Minimikeko keko = etsija.getKaymattomat();
        assertEquals("(0,3) (1,0) (1,1) (0,1) (0,0) (0,2) (1,2) (1,3)", keko.getAlkiot());
    }
    
    @Test
    public void palauttaaPienimman(){
        etsija.alustus();
        Minimikeko keko = etsija.getKaymattomat();
        Ruutu pienin = keko.pollPienin();
        assertEquals(0, pienin.getArvo());
        assertEquals(0, pienin.getX());
        assertEquals(3, pienin.getY());
        assertEquals(7, keko.getKeonKoko());
        assertEquals("(1,0) (0,0) (1,1) (0,1) (1,3) (0,2) (1,2)", keko.getAlkiot());
    }
    
    @Test
    public void reittiOikein(){
        etsija.aStar();
        etsija.getReittiMerkkijonona();
        assertEquals("(0,3) (0,2) (0,1) (1,1) (1,0)", etsija.getReittiMerkkijonona());
        etsija.tallennaReittiTaulukkoon();
        assertEquals(5, etsija.getReitinPituus());
        assertTrue(etsija.onkoRuutuReitilla(0, 2));
        assertTrue(!etsija.onkoRuutuReitilla(0, 0));
    }
}