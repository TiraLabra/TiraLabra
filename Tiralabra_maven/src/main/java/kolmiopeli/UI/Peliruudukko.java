package kolmiopeli.UI;

import kolmiopeli.UI.napit.AlaspainKolmioNappi;
import kolmiopeli.UI.napit.YlospainKolmioNappi;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.logiikka.Siirrot;

/**
 * Luo graafisen peliruudukon luomalla kolmiot KolmioNapeiksi
 *
 * @author Eemi
 */
public class Peliruudukko extends JPanel {

    private Kolmio[][] peliruudukko;
    private Kolmio valittuKolmio;
    private PeliFrame peliFrame;

    public Peliruudukko(PeliFrame peliFrame) {
        this.peliFrame = peliFrame;
        this.peliruudukko = peliFrame.getPeliruudukko().getRuudukko();

        int ruudukonKorkeus = this.peliruudukko.length;
        int ruudukonLeveys = this.peliruudukko[0].length;
        GridLayout ruudukonLayout = new GridLayout(ruudukonKorkeus, ruudukonLeveys, -30, 0);
        this.setLayout(ruudukonLayout);
        this.taytaKolmiot(ruudukonKorkeus, ruudukonLeveys);
    }

    public Kolmio getValittuKolmio() {
        return valittuKolmio;
    }

    public PeliFrame getPeliFrame() {
        return peliFrame;
    }

    public Kolmio[][] getPeliruudukko() {
        return peliruudukko;
    }

    public void setValittuKolmio(Kolmio valittuKolmio) {
        int edellinenSijainti;
        int monesko;
        if (this.valittuKolmio != null) {
            edellinenSijainti = this.valittuKolmio.getSijaintiRivi() * this.peliruudukko[0].length + this.valittuKolmio.getSijaintiSarake();
            this.getComponent(edellinenSijainti).setBackground(this.valittuKolmio.getKolmionVari().brighter());
            if (this.valittuKolmio == valittuKolmio) {
                this.valittuKolmio = null;
                return;
            }
        }
        this.valittuKolmio = valittuKolmio;
        if (valittuKolmio != null) {

            monesko = valittuKolmio.getSijaintiRivi() * this.peliruudukko[0].length + valittuKolmio.getSijaintiSarake();
            this.getComponent(monesko).setBackground(valittuKolmio.getKolmionVari().darker());
        }


    }

    public Siirrot getSiirrot() {
        return this.peliFrame.getSiirrot();
    }

    public void taytaKolmiot(int korkeus, int leveys) {
        this.removeAll();
        for (int rivi = 0; rivi < korkeus; rivi++) {
            for (int sarake = 0; sarake < leveys; sarake++) {
                Kolmio piirrettava = this.peliruudukko[rivi][sarake];
                
                if (piirrettava == null) {
                    this.add(new JLabel("lol"));
                    continue;
                }
                
                
                JButton kolmioNappina;
                if (piirrettava.osoittaakoKolmioYlospain()) {
                    kolmioNappina = new YlospainKolmioNappi(piirrettava, this);
                } else {
                    kolmioNappina = new AlaspainKolmioNappi(piirrettava, this);
                }
                kolmioNappina.addActionListener(new TapahtumaKuuntelija(this));
                this.add(kolmioNappina);

                this.repaint();
                this.revalidate();
            }
        }
    }
}
