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

    public Astar(Kartta kartta) {
        this.kartta = kartta;

    }

    /**
     * Hakee nopeimman reitin kahden solmun välillä, toiminta keskeneräinen.
     */
    public Solmu haku(int alkuX, int alkuY, final int maaliX, final int maaliY) {

        PriorityQueue<Solmu> rintama = new PriorityQueue<>(100, new Comparator<Solmu>() {

            @Override
            public int compare(Solmu t, Solmu t1) {
                return (t.getMatkaAlusta() + heuristinenMatka(t, maaliX, maaliY)) - (t1.getMatkaAlusta() + heuristinenMatka(t1, maaliX, maaliY));
            }

        });

        rintama.add(new Solmu(alkuY, alkuX, null, 0));
        ArrayList<Solmu> kayty = new ArrayList<>();

        while (!rintama.isEmpty()) {

            Solmu nykyinen = rintama.poll();
            kayty.add(nykyinen);

            if (nykyinen.getY() == kartta.getMaaliY() && nykyinen.getX() == kartta.getMaaliX()) {
                break;
            }
            for (Solmu n : kartta.naapurit(nykyinen.getY(), nykyinen.getX(), nykyinen, nykyinen.getMatkaAlusta())) {
                if (!kayty.contains(n) || !(rintama.contains(n))) {
                    rintama.add(n);
                }
            }
        }
        return kayty.get(kayty.size() - 1);
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

        heuristinen = Math.abs(maaliY - s.getY()) + Math.abs(maaliX - s.getX());
        return heuristinen;
    }

    /**
     * tulostaa kartan ja nopeimman polun kartalla (toivottavasti)
     *
     * @param polku
     */
    public void tulostaPolku(Solmu polku) {
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
                        if (s.getY() != kartta.getAlkuY() && s.getX() != kartta.getAlkuX()) {
                            if (s.getY() != kartta.getMaaliY() && s.getX() != kartta.getMaaliX()) {
                                System.out.print("+");
                                printattu = true;
                            }
                        }

                    }
                }
                if (x == kartta.getAlkuX() && y == kartta.getAlkuY() && !printattu) {
                    System.out.print("O");
                    printattu = true;
                } else if (x == kartta.getMaaliX() && y == kartta.getMaaliY() && !printattu) {
                    System.out.print("X");
                    printattu = true;
                } else if (kartta.getRuutu(x, y) == Ruutu.LATTIA && !printattu) {
                    System.out.print("-");
                    printattu = true;
                }
            }
            System.out.println("");

        }
    }
}
