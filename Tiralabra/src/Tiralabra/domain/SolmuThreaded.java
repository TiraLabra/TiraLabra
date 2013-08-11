package Tiralabra.domain;

/** Threaded-puiden käyttöön tarkoitettu solmu.
 * Tallentaa solmun perusominaisuuksien lisäksi sen, onko solmulle tallennettu lapsi todellinen lapsisolmu vai edeltäjä/seuraaja.
 * @author Pia Pakarinen
 */
public class SolmuThreaded{
    
    private int arvo;
    private SolmuThreaded oikea;
    private SolmuThreaded vasen;
    private boolean onkoOikeaLapsi;
    private boolean onkoVasenLapsi;
    private SolmuThreaded parent;

    public SolmuThreaded(int key) {
        this.arvo = key;
        this.oikea = this.vasen = this.parent = null;
        this.onkoOikeaLapsi = this.onkoVasenLapsi = false;
    }

    public SolmuThreaded getParent() {
        return parent;
    }

    public void setParent(SolmuThreaded parent) {
        this.parent = parent;
    }

    public void setKey(int arvo) {
        this.arvo = arvo;
    }
    
    public void setOikea(SolmuThreaded oikea) {
        this.oikea = oikea;
    }

    
    public void setVasen(SolmuThreaded vasen) {
        this.vasen = vasen;
    }

    public int getKey() {
        return this.arvo;
    }

    public SolmuThreaded getOikea() {
        return this.oikea;
    }

    public SolmuThreaded getVasen() {
        return this.vasen;
    }
    
    public boolean oikeaStatusGet() {
        if (this.onkoOikeaLapsi) {
            return true;
        }
        return false;
    }
    
    public boolean vasenStatusGet(){
        if (this.onkoVasenLapsi) {
            return true;
        }
        return false;
    }
    
    public void oikeanLapsenStatusSet(boolean tf){
        this.onkoOikeaLapsi = tf;
    }
    
    public void vasemmanLapsenStatusSet(boolean tf){
        this.onkoVasenLapsi = tf;
    }   
}