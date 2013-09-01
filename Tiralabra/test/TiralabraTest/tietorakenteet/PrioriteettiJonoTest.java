/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TiralabraTest.tietorakenteet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.tietorakenteet.Node;
import tiralabra.tietorakenteet.PrioriteettiJono;

/**
 *
 * @author joonaslongi
 */
public class PrioriteettiJonoTest {
    
    private PrioriteettiJono jono;
    
    public PrioriteettiJonoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.jono = new PrioriteettiJono();
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
    public void testaaTyhjyys(){
        assertEquals(true, jono.tyhja());
        assertEquals(0, jono.koko());
    }
    
    @Test
    public void eiTyhjaKunLisataan(){
        jono.lisaa(new Node(97, 2));
        assertFalse(jono.tyhja());
    }
    
    @Test
    public void tyhjaKunKaikkiOtetaan(){
        jono.lisaa(new Node(97, 2));
        jono.lisaa(new Node(98, 2));
        assertFalse(jono.tyhja());
        jono.ota();
        jono.ota();
        assertTrue(jono.tyhja());
    }
    
    @Test
    public void kokoOikeinKunLisataan(){
        jono.lisaa(new Node(97, 2));
        jono.lisaa(new Node(98, 2));
        assertEquals(2, jono.koko());
    }
    
    @Test
    public void kokoOikeinKunOtetaan(){
        jono.lisaa(new Node(97, 2));
        jono.lisaa(new Node(98, 2));
        assertEquals(2, jono.koko());
        jono.ota();
        assertEquals(1, jono.koko());
    }
    
    @Test
    public void kokoOikeinKunLisataanMonta(){
        jono.lisaa(new Node(97, 2));
        jono.lisaa(new Node(98, 3));
        jono.lisaa(new Node(99, 4));
        jono.lisaa(new Node(100, 5));
        jono.lisaa(new Node(101, 6));
        jono.lisaa(new Node(102, 7));
        assertEquals(6, jono.koko());
    }
    
    @Test
    public void lisaaYhden(){
        jono.lisaa(new Node(97, 2));
        assertEquals(1, jono.koko());
        assertEquals(97, jono.getKeko()[0].getMerkki());
    }
    
    @Test
    public void lisaaMonta(){
        jono.lisaa(new Node(97, 2));
        jono.lisaa(new Node(98, 3));
        jono.lisaa(new Node(99, 4));
        jono.lisaa(new Node(100, 5));
        jono.lisaa(new Node(101, 6));
        jono.lisaa(new Node(102, 7));
        assertEquals(97, jono.getKeko()[0].getMerkki());
        assertEquals(102, jono.getKeko()[5].getMerkki());
    }
    
    @Test
    public void korjaaOikein(){
        jono.lisaa(new Node(97, 2));
        jono.lisaa(new Node(98, 11));
        jono.lisaa(new Node(99, 1));
        jono.lisaa(new Node(100, 2));
        jono.lisaa(new Node(101, 4));
        jono.lisaa(new Node(102, 3));
        assertEquals(99, jono.getKeko()[0].getMerkki());
    }
    
    @Test
    public void ottaaPienimman(){
        jono.lisaa(new Node(97, 2));
        jono.lisaa(new Node(98, 11));
        jono.lisaa(new Node(99, 1));
        jono.lisaa(new Node(100, 2));
        jono.lisaa(new Node(101, 4));
        jono.lisaa(new Node(102, 3));
        assertEquals(99, jono.ota().getMerkki());
        assertEquals(97, jono.ota().getMerkki());

    }
    
    @Test
    public void vaihtaaNodet(){
        jono.lisaa(new Node(97, 2));
        jono.lisaa(new Node(98, 11));
        assertEquals(97, jono.getKeko()[0].getMerkki());
        assertEquals(98, jono.getKeko()[1].getMerkki());
        jono.vaihda(0, 1);
        assertEquals(98, jono.getKeko()[0].getMerkki());
        assertEquals(97, jono.getKeko()[1].getMerkki());
        
    }
}
