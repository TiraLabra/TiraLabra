/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kolmiopeli.logiikka.algoritmit;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Koordinaatti;

/**
 *
 * @author Eemi
 */
class KomboEtsijaDebugViestit {

    KomboEtsijaDebugViestit() {
    }

    void tuplaviiva() {
        System.out.println("");
        System.out.println("==========================================");
    }

    void loydetytKombot(HashSet<Koordinaatti> kaikkiTuhoutuvat) {
        System.out.println("*************************************");
        if (kaikkiTuhoutuvat.isEmpty()) {
            System.out.println("EI KOMBOJA");
        } else {
            System.out.println("KOMBOJA LOYTYI: ");
            System.out.println(kaikkiTuhoutuvat);

        }
    }

    void tahtia() {
        System.out.println("");
        System.out.println("***********tutkitaanJuurta***********");
    }

    void tutkitaanJuurta(Koordinaatti root) {
        System.out.println(root.toString());
    }

    void juuriOnJoTuhoutumassa(Koordinaatti root) {
        System.out.println(root.toString() + " - Juuri oli jo palautettavassa koordinaattijoukossa");
    }

    void juurenMukanaTuhoutuvat(Koordinaatti root, ArrayList<Koordinaatti> rootinVariset) {
        System.out.println(root.toString() + " - Juuri ja ymparilta loytyneita samanvarisia: " + rootinVariset.size());
    }

    void juurenVarisiaVain(Koordinaatti root, ArrayList<Koordinaatti> rootinVariset) {
        System.out.println(root.toString() + " - Samanvarisia loytyi vain: " + rootinVariset.size());
    }

    void valiviiva() {
        System.out.println("--------------------");
    }

    void tutkitaanKoordinaattia(Koordinaatti tutkittava, Color tVari) {
        System.out.println(tutkittava.toString() + " - Tutkitaan while-loopissa, vari " + tVari);
    }

    void tutkittavaAlareunassa(Koordinaatti tutkittava) {
        System.out.println(tutkittava.toString() + " - Tutkittava on alareunassa");
    }

    void tutkittavaYlareunassa(Koordinaatti tutkittava) {
        System.out.println(tutkittava.toString() + " - Tutkittava on ylareunassa");
    }

    void tutkittavaVasemmassaReunassa(Koordinaatti tutkittava) {
        System.out.println(tutkittava.toString() + " - Tutkittava on vasemmassa reunassa");
    }

    void tutkittavaOikeassaReunassa(Koordinaatti tutkittava) {
        System.out.println(tutkittava.toString() + " - Tutkittava on oikeassa reunassa");
    }

    void loytyiSamanvarinenTutkimaton(Koordinaatti tutkittava, Kolmio viereinen) {
        if (tutkittava.getRivi() + 1 == viereinen.getSijaintiRivi()) {
            System.out.println(">>> " + tutkittava.toString() + viereinen.getKoordinaatti().toString() + " - Alapuolelta LOYTYI samanvarinen, vari " + viereinen.getKolmionVari());
        } else if (tutkittava.getRivi() - 1 == viereinen.getSijaintiRivi()) {
            System.out.println(">>> " + tutkittava.toString() + viereinen.getKoordinaatti().toString() + " - Ylapuolelta LOYTYI samanvarinen, vari " + viereinen.getKolmionVari());
        } else if (tutkittava.getSarake() + 1 == viereinen.getSijaintiSarake()) {
            System.out.println(">>> " + tutkittava.toString() + viereinen.getKoordinaatti().toString() + " - Oikealta LOYTYI samanvarinen, vari " + viereinen.getKolmionVari());
        } else if (tutkittava.getSarake() - 1 == viereinen.getSijaintiSarake()) {
            System.out.println(">>> " + tutkittava.toString() + viereinen.getKoordinaatti().toString() + " - Vasemmalta LOYTYI samanvarinen, vari " + viereinen.getKolmionVari());
        } else {
            System.out.println("JOTAIN PIELESSA");
        }
    }

    void viereinenErivarinen(Koordinaatti tutkittava, Kolmio viereinen) {
        System.out.println(tutkittava + viereinen.getKoordinaatti().toString() + "erivarisia");
    }

    void viereinenKaytyLapi(Koordinaatti tutkittava, Kolmio viereinen) {
        System.out.println(tutkittava + viereinen.getKoordinaatti().toString() + "kayty lapi");
    }
}
