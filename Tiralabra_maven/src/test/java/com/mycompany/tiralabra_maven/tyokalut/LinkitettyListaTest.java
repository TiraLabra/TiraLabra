package com.mycompany.tiralabra_maven.tyokalut;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class LinkitettyListaTest {

    LinkitettyLista l;

    public LinkitettyListaTest() {
    }

    @Before
    public void setUp() {
        l = new LinkitettyLista();
    }

    @Test
    public void testToiminta() {
        int[] a = {0};
        int[] b = {0};
        int i = 0;
        for (Solmu solmu : l) {
            i++;
        }
        assertEquals(0, i);
        l.lisaa(a);
        i = 0;
        for (Solmu solmu : l) {
            i++;
        }
        assertEquals(1, i);
        l.lisaa(b);
        i = 0;
        for (Solmu solmu : l) {
            i++;
        }
        assertEquals(2, i);
    }
}
