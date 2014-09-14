package com.mycompany.tiralabra_maven.tyokalut;

import com.mycompany.tiralabra_maven.tietorakenteet.Hakupuu;
import com.mycompany.tiralabra_maven.tietorakenteet.Testipuu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class PuunTutkijaTest {

    PuunTutkija pt;
    Hakupuu puu = new Testipuu();

    public PuunTutkijaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        pt = new PuunTutkija();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of lisaysaika method, of class PuunTutkija.
     */
    @Test
    public void testLisaysaika() {
        Long aika = pt.lisaysaika(puu, 5);
        assertEquals(true, aika > 0L);
        assertEquals(true, aika < System.nanoTime());
    }

    /**
     * Test of lisaysaikaTulokset method, of class PuunTutkija.
     */
    @Test
    public void testLisaysaikaTulokset() {
        int[] a = {1, 3, 5};
        Mittaustulos tulos = pt.lisaysaikaTulokset(puu, a);
        assertEquals(true, puu.hae(5));
        assertEquals(true, tulos.getPienin() != Long.MAX_VALUE);
        assertEquals(true, tulos.getSuurin() != Long.MIN_VALUE);
        assertEquals(true, tulos.getKeskiarvo() > 0L);
    }

    /**
     * Test of hakuaika method, of class PuunTutkija.
     */
    @Test
    public void testHakuaika() {
        Long aika = pt.hakuaika(puu, 5);
        assertEquals(true, aika > 0L);
        assertEquals(true, aika < System.nanoTime());
        puu.lisaa(5);
        aika = pt.hakuaika(puu, 5);
        assertEquals(true, aika > 0L);
        assertEquals(true, aika < System.nanoTime());
    }

    /**
     * Test of poistoaika method, of class PuunTutkija.
     */
    @Test
    public void testPoistoaika() {
        Long aika = pt.poistoaika(puu, 5);
        assertEquals(true, aika > 0L);
        assertEquals(true, aika < System.nanoTime());
        puu.lisaa(5);
        aika = pt.poistoaika(puu, 5);
        assertEquals(true, aika > 0L);
        assertEquals(true, aika < System.nanoTime());
    }

}
