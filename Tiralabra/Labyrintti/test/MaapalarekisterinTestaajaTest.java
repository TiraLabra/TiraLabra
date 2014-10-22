/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class MaapalarekisterinTestaajaTest {
    
    public MaapalarekisterinTestaajaTest() {
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
    public void palauttaaOikeanAloitusnodenXKoordinaatin(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(3, 1, 0, 2, 1);
        maapalarekisteri.luoMaapalat();
        maapalarekisteri.alustaMaapalat();
        
        assertTrue(maapalarekisteri.getAlkuX() == 1);
        
    }
    
    @Test
    public void palauttaaOikeanAloitusnodenYkoordinaatin(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(3, 1, 0, 2, 1);
        maapalarekisteri.luoMaapalat();
        maapalarekisteri.alustaMaapalat();
        
        assertTrue(maapalarekisteri.getAlkuY() == 0);
    }
    
    @Test
    public void palauttaaOikeanLopetusnodenXKoordinaatin(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(3, 1, 0, 2, 1);
        maapalarekisteri.luoMaapalat();
        maapalarekisteri.alustaMaapalat();
        
        assertTrue(maapalarekisteri.getLoppuX() == 2);
    }
    
    @Test
    public void palauttaaOikeanLopetusnodenYKoordinaatin(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(3, 1, 0, 2, 1);
        maapalarekisteri.luoMaapalat();
        maapalarekisteri.alustaMaapalat();
        
        assertTrue(maapalarekisteri.getLoppuY() == 1);
    }
    
    @Test
    public void asettaaOikeanHeuristisenArvon(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(2, 0, 0, 1, 1);
        maapalarekisteri.luoMaapalat();
        maapalarekisteri.alustaMaapalat();
        
        assertTrue(maapalarekisteri.getAlku().getHArvo() == 2);
    }
    
    @Test
    public void asettaaOikeanHeuristisenArvon1(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(2, 1, 0, 1, 1);
        maapalarekisteri.luoMaapalat();
        maapalarekisteri.alustaMaapalat();
        
        assertTrue(maapalarekisteri.getAlku().getHArvo() == 1);
    }
    
    @Test
    public void asettaaOikeanMaapalan(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(2, 0, 0, 1, 1);
        maapalarekisteri.luoMaapalat();
        maapalarekisteri.alustaMaapalat();
        Maapala maapala = maapalarekisteri.getMaapala(0, 0);
        
        assertTrue(maapala.getHArvo() == 2);
    }
    
    @Test
    public void palauttaaOikeanKoon(){
        Maapalarekisteri maapalarekisteri = new Maapalarekisteri(2, 0, 0, 1, 1);
        assertTrue(maapalarekisteri.getKoko() == 2);
    }
}
