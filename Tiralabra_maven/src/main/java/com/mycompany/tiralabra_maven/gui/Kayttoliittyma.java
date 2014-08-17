package com.mycompany.tiralabra_maven.gui;

import com.mycompany.tiralabra_maven.Peli;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Luokka tarjoaa käyttöliittymän metodit
 * @author noora
 */
public class Kayttoliittyma implements Runnable {
    private Peli peli;
    private JFrame frame;
    private Piirtoalusta piirtoalusta;
    
    /**
     * 
     * @param peli Konstruktorille annetaan käynnissä oleva peli
     */
    public Kayttoliittyma(Peli peli){
        this.peli = peli;
    }

    public void run() {
        frame = new JFrame("Tammi");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Luo käyttöliittymän tarvitseman piirtoalustan
     * @param contentPane 
     */
    private void luoKomponentit(Container contentPane) {
        this.piirtoalusta = new Piirtoalusta(peli);
        contentPane.add(piirtoalusta);
    }
    
    public Piirtoalusta getPaivitettava(){
        return this.piirtoalusta;
    }
    
}
