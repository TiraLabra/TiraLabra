/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import labyrintti.logiikka.Maapala;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class MaapalojenTestaajaTest {

    public MaapalojenTestaajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void maapalallaOikeaHeuristinenArvo() {
        Maapala maapala = new Maapala(1, 1);
        
        maapala.alustaArvot(2);

        assertTrue(maapala.getHArvo() == 2);
    }

    @Test
    public void maapalallaOikeaHeuristinenArvo1() {
        Maapala maapala = new Maapala(1, 1);
        maapala.alustaArvot(1000);

        assertTrue(maapala.getHArvo() == 1000);
    }
    @Test
    public void maapalaMuuttuuSeinaksi(){
        Maapala maapala = new Maapala(0, 0);
        maapala.asetaSeinaksi();
        
        assertTrue(maapala.onkoSeina());
    }
    
    @Test
    public void maapalaMuuttuuLapaistavaksi(){
        Maapala maapala = new Maapala (0, 0);
        
        maapala.asetaSeinaksi();
        assertTrue(maapala.onkoSeina());
        
        maapala.asetaLapaistavaksi();
        assertFalse(maapala.onkoSeina());
    }
    
    @Test
    public void maapalallaOikeaXKoordinaatti(){
        Maapala maapala = new Maapala (0, 2);
        
        assertTrue(maapala.getX() == 0);
    }
    
    @Test
    public void maapalallaOikeaYKoordinaatti(){
         Maapala maapala = new Maapala (1, 3);
        
         assertTrue(maapala.getY() == 3);
    }
    
    @Test
    public void maapalaEiKuuluAluksiAvoimeenListaan(){
        Maapala maapala = new Maapala (1, 3);
        
        assertFalse(maapala.onkoAvoimellaListalla());
    }
    
    @Test
    public void maapalaSiirtyyOikeinAvoimeenListaan(){
        Maapala maapala = new Maapala (1, 3);
        assertFalse(maapala.onkoAvoimellaListalla());
    
        maapala.siirraAvoimelleListalle();
        assertTrue(maapala.onkoAvoimellaListalla());
        
    }
    
    @Test
    public void maapalaPoistuuAvoimeltaListalta(){
        Maapala maapala = new Maapala (1, 3);
        
        maapala.siirraAvoimelleListalle();
        assertTrue(maapala.onkoAvoimellaListalla());
    
        maapala.poistaAvoimeltaListalta();
        assertFalse(maapala.onkoAvoimellaListalla());
    }
    
    @Test
    public void maapalaEiKuuluAluksiSuljettuunListaan(){
        Maapala maapala = new Maapala (1, 3);
        
        assertFalse(maapala.onkoSuljetullaListalla());
    }
    
    @Test
    public void maapalaSiirtyyOikeinSuljettuunListaan(){
        Maapala maapala = new Maapala (1, 3);
        assertFalse(maapala.onkoSuljetullaListalla());
    
        maapala.siirraSuljetulleListalle();
        assertTrue(maapala.onkoSuljetullaListalla());
    }
    
    @Test
    public void maapalaPoistuuSuljetultaListaltaOikein(){
        Maapala maapala = new Maapala (1, 3);
        maapala.siirraSuljetulleListalle();
        assertTrue(maapala.onkoSuljetullaListalla());
    
        maapala.poistaSuljetultaListalta();
        assertFalse(maapala.onkoSuljetullaListalla());
    }
    
    @Test
    public void oikeaVanhempi(){
        Maapala maapala = new Maapala (1, 3);
        Maapala maapala1 = new Maapala (1, 2);
        
        maapala.setVanhempi(maapala1);
        
        assertTrue(maapala.getVanhempi().getX() == 1);
        assertTrue(maapala.getVanhempi().getY() == 2);
    }
    
}
