package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Binäärihakupuiden käyttämä solmu.
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


    
    public void setOikea(Puusolmu oikea) {
        this.oikea = oikea;
    }

 
    public void setVasen(Puusolmu vasen) {
        this.vasen = vasen;
    }

    public void setVanhempi(Puusolmu vanhempi) {
        this.vanhempi = vanhempi;
    }

    public void setAvain(int avain) {
        this.avain = avain;
    }

    public int getAvain() {
        return avain;
    }

    public Puusolmu getOikea() {
        return oikea;
    }

    public Puusolmu getVasen() {
        return vasen;
    }

    public Puusolmu getVanhempi() {
        return vanhempi;
    }
}
