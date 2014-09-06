/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka.algoritmi;

import com.mycompany.tiralabra_maven.Koordinaatit;

/**
 * Solmu sisältää tiedon pisteen koordinaateista, tähän asti kuljetusta
 * matkasta, ja linkitettynä listana reitin maaliin.
 *
 * @author mikko
 */
public class Solmu implements Comparable<Solmu> {

    private final Koordinaatit koordinaatit;
    private final int kuljettuMatka;
    private final Solmu edellinen;

    /**
     * Konstruktorissa annetaan solmun koordinaatit, tähän asti kuljettu matka
     * ja viite edelliseen solmuun.
     *
     * @param koordinaatit
     * @param kuljettuMatka
     * @param edellinen
     */
    public Solmu(Koordinaatit koordinaatit, int kuljettuMatka, Solmu edellinen) {
        this.koordinaatit = koordinaatit;
        this.kuljettuMatka = kuljettuMatka;
        this.edellinen = edellinen;
    }

    /**
     * Palauttaa tämän solmun koordinaatit.
     *
     * @return koordinaatit
     */
    public Koordinaatit getKoord() {
        return koordinaatit;
    }

    /**
     * Palauttaa tiedon tähän asti kuljetusta matkasta.
     *
     * @return kuljettu matka
     */
    public int getKuljettuMatka() {
        return kuljettuMatka;
    }

    /**
     * Palauttaa viitteen edelliseen solmuun tai null, jos edellistä ei ole.
     *
     * @return edellinen solmu tai null
     */
    public Solmu getEdellinen() {
        return this.edellinen;
    }

    /**
     * Vertailussa suositaan solmua, jonka kuljettu matka on pienempi
     *
     * @param o
     * @return tämän solmun kuljettu matka miinus parametrina annetun solmun
     * kuljettu matka
     */
    @Override
    public int compareTo(Solmu o) {
        return this.kuljettuMatka - o.kuljettuMatka;
    }

}
