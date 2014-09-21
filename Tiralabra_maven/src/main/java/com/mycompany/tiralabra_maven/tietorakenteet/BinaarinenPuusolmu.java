package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Binäärihakupuiden käyttämä solmu
 *
 * @author Markus
 */
public class BinaarinenPuusolmu implements Puusolmu {

    private Puusolmu vasen, oikea, vanhempi;
    private int avain;

    /**
     * Luo uuden solmun ja asettaa asianmukaiset olio-muuttujien alkuarvot.
     *
     * @param avain Avaimen alkuarvo
     */
    public BinaarinenPuusolmu(int avain) {
        this.avain = avain;
        vasen = null;
        oikea = null;
        vanhempi = null;
    }

    /**
     * Asettaa oikean lapsen solmulle
     *
     * @param oikea Uusi oikea lapsi
     */
    @Override
    public void setOikea(Puusolmu oikea) {
        this.oikea = oikea;
    }

    /**
     * Asettaa vasemman lapsen solmulle
     *
     * @param vasen Uusi vasen lapsi
     */
    @Override
    public void setVasen(Puusolmu vasen) {
        this.vasen = vasen;
    }

    /**
     * Asettaa vanhemman solmulle
     *
     * @param vanhempi Uusi vanhempi
     */
    @Override
    public void setVanhempi(Puusolmu vanhempi) {
        this.vanhempi = vanhempi;
    }

    /**
     *  Asettaa parametrina saadun arvon solmun avaimeksi
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
    public Puusolmu getOikea() {
        return oikea;
    }

    /**
     * Palauttaa solmun vasemman lapsen
     *
     * @return Vasempana lapsena oleva solmu
     */
    @Override
    public Puusolmu getVasen() {
        return vasen;
    }

    /**
     * Palauttaa solmun vanhemman
     *
     * @return Vanhempana oleva solmu
     */
    @Override
    public Puusolmu getVanhempi() {
        return vanhempi;
    }
}
