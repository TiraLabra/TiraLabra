
package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 *  Hakupuun ulospäin näkyvät toiminnot määrittävä rajapinta
 * 
 * @author Markus
 */
public interface Hakupuu {

    /**
     *  Lisää parametrina saadun arvon puuhun
     * @param arvo Lisättävä arvo
     */
    public void lisaa(int arvo);
    
    /**
     *  Lisää parametrina saadut arvot puuhun
     * @param arvot Lisättävät arvot
     */
    public void lisaaKaikki(int[] arvot);
    
    /**
     *  Etsii parametrina saatua arvoa vastaavan solmun puusta
     * @param arvo Etsittävä arvo
     * @return Palauttaa true mikäli arvo löytyy puusta. 
     */
    public boolean hae(int arvo);
    
    /**
     *  Poistaa annettua arvoa vastaavan solmun puusta
     * @param arvo Poistettavan arvo
     */
    public void poista(int arvo);
}
