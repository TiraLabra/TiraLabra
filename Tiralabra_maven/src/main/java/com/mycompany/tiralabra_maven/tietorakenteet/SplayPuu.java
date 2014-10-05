package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Splay-puun implementaatio
 *
 * @see http://en.wikipedia.org/wiki/Splay_tree
 * @author Markus
 */
public class SplayPuu implements Hakupuu {

    private Puusolmu juuri;

    public SplayPuu() {
        juuri = null;
    }

    public void lisaa(int avain) {
        Puusolmu uusi = new BinaarinenPuusolmu(avain);
        if (onTyhja()) {
            juuri = uusi;
        } else {
            Puusolmu solmu = this.juuri;
            Puusolmu vanhempi = null;
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
            splay(uusi);
        }
    }

    /**
     * Suorittaa Splay operaation, joka siirtää solmun puun juureksi
     * kierto-operaatioilla.
     *
     * @param solmu Solmu joka siirretään puun juureksi
     */
    private void splay(Puusolmu solmu) {
        while (solmu.getVanhempi() != null) {
            Puusolmu vanhempi = solmu.getVanhempi();
            Puusolmu isovanhempi = PuuOperaatiot.isovanhempi(solmu);
            if (isovanhempi == null) {
                if (PuuOperaatiot.onVasen(solmu)) {
                    PuuOperaatiot.oikeaKierto(vanhempi);
                } else {
                    PuuOperaatiot.vasenKierto(vanhempi);
                }
            } else {
                if (PuuOperaatiot.onVasen(solmu)) {
                    if (PuuOperaatiot.onVasen(vanhempi)) {
                        PuuOperaatiot.oikeaKierto(isovanhempi);
                        PuuOperaatiot.oikeaKierto(vanhempi);
                    } else {
                        PuuOperaatiot.oikeaKierto(vanhempi);
                        PuuOperaatiot.vasenKierto(vanhempi);
                    }
                } else {
                    if (PuuOperaatiot.onVasen(vanhempi)) {
                        PuuOperaatiot.vasenKierto(vanhempi);
                        PuuOperaatiot.oikeaKierto(vanhempi);
                    } else {
                        PuuOperaatiot.vasenKierto(isovanhempi);
                        PuuOperaatiot.vasenKierto(vanhempi);
                    }
                }
            }
        }
        juuri = solmu;
    }

    public void lisaaKaikki(int[] avaimet) {
        for (int i : avaimet) {
            lisaa(i);
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
    private Puusolmu haeSolmu(int avain) {
        Puusolmu solmu = null;
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
        poista(haeSolmu(avain));
    }

    /**
     * Poistaa parametrina saadun solmun puusta
     *
     * @param poistettava Puusta poistettava solmu
     */
    private void poista(Puusolmu poistettava) {
        if (poistettava == null) {
            return;
        }
        splay(poistettava);
        if(poistettava.getVasen() != null && poistettava.getOikea() != null){
            Puusolmu edeltaja = PuuOperaatiot.edeltaja(poistettava);
            edeltaja.setOikea(poistettava.getOikea());
            poistettava.getOikea().setVanhempi(edeltaja);
            poistettava.getVasen().setVanhempi(null);
            juuri = poistettava.getVasen();
        } else if (poistettava.getOikea() != null){
            poistettava.getOikea().setVanhempi(null);
            juuri = poistettava.getOikea();
        } else if(poistettava.getVasen() != null){
            poistettava.getVasen().setVanhempi(null);
            juuri = poistettava.getVasen();
        } else {
            juuri = null;
        }
    }

    public boolean onTyhja() {
        return juuri == null;
    }

    public Puusolmu getJuuri() {
        return this.juuri;
    }

    public void tyhjenna() {
        juuri = null;
    }

    public String getNimi() {
        return "Splay-Puu";
    }
}
