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
}
