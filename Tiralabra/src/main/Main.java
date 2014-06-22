package main;

import gui.Kayttoliittyma;

/**
 * Main-luokka, joka käynnistää ohjelman luomalla uuden Kayttoliittyma-luokan
 * ilmentymän ja aktivoimalla sen näkyväksi keskelle ruutua.
 *
 * @author Eversor
 */
public class Main {

    public static void main(String[] args) {
        Kayttoliittyma gui = new Kayttoliittyma();
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
    }
}