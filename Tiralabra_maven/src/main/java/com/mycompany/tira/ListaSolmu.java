package com.mycompany.tira;

/**
 * Käsilista käyttää listasolmua. Solmu tietää datakenttänään käsipari-olion sekä 
 * seuraavan listasolmun
 */
public class ListaSolmu {
    
    private Kasipari kasiPari;
    private ListaSolmu seuraava;

    /**
     * Asettaa luokkamuuttujat
     * 
     * @param lisattava Listasolmuun liitettävä käsipari-olio
     * @param seuraava solmua seuraava listasolmu
     */
    public ListaSolmu(Kasipari lisattava, ListaSolmu seuraava) {
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
     * @return ListaSolmu 
     */
    public ListaSolmu getSeuraavaListaSolmu() {
        return this.seuraava;
    }
    
    /**
     * Asettaa solmua seuraavan listasolmun
     * 
     * @param seuraavaSolmu ListaSolmu joka seuraa tätä listasolmua
     */
    public void setSeuraavaListaSolmu(ListaSolmu seuraavaSolmu) {
        this.seuraava = seuraavaSolmu;
    }
}
