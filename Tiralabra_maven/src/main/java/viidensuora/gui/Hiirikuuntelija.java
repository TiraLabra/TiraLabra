package viidensuora.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import viidensuora.ai.Tekoaly;
import viidensuora.logiikka.Koordinaatti;
import viidensuora.logiikka.Pelimerkki;
import viidensuora.logiikka.Ristinolla;

/**
 * Peliä edistetään klikkaamalla peliä.. ToDo
 *
 * @author juha
 */
public class Hiirikuuntelija implements MouseListener {
//private final Cursor X_KURSORI = new Cursor(Cursor.CROSSHAIR_CURSOR);
//private final Cursor O_KURSORI = new Cursor(Cursor.HAND_CURSOR);
//private final Cursor DEF_KURSORI = new Cursor(Cursor.DEFAULT_CURSOR);

    private final Ristinolla ristinolla;
    private final Peliruutu peliruutu;
    private final Tekoaly ai;

    public Hiirikuuntelija(Ristinolla peli, Peliruutu peliruutu) {
        this.ristinolla = peli;
        this.peliruutu = peliruutu;
        this.ai = new Tekoaly(peli);
    }

    /**
     * Edistää peliä hiiren klikkauksella. Jos on ristin vuoro, koordinaatti
     * lasketaan hiiren x- ja y-arvoista. Jos nollan vuoro, tekoäly laskee
     * seuraavan siirron. (Ei jääne tämmöiseksi, purkkakoodia..)
     *
     * @param e
     */
    public void mouseClicked(MouseEvent e) {
        if (ristinolla.onRistinVuoro()) {
            int x = e.getX() / Peliruutu.RUUDUN_KOKO;
            int y = e.getY() / Peliruutu.RUUDUN_KOKO;
            ristinolla.pelaaVuoro(x, y);
            peliruutu.repaint();
        } else if (!ai.miettii()) {
            Koordinaatti k = ai.etsiParasSiirto(4, false);
            ristinolla.pelaaVuoro(k.x, k.y);
            peliruutu.repaint();
        }
        if (ristinolla.getVoittaja() != null) {
            String voittaja = ristinolla.getVoittaja() == Pelimerkki.RISTI ? "Risti" : "Nolla";
            JOptionPane.showMessageDialog(null, voittaja + " voitti.");
            System.exit(0);
        } else if (ristinolla.lautaTaynna()) {
            JOptionPane.showMessageDialog(null, "Tasapeli.");
            System.exit(0);
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
//peliruutu.setCursor(peli.onRistinVuoro() ? X_KURSORI : O_KURSORI);
    }

    public void mouseExited(MouseEvent e) {
//peliruutu.setCursor(DEF_KURSORI);
    }
}
