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
        int[] a = {1, 2, 3, 0};
        try {
            t.lisaaKaikki(a);
        } catch (Exception e) {
            fail("Jokin meni pieleen");
        }
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
        assertEquals(true, t.haeSolmu(5) instanceof AvlPuusolmu);
        assertEquals(5, t.haeSolmu(5).getAvain());
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
        assertEquals(true, t.hae(5));
        t.poista(5);
        assertEquals(false, t.hae(5));
        assertEquals(a.length - 1, t.getKoko());
        for (int i : a) {
            if (i != 5) {
                assertEquals(true, t.hae(i));
            }
        }

        t.poista(1);
        t.poista(9);
        assertEquals(a.length - 3, t.getKoko());
        for (int i : a) {
            if ((i != 5 && i != 1) && i != 9) {
                assertEquals(true, t.hae(i));
            }
        }
    }

    @Test
    public void testGetJuuri() {
        assertEquals(null, t.getJuuri());
        t.lisaa(5);
        assertEquals(true, t.getJuuri() instanceof AvlPuusolmu);
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
