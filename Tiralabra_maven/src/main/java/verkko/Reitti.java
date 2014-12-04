/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko;

import java.util.Comparator;
import tira.DynaaminenLista;
import tira.Lista;
import verkko.rajapinnat.Edge;
import verkko.rajapinnat.Node;
import verkko.rajapinnat.Value;
import verkko.satunnainen.Polku;

/**
 * Verkossa eteneminen: pidetään kirjaa reitistä, kuljetuista kaarista,
 * kuljetusta etäisyydestä, heuristinen arvio jäljellä olevasta matkasta sekä
 * nykyisestä solmusta Toteutus: tunnussolmuton yhteen suuntaan linkitetty lista
 * (olioviitteillä tässä)
 *
 * @author E
 */
public class Reitti implements Node {

    /**
     * Edellinen tila olioviittenä. Lista voidaan kääntää tätä käyttäen
     */
    private Reitti previous;
    /**
     * Kaari, jota pitkin solmuun saavuttiin
     */
    private Kaari kuljettuKaari;
    /**
     * Nykyinen solmu
     */
    private Pysakki solmu;
    /**
     * Verkon kaarista luettu tähän solmuun johtanut kustannus
     */
    private double kustannus;
    /**
     * Heuristiikan arvioima loppumatka
     */
    private double arvioituKustannus;

    /**
     * Kuljettu aika
     */
    private double aika;
    /**
     * Kuljettu matka
     */
    private double matka;

    /////////////////////////////////////////////
    ///// automaattiset setterit & getterit /////
    /////////////////////////////////////////////  
    public Reitti getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = (Reitti)previous;
    }

    public Kaari getKuljettuKaari() {
        return kuljettuKaari;
    }

    public void setKuljettuKaari(Edge kuljettuKaari) {
        this.kuljettuKaari = (Kaari)kuljettuKaari;
    }

    public Pysakki getSolmu() {
        return solmu;
    }

    public void setSolmu(Value solmu) {
        this.solmu = (Pysakki)solmu;
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

    /**
     * Reitin solmut järjestyksessä
     * 
     * @return 
     */
    public Lista<Value> solmut(  ) {
        Lista<Value> seuraajat = new DynaaminenLista();
        
        Reitti p = this;
        while ( p!=null ) {
            seuraajat.add(p.getSolmu());
            p = p.getPrevious();
        }
   
   
        return seuraajat;
    }
    /**
     * Reitin pituus.
     * 
     * @return 
     */
    public int size() {
        Reitti p = this;
        int i =0;
        while ( p!=null ) {
            i++;
            p = p.getPrevious();
        } 
        return i;
    }
    
    
    @Override
    public String toString() {
        String tuloste = "Reitti{" + "kuljettuKaari=" + kuljettuKaari
                + ", solmu=" + solmu.getNimi() + ", kustannus=" + kustannus
                + ", arvioituKustannus=" + arvioituKustannus + ", aika=" + aika
                + ", matka=" + matka + '}';
        if (this.getPrevious() != null) {
            tuloste = this.getPrevious().toString() + "\n" + tuloste;
        }
        return tuloste;
    }
    ///////////////////////////////////
    // AUTOMAATTINEN HASHCODE&EQUALS //
    ///////////////////////////////////
    // WIP: ovatko tarpeen, parempi toteutus näille
    
    /**
     * Hashcode lasketaan tämänhetkisestä solmusta.
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.solmu != null ? this.solmu.hashCode() : 0);
        return hash;
    }

    /**
     * WIP: Reitit ovat samoja, jos ollaan samassa solmussa
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reitti other = (Reitti) obj;
        if (this.solmu != other.solmu && (this.solmu == null || !this.solmu.equals(other.solmu))) {
            return false;
        }
        return true;
    }

    /**
     * Node-rajapinnan toteutus.
     * 
     * @param x Vertailijan tarkkuus
     * @return 
     */
    public Comparator<Node> vertailija( int x ) {
        final int tarkkuus = x;
        return new Comparator<Node>() {

            public int compare(Node r1, Node r2) {
                Reitti a=(Reitti)r1, b=(Reitti)r2;
                return (int) ( tarkkuus*( a.getArvioituKustannus() + a.getKustannus() - b.getArvioituKustannus() - b.getKustannus()));
            }
        };
    }
}
