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

    private final Kartta kartta;
    private Integer[][] parasreitti;

    public Astar(Kartta kartta) {
        this.kartta = kartta;

    }

    /**
     * Hakee nopeimman reitin kahden solmun välillä, toiminta keskeneräinen.
     *
     * @param alkuX
     * @param alkuY
     * @param maaliX
     * @param maaliY
     * @return
     */
    public Solmu haku(int alkuX, int alkuY, final int maaliX, final int maaliY) {
        PriorityQueue<Solmu> rintama = new PriorityQueue<>(10000, new Comparator<Solmu>() {
            // PrioKeko<Solmu> rintama = new PrioKeko<>(new Comparator<Solmu>() {

            @Override
            public int compare(Solmu t, Solmu t1) {
                return (t.getMatkaAlusta() + heuristinenMatka(t, maaliX, maaliY)) - (t1.getMatkaAlusta() + heuristinenMatka(t1, maaliX, maaliY));
            }
        });
        Solmu nykyinen;
        rintama.add(new Solmu(alkuX, alkuY, null, 0));

        parasreitti = new Integer[kartta.getKorkeus()][kartta.getLeveys()];

        while (!rintama.isEmpty()) {

            nykyinen = (Solmu) rintama.poll();

            if (nykyinen.getY() == maaliY && nykyinen.getX() == maaliX) {

                return nykyinen;
            }
            for (Solmu n : kartta.naapurit(nykyinen.getX(), nykyinen.getY(), nykyinen, nykyinen.getMatkaAlusta())) {

                if (parasreitti[n.getY()][n.getX()] != null && parasreitti[n.getY()][n.getX()] <= n.getMatkaAlusta()) {
                    continue;
                }
                parasreitti[n.getY()][n.getX()] = n.getMatkaAlusta();
                rintama.add(n);

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
        double heuristinen;
        heuristinen = Math.sqrt(Math.pow(s.getX() - maaliX, 2) + Math.pow(s.getY() - maaliY, 2));

//        System.out.println("solmu (" + s.getX() +", " + s.getY() + "): " + heuristinen);
        return (int) heuristinen;
    }

    /**
     * Tulostaa kartan ja nopeimman polun kartalla
     *
     * @param polku
     */
    public void tulostaPolku(Solmu polku) {
        //    Lista<Solmu> ruudut = new Lista<>();
        ArrayList<Solmu> ruudut = new ArrayList<>();
        ruudut.add(polku);
        Solmu d = polku.getEdellinen();
        boolean printattu;

        while (d != null) {
            ruudut.add(d);

            d = d.getEdellinen();
        }

        for (int y = 0; y < kartta.getKorkeus(); y++) {
            for (int x = 0; x < kartta.getLeveys(); x++) {
                printattu = false;
                for (Solmu s : ruudut) {
                    if (s.getY() == y && s.getX() == x) {
//                        if (kartta.getRuutu(x, y) == (kartta.getAlkuRuutu())) {
//                            System.out.print("O");
//                            printattu = true;
//                        }
//                        if (kartta.getRuutu(x, y) == (kartta.getMaaliRuutu()) && !printattu) {
//                            System.out.print("X");
//                            printattu = true;
//                        }
                        if(!printattu){
                        System.out.print("+");
                        printattu = true;
                        }
//                            }
//                        }

                    }
                }
//                if (x == kartta.getAlkuX() && y == kartta.getAlkuY() && !printattu) {
//                    System.out.print("O");
//                    printattu = true;
//                } else if (x == kartta.getMaaliX() && y == kartta.getMaaliY() && !printattu) {
//                    System.out.print("X");
//                    printattu = true;
//                } else 
                if (kartta.getRuutu(x, y) == Ruutu.SEINÄ && !printattu) {
                    System.out.print("@");
                    printattu = true;
                }
                if (kartta.getRuutu(x, y) == Ruutu.LATTIA && !printattu) {
                    System.out.print("-");
                }
            }
            System.out.println("");

        }
    }
}
