package Tiralabra.domain;

/** Määrittelee jokaisen puun perustoiminallisuudet.
 *
 * @author Pia Pakarinen
 */

public interface Puu {    
    
    /** Tulostaa puun solmujen arvot suuruusjärjestyksessä.
     * 
     * @return alkioiden arvot suuruusjärjestyksessä
     */
    public String tulostaArvot();
    
    /** Lisää puuhun uuden solmun.
     * @param key puuhun lisättävä uusi arvo.
     */
    public void insert(int key);
    
    /** Poistaa puusta solmun, joka sisältää annetun arvon.
     * 
     * @param key poistettavan solmun arvo.
     */
    public void delete(int key);
    
    /** Palauttaa solmun, joka sisältää annetun arvon; null jos arvoa ei löydy puusta. 
     * @param key etsittävä arvo.
     * @return etsitty Solmu tai null-arvo, jos haettu arvo ei kuulu puuhun.
     */
    public Solmu search(int key);
}