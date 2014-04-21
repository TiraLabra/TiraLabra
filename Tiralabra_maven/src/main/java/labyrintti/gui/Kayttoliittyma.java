package labyrintti.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import labyrintti.Kaynnistys;
import labyrintti.osat.Pohja;

/**
 * Sovelluksen käyttöliittymä.
 * @author heidvill
 */
public class Kayttoliittyma implements Runnable {

    /**
     * Käyttöliittymän ikkuna.
     */
    private JFrame frame;
    /**
     * Piirtää ikkunaan kuvaa.
     */
    private Piirtoalusta alusta;
    /**
     * Käynnistys-luokasta saa pohjan ja etsijän.
     */
    private Kaynnistys kaynnistys;
    /**
     * Ruudun sivun pituus pikseleinä.
     */
    private int sivu;

    /**
     * 
     * @param kaynnistys
     * @param sivu 
     */
    public Kayttoliittyma(Kaynnistys kaynnistys, int sivu) {
        this.alusta = new Piirtoalusta(kaynnistys, sivu);
        this.kaynnistys = kaynnistys;
        this.sivu = sivu;
//        kaynnistys.getEtsija().setPiirtoalusta(alusta);
    }

    /**
     * Määrittää ikkunan koon pohjakartan leveyden ja korkeuden perusteella.
     */
    @Override
    public void run() {
        Pohja p = kaynnistys.getPohja();
        frame = new JFrame("Labyrintti");
        frame.setPreferredSize(new Dimension((p.getLeveys() + 1) * (sivu+2) + 80, (p.getKorkeus() + 1) * (sivu+2)));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Liittää ikkunaan tarvittavat komponentit.
     * @param container 
     */
    private void luoKomponentit(Container container) {
        container.add(alusta);
        JButton nappi = new JButton("Laske reitti");
        nappi.addActionListener(new NapinKuuntelija(kaynnistys.getEtsija(), alusta));
        container.add(nappi, BorderLayout.EAST);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Piirtoalusta getPiirtoalusta() {
        return alusta;
    }
}
