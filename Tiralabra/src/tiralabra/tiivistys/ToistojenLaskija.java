/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.tiivistys;

import tiralabra.tiedostonkasittely.TiedostonLukija;

/**
 * Laskee merkkien toistot tiedostossa.
 * @author joonaslongi
 */
public class ToistojenLaskija {
    private int[] toisto;
    
    /**
     *  Alustaa uuden laskijan. Max 256 eri ascii merkkiä.
     */
    
    public ToistojenLaskija(){
        toisto = new int[256];
    }
    
    /**
     * Käyttää lukijaa lukeakseen tiedoston ja tallettaa taulukkoon 
     * kyseisen merkin ascii muodon kohdalle sen toistojen määrän.
     * @param tiedosto 
     */
    
    public void laske(String tiedosto){
        TiedostonLukija lukija = new TiedostonLukija (tiedosto);
        while (lukija.vapaana() > 0){
            toisto[lukija.lue()]++;
        }
        lukija.sulje();
    }
    
    /**
     * Palauttaa toisto taulukon.
     * @return 
     */
    
    public int[] getToistot() {
        return this.toisto;

    }

}
