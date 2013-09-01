
package kolmiopeli.logiikka.tiralabraAlgoritmit;

import java.awt.Color;
import java.util.Collection;
import java.util.HashSet;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Koordinaatti;
import kolmiopeli.logiikka.tiralabraAlgoritmit.omatTietorakenteet.OmaLinkedList;

/**
 * Kapseloidut debug viestit, joita KomboEtsija lahettaa etsiessaan.
 * 
 */
class KomboEtsijaDebugViestit {

    private boolean onkoPaalla;

    KomboEtsijaDebugViestit(boolean onkoPaalla) {
        this.onkoPaalla = onkoPaalla;
    }

    public void setOnkoPaalla(boolean onkoPaalla) {
        this.onkoPaalla = onkoPaalla;
    }
    
    

    void tuplaviiva() {
        if (onkoPaalla) {
            System.out.println("");
            System.out.println("==========================================");
        }
    }

    void loydetytKombot(HashSet<Koordinaatti> kaikkiTuhoutuvat) {
        if (onkoPaalla) {
            System.out.println("*************************************");
            if (kaikkiTuhoutuvat.isEmpty()) {
                System.out.println("EI KOMBOJA");
            } else {
                System.out.println("KOMBOJA LOYTYI: ");
                System.out.println(kaikkiTuhoutuvat);

            }
        }
    }

    void tahtia() {
        if (onkoPaalla) {
            System.out.println("");
            System.out.println("***********tutkitaanJuurta***********");
        }
    }

    void tutkitaanJuurta(Koordinaatti root) {
        if (onkoPaalla) {
            System.out.println(root.toString());
        }
    }

    void juuriOnJoTuhoutumassa(Koordinaatti root) {
        if (onkoPaalla) {
            System.out.println(root.toString() + " - Juuri oli jo palautettavassa koordinaattijoukossa");
        }
    }

    void juurenMukanaTuhoutuvat(Koordinaatti root, OmaLinkedList<Koordinaatti> rootinVariset) {
        if (onkoPaalla) {
            System.out.println(root.toString() + " - Juuri ja ymparilta loytyneita samanvarisia: " + rootinVariset.size());
        }
    }

    void juurenVarisiaVain(Koordinaatti root, OmaLinkedList<Koordinaatti> rootinVariset) {
        if (onkoPaalla) {
            System.out.println(root.toString() + " - Samanvarisia loytyi vain: " + rootinVariset.size());
        }
    }

    void valiviiva() {
        if (onkoPaalla) {
            System.out.println("--------------------");
        }
    }

    void tutkitaanKoordinaattia(Koordinaatti tutkittava, Color tVari) {
        if (onkoPaalla) {
            System.out.println(tutkittava.toString() + " - Tutkitaan while-loopissa, vari " + tVari);
        }
    }

    void tutkittavaAlareunassa(Koordinaatti tutkittava) {
        if (onkoPaalla) {
            System.out.println(tutkittava.toString() + " - Tutkittava on alareunassa");
        }
    }

    void tutkittavaYlareunassa(Koordinaatti tutkittava) {
        if (onkoPaalla) {
            System.out.println(tutkittava.toString() + " - Tutkittava on ylareunassa");
        }
    }

    void tutkittavaVasemmassaReunassa(Koordinaatti tutkittava) {
        if (onkoPaalla) {
            System.out.println(tutkittava.toString() + " - Tutkittava on vasemmassa reunassa");
        }
    }

    void tutkittavaOikeassaReunassa(Koordinaatti tutkittava) {
        if (onkoPaalla) {
            System.out.println(tutkittava.toString() + " - Tutkittava on oikeassa reunassa");
        }
    }

    void loytyiSamanvarinenTutkimaton(Koordinaatti tutkittava, Kolmio viereinen) {
        if (onkoPaalla) {
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
    }

    void viereinenErivarinen(Koordinaatti tutkittava, Kolmio viereinen) {
        if (onkoPaalla) {
            System.out.println(tutkittava + viereinen.getKoordinaatti().toString() + "erivarisia");
        }
    }

    void viereinenKaytyLapi(Koordinaatti tutkittava, Kolmio viereinen) {
        if (onkoPaalla) {
            System.out.println(tutkittava + viereinen.getKoordinaatti().toString() + "kayty lapi");
        }
    }

    void kayLapiJoukko(Collection juuriArvotut) {
        if (onkoPaalla) {
            System.out.println("Etsija sai parametriksi joukon:\n" + juuriArvotut);
        }
    }
}
