
package com.mycompany.tiralabra_maven.tietorakenteet;

import com.mycompany.tiralabra_maven.automaatit.Tila;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public class TilasiirtymakarttaTest {
    
    public TilasiirtymakarttaTest() {
    }
    
    private Tilasiirtymakartta hk;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        hk = new Tilasiirtymakartta(3);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testHaeEnsimmainen() {
        Tila t = new Tila(false), u = new Tila(true), v = new Tila(false);
        hk.lisaa('a', t);
        hk.lisaa('b', t);
        hk.lisaa('a', u);
        hk.lisaa('a', v);
        hk.lisaa(' ', t);
        assertEquals(t, hk.haeEnsimmainen('a'));
    }
    
    @Test
    public void testHaeKaikki() {
        Tila t = new Tila(false), u = new Tila(true), v = new Tila(false);
        hk.lisaa('a', t);
        hk.lisaa('b', t);
        hk.lisaa('a', u);
        hk.lisaa('a', v);
        hk.lisaa(' ', t);
        Jono<Tila> oikeaVastaus = new Jono<>();
        oikeaVastaus.lisaa(t);
        oikeaVastaus.lisaa(u);
        oikeaVastaus.lisaa(v);
        AvainArvoJonoTest.vertaaJonoja(oikeaVastaus, hk.haeKaikki('a'));
        assertNull(hk.haeKaikki('z'));
    }
    
    @Test
    public void testTayttosuhde() {
        Tila t = new Tila(false), u = new Tila(true), v = new Tila(false);
        assertEquals(0, hk.tayttosuhde(), 0.000001);
        hk.lisaa('c', t);
        assertEquals(1 / 3.0, hk.tayttosuhde(), 0.000001);
        hk.lisaa('g', u);
        hk.lisaa('a', v);
        assertEquals(1, hk.tayttosuhde(), 0.000001);
    }
    
    @Test
    public void testUudelleenhajauta() {
        Tila.nollaaTilalaskuri();
        Tila t = new Tila(false), u = new Tila(true), v = new Tila(false);
        hk.lisaa('a', t);
        hk.lisaa('b', t);
        hk.lisaa('c', u);
        hk.lisaa('d', v);
        hk.lisaa('e', t);
        hk.uudelleenhajauta(5);
        assertEquals("{{c\u21A6[1]<...>},(a\u21A6[0]<...>,d\u21A6[2]<...>),"
                + "(b\u21A6[0]<...>,e\u21A6[0]<...>),\u2205,\u2205}",
                hk.toString());
    }
    
}