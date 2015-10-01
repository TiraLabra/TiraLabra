/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.logiikka;

import astar.tietorakenteet.PrioKeko;
import astar.verkko.Kartta;
import astar.verkko.PolkuTulostin;
import astar.verkko.Solmu;
import java.util.Comparator;

/**
 * Reitinhaku algoritmi
 *
 * @author sasumaki
 */
public class Astar {

    public final Kartta kartta;
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
       // PriorityQueue<Solmu> rintama = new PriorityQueue<>(10000, new Comparator<Solmu>() {
             PrioKeko<Solmu> rintama = new PrioKeko<>(new Comparator<Solmu>() {

            @Override
            public int compare(Solmu t, Solmu t1) {
                return (t.getMatkaAlusta() + heuristinenMatka(t, maaliX, maaliY)) - (t1.getMatkaAlusta() + heuristinenMatka(t1, maaliX, maaliY));
            }
        });
        PolkuTulostin tulostin = new PolkuTulostin();
        Solmu nykyinen;
        rintama.heapInsert(new Solmu(alkuX, alkuY, null, 0));

        parasreitti = new Integer[kartta.getKorkeus()][kartta.getLeveys()];

        while (!rintama.isEmpty()) {

            nykyinen = (Solmu) rintama.pullDelete();

            if (nykyinen.getY() == maaliY && nykyinen.getX() == maaliX) {
                tulostin.tulostaPolku(nykyinen, this);
                return nykyinen;
            }
            for (Solmu n : kartta.naapurit(nykyinen.getX(), nykyinen.getY(), nykyinen, nykyinen.getMatkaAlusta())) {

                if (parasreitti[n.getY()][n.getX()] != null && parasreitti[n.getY()][n.getX()] <= n.getMatkaAlusta()) {
                    continue;
                }
                parasreitti[n.getY()][n.getX()] = n.getMatkaAlusta();
                rintama.heapInsert(n);

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

        return (int) heuristinen;
    }

}
