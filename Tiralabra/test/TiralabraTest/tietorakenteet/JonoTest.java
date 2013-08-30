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
import tiralabra.tietorakenteet.Jono;

/**
 *
 * @author joonaslongi
 */
public class JonoTest {
    
    private Jono jono;
    
    public JonoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.jono = new Jono();
        
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
        assertEquals(0, jono.getKoko());
    }
    
    @Test
    public void eiTyhjaKunLisataan(){
        jono.lisaa(true);
        assertTrue(!jono.tyhja());
    }
    
    @Test
    public void lisaaYhden(){
        assertEquals(0, jono.getKoko());
        jono.lisaa(true);
        assertEquals(1, jono.getKoko());
    }
    
    @Test
    public void ottaaYhdenOikein(){
        jono.lisaa(true);
        assertEquals(true, jono.ota());
    }
    
    @Test
    public void kokoOikeinKunOtetaan(){
        jono.lisaa(true);
        jono.lisaa(true);
        assertEquals(2, jono.getKoko());
        jono.ota();
        assertEquals(1, jono.getKoko());
    }
    
    @Test
    public void lisaaMonta(){
        assertEquals(0, jono.getKoko());
        jono.lisaa(true);
        jono.lisaa(false);
        jono.lisaa(false);
        jono.lisaa(true);
        jono.lisaa(true);
        assertEquals(5, jono.getKoko());

    }
    
    @Test
    public void eiLisaaLiikaa(){
        assertEquals(0, jono.getKoko());
        for(int i = 0; i < 512; i++){
            jono.lisaa(true);
        }
        jono.lisaa(false);
        assertEquals(512, jono.getKoko());
        assertEquals(true, jono.ota());
    }
    
    @Test
    public void ottaaMontaOikein(){
        jono.lisaa(true);
        jono.lisaa(true);
        jono.lisaa(true);
        jono.lisaa(false);
        jono.lisaa(false);
        jono.lisaa(true);
        jono.lisaa(true);
        assertEquals(true, jono.ota());
        assertEquals(true, jono.ota());
        assertEquals(true, jono.ota());
        assertEquals(false, jono.ota());
        assertEquals(false, jono.ota());
        assertEquals(true, jono.ota());
        assertEquals(true, jono.ota());
    }
}
