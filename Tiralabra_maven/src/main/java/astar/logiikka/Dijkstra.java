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
 *
 * @author sasumaki
 */
public class Dijkstra {

    public final Kartta kartta;
    private Integer[][] parasreitti;

    public Dijkstra(Kartta kartta) {
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
        PrioKeko<Solmu> rintama = new PrioKeko<>();
        PolkuTulostin tulostin = new PolkuTulostin();
        Solmu nykyinen;
        rintama.heapInsert(new Solmu(alkuX, alkuY, null, 0));

        parasreitti = new Integer[kartta.getKorkeus()][kartta.getLeveys()];

        while (!rintama.isEmpty()) {

            nykyinen = (Solmu) rintama.pullDelete();

            if (nykyinen.getY() == maaliY && nykyinen.getX() == maaliX) {
                tulostin.tulostaPolku(nykyinen, kartta);
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
}
