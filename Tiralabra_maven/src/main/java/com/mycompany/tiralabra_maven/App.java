package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.gui.Kayttoliittyma;
import com.mycompany.tiralabra_maven.gui.PiirtoSaie;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        Piirtologiikka piirtologiikka = new Piirtologiikka(10, 10);
        Simulaatio simulaatio = new Simulaatio(piirtologiikka);

        Kayttoliittyma gui = new Kayttoliittyma(simulaatio, piirtologiikka, 24);
        SwingUtilities.invokeLater(gui);
        while (gui.getPiirtoalusta() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }
        //simulaatio.setPaivitettava(gui.getPiirtoalusta());

        PiirtoSaie piirtosaie = new PiirtoSaie(gui.getPiirtoalusta(), simulaatio);
        piirtosaie.start();
//        simulaatio.start();
//        //simulaatio.aloita();
//        System.out.println("simulaatio valmis?");
//        for (int i = 0; i < 100; i++) {
//            gui.getPiirtoalusta().paivita();
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

}
