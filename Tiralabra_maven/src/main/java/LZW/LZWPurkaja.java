package LZW;

import Apuvalineet.BinaariMuuntaja;
import Apuvalineet.Purkaja;
import Tietorakenteet.HajautusTaulu;

public class LZWPurkaja extends Purkaja {
    private BinaariMuuntaja muuntaja;
    private StringBuilder teksti;
    private HajautusTaulu ascii;
    private HajautusTaulu laaj;
    private int pituus;
    
    public LZWPurkaja() {
        super(".lzw");
        this.muuntaja = new BinaariMuuntaja();
        this.laaj = new HajautusTaulu();
        
        YleisMetodeja y = new YleisMetodeja();
        this.ascii = y.alustaAscii();
        this.pituus = y.merkkienPituus(ascii, laaj);
    }
    
    protected HajautusTaulu getAscii() {
        return this.ascii;
    }
    
    protected HajautusTaulu getLaaj() {
       return this.laaj; 
    }
    
    protected int getPituus() {
        return this.pituus;
    }
    
    @Override
    protected String puretunTiedostonSisalto(String teksti) {
        String binaarina = tekstiBinaarina(teksti, 0);
        return kirjoitettavaTeksti(binaarina);
    }
    
    /**
     * Metodi saa tekstin bin‰‰rin‰ juuri niin kuin pit‰‰kin.
     * @param binaarina
     * @return 
     */
    
    protected String kirjoitettavaTeksti(String binaarina) {
        this.teksti = new StringBuilder();
        String nykyinen = "";
        int i = 0;
        
        while (i < binaarina.length()) {
            String merkkijono = seuraavaMerkkiJono(binaarina, i);
            nykyinen = lisaaMerkki(nykyinen, merkkijono);
            
            i += pituus; 
        }
        
        teksti.append(nykyinen);
        return teksti.toString();
    }
    
    
    protected String lisaaMerkki(String nykyinen, String merkki) {
        String seuraava = nykyinen + merkki;
        
        if (! nykyinen.isEmpty() && ! laaj.sisaltaaAvaimen(seuraava)) {
            
            teksti.append(nykyinen);
            
            lisaaKoodistoon(seuraava);
            seuraava = merkki;
        }
        
        return seuraava;
    }
    
    protected void lisaaKoodistoon(String avain) {
        YleisMetodeja yleis = new YleisMetodeja();
        int arvoja = yleis.arvoja(ascii, laaj);
        laaj.lisaa(avain, muuntaja.binaariEsitys(arvoja));

        paivitaPituusJosPienempi(new YleisMetodeja().merkkienPituus(ascii, laaj));
    }
    
    protected String seuraavaMerkkiJono(String binaarina, int i) {
        String bittijono = seuraavaBittijono(binaarina, i);
        return asciiMerkki(bittijono);
    }    
    
    protected String seuraavaBittijono(String binaarina, int i) {
        return binaarina.substring(i, i + pituus);
    }
    
    /**
     * Palauttaa ascii -merkin bittiesityksen (8 bitti‰) bittijonosta.
     * @param bittijono
     * @return 
     */
    
    protected String asciiMerkki(String bittijono) {
        String loppuosa = bittijono.substring(bittijono.length() - 8);
        return muuntaja.asciiMerkkina(loppuosa) + "";
    }

    protected void paivitaPituusJosPienempi(int merkkienPituus) {
        if (merkkienPituus > pituus) {
            pituus = merkkienPituus;
        }
    }
    

}