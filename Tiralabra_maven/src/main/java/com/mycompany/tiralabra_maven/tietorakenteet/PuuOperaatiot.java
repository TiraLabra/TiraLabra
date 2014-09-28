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
        while (vanhempi != null && solmu == vanhempi.getOikea()) {
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
        while (vanhempi != null && solmu == vanhempi.getVasen()) {
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

}
