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
     * Poistaa solmun arvon perusteella
     * @param i on solmun arvo
     * @return true jos poisto onnistuu
     */
    public boolean poistaSolmu(int i);

    /**
     * Lisää solmun puuhun
     * @param uusi on viite solmuun joka halutaan lisätä
     */
    public void lisaaSolmu(Solmu uusi);
    

}
