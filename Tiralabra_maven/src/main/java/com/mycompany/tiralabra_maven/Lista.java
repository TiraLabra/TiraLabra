package com.mycompany.tiralabra_maven;

import java.util.Comparator;

/**
 * Tietorakenne, jolla on alkioiden käsittelyyn tarvittavia metodeja.
 * @author noora
 */
public class Lista<E> {

    private Object[] taulukko;
    private int koko;
    private int taulukonKoko;
    private Comparator<? super E> vertailija;

    /**
     * Konstruktorissa luodaan uusi taulukko, johon alkioita voi sitten tallettaa.
     * Taulukko on aluksi tyhjä ja sen pituus on määritelty.
     */
    public Lista() {
        this.taulukonKoko = 100;
        this.koko = 0;
        this.taulukko = new Object[taulukonKoko];
    }

    /**
     * Konstruktori, jolle voi antaa vertailijan, jonka avulla voidaan vertailla sellaisia objekteja, joiden luonnollinen vertailu ei onnistu.
     * @param vertailija Objektien vertailuun käytettty vertailija
     */
    public Lista(Comparator<? super E> vertailija) {
        this.vertailija = vertailija;
        this.taulukonKoko = 100;
        this.koko = 0;
        this.taulukko = new Object[taulukonKoko];
    }

    /**
     * Metodin avulla lisätään annettu objekti listaan.
     * Jos taulukko tulee täyteen, sitä kasvatetaan.
     * @param lisattava Listaan lisättävä objekti
     */
    public void lisaa(E lisattava) {
        if (koko >= taulukonKoko) {
            kasvataTaulukko();
        }
        this.koko++;
        int i = koko - 1;
        this.taulukko[i] = lisattava;
    }

    private void kasvataTaulukko() {
        this.taulukonKoko *= 2;
        Object[] uusiTaulukko = new Object[taulukonKoko];
        for (int i = 0; i < koko; i++) {
            uusiTaulukko[i] = taulukko[i];
        }
        this.taulukko = uusiTaulukko;
    }

    /**
     * Palauttaa Listan suurimman alkion. Vertailu tehdään tarvittaessa vertailijan avulla
     * @return Palauttaa listan suurimman alkion
     */
    public E getSuurin() {
        E palautus = (E) taulukko[0];
        if (this.vertailija == null) {
            for (int i = 0; i < koko; i++) {
                if (((Comparable<? super E>) (taulukko[i])).compareTo((E) palautus) > 0) {
                    palautus = (E) taulukko[i];
                }
            }
        } else {
            for (int j = 0; j < koko; j++) {
                if (vertailija.compare((E) taulukko[j], (E) palautus) > 0) {
                    palautus = (E) taulukko[j];
                }
            }

        }

        return palautus;
    }

    /**
     * Palauttaa taulukon tietyn alkion
     * @param paikka Mikä alkion halutaan
     * @return Haluttu alkio
     */
    public Object getAlkio(int paikka) {
        return this.taulukko[paikka];
    }

}
