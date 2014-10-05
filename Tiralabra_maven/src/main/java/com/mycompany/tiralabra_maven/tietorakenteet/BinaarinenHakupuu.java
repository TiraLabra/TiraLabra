package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Binäärisen hakupuun implementaatio
 *
 * @see http://en.wikipedia.org/wiki/Binary_search_tree
 * @author Markus
 */
public class BinaarinenHakupuu implements Hakupuu {

    private Puusolmu juuri;

    /**
     * Luo uuden binäärisen hakupuun ja asettaa asianmukaiset olio-muuttujien
     * alkuarvot.
     */
    public BinaarinenHakupuu() {
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
    public Puusolmu haeSolmu(int avain) {
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
        //Poistettavalla ei lapsia
        if (poistettava.getVasen() == null && poistettava.getOikea() == null) {
            Puusolmu vanhempi = poistettava.getVanhempi();
            if (vanhempi == null) {
                juuri = null;
                return;
            }
            if (poistettava == vanhempi.getVasen()) {
                vanhempi.setVasen(null);
            } else {
                vanhempi.setOikea(null);
            }
            return;
        }
        //Poistettavalla yksi lapsi
        if (poistettava.getVasen() == null || poistettava.getOikea() == null) {
            Puusolmu lapsi;
            if (poistettava.getVasen() != null) {
                lapsi = poistettava.getVasen();
            } else {
                lapsi = poistettava.getOikea();
            }
            Puusolmu vanhempi = poistettava.getVanhempi();
            if (vanhempi == null) {
                juuri = lapsi;
                return;
            }
            if (poistettava == vanhempi.getVasen()) {
                vanhempi.setVasen(lapsi);
            } else {
                vanhempi.setOikea(lapsi);
            }
            return;
        }
        //Poistettavalla 2 lasta
        Puusolmu seuraaja = PuuOperaatiot.seuraaja(poistettava);
        poistettava.setAvain(seuraaja.getAvain());
        Puusolmu lapsi = seuraaja.getOikea();
        Puusolmu vanhempi = seuraaja.getVanhempi();
        if (vanhempi.getVasen() == seuraaja) {
            vanhempi.setVasen(lapsi);
        } else {
            vanhempi.setOikea(lapsi);
        }
        if (lapsi != null) {
            lapsi.setVanhempi(vanhempi);
        }
    }

    /**
     * Palauttaa puun juurena olevan solmun.
     *
     * @return Juurena oleva puusolmu-olio
     */
    public Puusolmu getJuuri() {
        return juuri;
    }

    /**
     * Palauttaa totuusarvon, joka kertoo onko puu tyhjä
     *
     * @return True, jos puu tyhjä; muulloin false.
     */
    public boolean onTyhja() {
        return juuri == null;
    }
    
     public void tyhjenna() {
        juuri = null;
    }

    public String getNimi() {
        return "Binäärinen hakupuu";
    }
     
    

}
