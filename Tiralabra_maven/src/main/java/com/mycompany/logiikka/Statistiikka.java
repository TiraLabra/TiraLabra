package com.mycompany.logiikka;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka pitää yllä pelin statistiikkaa
 * 
 */
public class Statistiikka {
    private List<Kasi> pelaajanPelaamatKadet;
    private int kierroksia;
    private int pelaajanVoittoja;
    private int tasapeleja;
    private Logiikka logiikka;
    private boolean pelaajaVoittiViimeksi;
    
    /**
     * Konstruktori alustaa luokkamuuttuja
     */
    public Statistiikka() {
        this.pelaajanPelaamatKadet = new ArrayList<Kasi>();
        this.kierroksia = 0;
        this.pelaajanVoittoja = 0;
        this.tasapeleja = 0;
        this.logiikka = new Logiikka();
    }

    /**
     * Antaa listana pelaajan pelaamat kädet
     * 
     * @return pelaajan kädet 
     */
    public List<Kasi> getPelatutKadet() {
        return this.pelaajanPelaamatKadet;
    }
    
    /**
     * Päivittää statistiikkaa yhdeltä pelatulta kierrokselta
     * 
     * @param k1 pelaajan kierroksen käsi
     * @param k2 tekoälyn käsi
     */
    public void paivita(Kasi k1, Kasi k2) {
        this.kierroksia++;
        this.pelaajanPelaamatKadet.add(k1);
        int apu = this.logiikka.voittaako(k1, k2);
        if (apu == 0) {
            this.tasapeleja++;
            this.pelaajaVoittiViimeksi = false;
        } else if (apu == 1) {
            this.pelaajanVoittoja++;
            this.pelaajaVoittiViimeksi = true;
        }
    }
    
    /**
     * Kertoo, jos pelaaja voitti viimeisimmäin kierroksen
     * 
     * @return true, jos pelaaja voitti 
     */
    public boolean voittikoPelaajaViimeksi() {
        return this.pelaajaVoittiViimeksi;
    }
    
    /**
     * Palauttaa pelattujen kierrosten määrän
     * 
     * @return kierrosten määrä 
     */
    public int getKierrokset() {
        return this.kierroksia;
    }
    
    /**
     * Kertoo montako kertaa pelaaja on voittanut tässä pelissä
     * 
     * @return voittojen määrä 
     */
    public int getPelaajanVoittoja() {
        return this.pelaajanVoittoja;
    }
}
