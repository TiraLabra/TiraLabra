package viidensuora.ai;

import viidensuora.peli.Koordinaatti;
import viidensuora.peli.Peli;
import viidensuora.peli.Pelilauta;

/**
 * Tekoälylle annettava Etsintämetodi, jota käytetään etsimään paras siirto.
 *
 * @author juha
 */
public class AlphaBetaKarsinta implements Etsintametodi {

    /**
     * Pelilogiikka
     */
    private Peli peli;

    /**
     * Pelilauta, josta siirrot etsitään.
     */
    private Pelilauta pelilauta;

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

    public void setPeli(Peli peli) {
        this.peli = peli;
        this.pelilauta = peli.getPelilauta();
    }

    /**
     * AlphaBeta-karsinnan MAX-osa, joka palauttaa pelipuun suurimman arvon.
     * ToDo
     *
     * @param syvyys Kuinka syvältä pelipuusta siirtoa etsitään.
     * @return Paras arvo joka loydettiin Ristille.
     */
    private int maxArvo(int syvyys, int a, int b) {
        if (syvyys == 0) {
            //heuristiikka ToDo
        }
        int arvo = Integer.MIN_VALUE;
        for (int i = 0; i < pelilauta.getKorkeus(); i++) {
            for (int j = 0; j < pelilauta.getLeveys(); j++) {
                if (!pelilauta.ruutuVapaa(i, j)) {
                    continue;
                }
                pelilauta.asetaRisti(i, j);
                arvo = Math.max(arvo, minArvo(syvyys - 1, a, b));
                pelilauta.poistaMerkki(i, j);
                if (arvo >= b) {
                    return arvo;
                }
                a = Math.max(a, arvo);

            }
        }
        return arvo;
    }

    /**
     * AlphaBeta-karsinnan MIN-osa, joka palauttaa pelipuun suurimman arvon.
     * ToDo
     *
     * @param syvyys Kuinka syvältä pelipuusta siirtoa etsitään.
     * @return Paras arvo joka loydettiin Ristille.
     */
    private int minArvo(int syvyys, int a, int b) {
        if (syvyys == 0) {

        }
        int arvo = Integer.MAX_VALUE;
        for (int i = 0; i < pelilauta.getKorkeus(); i++) {
            for (int j = 0; j < pelilauta.getLeveys(); j++) {
                if (!pelilauta.ruutuVapaa(i, j)) {
                    continue;
                }
                pelilauta.asetaNolla(i, j);
                arvo = Math.min(arvo, maxArvo(syvyys - 1, a, b));
                pelilauta.poistaMerkki(i, j);
                if (arvo <= a) {
                    return arvo;
                }
                b = Math.min(b, arvo);
            }
        }
        return arvo;
    }

}
