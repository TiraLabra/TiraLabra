
package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 *
 * @author Markus
 */
public interface Puusolmu {

    /**
     * Palauttaa solmun avaimen arvon
     *
     * @return Avaimen arvo
     */
    int getAvain();

    /**
     * Palauttaa solmun oikean lapsen
     *
     * @return Oikeana lapsena oleva solmu
     */
    Puusolmu getOikea();

    /**
     * Palauttaa solmun vanhemman
     *
     * @return Vanhempana oleva solmu
     */
    Puusolmu getVanhempi();

    /**
     * Palauttaa solmun vasemman lapsen
     *
     * @return Vasempana lapsena oleva solmu
     */
    Puusolmu getVasen();

    /**
     *  Asettaa parametrina saadun arvon solmun avaimeksi
     * @param avain Uuden avaimen arvo
     */
    void setAvain(int avain);

    /**
     * Asettaa oikean lapsen solmulle
     *
     * @param oikea Uusi oikea lapsi
     */
    void setOikea(Puusolmu oikea);

    /**
     * Asettaa vanhemman solmulle
     *
     * @param vanhempi Uusi vanhempi
     */
    void setVanhempi(Puusolmu vanhempi);

    /**
     * Asettaa vasemman lapsen solmulle
     *
     * @param vasen Uusi vasen lapsi
     */
    void setVasen(Puusolmu vasen);
    
}
