package kolmiopeli.logiikka.tiralabraAlgoritmit;

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

    public Romahduttaja(Kolmio[][] peliruudukko) {
        this.peliruudukko = peliruudukko;
        this.variarpoja = new Variarpoja();
    }

    public void romahduta(Collection romahtavat) {
    }

    public void tiputaYksiKolmio(Koordinaatti tyhja) {
        if (tyhja.getRivi() == 0) {
            tyhjaOnYlarivilla(tyhja);
        } else {
            tyhjaOnMuullaRivilla(tyhja);
        }
    }

    public void siirraKolmionPaikkaa(int tyhjaRivi, int tyhjaSarake, int romahtavanRivi, int romahtavanSarake) {
        peliruudukko[tyhjaRivi][tyhjaSarake] = peliruudukko[romahtavanRivi][romahtavanSarake];
        peliruudukko[romahtavanRivi][romahtavanSarake] = null;
    }

    private void siirraViereista(Koordinaatti tyhja) {
    }

    private void tyhjaOnYlarivilla(Koordinaatti tyhja) {
        if (tyhja.osoittaakoKoordinaatinRuutuYlospain()) {
            siirraViereista(tyhja);
        } else {
            peliruudukko[tyhja.getRivi()][tyhja.getSarake()] = new Kolmio(variarpoja.arvoVari(), tyhja.getRivi(), tyhja.getSarake());
        }
    }

    private void tyhjaOnMuullaRivilla(Koordinaatti tyhja) {
        if (!tyhja.osoittaakoKoordinaatinRuutuYlospain()) {
            int romahtavanRivi = tyhja.getRivi() - 1;
            int romahtavanSarake = tyhja.getSarake();
            siirraKolmionPaikkaa(tyhja.getRivi(), tyhja.getSarake(), romahtavanRivi, romahtavanSarake);   
        }
    }
}
