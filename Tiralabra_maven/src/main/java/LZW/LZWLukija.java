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
    private HajautusTaulu asciiKoodisto;
    private HajautusTaulu laajennettuKoodisto;
    private int index;
    
    public LZWLukija() {
        this.teksti = new StringBuilder();
        this.muuntaja = new BinaariMuuntaja();
        this.index = 0;
        alustaHajTaulu();
    }
    
    public String getTeksti() {
        return this.teksti.toString();
    }
    
    protected HajautusTaulu getAsciiKoodisto() {
        return this.asciiKoodisto;
    }
    
    protected HajautusTaulu getLaajennettuKoodisto() {
        return this.laajennettuKoodisto;
    }
    
    protected int getIndex() {
        return this.index;
    }
    
    private void alustaHajTaulu() {
        this.asciiKoodisto = new HajautusTaulu();
        
        for (int i = 0; i < 256; i++) {
            asciiKoodisto.lisaa((char) i + "", muuntaja.binaariEsitys8Bit(i));
            index++;
        }
        this.laajennettuKoodisto = new HajautusTaulu();
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
        
        if (! nykyinen.isEmpty() && ! laajennettuKoodisto.sisaltaaAvaimen(seuraava)) {
            lisaaKoodistoon(seuraava);
            lisaaBittijonoTekstiin(nykyinen);
                
            lisattava = new StringBuilder();
        }
        
        lisattava.append(merkki);
        return lisattava;
    }
    
    protected void lisaaKoodistoon(String avain) {
        laajennettuKoodisto.lisaa(avain, muuntaja.binaariEsitys(index));
        index++;
    }
    
    protected void lisaaBittijonoTekstiin(String nykyinen) {
        if (laajennettuKoodisto.sisaltaaAvaimen(nykyinen)) {
            lisaaBittijono(laajennettuKoodisto, nykyinen);
        }
        else {
            lisaaBittijono(asciiKoodisto, nykyinen);
        }
    }
    
    protected void lisaaBittijono(HajautusTaulu koodisto, String lisattava) {
        StringBuilder builder = new StringBuilder();
        String arvo = koodisto.getArvo(lisattava);
        
        while (builder.toString().length() + arvo.length() < merkkienPituus()) {
            builder.append((char) 0);
        }
        
        builder.append(arvo);
        teksti.append(builder.toString());
    }
    
    
    protected int  merkkienPituus() {
        int i = 8;
        
        while (true) {
            double potenssi = Math.pow(2, i);
            
            if (potenssi < index) {
                i++;
                continue;
            }
            return i;
        }
    }
//    
//    protected void lisaaArvojenEteenEtuNolla() {
//        for (String avain : esitykset.getAvaimet()) {
//            esitykset.lisaa(avain, (char) 0 + esitykset.getArvo(avain));
//        }
//    }
}
