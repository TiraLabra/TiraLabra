package viidensuora.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import viidensuora.peli.Peli;

/**
 * Käyttöliittymän ikkuna.
 * 
 * @author juha
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;

    /**
     * Luo Pelin, Peliruudun ja näyttää ne ikkunassa.
     */
    public void run() {
        frame = new JFrame("Ristinolla");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Peliruutu peliruutu = new Peliruutu(new Peli(3, 3, 3));
        frame.getContentPane().add(peliruutu);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
