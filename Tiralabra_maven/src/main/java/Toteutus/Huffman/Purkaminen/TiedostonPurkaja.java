package Toteutus.Huffman.Purkaminen;

import Apuvalineet.BinaariMuuntaja;
import Apuvalineet.Kirjoittaja;
import Toteutus.TekstinLukija;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class TiedostonPurkaja {
    private BinaariMuuntaja muuntaja;
    
    public TiedostonPurkaja() {
        this.muuntaja = new BinaariMuuntaja();
    }
    
    public void pura(String polku) throws FileNotFoundException, IOException {
        tarkistaOnkoPolkuValidi(polku);
        File pakkaus = haePakkaus(polku);
        File tiedosto = muodostaTiedosto(polku);
        
        puraTiedosto(pakkaus, tiedosto);
    }
    
    protected void tarkistaOnkoPolkuValidi(String polku) throws IOException {
        if (! polku.toLowerCase().endsWith(".hemi")) {
            throw new IOException("Tiedosto ei ole tyyppiä '.hemi' ja sitä ei voida tällä ohjelmalla purkaa.\nOhjelma suljetaan.");
        }
    }
    
    protected File haePakkaus(String polku) throws IOException {
        File pakkaus = new File(polku);
        if (! pakkaus.exists()) {
            throw new IOException("Valitsemasi tiedosto ei ole olemassa.\nOhjelma suljetaan.");
        }
        
        return pakkaus;
    }
    
    protected File muodostaTiedosto(String polku) throws IOException {
        File tiedosto = new File(luotavanTiedostonPolku(polku));
        if (tiedosto.exists()) {
            throw new IOException("Purettu tiedosto on jo olemasssa. Pakattua tiedostoa ei pureta uudestaan.\nOhjelma suljetaan.");
        }
        
        return tiedosto;
    }
    
    protected void puraTiedosto(File pakkaus, File tiedosto) throws IOException {
        String teksti = lueTeksti(pakkaus);
        HashMap<String, String> bittijonotJaMerkit = new HashMap<>();
        
        int binaariTekstinAlku = kayPuuLapi(teksti, bittijonotJaMerkit);
        String tekstiBinaarina = tekstiBinaarina(teksti, binaariTekstinAlku);
        
        String kirjoitettava = kirjoitettavaTeksti(tekstiBinaarina, bittijonotJaMerkit);
        new Kirjoittaja(tiedosto.getPath()).kirjoita(kirjoitettava);
    }
    
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
    
    protected int kayPuuLapi(String teksti, HashMap<String, String> bittijonotJaMerkit) {
        StringBuilder bittiEsitys = new StringBuilder();
        char kirjain = teksti.charAt(0);
        
        int i = 1;
        while (true) {
            char merkki = teksti.charAt(i);
            if (merkki == '0' || merkki == '1') {
                bittiEsitys.append(merkki);
            }
            
            else {
                if (! bittiEsitys.toString().isEmpty()) {
                    bittijonotJaMerkit.put(bittiEsitys.toString(), kirjain + "");
                    kirjain = merkki;
                    bittiEsitys = new StringBuilder();
                }
                
                if (merkki == (char) 127) {
                    if (i == 1 || teksti.charAt(i+1) == (char) 127) {
                        break;
                    }
                }
            }

            i++;
        }
        
        if (i == 1) {
            return 2;
        }
        return i + 2;
    }
    
    protected String tekstiBinaarina(String teksti, int poistettavienEtuNollienOsoite) {
        StringBuilder binaarina = new StringBuilder();
        binaarina.append(tavuIlmanEtuNollia(teksti, poistettavienEtuNollienOsoite));
        binaarina.append(lisaaMuuTeksti(teksti, poistettavienEtuNollienOsoite));       
        
        return binaarina.toString();
    }
    
    protected String tavuIlmanEtuNollia(String teksti, int poistettavienEtuNollienOsoite) {
        int arvo = teksti.codePointAt(poistettavienEtuNollienOsoite + 1);                               // EI TOIMI
        
        String binaarina = muuntaja.binaariEsitysEtuNollilla8Bit(arvo);
        return muuntaja.poistaEtuMerkkeja(binaarina, teksti.charAt(poistettavienEtuNollienOsoite));
    }
    
    protected String lisaaMuuTeksti(String teksti, int poistettavienEtuNollienOsoite) {
        StringBuilder lisaaja = new StringBuilder();
        
        for (int i = poistettavienEtuNollienOsoite + 2; i < teksti.length(); i++) {
            String lisattava = muuntaja.binaariEsitysEtuNollilla8Bit(teksti.codePointAt(i));            // EI TOIMI
            lisaaja.append(lisattava);
        }
        return lisaaja.toString();
    }
    
            
    protected String lueTeksti(File pakkaus) throws FileNotFoundException {
        TekstinLukija lukija = new TekstinLukija(false);
        lukija.lueTiedosto(pakkaus.getPath());
        
        return lukija.getTeksti();
    }

    protected String luotavanTiedostonPolku(String polku) {
        StringBuilder puretunPolku = new StringBuilder();
        for (int i = 0; i < polku.length() - 5; i++) {
            puretunPolku.append(polku.charAt(i));
        }
        return puretunPolku.toString();
    }
}
