/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka.tietorakenteet;

import com.mycompany.tiralabra_maven.logiikka.tietorakenteet.Jono;
import static junit.framework.Assert.*;
import org.junit.*;

/**
 *
 * @author mikko
 */
public class JonoTest {

    private Jono<Integer> jono;

    @Before
    public void setUp() {
        jono = new Jono<>();
    }

    @Test
    public void jonoOnAlussaTyhja() {
        assertEquals(0, jono.koko());
        assertTrue(jono.tyhja());
    }

    @Test
    public void jononKokoKasvaaKunLisataanAlkioita() {
        jono.lisaa(2);
        assertEquals(1, jono.koko());
        jono.lisaa(7);
        assertEquals(2, jono.koko());
        jono.lisaa(0);
        assertEquals(3, jono.koko());
        jono.lisaa(12);
        assertEquals(4, jono.koko());
        jono.lisaa(16);
        assertEquals(5, jono.koko());
        jono.lisaa(3);
        assertEquals(6, jono.koko());
        jono.lisaa(1);
        assertEquals(7, jono.koko());
        jono.lisaa(4);
        assertEquals(8, jono.koko());
        jono.lisaa(7);
        assertEquals(9, jono.koko());
        jono.lisaa(0);
        assertEquals(10, jono.koko());
        jono.lisaa(4);
        assertEquals(11, jono.koko());
        jono.lisaa(8);
        assertEquals(12, jono.koko());
        jono.lisaa(2);
        assertEquals(13, jono.koko());
        jono.lisaa(4);
        assertEquals(14, jono.koko());
        jono.lisaa(7);
        assertEquals(15, jono.koko());
        jono.lisaa(3);
    }

    @Test
    public void jononKokoPieneneeKunOtetaanPoisAlkioita() {
        jono.lisaa(2);
        jono.lisaa(2);
        jono.lisaa(2);
        jono.lisaa(2);
        jono.lisaa(2);
        jono.lisaa(2);
        jono.lisaa(2);
        jono.lisaa(2);
        jono.lisaa(2);
        jono.lisaa(2);
        jono.lisaa(2);
        jono.lisaa(2);
        assertEquals(12, jono.koko());
        jono.otaJonosta();
        assertEquals(11, jono.koko());
        jono.otaJonosta();
        assertEquals(10, jono.koko());
        jono.otaJonosta();
        assertEquals(9, jono.koko());
        jono.otaJonosta();
        assertEquals(8, jono.koko());
        jono.otaJonosta();
        assertEquals(7, jono.koko());
    }

    @Test
    public void jonostaSaadaanAlkioitaSiinaJarjestyksessaMissaNeLaitettiin() {
        jono.lisaa(1);
        jono.lisaa(2);
        jono.lisaa(3);
        jono.lisaa(4);
        jono.lisaa(5);
        jono.lisaa(6);
        assertEquals(1, (int) jono.otaJonosta());
        assertEquals(2, (int) jono.otaJonosta());
        assertEquals(3, (int) jono.otaJonosta());
        assertEquals(4, (int) jono.otaJonosta());
        assertEquals(5, (int) jono.otaJonosta());
        jono.lisaa(7);
        jono.lisaa(8);
        jono.lisaa(9);
        jono.lisaa(10);
        jono.lisaa(11);
        jono.lisaa(12);
        jono.lisaa(13);
        jono.lisaa(14);
        jono.lisaa(15);
        jono.lisaa(16);
        jono.lisaa(17);
        jono.lisaa(18);
        jono.lisaa(19);
        jono.lisaa(20);
        assertEquals(6, (int) jono.otaJonosta());
        assertEquals(7, (int) jono.otaJonosta());
        assertEquals(8, (int) jono.otaJonosta());
        assertEquals(9, (int) jono.otaJonosta());
        assertEquals(10, (int) jono.otaJonosta());
        assertEquals(11, (int) jono.otaJonosta());
        assertEquals(12, (int) jono.otaJonosta());
        assertEquals(13, (int) jono.otaJonosta());
        assertEquals(14, (int) jono.otaJonosta());
        assertEquals(15, (int) jono.otaJonosta());
        assertEquals(16, (int) jono.otaJonosta());
        assertEquals(17, (int) jono.otaJonosta());
        assertEquals(18, (int) jono.otaJonosta());
        assertEquals(19, (int) jono.otaJonosta());
        assertEquals(20, (int) jono.otaJonosta());

    }

}
