package kolmiopeli.logiikka;

import java.util.ArrayList;
import kolmiopeli.domain.Koordinaatti;

/**
 * Luokka pitaa kirjaa pelin pisteista.
 *
 * @author Eemi
 */
public class Pisteenlaskija {

    private int pisteet;


    /**
     * Alustaa pisteenlaskijalle pisteet nollaksi.
     *
     * @param peliruudukko
     */
    public Pisteenlaskija() {
        this.pisteet = 0;

    }

    public int getPisteet() {
        return pisteet;
    }

    /**
     * Nollaa pistelaskijan pisteet.
     */
    public void nollaaPisteet() {
        this.pisteet = 0;
    }

    /**
     * Kirjaa tuhoutuneiden kolmioiden pisteet.
     *
     * @param tuhoutuvat Lista tuhottavien ruutujen koordinaateista
     */
    public void lisaaTuhoutuneistaPisteet(ArrayList<Koordinaatti> tuhoutuvat) {
        this.pisteet += 10 * tuhoutuvat.size() * (tuhoutuvat.size() - 2);
    }
}
