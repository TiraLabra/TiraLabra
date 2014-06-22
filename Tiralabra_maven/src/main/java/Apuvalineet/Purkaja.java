package Apuvalineet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Luokka suorittaa tiedoston purkamisen k‰ytt‰en BinaariMuuntaja luokkaa apuna.
 * Luokat "HuffmanPurkaja" ja "LZWPurkaja" periv‰t t‰m‰n luokan.
 */

public abstract class Purkaja {
    private final BinaariMuuntaja muuntaja;
    private String paate;
    
    /**
     * Saa parametrinaan tiedoston p‰‰tteen.
     * @param paate 
     */
    
    public Purkaja(String paate) {
        this.paate = paate;
        this.muuntaja = new BinaariMuuntaja();
    }
    
    /**
     * Purkaa tiedoston luoden tekstin, joka kirjoitetaan ja kirjoittaen sen.
     * @param polku
     * @throws IOException
     */
    
    public void pura(String polku) throws IOException {
        String kirjoitettava = muodostaTeksti(polku);
        kirjoitaTeksti(luotavanTiedostonPolku(polku), kirjoitettava);
    }
    
    /**
     * Tarkistaa onko tiedoston polku validi. Jos on, hakee pakkauksen ja muodostaa sit‰ vastaavan tiedoston (ilman
     * pakkauksen loppup‰‰tett‰). T‰m‰n j‰lkeen purkaa pakkauksen.
     * @param polku
     * @return
     * @throws IOException 
     */
    
    protected String muodostaTeksti(String polku) throws IOException {
        tarkistaOnkoPolkuValidi(polku);
        File pakkaus = haePakkaus(polku);
        String teksti = lueTeksti(pakkaus);
        
        return puretunTiedostonSisalto(teksti);
    }
    
    /**
     * Kirjoittaa puretun tiedoston sis‰llˆn.
     * @param polku
     * @param teksti
     * @throws IOException
     */
    
    protected void kirjoitaTeksti(String polku, String teksti) throws IOException {
        Kirjoittaja kirjoittaja = new Kirjoittaja(polku);
        kirjoittaja.kirjoita(teksti);
    }
    
    /**
     * Tarkistaa onko pakkauksen p‰‰te oikeanlainen.
     * @param polku
     * @throws IOException 
     */
    
    protected void tarkistaOnkoPolkuValidi(String polku) throws IOException {
        if (! polku.toLowerCase().endsWith(paate)) {
            throw new IOException("Tiedosto ei ole tyyppi‰ '" + paate + "' ja sit‰ ei voida t‰ll‰ ohjelmalla purkaa.\n"
                                  + "Ohjelma suljetaan.");
        }
    }
    
    /**
     * Hakee pakkauksen ja heitt‰‰ poikkeuksen jos se ei ole olemassa.
     * @param polku
     * @return
     * @throws FileNotFoundException
     */
    
    protected File haePakkaus(String polku) throws FileNotFoundException {
        File pakkaus = new File(polku);
        if (! pakkaus.exists()) {
            throw new FileNotFoundException("Valitsemasi tiedosto ei ole olemassa.\nOhjelma suljetaan.");
        }
        
        return pakkaus;
    }
    
    /**
     * Palauttaa tekstin "bin‰‰riesityksen", joka on muotoa: 00 01 01 00 01...
     * alkuOsoite viittaa tekstin kohtaan, jossa on tieto siit‰,
     * kuinka monta etunollaa tekstiin on lis‰tty ja muu teksti
     * seuraa t‰t‰ osoitetta.
     * @param teksti
     * @param alkuOsoite
     * @return 
     */
    
    protected String tekstiBinaarina(String teksti, int alkuOsoite) {
        return muuntaja.puraBinaariEsitykseksi(teksti, alkuOsoite);
    }
    
    /**
     * Palauttaa pakkauksen sis‰llˆn.
     * @param pakkaus
     * @return
     * @throws FileNotFoundException 
     */
    
    protected String lueTeksti(File pakkaus) throws IOException {
        Lukija lukija = new Lukija();
        lukija.lue(pakkaus.getPath());
        
        return lukija.getTeksti();
    }
    
    /**
     * Muodostaa polun puretulle tiedostolle poistamalla p‰‰tteen.
     * @param polku
     * @return 
     */
    
    protected String luotavanTiedostonPolku(String polku) {
        return polku.substring(0, polku.length() - paate.length());
    }
    
    /**
     * Metodi jonka periv‰t luokat toteuttavat ja se palauttaa
     * ascii -esityksen, joka kirjoitetaan "purettuun" tiedostoon.
     * @param teksti
     * @return 
     */
    
    protected abstract String puretunTiedostonSisalto(String teksti);
}