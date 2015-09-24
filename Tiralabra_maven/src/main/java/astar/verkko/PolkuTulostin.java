/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.verkko;

import astar.logiikka.Astar;
import java.util.ArrayList;

/**
 *
 * @author sasumaki
 */
public class PolkuTulostin {

    /**
     * Tulostaa kartan ja nopeimman polun kartalla
     *
     * @param polku
     */
    public void tulostaPolku(Solmu polku, Astar astar) {
        ArrayList<Solmu> ruudut = new ArrayList<>();
        ruudut.add(polku);
        Solmu d = polku.getEdellinen();
        boolean printattu;
        while (d != null) {
            ruudut.add(d);
            d = d.getEdellinen();
        }
        for (int y = 0; y < astar.kartta.getKorkeus(); y++) {
            for (int x = 0; x < astar.kartta.getLeveys(); x++) {
                printattu = false;
                for (Solmu s : ruudut) {
                    if (s.getY() == y && s.getX() == x) {
                        if (!printattu) {
                            System.out.print("+");
                            printattu = true;
                        }
                    }
                }
                if (astar.kartta.getRuutu(x, y) == Ruutu.SEINÄ && !printattu) {
                    System.out.print("@");
                    printattu = true;
                }
                if (astar.kartta.getRuutu(x, y) == Ruutu.LATTIA && !printattu) {
                    System.out.print("-");
                }
            }
            System.out.println("");
        }
    }
    
}
