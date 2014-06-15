package Toteutus.Huffman;

import Tietorakenteet.HajTaulu;
import Tietorakenteet.MinKeko;
import Tietorakenteet.Solmu;

/**
 * Sen j�lkeen kun tiedoston teksti on luettu ja (samanaikaisesti) kaikkien siin� esiintyvien merkkien
 * m��r�t talletettu hajautustauluun, voidaan t�t� luokkaa k�ytt�en muodostaa Huffman -puu.
 */

public class HuffmanPuu {
    private MinKeko keko;
    
    public MinKeko getKeko() {
        return this.keko;
    }
    
    public void muodostaHuffmanPuu(HajTaulu esiintymat) {
        luoKeko(esiintymat);
        yhdistaKeonSolmutPuuksi();
    }
    
    /**
     * Ensin luodaan solmu jokaista merkki� varten ja vied��n ne minimikekoon (merkkien esiintymism��rien mukaan).
     * @param esiintymat 
     */
    
    protected void luoKeko(HajTaulu esiintymat)  {
        this.keko = new MinKeko(esiintymat.getKoko());
        for (String avain : esiintymat.getAvaimet()) {
            keko.lisaa(new Solmu(avain, Integer.parseInt(esiintymat.getArvo(avain))));
        }
    }  
    
    /**
     * Kun keko on luotu, aletaan solmuja poistaa sielt� siten ett� otetaan aina kaksi seuraavaa solmua (joilla siis
     * v�hiten esiintymism��ri�), luodaan uusi solmu ja linkitet��n n�m� kolme yhteen siten ett� uudesta solmusta tulee
     * n�iden kahden vanhempi. Sitten uusi solmu lis�t��n kekoon. *ja t�t� vaihetta toistetaan..*
     * 
     * Lopulta keon koko on en�� 1 ja algoritmin suoritus p��ttyy (koko kasvaa luonnollisesti lis�tess� ja pienenee
     * poistaessa). T�ss� vaiheessa Huffman -puu on valmis ja solmujen bittiesitykset voidaan lukea k�ytt�en keon
     * p��llimm�ist� solmua apuna.
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
     * Muodostaa "yhdistetyst�" solmusta n�iden toisten vanhemman.
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