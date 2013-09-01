
package com.mycompany.tiralabra_maven.tietorakenteet;

import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AvainArvoJonoTest {
    
    private AvainArvoJono<Character, Integer> aajono;
    private Random arpoja;
    
    public AvainArvoJonoTest() {
    }
    
    @Before
    public void setUp() {
        this.aajono = new AvainArvoJono<>();
        this.arpoja = new Random();
    }
    
    @Test
    public void testLisaa() {
        aajono.lisaa('a', 1);
//        assertTrue(aajono.pituus() == 1);
        aajono.lisaa('b', 1);
//        assertTrue(aajono.pituus() == 2);
        aajono.lisaa('b', 2);
//        assertTrue(aajono.pituus() == 3);
        aajono.lisaa('b', 2);
//        assertTrue(aajono.pituus() == 4);
    }

    @Test
    public void testHae() {
        aajono.lisaa('a', 4);
        assertEquals((Object) aajono.hae('a'), 4);
        aajono.lisaa('b', 2);
        assertEquals((Object) aajono.hae('b'), 2);
        aajono.lisaa('a', 3);
        assertEquals((Object) aajono.hae('a'), 4);
    }

    @Test
    public void testToString() {
        String oikeaVastaus = "\u2205";
        assertEquals(oikeaVastaus, aajono.toString());
        
        oikeaVastaus = "{a\u21A61}";
        aajono.lisaa('a', 1);
        assertEquals(oikeaVastaus, aajono.toString());
        
        oikeaVastaus = "(a\u21A61,c\u21A63,f\u21A66,i\u21A69,"
                + "l\u21A62)";
        aajono.lisaa('c', 3);
        aajono.lisaa('f', 6);
        aajono.lisaa('i', 9);
        aajono.lisaa('l', 2);
        assertEquals(oikeaVastaus, aajono.toString());
    }
}