package Apuvalineet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Luokka suorittaa tiedoston purkamisen k�ytt�en BinaariMuuntaja luokkaa apuna.
 */

public abstract class Purkaja {
    private final BinaariMuuntaja muuntaja;
    private String paate;
    
    public Purkaja(String paate) {
        this.paate = paate;
        this.muuntaja = new BinaariMuuntaja();
    }
    
    public BinaariMuuntaja getMuuntaja() {
        return this.muuntaja;
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
     * Tarkistaa onko tiedoston polku validi. Jos on, hakee pakkauksen ja muodostaa sit� vastaavan tiedoston (ilman
     * pakkauksen loppup��tett�). T�m�n j�lkeen purkaa pakkauksen.
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
     * Kirjoittaa puretun tiedoston sis�ll�n.
     * @param polku
     * @param teksti
     * @throws IOException
     */
    
    protected void kirjoitaTeksti(String polku, String teksti) throws IOException {
        Kirjoittaja kirjoittaja = new Kirjoittaja(polku);
        kirjoittaja.kirjoita(teksti);
    }
    
    /**
     * Tarkistaa onko pakkauksen p��te oikeanlainen.
     * @param polku
     * @throws IOException 
     */
    
    protected void tarkistaOnkoPolkuValidi(String polku) throws IOException {
        if (! polku.toLowerCase().endsWith(paate)) {
            throw new IOException("Tiedosto ei ole tyyppi� '" + paate + "' ja sit� ei voida t�ll� ohjelmalla purkaa.\n"
                                  + "Ohjelma suljetaan.");
        }
    }
    
    /**
     * Hakee pakkauksen ja heitt�� poikkeuksen jos se ei ole olemassa.
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
    
    protected String tekstiBinaarina(String teksti, int alkuOsoite) {
        return muuntaja.puraBinaariEsitykseksi(teksti, alkuOsoite);
    }
    
    /**
     * Palauttaa pakkauksen sis�ll�n.
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
     * Muodostaa polun puretulle tiedostolle poistamalla p��tteen.
     * @param polku
     * @return 
     */
    
    protected String luotavanTiedostonPolku(String polku) {
        return polku.substring(0, polku.length() - paate.length());
    }
    
    protected abstract String puretunTiedostonSisalto(String teksti);
}