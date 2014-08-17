/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.algoritmi;

import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.Toiminto;
import com.mycompany.tiralabra_maven.gui.PiirrettavaRuutu;
import com.mycompany.tiralabra_maven.gui.RuudunTila;
import com.mycompany.tiralabra_maven.gui.Ruutu;

public class Simulaatio {

    private Ruutu[][] maailma;
    private Koordinaatit alku;
    private Koordinaatit maali;
    private boolean hidaste;
    private boolean vinottain;
    private int leveys;
    private int korkeus;
    //private Paivitettava paivitettava;
    private boolean valmis;
    private int[][] parhaatReitit;
    private Solmu reitti;
    private Heuristiikka heuristiikka;

    //private Scanner sc;
    private Algoritmi algoritmi;

    //Jonkun toisen luokan asiaa(?):
    private Koordinaatit hiiri;
    private boolean hiiriPainettu;
    private Toiminto toiminto = Toiminto.SEINA;

    /**
     * Konstruktorissa annetaan parametrina päivitettävä piirtologiikka ja tieto
     * siitä, halutaanko hidastettu vai nopea simulaatio.
     *
     * @param hidaste jos true, odotetaan jonkin verran aikaa jokaisen
     * simulaation askeleen välillä.
     */
    public Simulaatio(boolean hidaste) {
        this.leveys = 24;
        this.korkeus = 20;
        alustaMaailma();
        this.hidaste = hidaste;
        this.alku = new Koordinaatit(0, 0);
        this.maali = new Koordinaatit(9, 5);
        this.vinottain = true;
    }

    private void alustaMaailma() {
        this.maailma = new Ruutu[korkeus][leveys];
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                maailma[y][x] = Ruutu.LATTIA;
            }
        }
    }

    public void teeUusiRuudukko(int leveys, int korkeus) {
        lopetaReitinEtsiminen();
        this.leveys = leveys;
        this.korkeus = korkeus;
        alustaMaailma();
    }

    public Ruutu[][] getRuudukko() {
        return this.maailma;
    }

//    public Ruutu getRuutu(int x, int y) {
//        return this.ruudut[y][x];
//    }
    public void setRuutu(int x, int y, Ruutu ruutu) {
        this.maailma[y][x] = ruutu;
    }

    public void setHeuristiikka(Heuristiikka heuristiikka) {
        this.heuristiikka = heuristiikka;
    }

    public void setAlkuPiste(Koordinaatit koord) {
        this.alku = koord;
    }

    public Koordinaatit getAlkuPiste() {
        return this.alku;
    }

    public void setMaali(Koordinaatit koord) {
        this.maali = koord;
    }

    public Koordinaatit getMaali() {
        return this.maali;
    }

    public void etsiReitti() {
        this.algoritmi = new Algoritmi(maailma, hidaste, alku, maali, vinottain);
        this.algoritmi.start();
    }

    public void lopetaReitinEtsiminen() {
        if (this.algoritmi == null) {
            return;
        }
        this.algoritmi.lopeta();
        this.algoritmi = null;
    }

    public int getLeveys() {
        return this.leveys;
    }

    public int getKorkeus() {
        return this.korkeus;
    }

    public boolean onkoSimulaatioKaynnissa() {
        return this.algoritmi != null;
    }

    public void asetaVinottainLiikkuminenSallituksi(boolean sallittu) {
        this.vinottain = sallittu;
    }

    public boolean saakoLiikkuaVinottain() {
        return this.vinottain;
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

    /**
     * Tämän metodin avulla piirtologiikka saa tiedon siitä, että hiiri on tällä
     * hetkellä jonkun ruudukon ruudun päällä. Tätä tietoa voidaan käyttää
     * avuksi varsinaisessa käyttöliittymäkoodissa.
     *
     * @param x
     * @param y
     */
    public void hiiriRuudunPaalla(int x, int y) {
        if (hiiri == null || x != hiiri.getX() || y != hiiri.getY()) {
            this.hiiri = new Koordinaatit(x, y);
            suoritaToimintoJosHiiriPainettu();
        }
    }

    /**
     * Tämän metodin avulla piirtologiikka saa tiedon siitä, että hiiri on
     * poistunut ruudukon päältä.
     */
    public void hiiriPoistunut() {
        this.hiiri = null;
    }

    /**
     * Palauttaa sen ruudun koordinaatit, jonka päällä hiiri on, tai null, jos
     * hiiri ei ole minkään ruudun päällä.
     *
     * @return hiiren koordinaatit
     */
    public Koordinaatit hiirenKoordinaatit() {
        return this.hiiri;
    }

    /**
     * Tämän metodin avulla piirtologiikka saa tiedon siitä, että hiiren nappi
     * on painettu pohjaan tai päästetty irti.
     */
    public void hiiriPainettu(boolean painettu) {
        //System.out.println("painettu: " + painettu);
        this.hiiriPainettu = painettu;
        suoritaToimintoJosHiiriPainettu();
    }

    private void suoritaToimintoJosHiiriPainettu() {
        if (this.hiiriPainettu) {
            switch (toiminto) {
                case SEINA:
                    maailma[hiiri.getY()][hiiri.getX()] = Ruutu.SEINA;
                    break;
                case LATTIA:
                    maailma[hiiri.getY()][hiiri.getX()] = Ruutu.LATTIA;
                    break;
                case ALKU:
                    this.alku = new Koordinaatit(hiiri.getX(), hiiri.getY());
                    break;
                case MAALI:
                    this.maali = new Koordinaatit(hiiri.getX(), hiiri.getY());
            }

        }
    }

    /**
     * Palauttaa true jos hiiren nappi on painettuna pohjaan.
     *
     * @return onko painettu pohjaan
     */
    public boolean onkoHiiriPainettu() {
        return this.hiiriPainettu;
    }

    public Toiminto getValittuToiminto() {
        return this.toiminto;
    }

    public void setToiminto(Toiminto toiminto) {
        this.toiminto = toiminto;
    }

    public PiirrettavaRuutu getRuutu(int x, int y) {
        if (algoritmi != null) {
            RuudunTila ruuduntila = algoritmi.getRuudunTila(x, y);
            if (ruuduntila != null) {
                return ruuduntila;
            }
        }
        return maailma[y][x];
    }

}
