package viidensuora.gui;

import viidensuora.ai.MinMax;
import viidensuora.ai.Tekoaly;
import viidensuora.peli.Peli;

/**
 * Ohjelman käynnistävä luokka. ToDo..
 *
 * @author juha
 */
public class Main {

    public static void main(String[] args) {
        Peli p = new Peli(3, 3, 3);
        Tekoaly ai = new Tekoaly(p, new MinMax(), 4);
    }

}
