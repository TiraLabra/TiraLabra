package LZW;

import Apuvalineet.BinaariMuuntaja;
import Apuvalineet.Kirjoittaja;
import java.io.IOException;

public class LZWPakkaaja {
    private BinaariMuuntaja muuntaja;
    private LZWLukija lukija;
    
    public LZWPakkaaja() {
        this.muuntaja = new BinaariMuuntaja();
        this.lukija = new LZWLukija();  
    }
    
    public void suoritaPakkaaminen(String polku) throws IOException {
        lukija.lue(polku);

        Kirjoittaja kirjoittaja = new Kirjoittaja(polku + ".lzw");
        kirjoittaja.kirjoita(pakattavaTeksti());
    }
    
    public String pakattavaTeksti() {
        StringBuilder pakattavaTeksti = new StringBuilder();
        String pakattuna = pakattuna(lukija.getTeksti());
        
        pakattavaTeksti.append((char) muuntaja.getLisatytEtuNollat());
        pakattavaTeksti.append(pakattuna);
        
        return pakattavaTeksti.toString();
    }
    
    protected String pakattuna(String ilmanEtuNollia) {
        this.muuntaja = new BinaariMuuntaja();
        String ykkosinaJaNollina = muuntaja.lisaaEtuNollat(ilmanEtuNollia);
        
        return muuntaja.pakatuksiTekstiksi(ykkosinaJaNollina);
    }
}
