package viidensuora.ai;

import viidensuora.logiikka.Koordinaatti;
import viidensuora.logiikka.Ristinolla;

/**
 * Tekoälyn käyttämä etsintämetodi, jolla etsitään paras seuraava siirto.
 * Toiminta perustuu MinMax-algoritmiin. Blaah pois pois, ihan liian hidas
 *
 * @author juha
 */
public class MinMax implements Etsintametodi {

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

    public MinMax(Ristinolla ristinolla, Evaluointimetodi e) {
        this.ristinolla = ristinolla;
        this.evaluoija = e;
    }

    /**
     * Etsii parhaan Ristin siirron tietyltä syvyydeltä.
     *
     * @param syvyys Syvyys jolta etsitään
     * @return Parhaan löydetyn siirron koordinaatti
     */
    public Hakutulos etsiRistinSiirto(int syvyys) {
        parasSiirto = null;
        hakusyvyys = syvyys;
        maxArvo(syvyys, null);
        //return parasSiirto;
        return null;
    }

    /**
     * Etsii parhaan Nollan siirron tietyltä syvyydeltä.
     *
     * @param syvyys Syvyys jolta etsitään
     * @return Parhaan löydetyn siirron koordinaatti
     */
    public Hakutulos etsiNollanSiirto(int syvyys) {
        parasSiirto = null;
        hakusyvyys = syvyys;
        minArvo(syvyys, null);
        //return parasSiirto;
        return null;
    }

    /**
     * MinMax-algoritmin MAX-osa, jossa etsitään suurinta arvoa Ristille.
     *
     * @param syvyys syvyys jolta etsitään
     * @param siirto edellisen siirron koordinaatti
     * @return suurin löydetty arvo
     */
    private int maxArvo(int syvyys, Koordinaatti siirto) {
        // Edellinen siirto, eli Nolla voitti.
        if (siirto != null && ristinolla.siirtoVoitti(siirto.x, siirto.y)) {
            return Integer.MIN_VALUE;
        } else if (ristinolla.lautaTaynna()) {
            return 0;
        } else if (syvyys == 0) {
            return evaluoija.evaluoiPelitilanne(ristinolla);
        }

        int parasArvo = Integer.MIN_VALUE;
        for (int y = 0; y < ristinolla.korkeus; y++) {
            for (int x = 0; x < ristinolla.leveys; x++) {
                if (ristinolla.ruutuOnTyhja(x, y)) {
                    ristinolla.lisaaRisti(x, y);
                    int arvo = minArvo(syvyys - 1, new Koordinaatti(x, y));
                    if (arvo >= parasArvo) {
                        parasArvo = arvo;
                        if (syvyys == hakusyvyys) {
                            parasSiirto = new Koordinaatti(x, y);
                        }
                    }
                    ristinolla.poistaMerkki(x, y);
                }
            }
        }
        return parasArvo;
    }

    /**
     * MinMax-algoritmin MIN-osa, jossa etsitään pienintä arvoa Nollalle.
     *
     * @param syvyys syvyys jolta etsitään
     * @param siirto edellisen siirron koordinaatti
     * @return suurin löydetty arvo
     */
    private int minArvo(int syvyys, Koordinaatti siirto) {
        // Edellinen siirto, eli Risti voitti.
        if (siirto != null && ristinolla.siirtoVoitti(siirto.x, siirto.y)) {
            return Integer.MAX_VALUE;
        } else if (ristinolla.lautaTaynna()) {
            return 0;
        } else if (syvyys == 0) {
            return evaluoija.evaluoiPelitilanne(ristinolla);
        }

        int parasArvo = Integer.MAX_VALUE;
        for (int y = 0; y < ristinolla.korkeus; y++) {
            for (int x = 0; x < ristinolla.leveys; x++) {
                if (ristinolla.ruutuOnTyhja(x, y)) {
                    ristinolla.lisaaNolla(x, y);
                    int arvo = maxArvo(syvyys - 1, new Koordinaatti(x, y));
                    if (arvo <= parasArvo) {
                        parasArvo = arvo;
                        if (syvyys == hakusyvyys) {
                            parasSiirto = new Koordinaatti(x, y);
                        }
                    }
                    ristinolla.poistaMerkki(x, y);
                }
            }
        }
        return parasArvo;
    }
}
