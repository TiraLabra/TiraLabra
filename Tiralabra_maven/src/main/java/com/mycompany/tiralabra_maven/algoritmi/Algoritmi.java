/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.algoritmi;

import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.Suunta;
import com.mycompany.tiralabra_maven.gui.RuudunTila;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Algoritmi-luokka sisältää varsinaisen reittialgoritmin suoritettavana
 * säikeenä. Säikeen käynnistäminen (start-metodin kutsu) käynnistää algoritmin
 * suorituksen.
 *
 * @author mikko
 *
 */
public class Algoritmi extends Thread {

    private boolean jatketaanko;
    private Koordinaatit alku;
    private Koordinaatit maali;
    private boolean hidaste;
    private Simulaatio simulaatio;
    private final int leveys;
    private final int korkeus;
    private boolean valmis;
    private int[][] parhaatReitit;
    private Ruutu[][] maailma;
    private RuudunTila[][] ruutujenTilat;
    private Solmu reitti;
    private Heuristiikka heuristiikka;
    private boolean vinottain;

    /**
     * Konstruktorissa annetaan parametrina tieto siitä, halutaanko hidastettu
     * vai nopea simulaatio.
     *
     * @param hidaste jos true, odotetaan jonkin verran aikaa jokaisen
     * simulaation askeleen välillä.
     */
    public Algoritmi(Ruutu[][] maailma, boolean hidaste, Koordinaatit alku, Koordinaatit maali, boolean vinottain) {
        if (maailma == null) {
            throw new IllegalStateException("Maailma null");
        }

        this.alku = alku;
        this.maali = maali;
        this.leveys = maailma[0].length;
        this.korkeus = maailma.length;
        this.valmis = false;
        this.parhaatReitit = new int[korkeus][leveys];
        this.hidaste = hidaste;
        this.heuristiikka = new ManhattanHeuristiikka(maali);
        this.maailma = maailma;
        this.ruutujenTilat = new RuudunTila[korkeus][leveys];
        this.jatketaanko = true;
        this.vinottain = vinottain;
        //this.sc = new Scanner(System.in);
    }

    /**
     * Algoritmin asettama tila ruudulle tietyssä pisteessä. Jos ruutu on
     * koskematon, palautetaan null. Kertoo onko ruutu tutkittu, tai lisätty
     * tutkittavaksi, tai osa lopullista reittiä.
     *
     * @param x
     * @param y
     * @return
     */
    public RuudunTila getRuudunTila(int x, int y) {
        return this.ruutujenTilat[y][x];
    }

//    public void setHeuristiikka(Heuristiikka heuristiikka) {
//        this.heuristiikka = heuristiikka;
//    }
    
    /**
     * Lopettaa reittialgoritmin suorituksen.
     */
    public void lopeta() {
        this.jatketaanko = false;
        System.out.println("lopetettiin");
    }

    /**
     * Käynnistää algoritmin suorituksen.
     */
    @Override
    public void run() {
        //alustaPiirtologiikka();
        alustaParhaatReitit();

        //Tehdään priorityQueue joka palauttaa aina sen solmun, jolle (etäisyys alkuun + arvioitu etäisyys loppuun) on pienin
        Vertailija vertailija = new Vertailija(this.heuristiikka);
        PriorityQueue<Solmu> tutkimattomat = new PriorityQueue<Solmu>(100, vertailija);

        Solmu solmu = new Solmu(alku, 0, null);

        System.out.println("alkutilanne: " + solmu);

        while (!solmu.getKoordinaatit().equals(maali)) {
            if (!jatketaanko) {
                return;
            }

            //käydään läpi solmun naapurisolmut ja lisätään ne tutkittavien joukkoon.
            for (Koordinaatit koord : solmunNaapurit(solmu)) {

                //int matka = solmu.getKuljettuMatka() + ruudukko[koord.getY()][koord.getX()];
                int matka = solmu.getKuljettuMatka() + maailma[koord.getY()][koord.getX()].getHinta();

                //jos tähän solmuun ei ole päästy lyhyempää reittiä pitkin
                if (parhaatReitit[koord.getY()][koord.getX()] == -1 || matka < parhaatReitit[koord.getY()][koord.getX()]) {
                    parhaatReitit[koord.getY()][koord.getX()] = matka;
                    //Lisätään tutkittaviin uusi solmu, jonka kuljetuksi matkaksi annetaan tämän solmun kuljettu matka + maaston vaikeustaso
                    tutkimattomat.add(new Solmu(koord, matka, solmu));
                    //simulaatio.setRuutu(koord.getX(), koord.getY(), RuudunTila.TUTKIMATON);
                    ruutujenTilat[koord.getY()][koord.getX()] = RuudunTila.TUTKIMATON;
                }
            }
            ruutujenTilat[solmu.getKoordinaatit().getY()][solmu.getKoordinaatit().getX()] = RuudunTila.TUTKITTU;
            //simulaatio.setRuutu(solmu.getKoordinaatit().getX(), solmu.getKoordinaatit().getY(), Ruutu.TUTKITTU);
            solmu = tutkimattomat.poll();
            if (solmu == null) {
                return;
            }
            ruutujenTilat[solmu.getKoordinaatit().getY()][solmu.getKoordinaatit().getX()] = RuudunTila.KASITTELYSSA;
            //simulaatio.setRuutu(solmu.getKoordinaatit().getX(), solmu.getKoordinaatit().getY(), Ruutu.KASITTELYSSA);
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
            ruutujenTilat[solmu.getKoordinaatit().getY()][solmu.getKoordinaatit().getX()] = RuudunTila.REITTI;
            System.out.println(solmu);
            solmu = solmu.getEdellinen();
        }
        this.valmis = true;
    }

    private ArrayList<Koordinaatit> solmunNaapurit(Solmu solmu) {
        ArrayList<Koordinaatit> palautus = new ArrayList<Koordinaatit>();
        Suunta[] lapikaytavat;

        if (vinottain) {
            lapikaytavat = Suunta.values();
        } else {
            lapikaytavat = Suunta.kohtisuoratSuunnat();
        }

        for (Suunta suunta : lapikaytavat) {
            Koordinaatit koord = solmu.getKoordinaatit().suuntaan(suunta);
            if (koordinaatitUlkopuolella(koord)) {
                continue;
            }
            if (maailma[koord.getY()][koord.getX()] == null) {
                continue;
            }
            if (maailma[koord.getY()][koord.getX()].getHinta() == 0) {
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

//    private void alustaPiirtologiikka() {
//        for (int x = 0; x < this.leveys; x++) {
//            for (int y = 0; y < this.korkeus; y++) {
//                if (this.alku.getX() == x && this.alku.getY() == y) {
//                    this.simulaatio.setRuutu(x, y, Ruutu.ALKU);
//                } else if (this.maali.getX() == x && this.maali.getY() == y) {
//                    this.simulaatio.setRuutu(x, y, Ruutu.MAALI);
//                } else if (this.ruudukko[y][x] == 0) {
//                    this.simulaatio.setRuutu(x, y, Ruutu.SEINA);
//                }
//            }
//        }
//    }
    private boolean koordinaatitUlkopuolella(Koordinaatit koordinaatit) {
        return koordinaatit.getX() < 0 || koordinaatit.getY() < 0 || koordinaatit.getX() >= maailma[0].length || koordinaatit.getY() >= maailma.length;

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
