package viidensuora.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import viidensuora.logiikka.Ristinolla;

/**
* Paneeli, jossa pelitilanne näytetään.
*
* @author juha
*/
public class Peliruutu extends JPanel {

    public static final int RUUDUN_KOKO = 20;
    private final Ristinolla ristinolla;
    private final int leveys;
    private final int korkeus;

    public Peliruutu(Ristinolla ristinolla) {
        this.ristinolla = ristinolla;
        this.leveys = ristinolla.leveys * RUUDUN_KOKO;
        this.korkeus = ristinolla.korkeus * RUUDUN_KOKO;
        setPreferredSize(new Dimension(leveys, korkeus));
        addMouseListener(new Hiirikuuntelija(ristinolla, this));
    }

    /**
     * Piirtää pelimerkit ja ruudukon.
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
     * @param g 
     */
    private void piirraPelimerkit(Graphics g) {
        for (int y = 0; y < ristinolla.korkeus; y++) {
            for (int x = 0; x < ristinolla.leveys; x++) {
                if (ristinolla.onRisti(x, y)) {
                    g.setColor(Color.BLUE);
                    g.drawLine(x * RUUDUN_KOKO, y * RUUDUN_KOKO, (x + 1) * RUUDUN_KOKO, (y + 1) * RUUDUN_KOKO);
                    g.drawLine(x * RUUDUN_KOKO, (y + 1) * RUUDUN_KOKO, (x + 1) * RUUDUN_KOKO, y * RUUDUN_KOKO);
                } else if (ristinolla.onNolla(x, y)) {
                    g.setColor(Color.RED);
                    g.drawOval(x * RUUDUN_KOKO + 1, y * RUUDUN_KOKO + 1, RUUDUN_KOKO - 4, RUUDUN_KOKO - 4);
                }
            }
        }
    }
}
