package com.mycompany.tira;

/**
 * Linkitetty lista käsipareista. Listalla on ennalta määrätty koko. Kun listan
 * koko saavuttaa enimmäiskoon, vanhin listan jäsen poistetaan ja uusin lisätään
 * listan alkuun. Toimintaperiaate on sama kuin pinolla
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
        this.maara = 0;
        this.listanKoko = koko;
    }

    /**
     * Palauttaa viimeksi lisätyn käsiparin
     * 
     * @return viimeisin käsipari
     */
    public Kasipari getViimeisinPari() {
        return this.eka.getKasipari();
    }

    /**
     * Palauttaa listan ensimmäisen solmun
     * 
     * @return ensimmäinen listasolmu
     */
    public ListaSolmu getEkaSolmu() {
        return this.eka;
    }
    
    /**
     * Lisää listaan käsiparin. Jos listan maksimikoko on saavutettu, poistetaan
     * listan vanhin alkio ja lisätään sitten uusi käsipari
     *
     * @param lisattavaPari listaan lisättävä käsipari
     */
    public void lisaaKasipari(Kasipari lisattavaPari) {
        ListaSolmu lisattava = new ListaSolmu(lisattavaPari, null);
        ListaSolmu viimeinen = this.eka;

        if (this.maara == 0) {
            this.eka = lisattava;
            this.maara++;
        } else {
            lisattava.setSeuraavaListaSolmu(this.eka);
            this.eka = lisattava;
            this.maara++;
        }
        if (this.maara > this.listanKoko) {
            for (int i = 0; i < this.listanKoko-1; i++) {
                viimeinen = viimeinen.getSeuraavaListaSolmu();
            }
            viimeinen.setSeuraavaListaSolmu(null);
        }
    }
}
