package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Hakupuun ulospäin näkyvät toiminnot määrittävä rajapinta.
 *
 * @author Markus
 */
public interface Hakupuu {

    /**
     * Lisää parametrina saadun arvon puuhun.
     *
     * @param avain Lisättävä arvo.
     */
    public void lisaa(int avain);

    /**
     * Lisää kaikki parametrina saadun taulukon arvot puuhun.
     *
     * @param avaimet Lisättävät arvot.
     */
    public void lisaaKaikki(int[] avaimet);

    /**
     * Etsii parametrina saatua avainta vastaavan solmun puusta.
     *
     * @param avain Etsittävä arvo
     * @return Palauttaa true mikäli arvo löytyy puusta. Muulloin false.
     */
    public boolean hae(int avain);

    /**
     * Poistaa annettua avainta vastaavan solmun puusta.
     *
     * @param avain Poistettavan solmun avaimen arvo.
     */
    public void poista(int avain);

    /**
     * Kertoo onko puu tyhjä.
     *
     * @return True jos puu on tyhjä, muulloin false.
     */
    public boolean onTyhja();

    /**
     * Palauttaa puun juurisolmun.
     *
     * @return Puun juurisolmu tai null mikäli puu tyhjä.
     */
    public Puusolmu getJuuri();

    /**
     * Tyhjentää puun.
     */
    public void tyhjenna();

    /**
     * Palauttaa puun tyypin / nimen.
     *
     * @return Puun tyyppi.
     */
    public String getNimi();

    /**
     * Palauttaan puussa olevien solmujen määrän kokonaislukuna.
     *
     * @return Puussa olevien solmujen määrä.
     */
    public int getKoko();

    /**
     * Palauttaa puun korkeuden kokonaislukuna. Tyhjä puu palauttaa 0. Muulloin
     * korkeus on puun kerrosten lukumäärä.
     *
     * @return Puun korkeus.
     */
    public int getKorkeus();
}
