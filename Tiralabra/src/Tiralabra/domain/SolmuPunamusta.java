package Tiralabra.domain;

/** Solmu punamustapuille.
 * Tallentaa solmun perusominaisuuksien lisäksi värin.
 * @author Pia Pakarinen
 */
public class SolmuPunamusta {
    
    /**
     * Solmun sisältämä arvo;
     */
    private int arvo;
    
    /**
     * Solmun oikea lapsi.
     */
    private SolmuPunamusta oikea;
    
    /**
     * Solmun vasen lapsi;
     */
    private SolmuPunamusta vasen;
    
    /**
     * Solmun vanhempi.
     */
    private SolmuPunamusta parent;
    
    /** Solmun väri.
     * True jos solmu on punainen, false jos musta.
     */
    private boolean punainen;

    /** Luo uuden punamusta-solmun.
     * 
     * @param key solmuun asetettava arvo
     * @param color solmun väri
     */
    public SolmuPunamusta(int key, boolean color) {
        this.arvo = key;
        this.punainen = color;
    }

    /**
     * Palauttaa solmun arvon.
     * @return solmun arvo
     */
    public int getArvo() {
        return arvo;
    }

    /**
     * Palauttaa solmun oikean lapsen.
     * @return oikea lapsi
     */
    public SolmuPunamusta getOikea() {
        return oikea;
    }

    /**
     * Palauttaa solmun vasemman lapsen.
     * @return vasen lapsi
     */
    public SolmuPunamusta getVasen() {
        return vasen;
    }

    /** Palauttaa solmun vanhemman.
     * 
     * @return parent-solmu
     */
    public SolmuPunamusta getParent() {
        return parent;
    }
    
    /** Palauttaa solmun värin; true jos punainen.
     * @return solmun väri
     */
    public boolean getVari(){
        return punainen;
    }

    /**
     * Asettaa solmulle arvon.
     * @param arvo solmun uusi arvo
     */
    public void setArvo(int arvo) {
        this.arvo = arvo;
    }

    /**
     * Asettaa solmulle oikea lapsen.
     * @param oikea uusi oikea lapsi
     */
    public void setOikea(SolmuPunamusta oikea) {
        this.oikea = oikea;
    }

    /** Asettaa solmun värin.
     * 
     * @param punainen solmun uusi väri
     */
    public void setVari(boolean punainen) {
        this.punainen = punainen;
    }

    /**
     * Asettaa solmulle vasemman lapsen.
     * @param oikea uusi vasen lapsi
     */
    public void setVasen(SolmuPunamusta vasen) {
        this.vasen = vasen;
    }

    /** Asettaa solmulle vanhemman.
     * 
     * @param parent solmun uusi parent-solmu
     */
    public void setParent(SolmuPunamusta parent) {
        this.parent = parent;
    }
    
}
