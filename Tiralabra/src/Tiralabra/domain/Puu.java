package Tiralabra.domain;

/** Määrittelee jokaisen puun perustoiminallisuudet.
 *
 * @author Pia Pakarinen
 */

public abstract class Puu {
    
    /** Puun juurisolmu.
     * 
     */
    private Solmu juuri;

    /** Luo uuden puun, juurena annetun arvon sisältämä solmu.
     * 
     * @param emo arvo puun juurisolmulle
     */
    public Puu(int emo) {
        this.juuri = new Solmu(emo);
    }
    
    /** Tulostaa puun solmujen arvot suuruusjärjestyksessä.
     * 
     * @return alkioiden arvot suuruusjärjestyksessä
     */
    public abstract String tulostaArvot();
    
    /** Lisää puuhun uuden solmun.
     * @param key puuhun lisättävä uusi arvo.
     */
    public abstract void insert(int key);
    
    /** Poistaa puusta solmun, joka sisältää annetun arvon.
     * 
     * @param key poistettavan solmun arvo.
     */
    public abstract void delete(int key);
    
    /** Palauttaa solmun, joka sisältää annetun arvon; null jos arvoa ei löydy puusta. 
     * @param key etsittävä arvo.
     * @return etsitty Solmu tai null-arvo, jos haettu arvo ei kuulu puuhun.
     */
    public abstract Solmu search(int key);
}