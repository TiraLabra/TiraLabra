/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.verkko;

import astar.logiikka.Astar;
import astar.tietorakenteet.Lista;

/**
 *
 * @author sasumaki
 */
public class PolkuTulostin {

    /**
     * Tulostaa kartan ja nopeimman polun kartalla
     *
     * @param polku
     * @param kartta
     */
    public void tulostaPolku(Solmu polku, Kartta kartta) {
        Lista<Solmu> ruudut = new Lista<>();
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
                        if (!printattu) {
                            if (s.getMatkaAlusta() > 10000) {
                                System.out.print("X");
                                printattu = true;
                            } else {
                                System.out.print("+");
                                printattu = true;
                            }
                        }
                    }
                }
                if (kartta.getRuutu(x, y) == Ruutu.SEINÄ && !printattu) {
                    System.out.print("@");
                    printattu = true;
                }
                if (kartta.getRuutu(x, y) == Ruutu.METSÄ && !printattu) {
                    System.out.print("^");
                    printattu = true;
                }
                if (kartta.getRuutu(x, y) == Ruutu.VESI && !printattu) {
                    System.out.print("?");
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
