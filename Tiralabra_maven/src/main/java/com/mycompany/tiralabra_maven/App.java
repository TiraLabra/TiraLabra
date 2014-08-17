package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.gui.Kayttoliittyma;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 * Luokka luo uuden peliolion ja käyttöliittymän.
 *
 */
public class App {

    public static void main(String[] args) {
        Peli peli = new Peli();
        //Tekstikayttoliittyma kayttoliittyma = new Tekstikayttoliittyma(peli);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(peli);
        try {
            SwingUtilities.invokeAndWait(kayttoliittyma);
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        peli.setPaivitettava(kayttoliittyma.getPaivitettava());

    }
}
