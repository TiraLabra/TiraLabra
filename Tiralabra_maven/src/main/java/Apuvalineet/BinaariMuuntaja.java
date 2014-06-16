package Apuvalineet;

import Tietorakenteet.HajTaulu;

/**
 * Yleisluokka joka suorittaa muunnoksia int -arvojen ja niitä vastaavien binääriesitysten (talletetaan
 * String -formaatissa) välillä.
*/

public class BinaariMuuntaja {
    private int lisatytEtuNollat;
    private final char n = (char) 0;
    private final char y = (char) 1;
    
    public BinaariMuuntaja() {
        this.lisatytEtuNollat = 0;
    }
    
    public int getLisatytEtuNollat() {
        return this.lisatytEtuNollat;
    }
    
    public String binaariEsitysEtuNollilla(int arvo) {
        StringBuilder esitys = new StringBuilder();
        boolean ykkosBittiLoydetty = false;
        
        for (int i = 30; i >= 0; i--) {
            if (arvo >= Math.pow(2, i)) {
                arvo -= Math.pow(2, i);
                esitys.append(y);
                ykkosBittiLoydetty = true;
            }
            else if (ykkosBittiLoydetty) {
                esitys.append(n);
            }
        }
        return esitys.toString();
    }
    
    /**
     * Palauttaa String -olion joka vastaa parametrina annetun arvon binääriesitystä (8bit -luku).
     * @param arvo
     * @return 
     */
    
    public String binaariEsitysEtuNollilla8Bit(int arvo) {
        StringBuilder esitys = new StringBuilder();
        
        for (int i = 7; i >= 0; i--) {
            if (arvo >= Math.pow(2, i)) {
                arvo -= Math.pow(2, i);
                esitys.append(y);
            }
            else {
                esitys.append(n);
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
     * Saa parametrina 8 merkin mittaisen jonon (01) ykkösiä ja (00) nollia, jotka ajatellaan biteiksi.
     * Näistä lasketaan kokonaisluku (0-255) ja käännetään ascii -merkiksi.
     * @param bittijono
     * @return - "bittijonon" ascii-koodi
     */
    
    protected char asciiMerkkina(String bittijono) {
        int luku = kokonaisLukuna(bittijono);
        
        char[] kirjain = Character.toChars(luku);
        return kirjain[0];
    }
    
    /**
     * Palauttaa bittijonoa (bin. esitys) vastaavan kokonaisluvun.
     * @param bittijono
     * @return 
     */
    
    protected int kokonaisLukuna(String bittijono) {
        int luku = 0;
        int suurin = bittijono.length();
        
        for (int i = 0; i < suurin; i++) {
            if (bittijono.charAt(i) == y) {
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
    
    public String ykkosinaJaNollina(String teksti, HajTaulu bittijonot) {
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
                ykkosinaJaNollina.append(n);
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
    
    protected String ilmanEtuNollia(String teksti, HajTaulu bittijonot) {
        StringBuilder ilmanEtuNollia = new StringBuilder();
        
        for (int i = 0; i < teksti.length(); i++) {
            String merkki = teksti.charAt(i) + "";
            ilmanEtuNollia.append(bittijonot.getArvo(merkki));
        }
        return ilmanEtuNollia.toString();
    }
}