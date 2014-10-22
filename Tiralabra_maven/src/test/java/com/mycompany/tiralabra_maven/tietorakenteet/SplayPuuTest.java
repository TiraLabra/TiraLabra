package com.mycompany.tiralabra_maven.tietorakenteet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class SplayPuuTest {

    SplayPuu t;

    public SplayPuuTest() {
    }

    @Before
    public void setUp() {
        t = new SplayPuu();
    }

    @Test
    public void testLisaa() {
        try {
            t.lisaa(5);
        } catch (Exception e) {
            fail("Jokin meni pieleen");
        }
        assertEquals(5, t.getJuuri().getAvain());
        assertEquals(1, t.getKoko());
        assertEquals(true, t.hae(5));
    }

    @Test
    public void testLisaaKaikki() {
        int[] a = {1, 2, 3, 4, 5};
        try {
            t.lisaaKaikki(a);
        } catch (Exception e) {
            fail("Jokin meni pieleen");
        }
        assertEquals(5, t.getJuuri().getAvain());
        assertEquals(a.length, t.getKoko());
        for (int i : a) {
            assertEquals(true, t.hae(i));
        }
    }

    @Test
    public void testHae() {
        assertEquals(false, t.hae(5));
        t.lisaa(5);
        assertEquals(true, t.hae(5));
        int[] a = {1, 2, 3, 4, 5};
        t.lisaaKaikki(a);
        assertTrue(t.hae(3));
        assertEquals(3, t.getJuuri().getAvain());
        assertEquals(4, t.getJuuri().getOikea().getAvain());
        assertEquals(2, t.getJuuri().getVasen().getAvain());
        assertTrue(t.hae(1));
        assertEquals(1, t.getJuuri().getAvain());
        assertEquals(2, t.getJuuri().getOikea().getAvain());
        assertEquals(null, t.getJuuri().getVasen());

    }

    @Test
    public void testHaeSolmu() {
        assertEquals(null, t.haeSolmu(5));
        t.lisaa(5);
        assertEquals(true, t.haeSolmu(5) instanceof BinaarinenPuusolmu);
        assertEquals(5, t.haeSolmu(5).getAvain());
        t.lisaa(3);
        assertEquals(3, t.getJuuri().getAvain());
        t.hae(5);
        assertEquals(5, t.getJuuri().getAvain());
    }

    @Test
    public void testPoista() {
        assertEquals(false, t.hae(5));
        t.lisaa(1);
        t.lisaa(2);
        t.poista(1);
        assertEquals(2, t.getJuuri().getAvain());
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        t.lisaaKaikki(a);
        t.poista(5);
        assertEquals(false, t.hae(5));
        assertEquals(a.length - 1, t.getKoko());
        assertEquals(4, t.getJuuri().getAvain());
        assertEquals(8, t.getJuuri().getOikea().getAvain());
        assertEquals(6, t.getJuuri().getOikea().getVasen().getAvain());
        assertEquals(3, t.getJuuri().getVasen().getAvain());
    }

    @Test
    public void testGetJuuri() {
        assertEquals(null, t.getJuuri());
        t.lisaa(5);
        assertEquals(true, t.getJuuri() instanceof BinaarinenPuusolmu);
    }

    @Test
    public void testOnTyhja() {
        assertEquals(true, t.onTyhja());
        t.lisaa(5);
        assertEquals(false, t.onTyhja());
        t.poista(5);
        assertEquals(true, t.onTyhja());
    }

    @Test
    public void testTyhjenna() {
        assertEquals(true, t.onTyhja());
        t.lisaa(5);
        assertEquals(false, t.onTyhja());
        t.tyhjenna();
        assertEquals(true, t.onTyhja());
    }

    @Test
    public void testGetKorkeus() {
        assertEquals(0, t.getKorkeus());
        t.lisaa(0);
        assertTrue(1 == t.getKorkeus());
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        t.lisaaKaikki(a);
        assertTrue(a.length >= t.getKorkeus());
    }

    @Test
    public void testGetKoko() {
        assertEquals(0, t.getKoko());
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        t.lisaaKaikki(a);
        assertEquals(a.length, t.getKoko());
    }

    @Test
    public void testGetNimi() {
        assertTrue(t.getNimi() instanceof String);
        assertFalse(t.getNimi().equalsIgnoreCase(""));
    }
}
