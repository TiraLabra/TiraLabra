package viidensuora.ai;

import viidensuora.logiikka.Koordinaatti;
import viidensuora.logiikka.Ristinolla;

/**
 * Tekoälyn käyttämä etsintämetodi, jolla etsitään paras seuraava siirto.
 * Toiminta perustuu MinMax-algoritmiin. (Kovin hidas pelikäytössä)
 *
 * @author juha
 */
public class MinMax implements Etsintametodi {

    /**
     * Kuinka etäällä tyhjä ruutu saa olla pelimerkistä, jotta se katsotaan
     * olevan aktiivinen ruutu.
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
     * Apumuuttuja, johon talletetaan parhaan siirron koordinaatti.
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
    private int evaluoitujaTilanteita;

    public MinMax(Ristinolla ristinolla, Evaluointimetodi e) {
        this.ristinolla = ristinolla;
        this.evaluoija = e;
        this.aktiivinenAlue = new AktiivinenAlue(ristinolla);
    }

    /**
     * Etsii parhaan Ristin siirron tietyltä syvyydeltä.
     *
     * @param syvyys Syvyys jolta etsitään
     * @return Parhaan löydetyn siirron koordinaatti
     */
    public Hakutulos etsiRistinSiirto(int syvyys) {
        return etsiSiirto(syvyys, true);
    }

    /**
     * Etsii parhaan Nollan siirron tietyltä syvyydeltä.
     *
     * @param syvyys Syvyys jolta etsitään
     * @return Parhaan löydetyn siirron koordinaatti
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
        evaluoitujaTilanteita = 0;
        if (ristinSiirto) {
            maxArvo(syvyys, null);
        } else {
            minArvo(syvyys, null);
        }
        return new Hakutulos(parasSiirto, parhaanSiirronArvo,
                System.currentTimeMillis() - aloitusaika,
                evaluoitujaTilanteita, syvyys, ristinolla.vapaitaRuutuja());
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
            evaluoitujaTilanteita++;
            return Integer.MIN_VALUE + (hakusyvyys - syvyys);
        } else if (ristinolla.lautaTaynna()) {
            evaluoitujaTilanteita++;
            return 0;
        } else if (syvyys == 0) {
            evaluoitujaTilanteita++;
            return evaluoija.evaluoiPelitilanne(ristinolla);
        }

        int parasArvo = Integer.MIN_VALUE;
        for (int y = 0; y < ristinolla.korkeus; y++) {
            for (int x = 0; x < ristinolla.leveys; x++) {
                if (aktiivinenAlue.onAktiivinenTyhja(x, y)) {
                    ristinolla.lisaaRisti(x, y);
                    aktiivinenAlue.paivita(x, y, AKTIIVISTEN_ETAISYYS, 1);
                    int arvo = minArvo(syvyys - 1, new Koordinaatti(x, y));
                    aktiivinenAlue.paivita(x, y, AKTIIVISTEN_ETAISYYS, -1);
                    ristinolla.poistaMerkki(x, y);
                    if (arvo >= parasArvo) {
                        parasArvo = arvo;
                        if (syvyys == hakusyvyys) {
                            parasSiirto = new Koordinaatti(x, y);
                            parhaanSiirronArvo = arvo;
                        }
                    }
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
            evaluoitujaTilanteita++;
            return Integer.MAX_VALUE - (hakusyvyys - syvyys);
        } else if (ristinolla.lautaTaynna()) {
            evaluoitujaTilanteita++;
            return 0;
        } else if (syvyys == 0) {
            evaluoitujaTilanteita++;
            return evaluoija.evaluoiPelitilanne(ristinolla);
        }

        int parasArvo = Integer.MAX_VALUE;
        for (int y = 0; y < ristinolla.korkeus; y++) {
            for (int x = 0; x < ristinolla.leveys; x++) {
                if (aktiivinenAlue.onAktiivinenTyhja(x, y)) {
                    ristinolla.lisaaNolla(x, y);
                    aktiivinenAlue.paivita(x, y, AKTIIVISTEN_ETAISYYS, 1);
                    int arvo = maxArvo(syvyys - 1, new Koordinaatti(x, y));
                    aktiivinenAlue.paivita(x, y, AKTIIVISTEN_ETAISYYS, -1);
                    ristinolla.poistaMerkki(x, y);
                    if (arvo <= parasArvo) {
                        parasArvo = arvo;
                        if (syvyys == hakusyvyys) {
                            parasSiirto = new Koordinaatti(x, y);
                            parhaanSiirronArvo = arvo;
                        }
                    }
                }
            }
        }
        return parasArvo;
    }
}
