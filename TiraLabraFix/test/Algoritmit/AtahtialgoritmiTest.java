/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmit;

import Tietorakenteet.Abstraktisolmu;
import Tietorakenteet.DiskreettiSolmu;
import Tietorakenteet.DiskreettiVerkko;
import Tietorakenteet.JatkuvaSolmu;
import Tietorakenteet.JatkuvaVerkko;
import Tietorakenteet.Jatkuvamonikulmio;
import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Kordinaatti;
import Tietorakenteet.Verkko;
import java.util.ArrayList;
import java.util.HashMap;
import logiikka.Jatkuvaverkkorakennus;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AtahtialgoritmiTest {

    /**
     *
     */
    private Atahtialgoritmi algoritmi;
    private DiskreettiSolmu[] solmuvektori;
    private DiskreettiVerkko verkko;

    @Before
    public void setUp() {

        this.verkko = new DiskreettiVerkko(1);

    }

    /**
     * Test of laske method, of class Atahtialgoritmi.
     */
    @Test
    public void loytaakoreitinneljanpisteenruudukossa() {
        this.solmuvektori = new DiskreettiSolmu[4];
        this.solmuvektori[0] = new DiskreettiSolmu(0, 0);
        this.solmuvektori[1] = new DiskreettiSolmu(0, 1);
        this.solmuvektori[2] = new DiskreettiSolmu(1, 0);
        this.solmuvektori[3] = new DiskreettiSolmu(1, 1);
        Atahtialgoritmi alg = new Atahtialgoritmi(this.verkko, 100);
        alg.asetaPisteet(solmuvektori[0], solmuvektori[3]);
        this.verkko.Lisaa(this.solmuvektori);
        assertEquals(alg.laske(), true);

    }

    @Test
    public void loytaakoreitinneljanpisteenruudukossaOsa2() {
        this.solmuvektori = new DiskreettiSolmu[4];
        this.solmuvektori[0] = new DiskreettiSolmu(0, 0);
        this.solmuvektori[1] = new DiskreettiSolmu(0, 1);
        this.solmuvektori[2] = new DiskreettiSolmu(1, 0);
        this.solmuvektori[3] = new DiskreettiSolmu(1, 1);
        Atahtialgoritmi alg = new Atahtialgoritmi(this.verkko, 100);
        alg.asetaPisteet(solmuvektori[0], solmuvektori[3]);
        this.verkko.Lisaa(this.solmuvektori);
        alg.laske();
        assertEquals(this.solmuvektori[3].palautaSolmuMuisti().palautaEdellinen(), this.solmuvektori[0]);

    }

    /**
     * Test of initKeko method, of class Atahtialgoritmi.
     */
    @Test
    public void loytaakovahansuuremassaymparistossa() {

        this.solmuvektori = new DiskreettiSolmu[9];
        this.solmuvektori[0] = new DiskreettiSolmu(0, 0);
        this.solmuvektori[1] = new DiskreettiSolmu(0, 1);
        this.solmuvektori[2] = new DiskreettiSolmu(1, 0);
        this.solmuvektori[3] = new DiskreettiSolmu(1, 1);
        this.solmuvektori[4] = new DiskreettiSolmu(0, 2);
        this.solmuvektori[5] = new DiskreettiSolmu(2, 0);
        this.solmuvektori[6] = new DiskreettiSolmu(1, 2);
        this.solmuvektori[7] = new DiskreettiSolmu(2, 1);
        this.solmuvektori[8] = new DiskreettiSolmu(2, 2);
        Atahtialgoritmi alg = new Atahtialgoritmi(this.verkko, 100);
        alg.asetaPisteet(solmuvektori[0], solmuvektori[8]);
        this.verkko.Lisaa(this.solmuvektori);
        assertEquals(alg.laske(), true);

    }

    @Test
    public void loytaakovahansuuremassaymparistossaosa2() {

        solmuvektori = new DiskreettiSolmu[9];
        solmuvektori[0] = new DiskreettiSolmu(0, 0);
        solmuvektori[1] = new DiskreettiSolmu(0, 1);
        solmuvektori[2] = new DiskreettiSolmu(1, 0);
        solmuvektori[3] = new DiskreettiSolmu(1, 1);
        solmuvektori[4] = new DiskreettiSolmu(0, 2);
        solmuvektori[5] = new DiskreettiSolmu(2, 0);
        solmuvektori[6] = new DiskreettiSolmu(1, 2);
        solmuvektori[7] = new DiskreettiSolmu(2, 1);
        solmuvektori[8] = new DiskreettiSolmu(2, 2);
        Atahtialgoritmi alg = new Atahtialgoritmi(this.verkko, 100);
        alg.asetaPisteet(solmuvektori[0], solmuvektori[8]);
        this.verkko.Lisaa(this.solmuvektori);
        alg.laske();
        assertEquals(this.solmuvektori[8].palautaSolmuMuisti().palautaEdellinen().palautaSolmuMuisti().palautaEdellinen(), this.solmuvektori[0]);

    }

    /**
     * Test of rakennapolku method, of class Atahtialgoritmi.
     */
    @Test
    public void testRakennapolku() {

        solmuvektori = new DiskreettiSolmu[9];
        solmuvektori[0] = new DiskreettiSolmu(0, 0);
        solmuvektori[1] = new DiskreettiSolmu(0, 1);
        solmuvektori[2] = new DiskreettiSolmu(1, 0);
        solmuvektori[3] = new DiskreettiSolmu(1, 1);
        solmuvektori[4] = new DiskreettiSolmu(0, 2);
        solmuvektori[5] = new DiskreettiSolmu(2, 0);
        solmuvektori[6] = new DiskreettiSolmu(1, 2);
        solmuvektori[7] = new DiskreettiSolmu(2, 1);
        solmuvektori[8] = new DiskreettiSolmu(2, 2);
        Atahtialgoritmi alg = new Atahtialgoritmi(this.verkko, 100);
        alg.asetaPisteet(solmuvektori[0], solmuvektori[8]);
        this.verkko.Lisaa(this.solmuvektori);
        alg.laske();
        
        assertEquals(alg.palautapolku().palautaKoko(), 3);

    }

    @Test
    public void testReitinhakuJossaEiReittia() {

        solmuvektori = new DiskreettiSolmu[9];
        solmuvektori[0] = new DiskreettiSolmu(0, 0);
        solmuvektori[1] = new DiskreettiSolmu(0, 1);
        solmuvektori[2] = new DiskreettiSolmu(1, 0);
        solmuvektori[3] = new DiskreettiSolmu(1, 1);
        solmuvektori[4] = new DiskreettiSolmu(0, 2);
        solmuvektori[5] = new DiskreettiSolmu(2, 0);
        solmuvektori[6] = new DiskreettiSolmu(1, 2);
        solmuvektori[7] = new DiskreettiSolmu(2, 1);
        solmuvektori[8] = new DiskreettiSolmu(2, 2);
        solmuvektori[1].asetaKulku(false);
        solmuvektori[2].asetaKulku(false);
        solmuvektori[3].asetaKulku(false);
        Atahtialgoritmi alg = new Atahtialgoritmi(this.verkko, 100);
        alg.asetaPisteet(solmuvektori[0], solmuvektori[8]);
        this.verkko.Lisaa(this.solmuvektori);

        assertEquals(alg.laske(), false);

    }
    @Test
    public void testYksiErikoistapauslisaa() {

        solmuvektori = new DiskreettiSolmu[9];
        solmuvektori[0] = new DiskreettiSolmu(0, 0);
        solmuvektori[1] = new DiskreettiSolmu(0, 1);
        solmuvektori[2] = new DiskreettiSolmu(1, 0);
        solmuvektori[3] = new DiskreettiSolmu(1, 1);
        solmuvektori[4] = new DiskreettiSolmu(0, 2);
        solmuvektori[5] = new DiskreettiSolmu(2, 0);
        solmuvektori[6] = new DiskreettiSolmu(1, 2);
        solmuvektori[7] = new DiskreettiSolmu(2, 1);
        solmuvektori[8] = new DiskreettiSolmu(2, 2);
        solmuvektori[1].asetaKulku(false);
        solmuvektori[2].asetaKulku(false);
        solmuvektori[4].asetaKulku(false);
        solmuvektori[5].asetaKulku(false);
        solmuvektori[6].asetaKulku(false);
        solmuvektori[7].asetaKulku(false);


        Atahtialgoritmi alg = new Atahtialgoritmi(this.verkko, 100);
        alg.asetaPisteet(solmuvektori[0], solmuvektori[8]);
        this.verkko.Lisaa(this.solmuvektori);

        assertEquals(alg.laske(), true);

    }
    @Test
    public void tavallinentapaus()
    {
     Kordinaatti k1 = new Kordinaatti(1, 1);
        Kordinaatti k2 = new Kordinaatti(2, 1);
        Kordinaatti k3 = new Kordinaatti(2, 2);
        Kordinaatti k4 = new Kordinaatti(1, 2);
        Kordinaatti alku = new Kordinaatti(0, 0);
        Kordinaatti loppu = new Kordinaatti(3, 3);
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
        System.out.println(tarkistus);
        JatkuvaSolmu alkus = (JatkuvaSolmu) rakentaja.palautaAlku();
        JatkuvaSolmu loppus = (JatkuvaSolmu) rakentaja.palautaLoppu();
        Atahtialgoritmi algoritmi = new Atahtialgoritmi(xD, 20);
        algoritmi.asetaPisteet(alkus, loppus);
        boolean d = algoritmi.laske();
        assertEquals(d, true);
    }
     @Test
    public void tavallinentapauspituus()
    {
     Kordinaatti k1 = new Kordinaatti(1, 1);
        Kordinaatti k2 = new Kordinaatti(2, 1);
        Kordinaatti k3 = new Kordinaatti(2, 2);
        Kordinaatti k4 = new Kordinaatti(1, 2);
        Kordinaatti alku = new Kordinaatti(0, 0);
        Kordinaatti loppu = new Kordinaatti(3, 3);
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
        System.out.println(tarkistus);
        JatkuvaSolmu alkus = (JatkuvaSolmu) rakentaja.palautaAlku();
        JatkuvaSolmu loppus = (JatkuvaSolmu) rakentaja.palautaLoppu();
        Atahtialgoritmi algoritmi = new Atahtialgoritmi(xD, 20);
        algoritmi.asetaVerkko(xD);
        algoritmi.asetaPisteet(alkus, loppus);
        algoritmi.laske();
        algoritmi.palautapolku();
        double abcd = algoritmi.palautaPituus();
        double zad = 2*Math.sqrt(5);
        assertEquals(abcd, zad, 0.01 );
    }

}
