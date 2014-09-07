/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import Tietorakenteet.Jono.Jono;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author elkyur
 */
public class NaivimonikulmioTest {

    public NaivimonikulmioTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

   
    @Test
    public void NelionVirittavaNelioOnNelio() {
        Jono j = new Jono();
        j.lisaa(new Kordinaatti(0, 0));
        j.lisaa(new Kordinaatti(0, 1));
        j.lisaa(new Kordinaatti(1, 1));
        j.lisaa(new Kordinaatti(1, 0));
        Naivimonikulmio n = new Naivimonikulmio(j);
        assertEquals(n.palautaVirittavaNelio()[1], new Kordinaatti(1, 1));
    }

   

    @Test
    public void testJanojenpalautus() {
        Jono j = new Jono();
        j.lisaa(new Kordinaatti(0, 0));
        j.lisaa(new Kordinaatti(0, 1));
        j.lisaa(new Kordinaatti(1, 1));
        j.lisaa(new Kordinaatti(1, 0));
        Naivimonikulmio n = new Naivimonikulmio(j);
        Kordinaatti[][] kartta = n.PalautaJanat();
        assertEquals(kartta.length, 4);
    }

    @Test
    public void testJanojenpalautusosa2() {
        Jono j = new Jono();
        j.lisaa(new Kordinaatti(0,0));
        j.lisaa(new Kordinaatti(0,1));
        j.lisaa(new Kordinaatti(1,1));
        j.lisaa(new Kordinaatti(1,0));
        Naivimonikulmio n = new Naivimonikulmio(j);
        Kordinaatti[][] kartta = n.PalautaJanat();
        boolean k = false;
        for (int i = 0; i < kartta.length; i++) {
            Kordinaatti k1 = kartta[i][0];
            Kordinaatti k2 = kartta[i][1];
            Kordinaatti b1 = new Kordinaatti(0,0);
            Kordinaatti b2 = new Kordinaatti(0,1);

            if ((k2.equals(b2)) && (k1.equals(b1))) {
                k = true;

            }

        }
        assertEquals(k, true);
    }


}
