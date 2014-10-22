/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import labyrintti.gui.Nappula;
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
public class NappulaTest {
    
    public NappulaTest() {
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
    public void nappulallaOikeaXKoordinaatti(){
        Nappula nappula = new Nappula(3, 4);
        assertTrue(nappula.getXKoordinaatti() == 3);
    }
    
    @Test
    public void nappulallaOikeaYKoordinaatti(){
        Nappula nappula = new Nappula(6,4);
        assertTrue(nappula.getYKoordinaatti() == 4);
    }
    @Test
    public void nappulaAvattu(){
        Nappula nappula = new Nappula(2,6);
        nappula.avaa();
        assertTrue(nappula.getAvattu());
    }
    @Test
    public void nappulaAluksiAvaamaton(){
        Nappula nappula = new Nappula(3,3);
        assertFalse(nappula.getAvattu());
    }
}
