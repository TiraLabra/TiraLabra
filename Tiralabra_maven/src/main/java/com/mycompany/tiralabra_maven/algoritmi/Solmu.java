/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.algoritmi;

import com.mycompany.tiralabra_maven.Koordinaatit;

/**
 * Solmu sisältää tiedon pisteen koordinaateista, tähän asti kuljetusta
 * matkasta, ja linkitettynä listana reitin maaliin.
 *
 * @author mikko
 */
public class Solmu {

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
     * @return koordinaatit
     */
    public Koordinaatit getKoordinaatit() {
        return koordinaatit;
    }

    /**
     * Palauttaa tiedon tähän asti kuljetusta matkasta.
     * @return kuljettu matka
     */
    public int getKuljettuMatka() {
        return kuljettuMatka;
    }

    /**
     * Palauttaa merkkijonoesityksen solmusta.
     * @return stringit
     */
    @Override
    public String toString() {
        return this.koordinaatit.toString() + ", kuljettu " + this.kuljettuMatka;
    }

    /**
     * Palauttaa viitteen edelliseen solmuun tai null, jos edellistä ei ole.
     * @return edellinen solmu tai null
     */
    public Solmu getEdellinen() {
        return this.edellinen;
    }

}
