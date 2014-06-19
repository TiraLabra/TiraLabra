package LZW;

import Apuvalineet.BinaariMuuntaja;
import Apuvalineet.Kirjoittaja;
import java.io.IOException;

/**
 * Suorittaa tiedoston pakkaamisen.
 * Tämä tapahtuu käytännössä pyytämällä LZWLukijaa lukemaan tiedosto ja
 * kääntämällä se ascii -koodiksi.
 * 
 * Tämän jälkeen pyydetään Kirjoittajaa kirjoittamaan ko. ascii- koodi.
*/

public class LZWPakkaaja {
    private BinaariMuuntaja muuntaja;
    private LZWLukija lukija;
    
    public LZWPakkaaja() {
        this.muuntaja = new BinaariMuuntaja();
        this.lukija = new LZWLukija();  
    }
    
    /**
     * Lukee tiedoston ja muuttaa sisällön ascii -koodiksi ja kirjoittaa tämän.
     * @param polku
     * @throws IOException 
     */
    
    public void suoritaPakkaaminen(String polku) throws IOException {
        lukija.lue(polku);

        Kirjoittaja kirjoittaja = new Kirjoittaja(polku + ".lzw");
        kirjoittaja.kirjoita(pakattavaTeksti());
    }
    
    /**
     * Muuntaa binäärimuotoisen tekstin ascii -koodiksi.
     * @return 
     */
    
    public String pakattavaTeksti() {
        StringBuilder pakattavaTeksti = new StringBuilder();
        String pakattuna = pakattuna(lukija.getTeksti());
        
        pakattavaTeksti.append((char) muuntaja.getLisatytEtuNollat());
        pakattavaTeksti.append(pakattuna);
        
        return pakattavaTeksti.toString();
    }
    
    /**
     * Lisää etunollia kunnes koko on 8:lla jaollinen ja muuntaa tämän
     * jälkeen asciiksi.
     * @param ilmanEtuNollia
     * @return 
     */
    
    protected String pakattuna(String ilmanEtuNollia) {
        this.muuntaja = new BinaariMuuntaja();
        String ykkosinaJaNollina = muuntaja.lisaaEtuNollat(ilmanEtuNollia);
        
        return muuntaja.asciiKoodiksi(ykkosinaJaNollina);
    }
}
