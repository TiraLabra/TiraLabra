package com.mycompany.tira;

public class Kasipari {
    
    private int pelaajanKasi;
    private int koneenKasi;
    private int voittaja;
    
    public Kasipari(int pelaaja, int kone, int voittaja) {
        this.koneenKasi = kone;
        this.pelaajanKasi = pelaaja;
        this.voittaja = voittaja;
    }

    public int getPelaajanKasi() {
        return pelaajanKasi;
    }

    public int getKoneenKasi() {
        return koneenKasi;
    }

    public int getVoittaja() {
        return voittaja;
    }
}
