package Tiralabra.domain;

import Tiralabra.util.ALista;

/** Toteuttaa solmun 2-3 B-puulle.
 * Solmulla voi olla useampia arvoja, ja korkeintaan kolme lasta.
 * @author Pia Pakarinen
 */
public class SolmuKaksiKolme {
    
    /**
     * Lista, joka tallentaa solmuun kuuluvat 1 tai 2 arvoa.
     */
    private ALista solmunarvot;
    
    /**
     * Oikeanpuolinen lapsisolmu.
     */
    private SolmuKaksiKolme oikea;
    
    /**
     * Vasemmanpuolinen lapsisolmu.
     */
    private SolmuKaksiKolme vasen;
    
    /**
     * Keskimmäinen lapsisolmu.
     */
    private SolmuKaksiKolme keski;

    /**
     * Solmun vanhempi.
     */
    private SolmuKaksiKolme vanh;
    
    /** Luo uuden solmun yhdellä arvolla.
     * 
     * @param key uuden solmun ensimmäinen arvo
     */
    public SolmuKaksiKolme(int key, SolmuKaksiKolme v) {
        this.solmunarvot = new ALista(key);
        this.vanh = v;
    }
    
    /**
     * Lisaa solmuun arvon.
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
    public SolmuKaksiKolme getOikea() {
        return oikea;
    }

    /**
     * Vasen lapsisolmu.
     * @return vasen lapsi
     */
    public SolmuKaksiKolme getVasen() {
        return vasen;
    }

    /**
     * Keskimmäinen lapsisolmu.
     * @return keskimmäinen lapsi
     */
    public SolmuKaksiKolme getKeski() {
        return keski;
    }

    /**
     * Asettaa keskimmäisen lapsen ja sille vanhemmaksi tämän solmun.
     * @param keski uusi keskimmäinen lapsisolmu
     */
    public void setKeski(SolmuKaksiKolme keski) {
        this.keski = keski;
        if (this.keski != null) {
            this.keski.setParent(this);
        }
    }

    /**
     * Asettaa oikean lapsen ja sille vanhemmaksi tämän solmun.
     * @param oikea uusi oikea lapsisolmu
     */
    public void setOikea(SolmuKaksiKolme oikea) {
        this.oikea = oikea;
        if (this.oikea != null) {
            this.oikea.setParent(this);
        }
    }

    /**
     * Asettaa vasemman lapsen, ja sille vanhemmaksi tämän solmun.
     * @param vasen uusi keskimmäinen lapsisolmu
     */
    public void setVasen(SolmuKaksiKolme vasen) {
        this.vasen = vasen;
        if (this.vasen != null) {
            this.vasen.setParent(this);
        }
    }

    /**
     * Palauttaa solmun ensimmäisen arvon.
     * @return solmun ensimmäinen ja pienin arvo
     */
    public int getEnsimmainenArvo() {
        return solmunarvot.getLista().getArvo();
    }
    
    /**
     * Palauttaa solmun toisen arvon.
     * Ei kutsuta, jos toista arvoa ei olemassa.
     * @return solmun toinen ja suurempi arvo
     */
    public int getToinenArvo(){
        return solmunarvot.getLista().getNext().getArvo();
    }

    /**
     * Palauttaa solmun vanhemman.
     * @return parent-solmu
     */
    public SolmuKaksiKolme getParent() {
        return vanh;
    }

    /**
     * Asettaa solmulle vanhemman.
     * @param vanh solmun uusi parent-solmu
     */
    public void setParent(SolmuKaksiKolme vanh) {
        this.vanh = vanh;
    }

    /**
     * Kertoo solmun koon; kuinka monta arvoa siihen kuuluu.
     */
    public int solmunKoko(){
        return this.solmunarvot.getKoko();
    }
    
}