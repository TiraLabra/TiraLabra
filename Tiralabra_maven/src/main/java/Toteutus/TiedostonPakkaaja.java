package Toteutus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class TiedostonPakkaaja {

    public void pakkaaTiedosto(TekstinLukija lukija, HuffmanPuu puu, BittiEsitykset esitykset, String polku) throws IOException {
        File tiedosto = luoUusiTiedosto(polku);
        kirjoitaTiedostoon(tiedosto, esitykset.getEsitykset(), puu, lukija.getTeksti());
    }
    
    protected File luoUusiTiedosto(String polku) throws IOException {
        File tiedosto = new File(polku + ".hemi");
        
        if (! tiedosto.exists()) {
            tiedosto.createNewFile();
            tiedosto.setWritable(true);
            return tiedosto;
        }

        throw new IOException("Tiedostoa vastaava pakkaus on jo olemassa. Tiedostoa ei pakata uudestaan.");
    }
    
    protected void kirjoitaTiedostoon(File tiedosto, HashMap<String, String> bittijonot, HuffmanPuu puu, String teksti) throws IOException {
        FileWriter kirjoittaja = new FileWriter(tiedosto);
        String ykkosinaJaNollina = ykkosinaJaNollina(teksti, bittijonot);
        String pakattuna = pakatuksiTekstiksi(ykkosinaJaNollina);

        int puuOsoitin = 4 + pakattuna.length();    // huffmanpuun talletussijainti, int 32-bit arvo = 4 tavua

        kirjoittaja.append(Integer.toString(puuOsoitin) + pakattuna + puu.puunTekstiEsitys());
        kirjoittaja.close();
    }
    
    protected String pakatuksiTekstiksi(String ykkosinaJaNollina) {
        String pakattuna = "";
        
        for (int i = 0; i < ykkosinaJaNollina.length() / 8; i++) {
            pakattuna += seuraavaTavuAsciiMerkkina(ykkosinaJaNollina, 8 * i);
        }
        
        return pakattuna;
    }
    
    protected char seuraavaTavuAsciiMerkkina(String ykkosinaJaNollina, int alku) {
        String tavu = ykkosinaJaNollina.substring(alku, alku + 8);
        return asciiMerkkina(tavu);
    }
    
    protected char asciiMerkkina(String bittijono) {
        int luku = 0;
        int suurin = bittijono.length();
        
        for (int i = 0; i < suurin; i++) {
            if (bittijono.charAt(i) == '1') {
                luku += Math.pow(2, suurin - i - 1);
            }
        }
            
        return (char) luku;
    }

    protected String ykkosinaJaNollina(String teksti, HashMap<String, String> bittijonot) {
        String ilmanEtuNollia = ilmanEtuNollia(teksti, bittijonot);
        return lisaaEtuNollat(ilmanEtuNollia) + ilmanEtuNollia;
    }
    
    protected String lisaaEtuNollat(String ilmanEtuNollia) {
        String ykkosinaJaNollina = "";
        
        if (ilmanEtuNollia.length() % 8 != 0) {
            for (int i = 0; i < (8 - ilmanEtuNollia.length() % 8); i++) {
                ykkosinaJaNollina+= "0";
            }
        }

        return ykkosinaJaNollina;
    }
    
    protected String ilmanEtuNollia(String teksti, HashMap<String, String> bittijonot) {
        String ilmanEtuNollia = "";
        
        for (int i = 0; i < teksti.length(); i++) {
            String merkki = teksti.charAt(i) + "";
            ilmanEtuNollia += bittijonot.get(merkki);     // 11
        }
        return ilmanEtuNollia;
    }
}
