
package labyrintti.sovellus;

import java.util.ArrayList;
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
    public void reittiOikein(){
        etsija.aStar();
        ArrayList<Ruutu> reitti = etsija.getReitti();
        assertEquals("0,3", reitti.get(0).koordinaatit());
        assertEquals("0,2", reitti.get(1).koordinaatit());
        assertEquals("0,1", reitti.get(2).koordinaatit());
        assertEquals("1,1", reitti.get(3).koordinaatit());
        assertEquals("1,0", reitti.get(4).koordinaatit());
    }
}