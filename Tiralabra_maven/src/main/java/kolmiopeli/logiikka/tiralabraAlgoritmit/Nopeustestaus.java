package kolmiopeli.logiikka.tiralabraAlgoritmit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Koordinaatti;
import kolmiopeli.domain.Variarpoja;
import kolmiopeli.logiikka.tiralabraAlgoritmit.omatTietorakenteet.OmaHashSet;

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
    private KomboEtsijaJavalla javaEtsija;
    private Romahduttaja romahduttaja;

    public Nopeustestaus(int korkeus, int leveys) {
        arpoja = new Variarpoja();
        this.korkeus = korkeus;
        this.leveys = leveys;
        peliruudukko = new Kolmio[korkeus][leveys];
        taytaKolmiotSatunnaisesti();
        etsija = new KomboEtsija(peliruudukko, false);
        javaEtsija = new KomboEtsijaJavalla(peliruudukko, false);
        romahduttaja = new Romahduttaja(peliruudukko, false);
        alustaListaKokoRuudukonKoordinaateista();
    }
    
    public void rakenteidenNopeusvertailu(int kertoja) {
        System.out.println("-- NOPEUSVERTAILU --");
        System.out.println("Peliruudukon koko: " + korkeus + "x" + leveys);
        System.out.println("Testin toistomaara: " + kertoja);
        algoritminKeskiarvoNopeusOmillaRakenteilla(kertoja);
        algoritminKeskiarvoNopeusJavanRakenteilla(kertoja);
    }
    
    private void algoritminKeskiarvoNopeusOmillaRakenteilla(int kertoja) {
        ArrayList<Double> testitulokset = new ArrayList<Double>();
        for (int i = 0; i < kertoja; i++) {
            this.taytaKolmiotSatunnaisesti();
            double tulos = kayLapiKombojaOmillaRakenteilla();
            testitulokset.add(tulos);
        }
        System.out.println("Keskiarvo omilla rakenteilla: " + laskeKeskiarvo(testitulokset) + " ms"); 
    }
    
    private void algoritminKeskiarvoNopeusJavanRakenteilla(int kertoja) {
        ArrayList<Double> testitulokset = new ArrayList<Double>();
        for (int i = 0; i < kertoja; i++) {
            this.taytaKolmiotSatunnaisesti();
            double tulos = kayLapiKombojaJavanRakenteilla();
            testitulokset.add(tulos);
        }
        System.out.println("Keskiarvo javan rakenteilla: " + laskeKeskiarvo(testitulokset) + " ms"); 
    }
    
    
    private double kayLapiKombojaOmillaRakenteilla() {
        long aika = System.nanoTime();
        HashSet<Koordinaatti> kombot = (HashSet<Koordinaatti>) etsija.etsiKombot(tuhoutuvat);
        while (kombot.size() != 0) {
            
            kombot = (HashSet<Koordinaatti>) etsija.etsiKombot(kombot);
            tuhoaJaArvoUudetKohtiin(kombot);   
            
        }
        return (System.nanoTime() - aika)/1000000.0;
    }
    private double kayLapiKombojaJavanRakenteilla() {
        long aika = System.nanoTime();
        HashSet<Koordinaatti> kombot = (HashSet<Koordinaatti>) javaEtsija.etsiKombot(tuhoutuvat);
        while (!kombot.isEmpty()) {
            
            kombot = (HashSet<Koordinaatti>) javaEtsija.etsiKombot(kombot);
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
            peliruudukko[k.getRivi()][k.getSarake()] = null;
        }
        ArrayList<Koordinaatti> lista = new ArrayList<Koordinaatti>();
        lista.addAll(kombot);
        this.romahduttaja.romahduta(lista);
    }

    private double laskeKeskiarvo(ArrayList<Double> testitulokset) {
        double summa = 0;
        for (Double arvo : testitulokset) {
            summa += arvo;
        }
        return summa/testitulokset.size();
    }
}
