package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Luokka joka sisältää puiden/puusolmujen käyttämiä staattisia metodeja.
 *
 * @author Markus
 */
public class PuuOperaatiot {

    /**
     * Laskee ja palauttaa annetusta juuresta alkavan puun solmujen määrän.
     *
     * @param juuri Juurisolmu puusta jonka solmujen määrä halutaan selvittää.
     * @return Puun solmujen määrä kokonaislukuna.
     */
    public static int koko(Puusolmu juuri) {
        if (juuri == null) {
            return 0;
        } else {
            return 1 + koko(juuri.getVasen()) + koko(juuri.getOikea());
        }
    }

    /**
     * Laskee ja palauttaa annetusta solmusta alkavan puun korkeuden.
     *
     * @param solmu Solmu jonka alipuun korkeus halutaan selvittää.
     * @return Puun korkeus kokonaislukuna.
     */
    public static int korkeus(Puusolmu solmu) {
        if (solmu == null) {
            return 0;
        } else {
            return 1 + Math.max(korkeus(solmu.getVasen()), korkeus(solmu.getOikea()));
        }
    }

    /**
     * Palauttaa avaimeltaan pienimmän parametrina saadusta solmusta alkavasta
     * alipuusta löytyvän solmun.
     *
     * @param solmu Puusolmu, jonka alipuusta arvoa etsitään.
     * @return Pienimmän avaimen omaava solmu.
     */
    public static Puusolmu pienin(Puusolmu solmu) {
        if (solmu != null) {
            while (solmu.getVasen() != null) {
                solmu = solmu.getVasen();
            }
        }
        return solmu;
    }

    /**
     * Palauttaa avaimeltaan suurimman parametrina saadusta solmusta alkavasta
     * alipuusta löytyvän solmun.
     *
     * @param solmu Puusolmu, jonka alipuusta arvoa etsitään.
     * @return Suurimman avaimen omaava solmu.
     */
    public static Puusolmu suurin(Puusolmu solmu) {
        if (solmu != null) {
            while (solmu.getOikea() != null) {
                solmu = solmu.getOikea();
            }
        }
        return solmu;
    }

    /**
     * Palauttaa parametrina saadun solmun seuraajan puussa, mikäli sellainen on
     * olemassa.
     *
     * @param solmu Solmu, jonka seuraaja halutaan selvittää
     * @return Parametrina saadun solmun seuraaja tai null, mikäli sellaista ei
     * löydy.
     */
    public static Puusolmu seuraaja(Puusolmu solmu) {
        if (solmu == null) {
            return null;
        }
        if (solmu.getOikea() != null) {
            return pienin(solmu.getOikea());
        }
        Puusolmu vanhempi = solmu.getVanhempi();
        while (onOikea(solmu)) {
            solmu = vanhempi;
            vanhempi = solmu.getVanhempi();
        }
        return vanhempi;
    }
    
    /**
     * Palauttaa parametrina saadun solmun edeltäjän puussa, mikäli sellainen on
     * olemassa.
     *
     * @param solmu Solmu, jonka edeltäjä halutaan selvittää
     * @return Parametrina saadun solmun edeltäjä tai null, mikäli sellaista ei
     * löydy.
     */
    public static Puusolmu edeltaja(Puusolmu solmu) {
        if (solmu == null) {
            return null;
        }
        if (solmu.getVasen() != null) {
            return suurin(solmu.getVasen());
        }
        Puusolmu vanhempi = solmu.getVanhempi();
        while (onVasen(solmu)) {
            solmu = vanhempi;
            vanhempi = solmu.getVanhempi();
        }
        return vanhempi;
    }

    /**
     * Tulostaa annetusta juuresta alkavan puun esijärjestyksessä.
     *
     * @param juuri Tulostettavan puun juuri.
     */
    public static void esijarjestys(Puusolmu juuri) {
        if (juuri != null) {
            System.out.print(juuri.getAvain() + " ");
            esijarjestys(juuri.getVasen());
            esijarjestys(juuri.getOikea());
        }
    }

    /**
     * Tulostaa annetusta juuresta alkavan puun sisäjärjestyksessä.
     *
     * @param juuri Tulostettavan puun juuri.
     */
    public static void sisajarjestys(Puusolmu juuri) {
        if (juuri != null) {
            sisajarjestys(juuri.getVasen());
            System.out.print(juuri.getAvain() + " ");
            sisajarjestys(juuri.getOikea());
        }
    }

    /**
     * Tulostaa annetusta juuresta alkavan puun jälkijärjestyksessä.
     *
     * @param juuri Tulostettavan puun juuri.
     */
    public static void jalkijarjestys(Puusolmu juuri) {
        if (juuri != null) {
            jalkijarjestys(juuri.getVasen());
            jalkijarjestys(juuri.getOikea());
            System.out.print(juuri.getAvain() + " ");
        }
    }
    
    /**
     * Palauttaa solmun isovanhemman mikäli sellainen on.
     * 
     * @param solmu Solmu jonka isovanhempi halutaan selvittää
     * @return Löydetty solmu tai null mikäli parametrina saadulla solmulla ei ole isovanhempaa
     */
    public static Puusolmu isovanhempi(Puusolmu solmu){
        return solmu.getVanhempi() == null ? null : solmu.getVanhempi().getVanhempi();
    }
    
    
    /**
     * Vaihtaa solmun ja sen vasemman lapsen paikkaa sekä korjaa näihin liittyvät viittaukset vastaamaan uutta tilannetta.
     * @param solmu Solmu jonka suhteen vaihto suoritetaan
     */
    public static void oikeaKierto(Puusolmu solmu){
         if(solmu == null ? true : solmu.getVasen() == null){
            return;
        }
        Puusolmu solmu2 = solmu.getVasen();
        if(solmu.getVanhempi() != null){
            if(onOikea(solmu)){
                solmu.getVanhempi().setOikea(solmu2);
            } else {
                solmu.getVanhempi().setVasen(solmu2);
            }
        }
        if(solmu2.getOikea() != null){
            solmu2.getOikea().setVanhempi(solmu);
        }
        solmu2.setVanhempi(solmu.getVanhempi());
        solmu.setVanhempi(solmu2);
        solmu.setVasen(solmu2.getOikea());
        solmu2.setOikea(solmu);
    }
    
    
    /**
     * Vaihtaa solmun ja sen oikean lapsen paikkaa sekä korjaa näihin liittyvät viittaukset vastaamaan uutta tilannetta.
     * @param solmu Solmu jonka suhteen vaihto suoritetaan
     */
    public static void vasenKierto(Puusolmu solmu){
        if(solmu == null ? true : solmu.getOikea() == null){
            return;
        }
        Puusolmu solmu2 = solmu.getOikea();
        if(solmu.getVanhempi() != null){
            if(onVasen(solmu)){
                solmu.getVanhempi().setVasen(solmu2);
            } else {
                solmu.getVanhempi().setOikea(solmu2);
            }
        }
        if(solmu2.getVasen() != null){
            solmu2.getVasen().setVanhempi(solmu);
        }
        solmu2.setVanhempi(solmu.getVanhempi());
        solmu.setVanhempi(solmu2);
        solmu.setOikea(solmu2.getVasen());
        solmu2.setVasen(solmu);
    }
    
    /**
     * Kertoo onko parametrin solmu jonkin solmun vasen lapsi
     * @param solmu Solmu josta jonka suhde vanhempaan halutaan selvittää
     * @return True mikäli solmu on vasen lapsi jollekin solmulle, muulloin false
     */
    public static boolean onVasen(Puusolmu solmu){
        return solmu == null ? false : solmu.getVanhempi() == null ? false : solmu.getVanhempi().getVasen() == solmu;
    }
    
    /**
     * Kertoo onko parametrin solmu jonkin solmun oikea lapsi
     * @param solmu Solmu josta jonka suhde vanhempaan halutaan selvittää
     * @return True mikäli solmu on oikea lapsi jollekin solmulle, muulloin false
     */
    public static boolean onOikea(Puusolmu solmu){
        return solmu == null ? false : solmu.getVanhempi() == null ? false : solmu.getVanhempi().getOikea() == solmu;
    }
    
    /**
     *  Palautaa solmun sisaruksen mikäli sellainen on.
     * @param solmu Solmu jonka sisarus selvitetään
     * @return  Tämän solmun sisarus tai NULL mikäli sellaista ei ole.
     */
    public static Puusolmu sisarus(Puusolmu solmu){
        return solmu == null ? null : solmu.getVanhempi() == null ? null : solmu == solmu.getVanhempi().getVasen() ? solmu.getVanhempi().getOikea() : solmu.getVanhempi().getVasen();
    }
}
