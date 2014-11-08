package com.mycompany.logiikka;

/**
 * Luokka kuvaa pelin kättä. Mahdollisia vaihtoehtoja ovat 
 * KIVI, PAPERI, SAKSET, LISKO JA SPOCK.
 * Luokka tietää mitkä kädet voittavat tämä instanssin
 */
public class Kasi {

    private String nimi;
    private Kasi tamaVoittaaYksi;
    private Kasi tamaVoittaaKaksi;

    /**
     * Konstruktori asettaa luokkamuuttujien arvot. 
     * 
     * @param nimi Käden nimi 
     * @param voittaa1 Ensimmäinen käsi joka voittaa tämän käden
     * @param voittaa2 Toinen käsi, joka voittaa tämän käden
     */
    public Kasi(String nimi, Kasi voittaa1, Kasi voittaa2) {
        this.nimi = nimi;
        this.tamaVoittaaYksi = voittaa1;
        this.tamaVoittaaKaksi = voittaa2;
    }
    
    /**
     * Konstruktori asettaa kädelle vain nimen. Lisää voittavat kädet metodien
     * kautta, jotta peli toimii.
     * 
     * @param nimi Käden nimi 
     */
    public Kasi(String nimi) {
        this.nimi = nimi;
    }

    /**
     * Lisää ensimmäisen tämän käden voittavan käden
     * 
     * @param k Voittava käsi 
     */
    public void lisaaVoittavaKasiYksi(Kasi k) {
        this.tamaVoittaaYksi = k;
    }
    
    /**
     * Lisää toisen tämän käden voittavan käden
     * 
     * @param k Voitttava käsi
     */
    public void lisaaVoittavaKasiKaksi(Kasi k) {
        this.tamaVoittaaKaksi = k;
    }
    /**
     * Palauttaa käden nimen
     *
     * @return nimi
     */
    public String getNimi() {
        return this.nimi;
    }
    
    /**
     * Kertoo voittaako tämä käsi argumenttina annetun käden.
     * Mikäli kädet ovat samat on tulos false.
     * 
     * @param voitettava käsi johon tätä kättä verrataan
     * @return true jos tämä käsi voittaa, muuten false
     */
    public boolean voittaako(Kasi voitettava) {
        if (this.tamaVoittaaYksi != null) {
            if (this.tamaVoittaaYksi.getNimi().equals(voitettava.getNimi())) {
                return false;
            }
        } 
        if (this.tamaVoittaaKaksi != null) {
            if (this.tamaVoittaaKaksi.getNimi().equals(voitettava.getNimi())) {
                return false;
            }
        }
        if (this.nimi.equals(voitettava.getNimi())) {
            return false;
        }
        return true;
    }
}
