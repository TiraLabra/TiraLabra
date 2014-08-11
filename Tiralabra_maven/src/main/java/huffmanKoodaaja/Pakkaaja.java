package huffmanKoodaaja;

import huffmanKoodaaja.kasittely.Algoritmi;
import huffmanKoodaaja.kasittely.Tiedosto;

/**
 * Pääluokka. Vastaa algoritmin käynnistämisestä.
 */
public class Pakkaaja {

    /**
     * Käynnistää tiedostonkäsittelyn ja algoritmin.
     *
     * @param args Pakattavan/purettavan tiedoston tiedostosijainti ja
     * mahdollisesti myös pakatun/puretun tiedoston tallennussijainnin.
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            String sijainti = args[0];
            Tiedosto tiedosto = null;
            if (args.length > 1) {
                String tallennus = args[1];
                tiedosto = new Tiedosto(sijainti, tallennus);
            } else {
                tiedosto = new Tiedosto(sijainti);
            }
            Algoritmi algoritmi = new Algoritmi();
            algoritmi.setTiedosto(tiedosto);
            algoritmi.kasittele();
        }
    }

}
