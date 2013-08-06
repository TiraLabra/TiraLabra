package ohjelma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kkivikat
 */
public class BellmanFord {

    private Verkko verkko;
    private HashMap<Integer, Solmu> solmut;
    private HashSet<Kaari> kaaret;

    public BellmanFord(Verkko verkko, HashMap<Integer, Solmu> solmut, HashSet<Kaari> kaaret) {
        this.verkko = verkko;
        this.solmut = solmut;
        this.kaaret = kaaret;

    }

    public void BellmanFord(Verkko G) {
        alusta(G);
        for (int i = 1; i < solmut.size() - 1; i++) {
            for (Kaari kaari : kaaret) {
                Relax(kaari);
            }
        }
        System.out.println(solmut.values().toString());
    }

    public void alusta(Verkko G) {
        for (int i = 2; i < solmut.size(); i++) {
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