package Toteutus;

import Tietorakenteet.MinKeko;
import Tietorakenteet.Solmu;
import java.util.HashMap;

/**
 * Sen jälkeen kun tiedoston teksti on luettu ja (samanaikaisesti) kaikkien siinä esiintyvien merkkien
 * määrät talletettu hajautustauluun, voidaan tätä luokkaa käyttäen muodostaa Huffman -puu.
 */

public class HuffmanPuu {
    private MinKeko keko;
    
    public MinKeko getKeko() {
        return this.keko;
    }
    
    public void muodostaHuffmanPuu(HashMap<String, Integer> esiintymat) {
        luoKeko(esiintymat);
        yhdistaKeonSolmutPuuksi();
    }
    
    /**
     * Ensin luodaan solmu jokaista merkkiä varten ja viedään ne minimikekoon (merkkien esiintymismäärien mukaan).
     * @param esiintymat 
     */
    
    protected void luoKeko(HashMap<String, Integer> esiintymat) {
        this.keko = new MinKeko(esiintymat.keySet().size());
        for (String avain : esiintymat.keySet()) {
            keko.lisaa(new Solmu(avain, esiintymat.get(avain)));
        }
    }  
    
    /**
     * Kun keko on luotu, aletaan solmuja poistaa sieltä siten että otetaan aina kaksi seuraavaa solmua (joilla siis
     * vähiten esiintymismääriä), luodaan uusi solmu ja linkitetään nämä kolme yhteen siten että uudesta solmusta tulee
     * näiden kahden vanhempi. Sitten uusi solmu lisätään kekoon. *ja tätä vaihetta toistetaan..*
     * 
     * Lopulta keon koko on enää 1 ja algoritmin suoritus päättyy (koko kasvaa luonnollisesti lisätessä ja pienenee
     * poistaessa). Tässä vaiheessa Huffman -puu on valmis ja solmujen bittiesitykset voidaan lukea käyttäen keon
     * päälimmäistä solmua apuna.
     */
    
    protected void yhdistaKeonSolmutPuuksi() {
        while (keko.getKoko() > 1) {
            Solmu vasen = keko.poistaHuippuSolmu();
            Solmu oikea = keko.poistaHuippuSolmu();
            
            Solmu yhdistetty = new Solmu(vasen.getEsiintymat() + oikea.getEsiintymat());
            linkitaSolmut(yhdistetty, vasen, oikea);

            keko.lisaa(yhdistetty);
        }
    }
    
    /**
     * Muodostaa "yhdistetystä" solmusta näiden toisten vanhemman.
     * @param yhdistetty
     * @param vasen
     * @param oikea 
     */
    
    protected void linkitaSolmut(Solmu yhdistetty, Solmu vasen, Solmu oikea) {
        yhdistetty.setVasen(vasen);
        yhdistetty.setOikea(oikea);
        vasen.setVanh(yhdistetty);
        oikea.setVanh(yhdistetty);
    }
}
