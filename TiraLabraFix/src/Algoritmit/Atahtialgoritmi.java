/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmit;

import Tietorakenteet.Abstraktisolmu;
import Tietorakenteet.DiskreettiSolmu;
import Tietorakenteet.Iteroitava;
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
    private ArrayList<Abstraktisolmu> polku;


    /*
     * 
     * Konstruktori joka asettaa Verkon jota käytetään.
     * 
     */
    public Atahtialgoritmi(Verkko verkko, int maksimi) {
        this.verkko = verkko;
        this.polku = new ArrayList<Abstraktisolmu>();
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
        initKeko();
        this.verkko.tyhjenna();

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
            ArrayList<Abstraktisolmu> naapurit = this.verkko.naapurit(current);
            for (Abstraktisolmu naapuri : naapurit) {
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
        this.laskentaJoukko = new Keko();
        Iteroitava[] taulukko = new Iteroitava[this.maksimi];
        this.laskentaJoukko.asetaTaulukko(taulukko, 0);

    }

    /*
     * 
     * Rakentaa polun jolla kyseisen ongelman ratkaisu löytyy
     * 
     */
    public void rakennapolku() {
        Abstraktisolmu iteroiva = this.loppu;
        Stack<Abstraktisolmu> pino = new Stack<>();
        while (iteroiva != this.alku) {
            pino.add(iteroiva);
            iteroiva = iteroiva.palautaSolmuMuisti().palautaEdellinen();
        }
        if (iteroiva == this.alku) {
            pino.add(iteroiva);
        }
        while (!pino.empty()) {
            this.polku.add(pino.pop());
        }
    }

    public ArrayList<Abstraktisolmu> palautapolku() {
        return this.polku;
    }

}
