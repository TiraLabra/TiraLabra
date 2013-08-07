/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maef
 */
public class LabyrinttiTest {
    
    public LabyrinttiTest() {
    }
    private Labyrintti labyrintti;
    
    @Before
    public void setUp() {
        labyrintti = new Labyrintti("labyrintti");
    }

    /**
     * Testataan Labyrintti-luokan metodeja.
     */
    
    @Test
    public void asetaLattiaJaSeinaTest(){
         assertEquals(-1, labyrintti.getLattia());
         assertEquals(-16777216, labyrintti.getSeina());
     } 
     
     @Test
     public void etaisyysTest(){
         Solmu a = new Solmu(5,5, labyrintti);
         
         assertEquals(1, labyrintti.etaisyys(a));
         
         a = new Solmu(1,1, labyrintti);
         
         assertEquals(1000000, labyrintti.etaisyys(a));
     }
    
}
