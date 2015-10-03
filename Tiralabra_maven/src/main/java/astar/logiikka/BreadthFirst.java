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

/**
 *
 * @author sasumaki
 */
public class BreadthFirst {

    private final Kartta kartta;
    private final Integer[][] kayty;

    public BreadthFirst(Kartta kartta) {
        this.kartta = kartta;
        kayty = new Integer[kartta.getKorkeus()][kartta.getLeveys()];
    }

    public Solmu haku(int alkuX, int alkuY, final int maaliX, final int maaliY) {
        PrioKeko<Solmu> rintama = new PrioKeko<>();
        PolkuTulostin tulostin = new PolkuTulostin();

        rintama.heapInsert(new Solmu(alkuX, alkuY, null, 0));

        while (!rintama.isEmpty()) {
            Solmu nykyinen = rintama.pullDelete();

            if (nykyinen.getX() == maaliX && nykyinen.getY() == maaliY) {
                tulostin.tulostaPolku(nykyinen, kartta);

                break;
            }

            for (Solmu s : kartta.naapurit(nykyinen.getX(), nykyinen.getY(), nykyinen, nykyinen.getMatkaAlusta())) {
                if (kayty[s.getY()][s.getX()] != null) {
                    rintama.heapInsert(s);
                    kayty[s.getY()][s.getX()] = s.getMatkaAlusta();
                }
            }

        }

        return null;
    }
}
