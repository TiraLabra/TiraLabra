package Apuvalineet;

import java.util.HashMap;

/**
 * Yleisluokka joka suorittaa muunnoksia int -arvojen ja niitä vastaavien binääriesitysten (talletetaan
 * String -formaatissa) välillä. Tällä hetkellä käytössä ainoastaan tiedoston pakkaamisen aikana. (TiedostonPakkaaja käyttää)
 */

public class BinaariMuuntaja {
    private int lisatytEtuNollat;
    
    public BinaariMuuntaja() {
        this.lisatytEtuNollat = 0;
    }
    
    public int getLisatytEtuNollat() {
        return this.lisatytEtuNollat;
    }
    
    public String binaariEsitysEtuNollilla8Bit(int arvo) {
        StringBuilder esitys = new StringBuilder();
        
        for (int i = 7; i >= 0; i--) {
            if (arvo >= Math.pow(2, i)) {
                arvo -= Math.pow(2, i);
                esitys.append("1");
            }
            else {
                esitys.append("0");
            }
        }
        return esitys.toString();
    }
    
    /**
     * Muodostaa String -formaatissa binääriesityksen ko. arvosta.
     * @param arvo
     * @param bitteja
     * @return 
     */
    
    public String binaariEsitysIlmanEtuNollia(int arvo, int bitteja) {
        if (arvo == 0) {
            return "0";
        }
        return binaariEsitysIlmanEtuNollia(new StringBuilder(), false, arvo, bitteja);
    }
    
    /**
     * Yllä olevan alametodi joka toimii siten että vähennetään aina suurin mahd. kakkosen potenssi arvosta.
     * Hankala selittää miksi se toimii mutta olen tällaisia juttuja tehnyt aiemminkin ja heksadesimaali - binääri -muunnokset
     * voi yksinkertaisesti suorittaa näin.
     
     * @param esitys
     * @param bitti1Loydetty
     * @param arvo
     * @param bitteja
     * @return 
     */
    protected String binaariEsitysIlmanEtuNollia(StringBuilder esitys, boolean bitti1Loydetty, int arvo, int bitteja) {
        for (int i = bitteja; i >= 0; i--) {
            if (arvo >= Math.pow(2, i)) {
                arvo -= Math.pow(2, i);
                esitys.append("1");
                bitti1Loydetty = true;
            }
            else if (bitti1Loydetty) {
                esitys.append("0");
            }
        }
        return esitys.toString();
    }
    
    /**
     * Muuntaa parametrina saadun merkkiesityksen ykkösiä ja nollia ascii-koodiksi.
     * @param ykkosinaJaNollina
     * @return 
     */
    
    public String pakatuksiTekstiksi(String ykkosinaJaNollina) {
        StringBuilder pakkaaja = new StringBuilder();
        
        for (int i = 0; i < ykkosinaJaNollina.length() / 8; i++) {
            pakkaaja.append(seuraavaTavuAsciiMerkkina(ykkosinaJaNollina, 8 * i));
        }
        
        return pakkaaja.toString();
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
        int luku = kokonaisLukuna(bittijono);
        
        char[] kirjain = Character.toChars(luku);
        return kirjain[0];
    }
    
    protected int kokonaisLukuna(String bittijono) {
        int luku = 0;
        int suurin = bittijono.length();
        
        for (int i = 0; i < suurin; i++) {
            if (bittijono.charAt(i) == '1') {
                luku += Math.pow(2, suurin - i - 1);
            }
        }
        return luku;
    }

    /**
     * Palauttaa alkuperäisestä tekstistä binääriesityksen.
     * @param teksti
     * @param bittijonot
     * @return 
     */
    
    public String ykkosinaJaNollina(String teksti, HashMap<String, String> bittijonot) {
        this.lisatytEtuNollat = 0;
        String ilmanEtuNollia = ilmanEtuNollia(teksti, bittijonot);
        return lisaaEtuNollat(ilmanEtuNollia);
    }
    
    /**
     * Lisää luettuun tekstiin etunollia siten että tekstin pituus on luvun 8 kerrannainen.
     * @param ilmanEtuNollia
     * @return 
     */
    
    protected String lisaaEtuNollat(String ilmanEtuNollia) {
        StringBuilder ykkosinaJaNollina = new StringBuilder();
        
        if (ilmanEtuNollia.length() % 8 != 0) {
            for (int i = 0; i < (8 - ilmanEtuNollia.length() % 8); i++) {
                ykkosinaJaNollina.append("0");
                this.lisatytEtuNollat++;
            }
        }

        ykkosinaJaNollina.append(ilmanEtuNollia);
        return ykkosinaJaNollina.toString();
    }
    
    /**
     * Kelaa koko alkuperäisen tekstin läpi ja muodostaa sitä vastaavan "binääriesityksen" ilman etunollia.
     * @param teksti
     * @param bittijonot
     * @return 
     */
    
    protected String ilmanEtuNollia(String teksti, HashMap<String, String> bittijonot) {
        StringBuilder ilmanEtuNollia = new StringBuilder();
        
        for (int i = 0; i < teksti.length(); i++) {
            String merkki = teksti.charAt(i) + "";
            ilmanEtuNollia.append(bittijonot.get(merkki));
        }
        return ilmanEtuNollia.toString();
    }

    public String poistaEtuMerkkeja(String teksti, int maara) {
        StringBuilder ilmanEtuMerkkeja = new StringBuilder();
        for (int i = maara; i < teksti.length(); i++) {
            ilmanEtuMerkkeja.append(teksti.charAt(i));
        }
        return ilmanEtuMerkkeja.toString();
    }
}
