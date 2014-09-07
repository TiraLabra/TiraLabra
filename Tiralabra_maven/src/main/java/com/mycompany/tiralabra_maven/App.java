package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.peli.PeliOhjain;
import com.mycompany.tiralabra_maven.gui.Kayttoliittyma;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 * Luokka luo uuden peliohjaimen ja käyttöliittymän.
 *
 */
public class App {

    public static void main(String[] args) {
        PeliOhjain ohjain = new PeliOhjain();
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(ohjain);
        try {
            SwingUtilities.invokeAndWait(kayttoliittyma);
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        ohjain.setPaivitettava(kayttoliittyma.getPaivitettava());

    }
}
