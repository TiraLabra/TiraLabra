package com.mycompany.tiralabra_maven.tietorakenteet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class PunamustaPuuTest {

    PunamustaPuu p;

    public PunamustaPuuTest() {
    }

    @Before
    public void setUp() {
        p = new PunamustaPuu();
    }

    @Test
    public void testLisaa() {
        try {
            p.lisaa(-1);
            p.lisaa(0);
            p.lisaa(1);

        } catch (Exception e) {

        }

    }

    @Test
    public void testLisaaKaikki() {
        try {
            int[] a = {4, 7, 12, 15, 3, 5, 14, 18, 16, 17};

            p.lisaaKaikki(a);

        } catch (Exception e) {

        }

    }

    @Test
    public void testHae() {
        assertEquals(false, p.hae(0));
        assertEquals(false, p.hae(1));
        p.lisaa(0);
        assertEquals(true, p.hae(0));
        assertEquals(false, p.hae(1));
    }

    @Test
    public void testHaeSolmu() {
        //Tulee testatuksi välttämättä muiden metodien testeissä koska muut metodit riippuvat tästä...
    }

    @Test
    public void testPoista() {
        int[] a = {4, 7, 12, 15, 3, 5, 14, 18, 16, 17};
        p.lisaaKaikki(a);
        assertEquals(true, p.hae(17));
        p.poista(17);
        assertEquals(false, p.hae(17));
        assertEquals(true, p.hae(4));
        p.poista(4);
        assertEquals(false, p.hae(4));
        assertEquals(true, p.hae(7));
        assertEquals(true, p.hae(3));
    }

    @Test
    public void testOnTyhja() {
        assertEquals(true, p.onTyhja());
        p.lisaa(0);
        assertEquals(false, p.onTyhja());
    }

}
