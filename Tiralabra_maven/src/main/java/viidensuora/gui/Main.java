package viidensuora.gui;

import javax.swing.SwingUtilities;
import viidensuora.ai.Tekoaly;
import viidensuora.logiikka.Koordinaatti;
import viidensuora.logiikka.Pelimerkki;
import viidensuora.logiikka.Ristinolla;

public class Main {

    public static void main(String[] args) {
        gui();
    }

    /**
     * Luo graafinen käyttis.
     */
    private static void gui() {
        Kayttoliittyma kali = new Kayttoliittyma();
        SwingUtilities.invokeLater(kali);
    }

    /**
     * Demoa tekstillä.
     */
    private static void teksti() {
        Ristinolla rn = new Ristinolla(8, 8, 5);
        Tekoaly ai = new Tekoaly(rn);
        Pelimerkki voittaja = null;

        while (!rn.lautaTaynna()) {
            Koordinaatti k = ai.etsiParasSiirto(4, rn.onRistinVuoro());
            rn.pelaaVuoro(k.x, k.y);
            System.out.println(rn);
            if (rn.getVoittaja() != null) {
                voittaja = rn.getVoittaja();
                break;
            }
        }

        if (voittaja == null) {
            System.out.println("Taspeli");
        } else if (voittaja == Pelimerkki.RISTI) {
            System.out.println("Risti voitti");
        } else {
            System.out.println("Nolla voitti");
        }
    }

}
