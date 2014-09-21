
package com.mycompany.tiralabra_maven.tyokalut;

/**
 *  Luokka jonka tarkoitus on helpottaa mittaustulosten käsittelyä.
 * @author Markus
 */
public class Vertailu {
    private Mittaustulos[] tulokset;
    private String kuvaus;

    /**
     * Luo uuden vertailun jolla on kuvaus ja tuntematon määrä mittaustuloksia
     * @param kuvaus Kuvaus siitä mitä verrataan
     * @param tulokset Verrattavat tulokset
     */
    public Vertailu(String kuvaus, Mittaustulos... tulokset) {
        this.tulokset = tulokset;
        this.kuvaus = kuvaus;
    }
    
  

    @Override
    public String toString() {
        //TODO Tee tähän jotain hienoa...
        return "UPEAN HIENO VERTAILUTAULUKKO!";
    }
    
    
    
    
}
