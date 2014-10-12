package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * AVL-puun implementaatio
 *
 * @see http://en.wikipedia.org/wiki/AVL_tree
 * @author Markus
 */
public class AvlPuu implements Hakupuu {

    AvlPuusolmu juuri;

    public AvlPuu() {
        juuri = null;
    }

    public void lisaa(int avain) {
        //Lisäys
        AvlPuusolmu uusi = new AvlPuusolmu(avain);
        AvlPuusolmu vanhempi;
        AvlPuusolmu solmu;
        if (onTyhja()) {
            juuri = uusi;
        } else {
            solmu = this.juuri;
            vanhempi = null;
            while (solmu != null) {
                vanhempi = solmu;
                if (avain < solmu.getAvain()) {
                    solmu = solmu.getVasen();
                } else {
                    solmu = solmu.getOikea();
                }
            }
            uusi.setVanhempi(vanhempi);
            if (avain < vanhempi.getAvain()) {
                vanhempi.setVasen(uusi);
            } else {
                vanhempi.setOikea(uusi);
            }
        }
        //Tasapainotus
        solmu = uusi.getVanhempi();
        AvlPuusolmu alipuu;
        while (solmu != null) {
            if (this.korkeus(solmu.getVasen()) > this.korkeus(solmu.getOikea()) + 1) { // Vasen lapsi aiheuttaa epätasapainon
                vanhempi = solmu.getVanhempi();
                if (this.korkeus(solmu.getVasen().getVasen()) > this.korkeus(solmu.getVasen().getOikea())) {
                    alipuu = oikeaKierto(solmu);
                } else {
                    alipuu = vasenOikeaKierto(solmu);
                }
                if (vanhempi == null) {
                    juuri = alipuu;
                } else if (vanhempi.getVasen() == solmu) {
                    vanhempi.setVasen(alipuu);
                } else {
                    vanhempi.setOikea(alipuu);
                }
                if (vanhempi != null) {
                    vanhempi.setKorkeus(Math.max(this.korkeus(vanhempi.getVasen()), this.korkeus(vanhempi.getOikea())) + 1);
                }
                return;
            }
            if (this.korkeus(solmu.getOikea()) > this.korkeus(solmu.getVasen()) + 1) { // Oikean lapsi aiheuttaa epätasapainon
                vanhempi = solmu.getVanhempi();
                if (this.korkeus(solmu.getOikea().getOikea()) > this.korkeus(solmu.getOikea().getVasen())) {
                    alipuu = vasenKierto(solmu);
                } else {
                    alipuu = oikeaVasenKierto(solmu);
                }
                if (vanhempi == null) {
                    juuri = alipuu;
                } else if (vanhempi.getVasen() == solmu) {
                    vanhempi.setVasen(alipuu);
                } else {
                    vanhempi.setOikea(alipuu);
                }
                if (vanhempi != null) {
                    vanhempi.setKorkeus(Math.max(this.korkeus(vanhempi.getVasen()), this.korkeus(vanhempi.getOikea())) + 1);
                }
                return;
            }
            solmu.setKorkeus(Math.max(this.korkeus(solmu.getVasen()), this.korkeus(solmu.getOikea())) + 1);
            solmu = solmu.getVanhempi();
        }
    }

    public void lisaaKaikki(int[] avaimet) {
        for (int i : avaimet) {
            this.lisaa(i);
        }
    }

    public boolean hae(int avain) {
        Puusolmu solmu = haeSolmu(avain);
        return solmu != null;
    }

    /**
     * Etsii ja palauttaa puusta avainta vastaavan solmun
     *
     * @param avain Haettavan solmun avain.
     * @return Avainta vastaava solmu.
     */
    public AvlPuusolmu haeSolmu(int avain) {
        AvlPuusolmu solmu = null;
        if (!onTyhja()) {
            solmu = this.juuri;
            while (solmu != null && solmu.getAvain() != avain) {
                if (avain < solmu.getAvain()) {
                    solmu = solmu.getVasen();
                } else {
                    solmu = solmu.getOikea();
                }
            }
        }
        return solmu;
    }

    public void poista(int avain) {
        avlPoista(haeSolmu(avain));
    }

    /**
     * Poistaa parametrina saadun solmun puusta
     *
     * @param poistettava Puusta poistettava solmu
     */
    private AvlPuusolmu poista(AvlPuusolmu poistettava) {
        if (poistettava == null) {
            return null;
        }
        //Poistaminen
        //Poistettavalla ei lapsia
        if (poistettava.getVasen() == null && poistettava.getOikea() == null) {
            AvlPuusolmu vanhempi = poistettava.getVanhempi();
            if (vanhempi == null) {
                juuri = null;
                return poistettava;
            }
            if (poistettava == vanhempi.getVasen()) {
                vanhempi.setVasen(null);
            } else {
                vanhempi.setOikea(null);
            }
            return poistettava;
        }
        //Poistettavalla yksi lapsi
        if (poistettava.getVasen() == null || poistettava.getOikea() == null) {
            AvlPuusolmu lapsi;
            if (poistettava.getVasen() != null) {
                lapsi = poistettava.getVasen();
            } else {
                lapsi = poistettava.getOikea();
            }
            AvlPuusolmu vanhempi = poistettava.getVanhempi();
            if (vanhempi == null) {
                juuri = lapsi;
                return poistettava;
            }
            if (poistettava == vanhempi.getVasen()) {
                vanhempi.setVasen(lapsi);
            } else {
                vanhempi.setOikea(lapsi);
            }
            return poistettava;
        }
        //Poistettavalla 2 lasta
        AvlPuusolmu seuraaja = (AvlPuusolmu) PuuOperaatiot.seuraaja(poistettava);
        poistettava.setAvain(seuraaja.getAvain());
        AvlPuusolmu lapsi = seuraaja.getOikea();
        AvlPuusolmu vanhempi = seuraaja.getVanhempi();
        if (vanhempi.getVasen() == seuraaja) {
            vanhempi.setVasen(lapsi);
        } else {
            vanhempi.setOikea(lapsi);
        }
        if (lapsi != null) {
            lapsi.setVanhempi(vanhempi);
        }
        return seuraaja;
    }

    /**
     * Suorittaa ensin perus poistamisoperaation, jonkä jälkeen tasapainottaa
     * puun tarvittaessa.
     *
     * @param poistettava Puusta poistettava solmu.
     */
    private void avlPoista(AvlPuusolmu poistettava) {
        AvlPuusolmu pois = poista(poistettava);
        if (pois == null) {
            return;
        }
        AvlPuusolmu solmu = pois.getVanhempi();
        AvlPuusolmu vanhempi, alipuu;
        while (solmu != null) {
            if (korkeus(solmu.getVasen()) > (korkeus(solmu.getOikea()) + 1)) { // Vasen lapsi aiheuttaa epätasapainon
                vanhempi = solmu.getVanhempi();
                if (korkeus(solmu.getVasen().getVasen()) > korkeus(solmu.getVasen().getOikea())) {
                    alipuu = oikeaKierto(solmu);
                } else {
                    alipuu = vasenOikeaKierto(solmu);
                }
                if (vanhempi == null) {
                    juuri = alipuu;
                } else if (vanhempi.getVasen() == solmu) {
                    vanhempi.setVasen(alipuu);
                } else {
                    vanhempi.setOikea(alipuu);
                }
                if (vanhempi != null) {
                    vanhempi.setKorkeus(Math.max(korkeus(vanhempi.getVasen()), korkeus(vanhempi.getOikea())) + 1);
                }
                return;
            }
            if (korkeus(solmu.getOikea()) > (korkeus(solmu.getVasen()) + 1)) { // Oikean lapsi aiheuttaa epätasapainon
                vanhempi = solmu.getVanhempi();
                if (korkeus(solmu.getOikea().getOikea()) > korkeus(solmu.getOikea().getVasen())) {
                    alipuu = vasenKierto(solmu);
                } else {
                    alipuu = oikeaVasenKierto(solmu);
                }
                if (vanhempi == null) {
                    juuri = alipuu;
                } else if (vanhempi.getVasen() == solmu) {
                    vanhempi.setVasen(alipuu);
                } else {
                    vanhempi.setOikea(alipuu);
                }
                if (vanhempi != null) {
                    vanhempi.setKorkeus(Math.max(korkeus(vanhempi.getVasen()), korkeus(vanhempi.getOikea())) + 1);
                }
                return;
            }
            solmu.setKorkeus(Math.max(this.korkeus(solmu.getVasen()), this.korkeus(solmu.getOikea())) + 1);
            solmu = solmu.getVanhempi();
        }
    }

    /**
     * Puun tasapainotukseen käytettävä operaatio, joka vaihtaa parametrina
     * saadun solmun ja sen vasemman lapsen paikan puussa.
     *
     * @param solmu Solmu jonka suhteen kierto tehdään.
     * @return Alkuperäisen solmun paikalle siirretty solmu.
     */
    private AvlPuusolmu oikeaKierto(AvlPuusolmu solmu) {
        AvlPuusolmu solmu2 = solmu.getVasen();
        solmu2.setVanhempi(solmu.getVanhempi());
        solmu.setVanhempi(solmu2);
        solmu.setVasen(solmu2.getOikea());
        solmu2.setOikea(solmu);
        if (solmu.getVasen() != null) {
            solmu.getVasen().setVanhempi(solmu);
        }
        solmu.setKorkeus(Math.max(korkeus(solmu.getVasen()), korkeus(solmu.getOikea())) + 1);
        solmu2.setKorkeus(Math.max(korkeus(solmu2.getVasen()), korkeus(solmu2.getOikea())) + 1);
        return solmu2;
    }

    /**
     * Puun tasapainotukseen käytettävä operaatio, joka vaihtaa parametrina
     * saadun solmun ja sen oikean lapsen paikan puussa.
     *
     * @param solmu Solmu jonka suhteen kierto tehdään.
     * @return Alkuperäisen solmun paikalle siirretty solmu.
     */
    private AvlPuusolmu vasenKierto(AvlPuusolmu solmu) {
        AvlPuusolmu solmu2 = solmu.getOikea();
        solmu2.setVanhempi(solmu.getVanhempi());
        solmu.setVanhempi(solmu2);
        solmu.setOikea(solmu2.getVasen());
        solmu2.setVasen(solmu);
        if (solmu.getOikea() != null) {
            solmu.getOikea().setVanhempi(solmu);
        }
        solmu.setKorkeus(Math.max(korkeus(solmu.getVasen()), korkeus(solmu.getOikea())) + 1);
        solmu2.setKorkeus(Math.max(korkeus(solmu2.getVasen()), korkeus(solmu2.getOikea())) + 1);
        return solmu2;
    }

    /**
     * Puun tasapainotukseen käytettävä operaatio, joka suorittaa peräkkäin
     * kaksi kiertoa. Ensin solmun oikean lapsen ja sen vasemman lapsen välillä
     * ja sitten itsensä ja uuden oikean lapsen välillä.
     *
     * @param solmu Solmu jonka suhteen kierto tehdään.
     * @return Alkuperäisen solmun paikalle siirretty solmu.
     */
    private AvlPuusolmu oikeaVasenKierto(AvlPuusolmu solmu) {
        AvlPuusolmu solmu2 = solmu.getOikea();
        solmu.setOikea(oikeaKierto(solmu2));
        return vasenKierto(solmu);
    }

    /**
     * Puun tasapainotukseen käytettävä operaatio, joka suorittaa peräkkäin
     * kaksi kiertoa. Ensin solmun vasemman lapsen ja sen oikean lapsen välillä
     * ja sitten itsensä ja uuden vasemman lapsen välillä.
     *
     * @param solmu Solmu jonka suhteen kierto tehdään.
     * @return Alkuperäisen solmun paikalle siirretty solmu.
     */
    private AvlPuusolmu vasenOikeaKierto(AvlPuusolmu solmu) {
        AvlPuusolmu solmu2 = solmu.getVasen();
        solmu.setVasen(vasenKierto(solmu2));
        return oikeaKierto(solmu);
    }

    /**
     * Palauttaa totuusarvon, joka kertoo onko puu tyhjä
     *
     * @return True, jos puu tyhjä; muulloin false.
     */
    public boolean onTyhja() {
        return juuri == null;
    }

    /**
     * Palauttaa parametrina saatavan solmun korkeus-muuttujan arvon tai -1
     * mikäli annetaan null
     *
     * @param solmu Solmu jonka korkeus halutaan tarkistaa
     * @return -1 mikäli solmu on null, muulloin solmun korkeus-muuttujan arvo
     */
    private int korkeus(AvlPuusolmu solmu) {
        if (solmu == null) {
            return -1;
        } else {
            return solmu.getKorkeus();
        }
    }

    public Puusolmu getJuuri() {
        return this.juuri;
    }

    public void tyhjenna() {
        juuri = null;
    }

    public String getNimi() {
        return "AVL-Puu";
    }

}
