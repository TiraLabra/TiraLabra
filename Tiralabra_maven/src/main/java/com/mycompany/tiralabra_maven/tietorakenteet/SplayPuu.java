package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Splay-puun implementaatio
 *
 * @see http://en.wikipedia.org/wiki/Splay_tree
 * @author Markus
 */
public class SplayPuu extends YliHakupuu {

    public void lisaa(int avain) {
        Puusolmu uusi = new BinaarinenPuusolmu(avain);
        if (lisaa(uusi)) {
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
            Puusolmu isovanhempi = isovanhempi(solmu);
            if (isovanhempi == null) {
                if (onVasen(solmu)) {
                    oikeaKierto(vanhempi);
                } else {
                    vasenKierto(vanhempi);
                }
            } else {
                if (onVasen(solmu)) {
                    if (onVasen(vanhempi)) {
                        oikeaKierto(isovanhempi);
                        oikeaKierto(vanhempi);
                    } else {
                        oikeaKierto(vanhempi);
                        vasenKierto(isovanhempi);
                    }
                } else {
                    if (onVasen(vanhempi)) {
                        vasenKierto(vanhempi);
                        oikeaKierto(isovanhempi);
                    } else {
                        vasenKierto(isovanhempi);
                        vasenKierto(vanhempi);
                    }
                }
            }
        }
    }

    /**
     * Etsii ja palauttaa puusta avainta vastaavan solmun
     *
     * @param avain Haettavan solmun avain.
     * @return Avainta vastaava solmu.
     */
    @Override
    protected Puusolmu haeSolmu(int avain) {
        Puusolmu solmu = super.haeSolmu(avain);
        if (solmu != null) {
            splay(solmu);
        }
        return solmu;
    }

    @Override
    public void poista(int avain) {
        poista(haeSolmu(avain));
    }

    /**
     * Poistaa parametrina saadun solmun puusta. Poistettava solmu on aina puun
     * juuri.
     *
     * @param poistettava Puusta poistettava solmu
     */
    private void poista(Puusolmu poistettava) {
        if (poistettava == null) {
            return;
        }
        splay(poistettava);
        if (poistettava.getVasen() != null && poistettava.getOikea() != null) {
            Puusolmu edeltaja = edeltaja(poistettava);
            edeltaja.setOikea(poistettava.getOikea());
            poistettava.getOikea().setVanhempi(edeltaja);
            poistettava.getVasen().setVanhempi(null);
            juuri = poistettava.getVasen();
        } else if (poistettava.getOikea() != null) {
            poistettava.getOikea().setVanhempi(null);
            juuri = poistettava.getOikea();
        } else if (poistettava.getVasen() != null) {
            poistettava.getVasen().setVanhempi(null);
            juuri = poistettava.getVasen();
        } else {
            juuri = null;
        }
    }

    public String getNimi() {
        return "Splay-Puu";
    }
}
