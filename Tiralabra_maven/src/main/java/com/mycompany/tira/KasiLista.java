package com.mycompany.tira;

public class KasiLista {
    
    private ListaSolmu eka;
    private int listanKoko;
    private int maara;
    
    public KasiLista(int koko) {
        this.eka = null;
        this.listanKoko = koko;
        this.maara = 0;
    }
    
    public void lisaaKasipari(Kasipari k) {
        ListaSolmu lisattava = new ListaSolmu(k, null);
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
    
    public ListaSolmu getEnsimmainenSolmu() {
        return this.eka;
    }
}
