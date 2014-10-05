package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Punamustan puun käyttämä solmu
 *
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

    /**
     * Asettaa oikean lapsen solmulle
     *
     * @param oikea Uusi oikea lapsi
     */
    @Override
    public void setOikea(Puusolmu oikea) {
        this.oikea = (PunamustaPuusolmu) oikea;
    }

    /**
     * Asettaa vasemman lapsen solmulle
     *
     * @param vasen Uusi vasen lapsi
     */
    @Override
    public void setVasen(Puusolmu vasen) {
        this.vasen = (PunamustaPuusolmu) vasen;
    }

    /**
     * Asettaa vanhemman solmulle
     *
     * @param vanhempi Uusi vanhempi
     */
    @Override
    public void setVanhempi(Puusolmu vanhempi) {
        this.vanhempi = (PunamustaPuusolmu) vanhempi;
    }

    /**
     * Asettaa parametrina saadun arvon solmun avaimeksi
     *
     * @param avain Uuden avaimen arvo
     */
    @Override
    public void setAvain(int avain) {
        this.avain = avain;
    }

    /**
     * Palauttaa solmun avaimen arvon
     *
     * @return Avaimen arvo
     */
    @Override
    public int getAvain() {
        return avain;
    }

    /**
     * Palauttaa solmun oikean lapsen
     *
     * @return Oikeana lapsena oleva solmu
     */
    @Override
    public PunamustaPuusolmu getOikea() {
        return oikea;
    }

    /**
     * Palauttaa solmun vasemman lapsen
     *
     * @return Vasempana lapsena oleva solmu
     */
    @Override
    public PunamustaPuusolmu getVasen() {
        return vasen;
    }

    /**
     * Palauttaa solmun vanhemman
     *
     * @return Vanhempana oleva solmu
     */
    @Override
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
     * @return Tämän solmun isovanhempi tai NULL mikäli sellaista ei ole.
     */
    public PunamustaPuusolmu getIsovanhempi() {
        return this.vanhempi == null ? null : this.vanhempi.getVanhempi();
    }

    /**
     * Palautaa solmun sisaruksen mikäli sellainen on.
     *
     * @return Tämän solmun sisarus tai NULL mikäli sellaista ei ole.
     */
    public PunamustaPuusolmu getSisarus() {
        return this.vanhempi == null ? null : this == vanhempi.getVasen() ? vanhempi.getOikea() : vanhempi.getVasen();
    }

    public PunamustaPuusolmu getSeta() {
        return this.vanhempi == null ? null : this.vanhempi.getSisarus();
    }
}
