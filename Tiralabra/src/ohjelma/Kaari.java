/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma;

/**
 *
 * @author kkivikat
 */
public class Kaari {
    // HUOM: Etäisyys siis kahden solmun välinen etäisyys
    private final Solmu alku;
    private final Solmu kohde;
    private int etaisyys;
    
    public Kaari(Solmu alku, Solmu kohde, int etaisyys) {
        this.alku = alku;
        this.kohde = kohde;
        this.etaisyys = etaisyys;
    }
    
    public Solmu getAlku() {
        return alku;
    }
    
    public Solmu getKohde() {
        return kohde;
    }
    
    public int getEtaisyys() {
        return etaisyys;
    }
}
