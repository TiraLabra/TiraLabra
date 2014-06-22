package Huffman;

import Apuvalineet.Purkaja;
import Tietorakenteet.HajautusTaulu;

/**
 * Luokka suorittaa tiedoston purkamisen käyttäen BinaariMuuntaja luokkaa apuna.
 */

public class HuffmanPurkaja extends Purkaja {
    
    public HuffmanPurkaja() {
        super(".huff");
    }
    
    /**
     * Purkaa pakkauksen sisällön tiedostoon hakemalla ensin pakkauksen sisällön (ascii merkkeinä), käyden tästä
     * Huffman puun läpi ja keräten siitä "bittijonot ja niitä vastaavat merkit", muodostaen String -olion, jossa
     * on sisällön binääritekstiosa ja muodostaen tästä sitten puretun tiedoston sisällön.
     * @param teksti
     * @return
     */
    
    @Override
    protected String puretunTiedostonSisalto(String teksti) {
        HajautusTaulu bittijonotJaMerkit = new HajautusTaulu();
        
        int binaariTekstinAlku = kayPuuLapi(teksti, bittijonotJaMerkit);
        String tekstiBinaarina = tekstiBinaarina(teksti, binaariTekstinAlku);
        return kirjoitettavaTeksti(tekstiBinaarina, bittijonotJaMerkit);
    }
    
    /**
     * Muodostaa binääritekstistä (010110010...) ja haj. taulusta, jossa on bittijonoja ja niitä vastaavia ascii -merkkejä,
     * puretun tiedoston sisällön.
     * @param tekstiBinaarina
     * @param bittijonotJaMerkit
     * @return 
     */
    
    protected String kirjoitettavaTeksti(String tekstiBinaarina, HajautusTaulu bittijonotJaMerkit) {
        StringBuilder kirjoitettava = new StringBuilder();
        StringBuilder bittijono = new StringBuilder();
        
        for (int i = 0; i < tekstiBinaarina.length(); i++) {
            bittijono.append(tekstiBinaarina.charAt(i));
            
            if (bittijonotJaMerkit.sisaltaaAvaimen(bittijono.toString())) {
                kirjoitettava.append(bittijonotJaMerkit.getArvo(bittijono.toString()));
                bittijono = new StringBuilder();
            }
        }
        
        return kirjoitettava.toString();
    }
    
    /**
     * Kelaa pakatussa tiedostossa olevan Huffman -puun läpi ja kerää siitä tietona;
     * 1) Puuta seuraavan alkion osoitteen
     * 2) Binääriesitykset kutakin ascii -merkkiä varten.
     
     * @param teksti
     * @param bittijonotJaMerkit
     * @return - osoite, jossa puuta seuraavat merkit sijaitsevat
     */
    
    protected int kayPuuLapi(String teksti, HajautusTaulu bittijonotJaMerkit) {
        if (puuOnKelattuLoppuun(teksti, 0)) {
            return 2;
        }
        
        return kelaaPuuLapi(teksti, bittijonotJaMerkit);
    }
    
    private int kelaaPuuLapi(String teksti, HajautusTaulu bittijonotJaMerkit) {
        StringBuilder bittiEsitys = new StringBuilder();
        char kirjain = teksti.charAt(0);

        int i = 1;
        while (true) {
            char seuraava = teksti.charAt(i);
            if (! lisaaMerkkiJosSeOn0Tai1(seuraava, bittiEsitys)) {
                
                if (bittiEsitysEiTyhjaLisaaMerkki(kirjain, bittiEsitys.toString(), bittijonotJaMerkit)) {
                    kirjain = seuraava;
                    bittiEsitys = new StringBuilder();
                }
                
                if (puuOnKelattuLoppuun(teksti, i)) {
                    return i + 2;
                }
            }

            i++;
        }
    }
    
    /**
     * Lisää hajautustauluun bittijonon ja sitä vastaavan merkin, jos bittiesitys ei tyhjä.
     * @param kirjain
     * @param bittiEsitys
     * @param bittijonotJaMerkit
     * @return
     */
    
    protected boolean bittiEsitysEiTyhjaLisaaMerkki(char kirjain, String bittiEsitys, HajautusTaulu bittijonotJaMerkit) {
        if (bittiEsitys.isEmpty()) {
            return false;
        }
        
        bittijonotJaMerkit.lisaa(bittiEsitys, kirjain + "");
        return true;
    }
    
    /**
     * Jos merkki on 00 tai 01, lisätään se ja palautetaan tästä tieto.
     * @param merkki
     * @param bittiEsitys
     * @return 
     */
    
    protected boolean lisaaMerkkiJosSeOn0Tai1(char merkki, StringBuilder bittiEsitys) {
        if (merkki == 0 || merkki == 1) {
            String lisattava = merkki + "";
            bittiEsitys.append(lisattava);
            return true;
        }
        return false;
    }
    
    /**
     * Jos merkit paikoissa i ja (i+1) ovat 0x7F, puu on kelattu loppuun.
     * @param teksti
     * @param i
     * @return 
     */
    
    protected boolean puuOnKelattuLoppuun(String teksti, int i) {
        return teksti.charAt(i) == (char) 127 && teksti.charAt(i + 1) == (char) 127;
    }
}
