
package com.mycompany.tiralabra_maven.tietorakenteet;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author johnny
 */
public class HajautuskarttaTest {
    
    /**
     *
     */
    public HajautuskarttaTest() {
    }
    
    private Hajautuskartta h;
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    @Before
    public void setUp() {
        h = new Hajautuskartta(3);
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     *
     */
    @Test
    public void testLisaaJaHae() {
        h.lisaa('a', 1);
        h.lisaa('b', 2);
        h.lisaa('c', 3);
        h.lisaa('d', 4);
        h.lisaa('e', 5);
        h.lisaa('f', 6);
        h.lisaa('g', 7);
        h.lisaa('h', 8);
        h.lisaa('i', 9);
        h.lisaa('j', 0);
        h.lisaa('k', 1);
        h.lisaa('l', 2);
        assertEquals(5, h.hae('e'));
    }
    
    /**
     *
     */
    @Test
    public void testToString() {
        String oikeaVastaus = "{(c\u21A63,f\u21A66,i\u21A69,l\u21A62),"
                + "{a\u21A61},\u2205}";
        h.lisaa('a', 1);
        h.lisaa('c', 3);
        h.lisaa('f', 6);
        h.lisaa('i', 9);
        h.lisaa('l', 2);
        assertEquals(oikeaVastaus, h.toString());
    }
}