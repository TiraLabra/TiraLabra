package Apuvalineet;

import Tietorakenteet.HajautusTaulu;

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
    
    /**
     * Palauttaa parametrina saadun luvun binääriesityksen.
     * @param arvo
     * @return 
     */
    public String binaariEsitys(int arvo) {
        if (arvo == 0) {
            return n + "";
        }
        return binaariEsitys(arvo, 30, false);
    }
    
    /**
     * Palauttaa String -olion joka vastaa parametrina annetun arvon binääriesitystä (8bit -luku).
     * @param arvo
     * @return 
     */
    
    public String binaariEsitys8Bit(int arvo) {
        return binaariEsitys(arvo, 7, true);
    }
    
    /**
     * Palauttaa parametrina annetulle arvolle binääriesityksen, joka on joko
     * 8- tai 31-bittinen (ainoastaan pos. int-arvoille).
     * @param arvo
     * @param alku - alussa 7 tai 30
     * @param lisaaEtunolla - alussa true tai false
     * @return - arvon binääriesitys
     */
    
    protected String binaariEsitys(int arvo, int alku, boolean lisaaEtunolla) {
        StringBuilder esitys = new StringBuilder();
        
        for (int i = alku; i >= 0; i--) {
            if (arvo >= Math.pow(2, i)) {
                arvo -= Math.pow(2, i);
                esitys.append(y);
                lisaaEtunolla = true;
            }
            else if (lisaaEtunolla) {
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
    
    public char asciiMerkkina(String bittijono) {
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
    
    public String ykkosinaJaNollina(String teksti, HajautusTaulu bittijonot) {
        this.lisatytEtuNollat = 0;
        String ilmanEtuNollia = ilmanEtuNollia(teksti, bittijonot);
        return lisaaEtuNollat(ilmanEtuNollia);
    }
    
    /**
     * Lisää luettuun tekstiin etunollia siten että tekstin pituus on luvun 8 kerrannainen.
     * @param ilmanEtuNollia
     * @return 
     */
    
    public String lisaaEtuNollat(String ilmanEtuNollia) {
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
    
    protected String ilmanEtuNollia(String teksti, HajautusTaulu bittijonot) {
        StringBuilder ilmanEtuNollia = new StringBuilder();
        
        for (int i = 0; i < teksti.length(); i++) {
            String merkki = teksti.charAt(i) + "";
            ilmanEtuNollia.append(bittijonot.getArvo(merkki));
        }
        return ilmanEtuNollia.toString();
    }
    

    /**
     * Muodostaa binääriesityksen pakkauksen sisällöstä "alkuOsoite":en jälkeen.
     * @param teksti
     * @param alkuOsoite
     * @return 
     */ 
    
    public String puraBinaariEsitykseksi(String teksti, int alkuOsoite) {
        StringBuilder binaarina = new StringBuilder();
        binaarina.append(tavuIlmanEtuNollia(teksti, alkuOsoite));
        binaarina.append(lisaaMuuTeksti(teksti, alkuOsoite));       
        
        return binaarina.toString();
    }
    
    /**
     * Poistaa "alkuOsoite":tta seuraavasta tavusta ko. osoitteessa olevan
     * ascii -merkin määrän verran etunollia.
     * @param teksti
     * @param alkuOsoite
     * @return 
     */
    
    protected String tavuIlmanEtuNollia(String teksti, int alkuOsoite) {
        int arvo = teksti.charAt(alkuOsoite + 1);
        int poistettavia = teksti.charAt(alkuOsoite);
        
        String binaarina = binaariEsitys8Bit(arvo);
        return binaarina.substring(poistettavia);
    }
    
    /**
     * Palauttaa pakkauksen sisällöstä binääriesityksen kohdasta alkuOsoite + 2 alkaen.
     * @param teksti
     * @param alkuOsoite
     * @return 
     */
    
    protected String lisaaMuuTeksti(String teksti, int alkuOsoite) {
        StringBuilder lisaaja = new StringBuilder();
        
        for (int i = alkuOsoite + 2; i < teksti.length(); i++) {
            int arvo = teksti.charAt(i);
            lisaaja.append(binaariEsitys8Bit(arvo));
        }
        return lisaaja.toString();
    }
}