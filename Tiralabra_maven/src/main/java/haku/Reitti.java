/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haku;

import verkko.Kaari;
import verkko.Solmu;

/**
 * Verkossa eteneminen: pidetään kirjaa reitistä, kuljetuista kaarista, kuljetusta etäisyydestä, heuristinen arvio jäljellä olevasta matkasta sekä nykyisestä solmusta
 * Toteutus: tunnussolmuton yhteen suuntaan linkitetty lista (olioviitteillä tässä)
 * @author E
 */
public class Reitti {
    /**
     * Edellinen tila olioviittenä. Lista voidaan kääntää tätä käyttäen
     */
    private Reitti    previous;
    /**
     * Kaari, jota pitkin solmuun saavuttiin
     */
    private Kaari  kuljettuKaari;
    /**
     * Nykyinen solmu
     */
    private Solmu   solmu;
    /**
     * Verkon kaarista luettu tähän solmuun johtanut kustannus
     */
    private double kustannus;
    /**
     * Heuristiikan arvioima loppumatka
     */
    private double arvioituKustannus;
    
    
    // automaattiset setterit & getterit

    public Reitti getPrevious() {
        return previous;
    }

    public void setPrevious(Reitti previous) {
        this.previous = previous;
    }

    public Kaari getKuljettuKaari() {
        return kuljettuKaari;
    }

    public void setKuljettuKaari(Kaari kuljettuKaari) {
        this.kuljettuKaari = kuljettuKaari;
    }

    
    public Solmu getSolmu() {
        return solmu;
    }

    public void setSolmu(Solmu solmu) {
        this.solmu = solmu;
    }

    public double getKustannus() {
        return kustannus;
    }

    public void setKustannus(double kustannus) {
        this.kustannus = kustannus;
    }

    public double getArvioituKustannus() {
        return arvioituKustannus;
    }

    public void setArvioituKustannus(double arvioituKustannus) {
        this.arvioituKustannus = arvioituKustannus;
    }
    
}
