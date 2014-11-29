/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko.satunnainen;

import java.util.Comparator;
import java.util.Random;
import tira.DynaaminenLista;
import tira.Lista;
import verkko.rajapinnat.*;

/**
 * Verkko: nxn-taulukko, jossa voi edetä koordinaattiakselien suuntaan yhden
 * ruudun kerrallaan
 *
 * @author E
 */
public class SatunnainenVerkko implements Graph {

    private Value[][] verkko;
    private Edge[][][] kaaret;
    private double[][] painot;
    private double minimiPaino, kerroinPaino;

    /**
     * Luo verkon, jonka koko on nxn
     *
     * @param koko
     */
    public SatunnainenVerkko(int koko) {
        painot = new double[koko][koko];
        verkko = new V[koko][koko];
        kaaret = new E[koko][koko][1];
        generoiSatunnainen();
    }

    /**
     * Verkon generointi
     */
    private void generoiSatunnainen() {
        Random random = new Random();
        for (int i = 0; i < verkko.length; i++) {
            for (int j = 0; j < verkko[i].length; j++) {
                painot[i][j] = minimiPaino + kerroinPaino * random.nextDouble(); // satunnainen paino tähän solmuun kulkemiselle
                verkko[i][j] = new V(i, j);
                kaaret[i][j][0] = new E(painot[i][j]);
            }
        }

    }

    public Value getSolmu(int i, int j) {
        return verkko[i][j];
    }
    
    public Node getNode() {
        return new Polku();
    }
    ///////////////////////
    /// GRAPH-RAJAPINTA ///
    ///////////////////////

    /**
     * Kaaret alkusolmusta loppusolmuun
     *
     * @param alku
     * @param loppu
     * @return
     */
    public Iterable<Edge> getKaaret(Value alku, Value loppu) {
        V l = (V) loppu;
        Lista<Edge> returnvalue = new DynaaminenLista(kaaret[l.getX()][l.getY()].length);
        for (Edge e : kaaret[l.getX()][l.getY()]) {
            returnvalue.add(e);
        }
        return returnvalue;
    }

    /**
     * Solmun naapurit
     *
     * @param alku
     * @return
     */
    public Iterable<Value> getNaapurit(Value alku) {
        V l = (V) alku;
        int x = l.getX();
        int y = l.getY();
        Lista<Value> returnvalue = new DynaaminenLista(4);
        if (x > 0) {
            returnvalue.add(verkko[x - 1][y]);
        }
        if (x < verkko.length - 1) {
            returnvalue.add(verkko[x + 1][y]);
        }
        if (y > 0) {
            returnvalue.add(verkko[x][y - 1]);
        }
        if (y < verkko.length - 1) {
            returnvalue.add(verkko[x][y + 1]);
        }
        return returnvalue;
    }

    /**
     * Verkon kaari
     *
     * @author E
     */
    private class E implements Edge {

        private double kustannus;

        public E(double kustannus) {
            this.kustannus = kustannus;
        }

        public void setKustannus(double kustannus) {
            this.kustannus = kustannus;
        }

        public double getKustannus() {
            return kustannus;
        }

    }

    /**
     * Verkon solmu
     *
     * @author E
     */
    private class V implements Value {

        private int x, y;

        public int getX() {
            return x;
        }

        public V(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getY() {
            return y;
        }

        public double etaisyys(Value s) {
            V v = (V) s;
            return Math.abs(v.getX() - this.getX()) + Math.abs(v.getY() - this.getY());
        }

    }

    /**
     * Verkossa kuljettu polku
     *
     * @author E
     */
    private class Polku implements Node {

        private double arvioituKustannus, kustannus;
        private Value solmu;
        private Edge kuljettuKaari;
        private Polku previous;

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

    }

}
