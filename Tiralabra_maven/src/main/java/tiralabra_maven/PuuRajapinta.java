package tiralabra_maven;

/**
 * Puurajapinta, jossa määritellään puissa käytetyt metodit
 * @author esaaksvu
 */
public interface PuuRajapinta {
    
    /**
     * Hakee puista solmun arvon perusteella
     * @param i on solmun arvo jolla haetaan
     * @return viite solmuun jos löytyy, null jos ei
     */
    public Solmu hae(int i);
    
    /**
     * Poistaa solmun viitteen perusteella
     * @param pois on solmun viite
     */
    public void poistaSolmu(Solmu pois);

    /**
     * Lisää solmun puuhun
     * @param uusi on viite solmuun joka halutaan lisätä
     */
    public void lisaaSolmu(Solmu uusi);
    

}
