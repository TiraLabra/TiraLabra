package labyrintti.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import labyrintti.sovellus.Etsija;

/**
 * Huolehtii napinpainalluksen toiminnoista.
 *
 * @author heidvill
 */
public class NapinKuuntelija implements ActionListener {

    private Etsija e;
    private Piirtoalusta alusta;

    /**
     *
     * @param e Etsijä
     * @param a Piirtoalusta
     */
    public NapinKuuntelija(Etsija e, Piirtoalusta a) {
        this.e = e;
        alusta = a;
    }

    /**
     * Napinpainalluksesta piirtää lyhimmän reitin.
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        e.aStar();
        e.tallennaReittiTaulukkoon();
        alusta.paivita();
    }
}
