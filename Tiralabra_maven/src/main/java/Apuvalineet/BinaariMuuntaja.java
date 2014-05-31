package Apuvalineet;

import java.util.HashMap;

/**
 * Yleisluokka joka suorittaa muunnoksia int -arvojen ja niitä vastaavien binääriesitysten (talletetaan
 * String -formaatissa) välillä. Tällä hetkellä käytössä ainoastaan tiedoston pakkaamisen aikana. (TiedostonPakkaaja käyttää)
 */

public class BinaariMuuntaja {
    
    /**
     * Palauttaa pakatun tiedoston alkuun asetettavan 4-tavun mittaisen osoittimen.
     * @param tekstinPituus - pakattavan tiedoston tekstin/datan pituus
     * @return 
     */
    
    public String muodostaOsoitin(int tekstinPituus) {
        int arvo = 4 + tekstinPituus;
        String osoitin = lisaaEtuNollatOsoittimeen("", Integer.numberOfLeadingZeros(arvo));
        
        return pakatuksiTekstiksi(osoitin + binaariEsitys(arvo));
    }
    
    /**
     * Lisää osoittimen alkuun etunollia "maaran" verran.
     * @param osoitin
     * @param maara
     * @return 
     */
    
    protected String lisaaEtuNollatOsoittimeen(String osoitin, int maara) {
        while (maara > 0) {
            osoitin += "0";
            maara--;
        }
        return osoitin;
    }
    
    /**
     * Muodostaa String -formaatissa binääriesityksen ko. arvosta.
     * @param arvo
     * @return 
     */
    
    public String binaariEsitys(int arvo) {
        if (arvo == 0) {
            return "0";
        }
        return binaariEsitys("", false, arvo);
    }
    
    /**
     * Yllä olevan alametodi joka toimii siten että vähennetään aina suurin mahd. kakkosen potenssi arvosta.
     * Hankala selittää miksi se toimii mutta olen tällaisia juttuja tehnyt aiemminkin ja heksadesimaali - binääri -muunnokset
     * voi yksinkertaisesti suorittaa näin.
     
     * @param esitys
     * @param bitti1Loydetty
     * @param arvo
     * @return 
     */
    protected String binaariEsitys(String esitys, boolean bitti1Loydetty, int arvo) {
        for (int i = 30; i >= 0; i--) {
            if (arvo >= Math.pow(2, i)) {
                arvo -= Math.pow(2, i);
                esitys += "1";
                bitti1Loydetty = true;
            }
            else if (bitti1Loydetty) {
                esitys += "0";
            }
        }
        return esitys;
    }
    
    /**
     * Muuntaa parametrina saadun merkkiesityksen ykkösiä ja nollia ascii-koodiksi.
     * @param ykkosinaJaNollina
     * @return 
     */
    
    public String pakatuksiTekstiksi(String ykkosinaJaNollina) {
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
    
    /**
     * Saa parametrina 8 merkin mittaisen jonon ykkösiä ja nollia, jotka ajatellaan biteiksi.
     * Näistä lasketaan kokonaisluku (0-255) ja käännetään asii merkiksi.
     * @param bittijono
     * @return - "bittijonon" ascii-koodi
     */
    
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

    /**
     * Palauttaa alkuperäisestä tekstistä binääriesityksen.
     * @param teksti
     * @param bittijonot
     * @return 
     */
    
    public String ykkosinaJaNollina(String teksti, HashMap<String, String> bittijonot) {
        String ilmanEtuNollia = ilmanEtuNollia(teksti, bittijonot);
        return lisaaEtuNollat(ilmanEtuNollia);
    }
    
    /**
     * Lisää luettuun tekstiin etunollia siten että tekstin pituus on luvun 8 kerrannainen.
     * @param ilmanEtuNollia
     * @return 
     */
    
    protected String lisaaEtuNollat(String ilmanEtuNollia) {
        String ykkosinaJaNollina = "";
        
        if (ilmanEtuNollia.length() % 8 != 0) {
            for (int i = 0; i < (8 - ilmanEtuNollia.length() % 8); i++) {
                ykkosinaJaNollina+= "0";
            }
        }

        return ykkosinaJaNollina + ilmanEtuNollia;
    }
    
    /**
     * Kelaa koko alkuperäisen tekstin läpi ja muodostaa sitä vastaavan "binääriesityksen" ilman etunollia.
     * @param teksti
     * @param bittijonot
     * @return 
     */
    
    protected String ilmanEtuNollia(String teksti, HashMap<String, String> bittijonot) {
        String ilmanEtuNollia = "";
        
        for (int i = 0; i < teksti.length(); i++) {
            String merkki = teksti.charAt(i) + "";
            ilmanEtuNollia += bittijonot.get(merkki);
        }
        return ilmanEtuNollia;
    }
}
