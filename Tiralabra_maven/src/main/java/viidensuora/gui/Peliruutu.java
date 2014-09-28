package viidensuora.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import viidensuora.peli.Peli;
import viidensuora.peli.Pelilauta;

/**
 * Paneeli, jossa pelitilanne näytetään.
 * 
 * @author juha
 */
public class Peliruutu extends JPanel {

    public static final int RUUDUN_KOKO = 20;

    private final Peli ristinolla;
    private final int leveys;
    private final int korkeus;

    public Peliruutu(Peli peli) {
        this.ristinolla = peli;
        this.leveys = ristinolla.getPelilauta().getLeveys() * RUUDUN_KOKO;
        this.korkeus = ristinolla.getPelilauta().getLeveys() * RUUDUN_KOKO;
        setPreferredSize(new Dimension(leveys, korkeus));
        addMouseListener(new Hiirikuuntelija(peli, this));
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        piirraPelimerkit(g);
        piirraRuudukko(g);
    }

    private void piirraRuudukko(Graphics g) {
        g.setColor(Color.BLACK);
        int pystyviivoja = ristinolla.getPelilauta().getLeveys() - 1;
        for (int i = 1; i <= pystyviivoja; i++) {
            int x = RUUDUN_KOKO * i - 1;
            g.drawLine(x, 0, x, korkeus - 1);
        }

        int vaakaviivoja = ristinolla.getPelilauta().getKorkeus() - 1;
        for (int i = 1; i <= vaakaviivoja; i++) {
            int y = RUUDUN_KOKO * i - 1;
            g.drawLine(0, y, leveys - 1, y);
        }
    }

    private void piirraPelimerkit(Graphics g) {
        Pelilauta pl = ristinolla.getPelilauta();
        for (int y = pl.getKorkeus() - 1; y >= 0; y--) {
            for (int x = pl.getLeveys() - 1; x >= 0; x--) {
                if (pl.onRisti(y, x)) {
                    g.setColor(Color.BLUE);
                    g.drawLine(x * RUUDUN_KOKO, y * RUUDUN_KOKO, (x + 1) * RUUDUN_KOKO, (y + 1) * RUUDUN_KOKO);
                    g.drawLine(x * RUUDUN_KOKO, (y + 1) * RUUDUN_KOKO, (x + 1) * RUUDUN_KOKO, y * RUUDUN_KOKO);
                } else if (pl.onNolla(y, x)) {
                    g.setColor(Color.RED);
                    g.drawOval(x * RUUDUN_KOKO + 1, y * RUUDUN_KOKO + 1, RUUDUN_KOKO - 4, RUUDUN_KOKO - 4);
                }
            }
        }
    }

}
