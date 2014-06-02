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

        StringBuilder ykkosinaJaNollina = new StringBuilder();
        ykkosinaJaNollina.append(kuudesTavuIlmanEtuNollia(teksti));
        ykkosinaJaNollina.append(lisaaMuuTeksti(teksti, puunOsoite));
        
        FileWriter kirjoittaja = new FileWriter(tiedosto);
        
        int osoite = puunOsoite;
        
        for (int i = 0; i < ykkosinaJaNollina.toString().length(); i++) {
            if (teksti.charAt(osoite) != (char) 0) {
                kirjoittaja.append(teksti.charAt(osoite));
                osoite = puunOsoite;
                continue;
            }
            
            osoite += osoite - puunOsoite + 1;
            if (ykkosinaJaNollina.toString().charAt(i) == '1') {
                osoite += 1;
            }
        }
        
        kirjoittaja.close();
    }
    
    protected String kuudesTavuIlmanEtuNollia(String teksti) {
        String binaarina = muuntaja.binaariEsitys(teksti.charAt(5));
        return muuntaja.poistaEtuNollat(binaarina, teksti.charAt(4));
    }
    
    protected String lisaaMuuTeksti(String teksti, int puunOsoite) {
        StringBuilder lisaaja = new StringBuilder();
        
        for (int i = 6; i < puunOsoite; i++) {
            lisaaja.append(muuntaja.binaariEsitys(teksti.charAt(i)));
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
    
//    /**
//     * Palauttaa osoitteen (ajateltuna yhtä siirtymää 0/1 :nä), josta tekstin binääriesitys alkaa.
//     * 40 lisätään koska edessä ensin 5 tavua (5*8) ja näistä viimeinen tavu kertoo, kuinka monta etunollaa
//     * tekstin alkuun on lisätty.
//     * @param teksti
//     * @return 
//     */
//    protected int alkuOsoite(String teksti) {
//        return 40 + teksti.charAt(4);
//    }
}
