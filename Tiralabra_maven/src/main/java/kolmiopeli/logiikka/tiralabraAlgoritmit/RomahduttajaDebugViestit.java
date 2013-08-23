package kolmiopeli.logiikka.tiralabraAlgoritmit;

import java.util.Collection;
import kolmiopeli.domain.Koordinaatti;

/**
 *
 * @author Eemi
 */
class RomahduttajaDebugViestit {

    private boolean onkoPaalla;

    public RomahduttajaDebugViestit(boolean debugViestitPaalla) {
        this.onkoPaalla = debugViestitPaalla;
    }

    public void setOnkoPaalla(boolean onkoPaalla) {
        this.onkoPaalla = onkoPaalla;
    }

    void kolmioRomahtaa(int tyhjaRivi, int tyhjaSarake, int romahtavanRivi, int romahtavanSarake) {
        if (onkoPaalla) {
            System.out.println("{" + tyhjaRivi + ", " + tyhjaSarake + "} <-- {" + romahtavanRivi + ", " + romahtavanSarake + "}");
        }
    }

    void viivoja() {
        if (onkoPaalla) {
            System.out.println("--------------------");
        }
    }

    void tutkitaanJuurta(Koordinaatti taytettavaTyhja) {
        if (onkoPaalla) {
            System.out.println("Tutkitaan: " + taytettavaTyhja);
        }
    }

    void siirrettiinListanPohjalle(Koordinaatti siirraViimeiseksi) {
        if (onkoPaalla) {
            System.out.println("Siirrettiin viimeiseksi: " + siirraViimeiseksi);
        }
    }

    void romahdusAlkaa() {
        if (onkoPaalla) {
            System.out.println("\n-ROMAHDUS ALKAA-");
        }
    }

    void romahdusLoppuu() {
        if (onkoPaalla) {
            System.out.println("-ROMAHDUS LOPPUI-\n");
        }
    }

    void romautettujenJoukkoOn(Collection muutettujenJoukko) {
        if (onkoPaalla) {
            System.out.println("Romahduttaja liikutti koordinaatteja:\n" + muutettujenJoukko + "\n");
            romahdusLoppuu();
        }
    }
}
