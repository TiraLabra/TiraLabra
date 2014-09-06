/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmit;

import Tietorakenteet.Abstraktisolmu;
import Tietorakenteet.DiskreettiSolmu;
import Tietorakenteet.Iteroitava;
import Tietorakenteet.JatkuvaSolmu;
import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Jono.Jonoiteroitava;
import Tietorakenteet.Keko;
import Tietorakenteet.Verkko;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Serafim
 */
/*
 * 
 * Atahtialgoritmi joka loytaa lyhimman polun kahden solmun välillä.
 * 
 */
public class Atahtialgoritmi {

    private Verkko verkko;
    private Abstraktisolmu alku;
    private Abstraktisolmu loppu;
    private Keko laskentaJoukko;
    private int maksimi;
    private double kokonaispituus;


    /*
     * 
     * Konstruktori joka asettaa Verkon jota käytetään.
     * 
     */
    public Atahtialgoritmi(Verkko verkko, int maksimi) {
        this.verkko = verkko;
        this.maksimi = maksimi;

    }
    /*
     * 
     * Palauttaa verkon
     * 
     */

    public Verkko palautaVerkko() {
        return this.verkko;
    }

    /*
     * 
     * Asettaa toisen verkon
     * 
     */
    public void asetaVerkko(Verkko verkko) {
        this.verkko = verkko;
    }
    /*
     * 
     * Asettaa alku ja loppu pisteet.
     * @param Abstraktisolmu alkusolmu
     * @param Abstraktisolmu loppusolmu
     */

    public void asetaPisteet(Abstraktisolmu alku, Abstraktisolmu loppu) {
        this.alku = alku;
        this.loppu = loppu;
    }

    /*
     * 
     * Laskee lyhimman polun
     * @return boolean palauttaa true jos reitti löytyy muuten false
     */
    public boolean laske() {
        this.verkko.tyhjenna();

        initKeko();

        this.laskentaJoukko.Lisaa(alku);
        alku.palautaSolmuMuisti().asetaKekoon(true);
        this.alku.palautaSolmuMuisti().asetaGScore(0);
        this.alku.palautaSolmuMuisti().asetaFScore(this.verkko.etaisyys(alku, loppu));
        while (this.laskentaJoukko.palautaNykyinenKoko() > 0) {
            Abstraktisolmu current = (Abstraktisolmu) this.laskentaJoukko.poistaMinimi();
            current.palautaSolmuMuisti().asetaKekoon(false);
            if (current == loppu) {

                return true;
            }
            current.palautaSolmuMuisti().Varita(1);
            Jono naapurit = this.verkko.naapurit(current);
            Jonoiteroitava naapurid = naapurit.palautaEnsimmainen();
            Abstraktisolmu naapuri = null;
            if (naapurid != null) {
                naapuri = (Abstraktisolmu) naapurid.palautaObjekti();
            }
            while (naapurid != null) {
                if (naapuri.palautaSolmuMuisti().palautaVari() != 1) {
                    double gscore = current.palautaSolmuMuisti().palautaGScore() + this.verkko.etaisyys(current, naapuri);
                    if ((naapuri.palautaSolmuMuisti().Keossa() == false) || (gscore < naapuri.palautaSolmuMuisti().palautaGScore())) {
                        naapuri.palautaSolmuMuisti().asetaEdellinen(current);
                        naapuri.palautaSolmuMuisti().asetaGScore(gscore);
                        double d = gscore + this.verkko.heurestiikka(naapuri, loppu);
                        boolean kasvaa;
                        if (d > naapuri.palautaSolmuMuisti().palautaFScore()) {
                            kasvaa = true;
                        } else {
                            kasvaa = false;
                        }
                        naapuri.palautaSolmuMuisti().asetaFScore(d);
                        if (naapuri.palautaSolmuMuisti().Keossa() == false) {
                            this.laskentaJoukko.Lisaa(naapuri);
                            naapuri.palautaSolmuMuisti().asetaKekoon(true);
                        } else {
                            if (kasvaa) {
                                this.laskentaJoukko.kasvatettu(naapuri.sijaintiKeossa());
                            } else {
                                this.laskentaJoukko.pienennetty(naapuri.sijaintiKeossa());
                            }
                        }
                    }
                }
                //alkionpaivitys
                naapurid = naapurid.palautaSeuraava();
                if (naapurid != null) {
                    naapuri = (Abstraktisolmu) naapurid.palautaObjekti();
                }
            }

        }
        return false;

    }

    /*
     * 
     * Luo uuden keon
     * 
     */
    public void initKeko() {
        this.laskentaJoukko = new Keko(true);
        Iteroitava[] taulukko = new Iteroitava[this.maksimi];
        this.laskentaJoukko.asetaTaulukko(taulukko, 0);

    }

    /*
     * 
     * Rakentaa polun jolla kyseisen ongelman ratkaisu löytyy. Palauttaa sen Kordinaatti olioiden muoddossa
     *  
     */
    public Jono palautapolku() {
        this.kokonaispituus = 0;
        Jono pino = new Jono();
        Jono palautus = new Jono();
        Abstraktisolmu iteroitava = this.loppu;
        while (iteroitava != this.alku) {
            pino.lisaa(iteroitava);
            iteroitava = iteroitava.palautaSolmuMuisti().palautaEdellinen();

        }
        if (iteroitava == this.alku) {
            pino.lisaa(iteroitava);
        }
        Jonoiteroitava iter = pino.palautaViimeinen();
        while (iter != null) {
            Abstraktisolmu s = (Abstraktisolmu) iter.palautaObjekti();

            palautus.lisaa(s.palautaKordinaatti());
            if (iter.palauataEdellinen() != null) {
                Abstraktisolmu d = (Abstraktisolmu) iter.palauataEdellinen().palautaObjekti();
                this.kokonaispituus = (this.kokonaispituus + this.verkko.etaisyys(s, d));

            }
            iter = iter.palauataEdellinen();

        }

        return palautus;

    }

    public double palautaPituus() {
        return this.kokonaispituus;
    }

}
