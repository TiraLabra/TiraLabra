package labyrintti.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import labyrintti.Kaynnistys;
import labyrintti.osat.Pohja;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
//    private boolean alustus;
    private Piirtoalusta alusta;
    private Kaynnistys kaynnistys;
    private int sivu;

    public Kayttoliittyma(Piirtoalusta alusta, Kaynnistys kaynnistys, int sivu) {
//        alustus = true;
        this.alusta = alusta;
        this.kaynnistys = kaynnistys;
        this.sivu = sivu;
    }

    @Override
    public void run() {
        Pohja p = kaynnistys.getPohja();
        frame = new JFrame("Labyrintti");
        frame.setPreferredSize(new Dimension((p.getLeveys() + 1) * sivu, (p.getKorkeus() + 1) * sivu));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.add(alusta);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Piirtoalusta getPiirtoalusta() {
        return alusta;
    }

}
