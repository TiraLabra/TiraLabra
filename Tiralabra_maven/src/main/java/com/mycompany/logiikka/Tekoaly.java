package com.mycompany.logiikka;

import com.mycompany.domain.Kasi;

public class Tekoaly {

    private Statistiikka statistiikka;
    private int moodi;
    private Logiikka logiikka;
    private Kasi viimeisinTekoalynKasi;

    public Tekoaly(int moodi, Statistiikka s, Logiikka l) {
        this.statistiikka = s;
        this.moodi = moodi;
        this.logiikka = l;
        this.viimeisinTekoalynKasi = null;
    }

    private Kasi paivitaSeuraavaRotaationKasi(Kasi k) {
        if (this.moodi == 1) {
            if (k.getNimi().equals("KIVI")) {
                return new Kasi("PAPERI");
            } else if (k.getNimi().equals("PAPERI")) {
                return new Kasi("SAKSET");
            } else {
                return new Kasi("KIVI");
            }
        } else {
            if (k.getNimi().equals("KIVI")) {
                return new Kasi("PAPERI");
            } else if (k.getNimi().equals("PAPERI")) {
                return new Kasi("SAKSET");
            } else if (k.getNimi().equals("SAKSET")) {
                return new Kasi("LISKO");
            } else if (k.getNimi().equals("LISKO")) {
                return new Kasi("SPOCK");
            } else {
                return new Kasi("KIVI");
            }
        }
    }

    
    public Kasi tekoalynTarjoamaKasi() {
        Kasi palauta = new Kasi("PAPERI");
        
        // ensimmäinen kierros
        if (this.statistiikka.getKierrokset() == 0) {
            this.viimeisinTekoalynKasi = palauta;
            return palauta;
        }
        
        // tarkista edellisen kierroksen tilanne
        Kasi edellinen = this.logiikka.pelaajanViimeisinKasi();
        this.logiikka.setPelaajanKasi(edellinen);
        this.logiikka.setTekoalynKasi(this.viimeisinTekoalynKasi);
        int pelitilanne = this.logiikka.pelaajaVoittaaKierroksen();
        
        // jos pelaaja voitti, käytä pelaajan voittanutta kättä
        if (pelitilanne == 1) {
            palauta = edellinen;
        } else { // oleta pelaajan seuraavan rotaatiota
            Kasi oletus = paivitaSeuraavaRotaationKasi(edellinen);
            this.logiikka.setPelaajanKasi(oletus);
            for (int i=0; i<4; i++) {
                if (this.logiikka.pelaajaVoittaaKierroksen() >= 0) {
                    palauta = paivitaSeuraavaRotaationKasi(palauta);
                } else {
                    break;
                }
            }
        }

        this.viimeisinTekoalynKasi = palauta;
        return palauta;
    }

   
}
