/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko.satunnainen;

import java.util.Arrays;
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
    private int rivit, sarakkeet;

    /**
     * Luo verkon, jonka koko on nxn
     *
     * @param koko
     */
    public SatunnainenVerkko(int koko) {
        this(koko, koko);
    }

    public SatunnainenVerkko(int rivit, int sarakkeet) {
        this(rivit, sarakkeet, 1, 20);

    }

    public SatunnainenVerkko(int rivit, int sarakkeet,double minimiPaino, double kerroinPaino) {
        this.minimiPaino = minimiPaino;
        this.kerroinPaino = kerroinPaino;
        this.rivit = rivit;
        this.sarakkeet = sarakkeet;
        painot = new double[rivit][sarakkeet];
        verkko = new V[rivit][sarakkeet];
        kaaret = new E[rivit][sarakkeet][1];
        generoiSatunnainen();        
    }
    
    /**
     * Verkon generointi
     */
    private void generoiSatunnainen() {
        Random random = new Random();
        for (int i = 0; i < rivit; i++) {
            for (int j = 0; j < sarakkeet; j++) {
                //double x = random.nextDouble();
                //if ( x > 0.5 )
                painot[i][j] = minimiPaino + (int)(kerroinPaino * random.nextDouble()) /* + random.nextInt((int) kerroinPaino)*/; // satunnainen paino tähän solmuun kulkemiselle
                //else 
                //    painot[i][j] = minimiPaino;
                verkko[i][j] = new V(i, j);
                kaaret[i][j][0] = new E( /*verkko[i][j],*/painot[i][j]);
            }
        }

    }

    public Value getSolmu(int i, int j) {
        return verkko[i][j];
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
        Lista<Edge> returnvalue = new DynaaminenLista(/* kaaret[l.getX()][l.getY()].length*/1);

        for (Edge e : kaaret[l.getX()][l.getY()]) {
            returnvalue.add(e);
        }

        // returnvalue.add( new E((V)alku,(V)loppu,painot[l.getX()][l.getY()]) );
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
        // ylös-alas, vasen-oikea
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
     * Uusi Polku-olio
     * 
     * @return 
     */
    public Node getNode() {
        return new Polku();
    }    

    @Override
    public String toString() {
        String s = "";
        for ( double[] d  : painot ) {
            s+=""+Arrays.toString(d)+"\n";
        }
        return s;
    }

    
    
    /**
     * Verkon kaari
     *
     * @author E
     */
    private class E implements Edge {

        private double kustannus;
        /*
        private V alku, loppu;
        private int x1, x2, y1, y2;
        */
        public E(double kustannus) {
            this.kustannus = kustannus;
        }
        /*
        public E(V alku, V loppu, double kustannus) {
            this.kustannus = kustannus;
            this.x1 = alku.getX(); //this.alku = alku;
            this.x2 = loppu.getX();
            this.y1 = alku.getY();
            this.y2 = loppu.getY(); //this.loppu = loppu;
        }
        */
        public void setKustannus(double kustannus) {
            this.kustannus = kustannus;
        }

        public double getKustannus() {
            return kustannus;
        }
        /*
         @Override
         public int hashCode() {
         int hash = 3;
         hash = 79 * hash + (int) (Double.doubleToLongBits(this.kustannus) ^ (Double.doubleToLongBits(this.kustannus) >>> 32));
         hash = 79 * hash + this.x1;
         hash = 79 * hash + this.x2;
         hash = 79 * hash + this.y1;
         hash = 79 * hash + this.y2;
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
         final E other = (E) obj;
         if (Double.doubleToLongBits(this.kustannus) != Double.doubleToLongBits(other.kustannus)) {
         return false;
         }
         if (this.x1 != other.x1) {
         return false;
         }
         if (this.x2 != other.x2) {
         return false;
         }
         if (this.y1 != other.y1) {
         return false;
         }
         if (this.y2 != other.y2) {
         return false;
         }
         return true;
         }
         */

        @Override
        public String toString() {
            return "E{" + "kustannus=" + kustannus + '}';
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

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 97 * hash + this.x;
            hash = 97 * hash + this.y;
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
            final V other = (V) obj;
            if (this.x != other.x) {
                return false;
            }
            if (this.y != other.y) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "V{" + "x=" + x + ", y=" + y + '}';
        }

    }

    /**
     * Verkossa kuljettu polku
     *
     * @author E
     */
    private class Polku implements Node {

        /**
         * AStar-haussa käytetyt kuljetun matkan pituus ja arvioitu loppumatkan pituus
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
            double cost =0; // = this.getKustannus();
            if (this.getKuljettuKaari()!= null) {
                cost=this.getKuljettuKaari().getKustannus()+this.getPrevious().totalCost();
            }
            return cost;
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
         * @return
         */
        public String toString() {
            /*
            String s = "" + this.getKuljettuKaari() + "->" + this.getSolmu();
            if (this.previous != null) {
                s = this.getPrevious().toString() + "->" + s;
            }
            return s;
            */
            return "Polku{reitti=" +this.tuloste()+ " ,pituus="+this.size()+", kustannus="+this.totalCost()+"}";
        }

    }

}
