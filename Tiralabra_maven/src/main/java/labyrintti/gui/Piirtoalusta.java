package labyrintti.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import labyrintti.Kaynnistys;
import labyrintti.osat.Pohja;
import labyrintti.osat.Ruutu;

/**
 * Huolehtii ohjelman grafiikasta.
 *
 * @author heidvill
 */
public class Piirtoalusta extends JPanel {

    /**
     * Käynnistys-luokasta saa pohjan ja etsijän.
     */
    private Kaynnistys kaynnistys;
    /**
     * Ruudun sivun pituus pikseleinä.
     */
    private int sivu;
    /**
     * Pohjakartta.
     */
    private Pohja pohja;

    /**
     *
     * @param kaynnistys
     * @param sivu
     */
    public Piirtoalusta(Kaynnistys kaynnistys, int sivu) {
        this.kaynnistys = kaynnistys;
        this.sivu = sivu;
        pohja = kaynnistys.getPohja();
    }

    /**
     * Piirtää kartan.
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < pohja.getKorkeus(); i++) {
            for (int j = 0; j < pohja.getLeveys(); j++) {
                piirraRuutu(g, pohja.getRuutu(i, j));
                piirraKaydyt(g, i, j);
                piirraReitti(g, i, j);
            }
        }
    }

    /**
     * Määrittää ruudulle värin sen arvon perusteella.
     *
     * @param g Graphics
     * @param r Ruutu
     */
    private void piirraRuutu(Graphics g, Ruutu r) {
        if (r.getArvo() == 9) {
            g.setColor(Color.BLUE);
        } else if (lahtoTaiMaali(r.getX(), r.getY())) {
            g.setColor(Color.RED);
        } else {
            g.setColor(new Color(r.getArvo() * 20, 255 - r.getArvo() * 30, 0));
        }
        g.fill3DRect(r.getY() * sivu, r.getX() * sivu, sivu, sivu, true);
    }

    /**
     * Piirtää reitin kartalle ruutujen päälle.
     *
     * @param g Graphics
     * @param x korkeus
     * @param y leveys
     */
    private void piirraReitti(Graphics g, int x, int y) {
        if (kaynnistys.getEtsija().getReitti() != null && kaynnistys.getEtsija().onkoRuutuReitilla(x, y)) {
            g.setColor(Color.RED);
            g.fillOval(y * sivu + sivu / 2, x * sivu + sivu / 2, sivu / 4, sivu / 4);
        }
    }

    /**
     * Piirtää ruudun, jossa on pitänyt käydä reitin löytymiseksi.
     *
     * @param g
     * @param x ruudun rivi
     * @param y ruudun sarake
     */
    private void piirraKaydyt(Graphics g, int x, int y) {
        if (pohja.getRuutu(x, y).onkoKayty()) {
            g.setColor(Color.YELLOW);
            g.fillOval(y * sivu + sivu / 2, x * sivu + sivu / 2, sivu / 4, sivu / 4);
        }
    }

    /**
     * Tarkistaa, onko annetuissa koordinaateissa oleva ruutu lähtö tai maali.
     *
     * @param i
     * @param j
     * @return true, jos ruutu on lähtö tai maali, muuten false.
     */
    private boolean lahtoTaiMaali(int i, int j) {
        if (i == pohja.getLahtoX() && j == pohja.getLahtoY()) {
            return true;
        }
        if (i == pohja.getMaaliX() && j == pohja.getMaaliY()) {
            return true;
        }
        return false;
    }

    /**
     * Päivittää piirretyn kuvan.
     */
    public void paivita() {
        repaint();
    }
}
