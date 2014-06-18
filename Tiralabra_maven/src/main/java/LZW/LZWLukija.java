package LZW;

import Apuvalineet.BinaariMuuntaja;
import Tietorakenteet.HajautusTaulu;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class LZWLukija {
    private BinaariMuuntaja muuntaja;
    private DataInputStream lukija;
    private StringBuilder teksti;
    private HajautusTaulu ascii;
    private HajautusTaulu laaj;
    
    public LZWLukija() {
        this.teksti = new StringBuilder();
        this.muuntaja = new BinaariMuuntaja();
        alustaAsciiKoodistot();
    }
    
    private void alustaAsciiKoodistot() {
        this.ascii = new HajautusTaulu();
        for (int i = 0; i < 256; i++) {
            ascii.lisaa((char) i + "", muuntaja.binaariEsitys8Bit(i));
        }
        
        this.laaj = new HajautusTaulu();
    }
    
    public String getTeksti() {
        return this.teksti.toString();
    }
    
    protected HajautusTaulu getAsciiKoodisto() {
        return this.ascii;
    }
    
    protected HajautusTaulu getLaajennettuKoodisto() {
        return this.laaj;
    }
    
    public void lue(String polku) throws IOException {
        this.lukija = new DataInputStream(new FileInputStream(polku));
        StringBuilder lisattava = new StringBuilder();
        
        while (true) {
            lisattava = lisaaMerkki(lukija.read(), lisattava);
            
            if (lisattava == null) {
                break;
            }
            
        }
        lukija.close();
    }
    
    protected StringBuilder lisaaMerkki(int arvo, StringBuilder lisattava) {
        String nykyinen = lisattava.toString();
        
        if (arvo == -1) {
            lisaaBittijonoTekstiin(nykyinen);
            return null;
        }

        String merkki = (char) arvo + "";
        String seuraava = nykyinen + merkki;
        
        if (! nykyinen.isEmpty() && ! laaj.sisaltaaAvaimen(seuraava)) {
            lisaaKoodistoon(seuraava);
            lisaaBittijonoTekstiin(nykyinen);
                
            lisattava = new StringBuilder();
        }
        
        lisattava.append(merkki);
        return lisattava;
    }
    
    protected void lisaaKoodistoon(String avain) {
        int arvoja = new LZWYleisMetodeja().arvoja(ascii, laaj);
        laaj.lisaa(avain, muuntaja.binaariEsitys(arvoja));
    }
    
    protected void lisaaBittijonoTekstiin(String nykyinen) {
        if (laaj.sisaltaaAvaimen(nykyinen)) {
            lisaaBittijono(laaj, nykyinen);
        }
        else {
            lisaaBittijono(ascii, nykyinen);
        }
    }
    
    protected void lisaaBittijono(HajautusTaulu koodisto, String lisattava) {
        LZWYleisMetodeja yleis = new LZWYleisMetodeja();
        String bittijono = yleis.bittijonona(lisattava, ascii, laaj);
        teksti.append(bittijono);
    }
    

//    
//    protected void lisaaArvojenEteenEtuNolla() {
//        for (String avain : esitykset.getAvaimet()) {
//            esitykset.lisaa(avain, (char) 0 + esitykset.getArvo(avain));
//        }
//    }
}
