/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.astar;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Reitinhaku algoritmi
 *
 * @author sasumaki
 */
public class Astar {

    private Kartta kartta;

    public Astar(Kartta kartta) {
        this.kartta = kartta;
    }

    /**
     * Hakee nopeimman reitin kahden solmun välillä, toiminta keskeneräinen.
     */
    public Solmu[] haku(int alkuX, int alkuY, final int maaliX, final int maaliY) {

        PriorityQueue<Solmu> rintama = new PriorityQueue<Solmu>(10, new Comparator<Solmu>() {

            public int compare(Solmu t, Solmu t1) {
                return (t.getMatkaAlusta() + heuristinenMatka(t, maaliX, maaliY)) - (t1.getMatkaAlusta() + heuristinenMatka(t1, maaliX, maaliY));
            }
        });

        
        kartta.getAlkuSolmu().setMatkaAlusta(0);

        rintama.add(kartta.getAlkuSolmu());
        int matka = 0;
        Solmu[] camefrom = null;

        while (!rintama.isEmpty()) {
            Solmu nykyinen = rintama.poll();

            if (nykyinen.onkoMaali == true) {
                break;
            }
            for (Solmu n : nykyinen.solmunNaapurit(nykyinen.getX(), nykyinen.getY())) {
                int uusiMatka = matka + kartta.matkaArvo(nykyinen, n);
                boolean sisaltyy = false;

                for (int i = 0; i < camefrom.length; i++) {
                    if (n == camefrom[i]) {
                        sisaltyy = true;
                    }
                    if (!sisaltyy || matka < uusiMatka) {

                        rintama.add(n);
                        int a = 0;

                        for (int i1 = 0; i1 < camefrom.length; i1++) {
                            if (n == camefrom[i]) {
                                a = i;
                            }
                        }
                        matka = uusiMatka;
                        camefrom[a] = nykyinen; // eihä täs ooo mitää järkee. kai?

                    }
                }
            }
        }
        return camefrom;

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
