package com.mycompany.logiikka;

/**
 * Luokka pitää yllä pelin statistiikkaa ja tulostaa sen toString-pyynnöllä
 */
public class Statistiikka {

    private int kierrokset;
    private int pelaajanVoitot;
    private int tasapelit;

    /**
     * Konstruktori alustaa luokkamuuttujat
     */
    public Statistiikka() {
        this.kierrokset = 0;
        this.pelaajanVoitot = 0;
        this.tasapelit = 0;
    }

    /**
     * Päivitetään luokkamuuttujat kierroksen voittajan perusteella
     * 
     * @param voittaja int:
     * <ul>
     * <li> 1 kone voittaa
     * <li> 0 pelaaja voittaa
     * <li> -1 tasapeli
     * <ul>
     */
    public void update(int voittaja) {
        this.kierrokset++;
        if (voittaja == 0) {
            this.pelaajanVoitot++;
        } else if (voittaja == -1) {
            this.tasapelit++;
        }
    }

    /**
     * Tulostaa pelin statistiikan
     * 
     * @return toString()
     */
    @Override
    public String toString() {
        return "Kierroksia : " + this.kierrokset
                + ", pelaajan voittoja: " + this.pelaajanVoitot
                + ", tasapelejä: " + this.tasapelit + "";
    }
}
