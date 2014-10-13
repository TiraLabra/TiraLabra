package viidensuora.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import viidensuora.logiikka.Ristinolla;

/**
 * Paneeli, jossa pelitilanne näytetään.
 * 
* @author juha
 */
public class Peliruutu extends JPanel {

    public static final int RUUDUN_KOKO = 25;
    private final Ristinolla ristinolla;
    private final int leveys;
    private final int korkeus;

    /**
     * Kuva joka piirretään ristin merkeille.
     */
    private BufferedImage ristinKuva;
    
    
    /**
     * Kuva joka piirretään nollan merkeille.
     */
    private BufferedImage nollanKuva;

    public Peliruutu(Ristinolla ristinolla) {
        this.ristinolla = ristinolla;
        this.leveys = ristinolla.leveys * RUUDUN_KOKO;
        this.korkeus = ristinolla.korkeus * RUUDUN_KOKO;

        try {
            this.ristinKuva = ImageIO.read(this.getClass().getResource("/risti.png"));
            this.nollanKuva = ImageIO.read(this.getClass().getResource("/nolla.png"));
        } catch (IOException ex) {
        }

        setPreferredSize(new Dimension(leveys, korkeus));
        addMouseListener(new Hiirikuuntelija(ristinolla, this));
    }

    /**
     * Piirtää pelimerkit ja ruudukon.
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        piirraPelimerkit(g);
        piirraRuudukko(g);
    }

    /**
     * Piirtää peliruudulle vaaka- ja pystyviivat.
     *
     * @param g
     */
    private void piirraRuudukko(Graphics g) {
        g.setColor(Color.BLACK);
        int pystyviivoja = ristinolla.leveys - 1;
        for (int i = 1; i <= pystyviivoja; i++) {
            int x = RUUDUN_KOKO * i - 1;
            g.drawLine(x, 0, x, korkeus - 1);
        }
        int vaakaviivoja = ristinolla.korkeus - 1;
        for (int i = 1; i <= vaakaviivoja; i++) {
            int y = RUUDUN_KOKO * i - 1;
            g.drawLine(0, y, leveys - 1, y);
        }
    }

    /**
     * Piirtää ruudulle Ristit ja Nollat.
     *
     * @param g
     */
    private void piirraPelimerkit(Graphics g) {
        for (int y = 0; y < ristinolla.korkeus; y++) {
            for (int x = 0; x < ristinolla.leveys; x++) {
                if (ristinolla.onRisti(x, y)) {
                    g.drawImage(ristinKuva, x * RUUDUN_KOKO, y * RUUDUN_KOKO, this);
                } else if (ristinolla.onNolla(x, y)) {
                    g.drawImage(nollanKuva, x * RUUDUN_KOKO, y * RUUDUN_KOKO, this);
                }
            }
        }
    }
}
