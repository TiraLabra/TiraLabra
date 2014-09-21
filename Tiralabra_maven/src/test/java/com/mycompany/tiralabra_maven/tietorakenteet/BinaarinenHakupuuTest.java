package com.mycompany.tiralabra_maven.tietorakenteet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class BinaarinenHakupuuTest {

    BinaarinenHakupuu t;

    public BinaarinenHakupuuTest() {
    }

    @Before
    public void setUp() {
        t = new BinaarinenHakupuu();
    }

    /**
     * Test of lisaa method, of class BinaarinenHakupuu.
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
     * Test of lisaaKaikki method, of class BinaarinenHakupuu.
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
     * Test of hae method, of class BinaarinenHakupuu.
     */
    @Test
    public void testHae() {
        t.lisaa(5);
        assertEquals(true, t.hae(5));
        int[] a = {1, 2, 3};
        t.lisaaKaikki(a);
        assertEquals(true, t.hae(1));
        assertEquals(true, t.hae(2));
        assertEquals(true, t.hae(3));
        assertEquals(false, t.hae(8));
    }

    /**
     * Test of haeSolmu method, of class BinaarinenHakupuu.
     */
    @Test
    public void testHaeSolmu() {
        assertEquals(null, t.haeSolmu(5));
        t.lisaa(5);
        assertEquals(true, t.haeSolmu(5) instanceof BinaarinenPuusolmu);
    }

    /**
     * Test of poista method, of class BinaarinenHakupuu.
     */
    @Test
    public void testPoista() {
        t.lisaa(5);
        t.poista(5);
        assertEquals(false, t.hae(5));
    }

    /**
     * Test of getJuuri method, of class BinaarinenHakupuu.
     */
    @Test
    public void testGetJuuri() {
        assertEquals(null, t.getJuuri());
        t.lisaa(5);
        assertEquals(true, t.getJuuri() instanceof BinaarinenPuusolmu);
    }

    /**
     * Test of onTyhja method, of class BinaarinenHakupuu.
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
