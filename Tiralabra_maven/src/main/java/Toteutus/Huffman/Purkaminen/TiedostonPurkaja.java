package Toteutus.Huffman.Purkaminen;

import Apuvalineet.BinaariMuuntaja;
import Toteutus.TekstinLukija;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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
    
    protected void puraTiedosto(File pakkaus, File tiedosto) throws FileNotFoundException, IOException {
        String teksti = lueTeksti(pakkaus);
        int puunOsoite = puunOsoite(teksti);
        String tekstiBinaarina = tekstiBinaarina(teksti, puunOsoite);
        kirjoitaTiedostoon(teksti, puunOsoite, tekstiBinaarina, new FileWriter(tiedosto));
    }
    
    protected void kirjoitaTiedostoon(String teksti, int puunOsoite, String tekstiBinaarina, FileWriter kirjoittaja) throws IOException {
        int osoite = puunOsoite;
        int pituus = tekstiBinaarina.length();
        
        for (int i = 0; i < pituus; i++) {
            char merkki = teksti.charAt(osoite);

            if (merkki == (char) 0) {
                osoite = seuraavaOsoite(osoite, puunOsoite, tekstiBinaarina, i);
                continue;
            }
            
            kirjoittaja.append(merkki);
            osoite = puunOsoite;
        }
        
        kirjoittaja.close();
    }
    
    protected int seuraavaOsoite(int osoite, int puunOsoite, String tekstiBinaarina, int i) {
        osoite += osoite - puunOsoite + 1;
        if (tekstiBinaarina.charAt(i) == '1') {
            osoite += 1;
        }
        
        return osoite;
    }
    
    protected String tekstiBinaarina(String teksti, int puunOsoite)  {
        StringBuilder binaarina = new StringBuilder();
        binaarina.append(kuudesTavuIlmanEtuNollia(teksti));
        binaarina.append(lisaaMuuTeksti(teksti, puunOsoite));       
        
        return binaarina.toString();
    }
    
    protected String kuudesTavuIlmanEtuNollia(String teksti) {
        String binaarina = muuntaja.binaariEsitysEtuNollilla8Bit(teksti.charAt(5));
        return muuntaja.poistaEtuMerkkeja(binaarina, teksti.charAt(4));
    }
    
    protected String lisaaMuuTeksti(String teksti, int puunOsoite) {
        StringBuilder lisaaja = new StringBuilder();
        
        for (int i = 6; i < puunOsoite; i++) {
            String lisattava = muuntaja.binaariEsitysEtuNollilla8Bit(teksti.charAt(i));
            lisaaja.append(lisattava);
        }
        return lisaaja.toString();
    }
    
            
    protected String lueTeksti(File pakkaus) throws FileNotFoundException {
        TekstinLukija lukija = new TekstinLukija(false);
        lukija.lueTiedosto(pakkaus.getPath());
        
        return lukija.getTeksti();
    }
    
    protected int puunOsoite(String teksti) {
        BinaariMuuntaja muuntaja = new BinaariMuuntaja();
        return muuntaja.osoitinKokonaisLukuna(teksti.substring(0, 4));
    }
    
    protected String luotavanTiedostonPolku(String polku) {
        StringBuilder puretunPolku = new StringBuilder();
        for (int i = 0; i < polku.length() - 5; i++) {
            puretunPolku.append(polku.charAt(i));
        }
        return puretunPolku.toString();
    }
}
