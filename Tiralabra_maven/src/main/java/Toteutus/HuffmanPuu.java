package Toteutus;

import Tietorakenteet.MinKeko;
import Tietorakenteet.Solmu;
import java.util.ArrayDeque;
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
    
        
    // Pitääkö viedä solmut jonoon ja poistaa sieltä yksi kerrallaan??
    // -> Näin saataisiin solmut tekstitiedostoon järjestykseen siten että huippusolmu ensin, lapset sitten jne.
    // --> Decompression toimisi varmaankin tällä tavoin.
    
    public String puunTekstiEsitys() {
        if (this.getKeko().getSolmut().length <= 0) {
            return null;
        }
        
        String teksti = "";
        ArrayDeque jono = new ArrayDeque(this.getKeko().getSolmut().length);
        
        Solmu solmu = this.getKeko().getSolmut()[0];
        jono.add(solmu);
        
        while (! jono.isEmpty()) {
            
            if (solmu != null) {
                if (solmu.getVasen() != null) {
                    jono.add(solmu.getVasen());
                }   
                if (solmu.getOikea() != null) {
                    jono.add(solmu.getOikea());
                }

                teksti += solmu.getAvain(); // osa avaimista "null". Ei pitäisi olla ongelma.
            }
            solmu = (Solmu) jono.pollFirst();
        }
        
        return teksti;
    }
}
