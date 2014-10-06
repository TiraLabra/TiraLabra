package viidensuora.ai;

import viidensuora.logiikka.Koordinaatti;
import viidensuora.logiikka.Ristinolla;

/**
 * Tekoälyn käyttämä etsintämetodi, jolla etsitään paras seuraava siirto.
 * Toiminta perustuu Alpha-Beta karsintaan.
 *
 * @author juha
 */
public class AlphaBetaKarsinta implements Etsintametodi {

    /**
     * Pelitilanne.
     */
    private final Ristinolla ristinolla;

    /**
     * Metodi, jota käytetään evaluoimaan keskeneräinen pelitilanne.
     */
    private final Evaluointimetodi evaluoija;

    /**
     * Apumuuttuja, johon talletetaan parhaan siirron koordinaatti.
     */
    private Koordinaatti parasSiirto;

    /**
     * Kuinka syvältä haku suoritetaan.
     */
    private int hakusyvyys;

    public AlphaBetaKarsinta(Ristinolla rn, Evaluointimetodi e) {
        this.ristinolla = rn;
        this.evaluoija = e;
    }

    /**
     * Etsii parhaan Ristin siirron tietyltä syvyydeltä.
     *
     * @param syvyys Syvyys jolta etsitään
     * @return Parhaan löydetyn siirron koordinaatti
     */
    public Koordinaatti etsiRistinSiirto(int syvyys) {
        parasSiirto = null;
        hakusyvyys = syvyys;
        maxArvo(syvyys, Integer.MIN_VALUE, Integer.MAX_VALUE, null);
        return parasSiirto;
    }

    /**
     * Etsii parhaan Nollan siirron tietyltä syvyydeltä.
     *
     * @param syvyys Syvyys jolta etsitään
     * @return Parhaan löydetyn siirron koordinaatti
     */
    public Koordinaatti etsiNollanSiirto(int syvyys) {
        parasSiirto = null;
        hakusyvyys = syvyys;
        minArvo(syvyys, Integer.MIN_VALUE, Integer.MAX_VALUE, null);
        return parasSiirto;
    }

    /**
     * Alpha-Beta karsinnan MAX-osa, jossa etsitään suurinta arvoa Ristille.
     *
     * @param syvyys syvyys jolta etsitään
     * @param alpha karsinnassa käytettävä alpha-arvo
     * @param beta karsinnassa käytettävä beta-arvo
     * @param siirto edellisen siirron koordinaatti
     * @return suurin löydetty arvo
     */
    private int maxArvo(int syvyys, int alpha, int beta, Koordinaatti siirto) {
        // Edellinen siirto, eli Nolla voitti.
        if (siirto != null && ristinolla.siirtoVoitti(siirto.x, siirto.y)) {
            return Integer.MIN_VALUE + (hakusyvyys - syvyys);
        } else if (ristinolla.lautaTaynna()) {
            return 0;
        } else if (syvyys == 0) {
            return evaluoija.evaluoiPelitilanne(ristinolla);
        }
        muodostaSiirrot:
        for (int y = 0; y < ristinolla.korkeus; y++) {
            for (int x = 0; x < ristinolla.leveys; x++) {
                if (ristinolla.ruutuOnTyhja(x, y)) {
                    ristinolla.lisaaRisti(x, y);
                    int arvo = minArvo(syvyys - 1, alpha, beta, new Koordinaatti(x, y));
                    ristinolla.poistaMerkki(x, y);
                    if (arvo > alpha) {
                        alpha = arvo;
                        if (syvyys == hakusyvyys) {
                            parasSiirto = new Koordinaatti(x, y);
                        }
                    }
                    if (beta <= alpha) {
                        break muodostaSiirrot;
                    }
                }
            }
        }
        return alpha;
    }

    /**
     * Alpha-Beta karsinnan MIN-osa, jossa etsitään pienintä arvoa Nollalle.
     *
     * @param syvyys syvyys jolta etsitään
     * @param alpha karsinnassa käytettävä alpha-arvo
     * @param beta karsinnassa käytettävä beta-arvo
     * @param siirto edellisen siirron koordinaatti
     * @return suurin löydetty arvo
     */
    private int minArvo(int syvyys, int alpha, int beta, Koordinaatti siirto) {
        // Edellinen siirto, eli Risti voitti.
        if (siirto != null && ristinolla.siirtoVoitti(siirto.x, siirto.y)) {
            return Integer.MAX_VALUE - (hakusyvyys - syvyys);
        } else if (ristinolla.lautaTaynna()) {
            return 0;
        } else if (syvyys == 0) {
            return evaluoija.evaluoiPelitilanne(ristinolla);
        }
        muodostaSiirrot:
        for (int y = 0; y < ristinolla.korkeus; y++) {
            for (int x = 0; x < ristinolla.leveys; x++) {
                if (ristinolla.ruutuOnTyhja(x, y)) {
                    ristinolla.lisaaNolla(x, y);
                    int arvo = maxArvo(syvyys - 1, alpha, beta, new Koordinaatti(x, y));
                    ristinolla.poistaMerkki(x, y);
                    if (arvo < beta) {
                        beta = arvo;
                        if (syvyys == hakusyvyys) {
                            parasSiirto = new Koordinaatti(x, y);
                        }
                    }
                    if (beta <= alpha) {
                        break muodostaSiirrot;
                    }
                }
            }
        }
        return beta;
    }

}
