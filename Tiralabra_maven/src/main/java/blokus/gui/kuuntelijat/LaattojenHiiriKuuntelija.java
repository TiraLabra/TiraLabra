package blokus.gui.kuuntelijat;

import blokus.gui.Kayttoliittyma;
import blokus.gui.LaattaValitsin;
import blokus.logiikka.Blokus;
import blokus.logiikka.Pelaaja;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


/**
 * Kuuntelee hiirtä, kun hiiri on laatta valitsimen yläpuolella. Havaitsee 
 * laattojen vaihtamis klikkaukset ja vaihtaa valittuna olevaa laattaa.
 * 
 * @author Simo Auvinen
 */

public class LaattojenHiiriKuuntelija implements MouseListener, MouseMotionListener {

    Blokus blokus;
    Point hiirenSijainti;
    LaattaValitsin valitsin;
    Kayttoliittyma liittyma;

    /**
     *
     * @param blokus
     * @param valitsin
     * @param liittyma
     */
    public LaattojenHiiriKuuntelija(Blokus blokus, LaattaValitsin valitsin, Kayttoliittyma liittyma) {
        this.blokus = blokus;
        this.valitsin = valitsin;
        this.liittyma = liittyma;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Pelaaja vuorossa = blokus.getVuorossa();
        vuorossa.vaihdaValittuaLaattaa(hiirenSijainti.x, hiirenSijainti.y);
        liittyma.paivitaLaatat();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        hiirenSijainti = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = valitsin.getSijainti(e.getPoint());
        if (!p.equals(hiirenSijainti)) {
            hiirenSijainti = p;


        }

    }
    

}
