package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.kayttoliittyma.Tekstikayttoliittyma;
import com.mycompany.tiralabra_maven.tietorakenteet.*;
import com.mycompany.tiralabra_maven.tyokalut.PuunTutkija;

/**
 * Pääohjelma joka luo uuden tekstikäyttöliittymän uudella Puuntutkijalla, jolle
 * annetaan uusia puita. Tämän jälkeen käynnistää käyttöliittymän.
 *
 */
public class App {

    public static void main(String[] args) {
        Tekstikayttoliittyma ui = new Tekstikayttoliittyma(new PuunTutkija(new BinaarinenHakupuu(), new AvlPuu(), new PunamustaPuu(), new SplayPuu()));
        ui.kaynnista();
    }
}
