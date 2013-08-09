/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma;

import java.util.HashSet;

/**
 *
 * @author kkivikat
 */
public class Dijkstra {

    private iHashMap<Integer, Solmu> solmut;
    private HashSet<Kaari> kaaret;
    private Kaari lyhyin;

    public Dijkstra(Verkko verkko) {
        this.solmut = verkko.getSolmut();
        this.kaaret = verkko.getKaaret();
    }

    public void Dijkstra(Verkko G) {

        alusta(G);
//        for (int i = 1; i < solmut.koko() + 1; i++) {
        Solmu lahin = etsiLahin(solmut.get(1));
        System.out.println(lahin.toString());
//        }

    }

    
    // käy läpi kaaret joiden alku on sama kuin syötteen ja palauta se jolla pienin etäisyys
    public Solmu etsiLahin(Solmu alku) {
        Solmu x = null;
        int pienin = 99;
        for (Kaari kaari : kaaret) {
            if (kaari.getAlku() == alku && kaari.getEtaisyys() < pienin) {
                pienin = kaari.getEtaisyys();
                x = kaari.getKohde();
            }
        }
        return x;
    }

    // Apumetodeja Dijkstralle
    public void alusta(Verkko G) {
        for (int i = 2; i < solmut.koko(); i++) {
            solmut.get(i).setPaino(99);
        }
        solmut.get(1).setPaino(0);
    }

    public void Relax(Kaari kaari) {
        if (kaari.getKohde().getPaino() > ((kaari.getAlku().getPaino() + kaari.getEtaisyys()))) {
            kaari.getKohde().setPaino((kaari.getAlku().getPaino() + kaari.getEtaisyys()));
        }
    }
}
