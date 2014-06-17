package apurakenteet;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import tietorakenteet.ArrayListOma;
import tietorakenteet.Node;

/**
 * Luokka, joka huolehtii halutun kuvan näyttämisestä yksinkertaisena JFramena.
 */
public class Kuvanayttaja {
    
    /**
     * Oletusväri, jolla reitti tulostetaan.
     */
    private static final int oletusReittivari = Color.RED.getRGB();

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
    
    /**
     * Metodi, joka piirtää näytettävään kuvaan halutun reitin.
     * Tämä käyttää tulostukseen oletusväriä.
     * @param reitti Piirrettävä reitti, joka saadaan AStar-haulta.
     */
    public void muodostaKuvaanPolku(ArrayListOma reitti) {
        muodostaKuvaanPolku(reitti, oletusReittivari);
//        for (int i = 0; i < reitti.koko(); i++) {
//            Node n = (Node) reitti.palautaKohdasta(i);
//            kuva.setRGB(n.getSarake(), n.getRivi(), Color.RED.getRGB());
//        }
    }
    
    /**
     * Metodi, joka piirtää näytettävään kuvaan halutun reitin.
     * @param reitti Piirrettävä reitti, joka saadaan AStar-haulta.
     * @param vari Reitin piirtoon käytettävä väri rbg-int-arvona.
     */
    public void muodostaKuvaanPolku(ArrayListOma reitti, int vari) {
        if (reitti != null) {
            for (int i = 0; i < reitti.koko(); i++) {
                Node n = (Node) reitti.palautaKohdasta(i);
                kuva.setRGB(n.getSarake(), n.getRivi(), Color.RED.getRGB());
            }
        }
        
    }
    
}
