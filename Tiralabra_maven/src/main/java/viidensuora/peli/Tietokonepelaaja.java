package viidensuora.peli;

import viidensuora.ai.Tekoaly;

/**
 * Tietokonepelaaja, jolla on tekoäly. Todo
 *
 * @author juha
 */
public class Tietokonepelaaja extends Pelaaja {

    /**
     * Tietokonepelaajan käyttämä tekoäly.
     */
    private final Tekoaly ai;

    public Tietokonepelaaja(Tekoaly ai) {
        this.ai = ai;
    }

}
