package kolmiopeli.logiikka.tiralabraAlgoritmit;

import java.util.ArrayList;
import java.util.Collection;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Koordinaatti;
import kolmiopeli.domain.Variarpoja;

/**
 *
 * @author Eemi
 */
public class Romahduttaja {

    private Kolmio[][] peliruudukko;
    private final Variarpoja variarpoja;
    private final RomahduttajaDebugViestit debugViestit;
    
    public Romahduttaja(Kolmio[][] peliruudukko, boolean debugViestitPaalla) {
        this.peliruudukko = peliruudukko;
        this.variarpoja = new Variarpoja();
        this.debugViestit = new RomahduttajaDebugViestit(debugViestitPaalla);
    }
    
    // Jarjesta joukko oikein
    // Keraa kasaan kaikki koordinaatit joita on siirretty
    public void romahduta(Collection romahtavat) {
        ArrayList<Koordinaatti> jarjestettyRomahtavat = (ArrayList<Koordinaatti>) romahtavat;
        for (Koordinaatti koordinaatti : jarjestettyRomahtavat) {
            Koordinaatti taytettavaTyhja = koordinaatti;
            while (taytettavaTyhja != null) {
                taytettavaTyhja = tiputaYksiKolmio(taytettavaTyhja);
            }
            this.debugViestit.viivoja();
        }
    }

    
    
    
    public Koordinaatti tiputaYksiKolmio(Koordinaatti tyhja) {
        if (tyhja.getRivi() == 0) {
            return tyhjaOnYlarivilla(tyhja);
        } else {
            return tyhjaOnMuullaRivilla(tyhja);
        }
    }

    private Koordinaatti tyhjaOnYlarivilla(Koordinaatti tyhja) {
        if (tyhja.osoittaakoKoordinaatinRuutuYlospain()) {
            return siirraViereista(tyhja);
        } else {
            peliruudukko[tyhja.getRivi()][tyhja.getSarake()] = new Kolmio(variarpoja.arvoVari(), tyhja.getRivi(), tyhja.getSarake());
            return null;
        }
    }

    private Koordinaatti tyhjaOnMuullaRivilla(Koordinaatti tyhja) {
        if (!tyhja.osoittaakoKoordinaatinRuutuYlospain()) {
            int romahtavanRivi = tyhja.getRivi() - 1;
            int romahtavanSarake = tyhja.getSarake();
            return siirraKolmionPaikkaa(tyhja.getRivi(), tyhja.getSarake(), romahtavanRivi, romahtavanSarake);
        } else {
            return siirraViereista(tyhja);
        }
    }

    private Koordinaatti siirraViereista(Koordinaatti tyhja) {
        int romahtavanRivi;
        int romahtavanSarake;
        if (vieriikoTyhjaanKolmioOikealta(tyhja)) {
            romahtavanRivi = tyhja.getRivi();
            romahtavanSarake = tyhja.getSarake() + 1;
        } else {
            romahtavanRivi = tyhja.getRivi();
            romahtavanSarake = tyhja.getSarake() - 1;
        }
        return siirraKolmionPaikkaa(tyhja.getRivi(), tyhja.getSarake(), romahtavanRivi, romahtavanSarake);
    }

    public Koordinaatti siirraKolmionPaikkaa(int tyhjaRivi, int tyhjaSarake, int romahtavanRivi, int romahtavanSarake) {
        this.debugViestit.kolmioRomahtaa(tyhjaRivi, tyhjaSarake, romahtavanRivi, romahtavanSarake);
        peliruudukko[tyhjaRivi][tyhjaSarake] = peliruudukko[romahtavanRivi][romahtavanSarake];
        peliruudukko[romahtavanRivi][romahtavanSarake] = null;
        return new Koordinaatti(romahtavanRivi, romahtavanSarake);
    }

    private boolean vieriikoTyhjaanKolmioOikealta(Koordinaatti tyhja) {
        if (tyhja.getRivi() % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
