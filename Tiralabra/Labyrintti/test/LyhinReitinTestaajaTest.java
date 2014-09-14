/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import labyrintti.logiikka.LyhinReitti;
import labyrintti.logiikka.Maapala;
import labyrintti.logiikka.Maapalarekisteri;
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
public class LyhinReitinTestaajaTest {
    
    public LyhinReitinTestaajaTest() {
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
    public void palauttaaOikeanNaapurin(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(3, 1, 1, 2, 2);
        maapalarekisteri.luoMaapalat();
        LyhinReitti lyhinReitti = new LyhinReitti(maapalarekisteri);
        
        Maapala maapala = lyhinReitti.getParasNaapuri(maapalarekisteri.getAlku());
        
        assertTrue(maapala.getHValue() == 1);
    }
    
    @Test
    public void palauttaaOikeanNaapurin1(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(4, 1, 1, 3, 3);
        maapalarekisteri.luoMaapalat();
        LyhinReitti lyhinReitti = new LyhinReitti(maapalarekisteri);
        
        Maapala maapala = lyhinReitti.getParasNaapuri(maapalarekisteri.getAlku());
        
        assertTrue(maapala.getHValue() == 3);
    }
}
