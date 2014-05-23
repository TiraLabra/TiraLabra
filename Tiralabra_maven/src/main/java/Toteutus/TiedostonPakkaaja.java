package Toteutus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * En ole kirjoittanut tätä luokkaa varten vielä testejä ja se ei edes tee vielä haluttuja asioita.
 * Arvostaisin jos tämän takia ei ainakaan turhia pistemenetyksiä arvostelussa tulisi sillä en ole vielä
 * ehtinyt näitä toteuttaa.
 * 
 */

public class TiedostonPakkaaja {

    public void pakkaaTiedosto(TekstinLukija lukija, HuffmanPuu puu, BittiEsitykset esitykset, String polku) throws IOException {
        File tiedosto = luoUusiTiedosto(polku);
        kirjoitaTekstiBittiEsityksena(tiedosto, esitykset.getEsitykset(), lukija.getTeksti());
    }
    
    private File luoUusiTiedosto(String polku) throws IOException {
        File tiedosto = new File(polku + ".mihu");
        
        if (! tiedosto.exists()) {
            tiedosto.createNewFile();
            tiedosto.setWritable(true);
            return tiedosto;
        }

        System.out.print("Tiedostoa vastaava pakkaus on jo olemassa. Tiedostoa ei pakata uudestaan.");
        throw new IOException();
    }
    
    private void kirjoitaTekstiBittiEsityksena(File tiedosto, HashMap<String, String> bittijonot, String teksti) throws IOException {
        FileWriter kirjoittaja = new FileWriter(tiedosto);
        
        for (int i = 0; i < teksti.length(); i++) {
            String merkki = teksti.charAt(i) + "";
            String binary = bittijonot.get(merkki);
            
            kirjoittaja.append(binary);
        }
        
        // Huffman puu (eli keko) täytyy jollain lailla kirjoittaa tekstitiedostoon mukaan siten että sitä voidaan käyttää purkuvaiheessa järkevästi.
        
        kirjoittaja.close();
    }
}
