package tiralabra_maven;

/**
 * Puurajapinta, jossa määritellään puissa käytetyt metodit
 * @author esaaksvu
 */
public interface PuuRajapinta {

    /**
     * Metodi joka palauttaa puun päälimmäisen solmun
     * @return päälimmäinen solmu
     */
    public Solmu getJuuri();

     /**
     * Poistaa solmun arvon perusteella
     * @param i arvo joka poistetaan puusta
     * @return true jos poisto onnistui
     */
    public boolean poistaSolmu(int i);

   /**
     * Palauttaa tulostuksen puusta muodossa 
     *   1
     *  /\
     * 2  3 mutta keskeneräinen
     * @return tulostus koko puusta
     */
    public String tulostaPuu(Solmu s);

     /**
     * Lisää uuden solmun puuhun, tasapainoalgoritmi puuttuu vielä
     * @param uusi solmu joka lisätään puuhun
     */
    public void lisaaSolmu(Solmu uusi);

}
