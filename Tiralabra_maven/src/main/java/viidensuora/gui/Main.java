package viidensuora.gui;

import javax.swing.SwingUtilities;
import viidensuora.ai.AlphaBetaKarsinta;
import viidensuora.ai.Hakutulos;
import viidensuora.ai.MunEvaluoija;
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
     * Testailua tekstillä. Omaksi avuksi, ei jää lopulliseen versioon..
     */
    private static void teksti() {
        Ristinolla rn = new Ristinolla(8, 8, 5);
        AlphaBetaKarsinta ristinAI = new AlphaBetaKarsinta(rn, new MunEvaluoija());
        AlphaBetaKarsinta nollanAI = new AlphaBetaKarsinta(rn, new MunEvaluoija());
        Pelimerkki voittaja = null;
        System.out.println(rn);
        while (!rn.lautaTaynna()) {
            Hakutulos tulos;
            if (rn.onRistinVuoro()) {
                tulos = ristinAI.etsiRistinSiirto(5);
            } else {
                tulos = nollanAI.etsiNollanSiirto(5);
            }
            rn.pelaaVuoro(tulos.parasSiirto.x, tulos.parasSiirto.y);
            System.out.println(rn.toString());
            System.out.println(tulos);
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
