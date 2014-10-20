package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Punamustan puun implementaatio. Perii Ylihakupuun.
 *
 * @see YliHakupuu
 * @see http://en.wikipedia.org/wiki/Red%E2%80%93black_tree
 * @author Markus
 */
public class PunamustaPuu extends YliHakupuu {

    public void lisaa(int avain) {
        //Tehdään uusi solmu.
        PunamustaPuusolmu uusi = new PunamustaPuusolmu(avain);
        if (lisaa(uusi)) {
            lisaaTapaus1(uusi);
        }
        if (juuri != null) {
            ((PunamustaPuusolmu) juuri).setVari(Vari.MUSTA);
        }
    }

    /**
     * Lisäys mikäli uusi solmu on juuri. Asetetaan uusi solmu juureksi ja muita
     * toimia ei tarvita. Muuten jatketaan häntärekursiolla.
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
     * Lisäys mikäli uuden solmun vanhempi on musta. Tällöin mitään toimia ei
     * tarvita. Muuten jatketaan häntärekursiolla.
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
     * Lisäys mikäli solmun setä punainen. Vaihdetaan solmun vanhemman sekä
     * vanhemman sisaren sekä vanhemman väri ja palataan rekursiolla tapaukseen
     * 1. Muuten jatketaan häntärekursiolla.
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
     * Tapaus missä lisätty solmu on oikea lapsi ja sen vanhempi vasen lapsi tai
     * lisätty solmu on vasen lapsi ja sen vanhempi on oikea lapsi. Tällöin
     * suoritetaan kierto-operaatio. Kaikissa tapauksissa jatketaan
     * häntärekursiolla.
     *
     * @param solmu Uusi solmu
     */
    private void lisaaTapaus4(PunamustaPuusolmu solmu) {
        if (onOikea(solmu) && onVasen(solmu.getVanhempi())) {
            vasenKierto(solmu.getVanhempi());
            solmu = solmu.getVasen();
        } else if (onVasen(solmu) && onOikea(solmu.getVanhempi())) {
            oikeaKierto(solmu.getVanhempi());
            solmu = solmu.getOikea();
        }
        lisaaTapaus5(solmu);
    }

    /**
     * Aiempien vaiheiden johdosta jäljellä vain tapaus missä solmu ja sen
     * vanhempi ovat joko oikeita tai vasempia lapsia. Suoritetaan
     * kierto-operaatio ja vaihdetaan solmun vanhemman ja isovanhemman väri.
     *
     * @param solmu Uusi solmu
     */
    private void lisaaTapaus5(PunamustaPuusolmu solmu) {
        solmu.getVanhempi().setVari(Vari.MUSTA);
        solmu.getIsovanhempi().setVari(Vari.PUNAINEN);
        if (onVasen(solmu) && onVasen(solmu.getVanhempi())) {
            oikeaKierto(solmu.getIsovanhempi());
        } else if (onOikea(solmu) && onOikea(solmu.getVanhempi())) {
            vasenKierto(solmu.getIsovanhempi());
        }
    }

    /**
     * Poistaa avainta vastaavan arvon puusta, tekee tarvittavat
     * tasapainotustoimenpiteet ja asettaa juuren mustaksi.
     *
     * @param avain Puusta poistettava arvo.
     */
    @Override
    public void poista(int avain) {
        PunamustaPuusolmu solmu = (PunamustaPuusolmu) haeSolmu(avain);
        if (solmu == null) {
            return;
            // Jos solmulla 2 lasta vaihdetaan se itsensä edeltäjään.
        } else if (solmu.getVasen() != null && solmu.getOikea() != null) {
            PunamustaPuusolmu edeltaja = (PunamustaPuusolmu) edeltaja(solmu);
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
        if (juuri != null) {
            ((PunamustaPuusolmu) juuri).setVari(Vari.MUSTA);
        }
    }

    /**
     * Tapaus jossa poistettava on juuri. Nyt mitään toimenpiteitä ei tarvita.
     * Muuten jatketaan häntärekursiolla.
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
     * Tapaus jossa poistettavalla on punainen sisarus. Tällöin vaihdetaan
     * solmun vanhemman ja sisaren väri ja suoritetaan kierto. Kaikissa
     * tapauksissa jatketaan häntärekursiolla.
     *
     * @param solmu Poistettava solmu
     */
    private void poistaTapaus2(PunamustaPuusolmu solmu) {
        if (vari(solmu.getSisarus()) == Vari.PUNAINEN) {
            solmu.getVanhempi().setVari(Vari.PUNAINEN);
            solmu.getSisarus().setVari(Vari.MUSTA);
            if (onVasen(solmu)) {
                vasenKierto(solmu.getVanhempi());
            } else {
                oikeaKierto(solmu.getVanhempi());
            }
        }
        poistaTapaus3(solmu);
    }

    /**
     * Tapaus jossa solmun vanhempi, sisar ja sisaruksen lapset mustia. Tällöin
     * vaihdetaan sisaren väri mustaksi ja palataan rekursiolla tapaukseen 1
     * käsitellen solmun vanhempaa. Muuten jatketaan häntärekursiolla.
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
     * Tapaus jossa sisarus ja sen lapset mustia mutta vanhempi punainen.
     * Tällöin aihdetaan sisaren ja vanhemman väri. Muuten jatketaan
     * häntärekursiolla.
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
     * solmun puolella. Esim jos solmu on vasen lapsi ja sisaksen lapsi on oikea
     * lapsi. Tällöin vaihdetaan sisaren ja sen lapsen väri ja suoritetaan
     * kierto sen suhteen. Kaikissa tapauksissa jatketaan häntärekursiolla.
     *
     * @param solmu Poistettava solmu
     */
    private void poistaTapaus5(PunamustaPuusolmu solmu) {
        if (onVasen(solmu)
                && vari(solmu.getSisarus()) == Vari.MUSTA
                && vari(solmu.getSisarus().getVasen()) == Vari.PUNAINEN
                && vari(solmu.getSisarus().getOikea()) == Vari.MUSTA) {
            solmu.getSisarus().setVari(Vari.PUNAINEN);
            solmu.getSisarus().getVasen().setVari(Vari.MUSTA);
            oikeaKierto(solmu.getSisarus());
        } else if (onOikea(solmu)
                && vari(solmu.getSisarus()) == Vari.MUSTA
                && vari(solmu.getSisarus().getOikea()) == Vari.PUNAINEN
                && vari(solmu.getSisarus().getVasen()) == Vari.MUSTA) {
            solmu.getSisarus().setVari(Vari.PUNAINEN);
            solmu.getSisarus().getOikea().setVari(Vari.MUSTA);
            vasenKierto(solmu.getSisarus());
        }
        poistaTapaus6(solmu);
    }

    /**
     * Tapaus jossa Solmun sisarus on musta mutta sen lapsi joka on solmusta
     * vastakkaisella puolella on punainen. Esim jos solmu on vasen lapsi ja
     * sisaksen lapsi on myös vasen lapsi. Tällöin asetetaan sisaren lapsi
     * mustaksi ja kierretään solmun vanhemman suhteen.
     *
     * @param solmu Poistettava solmu
     */
    private void poistaTapaus6(PunamustaPuusolmu solmu) {
        solmu.getSisarus().setVari(vari(solmu.getVanhempi()));
        solmu.getVanhempi().setVari(Vari.MUSTA);
        if (onVasen(solmu)) {
            solmu.getSisarus().getOikea().setVari(Vari.MUSTA);
            vasenKierto(solmu.getVanhempi());
        } else {
            solmu.getSisarus().getVasen().setVari(Vari.MUSTA);
            oikeaKierto(solmu.getVanhempi());
        }
    }

    public String getNimi() {
        return "Punamusta puu";
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
