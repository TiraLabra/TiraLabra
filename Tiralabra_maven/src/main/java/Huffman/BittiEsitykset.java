package Huffman;

import Tietorakenteet.HajTaulu;
import Tietorakenteet.Solmu;

/**
 * Luokka jolla voidaan muodostaa (Huffman -puun muodostamisen j‰lkeen) String-muotoiset bittiesitykset
 * kaikille tiedostossa esiintyneille merkeille.
 */

public class BittiEsitykset {
    private HajTaulu esitykset;
    
    public BittiEsitykset() {
        this.esitykset = new HajTaulu();
    }
    
    public BittiEsitykset(HajTaulu esitykset) {
        this.esitykset = esitykset;
    }
    
    public HajTaulu getEsitykset() {
        return this.esitykset;
    }

    /**
     * Metodi toimii rekursiivisesti siten ett‰ sille annetaan ensin Huffman -puun (keon) huippusolmu,
     * jonka j‰lkeen kelataan ko. solmusta molempiin suuntiin aina niin pitk‰lle kunnes tulee vastaan jokin oikea
     * merkki. T‰m‰n lapset ovat joka tapauksessa "null", joten voidaan lopettaa siihen.
     * 
     * Huffman -puussa olevat v‰lisolmut sis‰lt‰v‰t avaimenaan "(char) 0)" arvon ja "bittijono" on alussa tyhj‰ ("").
     * @param huippu
     * @param bittijono 
     */
    
    public void muodostaMerkeilleBittiEsitykset(Solmu huippu, String bittijono) {
        if (! huippu.getAvain().equals((char) 0 + "")) {
            esitykset.lisaa(huippu.getAvain(), bittijono);
            return;
        }
        
        muodostaMerkeilleBittiEsitykset(huippu.getVasen(), bittijono + (char) 0 + "");
        muodostaMerkeilleBittiEsitykset(huippu.getOikea(), bittijono + (char) 1 + "");
    }
    
    /**
     * Palauttaa String -olion, joka sis‰lt‰‰ tiedon jokaisesta tiedostossa esiintyneest‰ merkist‰ sek‰
     * niiden bin‰‰riesityksist‰‰n. Tyyppi‰: "b001c10a1d011(0x7F)(0x7F)", jossa 0 = "00" ja 1 = "01".
     * Lopussa kaksi tavua, jotka kertovat ett‰ tekstiesitys p‰‰ttyy.
     * @return 
     */
    
    public String huffmanPuunTekstiEsitys() {
        StringBuilder teksti = new StringBuilder();
        
        for (String merkki : esitykset.getAvaimet()) {
            teksti.append(merkki);
            teksti.append(esitykset.getArvo(merkki));
        }
        
        return lisaaLoppuPaate(teksti);
    }
    
    /**
     * Lis‰‰ tekstiesitykseen loppup‰‰tteen.
     * @param teksti
     * @return 
     */
    protected String lisaaLoppuPaate(StringBuilder teksti) {
        teksti.append((char) 127);
        teksti.append((char) 127);
        return teksti.toString();
    }
}