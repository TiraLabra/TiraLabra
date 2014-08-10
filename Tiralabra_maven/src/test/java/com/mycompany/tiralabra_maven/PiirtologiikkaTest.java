/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.gui.Ruutu;
import org.junit.*;
import static junit.framework.Assert.*;

/**
 *
 * @author mikko
 */
public class PiirtologiikkaTest {

    private Piirtologiikka piirtologiikka;

    public PiirtologiikkaTest() {

    }

    @Before
    public void setUp() throws Exception {
        this.piirtologiikka = new Piirtologiikka(16, 10);
    }

    @Test
    public void konstruktoriLuoOikeanKokoisenRuudukon() {
        Ruutu[][] ruudukko = piirtologiikka.getRuudukko();
        assertEquals(10, ruudukko.length);
        assertEquals(10, piirtologiikka.getKorkeus());
        assertEquals(16, ruudukko[0].length);
        assertEquals(16, piirtologiikka.getLeveys());
    }

    @Test
    public void ruudukkoOnAlussaTyhja() {
        Ruutu[][] ruudukko = piirtologiikka.getRuudukko();
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 16; x++) {
                assertNull(ruudukko[y][x]);
            }
        }
    }

    @Test
    public void setRuutuAsettaaOikeanlaisenRuudun() {
        piirtologiikka.setRuutu(3, 5, Ruutu.SEINA);
        assertEquals(Ruutu.SEINA, piirtologiikka.getRuutu(3, 5));
    }

    @Test
    public void hiirenLiikutteluRuudukonPaallaToimii() {
        assertNull(piirtologiikka.hiirenKoordinaatit());
        piirtologiikka.hiiriRuudunPaalla(2, 3);
        assertEquals(new Koordinaatit(2, 3), piirtologiikka.hiirenKoordinaatit());
        piirtologiikka.hiiriRuudunPaalla(3, 3);
        assertEquals(new Koordinaatit(3, 3), piirtologiikka.hiirenKoordinaatit());
        piirtologiikka.hiiriPoistunut();
        assertNull(piirtologiikka.hiirenKoordinaatit());
    }

    @Test
    public void hiirenKlikkailuRuudukonPaallaToimii() {
        assertNull(piirtologiikka.hiirenKoordinaatit());
        assertFalse(piirtologiikka.onkoHiiriPainettu());
        piirtologiikka.hiiriRuudunPaalla(2, 3);
        assertEquals(new Koordinaatit(2, 3), piirtologiikka.hiirenKoordinaatit());
        assertFalse(piirtologiikka.onkoHiiriPainettu());
        piirtologiikka.hiiriPainettu(true);
        assertEquals(new Koordinaatit(2, 3), piirtologiikka.hiirenKoordinaatit());
        assertTrue(piirtologiikka.onkoHiiriPainettu());
        piirtologiikka.hiiriPainettu(false);
        assertEquals(new Koordinaatit(2, 3), piirtologiikka.hiirenKoordinaatit());
        assertFalse(piirtologiikka.onkoHiiriPainettu());
        piirtologiikka.hiiriPoistunut();
        assertNull(piirtologiikka.hiirenKoordinaatit());
        assertFalse(piirtologiikka.onkoHiiriPainettu());
    }
}
