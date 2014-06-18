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
        alustaKoodistot();
        this.pituus = new LZWYleisMetodeja().merkkienPituus(ascii, laaj);
    }
    
    private void alustaKoodistot() {
        this.ascii = new HajautusTaulu();
        for (int i = 0; i < 256; i++) {
            ascii.lisaa((char) i + "", muuntaja.binaariEsitys8Bit(i));
        }
        
        this.laaj = new HajautusTaulu();
    }
    
    protected HajautusTaulu getAsciiKoodisto() {
        return this.ascii;
    }
    
    protected HajautusTaulu getLaajennettuKoodisto() {
        return this.laaj;
    }
    
    @Override
    protected String puretunTiedostonSisalto(String teksti) {
        String tekstiBinaarina = tekstiBinaarina(teksti, 0);
        return kirjoitettavaTeksti(tekstiBinaarina);
    }
    
    protected String kirjoitettavaTeksti(String binaarina) {
        this.teksti = new StringBuilder();
        String nykyinen = "";
        int i = 0;
        
        while (i < binaarina.length() - pituus) {
            String bittijono = binaarina.substring(i, i + pituus);
            nykyinen = lisaaMerkki(nykyinen, asciiMerkki(bittijono));
            
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
        LZWYleisMetodeja yleis = new LZWYleisMetodeja();
        int arvoja = yleis.arvoja(ascii, laaj);
        laaj.lisaa(avain, muuntaja.binaariEsitys(arvoja));

        paivitaPituusJosPienempi(new LZWYleisMetodeja().merkkienPituus(ascii, laaj));
    }
    
    /**
     * Palauttaa ascii -merkin bittiesityksen (8 bittiä) bittijonosta.
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