package Toteutus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class TiedostonPakkaaja {

    public void pakkaaTiedosto(TekstinLukija lukija, HuffmanPuu puu, BittiEsitykset esitykset, String polku) throws IOException {
        File tiedosto = luoUusiTiedosto(polku);
        kirjoitaTiedostoon(tiedosto, esitykset.getEsitykset(), lukija.getTeksti());
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
    
    private void kirjoitaTiedostoon(File tiedosto, HashMap<String, String> bittijonot, String teksti) throws IOException {
        FileWriter kirjoittaja = new FileWriter(tiedosto);
        String binary = ykkosinaJaNollina(teksti, bittijonot);
        kirjoitaBittiEsitys(kirjoittaja, binary);
        
        // Huffman puu (eli keko) täytyy jollain lailla kirjoittaa tekstitiedostoon mukaan siten että sitä voidaan käyttää purkuvaiheessa järkevästi.
        // Purkuohjeet pakattavan tiedoston alkuun.
        
        kirjoittaja.close();
    }
    
    private void kirjoitaBittiEsitys(FileWriter kirjoittaja, String binary) throws IOException {
        for (int i = 0; i < binary.length() / 8; i++) {
            int alku = 8 * i;
            int loppu = alku + 8;
            String bittijono = binary.substring(alku, loppu);
            kirjoittaja.append(asciiMerkkina(bittijono));
        }
    }
    
    private char asciiMerkkina(String bittijono) {
        int luku = 0;
        
        for (int i = 0; i < 8; i++) {
            if (bittijono.charAt(i) == '1') {
                luku += Math.pow(2, 7 - i);
            }
        }
            
        return (char) luku;
    }

    private String ykkosinaJaNollina(String teksti, HashMap<String, String> bittijonot) {
        String ykkosinaJaNollina = "";
        String binary = "";
        
        for (int i = 0; i < teksti.length(); i++) {
            String merkki = teksti.charAt(i) + "";
            binary += bittijonot.get(merkki);     // 11
        }

        for (int i = 0; i < (8 - binary.length() % 8); i++) {
            ykkosinaJaNollina+= "0";
        }
        ykkosinaJaNollina += binary;
        return ykkosinaJaNollina;
    }
}
