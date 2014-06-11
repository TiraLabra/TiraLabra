package Apuvalineet;

import Tietorakenteet.HajautusTaulu;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Luokka jota k‰ytet‰‰n tiedostojen lukemiseen.
 * Sill‰ on kaksi konstruktoria. Toista kutsumalla voidaan ker‰t‰ myˆs luetun tiedoston sis‰lt‰m‰t merkit erilliseen
 * hajautustauluun.
 */

public class Lukija {
    private Reader lukija;
    private StringBuilder teksti;
    private boolean keraaEsiintymat;
    private HajautusTaulu esiintymat;
    
    public Lukija(String polku, boolean keraaEsiintymat) throws FileNotFoundException, UnsupportedEncodingException {
        this(polku);
        this.keraaEsiintymat = keraaEsiintymat;
        this.esiintymat = new HajautusTaulu();
    }
    
    public Lukija(String polku) throws FileNotFoundException, UnsupportedEncodingException {
        this.lukija = new InputStreamReader(new FileInputStream(polku), "UTF-8");
        this.teksti = new StringBuilder();
        this.keraaEsiintymat = false;
    }
    
    public HajautusTaulu getEsiintymat() {
        return this.esiintymat;
    }
    
    /**
     * Palauttaa luetun tekstin.
     * @return 
     */
    
    public String getTeksti() {
        return this.teksti.toString();
    }
    
    /**
     * Lukee tiedostoa merkki kerrallaan niin kauan kuin merkkej‰ riitt‰‰.
     * "lukija.read()" palauttaa -1 kun tiedosto on kelattu loppuun.
     * @throws IOException 
     */
    
    public void lue() throws IOException, Exception {
        while (true) {
            int arvo = lukija.read();
            if (arvo == -1) {
                break;
            }
            
            lisaaMerkki((char) arvo);
        }
    }
    
    /**
     * Lis‰‰ merkin "tekstin" p‰‰h‰n ja kasvattaa ko. merkin esiintymism‰‰ri‰ haj.taulussa.
     * @param merkki 
     */    
    
    protected void lisaaMerkki(char merkki) throws Exception {
        teksti.append(merkki);
        if (keraaEsiintymat) {
            lisaaEsiintyma(merkki + "");
        }
    }
    
    protected void lisaaEsiintyma(String merkki) throws Exception {
        int maara = 1;
            
        if (esiintymat.sisaltaaAvaimen(merkki)) {
            maara += Integer.parseInt(esiintymat.getArvo(merkki));
        }
        
        esiintymat.lisaa(merkki, Integer.toString(maara));
    }
}
