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


    @Test
    public void testKoko() {
        assertEquals(4, PuuOperaatiot.koko(p));
        assertEquals(1, PuuOperaatiot.koko(p3));
    }


    @Test
    public void testKorkeus() {
        assertEquals(4, PuuOperaatiot.korkeus(p));
        assertEquals(1, PuuOperaatiot.korkeus(p3));
    }


    @Test
    public void testPienin() {
        assertEquals(p1, PuuOperaatiot.pienin(p));
        assertEquals(p2, PuuOperaatiot.pienin(p2));
    }

 
    @Test
    public void testSuurin() {
        assertEquals(p, PuuOperaatiot.suurin(p));
        assertEquals(p3, PuuOperaatiot.suurin(p1));
    }


    @Test
    public void testSeuraaja() {
        assertEquals(null, PuuOperaatiot.seuraaja(p));
        assertEquals(p2, PuuOperaatiot.seuraaja(p1));
        assertEquals(p, PuuOperaatiot.seuraaja(p3));
    }

    @Test
    public void testEdeltaja() {
        assertEquals(p3, PuuOperaatiot.edeltaja(p));
        assertEquals(null, PuuOperaatiot.edeltaja(p1));
        assertEquals(p1, PuuOperaatiot.edeltaja(p2));
    }
    
    //Pitää selvittää miten näille saa hyvät testit...
    // toistaiseksi silmämääräinen testaus

    @Test
    public void testEsijarjestys() {
        System.out.print("Esi:\n");
        PuuOperaatiot.esijarjestys(p);
        System.out.println("");
    }


    @Test
    public void testSisajarjestys() {
        System.out.print("Sisa:\n");
        PuuOperaatiot.sisajarjestys(p);
        System.out.println("");
    }


    @Test
    public void testJalkijarjestys() {
        System.out.print("Jälki:\n");
        PuuOperaatiot.jalkijarjestys(p);
        System.out.println("");
    }

    @Test
    public void testIsovanhempi() {
        assertEquals(p1, PuuOperaatiot.isovanhempi(p3));
        assertEquals(null, PuuOperaatiot.isovanhempi(p1));
        assertEquals(null, PuuOperaatiot.isovanhempi(p));
    }

    @Test
    public void testOnOikea() {
        assertTrue(PuuOperaatiot.onOikea(p2));
        assertFalse(PuuOperaatiot.onOikea(p));
        assertFalse(PuuOperaatiot.onOikea(p1));
    }

    @Test
    public void testOnVasen() {
        assertTrue(PuuOperaatiot.onVasen(p1));
        assertFalse(PuuOperaatiot.onVasen(p));
        assertFalse(PuuOperaatiot.onVasen(p2));
    }
    
    @Test
    public void testVasenKierto(){
        PuuOperaatiot.vasenKierto(p1);
        assertTrue(PuuOperaatiot.onVasen(p1));
        assertTrue(PuuOperaatiot.onVasen(p2));
        assertTrue(PuuOperaatiot.onOikea(p3));
        assertEquals(p, p2.getVanhempi());
        assertEquals(p2, p1.getVanhempi());
        assertEquals(p3, p2.getOikea());
    }
    
    @Test
    public void testOikeaKierto(){
        PuuOperaatiot.oikeaKierto(p);
        assertTrue(PuuOperaatiot.onOikea(p));
        assertTrue(PuuOperaatiot.onVasen(p2));
        assertTrue(PuuOperaatiot.onOikea(p3));
        assertEquals(p1, p.getVanhempi());
        assertEquals(p, p2.getVanhempi());
        assertEquals(p3, p2.getOikea());
        assertEquals(null, p1.getVasen());
    }
}
