package Toteutus.Huffman;

import Tietorakenteet.Solmu;
import java.util.HashMap;

/**
 * Luokka jolla voidaan muodostaa (Huffman -puun muodostamisen j‰lkeen) String-muotoiset bittiesitykset
 * kaikille tiedostossa esiintyneille merkeille.
 */

public class BittiEsitykset {
    private HashMap<String, String> esitykset;
    
    public BittiEsitykset() {
        this.esitykset = new HashMap<>();
    }
    
    public BittiEsitykset(HashMap<String, String> esitykset) {
        this.esitykset = esitykset;
    }
    
    public HashMap<String, String> getEsitykset() {
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
            esitykset.put(huippu.getAvain(), bittijono);
            return;
        }
        
        muodostaMerkeilleBittiEsitykset(huippu.getVasen(), bittijono + "0");
        muodostaMerkeilleBittiEsitykset(huippu.getOikea(), bittijono + "1");
    }
    
    /**
     * Palauttaa String -olion, joka sis‰lt‰‰ tiedon jokaisesta tiedostossa esiintyneest‰ merkist‰ sek‰
     * niiden bin‰‰riesityksist‰‰n. Tyyppi‰: "b001c10a1d011(0x7F)(0x7F)".
     * Lopussa kaksi tavua, jotka kertovat ett‰ tekstiesitys p‰‰ttyy.
     * @return 
     */
    
    public String huffmanPuunTekstiEsitys() {
        StringBuilder teksti = new StringBuilder();
        
        for (String merkki : esitykset.keySet()) {
            teksti.append(merkki);
            teksti.append(esitykset.get(merkki));
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
