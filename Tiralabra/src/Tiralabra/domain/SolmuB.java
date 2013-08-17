package Tiralabra.domain;

import Tiralabra.util.ALista;

/** Toteuttaa solmun 2-3 B-puulle.
 * Solmulla voi olla useampia arvoja, ja korkeintaan kolme lasta.
 * @author Pia Pakarinen
 */
public class SolmuB {
    
    /**
     * Lista, joka tallentaa solmuun kuuluvat 1 tai 2 arvoa.
     */
    private ALista solmunarvot;
    
    /**
     * Oikeanpuolinen lapsisolmu.
     */
    private SolmuB oikea;
    
    /**
     * Vasemmanpuolinen lapsisolmu.
     */
    private SolmuB vasen;
    
    /**
     * Keskimmäinen lapsisolmu.
     */
    private SolmuB keski;

    /** Luo uuden solmun yhdellä arvolla.
     * 
     * @param key uuden solmun ensimmäinen arvo
     */
    public SolmuB(int key) {
        solmunarvot = new ALista(key);
    }
    
    /**
     * Lisaa solmuun toisen arvon.
     * @param k solmulle asetettava uusi arvo
     */
    public void lisaaArvo(int k){
        this.solmunarvot.lisaa(k);
    }
    
    /** Poistaa ja palauttaa annetun arvon solmusta.
     * Palauttaa annetun arvon vastaluvun jos poisto ei onnistunut.
     * @param k poistettava arvo
     * @return k jos poisto onnistui, k * -1 jos arvoa ei löydy solmusta
     */
    public int poistaArvo(int k){
        if (this.solmunarvot.poista(k)){
            return k;
        }
        return k * -1;
    }

    /**
     * Oikea lapsisolmu.
     * @return oikea lapsi
     */
    public SolmuB getOikea() {
        return oikea;
    }

    /**
     * Vasen lapsisolmu.
     * @return vasen lapsi
     */
    public SolmuB getVasen() {
        return vasen;
    }

    /**
     * Keskimmäinen lapsisolmu.
     * @return keskimmäinen lapsi
     */
    public SolmuB getKeski() {
        return keski;
    }

    /**
     * Asettaa keskimmäisen lapsen.
     * @param keski uusi keskimmäinen lapsisolmu
     */
    public void setKeski(SolmuB keski) {
        this.keski = keski;
    }

    /**
     * Asettaa oikean lapsen.
     * @param oikea uusi oikea lapsisolmu
     */
    public void setOikea(SolmuB oikea) {
        this.oikea = oikea;
    }

    /**
     * Asettaa vasemman lapsen.
     * @param vasen uusi keskimmäinen lapsisolmu
     */
    public void setVasen(SolmuB vasen) {
        this.vasen = vasen;
    }

    /**
     * Palauttaa listan solmun arvoista.
     * @return solmun arvot
     */
    public ALista getSolmunarvot() {
        return solmunarvot;
    }
}