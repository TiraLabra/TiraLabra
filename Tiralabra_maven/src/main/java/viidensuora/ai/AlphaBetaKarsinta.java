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
     * Kuinka etäällä tyhjä ruutu saa olla pelimerkistä, jotta se katsotaan
     * olevan aktiivinen ruutu. Kaukana olevia ruutuja ei kannata liittää
     * hakuun. Käytännössä 1 tuntuu riittävän. 2 olisi parempi, mutta hyöty haun
     * hidastumiseen nähden pieni.
     */
    private final int AKTIIVISTEN_ETAISYYS = 1;

    /**
     * Aktiivisten alueiden tallentamiseen.
     */
    private final AktiivinenAlue aktiivinenAlue;

    /**
     * Pelitilanne.
     */
    private final Ristinolla ristinolla;

    /**
     * Metodi, jota käytetään evaluoimaan keskeneräinen pelitilanne.
     */
    private final Evaluointimetodi evaluoija;

    /**
     * Apumuuttuja, johon päivitetää parhaan siirron koordinaatti haun aikana.
     */
    private Koordinaatti parasSiirto;

    /**
     * Apumuuttuja, johon talletetaan parhaan siirron arvo.
     */
    private int parhaanSiirronArvo;

    /**
     * Kuinka syvältä haku suoritetaan.
     */
    private int hakusyvyys;

    /**
     * Apumuuttuja, johon lasketaan vierailtujen solmujen lukumäärä.
     */
    private int avattujaNodeja;

    /**
     * Konstruktori.
     *
     * @param rn
     * @param e
     */
    public AlphaBetaKarsinta(Ristinolla rn, Evaluointimetodi e) {
        this.ristinolla = rn;
        this.evaluoija = e;
        this.aktiivinenAlue = new AktiivinenAlue(ristinolla);
    }

    /**
     * Etsii parhaan Ristin siirron tietyltä syvyydeltä.
     *
     * @param syvyys Syvyys jolta etsitään
     * @return Parhaan löydetyn siirron Hakutulos
     */
    public Hakutulos etsiRistinSiirto(int syvyys) {
        return etsiSiirto(syvyys, true);
    }

    /**
     * Etsii parhaan Nollan siirron tietyltä syvyydeltä.
     *
     * @param syvyys Syvyys jolta etsitään
     * @return Parhaan löydetyn siirron Hakutulos
     */
    public Hakutulos etsiNollanSiirto(int syvyys) {
        return etsiSiirto(syvyys, false);
    }

    /**
     * Itse haun käynnistävä metodi. Laskee ja tallettaa haukuun käytetyn ajan
     * yms. metriikkaa.
     *
     * @param syvyys Kuinka syvältä etistään hakupuusta
     * @param ristinSiirto TRUE jos etsitään ristin siirtoa, FALSE jos nollan
     * @return Hakutulos Haun tulos.
     */
    private Hakutulos etsiSiirto(int syvyys, boolean ristinSiirto) {
        long aloitusaika = System.currentTimeMillis();
        aktiivinenAlue.alusta(AKTIIVISTEN_ETAISYYS);
        parasSiirto = null;
        hakusyvyys = syvyys;
        avattujaNodeja = -1;
        if (ristinSiirto) {
            maxArvo(syvyys, Integer.MIN_VALUE, Integer.MAX_VALUE, null);
        } else {
            minArvo(syvyys, Integer.MIN_VALUE, Integer.MAX_VALUE, null);
        }
        long hakuaika = System.currentTimeMillis() - aloitusaika;
        
        // TODo
        int hakupuussaNodeja = ristinolla.vapaitaRuutuja()
                * (ristinolla.vapaitaRuutuja() - 1)
                * (ristinolla.vapaitaRuutuja() - 2)
                * (ristinolla.vapaitaRuutuja() - 3);

        return new Hakutulos(parasSiirto, parhaanSiirronArvo, syvyys, hakuaika,
                avattujaNodeja, hakupuussaNodeja);
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
        avattujaNodeja++;
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
                if (aktiivinenAlue.onAktiivinenTyhja(x, y)) {
                    ristinolla.lisaaRisti(x, y);
                    aktiivinenAlue.paivita(x, y, AKTIIVISTEN_ETAISYYS, 1);
                    int arvo = minArvo(syvyys - 1, alpha, beta, new Koordinaatti(x, y));
                    aktiivinenAlue.paivita(x, y, AKTIIVISTEN_ETAISYYS, -1);
                    ristinolla.poistaMerkki(x, y);
                    if (arvo > alpha) {
                        alpha = arvo;
                        if (syvyys == hakusyvyys) {
                            parasSiirto = new Koordinaatti(x, y);
                            parhaanSiirronArvo = arvo;
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
        avattujaNodeja++;
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
                if (aktiivinenAlue.onAktiivinenTyhja(x, y)) {
                    ristinolla.lisaaNolla(x, y);
                    aktiivinenAlue.paivita(x, y, AKTIIVISTEN_ETAISYYS, 1);
                    int arvo = maxArvo(syvyys - 1, alpha, beta, new Koordinaatti(x, y));
                    aktiivinenAlue.paivita(x, y, AKTIIVISTEN_ETAISYYS, -1);
                    ristinolla.poistaMerkki(x, y);
                    if (arvo < beta) {
                        beta = arvo;
                        if (syvyys == hakusyvyys) {
                            parasSiirto = new Koordinaatti(x, y);
                            parhaanSiirronArvo = arvo;
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
