package com.mycompany.Tiralabra_maven.logiikka;

import java.util.ArrayList;

/**
 * Luokka kuvaa paikkaa, joiden välillä siirrytään kun nopeinta reittiä haetaan.
 * Tätä luokkaa voi käyttää sekä Dijkstralle että Astarille: Dijkstarissa ei
 * käytetä attribuuttia etaisyysLoppuun.
 *
 * @author Hannu
 */
public class Paikka implements Comparable<Paikka> {

    public int i; // tarvitaan vain TESTaukseen
    public int j; // tarvitaan vain TESTaukseen
    /**
     * Luokan Paikka attribuutti aikaKustannus kertoo kuinka monta aikayksikköä
     * kuluu kun ko. Paikka-luokan ilmentymään siirrytään.
     */
    public int aikaKustannus;
    public int etaisyysAlkuun;
    public int etaisyysLoppuun;
    public ArrayList<Paikka> vierusPaikat;
    /**
     * Luokan Paikka attribuutti polku sisältää viitteen siihen Paikka-luokan
     * ilmentymään joka on (nopeimmalla) reitillä ennen ko. Paikka-luokan
     * ilmentymää.
     */
    public Paikka polku;

    public Paikka(int i, int j, int aikaKustannus) { // i ja j tarvitaan vain TESTaukseen
        this.i = i; // tarvitaan vain TESTaukseen
        this.j = j; // tarvitaan vain TESTaukseen
        this.aikaKustannus = aikaKustannus;
        this.etaisyysAlkuun = Integer.MAX_VALUE;
        this.etaisyysLoppuun = 0;
        this.vierusPaikat = new ArrayList<Paikka>();
        this.polku = null;
    }

    @Override
    public int compareTo(Paikka paikka) {
        return this.etaisyysAlkuun - paikka.etaisyysAlkuun;
    }

    public Paikka getPaikka(int i, int j) {
        return this;
    }
}
