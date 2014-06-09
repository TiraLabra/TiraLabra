package Toteutus.Huffman.Purkaminen;

import Apuvalineet.BinaariMuuntaja;
import Apuvalineet.Kirjoittaja;
import Toteutus.PakkauksenLukija;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Luokka suorittaa tiedoston purkamisen käyttäen BinaariMuuntaja luokkaa apuna.
 */

public class TiedostonPurkaja {
    private BinaariMuuntaja muuntaja;
    
    public TiedostonPurkaja() {
        this.muuntaja = new BinaariMuuntaja();
    }
    
    /**
     * Tarkistaa onko tiedoston polku validi. Jos on, hakee pakkauksen ja muodostaa sitä vastaavan tiedoston (ilman
     * pakkauksen loppupäätettä). Tämän jälkeen purkaa pakkauksen.
     * @param polku
     * @throws IOException 
     */
    
    public void pura(String polku) throws IOException {
        tarkistaOnkoPolkuValidi(polku);
        File pakkaus = haePakkaus(polku);
        File tiedosto = muodostaTiedosto(polku);
        
        String kirjoitettava = puretunTiedostonSisalto(pakkaus, tiedosto);
        kirjoitaTeksti(tiedosto.getPath(), kirjoitettava);
    }
    
    /**
     * Kirjoittaa puretun tiedoston sisällön.
     * @param polku
     * @param teksti
     * @throws IOException
     */
    
    protected void kirjoitaTeksti(String polku, String teksti) throws IOException {
        Kirjoittaja kirjoittaja = new Kirjoittaja(polku);
        kirjoittaja.kirjoita(teksti);
    }
    
    /**
     * Tarkistaa onko pakkauksen pääte ".hemi".
     * @param polku
     * @throws IOException 
     */
    
    protected void tarkistaOnkoPolkuValidi(String polku) throws IOException {
        if (! polku.toLowerCase().endsWith(".hemi")) {
            throw new IOException("Tiedosto ei ole tyyppiä '.hemi' ja sitä ei voida tällä ohjelmalla purkaa.\nOhjelma suljetaan.");
        }
    }
    
    /**
     * Hakee pakkauksen ja heittää poikkeuksen jos se ei ole olemassa.
     * @param polku
     * @return
     * @throws IOException 
     */
    
    protected File haePakkaus(String polku) throws IOException {
        File pakkaus = new File(polku);
        if (! pakkaus.exists()) {
            throw new IOException("Valitsemasi tiedosto ei ole olemassa.\nOhjelma suljetaan.");
        }
        
        return pakkaus;
    }
    
    /**
     * Hakee pakkauksta vastaavan tiedoston (ilman .hemi päätettä). Jos tiedosto on jo olemassa,
     * heittää poikkeuksen.
     * @param polku
     * @return
     * @throws IOException 
     */
    
    protected File muodostaTiedosto(String polku) throws IOException {
        File tiedosto = new File(luotavanTiedostonPolku(polku));
        if (tiedosto.exists()) {
            throw new IOException("Purettu tiedosto on jo olemasssa. Pakattua tiedostoa ei pureta uudestaan.\nOhjelma suljetaan.");
        }
        
        return tiedosto;
    }
    
    /**
     * Purkaa pakkauksen sisällön tiedostoon hakemalla ensin pakkauksen sisällön (ascii merkkeinä), käyden tästä
     * Huffman puun läpi ja keräten siitä "bittijonot ja niitä vastaavat merkit", muodostaen String -olion, jossa
     * on sisällön binääritekstiosa ja muodostaen tästä sitten puretun tiedoston sisällön.
     * @param pakkaus
     * @param tiedosto
     * @throws IOException 
     * @return
     */
    protected String puretunTiedostonSisalto(File pakkaus, File tiedosto) throws IOException {
        String teksti = lueTeksti(pakkaus);
        HashMap<String, String> bittijonotJaMerkit = new HashMap<>();
        
        int binaariTekstinAlku = kayPuuLapi(teksti, bittijonotJaMerkit);
        String tekstiBinaarina = tekstiBinaarina(teksti, binaariTekstinAlku);
        
        return kirjoitettavaTeksti(tekstiBinaarina, bittijonotJaMerkit);
    }
    
    /**
     * Muodostaa binääritekstistä (010110010...) ja haj. taulusta, jossa on bittijonoja ja niitä vastaavia ascii -merkkejä,
     * puretun tiedoston sisällön.
     * @param tekstiBinaarina
     * @param bittijonotJaMerkit
     * @return 
     */
    
    protected String kirjoitettavaTeksti(String tekstiBinaarina, HashMap<String, String> bittijonotJaMerkit) {
        StringBuilder kirjoitettava = new StringBuilder();
        StringBuilder bittijono = new StringBuilder();
        
        for (int i = 0; i < tekstiBinaarina.length(); i++) {
            bittijono.append(tekstiBinaarina.charAt(i));
            
            if (bittijonotJaMerkit.containsKey(bittijono.toString())) {
                kirjoitettava.append(bittijonotJaMerkit.get(bittijono.toString()));
                bittijono = new StringBuilder();
            }
        }
        
        return kirjoitettava.toString();
    }
    
    /**
     * Kelaa pakatussa tiedostossa olevan Huffman -puun läpi ja kerää siitä tietona;
     * 1) Puuta seuraavan alkion osoitteen
     * 2) Binääriesitykset kutakin ascii -merkkiä varten.
     
     * @param teksti
     * @param bittijonotJaMerkit
     * @return 
     */
    
    protected int kayPuuLapi(String teksti, HashMap<String, String> bittijonotJaMerkit) {
        if (puuOnKelattuLoppuun(teksti, 0)) {
            return 2;
        }
        
        return kelaaPuuLapi(teksti, bittijonotJaMerkit);
    }
    
    private int kelaaPuuLapi(String teksti, HashMap<String, String> bittijonotJaMerkit) {
        StringBuilder bittiEsitys = new StringBuilder();
        char kirjain = teksti.charAt(0);

        int i = 1;
        while (true) {
            char seuraava = teksti.charAt(i);
            if (! lisaaMerkkiJosSeOn0Tai1(seuraava, bittiEsitys)) {
                
                if (josBittiEsitysEpaTyhjaLisataanSeHajautusTauluun(kirjain, bittiEsitys.toString(), bittijonotJaMerkit)) {
                    kirjain = seuraava;
                    bittiEsitys = new StringBuilder();
                }
                
                if (puuOnKelattuLoppuun(teksti, i)) {
                    return i + 2;
                }
            }

            i++;
        }
    }
    
    protected boolean josBittiEsitysEpaTyhjaLisataanSeHajautusTauluun(char kirjain, String bittiEsitys, HashMap<String, String> bittijonotJaMerkit) {
        if (bittiEsitys.isEmpty()) {
            return false;
        }
        
        bittijonotJaMerkit.put(bittiEsitys, kirjain + "");
        return true;
    }
    
    protected boolean lisaaMerkkiJosSeOn0Tai1(char merkki, StringBuilder bittiEsitys) {
        if (merkki == 0 || merkki == 1) {
            String lisattava = merkki + "";
            bittiEsitys.append(lisattava);
            return true;
        }
        return false;
    }
    
    protected boolean puuOnKelattuLoppuun(String teksti, int i) {
        return teksti.charAt(i) == (char) 127 && teksti.charAt(i + 1) == (char) 127;
    }
    
    /**
     * 
     * @param teksti
     * @param poistettavienEtuNollienOsoite
     * @return 
     */
    
    /**
     * Muodostaa binääriesityksen pakkauksen sisällöstä "poistettavienEtuNollienOsoite":een jälkeen.
     * @param teksti
     * @param poistettavienEtuNollienOsoite
     * @return 
     */
    
    protected String tekstiBinaarina(String teksti, int poistettavienEtuNollienOsoite) {
        StringBuilder binaarina = new StringBuilder();
        binaarina.append(tavuIlmanEtuNollia(teksti, poistettavienEtuNollienOsoite));
        binaarina.append(lisaaMuuTeksti(teksti, poistettavienEtuNollienOsoite));       
        
        return binaarina.toString();
    }
    
    /**
     * Poistaa "poistettavienEtuNollienOsoite":tta seuraavasta tavusta ko. osoitteessa olevan
     * ascii -merkin määrän verran etunollia.
     * @param teksti
     * @param poistettavienEtuNollienOsoite
     * @return 
     */
    
    protected String tavuIlmanEtuNollia(String teksti, int poistettavienEtuNollienOsoite) {
        int arvo = teksti.charAt(poistettavienEtuNollienOsoite + 1);
        int poistettavia = teksti.charAt(poistettavienEtuNollienOsoite);
        
        String binaarina = muuntaja.binaariEsitysEtuNollilla8Bit(arvo);
        return binaarina.substring(poistettavia);
    }
    
    /**
     * Palauttaa pakkauksen sisällöstä binääriesityksen os. "poistettavienEtuNollienOsoite + 2" alkaen.
     * @param teksti
     * @param poistettavienEtuNollienOsoite
     * @return 
     */
    
    protected String lisaaMuuTeksti(String teksti, int poistettavienEtuNollienOsoite) {
        StringBuilder lisaaja = new StringBuilder();
        
        for (int i = poistettavienEtuNollienOsoite + 2; i < teksti.length(); i++) {
            int arvo = teksti.charAt(i);
            lisaaja.append(muuntaja.binaariEsitysEtuNollilla8Bit(arvo));
        }
        return lisaaja.toString();
    }
         
    /**
     * Palauttaa pakkauksen sisällön.
     * @param pakkaus
     * @return
     * @throws FileNotFoundException 
     */
    protected String lueTeksti(File pakkaus) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        PakkauksenLukija lukija = new PakkauksenLukija(pakkaus.getPath());
        lukija.luePakkaus();
        
        return lukija.getTeksti();
    }

    /**
     * Muodostaa polun puretulle tiedostolle (ts. poistaa ".hemi" päätteen).
     * @param polku
     * @return 
     */
    
    protected String luotavanTiedostonPolku(String polku) {
        return polku.substring(0, polku.length() - 5);
    }
}
