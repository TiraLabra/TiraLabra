package com.mycompany.tira;

/**
 * Dataelementti joka pitää sisällään kokonaislukumuodossa pelaajan ja
 * koneen kädet sekä kumpi voitti em. käsillä
 * 
 */
public class Kasipari {
    
    private int pelaajanKasi;
    private int koneenKasi;
    private int voittaja;

    /**
     * Alustaa luokkamuuttujat
     * Käsien kokonaislukuarvot
     * <ul>
     * <li>0 = Kivi
     * <li>1 = Paperi
     * <li>2 = Sakset
     * <li>3 = Lisko
     * <li>4 = Spock
     * </ul>
     * Voittoa kuvaava kokonaisluku
     * <ul>
     * <li> 1 = Pelaaja voitti
     * <li> 0 = Tasapeli
     * <li> -1 = Tekoäly voitti
     * </ul>
     * 
     * @param pelaaja Pelaajan kättä vastaava kokonaisluku
     * @param kone Tekoälyn kättä vastaava kokonaisluku
     * @param voittaja voittoa kuvaava kokonaisluku
     */
    public Kasipari(int pelaaja, int kone, int voittaja) {
        this.koneenKasi = kone;
        this.pelaajanKasi = pelaaja;
        this.voittaja = voittaja;
    }

    /**
     * Palauttaa pelaajan kättä vastaavan kokonaisluvun
     * 
     * @return Kättä vastaava kokonaisluku 
     */
    public int getPelaajanKasi() {
        return pelaajanKasi;
    }

    /**
     * Palauttaa tekoälyn kättä vastaavan kokonaisluvun
     * 
     * @return Kättä vastaava kokonaisluku 
     */
    public int getKoneenKasi() {
        return koneenKasi;
    }

    /**
     * Palauttaa voittotilannetta vastaavan kokonaisluvun
     * 
     * @return Voittoa kuvaava kokonaisluku 
     */
    public int getVoittaja() {
        return voittaja;
    }
}
