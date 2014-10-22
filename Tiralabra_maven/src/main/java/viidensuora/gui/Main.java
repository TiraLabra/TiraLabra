package viidensuora.gui;

import javax.swing.SwingUtilities;

/**
 * Ohjelman käynnistävä luokka.
 *
 * @author juha
 */
public class Main {

    public static void main(String[] args) {
        Kayttoliittyma kali = new Kayttoliittyma();
        SwingUtilities.invokeLater(kali);
    }

}
