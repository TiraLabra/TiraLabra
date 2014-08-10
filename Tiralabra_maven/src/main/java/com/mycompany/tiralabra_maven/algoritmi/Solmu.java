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

    public Solmu(Koordinaatit koordinaatit, int kuljettuMatka, Solmu edellinen) {
        this.koordinaatit = koordinaatit;
        this.kuljettuMatka = kuljettuMatka;
        this.edellinen = edellinen;
    }

    public Koordinaatit getKoordinaatit() {
        return koordinaatit;
    }

    public int getKuljettuMatka() {
        return kuljettuMatka;
    }

    public String toString() {
        return this.koordinaatit.toString() + ", kuljettu " + this.kuljettuMatka;
    }

    public Solmu getEdellinen() {
        return this.edellinen;
    }

}
