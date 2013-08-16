package kolmiopeli.logiikka.tiralabraAlgoritmit;

import java.util.ArrayList;
import java.util.HashSet;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Koordinaatti;
import kolmiopeli.domain.Variarpoja;

/**
 *
 * @author Eemi
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
        etsija = new KomboEtsija(peliruudukko);
        alustaListaKokoRuudukonKoordinaateista();
    }
    
    public void kayLapiKomboja() {
        HashSet<Koordinaatti> kombot = etsija.etsiKombot(tuhoutuvat);
        while (!kombot.isEmpty()) {
            
            kombot = etsija.etsiKombot(kombot);
            
            tuhoaJaArvoUudetKohtiin(kombot);
            
        } 
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
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
