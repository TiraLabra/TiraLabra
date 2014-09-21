package com.mycompany.tiralabra_maven.tietorakenteet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class PuuOperaatiotTest {

    public PuuOperaatiotTest() {
    }
    Puusolmu p, p1, p2, p3;

    @Before
    public void setUp() {
        p = new BinaarinenPuusolmu(10);
        p1 = new BinaarinenPuusolmu(1);
        p2 = new BinaarinenPuusolmu(2);
        p3 = new BinaarinenPuusolmu(3);
        p.setVasen(p1);
        p1.setVanhempi(p);
        p1.setOikea(p2);
        p2.setVanhempi(p1);
        p2.setOikea(p3);
        p3.setVanhempi(p2);
    }

    /**
     * Test of koko method, of class PuuOperaatiot.
     */
    @Test
    public void testKoko() {
        assertEquals(4, PuuOperaatiot.koko(p));
        assertEquals(1, PuuOperaatiot.koko(p3));
    }

    /**
     * Test of korkeus method, of class PuuOperaatiot.
     */
    @Test
    public void testKorkeus() {
        assertEquals(4, PuuOperaatiot.korkeus(p));
        assertEquals(1, PuuOperaatiot.korkeus(p3));
    }

    /**
     * Test of pienin method, of class PuuOperaatiot.
     */
    @Test
    public void testPienin() {
        assertEquals(p1, PuuOperaatiot.pienin(p));
        assertEquals(p2, PuuOperaatiot.pienin(p2));
    }

    /**
     * Test of suurin method, of class PuuOperaatiot.
     */
    @Test
    public void testSuurin() {
        assertEquals(p, PuuOperaatiot.suurin(p));
        assertEquals(p3, PuuOperaatiot.suurin(p1));
    }

    /**
     * Test of seuraaja method, of class PuuOperaatiot.
     */
    @Test
    public void testSeuraaja() {
        assertEquals(null, PuuOperaatiot.seuraaja(p));
        assertEquals(p2, PuuOperaatiot.seuraaja(p1));
        assertEquals(p, PuuOperaatiot.seuraaja(p3));
    }

    //Pitää selvittää miten näille saa hyvät testit...
    // toistaiseksi silmämääräinen testaus
    /**
     * Test of esijarjestys method, of class PuuOperaatiot.
     */
    @Test
    public void testEsijarjestys() {
        System.out.print("Esi:\n");
        PuuOperaatiot.esijarjestys(p);
        System.out.println("");
    }

    /**
     * Test of sisajarjestys method, of class PuuOperaatiot.
     */
    @Test
    public void testSisajarjestys() {
        System.out.print("Sisa:\n");
        PuuOperaatiot.sisajarjestys(p);
        System.out.println("");
    }

    /**
     * Test of jalkijarjestys method, of class PuuOperaatiot.
     */
    @Test
    public void testJalkijarjestys() {
        System.out.print("Jälki:\n");
        PuuOperaatiot.jalkijarjestys(p);
        System.out.println("");
    }

}
