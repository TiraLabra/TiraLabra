/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

/**
 *
 * @author joonaslongi
 */
public class Laskija {
    private int[] toisto;
    
    public Laskija(){
        toisto = new int[256];
    }
    
    public void laske(String tiedosto){
        Lukija lukija = new Lukija (tiedosto);
        while (lukija.vapaana() > 0){
            toisto[lukija.lue()]++;
        }
        lukija.sulje();
    }
    public int[] getToistot() {
        return this.toisto;
    }
    
   
    
    
    
}
