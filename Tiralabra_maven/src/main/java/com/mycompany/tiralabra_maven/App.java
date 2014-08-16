package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.algoritmi.Simulaatio;
import com.mycompany.tiralabra_maven.gui.Kayttoliittyma;
import com.mycompany.tiralabra_maven.tietorakenteet.PrioriteettiKeko;
import javax.swing.SwingUtilities;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        
//        PrioriteettiKeko<String> keko = new PrioriteettiKeko<>();
//        keko.lisaa(2);
//        keko.lisaa(7);
//        keko.lisaa(0);
//        keko.lisaa(12);
//        keko.lisaa(16);
//        keko.lisaa(3);
//        keko.lisaa(1);
//        
//        for (int i = 0; i<10; i++) {
//            System.out.println(keko.seuraava());
//        }
        
        Simulaatio simulaatio = new Simulaatio(true);
        Kayttoliittyma gui = new Kayttoliittyma(simulaatio, 24);
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
    }

}
