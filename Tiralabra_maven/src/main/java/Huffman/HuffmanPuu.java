package Huffman;

import Tietorakenteet.HajautusTaulu;
import Tietorakenteet.MinKeko;
import Tietorakenteet.Solmu;

/**
 * Sen jälkeen kun tiedoston teksti on luettu ja (samanaikaisesti) kaikkien siinä esiintyvien merkkien
 * määrät talletettu hajautustauluun, voidaan tätä luokkaa käyttäen muodostaa Huffman -puu.
 */

public class HuffmanPuu {
    private MinKeko keko;
    
    public MinKeko getKeko() {
        return this.keko;
    }
    
    public void muodostaHuffmanPuu(HajautusTaulu esiintymat) {
        luoKeko(esiintymat);
        yhdistaKeonSolmutPuuksi();
    }
    
    /**
     * Ensin luodaan solmu jokaista merkkiä varten ja viedään ne minimikekoon (merkkien esiintymismäärien mukaan).
     * @param esiintymat 
     */
    
    protected void luoKeko(HajautusTaulu esiintymat)  {
        this.keko = new MinKeko(esiintymat.getKoko());
        for (String avain : esiintymat.getAvaimet()) {
            keko.lisaa(new Solmu(avain, Integer.parseInt(esiintymat.getArvo(avain))));
        }
    }  
    
    /**
     * Kun keko on luotu, aletaan solmuja poistaa sieltä siten että otetaan aina kaksi seuraavaa solmua (joilla siis
     * vähiten esiintymismääriä), luodaan uusi solmu ja linkitetään nämä kolme yhteen siten että uudesta solmusta tulee
     * näiden kahden vanhempi. Sitten uusi solmu lisätään kekoon. *ja tätä vaihetta toistetaan..*
     * 
     * Lopulta keon koko on enää 1 ja algoritmin suoritus päättyy (koko kasvaa luonnollisesti lisätessä ja pienenee
     * poistaessa). Tässä vaiheessa Huffman -puu on valmis ja solmujen bittiesitykset voidaan lukea käyttäen keon
     * päällimmäistä solmua apuna.
     */
    
    protected void yhdistaKeonSolmutPuuksi() {
        while (keko.getKoko() > 1) {
            Solmu oikea = keko.poistaHuippuSolmu();
            Solmu vasen = keko.poistaHuippuSolmu();
            
            Solmu yhdistetty = yhdistettySolmu(vasen, oikea);
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
    
    protected Solmu yhdistettySolmu(Solmu vasen, Solmu oikea) {
        return new Solmu(vasen.getEsiintymat() + oikea.getEsiintymat());
    }
}