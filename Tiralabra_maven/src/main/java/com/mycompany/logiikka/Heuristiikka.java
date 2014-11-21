package com.mycompany.logiikka;

import com.mycompany.domain.Kasi;
import com.mycompany.tira.KasiLista;
import com.mycompany.tira.Kasipari;

public class Heuristiikka {

    private KasiLista kasiLista;
    private static final int KASILISTANKOKO = 20;
    private int pelaaja;
    private int kone;
    private int voitto;

    public Heuristiikka() {
        this.kasiLista = new KasiLista(KASILISTANKOKO);
    }

    public void setPelaajanKasi(Kasi k) {
        this.pelaaja = muunnaKasiNumeroksi(k);
    }

    public void setTietokoneenKasi(Kasi k) {
        this.kone = muunnaKasiNumeroksi(k);
    }

    public void setVoitto(int v) {
        this.voitto = v;
    }

    public void updateKasilista() {
        Kasipari pari = new Kasipari(this.pelaaja, this.kone, this.voitto);
        this.kasiLista.lisaaKasipari(pari);
    }

    private int muunnaKasiNumeroksi(Kasi k) {
        if (k.getNimi().equals("KIVI")) {
            return 0;
        } else if (k.getNimi().equals("PAPERI")) {
            return 1;
        } else if (k.getNimi().equals("SAKSET")) {
            return 2;
        } else if (k.getNimi().equals("LISKO")) {
            return 3;
        } else {
            return 4;
        }
    }
}
