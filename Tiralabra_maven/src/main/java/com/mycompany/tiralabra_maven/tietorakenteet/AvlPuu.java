package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * AVL-puun implementaatio. Käyttää solmuinaan AvlPuusolmu luokan olioita. Perii
 * ylihakupun.
 *
 * @see YliHakupuu
 * @see AvlPuusolmu
 * @see http://en.wikipedia.org/wiki/AVL_tree
 * @author Markus
 */
public class AvlPuu extends YliHakupuu {

    /**
     * {@inheritDoc} Lisäyksen jälkeen tekee tarvittavat tasapainotusoperaatiot.
     *
     * @param avain {@inheritDoc}
     */
    public void lisaa(int avain) {
        AvlPuusolmu uusi = new AvlPuusolmu(avain);
        if (lisaa(uusi)) {
            tasapainota(uusi);
        }
    }

    /**
     * Tasapainottaa puun alkaen parametrina saadusta solmusta. Etenee kohti
     * puun juurta ja suorittaa tarvittavat kierto-operaatiot puun tasapainon
     * palauttamiseksi.
     *
     * @param alku Solmu, josta ylöpäin tasapainotus suoritetaan..
     */
    private void tasapainota(AvlPuusolmu alku) {
        AvlPuusolmu solmu = alku.getVanhempi();
        AvlPuusolmu vanhempi;
        while (solmu != null) {
            if (this.korkeus(solmu.getVasen()) > this.korkeus(solmu.getOikea()) + 1) { // Vasen lapsi aiheuttaa epätasapainon.
                vanhempi = solmu.getVanhempi();
                if (this.korkeus(solmu.getVasen().getVasen()) > this.korkeus(solmu.getVasen().getOikea())) { // Vasemman lapsen vasen lapsi syyllinen.
                    oikeaKierto(solmu);
                } else {    // Vasemman lapsen oikea lapsi syyllinen.
                    vasenOikeaKierto(solmu);
                }
                korjaaKorkeus(vanhempi);
                return;
            }
            if (this.korkeus(solmu.getOikea()) > this.korkeus(solmu.getVasen()) + 1) { // Oikean lapsi aiheuttaa epätasapainon.
                vanhempi = solmu.getVanhempi();
                if (this.korkeus(solmu.getOikea().getOikea()) > this.korkeus(solmu.getOikea().getVasen())) { // Oikean lapsen oikea lapsi syyllinen.
                    vasenKierto(solmu);
                } else {    // Oikean lapsen oikea lapsi syyllinen.
                    oikeaVasenKierto(solmu);
                }
                korjaaKorkeus(vanhempi);
                return;
            }
            korjaaKorkeus(solmu);
            solmu = solmu.getVanhempi();
        }
    }

    /**
     * {@inheritDoc} Lisäksi tasapainottaa puun tarvittaessa.
     *
     * @param avain {@inheritDoc}
     */
    @Override
    public void poista(int avain) {
        AvlPuusolmu pois = (AvlPuusolmu) poistaSolmu(avain);
        if (pois == null) {
            return;
        }
        tasapainota(pois);
    }

    /**
     * Suorittaa yliluokan määrittämän vasemman kierron ja korjaa kierrossa
     * mukana olevien solmujen korkeudet
     *
     * @see YliHakupuu
     * @param solmu Solmu jonka suhteen kierto tehdään
     */
    private void vasenKierto(AvlPuusolmu solmu) {
        AvlPuusolmu oikea = solmu.getOikea();
        super.vasenKierto(solmu);
        korjaaKorkeus(solmu);
        korjaaKorkeus(oikea);
    }

    /**
     * Suorittaa yliluokan määrittämän oikean kierron ja korjaa kierrossa mukana
     * olevien solmujen korkeudet
     *
     * @see YliHakupuu
     * @param solmu Solmu jonka suhteen kierto tehdään
     */
    private void oikeaKierto(AvlPuusolmu solmu) {
        AvlPuusolmu vasen = solmu.getVasen();
        super.oikeaKierto(solmu);
        korjaaKorkeus(solmu);
        korjaaKorkeus(vasen);
    }

    /**
     * Puun tasapainotukseen käytettävä operaatio, joka suorittaa peräkkäin
     * kaksi kiertoa. Ensin solmun oikean lapsen ja sen vasemman lapsen välillä
     * ja sitten itsensä ja uuden oikean lapsen välillä.
     *
     * @param solmu Solmu jonka suhteen kierto tehdään.
     * @return Alkuperäisen solmun paikalle siirretty solmu.
     */
    private void oikeaVasenKierto(AvlPuusolmu solmu) {
        this.oikeaKierto(solmu.getOikea());
        this.vasenKierto(solmu);
    }

    /**
     * Puun tasapainotukseen käytettävä operaatio, joka suorittaa peräkkäin
     * kaksi kiertoa. Ensin solmun vasemman lapsen ja sen oikean lapsen välillä
     * ja sitten itsensä ja uuden vasemman lapsen välillä.
     *
     * @param solmu Solmu jonka suhteen kierto tehdään.
     * @return Alkuperäisen solmun paikalle siirretty solmu.
     */
    private void vasenOikeaKierto(AvlPuusolmu solmu) {
        this.vasenKierto(solmu.getVasen());
        this.oikeaKierto(solmu);
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

    /**
     * Laskee uudelleen ja asettaa parametrina saadun solmun korkeuden. Jos
     * solmu on null, ei tee mitään.
     *
     * @param solmu Solmu jonka korkeus halutaan laskea ja asettaa.
     */
    private void korjaaKorkeus(AvlPuusolmu solmu) {
        if (solmu == null) {
            return;
        }
        solmu.setKorkeus(Math.max(this.korkeus(solmu.getVasen()), this.korkeus(solmu.getOikea())) + 1);
    }

    public String getNimi() {
        return "AVL-Puu";
    }

}
