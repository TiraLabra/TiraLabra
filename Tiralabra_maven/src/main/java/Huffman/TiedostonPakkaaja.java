package Huffman;

import Apuvalineet.BinaariMuuntaja;
import Tietorakenteet.HajTaulu;

/**
 * Luokka suorittaa pakattavan tekstin luomisen.
 * Se k�ytt�� apuna luokkaa "BinaariMuuntaja", joka tekee muunnoksia int -ja String arvojen v�lill�.
 */

public class TiedostonPakkaaja {
    private BinaariMuuntaja muuntaja;
    
    public TiedostonPakkaaja() {
        this.muuntaja = new BinaariMuuntaja();
    }
    
    /**
     * Muodostaa tekstin, joka pakattavaan tiedostoon kirjoitetaan (ts. koko pakkauksen sis�ll�n).
     * Ensin muodostetaan tekstist� pakattava "0/1" -versio, jonka j�lkeen luodaan StringBuilder- olio, johon
     * lis�t��n kaikki tarpeellinen yksi kerrallaan.
     * @param esitykset
     * @param teksti
     * @return 
     */
    
    protected String muodostaKirjoitettavaTeksti(String teksti, BittiEsitykset esitykset) {
        String pakattuna = tekstiPakattuna(esitykset.getEsitykset(), teksti);
        StringBuilder kirjoitettava = new StringBuilder();
        lisaaTeksti(kirjoitettava, pakattuna, esitykset);

        return kirjoitettava.toString();
    }
    
    /**
     * Muodostaa tekstist� pakattavan 0/1 -version.
     * @param bittijonot
     * @param teksti
     * @return 
     */
    
    protected String tekstiPakattuna(HajTaulu bittijonot, String teksti) {
        String ykkosinaJaNollina = muuntaja.ykkosinaJaNollina(teksti, bittijonot);
        return muuntaja.pakatuksiTekstiksi(ykkosinaJaNollina);
    }
    
    /**
     * Lis�� tyhj�lle StringBuilder -oliolle koko pakattavan tiedoston sis�ll�n.
     * Ensin tulee Huffman -puun tekstiesitys. Sitten 1 tavu joka kertoo, kuinka monta
     * etunollaa tekstin bin��riesityksen eteen lis�ttiin ja lopuksi tekstin pakattu bin��riesitys.
     * @param kirjoitettava
     * @param pakattuna
     * @param esitykset
     */
    
    protected void lisaaTeksti(StringBuilder kirjoitettava, String pakattuna, BittiEsitykset esitykset) {
        kirjoitettava.append(esitykset.huffmanPuunTekstiEsitys());
        kirjoitettava.append((char) muuntaja.getLisatytEtuNollat());
        kirjoitettava.append(pakattuna);
    }
}