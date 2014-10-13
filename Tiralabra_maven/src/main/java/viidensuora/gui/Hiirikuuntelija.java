package viidensuora.gui;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import viidensuora.ai.AlphaBetaKarsinta;
import viidensuora.ai.Etsintametodi;
import viidensuora.ai.Hakutulos;
import viidensuora.ai.MunEvaluoija;
import viidensuora.logiikka.Pelimerkki;
import viidensuora.logiikka.Ristinolla;

/**
 * Peliä edistetään klikkaamalla peliä.. ToDo
 *
 * @author juha
 */
public class Hiirikuuntelija implements MouseListener {

    /**
     * Kursori, joka näytetään Ristin vuorolla.
     */
    private final Cursor X_KURSORI;

    /**
     * Kursori, joka näytetään Nollan vuorolla.
     */
    private final Cursor O_KURSORI;

    /**
     * Järjestelmän normaali kursori.
     */
    private final Cursor DEF_KURSORI;

    /**
     * Pelitilanne.
     */
    private final Ristinolla ristinolla;

    /**
     * Ruutu johon pelitilanne piirretään.
     */
    private final Peliruutu peliruutu;

    /**
     * Tietokonoeen käytämä tekoäly.
     */
    private final Etsintametodi tekoaly;

    /**
     * Alustaa muuttujat ja luo käytettävät kursorit.
     *
     * @param peli
     * @param peliruutu
     */
    public Hiirikuuntelija(Ristinolla peli, Peliruutu peliruutu) {
        this.ristinolla = peli;
        this.peliruutu = peliruutu;
        this.tekoaly = new AlphaBetaKarsinta(peli, new MunEvaluoija());
        this.X_KURSORI = luoKursori("risti.png", "Risti");
        this.O_KURSORI = luoKursori("nolla.png", "Nolla");
        //this.O_KURSORI = new Cursor(Cursor.WAIT_CURSOR);
        this.DEF_KURSORI = new Cursor(Cursor.DEFAULT_CURSOR);
    }

    /**
     * Luo uuden kursorin.
     *
     * @param kuvatiedosto Kursorissa näyettävän kuvan sijainti.
     * @param nimi Kursorin nimi.
     * @return Luotu kursori.
     */
    private Cursor luoKursori(String kuvatiedosto, String nimi) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image kuva = toolkit.createImage(this.getClass().getResource("/" + kuvatiedosto));
        Point hotSpot = new Point(12, 12);
        return toolkit.createCustomCursor(kuva, hotSpot, nimi);
    }

    /**
     * Edistää peliä hiiren klikkauksella. Jos on ristin vuoro, koordinaatti
     * lasketaan hiiren x- ja y-arvoista. Jos nollan vuoro, tekoäly laskee
     * seuraavan siirron. (Ei jääne tämmöiseksi, purkkakoodia..)
     *
     * @param e
     */
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / Peliruutu.RUUDUN_KOKO;
        int y = e.getY() / Peliruutu.RUUDUN_KOKO;
        if (!ristinolla.ruutuOnTyhja(x, y)) {
            return;
        }
        ristinolla.pelaaVuoro(x, y);
        peliruutu.repaint();
        tarkistaLoppu();
        asetaKursori();

        Hakutulos tulos = tekoaly.etsiNollanSiirto(4);
        System.out.println(tulos);
        x = tulos.parasSiirto.x;
        y = tulos.parasSiirto.y;
        ristinolla.pelaaVuoro(x, y);
        peliruutu.repaint();
        tarkistaLoppu();
        asetaKursori();
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Asetetaan pelitilanteeseen sopiva kursori aina, kun hiiri tuodaan
     * peliruudun päälle.
     *
     * @param e
     */
    public void mouseEntered(MouseEvent e) {
        asetaKursori();
    }

    /**
     * Palautetaan normaali kursori kun hiiri viedään ulos peliruudulta.
     *
     * @param e
     */
    public void mouseExited(MouseEvent e) {
        peliruutu.setCursor(DEF_KURSORI);
    }

    /**
     * Tarkistaa loppuiko peli ja näyttää mahdollisen tuloksen dialogina.
     */
    private void tarkistaLoppu() {
        if (ristinolla.getVoittaja() != null) {
            String voittaja = ristinolla.getVoittaja() == Pelimerkki.RISTI ? "Risti" : "Nolla";
            JOptionPane.showMessageDialog(null, voittaja + " voitti.");
            System.exit(0);
        } else if (ristinolla.lautaTaynna()) {
            JOptionPane.showMessageDialog(null, "Tasapeli.");
            System.exit(0);
        }
    }

    /**
     * Asettaa pelilanteeseen sopivan kursorin.
     */
    private void asetaKursori() {
        peliruutu.setCursor(ristinolla.onRistinVuoro() ? X_KURSORI : O_KURSORI);
    }
}
