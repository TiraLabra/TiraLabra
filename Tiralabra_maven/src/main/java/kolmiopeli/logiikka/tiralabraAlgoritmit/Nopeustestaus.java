package kolmiopeli.logiikka.tiralabraAlgoritmit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Koordinaatti;
import kolmiopeli.domain.Variarpoja;

/**
 * Nopeustestissa luodaan vapaasti arvottu peliruudukko jonka komboetsija kay
 * lapi kokonaan. Kaikkiin ensimmaisella lapikaynnilla loydettyihin kombo-kohtiin
 * arvotaan uudet kolmiot ja etsija kay nama kohdot uudelleen lapi. Tama jatkuu
 * kunnes taulukossa ei loydy enaa komboja.
 * 
 */
public class Nopeustestaus {

    private Kolmio[][] peliruudukko;
    private Variarpoja arpoja;
    private KomboEtsija etsija;
    private final int korkeus;
    private final int leveys;
    private ArrayList<Koordinaatti> tuhoutuvat;

    public Nopeustestaus(int korkeus, int leveys) {
        arpoja = new Variarpoja();
        this.korkeus = korkeus;
        this.leveys = leveys;
        peliruudukko = new Kolmio[korkeus][leveys];
        taytaKolmiotSatunnaisesti();
        etsija = new KomboEtsija(peliruudukko, false);
        alustaListaKokoRuudukonKoordinaateista();
    }
    
    public void algoritminKeskiarvoNopeus(int kertoja) {
        ArrayList<Double> testitulokset = new ArrayList<Double>();
        for (int i = 0; i < kertoja; i++) {
            this.taytaKolmiotSatunnaisesti();
            double tulos = kayLapiKomboja();
            testitulokset.add(tulos);
        }
        System.out.println(laskeKeskiarvo(testitulokset) + " ms"); 
    }
    
    
    public double kayLapiKomboja() {
        long aika = System.nanoTime();
        HashSet<Koordinaatti> kombot = (HashSet<Koordinaatti>) etsija.etsiKombot(tuhoutuvat);
        while (!kombot.isEmpty()) {
            
            kombot = (HashSet<Koordinaatti>) etsija.etsiKombot(kombot);
            tuhoaJaArvoUudetKohtiin(kombot);   
            
        }
        return (System.nanoTime() - aika)/1000000.0;
    }

    private void taytaKolmiotSatunnaisesti() {
        for (int rivi = 0; rivi < korkeus; rivi++) {
            for (int sarake = 0; sarake < leveys; sarake++) {
                peliruudukko[rivi][sarake] = new Kolmio(arpoja.arvoVari(), rivi, sarake);
            }
        }
    }

    private void alustaListaKokoRuudukonKoordinaateista() {
        tuhoutuvat = new ArrayList<Koordinaatti>();
        for (int rivi = 0; rivi < this.korkeus; rivi++) {
            for (int sarake = 0; sarake < this.leveys; sarake++) {
                this.tuhoutuvat.add(new Koordinaatti(rivi, sarake));
            }
        }
    }

    private void tuhoaJaArvoUudetKohtiin(HashSet<Koordinaatti> kombot) {
        for (Koordinaatti k : kombot) {
            peliruudukko[k.getRivi()][k.getSarake()] = new Kolmio(arpoja.arvoVari(), k.getRivi(), k.getSarake());
        }
    }

    private double laskeKeskiarvo(ArrayList<Double> testitulokset) {
        double summa = 0;
        for (Double arvo : testitulokset) {
            summa += arvo;
        }
        return summa/testitulokset.size();
    }
}
