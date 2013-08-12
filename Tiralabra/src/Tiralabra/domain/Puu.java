package Tiralabra.domain;

/** Määrittelee jokaisen puun perustoiminallisuudet.
 *
 * @author Pia Pakarinen
 */

public interface Puu {    
    
    /** Antaa String-muotoisen esityksen puun solmujen arvoista suuruusjärjestyksessä.
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
    
    /** Katsoo löytyykö puusta etsittyä arvoa; palauttaa false jos arvoa ei löydy puusta. 
     * @param key etsittävä arvo.
     * @return true jos arvo esiintyy puussa, false jos ei
     */
    public boolean search(int key);
}