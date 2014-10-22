package viidensuora.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import viidensuora.ai.Etsintametodi;
import viidensuora.logiikka.Ristinolla;

/**
 * Pelin pääikkuna.
 *
 * @author juha
 */
public class Kayttoliittyma implements Runnable, Observer {

    private JFrame frame;

    /**
     * Paneeli, johon pelitilanne piirretään.
     */
    private Peliruutu peliruutu;

    /**
     * Paneeli johon tulostetaan Ristin käyttämän tekoälyn tiedot.
     */
    private InfoPaneeli ristinInfo;

    /**
     * Paneeli johon tulostetaan Nollan käyttämän tekoälyn tiedot.
     */
    private InfoPaneeli nollanInfo;

    public Peliruutu getPeliruutu() {
        return peliruutu;
    }

    public InfoPaneeli getRistinInfo() {
        return ristinInfo;
    }

    public InfoPaneeli getNollanInfo() {
        return nollanInfo;
    }

    /**
     * Käynnistää GUI:n luomalla uuden Asetusruudun.
     */
    public void run() {
        frame = new JFrame("Ristinolla");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Asetusruutu asetusruutu = new Asetusruutu(this);
        frame.getContentPane().add(asetusruutu);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Tyhjentää Asetusruudun pääikkunasta ja luo uuden Peliruudun sekä
     * mahdolliset infopaneelit.
     *
     * @param ristinolla Uusi Ristinolla.
     * @param ristinAI Ristin käyttämä Etsintämetodi. NULL jos ihminen.
     * @param nollanAI Nollan käyttämä Etsintämetodi. NULL jos ihminen.
     * @param dataikkuna TRUE jos halutaan näyttää tekoälyn dataikkunat, FALSE
     * jos ei.
     */
    public void luoPeliruutu(Ristinolla ristinolla, Etsintametodi ristinAI,
            Etsintametodi nollanAI, boolean dataikkuna) {
        JPanel sisalto = new JPanel();
        sisalto.setLayout(new BorderLayout());

        // Kuunnellaan Ristinollan ilmoituksia pelitilanteen muutoksista.
        ristinolla.addObserver(this);

        peliruutu = new Peliruutu(ristinolla);
        Kontrolleri edistaja = new Kontrolleri(this, ristinolla, ristinAI, nollanAI);
        peliruutu.addMouseListener(edistaja);

        JPanel keski = new JPanel();
        keski.add(peliruutu);

        sisalto.add(keski, BorderLayout.CENTER);

        // Luodaan infopaneelit mikäli tarpeen
        if (dataikkuna && ristinAI != null) {
            ristinInfo = new InfoPaneeli("Ristin tekoäly\n", Color.BLUE);
            sisalto.add(ristinInfo, BorderLayout.WEST);
        }

        if (dataikkuna && nollanAI != null) {
            nollanInfo = new InfoPaneeli("Nollan tekoäly\n", Color.RED);
            sisalto.add(nollanInfo, BorderLayout.EAST);
        }

        frame.getContentPane().removeAll();
        frame.setContentPane(sisalto);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Piirretään ikkunat kun Ristinolla ilmoittaa uuden siirron tapahtyneen.
     *
     * @param arg0
     * @param arg1
     */
    public void update(Observable arg0, Object arg1) {
        frame.repaint();
    }

}
