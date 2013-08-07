package kolmiopeli.UI;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import kolmiopeli.logiikka.Pisteenlaskija;

/**
 * Pelitilanteen aikana nakyva infopaneeli josta nakyy pisteet ja jaljella olevien siirtojen maara
 * @author Eemi
 */
public class InfoPaneeli extends JPanel {

    private PeliFrame peliFrame;
    private Pisteenlaskija pisteenlaskija;
    private int siirtoja;

    public InfoPaneeli(PeliFrame peliFrame) {
        this.peliFrame = peliFrame;
        this.pisteenlaskija = peliFrame.getSiirrot().getPisteenlaskija();
        this.setLayout(new GridLayout(1, 2));
        this.add(new JLabel("Pisteet: " + getPisteet()));
        this.siirtoja = this.peliFrame.getEtsija().etsiKaikkiPistesiirrot();
        this.add(new JLabel("Siirtoja: " + this.siirtoja));

    }

    public int getSiirtoja() {
        return this.siirtoja;
    }

    
    
    public int getPisteet() {
        return this.pisteenlaskija.getPisteet();
    }

    private int laskeSiirrot() {
        this.siirtoja = this.peliFrame.getEtsija().etsiKaikkiPistesiirrot();
        return this.siirtoja;
    }

    void paivita() {
        this.removeAll();
        this.add(new JLabel("Pisteet: " + getPisteet()));
        this.add(new JLabel("Siirtoja: " + laskeSiirrot()));
        this.repaint();
        this.revalidate();
    }

    void nollaaPisteet() {
        this.pisteenlaskija.nollaaPisteet();
    }
}
