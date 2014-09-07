/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import Tietorakenteet.Jatkuvamonikulmio;
import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Kordinaatti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class LogiikkaTest {

    private Logiikka logiikka;

    public LogiikkaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.logiikka = new Logiikka();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSallitaankoTapausJossaPitaisisallia() {
        Kordinaatti k1 = new Kordinaatti(1, 1);
        Kordinaatti k2 = new Kordinaatti(2, 1);
        Kordinaatti k3 = new Kordinaatti(2, 2);
        Kordinaatti k4 = new Kordinaatti(1, 2);
        Kordinaatti b1 = new Kordinaatti(-1, -1);
        Kordinaatti b2 = new Kordinaatti(-1, -2);
        Kordinaatti b3 = new Kordinaatti(-2, -2);
        Kordinaatti b4 = new Kordinaatti(-2, -1);
        Kordinaatti alku = new Kordinaatti(0, 0);
        Kordinaatti loppu = new Kordinaatti(3, 3);
        Jono kordinaattijono = new Jono();
        Jono kordinaattijono2 = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        kordinaattijono2.lisaa(b1);
        kordinaattijono2.lisaa(b2);
        kordinaattijono2.lisaa(b3);
        kordinaattijono2.lisaa(b4);

        this.logiikka.lisaaMonikulmio(kordinaattijono);
        int i = this.logiikka.lisaaMonikulmio(kordinaattijono2);
        assertEquals(i, 0);

    }

    @Test
    public void testTapausjossaeipitaisisallia() {
        Kordinaatti k1 = new Kordinaatti(1, 1);
        Kordinaatti k2 = new Kordinaatti(2, 1);
        Kordinaatti k3 = new Kordinaatti(2, 2);
        Kordinaatti k4 = new Kordinaatti(1, 2);
        Kordinaatti b1 = new Kordinaatti(-1, -1);
        Kordinaatti b2 = new Kordinaatti(-1, 1.5);
        Kordinaatti b3 = new Kordinaatti(1.5, 1.5);
        Kordinaatti b4 = new Kordinaatti(1.5, -1);
        Kordinaatti alku = new Kordinaatti(0, 0);
        Kordinaatti loppu = new Kordinaatti(3, 3);
        Jono kordinaattijono = new Jono();
        Jono kordinaattijono2 = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        kordinaattijono2.lisaa(b1);
        kordinaattijono2.lisaa(b2);
        kordinaattijono2.lisaa(b3);
        kordinaattijono2.lisaa(b4);

        this.logiikka.lisaaMonikulmio(kordinaattijono);
        int i = this.logiikka.lisaaMonikulmio(kordinaattijono2);
        assertEquals(i, 2);

    }

    
    @Test
    public void testAsetaAlku() {
        Kordinaatti alku = new Kordinaatti(0, 0);
        int i = this.logiikka.asetaAlku(alku);
        assertEquals(i, 0);
    }

    @Test
    public void testtapausjokaleikkaa() {
        Kordinaatti k1 = new Kordinaatti(1, 1);
        Kordinaatti k2 = new Kordinaatti(2, 2);
        Kordinaatti k3 = new Kordinaatti(2, 1);
        Kordinaatti k4 = new Kordinaatti(1, 2);

        Jono kordinaattijono = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        boolean k = this.logiikka.leikkaakoitseaan(new Jatkuvamonikulmio(kordinaattijono));
        assertEquals(k, true);
    }

  
    @Test
    public void testLaskeVerkkoTAvallisessatapauksessatoimiii() {
        Kordinaatti k1 = new Kordinaatti(1, 1);
        Kordinaatti k2 = new Kordinaatti(2, 1);
        Kordinaatti k3 = new Kordinaatti(2, 2);
        Kordinaatti k4 = new Kordinaatti(1, 2);
        Kordinaatti b1 = new Kordinaatti(-1, -1);
        Kordinaatti b2 = new Kordinaatti(-1, -2);
        Kordinaatti b3 = new Kordinaatti(-2, -2);
        Kordinaatti b4 = new Kordinaatti(-2, -1);
        Kordinaatti alku = new Kordinaatti(0, 0);
        Kordinaatti loppu = new Kordinaatti(3, 3);
        Jono kordinaattijono = new Jono();
        Jono kordinaattijono2 = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        kordinaattijono2.lisaa(b1);
        kordinaattijono2.lisaa(b2);
        kordinaattijono2.lisaa(b3);
        kordinaattijono2.lisaa(b4);
        this.logiikka.lisaaMonikulmio(kordinaattijono);
        this.logiikka.lisaaMonikulmio(kordinaattijono2);
        this.logiikka.asetaAlku(alku);
        this.logiikka.asetaLoppu(loppu);
        int i = this.logiikka.laskeReitti();
        assertEquals(i, 0);
    }

    @Test
    public void testLaskeReitti() {
        Kordinaatti k1 = new Kordinaatti(1, 1);
        Kordinaatti k2 = new Kordinaatti(2, 1);
        Kordinaatti k3 = new Kordinaatti(2, 2);
        Kordinaatti k4 = new Kordinaatti(1, 2);
        Kordinaatti b1 = new Kordinaatti(-1, -1);
        Kordinaatti b2 = new Kordinaatti(-1, -2);
        Kordinaatti b3 = new Kordinaatti(-2, -2);
        Kordinaatti b4 = new Kordinaatti(-2, -1);
        Kordinaatti alku = new Kordinaatti(1.5, 1.5);
        Kordinaatti loppu = new Kordinaatti(3, 3);
        Jono kordinaattijono = new Jono();
        Jono kordinaattijono2 = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        kordinaattijono2.lisaa(b1);
        kordinaattijono2.lisaa(b2);
        kordinaattijono2.lisaa(b3);
        kordinaattijono2.lisaa(b4);
        this.logiikka.lisaaMonikulmio(kordinaattijono);
        this.logiikka.lisaaMonikulmio(kordinaattijono2);
        this.logiikka.asetaAlku(alku);
        this.logiikka.asetaLoppu(loppu);
        int i = this.logiikka.laskeReitti();
        assertEquals(i, 4);
    }

  
    @Test
    public void testPalautaMonikulmio() {
        Kordinaatti k1 = new Kordinaatti(1, 1);
        Kordinaatti k2 = new Kordinaatti(2, 1);
        Kordinaatti k3 = new Kordinaatti(2, 2);
        Kordinaatti k4 = new Kordinaatti(1, 2);
        Kordinaatti b1 = new Kordinaatti(-1, -1);
        Kordinaatti b2 = new Kordinaatti(-1, -2);
        Kordinaatti b3 = new Kordinaatti(-2, -2);
        Kordinaatti b4 = new Kordinaatti(-2, -1);
        Kordinaatti alku = new Kordinaatti(1.5, 1.5);
        Kordinaatti loppu = new Kordinaatti(3, 3);
        Jono kordinaattijono = new Jono();
        Jono kordinaattijono2 = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        kordinaattijono2.lisaa(b1);
        kordinaattijono2.lisaa(b2);
        kordinaattijono2.lisaa(b3);
        kordinaattijono2.lisaa(b4);
        this.logiikka.lisaaMonikulmio(kordinaattijono);
        this.logiikka.lisaaMonikulmio(kordinaattijono2);
        Jono d = this.logiikka.palautaMonikulmio();
        int i = d.palautaKoko();
        assertEquals(i, 2);
    }

    
    @Test
    public void testPalautaReitti() {
        Kordinaatti k1 = new Kordinaatti(1, 1);
        Kordinaatti k2 = new Kordinaatti(2, 1);
        Kordinaatti k3 = new Kordinaatti(2, 2);
        Kordinaatti k4 = new Kordinaatti(1, 2);
        Kordinaatti b1 = new Kordinaatti(-1, -1);
        Kordinaatti b2 = new Kordinaatti(-1, -2);
        Kordinaatti b3 = new Kordinaatti(-2, -2);
        Kordinaatti b4 = new Kordinaatti(-2, -1);
        Kordinaatti alku = new Kordinaatti(0, 0);
        Kordinaatti loppu = new Kordinaatti(3, 3);
        Jono kordinaattijono = new Jono();
        Jono kordinaattijono2 = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        kordinaattijono2.lisaa(b1);
        kordinaattijono2.lisaa(b2);
        kordinaattijono2.lisaa(b3);
        kordinaattijono2.lisaa(b4);
        this.logiikka.lisaaMonikulmio(kordinaattijono);
        this.logiikka.lisaaMonikulmio(kordinaattijono2);
        this.logiikka.asetaAlku(alku);
        this.logiikka.asetaLoppu(loppu);
        int i = this.logiikka.laskeReitti();
        Jono a = this.logiikka.palautaReitti();
        
        int j = a.palautaKoko();
        assertEquals(j, 3);
    }

   
    @Test
    public void testClear() {
        Kordinaatti k1 = new Kordinaatti(1, 1);
        Kordinaatti k2 = new Kordinaatti(2, 1);
        Kordinaatti k3 = new Kordinaatti(2, 2);
        Kordinaatti k4 = new Kordinaatti(1, 2);
        Kordinaatti b1 = new Kordinaatti(-1, -1);
        Kordinaatti b2 = new Kordinaatti(-1, -2);
        Kordinaatti b3 = new Kordinaatti(-2, -2);
        Kordinaatti b4 = new Kordinaatti(-2, -1);
        Kordinaatti alku = new Kordinaatti(0, 0);
        Kordinaatti loppu = new Kordinaatti(3, 3);
        Jono kordinaattijono = new Jono();
        Jono kordinaattijono2 = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        kordinaattijono2.lisaa(b1);
        kordinaattijono2.lisaa(b2);
        kordinaattijono2.lisaa(b3);
        kordinaattijono2.lisaa(b4);
        this.logiikka.lisaaMonikulmio(kordinaattijono);
        this.logiikka.lisaaMonikulmio(kordinaattijono2);
        this.logiikka.clear();
        Jono d = this.logiikka.palautaMonikulmio();
        int a = d.palautaKoko();
        assertEquals(a,0);
    }

  
    @Test
    public void testPoistaNs() {
       Kordinaatti k1 = new Kordinaatti(1, 1);
        Kordinaatti k2 = new Kordinaatti(2, 1);
        Kordinaatti k3 = new Kordinaatti(2, 2);
        Kordinaatti k4 = new Kordinaatti(1, 2);
        Kordinaatti b1 = new Kordinaatti(-1, -1);
        Kordinaatti b2 = new Kordinaatti(-1, -2);
        Kordinaatti b3 = new Kordinaatti(-2, -2);
        Kordinaatti b4 = new Kordinaatti(-2, -1);
        Kordinaatti alku = new Kordinaatti(0, 0);
        Kordinaatti loppu = new Kordinaatti(3, 3);
        Jono kordinaattijono = new Jono();
        Jono kordinaattijono2 = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        kordinaattijono2.lisaa(b1);
        kordinaattijono2.lisaa(b2);
        kordinaattijono2.lisaa(b3);
        kordinaattijono2.lisaa(b4);
        this.logiikka.lisaaMonikulmio(kordinaattijono);
        this.logiikka.lisaaMonikulmio(kordinaattijono2);
        this.logiikka.poistaNs(2);
        assertEquals(this.logiikka.palautaMonikulmio().palautaKoko(), 1);
    }

  

}
