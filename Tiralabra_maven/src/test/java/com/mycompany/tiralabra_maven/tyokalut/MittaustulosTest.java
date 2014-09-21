
package com.mycompany.tiralabra_maven.tyokalut;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class MittaustulosTest {
    
    Mittaustulos t;
    
    public MittaustulosTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        t = new Mittaustulos();
    }
    
    @After
    public void tearDown() {
    }



    /**
     * Test of getPienin method, of class Mittaustulos.
     */
    @Test
    public void testGetPienin() {       
        assertEquals(Long.MAX_VALUE, t.getPienin());
        t.lisaaAika(5);
        assertEquals(5, t.getPienin());
    }

    /**
     * Test of getSuurin method, of class Mittaustulos.
     */
    @Test
    public void testGetSuurin() {
        assertEquals(Long.MIN_VALUE, t.getSuurin());
        t.lisaaAika(5);
        assertEquals(5, t.getSuurin());
    }

    /**
     * Test of getKeskiarvo method, of class Mittaustulos.
     */
    @Test
    public void testGetKeskiarvo() {      
        assertEquals(0, t.getKeskiarvo());
        t.lisaaAika(100);
        assertEquals(100, t.getKeskiarvo());
        t.lisaaAika(100);
        t.lisaaAika(10);
        assertEquals(70, t.getKeskiarvo());
    }

    /**
     * Test of lisaaAika method, of class Mittaustulos.
     */
    @Test
    public void testLisaaAika() {        
        t.lisaaAika(0);
        t.lisaaAika(0);
        t.lisaaAika(0);
        t.lisaaAika(0);
        t.lisaaAika(0);
        t.lisaaAika(100);
        assertEquals(100, t.getKeskiarvo());
    }
    
}
