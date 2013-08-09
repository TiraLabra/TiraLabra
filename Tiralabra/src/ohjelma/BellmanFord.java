package ohjelma;


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
    private iHashMap<Integer, Solmu> solmut;
    private HashSet<Kaari> kaaret;

    /**
    * Alustetaan kaarien ja solmujen joukot.
    **/
    public BellmanFord(Verkko verkko) {
        this.solmut = verkko.getSolmut();
        this.kaaret = verkko.getKaaret();
    }

    /**
    * Päämetodi.
    **/
    public boolean BellmanFord(Verkko G) {
        alusta(G);
        for (int i = 1; i < solmut.koko() - 1; i++) {
            for (Kaari kaari : kaaret) {
                Relax(kaari);
            }
        }
        solmut.tulostaArvot(); // testausta varten
        for (Kaari kaari : kaaret) {
            if (sykliTarkastus(kaari) == false) {
                return false;
            }
        }
        return true;
    }
    
    
    /**
    * Alustaa verkon painot (aloitussolmu = 0 ja loput 99).
    **/
    
    public void alusta(Verkko G) {
        for (int i = 2; i < solmut.koko(); i++) {
            solmut.get(i).setPaino(99);
        }
        solmut.get(1).setPaino(0);
    }

    /**
    * Kaarien painojen päivitys/relaksointi.
    **/
    public void Relax(Kaari kaari) {
        if (kaari.getKohde().getPaino() > ((kaari.getAlku().getPaino() + kaari.getEtaisyys()))) {
            kaari.getKohde().setPaino((kaari.getAlku().getPaino() + kaari.getEtaisyys()));
        }
    }

    /**
    * Jos löytyy negatiivinen sykli, palautetaan false, muuten true.
    **/
    public boolean sykliTarkastus(Kaari kaari) {
        if (kaari.getKohde().getPaino() > ((kaari.getAlku().getPaino() + kaari.getEtaisyys()))) {
            return false;
        } else {
            return true;
        }
    }
}