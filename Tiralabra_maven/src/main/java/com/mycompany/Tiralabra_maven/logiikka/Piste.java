package com.mycompany.Tiralabra_maven.logiikka;

/**
 * Tätä luokkaan käytetään lähtö ja maalipisteen koordinaattien antamiseen.
 */
public class Piste {

    /**
     * Pisteen i-koordinaatti.
     */
    public int i;
    /**
     * Pisteen j-koordinaatti.
     */
    public int j;

    /**
     * Pisteen konstruktori.
     * 
     * @param i Pisteen i-koordinaatti.
     * @param j Pisteen j-koordinaatti.
     */
    public Piste(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
