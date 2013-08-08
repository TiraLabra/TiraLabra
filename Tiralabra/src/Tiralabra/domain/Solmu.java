package Tiralabra.domain;

/**
 * Puun solmu, yksinkertaisin versio.
 *
 * @author Pia Pakarinen
 */
public class Solmu {

    /**
     * Solmun oikea lapsi.
     */
    private Solmu oikea;
    /**
     * Solmun vasen lapsi;
     */
    private Solmu vasen;
    /**
     * Solmun sisältämä numeroarvo.
     */
    private int key;

    /**
     * Luo uuden solmun annetulla arvolla, lapset alussa null-arvoisia.
     *
     * @param key solmuun talletettava arvo.
     */
    public Solmu(int key) {
        this.oikea = null;
        this.vasen = null;
        this.key = key;
    }

    /** Asettaa solmulle oikean lapsen.
     * 
     * @param oikea Solmulle asetettava oikea lapsi.
     */
    public void setOikea(Solmu oikea) {
        this.oikea = oikea;
    }

    /** Asettaa solmulle vasemman lapsen.
     * 
     * @param vasen Solmulle asetettava vasen lapsi.
     */
    public void setVasen(Solmu vasen) {
        this.vasen = vasen;
    }

    /** Palauttaa solmun sisältämän arvon.
     * @return key
     */
    public int getKey() {
        return key;
    }

    /** Palauttaa solmun oikea lapsen.
     * 
     * @return oikea
     */
    public Solmu getOikea() {
        return oikea;
    }

    /** Palauttaa solmun vasemman lapsen.
     * 
     * @return vasen
     */
    public Solmu getVasen() {
        return vasen;
    }
       
}