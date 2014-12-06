package com.mycompany.tira;

/**
 * Käsilista käyttää listasolmua. Solmu tietää datakenttänään käsipari-olion sekä 
 * seuraavan listasolmun
 */
public class Listasolmu {
    
    private Kasipari kasiPari;
    private Listasolmu seuraava;

    /**
     * Asettaa luokkamuuttujat
     * 
     * @param lisattava Listasolmuun liitettävä käsipari-olio
     * @param seuraava solmua seuraava listasolmu
     */
    public Listasolmu(Kasipari lisattava, Listasolmu seuraava) {
        this.kasiPari = lisattava;
        this.seuraava = seuraava;
    }
    
    /**
     * Palautta listasolmun datan, eli käsiparin
     * 
     * @return Käsipari 
     */
    public Kasipari getKasipari() {
        return this.kasiPari;
    }
    
    /**
     * Palauttaa solmua seuraavan listasolmun
     * 
     * @return Listasolmu 
     */
    public Listasolmu getSeuraavaListaSolmu() {
        return this.seuraava;
    }
    
    /**
     * Asettaa solmua seuraavan listasolmun
     * 
     * @param seuraavaSolmu Listasolmu joka seuraa tätä listasolmua
     */
    public void setSeuraavaListaSolmu(Listasolmu seuraavaSolmu) {
        this.seuraava = seuraavaSolmu;
    }
}
