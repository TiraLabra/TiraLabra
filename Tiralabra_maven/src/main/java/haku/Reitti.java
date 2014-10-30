/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haku;

import verkko.Kaari;
import verkko.Pysakki;

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
    private Pysakki   solmu;
    /**
     * Verkon kaarista luettu tähän solmuun johtanut kustannus
     */
    private double kustannus;
    /**
     * Heuristiikan arvioima loppumatka
     */
    private double arvioituKustannus;
    
    /*
    Pidettävä kirjaa: kuljettu matka, käytetty aika ( & tämänhetkinen aika)
    */
    private double aika;
    private double matka;
    
    
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

    
    public Pysakki getSolmu() {
        return solmu;
    }

    public void setSolmu(Pysakki solmu) {
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

    public double getAika() {
        return aika;
    }

    public void setAika(double aika) {
        this.aika = aika;
    }

    public double getMatka() {
        return matka;
    }

    public void setMatka(double matka) {
        this.matka = matka;
    }

    @Override
    public String toString() {
        String tuloste = "Reitti{" + "kuljettuKaari=" + kuljettuKaari 
                + ", solmu=" + solmu.getNimi() + ", kustannus=" + kustannus 
                + ", arvioituKustannus=" + arvioituKustannus + ", aika=" + aika 
                + ", matka=" + matka + '}';
        if ( this.getPrevious()!=null )
            tuloste = this.getPrevious().toString()+"\n"+tuloste;
        return tuloste;
    }
    
    
}
