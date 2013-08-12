/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kolmiopeli.logiikka.algoritmit;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import kolmiopeli.domain.Koordinaatti;

/**
 *
 * @author Eemi
 */
class KomboEtsijaDebugViestit {

    KomboEtsijaDebugViestit() {
    }

    void tuplaviiva() {
        System.out.println("==========================================");
    }

    void loydetytKombot(HashSet<Koordinaatti> kaikkiTuhoutuvat) {
        if (kaikkiTuhoutuvat.isEmpty()) {
            System.out.println("EI KOMBOJA");
        } else {
            System.out.println("Loydetyt kombot: ");
            System.out.println(kaikkiTuhoutuvat);
        }
    }

    void viiva() {
        System.out.println("--------------------------------");
    }

    void tutkitaanJuurta(Koordinaatti root) {
        System.out.println(root.toString() + " - Lahdetaan tutkimaan juurta.");
    }

    void juuriOnJoTuhoutumassa(Koordinaatti root) {
        System.out.println(root.toString() + " - Juuri oli jo palautettavassa koordinaattijoukossa");
    }

    void juurenMukanaTuhoutuvat(Koordinaatti root, ArrayList<Koordinaatti> rootinVariset) {
        System.out.println(root.toString() + " - Juuren ymparilta loytyi samanvarisia: " + rootinVariset.size());
    }

    void juurenVarisiaVain(Koordinaatti root, ArrayList<Koordinaatti> rootinVariset) {
        System.out.println(root.toString() + " - Samanvarisia loytyi vain: " + rootinVariset.size());
    }

    void valiTahtia() {
        System.out.println("**********************");
    }

    void tutkitaanKoordinaattia(Koordinaatti tutkittava, Color tVari) {
        System.out.println(">>> " + tutkittava.toString() + ">>> - Tutkitaan while-loopissa, vari " + tVari);
    }
}
