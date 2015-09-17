/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.astar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Reitinhaku algoritmi
 *
 * @author sasumaki
 */
public class Astar {

    private Kartta kartta;

//    private enum Ruutu {
//
//        LATTIA, VESI, METSÄ, SEINÄ
//    }
//    private static final int LATTIA = 1;
//    private static final int VESI = 10;
//    private static final int METSÄ = 3;
//    private static final int SEINÄ = 10000;
    public Astar(Kartta kartta) {
        this.kartta = kartta;

    }

    /**
     * Hakee nopeimman reitin kahden solmun välillä, toiminta keskeneräinen.
     */
    public Solmu haku(int alkuX, int alkuY, final int maaliX, final int maaliY) {

        PriorityQueue<Solmu> rintama = new PriorityQueue<Solmu>(10, new Comparator<Solmu>() {

            public int compare(Solmu t, Solmu t1) {
                return (t.getMatkaAlusta() + heuristinenMatka(t, maaliX, maaliY)) - (t1.getMatkaAlusta() + heuristinenMatka(t1, maaliX, maaliY));
            }

          
        });

        rintama.add(new Solmu(alkuY, alkuX, null, 0));

        while (!rintama.isEmpty()) {
            Solmu nykyinen = rintama.poll();

            if (nykyinen.getY() == kartta.getMaaliY() && nykyinen.getX() == kartta.getMaaliX()) {
                break;
            }
            for (Ruutu n : kartta.naapurit(nykyinen.getY(), nykyinen.getX())) {
                rintama.add(new Solmu(1, 1, nykyinen, nykyinen.getMatkaAlusta() + n.liikeHinta()));
            }
        }
        return null;
    }



/**
 * laskee kahden solmun välisen heuristisen matkan ns. manhattanmatkana eli
 * ensin horisontaalitasossa ja sitten vertikaalitasossa
 *
 * @param s
 * @param maaliX
 * @param maaliY
 * @return
 */
private int heuristinenMatka(Solmu s, int maaliX, int maaliY) {
        int heuristinen = 0;

        heuristinen = Math.abs((maaliY - s.getY()) + (maaliX - s.getX()));
        return heuristinen;
    }

    /**
     * tulostaa kartan ja nopeimman polun kartalla (toivottavasti)
     *
     * @param polku
     */
    public void tulostaPolku(Solmu[] polku) {
        Solmu solmu;
        boolean skip = false;
        for (int x = 0; x < kartta.getLeveys(); x++) {

            if (x == 0) {
                for (int i = 0; i <= kartta.getLeveys(); i++) {
                    System.out.print("-");
                }
                System.out.println();
            }
            System.out.print("|");

            for (int y = 0; y < kartta.getKorkeus(); y++) {
                solmu = kartta.getSolmu(x, y);
                skip = false;

                for (int i = 0; i < polku.length; i++) {
                    if (solmu == polku[i]) {
                        System.out.print("+");
                        skip = true;
                    }
                }
                if (solmu.onkoMaali && !skip) {
                    System.out.print("X");
                } else if (solmu.onkoEste && !skip) {
                    System.out.print("@");
                } else if (solmu.onkoAlku && !skip) {
                    System.out.print("O");
                } else if (!skip) {
                    System.out.print(" ");
                }
            }
            System.out.println("");

        }
    }
}
