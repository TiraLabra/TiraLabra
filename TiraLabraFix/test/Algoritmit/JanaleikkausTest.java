/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmit;

import Tietorakenteet.Jatkuvamonikulmio;
import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Kordinaatti;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class JanaleikkausTest {

    private Janaleikkaus testaaja;

    public JanaleikkausTest() {
    }

    @Before
    public void setUp() {
        this.testaaja = new Janaleikkaus();
    }

    /**
     * Test of leikkaako method, of class Janaleikkaus.
     */
    @Test
    public void testLeikkaakoyksinkertainenleikkaus() {
        Kordinaatti k1 = new Kordinaatti(0, 0);
        Kordinaatti k2 = new Kordinaatti(1, 1);
        Kordinaatti k3 = new Kordinaatti(0, 1);
        Kordinaatti k4 = new Kordinaatti(1, 0);
        assertEquals(testaaja.leikkaako(k1, k2, k3, k4), true);
    }

    @Test
    public void testeileikkaayksinkertainenleikkaus() {
        Kordinaatti k1 = new Kordinaatti(0, 0);
        Kordinaatti k2 = new Kordinaatti(1, 0);
        Kordinaatti k3 = new Kordinaatti(2, 2);
        Kordinaatti k4 = new Kordinaatti(1, 1);
        assertEquals(testaaja.leikkaako(k1, k2, k3, k4), false);
    }

    @Test
    public void Vaakasuorapystysuora() {
        Kordinaatti k1 = new Kordinaatti(0, 1);
        Kordinaatti k2 = new Kordinaatti(2, 1);
        Kordinaatti k3 = new Kordinaatti(1, 0);
        Kordinaatti k4 = new Kordinaatti(1, 2);
        assertEquals(testaaja.leikkaako(k1, k2, k3, k4), true);
    }

    @Test
    public void testaavaikeampitapaus() {
        Kordinaatti k1 = new Kordinaatti(0, 0);
        Kordinaatti k2 = new Kordinaatti(2, 0);
        Kordinaatti k3 = new Kordinaatti(0, 0);
        Kordinaatti k4 = new Kordinaatti(2, 0);
        assertEquals(testaaja.leikkaako(k1, k2, k3, k4), false);
    }

    @Test
    public void testaaSuoranjajananleikkaus() {
        Kordinaatti k1 = new Kordinaatti(0, 0);
        Kordinaatti k2 = new Kordinaatti(2, 0);
        Kordinaatti k3 = new Kordinaatti(0, 0);
        Kordinaatti k4 = new Kordinaatti(2, 0);
        assertEquals(testaaja.suoranjaJananleikkaus(k1, k2, k3, k4, false), null);
    }

    @Test
    public void VaakasuoraPystysuorajana() {
        Kordinaatti k1 = new Kordinaatti(0, 1);
        Kordinaatti k2 = new Kordinaatti(2, 1);
        Kordinaatti k3 = new Kordinaatti(1, 0);
        Kordinaatti k4 = new Kordinaatti(1, 2);
        assertEquals(testaaja.suoranjaJananleikkaus(k1, k2, k3, k4, false), new Kordinaatti(1, 1));
    }
    //Nämä testit osoittavat että tämä toimii konvekseille monikulmioille

    @Test
    public void MonikulmioNakemistesti() {
        Kordinaatti k1 = new Kordinaatti(0, 0);
        Kordinaatti k2 = new Kordinaatti(1, 0);
        Kordinaatti k3 = new Kordinaatti(1, 1);
        Kordinaatti k4 = new Kordinaatti(0, 1);
        Jono kordinaattijono = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        Jatkuvamonikulmio kulmio = new Jatkuvamonikulmio(kordinaattijono);

        assertEquals(testaaja.nakeeko(k1, k2, kulmio), true);
    }

    @Test
    public void MonikulmioNakemistesti2() {
        Kordinaatti k1 = new Kordinaatti(0, 0);
        Kordinaatti k2 = new Kordinaatti(1, 0);
        Kordinaatti k3 = new Kordinaatti(1, 1);
        Kordinaatti k4 = new Kordinaatti(0, 1);
        Jono kordinaattijono = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        Jatkuvamonikulmio kulmio = new Jatkuvamonikulmio(kordinaattijono);

        assertEquals(testaaja.nakeeko(k1, k4, kulmio), true);
    }

    @Test
    public void MonikulmioNakemistesti3() {
        Kordinaatti k1 = new Kordinaatti(0, 0);
        Kordinaatti k2 = new Kordinaatti(1, 0);
        Kordinaatti k3 = new Kordinaatti(1, 1);
        Kordinaatti k4 = new Kordinaatti(0, 1);
        Jono kordinaattijono = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        Jatkuvamonikulmio kulmio = new Jatkuvamonikulmio(kordinaattijono);

        assertEquals(testaaja.nakeeko(k1, k3, kulmio), false);
    }

    @Test
    public void Erikoistapausnakemisesta1() {
        Kordinaatti k1 = new Kordinaatti(0, 0);
        Kordinaatti k2 = new Kordinaatti(2, 0);
        Kordinaatti k3 = new Kordinaatti(2, 2);
        Kordinaatti k4 = new Kordinaatti(1, 1);
        Kordinaatti k5 = new Kordinaatti(0, 2);
        Jono kordinaattijono = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        kordinaattijono.lisaa(k5);
        Jatkuvamonikulmio kulmio = new Jatkuvamonikulmio(kordinaattijono);

        assertEquals(testaaja.nakeeko(k2, k3, kulmio), true);
    }

    @Test
    public void Erikoistapausnakemisesta2() {
        Kordinaatti k1 = new Kordinaatti(0, 0);
        Kordinaatti k2 = new Kordinaatti(2, 0);
        Kordinaatti k3 = new Kordinaatti(2, 2);
        Kordinaatti k4 = new Kordinaatti(1, 1);
        Kordinaatti k5 = new Kordinaatti(0, 2);
        Jono kordinaattijono = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        kordinaattijono.lisaa(k5);
        Jatkuvamonikulmio kulmio = new Jatkuvamonikulmio(kordinaattijono);

        assertEquals(testaaja.nakeeko(k2, k5, kulmio), false);
    }

    @Test
    public void Erikoistapausnakemisesta3() {
        Kordinaatti k1 = new Kordinaatti(1, 0);
        Kordinaatti k2 = new Kordinaatti(0, 2);
        Kordinaatti k3 = new Kordinaatti(1, 1);
        Kordinaatti k4 = new Kordinaatti(2, 2);

        Jono kordinaattijono = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);

        Jatkuvamonikulmio kulmio = new Jatkuvamonikulmio(kordinaattijono);

        assertEquals(testaaja.nakeeko(k1, k3, kulmio), false);
    }

    @Test
    public void PisteUlkonamonikulmiosta() {
        Kordinaatti k1 = new Kordinaatti(0, 0);
        Kordinaatti k2 = new Kordinaatti(10, 0);
        Kordinaatti k3 = new Kordinaatti(10, 10);
        Kordinaatti k4 = new Kordinaatti(0, 10);

        Jono kordinaattijono = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);

        Jatkuvamonikulmio kulmio = new Jatkuvamonikulmio(kordinaattijono);

        Kordinaatti testipiste = new Kordinaatti(20, 5);
        Kordinaatti toinentesitpiste = new Kordinaatti(15, 5);
        int i = this.testaaja.Suorajamonikulmioleikkaus(testipiste, toinentesitpiste, kulmio);
        boolean d = (i % 2 == 0);
        assertEquals(d, true);
    }

    @Test
    public void PisteSisallamonikulmiossa() {
        Kordinaatti k1 = new Kordinaatti(0, 0);
        Kordinaatti k2 = new Kordinaatti(10, 0);
        Kordinaatti k3 = new Kordinaatti(10, 10);
        Kordinaatti k4 = new Kordinaatti(0, 10);

        Jono kordinaattijono = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);

        Jatkuvamonikulmio kulmio = new Jatkuvamonikulmio(kordinaattijono);

        Kordinaatti testipiste = new Kordinaatti(5, 5);
        Kordinaatti toinentesitpiste = new Kordinaatti(15, 5);
        int i = this.testaaja.Suorajamonikulmioleikkaus(testipiste, toinentesitpiste, kulmio);
        boolean d = (i % 2 == 0);
        assertEquals(d, false);
    }

    @Test
    public void PisteSisallamonikulmiossalisatesti() {
        Kordinaatti k1 = new Kordinaatti(0, 0);
        Kordinaatti k2 = new Kordinaatti(10, 0);
        Kordinaatti k3 = new Kordinaatti(10, 10);
        Kordinaatti k4 = new Kordinaatti(0, 10);

        Jono kordinaattijono = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);

        Jatkuvamonikulmio kulmio = new Jatkuvamonikulmio(kordinaattijono);

        Kordinaatti testipiste = new Kordinaatti(5, 5);
        Kordinaatti toinentesitpiste = new Kordinaatti(6, 5);
        int i = this.testaaja.Suorajamonikulmioleikkaus(testipiste, toinentesitpiste, kulmio);
        boolean d = (i % 2 == 1);
        assertEquals(d, true);
    }

    @Test
    public void KenekaansisallaTest() {
        Kordinaatti k1 = new Kordinaatti(0, 0);
        Kordinaatti k2 = new Kordinaatti(10, 0);
        Kordinaatti k3 = new Kordinaatti(10, 10);
        Kordinaatti k4 = new Kordinaatti(0, 10);
        Kordinaatti m1 = new Kordinaatti(2, 2);
        Kordinaatti m2 = new Kordinaatti(2, 3);
        Kordinaatti m3 = new Kordinaatti(3, 3);
        Kordinaatti m4 = new Kordinaatti(3, 2);
        Jono kjono2 = new Jono();
        Jono kordinaattijono = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        kjono2.lisaa(m1);
        kjono2.lisaa(m2);
        kjono2.lisaa(m3);
        kjono2.lisaa(m4);

        Jatkuvamonikulmio kulmio = new Jatkuvamonikulmio(kordinaattijono);
        Jatkuvamonikulmio kulmio2 = new Jatkuvamonikulmio(kjono2);

        Jono m = new Jono();
        m.lisaa(kulmio);

        boolean d = this.testaaja.kenenkaansisalla(m1, m);
        assertEquals(d, true);

    }

    @Test
    public void Erikoistapausnakemisesta4() {
        Kordinaatti[] k = new Kordinaatti[8];
        k[0] = new Kordinaatti(0, 0);
        k[1] = new Kordinaatti(0, 3);
        k[2] = new Kordinaatti(2, 2);
        k[3] = new Kordinaatti(1, 1);
        k[4] = new Kordinaatti(4, 1);
        k[5] = new Kordinaatti(3, 2);
        k[6] = new Kordinaatti(5, 3);
        k[7] = new Kordinaatti(5, 0);

        Jono kordinaattijono = new Jono();
        for (int i = 0; i < k.length; i++)
        {
        kordinaattijono.lisaa(k[i]);
        }

        Jatkuvamonikulmio kulmio = new Jatkuvamonikulmio(kordinaattijono);

        assertEquals(testaaja.nakeeko(k[2], k[5], kulmio), true);
    }
    @Test
    public void Reunapiste() {
        Kordinaatti k1 = new Kordinaatti(0, 0);
        Kordinaatti k2 = new Kordinaatti(10, 0);
        Kordinaatti k3 = new Kordinaatti(10, 10);
        Kordinaatti k4 = new Kordinaatti(0, 10);

        Jono kordinaattijono = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);

        Jatkuvamonikulmio kulmio = new Jatkuvamonikulmio(kordinaattijono);
        Jono kulmiot = new Jono();
        kulmiot.lisaa(kulmio);
        Kordinaatti testipiste = new Kordinaatti(0, 5);
       
        boolean d = this.testaaja.kenenkaansisalla(testipiste, kulmiot);
        assertEquals(d, true);
    }
   

}
