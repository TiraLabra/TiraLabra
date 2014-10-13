package viidensuora.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import viidensuora.logiikka.Ristinolla;

/**
 * Pääikkuna..
 *
 * @author juha
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;

    public void run() {
        frame = new JFrame("Ristinolla");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Peliruutu peliruutu = new Peliruutu(new Ristinolla(8, 8, 5));
        frame.getContentPane().add(peliruutu);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
