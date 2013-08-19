package ohjelma.algoritmit;
import java.util.HashMap;
import java.util.HashSet;
import ohjelma.verkko.Kaari;
import ohjelma.verkko.Solmu;
import ohjelma.verkko.Verkko;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kkivikat
 */
public class BellmanFord {
    private HashMap<Integer, Solmu> solmut;
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
    public boolean BellmanFord() {
        alusta();
        for (int i = 1; i < solmut.size(); i++) {   // Käydään kaikki kaaret läpi ja relaksoidaan jännitteet
            for (Kaari kaari : kaaret) {
                Relax(kaari);
            }
        }
        tulostaSolmut();                            // Huom! tulostaSolmut() vain testausta varten
        for (Kaari kaari : kaaret) {                // Tarkastetaan syklit
            if (sykliTarkastus(kaari) == false) {
                return false;
            }
        }
        return true;
    }
    
    
    /**
    * Alustaa verkon painot (aloitussolmu = 0 ja loput 99).
    **/
    
    public void alusta() {
        solmut.get(1).setPaino(0);
        for (int i = 2; i <= solmut.size(); i++) {
            solmut.get(i).setPaino(99);
        }
    }

    /**
    * Kaarien painojen päivitys/relaksointi.
    **/                                                    
    public void Relax(Kaari kaari) {                // päivittää kaarten painoja jos löytyy jännitteitä (eli toisin sanoen lyhyempi polku)
        if (kaari.getKohde().getPaino() > ((kaari.getAlku().getPaino() + kaari.getEtaisyys()))) {
            kaari.getKohde().setPaino((kaari.getAlku().getPaino() + kaari.getEtaisyys()));
        }
    }

    /**
    * Jos löytyy negatiivinen sykli, palautetaan false, muuten true.
    **/
    public boolean sykliTarkastus(Kaari kaari) {
        if (kaari.getKohde().getPaino() > (kaari.getAlku().getPaino() + kaari.getEtaisyys())) {
            return false;
        } else {
            return true;
            
        }
    }

    
    /*********************************************************/
    /*             Testaukseen käytettävät metodit           */
    /*********************************************************/
    
    private void tulostaSolmut() {
        System.out.println("Bellman-Ford tulokset: ");
        for (int x = 1; x < solmut.size() + 1; x++) {
            System.out.print(solmut.get(x).getPaino()+", ");
        }
    }
}