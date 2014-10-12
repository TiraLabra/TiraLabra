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
            //Etsitään oikea paikka uudelle solmulle.
            while (true) {
                if (avain == solmu.getAvain()) {
                    return;
                } else if (avain < solmu.getAvain()) {
                    if (solmu.getVasen() == null) {
                        solmu.setVasen(uusi);
                        break;
                    } else {
                        solmu = solmu.getVasen();
                    }
                } else {
                    if (solmu.getOikea() == null) {
                        solmu.setOikea(uusi);
                        break;
                    } else {
                        solmu = solmu.getOikea();
                    }
                }
            }
            uusi.setVanhempi(solmu);
        }
        //Tutkitaan vaiheittain tarvitaanko tasapainotsta
        lisaaTapaus1(uusi);
    }

    /**
     * Lisäys mikäli uusi solmu on juuri
     *
     * @param solmu Uusi solmu
     */
    private void lisaaTapaus1(PunamustaPuusolmu solmu) {
        if (solmu.getVanhempi() == null) {
            solmu.setVari(Vari.MUSTA);
        } else {
            lisaaTapaus2(solmu);
        }
    }

    /**
     * Lisäys mikäli uuden solmun vanhempi on musta
     *
     * @param solmu Uusi solmu
     */
    private void lisaaTapaus2(PunamustaPuusolmu solmu) {
        if (vari(solmu.getVanhempi()) == Vari.MUSTA) {
            return;
        } else {
            lisaaTapaus3(solmu);
        }
    }

    /**
     * Lisäys mikäli solmun setä punainen
     *
     * @param solmu Uusi solmu
     */
    private void lisaaTapaus3(PunamustaPuusolmu solmu) {
        if (vari(solmu.getSeta()) == Vari.PUNAINEN) {
            solmu.getVanhempi().setVari(Vari.MUSTA);
            solmu.getSeta().setVari(Vari.MUSTA);
            solmu.getIsovanhempi().setVari(Vari.PUNAINEN);
            lisaaTapaus1(solmu.getIsovanhempi());
        } else {
            lisaaTapaus4(solmu);
        }
    }

    /**
     * Tehdään valmistelevat kierrot kaksoiskiertoa vaativissa tapauksissa
     *
     * @param solmu Uusi solmu
     */
    private void lisaaTapaus4(PunamustaPuusolmu solmu) {
        if (PuuOperaatiot.onOikea(solmu) && PuuOperaatiot.onVasen(solmu.getVanhempi())) {
            vasenKierto2(solmu.getVanhempi());
            solmu = solmu.getVasen();
        } else if (PuuOperaatiot.onVasen(solmu) && PuuOperaatiot.onOikea(solmu.getVanhempi())) {
            oikeaKierto2(solmu.getVanhempi());
            solmu = solmu.getOikea();
        }
        lisaaTapaus5(solmu);
    }

    /**
     * Suoritetaan kierto tasapainon korjaamiseksi
     *
     * @param solmu Uusi solmu
     */
    private void lisaaTapaus5(PunamustaPuusolmu solmu) {
        solmu.getVanhempi().setVari(Vari.MUSTA);
        solmu.getIsovanhempi().setVari(Vari.PUNAINEN);
        if (PuuOperaatiot.onVasen(solmu) && PuuOperaatiot.onVasen(solmu.getVanhempi())) {
            oikeaKierto2(solmu.getIsovanhempi());
        } else if (PuuOperaatiot.onOikea(solmu) && PuuOperaatiot.onOikea(solmu.getVanhempi())) {
            vasenKierto2(solmu.getIsovanhempi());
        }
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
    private PunamustaPuusolmu haeSolmu(int avain) {
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
        PunamustaPuusolmu lapsi = solmu.getVasen() == null ? solmu.getOikea() : solmu.getVasen();

        //Nostetaan poistettavaa seuraavaa alipuuta, mikäli sellainen on
        if (vari(solmu) == Vari.MUSTA) {
            solmu.setVari(vari(lapsi));
            //Korjataan tasapainoehto
            poistaTapaus1(solmu);
        }
        //Korvataan poistettava lapsella
        korvaaSolmu(solmu, lapsi);
    }

    /**
     * Tapaus jossa poistettava on juuri
     *
     * @param solmu Poistettava solmu
     */
    private void poistaTapaus1(PunamustaPuusolmu solmu) {
        if (solmu.getVanhempi() == null) {
            return;
        } else {
            poistaTapaus2(solmu);
        }
    }

    /**
     * Tapaus jossa poistettavalla on punainen sisarus
     *
     * @param solmu Poistettava solmu
     */
    private void poistaTapaus2(PunamustaPuusolmu solmu) {
        if (vari(solmu.getSisarus()) == Vari.PUNAINEN) {
            solmu.getVanhempi().setVari(Vari.PUNAINEN);
            solmu.getSisarus().setVari(Vari.MUSTA);
            if (PuuOperaatiot.onVasen(solmu)) {
                vasenKierto2(solmu.getVanhempi());
            } else {
                oikeaKierto2(solmu.getVanhempi());
            }
        }
        poistaTapaus3(solmu);
    }

    /**
     * Tapaus jossa solmun vanhempi, sisar ja sisarusken lapset mustia
     *
     * @param solmu Poistettava solmu
     */
    private void poistaTapaus3(PunamustaPuusolmu solmu) {
        if (vari(solmu.getVanhempi()) == Vari.MUSTA
                && vari(solmu.getSisarus()) == Vari.MUSTA
                && vari(solmu.getSisarus().getVasen()) == Vari.MUSTA
                && vari(solmu.getSisarus().getOikea()) == Vari.MUSTA) {
            solmu.getSisarus().setVari(Vari.PUNAINEN);
            poistaTapaus1(solmu.getVanhempi());
        } else {
            poistaTapaus4(solmu);
        }
    }

    /**
     * Tapaus jossa sisarus ja sen lapset mustia mutta vanhempi punainen
     *
     * @param solmu Poistettava solmu
     */
    private void poistaTapaus4(PunamustaPuusolmu solmu) {
        if (vari(solmu.getVanhempi()) == Vari.PUNAINEN
                && vari(solmu.getSisarus()) == Vari.MUSTA
                && vari(solmu.getSisarus().getVasen()) == Vari.MUSTA
                && vari(solmu.getSisarus().getOikea()) == Vari.MUSTA) {
            solmu.getSisarus().setVari(Vari.PUNAINEN);
            solmu.getVanhempi().setVari(Vari.MUSTA);
        } else {
            poistaTapaus5(solmu);
        }
    }

    /**
     * Tapaus jossa solmun sisarus on musta ja sisaruksella on punainen lapsi
     * solmun puolella
     *
     * @param solmu Poistettava solmu
     */
    private void poistaTapaus5(PunamustaPuusolmu solmu) {
        if (PuuOperaatiot.onVasen(solmu)
                && vari(solmu.getSisarus()) == Vari.MUSTA
                && vari(solmu.getSisarus().getVasen()) == Vari.PUNAINEN
                && vari(solmu.getSisarus().getOikea()) == Vari.MUSTA) {
            solmu.getSisarus().setVari(Vari.PUNAINEN);
            solmu.getSisarus().getVasen().setVari(Vari.MUSTA);
            oikeaKierto2(solmu.getSisarus());
        } else if (PuuOperaatiot.onOikea(solmu)
                && vari(solmu.getSisarus()) == Vari.MUSTA
                && vari(solmu.getSisarus().getOikea()) == Vari.PUNAINEN
                && vari(solmu.getSisarus().getVasen()) == Vari.MUSTA) {
            solmu.getSisarus().setVari(Vari.PUNAINEN);
            solmu.getSisarus().getOikea().setVari(Vari.MUSTA);
            vasenKierto2(solmu.getSisarus());
        }
        poistaTapaus6(solmu);
    }

    /**
     * Tapaus jossa Solmun sisarus on musta mutta sen lapsi joka on solmusta
     * vastakkaisella puolella on punainen.
     *
     * @param solmu Poistettava solmu
     */
    private void poistaTapaus6(PunamustaPuusolmu solmu) {
        solmu.getSisarus().setVari(vari(solmu.getVanhempi()));
        solmu.getVanhempi().setVari(Vari.MUSTA);
        if (PuuOperaatiot.onVasen(solmu)) {
            solmu.getSisarus().getOikea().setVari(Vari.MUSTA);
            vasenKierto2(solmu.getVanhempi());
        } else {
            solmu.getSisarus().getVasen().setVari(Vari.MUSTA);
            oikeaKierto2(solmu.getVanhempi());
        }
    }

    /**
     * Palauttaa totuusarvon puun tyhjyydestä.
     *
     * @return True jos puu tyhjä, muulloin false.
     */
    public boolean onTyhja() {
        return juuri == null;
    }

    /**
     * Palauttaa puun juuren
     *
     * @return Puun juuri-solmu
     */
    public Puusolmu getJuuri() {
        return this.juuri;
    }

    public void tyhjenna() {
        juuri = null;
    }

    public String getNimi() {
        return "Punamusta puu";
    }

    //APUTOIMINNOT
    /**
     * Parannettu kiertotoiminto vasempaan joka myös vaihtaa juuren
     *
     * @param solmu Solmu jonka suhteen kierto tehdään
     */
    private void vasenKierto2(PunamustaPuusolmu solmu) {
        PunamustaPuusolmu oikea = solmu.getOikea();
        korvaaSolmu(solmu, oikea);
        solmu.setOikea(oikea.getVasen());
        if (oikea.getVasen() != null) {
            oikea.getVasen().setVanhempi(solmu);
        }
        oikea.setVasen(solmu);
        solmu.setVanhempi(oikea);
    }

    /**
     * Parannettu kiertotoiminto oikeaan joka myös vaihtaa juuren
     *
     * @param solmu Solmu jonka suhteen kierto tehdään
     */
    private void oikeaKierto2(PunamustaPuusolmu solmu) {
        PunamustaPuusolmu vasen = solmu.getVasen();
        korvaaSolmu(solmu, vasen);
        solmu.setVasen(vasen.getOikea());
        if (vasen.getOikea() != null) {
            vasen.getOikea().setVanhempi(solmu);
        }
        vasen.setOikea(solmu);
        solmu.setVanhempi(vasen);
    }

    /**
     * Apumetodi jokavaihtaa viitaukset solmuihin päittäin. Parametrina saadut
     * solmut siis vaihtavat keskenään paikkaa puussa.
     *
     * @param vanha Alipuun juurena toiminut solmu.
     * @param uusi Solmu joka tulee olemaan uusi alipuun juuri.
     */
    private void korvaaSolmu(PunamustaPuusolmu vanha, PunamustaPuusolmu uusi) {
        if (vanha.getVanhempi() == null) {
            juuri = uusi;
        } else {
            if (PuuOperaatiot.onVasen(vanha)) {
                vanha.getVanhempi().setVasen(uusi);
            } else {
                vanha.getVanhempi().setOikea(uusi);
            }
        }
        if (uusi != null) {
            uusi.setVanhempi(vanha.getVanhempi());
        }
    }

    /**
     * Apumetodi joka tarkistaa solmun värin. Mikäli solmu on null palautetaan
     * musta.
     *
     * @param solmu Tarkistettavasolmu
     * @return Solmun väri tai Vari.MUSTA jossolmu null
     */
    private Vari vari(PunamustaPuusolmu solmu) {
        return solmu == null ? Vari.MUSTA : solmu.getVari();
    }

}
