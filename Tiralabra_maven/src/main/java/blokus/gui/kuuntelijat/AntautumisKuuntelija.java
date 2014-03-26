package blokus.gui.kuuntelijat;

import blokus.conf.GlobaalitMuuttujat;
import blokus.gui.Kayttoliittyma;
import blokus.logiikka.Blokus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Kuuntelee antautumis nappia ja antauttaa pelaajan tarvittaessa
 * @author Simo Auvinen
 */
public class AntautumisKuuntelija implements ActionListener {

    Blokus blokus;
    Kayttoliittyma liittyma;

    /**
     *
     * @param blokus
     * @param liittyma
     */
    public AntautumisKuuntelija(Blokus blokus, Kayttoliittyma liittyma) {
        this.blokus = blokus;
        this.liittyma = liittyma;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        liittyma.lisaaTeksti(" " + blokus.getIDVariTekstina(blokus.getVuorossa().getPelaajantID()) + " pelaaja antautui!");
        blokus.lopetaVuoro(GlobaalitMuuttujat.OHITA_VUORO, GlobaalitMuuttujat.ANTAUDU);
        liittyma.vuoroVaihtuu();
    }
}
