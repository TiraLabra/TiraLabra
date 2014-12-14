/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko.satunnainen;

import java.util.Comparator;
import tira.DynaaminenLista;
import tira.Lista;
import verkko.rajapinnat.Edge;
import verkko.rajapinnat.Node;
import verkko.rajapinnat.Value;

/**
 * Verkossa kuljettu polku
 *
 * @author E
 */
public class Polku implements Node {

    /**
     * AStar-haussa käytetyt kuljetun matkan pituus ja arvioitu loppumatkan
     * pituus
     */
    private double arvioituKustannus, kustannus;
    /**
     * Tämänhetkinen solmu
     */
    private Value solmu;
    /**
     * Viimeisin kuljettu kaari
     */
    private Edge kuljettuKaari;
    /**
     * Polku, jolta tähän solmuun on tultu
     */
    private Polku previous;

    /////////////////
    // RAJAPINNAT ///
    /////////////////
    public double getArvioituKustannus() {
        return arvioituKustannus;
    }

    public void setArvioituKustannus(double arvioituKustannus) {
        this.arvioituKustannus = arvioituKustannus;
    }

    public double getKustannus() {
        return kustannus;
    }

    public void setKustannus(double kustannus) {
        this.kustannus = kustannus;
    }

    public Value getSolmu() {
        return solmu;
    }

    public void setSolmu(Value solmu) {
        this.solmu = solmu;
    }

    public Edge getKuljettuKaari() {
        return kuljettuKaari;
    }

    public void setKuljettuKaari(Edge kuljettuKaari) {
        this.kuljettuKaari = kuljettuKaari;
    }

    public Polku getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = (Polku) previous;
    }

    /**
     * Node-rajapinnan toteutus.
     *
     * @param x Vertailijan tarkkuus
     * @return
     */
    public Comparator<Node> vertailija(int x) {
        final int tarkkuus = x;
        return new Comparator<Node>() {

            public int compare(Node r1, Node r2) {
                Polku a = (Polku) r1, b = (Polku) r2;
                return (int) (tarkkuus * (a.getArvioituKustannus() + a.getKustannus() - b.getArvioituKustannus() - b.getKustannus()));
            }
        };
    }

    ///////////////////////
    // YKSITYISET METODIT//
    ///////////////////////
    /**
     * Esitys polusta
     *
     * @return
     */
    private String tuloste() {
        String s = "" + this.getKuljettuKaari() + "->" + this.getSolmu();
        if (this.previous != null) {
            s = this.getPrevious().tuloste() + "->" + s;
        }
        return s;
    }

    //////////////////////
    // JULKISET METODIT //
    //////////////////////
    /**
     * Polun pituus saadaan kulkemalla alkuun asti
     *
     * @return
     */
    public int size() {
        if (this.getPrevious() == null) {
            return 1;
        } else {
            return 1 + this.getPrevious().size();
        }
    }

    /**
     * Polun kulkemisen kokonaiskustannus
     *
     * @return
     */
    public double totalCost() {
        double cost = 0; // = this.getKustannus();
        if (this.getKuljettuKaari() != null) {
            cost = this.getKuljettuKaari().getKustannus() + this.getPrevious().totalCost();
        }
        return cost;
    }

    /**
     * Polun solmut järjestyksessä
     *
     * @param seuraajat
     * @return
     */
    public Lista<Value> solmut() {
        Lista<Value> seuraajat = new DynaaminenLista();

        Polku p = this;
        while (p != null) {
            seuraajat.add(p.getSolmu());
            p = p.getPrevious();
        }

        return seuraajat;
    }

        ///////////////////////////
    // AUTOMAATTISET METODIT //
    ///////////////////////////
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (this.solmu != null ? this.solmu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Polku other = (Polku) obj;
        if (this.solmu != other.solmu && (this.solmu == null || !this.solmu.equals(other.solmu))) {
            return false;
        }
        return true;
    }

    /**
     * Merkkijonoesitys polusta
     *
     * @return Kuljettu reitti, reitin pituus ja kustannus
     */
    public String toString() {

        return "Polku{reitti=" + this.tuloste() + " ,pituus=" + this.size() + ", kustannus=" + this.totalCost() + "}";
    }

}
