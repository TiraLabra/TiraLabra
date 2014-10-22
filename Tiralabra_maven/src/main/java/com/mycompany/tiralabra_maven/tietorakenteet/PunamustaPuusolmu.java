package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Punamustan puun käyttämä solmu.
 *
 * @see PunamustaPuu
 * @author Markus
 */
public class PunamustaPuusolmu implements Puusolmu {

    private PunamustaPuusolmu vasen, oikea, vanhempi;
    private int avain;
    private Vari vari;

    /**
     * Luo uuden solmun ja asettaa asianmukaiset olio-muuttujien alkuarvot.
     *
     * @param avain Avaimen alkuarvo
     */
    public PunamustaPuusolmu(int avain) {
        this.avain = avain;
        vasen = null;
        oikea = null;
        vanhempi = null;
        vari = Vari.PUNAINEN;
    }

    public void setOikea(Puusolmu oikea) {
        this.oikea = (PunamustaPuusolmu) oikea;
    }

    public void setVasen(Puusolmu vasen) {
        this.vasen = (PunamustaPuusolmu) vasen;
    }

    public void setVanhempi(Puusolmu vanhempi) {
        this.vanhempi = (PunamustaPuusolmu) vanhempi;
    }

    public void setAvain(int avain) {
        this.avain = avain;
    }

    public int getAvain() {
        return avain;
    }

    public PunamustaPuusolmu getOikea() {
        return oikea;
    }

    public PunamustaPuusolmu getVasen() {
        return vasen;
    }

    public PunamustaPuusolmu getVanhempi() {
        return vanhempi;
    }

    /**
     * Palauttaa solmun värin.
     *
     * @return Vari PUNAINEN tai MUSTA riippuen solmun väristä.
     */
    public Vari getVari() {
        return vari;
    }

    /**
     * Asettaa solmun värin
     *
     * @param vari Uusi väri
     */
    public void setVari(Vari vari) {
        this.vari = vari;
    }

    /**
     * Palauttaa solmun isovanhemman mikäli sellainen on.
     *
     * @return Tämän solmun isovanhempi tai null mikäli sellaista ei ole.
     */
    public PunamustaPuusolmu getIsovanhempi() {
        return this.vanhempi == null ? null : this.vanhempi.getVanhempi();
    }

    /**
     * Palautaa solmun sisaruksen mikäli sellainen on.
     *
     * @return Tämän solmun sisarus tai null mikäli sellaista ei ole.
     */
    public PunamustaPuusolmu getSisarus() {
        return this.vanhempi == null ? null : (this == vanhempi.getVasen() ? vanhempi.getOikea() : vanhempi.getVasen());
    }

    /**
     * Palauttaa solmun joka on tämän solmun vanhemman sisarus, mikäli sellainen
     * on.
     *
     * @return Tämän solmun setä-solmu tai null mikäli sitä ei ole.
     */
    public PunamustaPuusolmu getSeta() {
        return this.vanhempi == null ? null : this.vanhempi.getSisarus();
    }
}
