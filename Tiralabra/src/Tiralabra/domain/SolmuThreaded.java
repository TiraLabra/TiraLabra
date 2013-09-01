package Tiralabra.domain;

/** Threaded-puiden käyttöön tarkoitettu solmu.
 * Tallentaa solmun perusominaisuuksien lisäksi sen, onko solmulle tallennettu lapsi todellinen lapsisolmu vai edeltäjä/seuraaja.
 * @author Pia Pakarinen
 */
public class SolmuThreaded{
    
    /**
     * Solmun sisältämä arvo.
     */
    private int arvo;
    
    /** Solmun oikea lapsisolmu.
     * 
     */
    private SolmuThreaded oikea;
    
    /**
     * Solmun vasen lapsi. 
     */
    private SolmuThreaded vasen;
    
    /**
     * Tarkistusarvo, joka kertoo onko solmun oikea lapsi aito lapsisolmu vai
     * ylempänä puussa sijaitseva seuraaja.
     */
    private boolean onkoOikeaLapsi;
    
    /**
     * Tarkistusarvo, joka kertoo onko solmun vasen lapsi aito lapsisolmu vai ylempänä
     * puussa sijaiseva edeltäjä.
     */
    private boolean onkoVasenLapsi;
    
    /**
     * Solmun vanhempi.
     */
    private SolmuThreaded parent;

    /** Luo uuden Threaded-puun käyttämän solmun annetulla arvolla.
     * 
     * @param key luotavan solmun sisältämä arvo
     */
    public SolmuThreaded(int key) {
        this.arvo = key;
        this.oikea = this.vasen = this.parent = null;
        this.onkoOikeaLapsi = this.onkoVasenLapsi = false;
    }

    /**
     * Palauttaa solmun vanhemman
     * @return solmun parent-solmu
     */
    public SolmuThreaded getParent() {
        return parent;
    }

    /**
     * Asettaa solmulle parent-solmun
     * @param parent solmun parent-solmu
     */
    public void setParent(SolmuThreaded parent) {
        this.parent = parent;
    }

    /**Asettaa solmulle uuden arvon.
     * 
     * @param arvo solmun uusi arvo
     */
    public void setKey(int arvo) {
        this.arvo = arvo;
    }
    
    /**
     * Asettaa solmun oikeanpuolisen lapsisolmun.
     * @param oikea asetettava lapsisolmu
     */
    public void setOikea(SolmuThreaded oikea) {
        this.oikea = oikea;
    }

    /** Asettaa solmulle vasemmanpuolisen lapsisolmun.
     * 
     * @param vasen asetettava lapsisolmu
     */
    public void setVasen(SolmuThreaded vasen) {
        this.vasen = vasen;
    }

    /**
     * Palauttaa solmun sisältämän arvon.
     * @return solmun arvo
     */
    public int getKey() {
        return this.arvo;
    }

    /**
     * Palauttaa solmun oikean lapsen.
     * @return oikea lapsisolmu
     */
    public SolmuThreaded getOikea() {
        return this.oikea;
    }

    /**
     * Palauttaa solmun vasemman lapsen.
     * @return vasen lapsisolmu
     */
    public SolmuThreaded getVasen() {
        return this.vasen;
    }
    
    /**
     * Kertoo, onko solmun oikea lapsi aito lapsisolmu vai seuraaja.
     * @return true jos solmu on lapsisolmu
     */
    public boolean oikeaStatusGet() {
        if (this.onkoOikeaLapsi) {
            return true;
        }
        return false;
    }
    
    /**
     * Kertoo, onko solmun vasen lapsi aito lapsisolmu vai edeltäjä.
     * @return true jos solmu on lapsisolmu
     */
    public boolean vasenStatusGet(){
        if (this.onkoVasenLapsi) {
            return true;
        }
        return false;
    }
    
    /**
     * Asettaa oikean lapsisolmun/seuraajan tilan.
     * @param tf true jos lapsi on aito
     */
    public void oikeanLapsenStatusSet(boolean tf){
        this.onkoOikeaLapsi = tf;
    }
    
    /**
     * Asettaa vasemman lapsisolmun/edeltäjän tilan.
     * @param tf true jos lapsi on aito
     */
    public void vasemmanLapsenStatusSet(boolean tf){
        this.onkoVasenLapsi = tf;
    }   
}