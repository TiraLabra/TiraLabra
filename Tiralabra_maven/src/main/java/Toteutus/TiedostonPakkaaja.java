package Toteutus;

import Apuvalineet.BinaariMuuntaja;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Luokka suorittaa uuden tiedoston (pakkauksen) luomisen.
 * Se käyttää apuna luokkaa "BinaariMuuntaja", joka tekee muunnoksia int -ja String arvojen välillä.
 */

public class TiedostonPakkaaja {
    private BinaariMuuntaja muuntaja;
    
    public TiedostonPakkaaja() {
        this.muuntaja = new BinaariMuuntaja();
    }
    
    public void pakkaaTiedosto(TekstinLukija lukija, HuffmanPuu puu, BittiEsitykset esitykset, String polku) throws IOException {
        File tiedosto = luoUusiTiedosto(polku);
        kirjoitaTiedostoon(tiedosto, esitykset.getEsitykset(), puu, lukija.getTeksti());
    }
    
    /**
     * Luo uuden tyhjän tiedoston tai heittää poikkeuksen jos samanniminen tiedosto on jo olemassa (ts. haluttu tiedosto
     * on jo pakattu).
     * @param polku - pakattavan tiedoston polku
     * @return
     * @throws IOException - pakkaus on jo olemassa 
     */
    protected File luoUusiTiedosto(String polku) throws IOException {
        File tiedosto = new File(polku + ".hemi");
        
        if (! tiedosto.exists()) {
            tiedosto.createNewFile();
            tiedosto.setWritable(true);
            return tiedosto;
        }

        throw new IOException("Tiedostoa vastaava pakkaus on jo olemassa. Tiedostoa ei pakata uudestaan.");
    }
    
    /**
     * Tämä metodi suorittaa uuden luodun tiedoston datan kirjoittamisen. Siihen lisätään ensiksi "pointer", joka kertoo
     * tiedostoon laitettavan Huffman-puun tekstiesityksen sijainnin. Pointerin jälkeen liitetään koko alkuperäinen teksti
     * pakattuna "binääriesityksenään". Sen jälkeen tulee Huffman-puun tekstiesitys ja "pointer" asetetaan viittamaan
     * tähän osoitteeseen.
     * 
     * Metodi ei tällä hetkellä toimi oikein, sillä heksaeditorilla olen erilaisia pakkauksia tarkastellut ja pienillä
     * pakkauksilla pointer osoittaa Huffman-puun tekstiesitykseen, mutta kun joillakin isoilla olen testannut, osoitin
     * osoittaa jonnekin lähistölle. Täytyypä etsiä missä virhe.
 
     * @param tiedosto
     * @param bittijonot - tekstin merkit ja niitä vastaavat binääriesitykset
     * @param puu
     * @param teksti - alkuperäisen tiedoston teksti/data
     * @throws IOException 
     */
    protected void kirjoitaTiedostoon(File tiedosto, HashMap<String, String> bittijonot, HuffmanPuu puu, String teksti) throws IOException {
        FileWriter kirjoittaja = new FileWriter(tiedosto);
        String ykkosinaJaNollina = muuntaja.ykkosinaJaNollina(teksti, bittijonot);
        String pakattuna = muuntaja.pakatuksiTekstiksi(ykkosinaJaNollina);
        
        kirjoittaja.append(muuntaja.muodostaOsoitin(pakattuna.length()) + pakattuna + puu.puunTekstiEsitys());
        kirjoittaja.close();
    }
}