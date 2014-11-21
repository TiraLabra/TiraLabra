package com.mycompany.tira;

/**
 * Linkitetty lista käsipareista. Listalla on ennalta määrätty koko. Kun listan koko saavuttaa
 * enimmäiskoon, lista poistaa alusta jäseniä ja lisää uusia loppuun
 * 
 */
public class KasiLista {
    
    private ListaSolmu eka;
    private int listanKoko;
    private int maara;

    /**
     * Konstruktori alustaa luokan muuttujat
     * 
     * @param koko listan enimmäiskoko 
     */
    public KasiLista(int koko) {
        this.eka = null;
        this.listanKoko = koko;
        this.maara = 0;
    }
    
    /**
     * Lisää listaan käsiparin. Jos listan maksimikoko on saavutettu,
     *  poistetaan listan vanhin (ensimmäinen) alkio ja lisätään loppuun 
     * uusin lisättävä käsipari
     * 
     * @param lisattavaPari listaan lisättävä käsipari
     */
    public void lisaaKasipari(Kasipari lisattavaPari) {
        ListaSolmu lisattava = new ListaSolmu(lisattavaPari, null);
        ListaSolmu viimeinen = this.eka;
        
        if (this.maara == 0) {
            this.eka = lisattava;
        } else if (this.maara <= this.listanKoko) {
            for (int i=0; i<this.maara; i++) {
                viimeinen = viimeinen.getSeuraavaListaSolmu();
            }
            viimeinen.setSeuraavaListaSolmu(lisattava);
            this.maara++;
        } else {
            this.eka = this.eka.getSeuraavaListaSolmu();
            for (int i=0; i<this.listanKoko; i++) {
                viimeinen = viimeinen.getSeuraavaListaSolmu();
            }
            viimeinen.setSeuraavaListaSolmu(lisattava);
        }
    }
    
    /**
     * Palauttaa listan ensimmäisen jäsenen
     * 
     * @return listan ensimmäinen listasolmu
     */
    public ListaSolmu getEnsimmainenSolmu() {
        return this.eka;
    }
}
