package viidensuora.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import viidensuora.ai.MinMax;
import viidensuora.ai.Tekoaly;
import viidensuora.peli.Koordinaatti;
import viidensuora.peli.Peli;

/**
 * Edistää peliä hiiren klikkauksella.
 * 
 * @author juha
 */
public class Hiirikuuntelija implements MouseListener {

    //private final Cursor X_KURSORI = new Cursor(Cursor.CROSSHAIR_CURSOR);
    //private final Cursor O_KURSORI = new Cursor(Cursor.HAND_CURSOR);
    //private final Cursor DEF_KURSORI = new Cursor(Cursor.DEFAULT_CURSOR);
    private final Peli peli;
    private final Peliruutu peliruutu;
    private final Tekoaly ai;

    public Hiirikuuntelija(Peli peli, Peliruutu peliruutu) {
        this.peli = peli;
        this.peliruutu = peliruutu;
        this.ai = new Tekoaly(peli, new MinMax(), 9);
    }

    /**
     * Edistää peliä hiiren klikkauksella. Jos on ristin vuoro, koordinaatti
     * lasketaan hiiren x- ja y-arvoista. Jos nollan vuoro, tekoäly laskee
     * seuraavan siirron. (Ei jääne tämmöiseksi, purkkakoodia..)
     * 
     * @param e 
     */
    public void mouseClicked(MouseEvent e) {
        if (peli.onRistinVuoro()) {
            int x = e.getX() / Peliruutu.RUUDUN_KOKO;
            int y = e.getY() / Peliruutu.RUUDUN_KOKO;
            peli.lisaaMerkki(y, x);
            peliruutu.repaint();
        } else if (!ai.miettii()) {
            Koordinaatti k = ai.etsiParasNollanSiirto();
            peli.lisaaMerkki(k.getI(), k.getJ());
            peliruutu.repaint();
        }
        if (peli.getVoittaja() != 0) {
            String voittaja = peli.getVoittaja() == Peli.RISTI ? "Risti" : "Nolla";
            JOptionPane.showMessageDialog(null, voittaja + " voitti.");
            System.exit(0);
        } else if (peli.getPelilauta().taynna()) {
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
