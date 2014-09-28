package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Punamustan puun implementaatio
 *
 * @see http://en.wikipedia.org/wiki/Red%E2%80%93black_tree
 * @author Markus
 */
public class PunamustaPuu implements Hakupuu {

    private PunamustaPuusolmu juuri;

    public void lisaa(int avain) {
        //Tehdään uusi solmu.
        PunamustaPuusolmu uusi = new PunamustaPuusolmu(avain);
        //Asetetaan solmu juureksi mikäli puu on tyhjä.
        if (onTyhja()) {
            juuri = uusi;
        } else {
            //Puu ei ollut tyhjä
            Puusolmu solmu = this.juuri;
            Puusolmu vanhempi = null;
            //Etsitään oikea paikka uudelle solmulle.
            while (solmu != null) {
                vanhempi = solmu;
                if (avain < solmu.getAvain()) {
                    solmu = solmu.getVasen();
                } else {
                    solmu = solmu.getOikea();
                }
            }
            //Asetetaan uusi solmu oikealle paikalleen ja tasapainotetaan puu.
            uusi.setVanhempi(vanhempi);
            if (avain < vanhempi.getAvain()) {
                vanhempi.setVasen(uusi);
                tasapainotaLisäys(uusi);
            } else {
                vanhempi.setOikea(uusi);
                tasapainotaLisäys(uusi);
            }
        }
    }

    /**
     * Tasapainottaa puun lisäyksen jälkeen tarvittaessa
     *
     * @param solmu Puuhun lisätty solmu.
     */
    private void tasapainotaLisäys(PunamustaPuusolmu solmu) {
        if (solmu == null) {
            return;
        }
        //Asetetaan solmu punaiseksi
        solmu.setVari(Vari.PUNAINEN);

        //Tutkitaan onko 2 punaista peräkkäin
        if (solmu != juuri && solmu.getVanhempi().getVari() == Vari.PUNAINEN) {
            //Vaihdetaan värit ja jatketaan ylöspäin puuta
            if (solmu.getVanhempi().getSisarus() == null ? false : solmu.getVanhempi().getSisarus().getVari() == Vari.PUNAINEN) {
                solmu.getVanhempi().setVari(Vari.MUSTA);
                solmu.getVanhempi().getSisarus().setVari(Vari.MUSTA);
                solmu.getIsovanhempi().setVari(Vari.PUNAINEN);
                tasapainotaLisäys(solmu.getIsovanhempi());
                // Tehdään joko oikea tai vasen - oikea kierto tarpeen mukaan.
            } else if (solmu.getVanhempi() == solmu.getIsovanhempi().getVasen()) {
                if (solmu == solmu.getVanhempi().getOikea()) {
                    solmu = solmu.getVanhempi();
                    vasenKierto(solmu);
                }
                solmu.getVanhempi().setVari(Vari.MUSTA);
                solmu.getIsovanhempi().setVari(Vari.PUNAINEN);
                solmu = oikeaKierto(solmu.getIsovanhempi());
                if (solmu.getVanhempi() == null) {
                    juuri = solmu;
                }
                // Tehdään joko vasen tai oikea - vasen kierto tarpeen mukaan.
            } else if (solmu.getVanhempi() == solmu.getIsovanhempi().getOikea()) {
                if (solmu == solmu.getVanhempi().getVasen()) {
                    solmu = solmu.getVanhempi();
                    oikeaKierto(solmu);
                }
                solmu.getVanhempi().setVari(Vari.MUSTA);
                solmu.getIsovanhempi().setVari(Vari.PUNAINEN);
                solmu = vasenKierto(solmu.getIsovanhempi());
                if (solmu.getVanhempi() == null) {
                    juuri = solmu;
                }
            }
        }
        //Asetetaan juuri mustaksi
        juuri.setVari(Vari.MUSTA);
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
    public PunamustaPuusolmu haeSolmu(int avain) {
        PunamustaPuusolmu solmu = null;
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
        PunamustaPuusolmu solmu = haeSolmu(avain);
        if (solmu == null) {
            return;
            // Jos solmulla 2 lasta vaihdetaan se itsensä edeltäjään.
        } else if (solmu.getVasen() != null && solmu.getOikea() != null) {
            PunamustaPuusolmu edeltaja = (PunamustaPuusolmu) PuuOperaatiot.edeltaja(solmu);
            solmu.setAvain(edeltaja.getAvain());
            solmu = edeltaja;
        }
        //Ny solmulla 0 tai 1 lasta.
        PunamustaPuusolmu alipuu = solmu.getVasen() == null ? solmu.getOikea() : solmu.getVasen();

        //Nostetaan poistettavaa seuraavaa alipuuta, mikäli sellainen on
        if (alipuu != null) {
            if (solmu == juuri) {
                juuri = alipuu;
            } else if (solmu == solmu.getVanhempi().getVasen()) {
                solmu.getVanhempi().setVasen(alipuu);
            } else {
                solmu.getVanhempi().setOikea(alipuu);
            }
            if (solmu.getVari() == Vari.MUSTA) {
                tasapainotaPoisto(alipuu);
            }
            //Alipuuta ei ole
        } else if (solmu == juuri) {
            juuri = null;
        } else {
            if (solmu.getVari() == Vari.MUSTA) {
                tasapainotaPoisto(solmu);
            }
            //Poistetaan solmu sen vanhemmalta
            if (solmu.getVanhempi() != null) {
                if (solmu == solmu.getVanhempi().getOikea()) {
                    solmu.getVanhempi().setOikea(null);
                } else {
                    solmu.getVanhempi().setVasen(null);
                }
            }
        }
    }

    /**
     * Tasapainottaa puun poisto-operaation jälkeen.
     *
     * @param solmu Solmu jonka suhteen tasapainotus tehdään
     */
    private void tasapainotaPoisto(PunamustaPuusolmu solmu) {
        while (solmu != juuri && solmu.getVari() == Vari.MUSTA) {
            if (solmu == solmu.getVanhempi().getVasen()) {
                //Solmu on vasen lapsi
                PunamustaPuusolmu sisarus = solmu.getVanhempi().getOikea();
                if (sisarus.getVari() == Vari.PUNAINEN) {
                    sisarus.setVari(Vari.MUSTA);
                    solmu.getVanhempi().setVari(Vari.PUNAINEN);
                    vasenKierto(solmu.getVanhempi());
                    sisarus = solmu.getVanhempi().getOikea();
                }
                if (sisarus.getVasen().getVari() == Vari.MUSTA && sisarus.getOikea().getVari() == Vari.MUSTA) {
                    sisarus.setVari(Vari.PUNAINEN);
                    solmu = solmu.getVanhempi();
                } else {
                    if (sisarus.getOikea().getVari() == Vari.MUSTA) {
                        sisarus.getVasen().setVari(Vari.MUSTA);
                        sisarus.setVari(Vari.PUNAINEN);
                        oikeaKierto(sisarus);
                        sisarus = solmu.getVanhempi().getOikea();
                    }
                    sisarus.setVari(solmu.getVanhempi().getVari());
                    solmu.getVanhempi().setVari(Vari.MUSTA);
                    sisarus.getOikea().setVari(Vari.MUSTA);
                    vasenKierto(solmu.getVanhempi());
                    solmu = juuri;
                }
            } else {
                //Solmu on oikea lapsi
                PunamustaPuusolmu sisarus = solmu.getVanhempi().getVasen();
                if (sisarus.getVari() == Vari.PUNAINEN) {
                    sisarus.setVari(Vari.MUSTA);
                    solmu.getVanhempi().setVari(Vari.PUNAINEN);
                    oikeaKierto(solmu.getVanhempi());
                    sisarus = solmu.getVanhempi().getVasen();
                }
                if (sisarus.getVasen().getVari() == Vari.MUSTA && sisarus.getOikea().getVari() == Vari.MUSTA) {
                    sisarus.setVari(Vari.PUNAINEN);
                    solmu = solmu.getVanhempi();
                } else {
                    if (sisarus.getVasen().getVari() == Vari.MUSTA) {
                        sisarus.getOikea().setVari(Vari.MUSTA);
                        sisarus.setVari(Vari.PUNAINEN);
                        vasenKierto(sisarus);
                        sisarus = solmu.getVanhempi().getVasen();
                    }
                    sisarus.setVari(solmu.getVanhempi().getVari());
                    solmu.getVanhempi().setVari(Vari.MUSTA);
                    sisarus.getVasen().setVari(Vari.MUSTA);
                    oikeaKierto(solmu.getVanhempi());
                    solmu = juuri;
                }
            }
        }
        solmu.setVari(Vari.MUSTA);
    }

    /**
     * Puun tasapainotukseen käytettävä operaatio, joka vaihtaa parametrina
     * saadun solmun ja sen vasemman lapsen paikan puussa.
     *
     * @param solmu Solmu jonka suhteen kierto tehdään.
     * @return Alkuperäisen solmun paikalle siirretty solmu.
     */
    private PunamustaPuusolmu oikeaKierto(PunamustaPuusolmu solmu) {
        PunamustaPuusolmu solmu2 = solmu.getVasen();
        solmu2.setVanhempi(solmu.getVanhempi());
        solmu.setVanhempi(solmu2);
        solmu.setVasen(solmu2.getOikea());
        solmu2.setOikea(solmu);
        if (solmu.getVasen() != null) {
            solmu.getVasen().setVanhempi(solmu);
        }
        return solmu2;
    }

    /**
     * Puun tasapainotukseen käytettävä operaatio, joka vaihtaa parametrina
     * saadun solmun ja sen oikean lapsen paikan puussa.
     *
     * @param solmu Solmu jonka suhteen kierto tehdään.
     * @return Alkuperäisen solmun paikalle siirretty solmu.
     */
    private PunamustaPuusolmu vasenKierto(PunamustaPuusolmu solmu) {
        PunamustaPuusolmu solmu2 = solmu.getOikea();
        solmu2.setVanhempi(solmu.getVanhempi());
        solmu.setVanhempi(solmu2);
        solmu.setOikea(solmu2.getVasen());
        solmu2.setVasen(solmu);
        if (solmu.getOikea() != null) {
            solmu.getOikea().setVanhempi(solmu);
        }
        return solmu2;
    }

    /**
     * Palauttaa totuusarvon puun tyhjyydestä.
     *
     * @return True jos puu tyhjä, muulloin false.
     */
    public boolean onTyhja() {
        return juuri == null;
    }
}
