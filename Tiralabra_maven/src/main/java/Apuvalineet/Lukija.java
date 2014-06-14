package Apuvalineet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 * Luokka jota k‰ytet‰‰n tiedostojen lukemiseen, mitk‰ voivat sis‰lt‰‰ mit‰ tahansa tavu -arvoja.
 */

public class Lukija {
    private Reader lukija;
    private StringBuilder teksti;
    
    public Lukija(String polku) throws FileNotFoundException, UnsupportedEncodingException {
        this.lukija = new InputStreamReader(new FileInputStream(polku), "UTF-8");
        this.teksti = new StringBuilder();
    }
    
    /**
     * Palauttaa luetun tekstin.
     * @return 
     */
    
    public String getTeksti() {
        return this.teksti.toString();
    }
    
    /**
     * Lukee tiedostoa merkki kerrallaan ja lis‰‰ merkin teksiin toistaen t‰t‰
     * niin kauan kuin merkkej‰ riitt‰‰.
     * "lukija.read()" palauttaa -1 kun tiedosto on kelattu loppuun.
     * @throws IOException 
     */
    
    public void lue() throws IOException {
        while (true) {
            int arvo = lukija.read();
            if (arvo == -1) {
                break;
            }
            
            teksti.append((char) arvo);
        }
    }
}
