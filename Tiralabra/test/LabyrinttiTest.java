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
    private Labyrintti laby;
    
    @Before
    public void setUp() {
        labyrintti = new Labyrintti("labyrintti");
        laby = new Labyrintti("labyTest5x5");
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
         Solmu a = new Solmu(5,5, labyrintti.verkko);
         
         assertEquals(1, labyrintti.etaisyys(a));
         
         a = new Solmu(1,1, labyrintti.verkko);
         
         assertEquals(1000000, labyrintti.etaisyys(a));
     }
     
     @Test
     public void getHeight(){
         assertEquals(350, labyrintti.getHeight());
     }
     
     @Test
     public void getWidth() {
         assertEquals(500, labyrintti.getWidth());
     }
     
     @Test
    public void testKuvanLuentaJaLabyrintinLuonti(){
         assertTrue(laby.getWidth()==5);
         assertTrue(laby.getHeight()==5);
         
         assertFalse(laby.verkko[0][0].seina);
         assertTrue(laby.verkko[0][1].seina);
         assertFalse(laby.verkko[0][2].seina);
         assertFalse(laby.verkko[0][3].seina);
         assertFalse(laby.verkko[0][4].seina);
         
         assertFalse(laby.verkko[1][0].seina);
         assertTrue(laby.verkko[1][1].seina);
         assertTrue(laby.verkko[1][2].seina);
         assertFalse(laby.verkko[1][3].seina);
         assertFalse(laby.verkko[1][4].seina);
         
         assertFalse(laby.verkko[2][0].seina);
         assertFalse(laby.verkko[2][1].seina);
         assertTrue(laby.verkko[2][2].seina);
         assertFalse(laby.verkko[2][3].seina);
         assertFalse(laby.verkko[2][4].seina);
         
         assertFalse(laby.verkko[3][0].seina);
         assertFalse(laby.verkko[3][1].seina);
         assertTrue(laby.verkko[3][2].seina);
         assertFalse(laby.verkko[3][3].seina);
         assertFalse(laby.verkko[3][4].seina);
         
         assertFalse(laby.verkko[4][0].seina);
         assertFalse(laby.verkko[4][1].seina);
         assertFalse(laby.verkko[4][2].seina);
         assertFalse(laby.verkko[4][3].seina);
         assertFalse(laby.verkko[4][4].seina);
     }
}
