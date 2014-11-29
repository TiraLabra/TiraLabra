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
    private double minimiPaino=1, kerroinPaino=2;
    private int rivit,sarakkeet;

    /**
     * Luo verkon, jonka koko on nxn
     *
     * @param koko
     */
    public SatunnainenVerkko(int koko) {
        rivit=koko;
        sarakkeet=koko;
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
                painot[i][j] = minimiPaino + kerroinPaino * random.nextDouble(); // satunnainen paino tähän solmuun kulkemiselle
                verkko[i][j] = new V(i, j);
                kaaret[i][j][0] = new E( /*verkko[i][j],*/ painot[i][j] );
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
        Lista<Edge> returnvalue = new DynaaminenLista(/* kaaret[l.getX()][l.getY()].length*/ 1);
        
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
        private V alku,loppu;
        private int x1,x2,y1,y2;

        public E(double kustannus) {
            this.kustannus=kustannus;
        }
        public E(V alku, V loppu,double kustannus) {
            this.kustannus = kustannus;
            this.x1=alku.getX(); //this.alku = alku;
            this.x2=loppu.getX();
            this.y1=alku.getY();
            this.y2=loppu.getY(); //this.loppu = loppu;
        }

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
        
        public String toString() {
            String s = ""+this.getKuljettuKaari()+"->"+this.getSolmu();
            if ( this.previous!=null ) s=this.getPrevious().toString()+"->"+s;
            return s;
        }
        
        
    }

}
