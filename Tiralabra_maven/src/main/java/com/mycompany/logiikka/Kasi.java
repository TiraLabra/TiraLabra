package com.mycompany.logiikka;

/**
 * Luokka kuvaa pelin kättä. Mahdollisia vaihtoehtoja ovat 
 * KIVI, PAPERI, SAKSET, LISKO JA SPOCK
 */
public class Kasi {
    private String nimi;
    
    /**
     * Konstruktori asettaa kädelle nimen. Nimen tulee olla 
     * joku seuraavista:
     * KIVI, PAPERI, SAKSET, LISKO JA SPOCK
     * 
     * Konstruktori ei tee virheentarkistusta
     * 
     * @param nimi käden nimi 
     */
    public Kasi(String nimi) {
        this.nimi = nimi;
    }
    
    /**
     * Palauttaa käden nimen
     * 
     * @return nimi 
     */
    public String getNimi() {
        return this.nimi;
    }
}
