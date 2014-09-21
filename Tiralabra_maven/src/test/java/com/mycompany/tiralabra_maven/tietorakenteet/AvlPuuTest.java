package com.mycompany.tiralabra_maven.tietorakenteet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class AvlPuuTest {

    public AvlPuuTest() {
    }

    AvlPuu t;

    @Before
    public void setUp() {
        t = new AvlPuu();
    }

    /**
     * Test of lisaa method, of class AvlPuu.
     */
    @Test
    public void testLisaa() {
        try {
            t.lisaa(5);
        } catch (Exception e) {
            fail("Jokin meni pieleen");
        }
    }

    /**
     * Test of lisaaKaikki method, of class AvlPuu.
     */
    @Test
    public void testLisaaKaikki() {
        int[] a = {1, 2, 3};
        try {
            t.lisaaKaikki(a);
        } catch (Exception e) {
            fail("Jokin meni pieleen");
        }
    }

    /**
     * Test of hae method, of class AvlPuu.
     */
    @Test
    public void testHae() {
        t.lisaa(5);
        assertEquals(false, t.hae(8));
        assertEquals(true, t.hae(5));
    }

    /**
     * Test of haeSolmu method, of class AvlPuu.
     */
    @Test
    public void testHaeSolmu() {
        t.lisaa(5);
        assertEquals(true, t.haeSolmu(5) instanceof AvlPuusolmu);
        assertEquals(true, t.haeSolmu(5) != null);
        assertEquals(null, t.haeSolmu(6));
    }

    /**
     * Test of poista method, of class AvlPuu.
     */
    @Test
    public void testPoista() {
        t.lisaa(5);
        t.poista(5);
        assertEquals(false, t.hae(5));
    }

    /**
     * Test of onTyhja method, of class AvlPuu.
     */
    @Test
    public void testOnTyhja() {
        assertEquals(true, t.onTyhja());
        t.lisaa(5);
        assertEquals(false, t.onTyhja());
        t.poista(5);
        assertEquals(true, t.onTyhja());

    }

}
