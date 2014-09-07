/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tietorakenteet;

import Tietorakenteet.Jono.Jono;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class JatkuvamonikulmioTest {
    
    public JatkuvamonikulmioTest() {
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

 

    /**
     * Test of palautaJatkuvasolmut method, of class Jatkuvamonikulmio.
     */
    @Test
    public void testPalautaJatkuvasolmut() {
        
        Jono f = new Jono();
        f.lisaa(new Kordinaatti(0,0));
        f.lisaa(new Kordinaatti(1,0));
        f.lisaa(new Kordinaatti(1,1));
        f.lisaa(new Kordinaatti(0,1));
        Jatkuvamonikulmio d = new Jatkuvamonikulmio(f);
        int i = d.palautaJatkuvasolmut().palautaKoko();
        assertEquals(i, 4);
    }
    
}
