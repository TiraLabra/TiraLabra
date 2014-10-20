package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * AVL-Puun käyttämä solmu
 *
 * @author Markus
 */
public class AvlPuusolmu implements Puusolmu {

    private AvlPuusolmu vasen, oikea, vanhempi;
    private int avain, korkeus;

    /**
     * Luo uuden solmun ja asettaa asianmukaiset olio-muuttujien alkuarvot.
     *
     * @param avain Avaimen alkuarvo.
     */
    public AvlPuusolmu(int avain) {
        this.avain = avain;
        vasen = null;
        oikea = null;
        vanhempi = null;
        korkeus = 0;
    }

    public void setOikea(Puusolmu oikea) {
        this.oikea = (AvlPuusolmu)oikea;
    }

    public void setVasen(Puusolmu vasen) {
        this.vasen = (AvlPuusolmu)vasen;
    }

    public void setVanhempi(Puusolmu vanhempi) {
        this.vanhempi = (AvlPuusolmu)vanhempi;
    }

    public void setAvain(int avain) {
        this.avain = avain;
    }

    public int getAvain() {
        return avain;
    }

    public AvlPuusolmu getOikea() {
        return oikea;
    }

    public AvlPuusolmu getVasen() {
        return vasen;
    }

    public AvlPuusolmu getVanhempi() {
        return vanhempi;
    }

    /**
     * Palauttaa solmun korkeuden.
     *
     * @return Solmun korkeus kokonaislukuna.
     */
    public int getKorkeus() {
        return korkeus;
    }

    /**
     * Asettaa solmulle korkeuden.
     *
     * @param korkeus Uusi korkeuden arvo.
     */
    public void setKorkeus(int korkeus) {
        this.korkeus = korkeus;
    }

}
