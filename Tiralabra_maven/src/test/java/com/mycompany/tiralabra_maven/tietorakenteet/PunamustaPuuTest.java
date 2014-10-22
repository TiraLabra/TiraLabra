package com.mycompany.tiralabra_maven.tietorakenteet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class PunamustaPuuTest {

    PunamustaPuu t;
    int[] a;

    public PunamustaPuuTest() {
    }

    @Before
    public void setUp() {
        t = new PunamustaPuu();
        a = new int[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
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
        int[] a = new int[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        try {
            t.lisaaKaikki(a);
        } catch (Exception e) {
            fail("Jokin meni pieleen");
        }
        assertEquals(a.length, t.getKoko());
        for (int i : a) {
            assertEquals(true, t.hae(i));
        }
        assertTrue(t.getKorkeus() <= 2 * (Math.log(a.length) / Math.log(2)));
    }

    @Test
    public void testHae() {
        assertEquals(false, t.hae(5));
        t.lisaa(5);
        assertEquals(true, t.hae(5));
        int[] a = {1, 2, 3};
        t.lisaaKaikki(a);
        assertEquals(true, t.hae(1));
        assertEquals(true, t.hae(2));
        assertEquals(true, t.hae(3));
        assertEquals(false, t.hae(8));
        assertEquals(false, t.hae(0));
    }

    @Test
    public void testHaeSolmu() {
        assertEquals(null, t.haeSolmu(5));
        t.lisaa(5);
        assertEquals(true, t.haeSolmu(5) instanceof PunamustaPuusolmu);
        assertEquals(5, t.haeSolmu(5).getAvain());
    }

    @Test
    public void testPoista() {
        assertEquals(false, t.hae(5));
        t.lisaa(1);
        t.lisaa(2);
        t.poista(1);
        assertEquals(2, t.getJuuri().getAvain());
        t.lisaaKaikki(a);
        assertEquals(true, t.hae(5));
        t.poista(5);
        assertEquals(false, t.hae(5));
        for (int i = 0; i < a.length / 2; i++) {
            t.poista(i);
        }
        assertTrue(t.getKoko() == a.length - (a.length / 2));
        assertTrue(t.getKorkeus() <= 2 * (Math.log(t.getKoko()) / Math.log(2)));
    }

    @Test
    public void testGetJuuri() {
        assertEquals(null, t.getJuuri());
        t.lisaa(5);
        assertEquals(true, t.getJuuri() instanceof PunamustaPuusolmu);
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
