/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.algoritmi;

import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.gui.Paivitettava;
import com.mycompany.tiralabra_maven.Piirtologiikka;
import com.mycompany.tiralabra_maven.Suunta;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Simulaatio-luokka sisältää varsinaisen reittialgoritmin suoritettavana
 * säikeenä. Säikeen käynnistäminen (start-metodin kutsu) käynnistää algoritmin
 * suorituksen.
 *
 * @author mikko
 *
 */
public class Simulaatio extends Thread {

    private Koordinaatit alku;
    private Koordinaatit maali;
    private boolean hidaste;
    private final Piirtologiikka piirtologiikka;
    private final int leveys;
    private final int korkeus;
    //private Paivitettava paivitettava;
    private boolean valmis;
    private int[][] parhaatReitit;
    private Solmu reitti;
    private Heuristiikka heuristiikka;
    //private Scanner sc;

    private int[][] ruudukko = {
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    /**
     * Konstruktorissa annetaan parametrina päivitettävä piirtologiikka ja tieto
     * siitä, halutaanko hidastettu vai nopea simulaatio.
     *
     * @param piirtologiikka piirtologiikka jota päivitetään simulaation
     * edetessä.
     * @param hidaste jos true, odotetaan jonkin verran aikaa jokaisen
     * simulaation askeleen välillä.
     */
    public Simulaatio(Piirtologiikka piirtologiikka, boolean hidaste) {
        this.alku = new Koordinaatit(0, 0);
        this.maali = new Koordinaatit(9, 5);
        this.leveys = 10;
        this.korkeus = 10;
        this.piirtologiikka = piirtologiikka;
        this.valmis = false;
        this.parhaatReitit = new int[korkeus][leveys];
        this.hidaste = hidaste;
        this.heuristiikka = new ManhattanHeuristiikka(maali);
        //this.sc = new Scanner(System.in);
    }

    public void setHeuristiikka(Heuristiikka heuristiikka) {
        this.heuristiikka = heuristiikka;
    }

    public void setRuudukko(int[][] ruudukko) {
        this.ruudukko = ruudukko;
    }

    public void setAlkuPiste(Koordinaatit koord) {
        this.alku = koord;
    }

    public void setMaali(Koordinaatit koord) {
        this.maali = koord;
    }

    /**
     * Käynnistää algoritmin suorituksen.
     */
    @Override
    public void run() {
        alustaPiirtologiikka();
        alustaParhaatReitit();

        //Tehdään priorityQueue joka palauttaa aina sen solmun, jolle (etäisyys alkuun + arvioitu etäisyys loppuun) on pienin
        Vertailija vertailija = new Vertailija(this.heuristiikka);
        PriorityQueue<Solmu> tutkimattomat = new PriorityQueue<Solmu>(100, vertailija);

        Solmu solmu = new Solmu(alku, 0, null);

        System.out.println("alkutilanne: " + solmu);

        while (!solmu.getKoordinaatit().equals(maali)) {
            //käydään läpi solmun naapurisolmut ja lisätään ne tutkittavien joukkoon.
            for (Koordinaatit koord : solmunNaapurit(solmu)) {

                int matka = solmu.getKuljettuMatka() + ruudukko[koord.getY()][koord.getX()];

                //jos tähän solmuun ei ole päästy lyhyempää reittiä pitkin
                if (parhaatReitit[koord.getY()][koord.getX()] == -1 || matka < parhaatReitit[koord.getY()][koord.getX()]) {
                    parhaatReitit[koord.getY()][koord.getX()] = matka;
                    //Lisätään tutkittaviin uusi solmu, jonka kuljetuksi matkaksi annetaan tämän solmun kuljettu matka + maaston vaikeustaso
                    tutkimattomat.add(new Solmu(koord, matka, solmu));
                    piirtologiikka.setRuutu(koord.getX(), koord.getY(), Ruutu.TUTKIMATON);
                }
            }
            piirtologiikka.setRuutu(solmu.getKoordinaatit().getX(), solmu.getKoordinaatit().getY(), Ruutu.TUTKITTU);
            solmu = tutkimattomat.poll();
            piirtologiikka.setRuutu(solmu.getKoordinaatit().getX(), solmu.getKoordinaatit().getY(), Ruutu.KASITTELYSSA);
            if (this.hidaste) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
            }
        }

        System.out.println("REITTI (lopusta alkuun:");
        this.reitti = solmu;
        while (solmu != null) {
            piirtologiikka.setRuutu(solmu.getKoordinaatit().getX(), solmu.getKoordinaatit().getY(), Ruutu.REITTI);
            System.out.println(solmu);
            solmu = solmu.getEdellinen();
        }
        this.valmis = true;
    }

    private ArrayList<Koordinaatit> solmunNaapurit(Solmu solmu) {
        ArrayList<Koordinaatit> palautus = new ArrayList<Koordinaatit>();
        for (Suunta suunta : Suunta.values()) {
            Koordinaatit koord = solmu.getKoordinaatit().suuntaan(suunta);
            if (koordinaatitUlkopuolella(koord)) {
                continue;
            }
            if (ruudukko[koord.getY()][koord.getX()] == 0) {
                continue;
            }
            palautus.add(koord);
        }
        return palautus;
    }

    private void alustaParhaatReitit() {
        for (int x = 0; x < this.leveys; x++) {
            for (int y = 0; y < this.korkeus; y++) {
                parhaatReitit[y][x] = -1;
            }
        }
    }

    private void alustaPiirtologiikka() {
        for (int x = 0; x < this.leveys; x++) {
            for (int y = 0; y < this.korkeus; y++) {
                if (this.alku.getX() == x && this.alku.getY() == y) {
                    this.piirtologiikka.setRuutu(x, y, Ruutu.ALKU);
                } else if (this.maali.getX() == x && this.maali.getY() == y) {
                    this.piirtologiikka.setRuutu(x, y, Ruutu.MAALI);
                } else if (this.ruudukko[y][x] == 0) {
                    this.piirtologiikka.setRuutu(x, y, Ruutu.SEINA);
                }
            }
        }
    }

    private boolean koordinaatitUlkopuolella(Koordinaatit koordinaatit) {
        return koordinaatit.getX() < 0 || koordinaatit.getY() < 0 || koordinaatit.getX() >= ruudukko[0].length || koordinaatit.getY() >= ruudukko.length;

    }

    public int getLeveys() {
        return this.leveys;
    }

    public int getKorkeus() {
        return this.korkeus;
    }

    /**
     * Palauttaa tiedon siitä, onko algoritmin suorittaminen valmis, eli onko
     * reitti perille jo löytynyt.
     *
     * @return true, jos algoritmin suorittaminen on valmis ja false, jos ei ole
     */
    public boolean onkoValmis() {
        return this.valmis;
    }

    /**
     * Jos algoritmin suoritus on valmis (onkoValmis() -metodi palauttaa true),
     * niin tämä metodi palauttaa valmiin reitin maalisolmun, joka on linkitetty
     * lista kohti lähtöpistettä
     *
     * @return reitin viimeinen solmu
     */
    public Solmu getReitti() {
        return this.reitti;
    }

//    /**
//     * 
//     * @param paivitettava 
//     */
//    public void setPaivitettava(Paivitettava paivitettava) {
//        this.paivitettava = paivitettava;
//    }
}
