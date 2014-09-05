/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka.tietorakenteet;

import com.mycompany.tiralabra_maven.logiikka.tietorakenteet.PrioriteettiKeko;
import java.util.Comparator;
import static junit.framework.Assert.*;
import org.junit.*;

/**
 *
 * @author mikko
 */
public class PrioriteettiKekoTest {

    @Before
    public void setUp() {

    }

    @Test
    public void prioriteettiKekoToimiiIntegereilla() {
        PrioriteettiKeko<Integer> keko1 = new PrioriteettiKeko<>();
        keko1.lisaa(2);
        keko1.lisaa(7);
        keko1.lisaa(0);
        keko1.lisaa(12);
        keko1.lisaa(16);
        keko1.lisaa(3);
        keko1.lisaa(1);

        assertEquals(0, (int) keko1.seuraava());
        assertEquals(1, (int) keko1.seuraava());
        assertEquals(2, (int) keko1.seuraava());
        assertEquals(3, (int) keko1.seuraava());
        assertEquals(7, (int) keko1.seuraava());
        assertEquals(12, (int) keko1.seuraava());
        assertEquals(16, (int) keko1.seuraava());
        assertNull(keko1.seuraava());

    }

    @Test
    public void prioriteettiKekoToimiiStringeillaJaOmallaComparaattorilla() {
        Comparator<String> vertailija = new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }

        };

        PrioriteettiKeko<String> keko = new PrioriteettiKeko<>(vertailija);
        keko.lisaa("Matti");
        keko.lisaa("Juhani");
        keko.lisaa("Otso");
        keko.lisaa("Sirkka");
        keko.lisaa("Pentti");
        keko.lisaa("Anna");
        keko.lisaa("Viljami");
        keko.lisaa("Pentti2");

        assertEquals("Anna", keko.seuraava());
        assertEquals("Juhani", keko.seuraava());
        assertEquals("Matti", keko.seuraava());

        keko.lisaa("Tuomas");
        keko.lisaa("Matias");
        keko.lisaa("Linnea");

        assertEquals("Linnea", keko.seuraava());
        assertEquals("Matias", keko.seuraava());
        assertEquals("Otso", keko.seuraava());
        assertEquals("Pentti", keko.seuraava());
        assertEquals("Pentti2", keko.seuraava());
        assertEquals("Sirkka", keko.seuraava());
        assertEquals("Tuomas", keko.seuraava());
        assertEquals("Viljami", keko.seuraava());
        assertNull(keko.seuraava());

    }

    @Test
    public void prioriteettiKekoToimiiStringeillaJaOmallaComparaattorilla2() {
        Comparator<String> vertailija = new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }

        };

        PrioriteettiKeko<String> keko = new PrioriteettiKeko<>(vertailija);
        keko.lisaa("Matti");
        keko.lisaa("Juhani");
        keko.lisaa("Otso");
        keko.lisaa("Sirkka");
        keko.lisaa("Pentti");
        keko.lisaa("Anna");
        keko.lisaa("Viljami");
        keko.lisaa("Pentti2");

        assertEquals("Viljami", keko.seuraava());
        assertEquals("Sirkka", keko.seuraava());
        assertEquals("Pentti2", keko.seuraava());

        keko.lisaa("Tuomas");
        keko.lisaa("Matias");
        keko.lisaa("Linnea");

        assertEquals("Tuomas", keko.seuraava());
        assertEquals("Pentti", keko.seuraava());
        assertEquals("Otso", keko.seuraava());
        assertEquals("Matti", keko.seuraava());
        assertEquals("Matias", keko.seuraava());
        assertEquals("Linnea", keko.seuraava());
        assertEquals("Juhani", keko.seuraava());
        assertEquals("Anna", keko.seuraava());
        assertNull(keko.seuraava());

    }

}