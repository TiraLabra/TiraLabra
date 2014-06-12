package Toteutus.Huffman.Pakkaaminen;

import Apuvalineet.BinaariMuuntaja;
import Tietorakenteet.HajautusTaulu;
import Toteutus.Huffman.BittiEsitykset;
import java.util.HashMap;

/**
 * Luokka suorittaa pakattavan tekstin luomisen.
 * Se k‰ytt‰‰ apuna luokkaa "BinaariMuuntaja", joka tekee muunnoksia int -ja String arvojen v‰lill‰.
 */

public class TiedostonPakkaaja {
    private BinaariMuuntaja muuntaja;
    
    public TiedostonPakkaaja() {
        this.muuntaja = new BinaariMuuntaja();
    }
    
    /**
     * Muodostaa tekstin, joka pakattavaan tiedostoon kirjoitetaan (ts. koko pakkauksen sis‰llˆn).
     * Ensin muodostetaan tekstist‰ pakattava "0/1" -versio, jonka j‰lkeen luodaan StringBuilder- olio, johon
     * lis‰t‰‰n kaikki tarpeellinen yksi kerrallaan.
     * @param esitykset
     * @param puu
     * @param teksti
     * @return 
     */
    
    protected String muodostaKirjoitettavaTeksti(String teksti, BittiEsitykset esitykset) throws Exception {
        String pakattuna = tekstiPakattuna(esitykset.getEsitykset(), teksti);
        StringBuilder kirjoitettava = new StringBuilder();
        lisaaTeksti(kirjoitettava, pakattuna, esitykset);

        return kirjoitettava.toString();
    }
    
    /**
     * Muodostaa tekstist‰ pakattavan 0/1 -version.
     * @param bittijonot
     * @param teksti
     * @return 
     */
    
    protected String tekstiPakattuna(HajautusTaulu bittijonot, String teksti) throws Exception {
        String ykkosinaJaNollina = muuntaja.ykkosinaJaNollina(teksti, bittijonot);
        return muuntaja.pakatuksiTekstiksi(ykkosinaJaNollina);
    }
    
    /**
     * Lis‰‰ tyhj‰lle StringBuilder -oliolle koko pakattavan tiedoston sis‰llˆn.
     * Ensin tulee Huffman -puun tekstiesitys. Sitten 1 tavu joka kertoo, kuinka monta
     * etunollaa tekstin bin‰‰riesityksen eteen lis‰ttiin ja lopuksi tekstin pakattu bin‰‰riesitys.
     * @param kirjoitettava
     * @param pakattuna
     * @param esitykset
     */
    
    protected void lisaaTeksti(StringBuilder kirjoitettava, String pakattuna, BittiEsitykset esitykset) throws Exception {
        kirjoitettava.append(esitykset.huffmanPuunTekstiEsitys());
        kirjoitettava.append((char) muuntaja.getLisatytEtuNollat());
        kirjoitettava.append(pakattuna);
    }
}