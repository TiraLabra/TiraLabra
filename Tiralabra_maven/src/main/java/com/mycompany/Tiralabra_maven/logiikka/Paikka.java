package com.mycompany.Tiralabra_maven.logiikka;

import java.util.ArrayList;

/**
 * Luokka kuvaa paikkaa, joiden välillä siirrytään kun nopeinta reittiä haetaan.
 * Tätä luokkaa voi käyttää sekä Dijkstralle että Astarille: Dijkstarissa
 * attribuutin etaisyysLoppuun arvo on aina konstruktorissa asetettu eli 0.
 */
public class Paikka implements Comparable<Paikka> {

    /**
     * Paikan i-koordinaatti.
     */
    public int i;
    /**
     * Paikan j-koordinaatti.
     */
    public int j;
    /**
     * Luokan Paikka attribuutti aikaKustannus kertoo kuinka monta aikayksikköä
     * kuluu kun ko. Paikka-luokan ilmentymään siirrytään.
     */
    public int aikaKustannus;
    /**
     * Paikan etäisyys lähtöpisteeseen.
     */
    public int etaisyysAlkuun;
    /**
     * Paikan etäisyys loppupisteeseen. Dijkstarissa attribuutin etaisyysLoppuun
     * arvo on aina konstruktorissa asetettu eli 0.
     */
    public int etaisyysLoppuun;
    /**
     * Paikan viereiset paikat vaaka- ja pystysuunnassa (ei siis vinottain).
     * Keskellä karttaa olevalla Paikalla 4 vieruspaikka ja esim. nurkassa
     * olevalla Paikalla 2 vieruspaikkaa.
     */
    public ArrayList<Paikka> vierusPaikat;
    /**
     * Luokan Paikka attribuutti polku sisältää viitteen siihen Paikka-luokan
     * ilmentymään joka on (nopeimmalla) reitillä ennen ko. Paikka-luokan
     * ilmentymää.
     */
    public Paikka polku;

    /**
     * Luokan Paikka konstruktori.
     *
     * @param i Paikan i-koordinaatti.
     * @param j Paikan j-koordinaatti.
     * @param aikaKustannus kertoo kuinka monta aikayksikköä kuluu kun ko.
     * Paikka-luokan ilmentymään siirrytään
     */
    public Paikka(int i, int j, int aikaKustannus) {
        this.i = i;
        this.j = j;
        this.aikaKustannus = aikaKustannus;
        this.etaisyysAlkuun = Integer.MAX_VALUE / 2;
        this.etaisyysLoppuun = 0;
        this.vierusPaikat = new ArrayList<Paikka>();
        this.polku = null;
    }

    /**
     * Luokan Paikka toinen yksinkertaisempi konstruktori. Käytetään vain kun
     * luodaan Paikka-olio, josta halutaan saada Class Paikka metodilla
     * .getClass().
     */
    public Paikka() {
    }

    /**
     * Paikan komparaattori, jota käyttäen Paikat voidaan laittaa
     * etäisyysarvioden mukaiseen järjestykseen.
     *
     * @return kokonaisluku vertailua varten
     */
    @Override
    public int compareTo(Paikka paikka) {
        return (this.etaisyysAlkuun + this.etaisyysLoppuun) - (paikka.etaisyysAlkuun + paikka.etaisyysLoppuun);
    }

    public Paikka getPaikka(int i, int j) {
        return this;
    }
}
