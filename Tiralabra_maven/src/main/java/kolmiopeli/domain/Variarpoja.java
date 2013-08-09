package kolmiopeli.domain;

import java.awt.Color;
import java.util.Random;

/**
 * Olio joka arpoo satunnaisen varin sallituista neljasta (Kolmiolle).
 *
 * @author Eemi
 */
public class Variarpoja {

    /**
     * Random olio joka tuottaa satunnaisuuden.
     */
    private Random arpoja;

    /**
     * Luo tyhjasta uuden Random olion itselleen kaytettavaksi.
     */
    public Variarpoja() {
        this.arpoja = new Random();
    }

    /**
     * Arpoo varin kuudesta sallitusta varista.
     *
     * @return arvottu vari
     */
    public Color arvoVari() {
        int variLukuna = this.arpoja.nextInt(6); // MUUTA TATA JOS LISAAT VAREJA

        if (variLukuna == 0) {
            return Color.YELLOW;
        } else if (variLukuna == 1) {
            return Color.RED;
        } else if (variLukuna == 2) {
            return Color.BLUE;
        } else if (variLukuna == 3) {
            return Color.GREEN;
        } else if (variLukuna == 4) {
            return Color.MAGENTA;
        } else {
            return Color.CYAN;
        }

    }

    /**
     * Arpoo varin poislukien parametrina annetun varin.
     *
     * @param vari vari jota ei haluta
     * @return arvottu vari (eri kuin syote)
     */
    public Color arvoVariMuuKuin(Color vari) {
        Color palautettava;

        if (vari == Color.YELLOW) {
            palautettava = eiKeltainen();
        } else if (vari == Color.RED) {
            palautettava = eiPunainen();
        } else if (vari == Color.BLUE) {
            palautettava = eiSininen();
        } else if (vari == Color.GREEN) {
            palautettava = eiVihrea();
        } else if (vari == Color.MAGENTA) {
            palautettava = eiVioletti();
        } else {
            palautettava = eiCyan();
        }
        return palautettava;
    }

    private Color eiKeltainen() {
        int variLukuna = this.arpoja.nextInt(5);

        if (variLukuna == 0) {
            return Color.RED;
        } else if (variLukuna == 1) {
            return Color.BLUE;
        } else if (variLukuna == 2) {
            return Color.GREEN;
        } else if (variLukuna == 3) {
            return Color.MAGENTA;
        } else {
            return Color.CYAN;
        }
    }

    private Color eiPunainen() {
        int variLukuna = this.arpoja.nextInt(5);

        if (variLukuna == 0) {
            return Color.BLUE;
        } else if (variLukuna == 1) {
            return Color.GREEN;
        } else if (variLukuna == 2) {
            return Color.MAGENTA;
        } else if (variLukuna == 3) {
            return Color.CYAN;
        } else {
            return Color.YELLOW;
        }
    }

    private Color eiSininen() {
        int variLukuna = this.arpoja.nextInt(5);

        if (variLukuna == 0) {
            return Color.GREEN;
        } else if (variLukuna == 1) {
            return Color.MAGENTA;
        } else if (variLukuna == 2) {
            return Color.CYAN;
        } else if (variLukuna == 3) {
            return Color.YELLOW;
        } else {
            return Color.RED;
        }
    }

    private Color eiVihrea() {
        int variLukuna = this.arpoja.nextInt(5);

        if (variLukuna == 0) {
            return Color.MAGENTA;
        } else if (variLukuna == 1) {
            return Color.CYAN;
        } else if (variLukuna == 2) {
            return Color.YELLOW;
        } else if (variLukuna == 3) {
            return Color.RED;
        } else {
            return Color.BLUE;
        }
    }

    private Color eiVioletti() {
        int variLukuna = this.arpoja.nextInt(5);

        if (variLukuna == 0) {
            return Color.CYAN;
        } else if (variLukuna == 1) {
            return Color.YELLOW;
        } else if (variLukuna == 2) {
            return Color.RED;
        } else if (variLukuna == 3) {
            return Color.BLUE;
        } else {
            return Color.GREEN;
        }
    }

    private Color eiCyan() {
        int variLukuna = this.arpoja.nextInt(5);
        if (variLukuna == 0) {
            return Color.YELLOW;
        } else if (variLukuna == 1) {
            return Color.RED;
        } else if (variLukuna == 2) {
            return Color.BLUE;
        } else if (variLukuna == 3) {
            return Color.GREEN;
        } else {
            return Color.MAGENTA;
        }
    }
}
