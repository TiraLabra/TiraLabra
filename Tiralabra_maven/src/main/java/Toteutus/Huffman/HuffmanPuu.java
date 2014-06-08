package Toteutus.Huffman;

import Tietorakenteet.MinKeko;
import Tietorakenteet.Solmu;
import java.util.HashMap;

/**
 * Sen j‰lkeen kun tiedoston teksti on luettu ja (samanaikaisesti) kaikkien siin‰ esiintyvien merkkien
 * m‰‰r‰t talletettu hajautustauluun, voidaan t‰t‰ luokkaa k‰ytt‰en muodostaa Huffman -puu.
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
     * Ensin luodaan solmu jokaista merkki‰ varten ja vied‰‰n ne minimikekoon (merkkien esiintymism‰‰rien mukaan).
     * @param esiintymat 
     */
    
    protected void luoKeko(HashMap<String, Integer> esiintymat) {
        this.keko = new MinKeko(esiintymat.keySet().size());
        for (String avain : esiintymat.keySet()) {
            keko.lisaa(new Solmu(avain, esiintymat.get(avain)));
        }
    }  
    
    /**
     * Kun keko on luotu, aletaan solmuja poistaa sielt‰ siten ett‰ otetaan aina kaksi seuraavaa solmua (joilla siis
     * v‰hiten esiintymism‰‰ri‰), luodaan uusi solmu ja linkitet‰‰n n‰m‰ kolme yhteen siten ett‰ uudesta solmusta tulee
     * n‰iden kahden vanhempi. Sitten uusi solmu lis‰t‰‰n kekoon. *ja t‰t‰ vaihetta toistetaan..*
     * 
     * Lopulta keon koko on en‰‰ 1 ja algoritmin suoritus p‰‰ttyy (koko kasvaa luonnollisesti lis‰tess‰ ja pienenee
     * poistaessa). T‰ss‰ vaiheessa Huffman -puu on valmis ja solmujen bittiesitykset voidaan lukea k‰ytt‰en keon
     * p‰‰llimm‰ist‰ solmua apuna.
     */
    
    protected void yhdistaKeonSolmutPuuksi() {
        while (keko.getKoko() > 1) {
            Solmu oikea = keko.poistaHuippuSolmu();
            Solmu vasen = keko.poistaHuippuSolmu();
            
            Solmu yhdistetty = new Solmu(vasen.getEsiintymat() + oikea.getEsiintymat());
            linkitaSolmut(yhdistetty, vasen, oikea);

            keko.lisaa(yhdistetty);
        }
    }
    
    /**
     * Muodostaa "yhdistetyst‰" solmusta n‰iden toisten vanhemman.
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
//    /**
//     * Palauttaa Huffman -puusta tekstiesityksen siten ett√§ tekstiin lis√§t√§√§n aina solmun avain, jonka j√§lkeen solmun
//     * lapset laitetaan jonoon.
//     * @return - puun tekstiesitys
//     */
//    
//    
//    public String puunTekstiEsitys(HashMap<String, String> bittijonot) {
//        if (this.getKeko() == null || this.getKeko().getSolmut().length <= 0) {
//            return "";
//        }
//
//        return muodostaTekstiEsitys(bittijonot);
//    }
//    

//    
//    /**
//     * Teksti on alussa tyhj√§ ja siihen aletaan latoa solmujen avaimia j√§rjestyksess√§ laittamalla jokaisen solmun lapset
//     * aina jonoon ja poistaen jonosta sitten aina seuraava.
//     * @param teksti
//     * @param solmu - alussa huippusolmu
//     * @param jono - tyhj√§ jono
//     * @return - puun tekstiesitys
//     */
//    
//    protected String muodostaTekstiEsitys(StringBuilder teksti, Solmu solmu, ArrayDeque jono) {
//        while (solmu != null) {
//            lisaaLapsetJonoon(solmu, jono);
//            teksti.append(solmu.getAvain());
//            solmu = seuraava(jono);
//        }
//        
//        return teksti.toString();
//    }
//    
//    /**
//     * Lis√§√§ ko. solmun lapset jonoon siten ett√§ vasen ensin.
//     * @param solmu
//     * @param jono 
//     */
//    
//    protected void lisaaLapsetJonoon(Solmu solmu, ArrayDeque jono) {
//        if (solmu.getVasen() != null) {
//            jono.add(solmu.getVasen());
//        }   
//        if (solmu.getOikea() != null) {
//            jono.add(solmu.getOikea());
//        }
//    }
//   
//    /**
//     * Ottaa jononsta seuraavan lapsen tai palauttaa null jos se on tyhj√§.
//     * @param jono
//     * @return 
//     */
//    
//    protected Solmu seuraava(ArrayDeque jono) {
//        if (jono.isEmpty()) {
//            return null;
//        }
//        return (Solmu) jono.pollFirst();
//    }