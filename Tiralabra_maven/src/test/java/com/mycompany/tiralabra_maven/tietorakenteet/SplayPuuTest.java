package com.mycompany.tiralabra_maven.tietorakenteet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class SplayPuuTest {

    SplayPuu p;

    public SplayPuuTest() {
    }

    @Before
    public void setUp() {
        p = new SplayPuu();
    }

    /**
     * Test of lisaa method, of class SplayPuu.
     */
    @Test
    public void testLisaa() {
        try {
            p.lisaa(5);
            p.lisaa(1);
            p.lisaa(100);
            p.lisaa(5);

        } catch (Exception e) {
            fail("Lisäys kaataa ohjelman");
        }

    }

    /**
     * Test of lisaaKaikki method, of class SplayPuu.
     */
    @Test
    public void testLisaaKaikki() {
        try {
            int[] a = {1, 2, 3};
            p.lisaaKaikki(a);
        } catch (Exception e) {
            fail("arrayn lisäys kaataa ohjelman!");
        }

    }

    /**
     * Test of hae method, of class SplayPuu.
     */
    @Test
    public void testHae() {
        assertFalse("Haku löytää olemattomia!", p.hae(1));
        assertFalse("Haku löytää olemattomia!", p.hae(100));
        assertFalse("Haku löytää olemattomia!", p.hae(-11));
        int[] a = {1, 2, 3, 4, 5, 6};
        p.lisaaKaikki(a);
        for (int i : a) {
            assertTrue("Kaikkea lisättyä ei löydy", p.hae(i));
        }
        p.poista(3);
        p.poista(1);
        for (int i : a) {
            if (i != 1 && i != 3) {
                assertTrue("Kaikkea lisättyä ei löydy", p.hae(i));
            } else {
                assertFalse("Haku löytää olemattomia!", p.hae(i));
            }
        }
    }

    /**
     * Test of poista method, of class SplayPuu.
     */
    @Test
    public void testPoista() {
        int a[] = {5, 2, 6, 1, 7};
        p.lisaaKaikki(a);
        p.poista(5);
        for (int i : a) {
            if (i != 5) {
                assertTrue("Monilapsisen poistossa vikaa", p.hae(i));
            } else {
                assertFalse("Monilapsisen poistossa vikaa", p.hae(5));
            }
        }
        p.tyhjenna();

        int b[] = {1, 2};
        p.lisaaKaikki(b);
        p.poista(2);
        assertFalse("Yksilapsisen alkion poistossa vikaa", p.hae(2));
        assertTrue("Yksilapsisen alkion poistossa vikaa", p.hae(1));
        p.tyhjenna();

        p.lisaa(1);
        p.poista(1);
        assertTrue("Puu ei tyhjene poistolla", p.onTyhja());
    }

    /**
     * Test of onTyhja method, of class SplayPuu.
     */
    @Test
    public void testOnTyhja() {
        assertTrue("Uusi puu ei ole tyhjä!", p.onTyhja());
        p.lisaa(1);
        assertFalse("Puu on tyhjä lisäyksen jälkeen!", p.onTyhja());
        p.poista(1);
        assertTrue("Uusi puu ei ole tyhjä poiston jälkeen!", p.onTyhja());
        p.poista(1);
        p.tyhjenna();
        assertTrue("Uusi puu ei ole tyhjä tyhjenna-toiminnon jälkeen!", p.onTyhja());
    }

    /**
     * Test of getJuuri method, of class SplayPuu.
     */
    @Test
    public void testGetJuuri() {
        assertEquals("Juuri ei oletuksena null!", null, p.getJuuri());
        p.lisaa(1);
        assertFalse("Juuri ei vaihdu lisäyksen jälkeen!", p.getJuuri() == null);
        p.tyhjenna();
        assertTrue("Tyjenna ei vaihda juurta nulliksi!", p.getJuuri() == null);
        p.lisaa(1);
        p.poista(1);
        assertTrue("Juuri ei vaihdu poiston jälkeen!", p.getJuuri() == null);
    }

    /**
     * Test of tyhjenna method, of class SplayPuu.
     */
    @Test
    public void testTyhjenna() {
        assertTrue("Juuri ei ole olkujaan null!", p.getJuuri() == null);
        p.lisaa(1);
        p.tyhjenna();
        assertFalse("Haku löytää alkion tyjennetystä puusta!", p.hae(1));
        assertTrue("Tyjenna ei vaihda juurta nulliksi!", p.getJuuri() == null);
    }

}
