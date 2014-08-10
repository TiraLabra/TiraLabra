package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.algoritmi.Simulaatio;
import com.mycompany.tiralabra_maven.gui.Kayttoliittyma;
import javax.swing.SwingUtilities;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        
        Piirtologiikka piirtologiikka = new Piirtologiikka(10, 10);
        Simulaatio simulaatio = new Simulaatio(piirtologiikka, true);
        Kayttoliittyma gui = new Kayttoliittyma(simulaatio, piirtologiikka, 24);
        SwingUtilities.invokeLater(gui);
        while (gui.getPiirtoalusta() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }
        
        Thread piirtoSaie = new Thread(gui.getPiirtoalusta());
        piirtoSaie.start();
        simulaatio.start();
    }

}
