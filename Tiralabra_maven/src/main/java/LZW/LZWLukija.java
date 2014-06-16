package LZW;

import Apuvalineet.BinaariMuuntaja;
import Tietorakenteet.HajTaulu;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class LZWLukija {
    private BinaariMuuntaja muuntaja;
    private DataInputStream lukija;
    private StringBuilder teksti;
    private HajTaulu esitykset;
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
    
    public HajTaulu getEsitykset() {
        return this.esitykset;
    }
    
    protected int getIndex() {
        return this.index;
    }
    
    private void alustaHajTaulu() {
        this.esitykset = new HajTaulu();
        
        for (int i = 0; i < 256; i++) {
            esitykset.lisaa((char) i + "", muuntaja.binaariEsitysEtuNollilla8Bit(i));
            index++;
        }
    }
    
    public void lue(String polku) throws IOException {
        this.lukija = new DataInputStream(new FileInputStream(polku));
        StringBuilder lisattava = new StringBuilder();
        
        while (true) {
            
            int arvo = lukija.read();
            
            if (arvo == -1) {
                break;
            }
            lisattava = lisaaMerkki((char) arvo + "", lisattava);
            
        }
        lukija.close();
    }
    
    protected StringBuilder lisaaMerkki(String merkki, StringBuilder lisattava) {
        teksti.append(merkki);
        lisattava.append(merkki);
        
        if (! esitykset.sisaltaaAvaimen(lisattava.toString())) {
            lisaaAvain(lisattava.toString());

            lisattava = new StringBuilder();
            lisattava.append(merkki);
        }
        return lisattava;
    }
    
    protected void lisaaAvain(String avain) {
        esitykset.lisaa(avain, muuntaja.binaariEsitysEtuNollilla(index));
        index++;
            
        if (indexKahdenPotenssi()) {
            lisaaArvojenEteenEtuNolla();
        }
    }
    
    protected boolean indexKahdenPotenssi() {
        int i = 8;
        
        while (true) {
            double potenssi = Math.pow(2, i);
            
            if (potenssi < index) {
                i++;
            }
            else {
                return potenssi == index;
            }
        }
    }
    
    protected void lisaaArvojenEteenEtuNolla() {
        for (String avain : esitykset.getAvaimet()) {
            esitykset.lisaa(avain, (char) 0 + esitykset.getArvo(avain));
        }
    }
}
