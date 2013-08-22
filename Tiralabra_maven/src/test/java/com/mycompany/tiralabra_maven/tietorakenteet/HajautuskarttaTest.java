
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
    
    private Hajautuskartta hk;
    
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
        hk = new Hajautuskartta(3);
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testEpakelpoKonstruktorinParametri1() {
        Hajautuskartta a = new Hajautuskartta(-3);
    }
    
//    @Test(expected = IllegalArgumentException.class)
//    public void testEpakelpoKonstruktorinParametri2() {
//        Hajautuskartta a = new Hajautuskartta();
//    }

    /**
     *
     */
    @Test
    public void testLisaaJaHae() {
        hk.lisaa('a', 1);
        hk.lisaa('b', 2);
        hk.lisaa('c', 3);
        hk.lisaa('d', 4);
        hk.lisaa('e', 5);
        hk.lisaa('f', 6);
        hk.lisaa('g', 7);
        hk.lisaa('h', 8);
        hk.lisaa('i', 9);
        hk.lisaa('j', 0);
        hk.lisaa('k', 1);
        hk.lisaa('l', 2);
        hk.lisaa(' ', 2);
        hk.lisaa(' ', 3);
        assertEquals(5, hk.haeEnsimmainen('e'));
        assertEquals(2, hk.haeEnsimmainen(' '));
    }
    
    @Test
    public void testHaeKaikki() {
        hk.lisaa('a', 4);
        hk.lisaa('b', 5);
        hk.lisaa('a', 3);
        hk.lisaa('a', 3);
        hk.lisaa(' ', 16);
        Jono<Integer> oikeaVastaus = new Jono<>();
        oikeaVastaus.lisaa(4);
        oikeaVastaus.lisaa(3);
        oikeaVastaus.lisaa(3);
        AvainArvoJonoTest.vertaaJonoja(oikeaVastaus, hk.haeKaikki('a'));
        assertNull(hk.haeKaikki('z'));
    }
    
    /**
     *
     */
    @Test
    public void testToString() {
        String oikeaVastaus = "{(c\u21A63,f\u21A66,i\u21A69,l\u21A62),"
                + "{a\u21A61},\u2205}";
        hk.lisaa('a', 1);
        hk.lisaa('c', 3);
        hk.lisaa('f', 6);
        hk.lisaa('i', 9);
        hk.lisaa('l', 2);
        assertEquals(oikeaVastaus, hk.toString());
    }
    
    @Test
    public void testTayttosuhde() {
        assertEquals(0, hk.tayttosuhde(), 0.000001);
        hk.lisaa('c', 8);
        assertEquals(1 / 3.0, hk.tayttosuhde(), 0.000001);
        hk.lisaa('g', 3);
        hk.lisaa('a', 8);
        assertEquals(1, hk.tayttosuhde(), 0.000001);
    }
    
    @Test
    public void testUudelleenhajauta() {
        hk.lisaa('/', 1);
        hk.lisaa('*', 1);
        hk.lisaa('%', 1);
        hk.lisaa('+', 2);
        hk.lisaa('-', 2);
        hk.uudelleenhajauta(5);
        assertEquals("{{-\u21A62},\u2205,(*\u21A61,%\u21A61,/\u21A61),{+\u21A62},\u2205}", hk.toString());
    }
}