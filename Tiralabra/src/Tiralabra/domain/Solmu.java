package Tiralabra.domain;

/**
 * Puun solmu, yksinkertaisin versio.
 *
 * @author Pia Pakarinen
 */
public interface Solmu {

    /** Asettaa solmulle oikean lapsen.
     * 
     * @param oikea Solmulle asetettava oikea lapsi.
     */
    public void setOikea(Solmu oikea);

    /** Asettaa solmulle vasemman lapsen.
     * 
     * @param vasen Solmulle asetettava vasen lapsi.
     */
    public void setVasen(Solmu vasen);

    /** Palauttaa solmun sisältämän arvon.
     * @return key
     */
    public int getKey();

    /** Palauttaa solmun oikea lapsen.
     * 
     * @return oikea
     */
    public Solmu getOikea();

    /** Palauttaa solmun vasemman lapsen.
     * 
     * @return vasen
     */
    public Solmu getVasen();
       
}