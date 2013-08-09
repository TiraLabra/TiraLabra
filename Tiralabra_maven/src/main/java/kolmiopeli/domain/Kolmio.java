
package kolmiopeli.domain;

import java.awt.Color;



/**
 * 
 * 
 *  Kolmiopelin perusrakenne, kolmio.
 * 
 * @author Eemi
 */

public class Kolmio {
    private Color kolmionVari;
    private int sijaintiRivi;
    private int sijaintiSarake;
    /**
     *  @param On true jos kolmio osoittaa ylospain
     */
    private boolean osoittaakoKolmioYlospain; // Talla voi tarkistaa esim Siirrot -luokassa mitka sirrot kolmiolle voi tehda


    
    /**
     * 
     * Kolmion vari arvotaan ennen kun konstruktoria kutsutaan.
     */
     
    public Kolmio(Color vari, int sijaintiRivi, int sijaintiSarake) {
        this.kolmionVari = vari;
        this.sijaintiRivi = sijaintiRivi;
        this.sijaintiSarake = sijaintiSarake;
        setOsoittaakoKolmioYlospain(sijaintiRivi, sijaintiSarake);
    }

    public Color getKolmionVari() {
        return kolmionVari;
    }

    public void setKolmionVari(Color kolmionVari) {
        this.kolmionVari = kolmionVari;
    }

    public int getSijaintiRivi() {
        return sijaintiRivi;
    }

    public int getSijaintiSarake() {
        return sijaintiSarake;
    }
    /**
     *  Asettaa kolmion sijainnin uusiksi.
     */
    public void setSijainti(int sijaintiRivi, int sijaintiSarake) {
        this.sijaintiRivi = sijaintiRivi;
        this.sijaintiSarake = sijaintiSarake;
        setOsoittaakoKolmioYlospain(sijaintiRivi, sijaintiSarake);
    }

    
    private void setOsoittaakoKolmioYlospain(int sijaintiRivi, int sijaintiSarake) {
        if ((sijaintiRivi + sijaintiSarake) % 2 == 0) {
            this.osoittaakoKolmioYlospain = true;
        } else {
            this.osoittaakoKolmioYlospain = false;
        }
    }
        
    /**
     * 
     * @return True jos kolmio osoittaa ylospain.
     */
    public boolean osoittaakoKolmioYlospain() {
        return osoittaakoKolmioYlospain;
    }

    public Koordinaatti getKoordinaatti() {
        return new Koordinaatti(this.sijaintiRivi, this.sijaintiSarake);
    }
    
    
    
}
