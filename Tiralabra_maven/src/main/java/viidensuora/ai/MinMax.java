package viidensuora.ai;

import viidensuora.peli.Koordinaatti;
import viidensuora.peli.Peli;
import viidensuora.peli.Pelilauta;

/**
 * Tekoälylle annettava Etsintämetodi, jota käytetään etsimään paras siirto.
 *
 * @author juha
 */
public class MinMax implements Etsintametodi {

    /**
     * Pelilogiikka
     */
    private Peli peli;

    /**
     * Pelilauta, josta siirrot etsitään.
     */
    private Pelilauta pelilauta;

    public void setPeli(Peli peli) {
        this.peli = peli;
        this.pelilauta = peli.getPelilauta();
    }

    /**
     * Etsii parhaan Ristin siirron. ToDo
     *
     * @return Parhaan siirron koordinaatti.
     */
    public Koordinaatti etsiParasRistinSiirto() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Etsii parhaan Nollan siirron. ToDo
     *
     * @return Parhaan siirron koordinaatti.
     */
    public Koordinaatti etsiParasNollanSiirto() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * MinMax-algoritmin MAX-osa, joka palauttaa pelipuun suurimman arvon. ToDo
     *
     * @param syvyys Kuinka syvältä pelipuusta siirtoa etsitään.
     * @return Paras arvo joka loydettiin Ristille.
     */
    private int maxArvo(int syvyys) {
        if (syvyys == 0) {
            // Todo
        }
        int arvo = Integer.MIN_VALUE;
        for (int i = 0; i < pelilauta.getKorkeus(); i++) {
            for (int j = 0; j < pelilauta.getLeveys(); j++) {
                if (!pelilauta.ruutuVapaa(i, j)) {
                    continue;
                }
                pelilauta.asetaRisti(i, j);
                arvo = Math.max(arvo, minArvo(syvyys - 1));
                pelilauta.poistaMerkki(i, j);
            }
        }
        return arvo;
    }

    /**
     * MinMax-algoritmin MIN-osa, joka palauttaa pelipuun pienimmän arvon. ToDo
     *
     * @param Kuinka syvältä pelipuusta siirtoa etsitään.
     * @return Paras arvo joka loydettiin Nollalle.
     */
    private int minArvo(int syvyys) {
        if (syvyys == 0) {
            // ToDo
        }
        int arvo = Integer.MAX_VALUE;
        for (int i = 0; i < pelilauta.getKorkeus(); i++) {
            for (int j = 0; j < pelilauta.getLeveys(); j++) {
                if (!pelilauta.ruutuVapaa(i, j)) {
                    continue;
                }
                pelilauta.asetaNolla(i, j);
                arvo = Math.min(arvo, maxArvo(syvyys - 1));
                pelilauta.poistaMerkki(i, j);
            }
        }
        return arvo;
    }

    /*
     public int minmax(int syvyys, boolean maksimoi, Pelimerkki edellinen) {
     int pisinSuora = peli.pisinSuora(edellinen);
     int parasArvo = maksimoi ? Integer.MIN_VALUE : Integer.MAX_VALUE;
     if (pisinSuora >= peli.getVoittavaPituus()) {
     return parasArvo;
     } else if (pelilauta.taynna()) {
     return 0;
     } else if (syvyys == 0) {
     return heuristiikka();
     }
     for (int i = 0; i < pelilauta.getKorkeus(); i++) {
     for (int j = 0; j < pelilauta.getLeveys(); j++) {
     if (pelilauta.ruutuVapaa(i, j)) {
     Pelimerkki uusi = maksimoi
     ? new Risti(i, j)
     : new Nolla(i, j);
     pelilauta.lisaaMerkki(uusi);
     int arvo = minmax(syvyys - 1, !maksimoi, uusi);
     parasArvo = maksimoi ? Math.max(parasArvo, arvo) : Math.min(parasArvo, arvo);
     pelilauta.poistaMerkki(i, j);
     }
     }
     }
     return parasArvo;
     }
     */
}
