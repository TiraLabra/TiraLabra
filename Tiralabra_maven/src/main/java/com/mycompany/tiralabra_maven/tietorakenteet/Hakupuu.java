
package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 *  Hakupuun ulospäin näkyvät toiminnot määrittävä rajapinta
 * 
 * @author Markus
 */
public interface Hakupuu {

    /**
     *  Lisää parametrina saadun avaimen puuhun
     * @param avain Lisättävä arvo
     */
    public void lisaa(int avain);
    
    /**
     *  Lisää parametrina saadut avaimet puuhun
     * @param avaimet Lisättävät arvot
     */
    public void lisaaKaikki(int[] avaimet);
    
    /**
     *  Etsii parametrina saatua avainta vastaavan solmun puusta
     * @param avain Etsittävä arvo
     * @return Palauttaa true mikäli arvo löytyy puusta. Muissa tapauksissa palautetaan false. 
     */
    public boolean hae(int avain);
    
    /**
     *  Poistaa annettua avainta vastaavan solmun puusta
     * @param avain Poistettavan arvo
     */
    public void poista(int avain);
    
}
