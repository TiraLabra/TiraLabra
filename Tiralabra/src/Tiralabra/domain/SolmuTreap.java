package Tiralabra.domain;

/** Solmu Treapeille.
 * Tallettaa "normaalien" solmun ominaisuuksien lisäksi solmulle prioriteetin.
 * @author Pia Pakarinen
 */
class SolmuTreap {
    
    /**
     * Solmun vasen lapsi.
     */
    private SolmuTreap vasen;
    
    /**
     * Solmun oikea lapsi.
     */
    private SolmuTreap oikea;
    
    /**
     * Solmun vanhempi.
     */
    private SolmuTreap vanhempi;
    
    /**
     * Solmun talletettu arvo.
     */
    private int arvo;
    
    /**
     * Solmun prioriteetti puussa.
     */
    private int prioriteetti;

    /**
     * Luo uuden solmun ja asettaa sille arvon, prioriteetin ja vanhemman.
     * @param a solmun arvo
     * @param p solmun prioriteetti
     * @param v solmun vanhempi
     */
    public SolmuTreap(int a, int p, SolmuTreap v) {
        this.arvo = a;
        this.prioriteetti = p;
        this.vanhempi = v;
    }

    /**
     * Asettaa solmulle uuden arvon.
     * @param arvo solmun uusi arvo
     */
    public void setArvo(int arvo) {
        this.arvo = arvo;
    }

    /**
     * Palauttaa solmun arvon.
     * @return solmun arvo
     */
    public int getArvo() {
        return arvo;
    }

    /**
     * Palauttaa solmun oikea lapsen.
     * @return solmun oikea lapsi
     */
    public SolmuTreap getOikea() {
        return oikea;
    }

    /**
     * Palauttaa solmun vasemman lapsen.
     * @return solmun vasen lapsi
     */
    public SolmuTreap getVasen() {
        return vasen;
    }

    /**
     * Palauttaa solmun vanhempi-solmun.
     * @return solmun vanhempi
     */
    public SolmuTreap getVanhempi() {
        return vanhempi;
    }

    /**
     * Asettaa solmulle oikea lapsen.
     * @param oikea uusi oikea lapsi
     */
    public void setOikea(SolmuTreap oikea) {
        this.oikea = oikea;
        if (this.oikea != null) {
            this.oikea.setVanhempi(this);
        }
    }

    /**
     * Asettaa solmulle vanhemman.
     * @param vanhempi solmun vanhempi-solmu
     */
    public void setVanhempi(SolmuTreap vanhempi) {
        this.vanhempi = vanhempi;
    }

    /**
     * Asettaa solmulle vasemman lapsen.
     * @param vasen solmun uusi vasen lapsi
     */
    public void setVasen(SolmuTreap vasen) {
        this.vasen = vasen;
        if (this.vasen != null){
            this.vasen.setVanhempi(this);
        }
    }

    /**
     * Palauttaa solmun prioriteetin.
     * @return solmun prioriteetti
     */
    public int getPrioriteetti() {
        return prioriteetti;
    }

    /**
     * Asettaa solmulle prioriteetin.
     * @param prioriteetti solmun uusi prioriteetti
     */
    public void setPrioriteetti(int prioriteetti) {
        this.prioriteetti = prioriteetti;
    }
    
}
