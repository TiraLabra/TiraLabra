package com.mycompany.logiikka;

import com.mycompany.domain.Kasi;

/**
 * Luokka yrittää ennustaa pelaajan seuraavan käden ja 
 * vastata siihen
 */
public class Tekoaly {

    private Statistiikka statistiikka;
    private int moodi;
    private Logiikka logiikka;
    private Kasi viimeisinTekoalynKasi;

    /**
     * Konstruktori alustaa luokan muuttujat. Luokka tarvitsee
     * viitteen pelin käyttämiin Statistiikka, ja Logiikka-
     * olioihin
     * 
     * @param moodi Pelimoodi(1 tai 2)
     * @param s Statistiikkaolio
     * @param l Logiikkaolio
     */
    public Tekoaly(int moodi, Statistiikka s, Logiikka l) {
        this.statistiikka = s;
        this.moodi = moodi;
        this.logiikka = l;
        this.viimeisinTekoalynKasi = null;
    }

    /**
     * Sisäinen metodi joka laskee palauttaa argumenttina
     * annettun käden seuraavan käden rotaatiossa
     * <p>
     * Rotaatio:
     * Kivi-Paperi-Sakset-Lisko-Spock
     * 
     * @param k Käsi jonka seuraava käsi rotaatiossa halutaan
     * @return Rotaatiossa seuraava käsi
     */
    private Kasi paivitaSeuraavaRotaationKasi(Kasi k) {
        if (this.moodi == 1) {
            if (k.getNimi().equals("KIVI")) {
                return new Kasi("PAPERI");
            } else if (k.getNimi().equals("PAPERI")) {
                return new Kasi("SAKSET");
            } else {
                return new Kasi("KIVI");
            }
        } else {
            if (k.getNimi().equals("KIVI")) {
                return new Kasi("PAPERI");
            } else if (k.getNimi().equals("PAPERI")) {
                return new Kasi("SAKSET");
            } else if (k.getNimi().equals("SAKSET")) {
                return new Kasi("LISKO");
            } else if (k.getNimi().equals("LISKO")) {
                return new Kasi("SPOCK");
            } else {
                return new Kasi("KIVI");
            }
        }
    }

    /**
     * Tekoäly yrittää ennustaa seuraavalle vuorolle parhaan käden.
     * 
     * @return Paras käsi tekoälyn mukaan
     */
    public Kasi tekoalynTarjoamaKasi() {
//        Tekoälyn kehitetään vielä eteenpäin. Tarkoitus on saada
//        tekoäly voittamaan yli 80% peleistä!!!
        
        Kasi palauta = new Kasi("PAPERI");
        
        // pelin ensimmäinen kierros
        // TOISTAISEKSI palauttaa AINA paperin
        if (this.statistiikka.getKierrokset() == 0) {
            this.viimeisinTekoalynKasi = palauta;
            return palauta;
        }
        
        // tarkista kuka voitti edellisellä kierroksella
        Kasi edellinen = this.logiikka.pelaajanViimeisinKasi();
        this.logiikka.setPelaajanKasi(edellinen);
        this.logiikka.setTekoalynKasi(this.viimeisinTekoalynKasi);
        int pelitilanne = this.logiikka.pelaajaVoittaaKierroksen();
        
        // jos pelaaja voitti, käytä pelaajan voittanutta kättä
        if (pelitilanne == 1) {
            palauta = edellinen;
        } else if (pelitilanne == 0) { // tasapeli, pelaa tätä kättä voittava
            //palauta = edellinen;
            
        } else {
         // oleta pelaajan seuraavan rotaatiota
            Kasi oletus = paivitaSeuraavaRotaationKasi(edellinen);
            this.logiikka.setPelaajanKasi(oletus);
            for (int i=0; i<4; i++) {
                if (this.logiikka.pelaajaVoittaaKierroksen() >= 0) {
                    palauta = paivitaSeuraavaRotaationKasi(palauta);
                } else {
                    break;
                }
            }
        }

        this.viimeisinTekoalynKasi = palauta;
        return palauta;
    }

   
}
