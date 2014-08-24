/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import java.util.ArrayList;
import java.util.HashMap;
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
public class DiskreettiVerkkoTest {

    public DiskreettiVerkko verkko;

    public DiskreettiVerkkoTest() {
    }

    @Before
    public void setUp() {
        this.verkko = new DiskreettiVerkko(1);
    }

    /**
     * Test of asetaRuudunpituus method, of class DiskreettiVerkko.
     */
    @Test
    public void testPalautaPituus() {
        double d = this.verkko.palautaPituus();
        assertEquals(d, 1, 0.01);
    }

    /**
     * Test of asetaKartta method, of class DiskreettiVerkko.
     */
    @Test
    public void testAsetaKartta() {
        HashMap<Kordinaatti, DiskreettiSolmu> kartta = new HashMap<Kordinaatti, DiskreettiSolmu>();
        this.verkko.asetaKartta(kartta);
        assertEquals(kartta, this.verkko.palautaKartta());
    }

    /**
     * Test of Naapurit method, of class DiskreettiVerkko.
     */
    @Test
    public void testNaapuritOsa1() {
        HashMap<Kordinaatti, DiskreettiSolmu> kartta = new HashMap<Kordinaatti, DiskreettiSolmu>();
        DiskreettiSolmu[] solmuvektori = new DiskreettiSolmu[9];
        solmuvektori[0] = new DiskreettiSolmu(0, 0);
        solmuvektori[1] = new DiskreettiSolmu(1, 1);
        solmuvektori[2] = new DiskreettiSolmu(-1, -1);
        solmuvektori[3] = new DiskreettiSolmu(1, -1);
        solmuvektori[4] = new DiskreettiSolmu(-1, 1);
        solmuvektori[5] = new DiskreettiSolmu(1, 0);
        solmuvektori[6] = new DiskreettiSolmu(0, 1);
        solmuvektori[7] = new DiskreettiSolmu(-1, 0);
        solmuvektori[8] = new DiskreettiSolmu(0, -1);
        for (int i = 0; i < 9; i++) {
            Kordinaatti k = solmuvektori[i].palautaKordinaatit();
            kartta.put(k, solmuvektori[i]);
        }
        DiskreettiVerkko verkkor = new DiskreettiVerkko(1);
        verkkor.asetaKartta(kartta);
        ArrayList<Abstraktisolmu> lista = verkkor.naapurit(solmuvektori[0]);
        int i = lista.size();
        assertEquals(i, 8);

    }

    @Test
    public void testNaapuritOsa2() {
        HashMap<Kordinaatti, DiskreettiSolmu> kartta = new HashMap<Kordinaatti, DiskreettiSolmu>();
        DiskreettiSolmu[] solmuvektori = new DiskreettiSolmu[9];
        solmuvektori[0] = new DiskreettiSolmu(0, 0);
        solmuvektori[1] = new DiskreettiSolmu(1, 1);
        solmuvektori[2] = new DiskreettiSolmu(-1, -1);
        solmuvektori[3] = new DiskreettiSolmu(1, -1);
        solmuvektori[4] = new DiskreettiSolmu(-1, 1);
        solmuvektori[5] = new DiskreettiSolmu(1, 0);
        solmuvektori[6] = new DiskreettiSolmu(0, 1);
        solmuvektori[7] = new DiskreettiSolmu(-1, 0);
        solmuvektori[8] = new DiskreettiSolmu(0, -1);
        for (int i = 0; i < 9; i++) {
            Kordinaatti k = solmuvektori[i].palautaKordinaatit();
            kartta.put(k, solmuvektori[i]);
        }
        DiskreettiVerkko verkkor = new DiskreettiVerkko(1);
        verkkor.asetaKartta(kartta);
        ArrayList<Abstraktisolmu> lista = verkkor.naapurit(solmuvektori[1]);
        int i = lista.size();
        assertEquals(i, 3);

    }

    @Test
    public void testNaapuritOsa3() {
        HashMap<Kordinaatti, DiskreettiSolmu> kartta = new HashMap<Kordinaatti, DiskreettiSolmu>();
        DiskreettiSolmu[] solmuvektori = new DiskreettiSolmu[9];
        solmuvektori[0] = new DiskreettiSolmu(0, 0);
        solmuvektori[1] = new DiskreettiSolmu(1, 1);
        solmuvektori[2] = new DiskreettiSolmu(-1, -1);
        solmuvektori[3] = new DiskreettiSolmu(1, -1);
        solmuvektori[4] = new DiskreettiSolmu(-1, 1);
        solmuvektori[5] = new DiskreettiSolmu(1, 0);
        solmuvektori[6] = new DiskreettiSolmu(0, 1);
        solmuvektori[7] = new DiskreettiSolmu(-1, 0);
        solmuvektori[8] = new DiskreettiSolmu(0, -1);
        for (int i = 0; i < 9; i++) {
            Kordinaatti k = solmuvektori[i].palautaKordinaatit();
            kartta.put(k, solmuvektori[i]);
        }
        DiskreettiVerkko verkkor = new DiskreettiVerkko(1);
        verkkor.asetaKartta(kartta);
        ArrayList<Abstraktisolmu> lista = verkkor.naapurit(solmuvektori[5]);
        int i = lista.size();
        assertEquals(i, 5);

    }

    /**
     * Test of Olemassa method, of class DiskreettiVerkko.
     */
    @Test
    public void testOlemassa() {
        HashMap<Kordinaatti, DiskreettiSolmu> kartta = new HashMap<Kordinaatti, DiskreettiSolmu>();
        DiskreettiSolmu[] solmuvektori = new DiskreettiSolmu[9];
        solmuvektori[0] = new DiskreettiSolmu(0, 0);

        Kordinaatti k = solmuvektori[0].palautaKordinaatit();
        kartta.put(k, solmuvektori[0]);
        this.verkko.asetaKartta(kartta);

        assertEquals(true, this.verkko.olemassa(solmuvektori[0]));

    }

    @Test
    public void testOlemassa2() {
        HashMap<Kordinaatti, DiskreettiSolmu> kartta = new HashMap<Kordinaatti, DiskreettiSolmu>();
        DiskreettiSolmu[] solmuvektori = new DiskreettiSolmu[9];
        solmuvektori[0] = new DiskreettiSolmu(0, 0);

        Kordinaatti k = solmuvektori[0].palautaKordinaatit();
        kartta.put(k, solmuvektori[0]);

        assertEquals(false, this.verkko.olemassa(new DiskreettiSolmu(2, 3)));

    }

    /**
     * Test of Heurestiikka method, of class DiskreettiVerkko.
     */
    /**
     * Test of Etaisyys method, of class DiskreettiVerkko.
     */
    @Test
    public void testEtaisyys() {
        DiskreettiSolmu r = new DiskreettiSolmu(1,1);
        DiskreettiSolmu r2 = new DiskreettiSolmu(4,5);
        double d = this.verkko.etaisyys(r, r2);
        assertEquals(d, 5, 0.01);

    }
}
