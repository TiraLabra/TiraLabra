package wad.hakupuut;

import wad.solmu.Solmu;

/**
 *
 * Rajapinta määrittelee työssä käytettyjen hakupuiden jakamat metodit.
 * 
 */
public interface HakupuuRajapinta {
    
    /**
     * Metodi palauttaa haettavan solmun solmun avaimen perusteella
     * @param haettava, haettavan solmun avain
     * @return metodi palauttaa haetun solmun 
     */
    public Solmu hae(Object haettava);
    
    /**
     * Lisaa haluttua dataa puuhun.
     * @param lisattava lisättävä data puuhun
     * @return palauttaa lisätyn solmun.
     */
    public Solmu lisaa(Object lisattava);
    
    /**
     * Poistaa halutun solmun puusta.
     * @param poistettava poistettava solmu
     * @return Palauttaa poistetun solmun.
     */
    public Solmu poista(Object poistettava);
}
