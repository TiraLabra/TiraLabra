package viidensuora.ai;

import java.util.Random;
import viidensuora.peli.Koordinaatti;
import viidensuora.peli.Nolla;
import viidensuora.peli.Peli;
import viidensuora.peli.Pelilauta;
import viidensuora.peli.Pelimerkki;
import viidensuora.peli.Risti;

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
    public Koordinaatti etsiParasRistinSiirto(int syvyys) {
        Siirto paras = maxArvo(syvyys, Integer.MIN_VALUE, Integer.MAX_VALUE, null);
        return new Koordinaatti(paras.getI(), paras.getJ());
    }

    /**
     * Etsii parhaan Nollan siirron. ToDo
     *
     * @return Parhaan siirron koordinaatti.
     */
    public Koordinaatti etsiParasNollanSiirto(int syvyys) {
        Siirto paras = minArvo(syvyys, Integer.MIN_VALUE, Integer.MAX_VALUE, null);
        return new Koordinaatti(paras.getI(), paras.getJ());
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
    private Siirto maxArvo(int syvyys, int a, int b, Pelimerkki viimeisin) {
        if (viimeisin != null && peli.siirtoVoitti(viimeisin)) {
            return new Siirto(Integer.MIN_VALUE, -1, -1);
        } else if (pelilauta.taynna()) {
            return new Siirto(0, -1, -1);
        } else if (syvyys == 0) {
            return new Siirto(evaluoiPelitilanne(), -1, -1);
        }
        Siirto parasSiirto = new Siirto(Integer.MIN_VALUE, -1, -1);
        for (int i = 0; i < pelilauta.getKorkeus(); i++) {
            for (int j = 0; j < pelilauta.getLeveys(); j++) {
                if (!pelilauta.ruutuVapaa(i, j)) {
                    continue;
                }
                Pelimerkki uusi = new Risti(i, j);
                pelilauta.lisaaMerkki(uusi);
                Siirto s = minArvo(syvyys - 1, a, b, uusi);
                pelilauta.poistaMerkki(i, j);
                if (s.getArvo() >= parasSiirto.getArvo()) {
                    parasSiirto = new Siirto(s.getArvo(), i, j);
                }
                if (parasSiirto.getArvo() >= b) {
                    return parasSiirto;
                }
                a = Math.max(a, parasSiirto.getArvo());
            }
        }
        return parasSiirto;
    }

    /**
     * AlphaBeta-karsinnan MIN-osa, joka palauttaa pelipuun suurimman arvon.
     * ToDo
     *
     * @param syvyys Kuinka syvältä pelipuusta siirtoa etsitään.
     * @return Paras arvo joka loydettiin Ristille.
     */
    private Siirto minArvo(int syvyys, int a, int b, Pelimerkki viimeisin) {
        if (viimeisin != null && peli.siirtoVoitti(viimeisin)) {
            return new Siirto(Integer.MAX_VALUE, -1, -1);
        } else if (pelilauta.taynna()) {
            return new Siirto(0, -1, -1);
        } else if (syvyys == 0) {
            return new Siirto(evaluoiPelitilanne(), -1, -1);
        }
        Siirto parasSiirto = new Siirto(Integer.MAX_VALUE, -1, -1);
        for (int i = 0; i < pelilauta.getKorkeus(); i++) {
            for (int j = 0; j < pelilauta.getLeveys(); j++) {
                if (!pelilauta.ruutuVapaa(i, j)) {
                    continue;
                }
                Pelimerkki uusi = new Nolla(i, j);
                pelilauta.lisaaMerkki(uusi);
                Siirto s = maxArvo(syvyys - 1, a, b, uusi);
                pelilauta.poistaMerkki(i, j);
                if (s.getArvo() <= parasSiirto.getArvo()) {
                    parasSiirto = new Siirto(s.getArvo(), i, j);
                }
                if (parasSiirto.getArvo() <= a) {
                    return parasSiirto;
                }
                b = Math.min(b, parasSiirto.getArvo());
            }
        }
        return parasSiirto;
    }

    private int evaluoiPelitilanne() {
        return new Random().nextInt();
    }
}
