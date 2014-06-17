package apurakenteet;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * Luokka, joka huolehtii halutun kuvan näyttämisestä yksinkertaisena JFramena.
 */
public class Kuvanayttaja {

    /**
     * Näytettävä kuva
     */
    private BufferedImage kuva;
    
    /**
     * Konstruktori, jolle annetaan parametrina näytettäväksi tarkoitettu kuva.
     * @param kuva 
     */
    public Kuvanayttaja(BufferedImage kuva) {
        this.kuva = kuva;
    }
    
    /**
     * Kuvan näyttämisen toteuttava metodi.
     * Toiminta hyvin pelkistetysti JFramen luonti ja näyttäminen.
     */
    public void naytaKuva() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.getContentPane().add(new JLabel(new ImageIcon(kuva)));
        frame.pack();
        frame.setVisible(true);
    }
    
}
