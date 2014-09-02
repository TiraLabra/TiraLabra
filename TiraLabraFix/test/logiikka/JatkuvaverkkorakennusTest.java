/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import Tietorakenteet.JatkuvaSolmu;
import Tietorakenteet.JatkuvaVerkko;
import Tietorakenteet.Jatkuvamonikulmio;
import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Kordinaatti;
import Tietorakenteet.Monikulmio;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Serafim
 */
public class JatkuvaverkkorakennusTest {

    public JatkuvaverkkorakennusTest() {
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

    /**
     * Test of asetaKordinaatit method, of class Jatkuvaverkkorakennus.
     */
    /**
     * Test of laskeVerkko method, of class Jatkuvaverkkorakennus.
     */
    @Test
    public void testLaskeVerkkoRakentaminenonnistuuedesjollakintavalla() {
        //ASETETAAN PARAMETRIT, ENSIN MONIKULMIO:
        Kordinaatti k1 = new Kordinaatti(1, 1);
        Kordinaatti k2 = new Kordinaatti(2, 1);
        Kordinaatti k3 = new Kordinaatti(2, 2);
        Kordinaatti k4 = new Kordinaatti(1, 2);
        Kordinaatti alku = new Kordinaatti(0,0);
        Kordinaatti loppu = new Kordinaatti(3,3);
        Jono kordinaattijono = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        Jatkuvamonikulmio monikulmio = new Jatkuvamonikulmio(kordinaattijono);
        Jono monikulmiot = new Jono();
        monikulmiot.lisaa(monikulmio);
        Jatkuvaverkkorakennus rakentaja = new Jatkuvaverkkorakennus(monikulmiot);
        rakentaja.asetaAlkujaLoppu(alku, loppu);
        JatkuvaVerkko xD = rakentaja.laskeVerkko();
        boolean tarkistus = (xD == null);
        assertEquals(tarkistus, false);
        
        
        

    }

}
