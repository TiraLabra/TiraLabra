
package com.mycompany.tiralabra_maven.tietorakenteet;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public class AvainArvoJonoTest {
    
    private AvainArvoJono<Character, Integer> aajono;
    private Random arpoja;
    
    public AvainArvoJonoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.aajono = new AvainArvoJono<>();
        this.arpoja = new Random();
    }
    
    @After
    public void tearDown() {
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

    @Test
    public void testAvainjono() {
        assertNull(aajono.avainjono());
        
        Jono<Character> odotettuAvainjono = new Jono<>();
        odotettuAvainjono.lisaa('a');
        odotettuAvainjono.lisaa('b');
        odotettuAvainjono.lisaa('c');
        odotettuAvainjono.lisaa('b');
        
        aajono.lisaa('a', 1);
        aajono.lisaa('b', 1);
        aajono.lisaa('c', 2);
        aajono.lisaa('b', 2);   
        Jono saatuAvainjono = aajono.avainjono(); 
        
        vertaaJonoja(odotettuAvainjono, saatuAvainjono);
    }

    @Test
    public void testArvojono() {
        assertNull(aajono.arvojono());
        
        Jono<Integer> odotettuAvainjono = new Jono<>();
        odotettuAvainjono.lisaa(1);
        odotettuAvainjono.lisaa(1);
        odotettuAvainjono.lisaa(2);
        odotettuAvainjono.lisaa(2);
        
        aajono.lisaa('a', 1);
        aajono.lisaa('b', 1);
        aajono.lisaa('c', 2);
        aajono.lisaa('b', 2);    
        Jono saatuAvainjono = aajono.arvojono(); 
        
        vertaaJonoja(odotettuAvainjono, saatuAvainjono);
    }

//    @Test
//    public void testPituus() {
//        int lisaykset = arpoja.nextInt(10000);
//        
//        for (int i = 0; i < lisaykset; i++) {
//            aajono.lisaa('a', i);
//        }
//        
//        assertTrue(lisaykset == aajono.pituus());
//    }
    
    private void vertaaJonoja(Jono a, Jono b) {
        Object o, p;
        while (!a.onTyhja()) {
            o = a.poista();
            p = b.poista();
            assertEquals(o, p);
        }
    }
}