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
    /**
     * Luo annetun kokoisen verkon
     * 
     * @param rivit
     * @param sarakkeet 
     */
    public SatunnainenVerkko(int rivit, int sarakkeet) {
        this(rivit, sarakkeet, 1, 20);

    }
    /**
     * Luo annetun kokoisen verkon
     * 
     * @param rivit
     * @param sarakkeet
     * @param minimiPaino Kaaren kulkemisen minimipaino
     * @param kerroinPaino Satunnaisluvun painotus kaarien kustannuksiin
     */
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
    /**
     * Palauttaa verkon solmun
     * 
     * @param i Rivi
     * @param j Sarake
     * @return 
     */
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
        if (x < rivit-1) {
            returnvalue.add(verkko[x + 1][y]);
        }
        if (y > 0) {
            returnvalue.add(verkko[x][y - 1]);
        }
        if (y < sarakkeet - 1) {
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

    ///////////////////////////
    // AUTOMAATTISET METODIT //
    ///////////////////////////

    
    // lisätty GUI:ta varten
    
    public int getRivit() {
        return rivit;
    }

    public int getSarakkeet() {
        return sarakkeet;
    }

    public double[][] getPainot() {
        return painot;
    }
    
    


}
