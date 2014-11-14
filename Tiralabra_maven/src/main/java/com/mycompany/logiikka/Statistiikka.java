package com.mycompany.logiikka;

import com.mycompany.domain.Kasi;
import java.util.ArrayList;
import java.util.List;

public class Statistiikka {

    private int kierrokset;
    private int pelaajanVoitot;
    private int tasapelit;
    private int[] kadet;
    private int moodi;

    public Statistiikka(int moodi) {
        this.moodi = moodi;
        this.kierrokset = 0;
        this.pelaajanVoitot = 0;
        this.tasapelit = 0;
        this.kadet = new int[5];
        alustaKadet();
    }

    public int getTasapelit() {
        return this.tasapelit;
    }
    
    public void asetaTasapeli() {
        this.tasapelit++;
    }
    
    private void alustaKadet() {
        for (int i = 0; i < 5; i++) {
            this.kadet[i] = 0;
        }
        if (this.moodi == 1) {
            this.kadet[3] = Integer.MAX_VALUE;
            this.kadet[4] = Integer.MAX_VALUE;
        }
    }

    public void paivitaKierros(Kasi pelaaja) {
        this.kierrokset++;
        paivitakadet(pelaaja);
    }
    
    public void lisaaPelaajanVoitto() {
        this.pelaajanVoitot++;
    }

    private void paivitakadet(Kasi kasi) {
        if (kasi.getNimi().equals("KIVI")) {
            this.kadet[0]++;
        } else if (kasi.getNimi().equals("PAPERI")) {
            this.kadet[1]++;
        } else if (kasi.getNimi().equals("SAKSET")) {
            this.kadet[2]++;
        } else if (kasi.getNimi().equals("LISKO")) {
            this.kadet[3]++;
        } else {
            this.kadet[4]++;
        }
    }

    public Kasi pelaajanVahitenPelattuKasi() {
        int pienin = Integer.MAX_VALUE;
        int kasiIndeksi = -1;
        int moodiIndeksi = -1;

        if (this.moodi == 1) {
            moodiIndeksi = 3;
        } else {
            moodiIndeksi = 5;
        }

        for (int i = 0; i < moodiIndeksi; i++) {
            if (this.kadet[i] < pienin) {
                kasiIndeksi = i;
                pienin = this.kadet[i];
            }
        }

        if (kasiIndeksi == 0) {
            return new Kasi("KIVI");
        } else if (kasiIndeksi == 1) {
            return new Kasi("PAPERI");
        } else if (kasiIndeksi == 2) {
            return new Kasi("SAKSET");
        } else if (kasiIndeksi == 3) {
            return new Kasi("LISKO");
        } else {
            return new Kasi("SPOCK");
        }
    }

    public long vahitenPelattuKasiProsentit() {
        int maara = -1;
        Kasi vahiten = pelaajanVahitenPelattuKasi();
        if (vahiten.getNimi().equals("KIVI")) {
            maara = this.kadet[0];
        } else if (vahiten.getNimi().equals("PAPERI")) {
            maara = this.kadet[1];
        } else if (vahiten.getNimi().equals("SAKSET")) {
            maara = this.kadet[2];
        } else if (vahiten.getNimi().equals("LISKO")) {
            maara = this.kadet[3];
        } else {
            maara = this.kadet[4];
        }
        double pros = maara / this.kierrokset;
        return (long) Math.floor(pros + 0.5d);
    }
    
    public int getKierrokset() {
        return this.kierrokset;
    }
    
    @Override
    public String toString() {
        return "Kierroksia : " + this.kierrokset +
                ", pelaajan voittoja: " + this.pelaajanVoitot +
                ", tasapelejä: " + this.tasapelit + "";
    }
}
