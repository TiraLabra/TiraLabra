package com.mycompany.tiralabra_maven;

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
     * palauttaa puun syvyyden
     * @return puun syvyys
     */
    public int getSyvyys();

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
    public String tulostaPuu();

     /**
     * Lisää uuden solmun puuhun, tasapainoalgoritmi puuttuu vielä
     * @param uusi solmu joka lisätään puuhun
     */
    public void lisaaSolmu(Solmu uusi);

}
