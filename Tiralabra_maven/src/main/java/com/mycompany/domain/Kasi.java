package com.mycompany.domain;

/**
 * Pelin Käsi-luokka. Kädellä on ainoastaan nimi.
 */
public class Kasi {

    private String nimi;

    /**
     * Konstruktori asettaa nimen kädelle
     * @param nimi kädelle annettava nimi
     */
    public Kasi(String nimi) {
        nimi = nimi.toUpperCase();
        this.nimi = nimi;
    }

    /**
     * Palauttaa käden nimen
     * @return nim
     */
    public String getNimi() {
        return this.nimi;
    }
}
