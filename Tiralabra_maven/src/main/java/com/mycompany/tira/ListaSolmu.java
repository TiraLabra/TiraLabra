package com.mycompany.tira;

public class ListaSolmu {
    
    private Kasipari kasiPari;
    private ListaSolmu seuraava;
    
    public ListaSolmu(Kasipari lisattava, ListaSolmu seuraava) {
        this.kasiPari = lisattava;
        this.seuraava = seuraava;
    }
    
    public Kasipari getKasipari() {
        return this.kasiPari;
    }
    
    public ListaSolmu getSeuraavaListaSolmu() {
        return this.seuraava;
    }
    
    public void setSeuraavaListaSolmu(ListaSolmu s) {
        this.seuraava = s;
    }
}
