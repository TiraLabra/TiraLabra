package Toteutus.Huffman;

import Tietorakenteet.Solmu;
import java.util.HashMap;

/**
 * Luokka jolla voidaan muodostaa (Huffman -puun muodostamisen jälkeen) String-muotoiset bittiesitykset
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
     * Metodi toimii rekursiivisesti siten että sille annetaan ensin Huffman -puun (keon) huippusolmu,
     * jonka jälkeen kelataan ko. solmusta molempiin suuntiin aina niin pitkälle kunnes tulee vastaan jokin oikea
     * merkki. Tämän lapset ovat joka tapauksessa "null", joten voidaan lopettaa siihen.
     * 
     * Huffman -puussa olevat välisolmut sisältävät avaimenaan "(char) 0)" arvon ja "bittijono" on alussa tyhjä ("").
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
     * Palauttaa String -olion, joka sisältää tiedon jokaisesta tiedostossa esiintyneestä merkistä sekä
     * niiden binääriesityksistään. Tyyppiä: "b001c10a1d011(0x7F)(0x7F)".
     * Lopussa kaksi tavua, jotka kertovat että tekstiesitys päättyy.
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
     * Lisää tekstiesitykseen loppupäätteen.
     * @param teksti
     * @return 
     */
    protected String lisaaLoppuPaate(StringBuilder teksti) {
        teksti.append((char) 127);
        teksti.append((char) 127);
        return teksti.toString();
    }
}
