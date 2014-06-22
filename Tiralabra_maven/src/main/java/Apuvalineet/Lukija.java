package Apuvalineet;

import Tietorakenteet.HajautusTaulu;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Luokka jota k‰ytet‰‰n tiedostojen lukemiseen, mitk‰ voivat sis‰lt‰‰ mit‰ tahansa tavu -arvoja.
 */

public class Lukija {
    private DataInputStream lukija;
    private StringBuilder teksti;
    private boolean keraaEsiintymat = false;
    private HajautusTaulu esiintymat;
    
    public Lukija() {
        this.teksti = new StringBuilder();
    }
    
    /**
     * Luokalla on kaksi konstruktoria. Kutsumalla t‰t‰ arvolla "true" voidaan
     * ker‰t‰ jokaisen luetun merkin esiintymien m‰‰r‰ talteen.
     * (K‰ytet‰‰n Huffman -puun muodostamista varten.)
     * @param keraaEsiintymat 
     */
    
    public Lukija(boolean keraaEsiintymat) {
        this();
        this.keraaEsiintymat = keraaEsiintymat;
        this.esiintymat = new HajautusTaulu();
    }
    
    /**
     * Palauttaa luetun tekstin.
     * @return 
     */
    
    public String getTeksti() {
        return this.teksti.toString();
    }
    
    public HajautusTaulu getEsiintymat() {
        return this.esiintymat;
    }
    
    /**
     * Lukee tiedostoa merkki kerrallaan ja lis‰‰ merkin teksiin toistaen t‰t‰
     * niin kauan kuin merkkej‰ riitt‰‰.
     * "lukija.read()" palauttaa -1 kun tiedosto on kelattu loppuun.
     * @param polku
     * @throws IOException 
     */
    
    public void lue(String polku) throws IOException {
        this.lukija = new DataInputStream(new FileInputStream(polku));
        
        while (true) {
            int arvo = lukija.read();

            if (arvo == -1) {
                break;
            }
            lisaaMerkki((char) arvo); 
        }
        lukija.close();
    }
    
    protected void lisaaMerkki(char merkki) {
        teksti.append(merkki);
        
        if (keraaEsiintymat) {
            lisaaEsiintyma(merkki + "");
        }
    }
    
    /**
     * Kasvattaa merkin esiintymism‰‰r‰‰ yhdell‰ jos se on lis‰tty haj. tauluun.
     * Jos ei ole, lis‰‰ haj. tauluun arvolla 1.
     * @param merkki 
     */
    
    protected void lisaaEsiintyma(String merkki) {
        int maara = 1;
            
        if (esiintymat.sisaltaaAvaimen(merkki)) {
            maara += Integer.parseInt(esiintymat.getArvo(merkki));
        }
        
        esiintymat.lisaa(merkki, Integer.toString(maara));
    }
}
