package com.mycompany.tira;

/**
 * Dataelementti joka pitää sisällään kokonaislukumuodossa pelaajan ja
 * koneen kädet 
 */
public class Kasipari {
    
    private int pelaajanKasi;
    private int koneenKasi;

    /**
     * Alustaa käsiparin
     * 
     * @param pelaaja pelaajan käsi
     * @param kone tietokoneen käsi
     */
    public Kasipari(int pelaaja, int kone) {
        this.koneenKasi = kone;
        this.pelaajanKasi = pelaaja;
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
}
