package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.kayttoliittyma.Tekstikayttoliittyma;
import com.mycompany.tiralabra_maven.tietorakenteet.AvlPuu;
import com.mycompany.tiralabra_maven.tietorakenteet.BinaarinenHakupuu;
import com.mycompany.tiralabra_maven.tietorakenteet.PunamustaPuu;
import com.mycompany.tiralabra_maven.tyokalut.PuunTutkija;

/**
 * Pääohjelma joka ei toistaiseksi tee käytännössä mitään
 *
 */
public class App {

    public static void main(String[] args) {
        
        Tekstikayttoliittyma ui = new Tekstikayttoliittyma(new PuunTutkija(new BinaarinenHakupuu(),new AvlPuu(), new PunamustaPuu()));
        ui.kaynnista();
    }
}
