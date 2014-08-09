package com.mycompany.tiralabra_maven.logiikka;

import java.util.ArrayList;

/**
 *
 * @author Hannu
 */
public class Paikka implements Comparable<Paikka> {

    public int i; // tarvitaan vain TESTaukseen
    public int j; // tarvitaan vain TESTaukseen
    public int aikaKustannus;
    public int etaisyysAlkuun;
    public int etaisyysLoppuun;
    public ArrayList<Paikka> vierusPaikat;
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
}
