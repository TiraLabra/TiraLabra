package com.mycompany.logiikka;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka selvittää tietokoneelle seuraavan käden
 */
public class Tekoaly {

    private Statistiikka statistiikka;
    private Kasi vahitenPelattuKasi;
    private int vahitenPelattuProsentit;
    private Kasi seuraavaKasiRotaatiossa;
    private int moodi;

    /**
     * Konstruktori alustaa luokkamuuttujat
     *
     * @param s Pelin Statistiikka-olio
     * @param m Pelin moodi (1=normaali, 2=laajennettu)
     */
    public Tekoaly(Statistiikka s, int m) {
        this.statistiikka = s;
        this.vahitenPelattuKasi = null;
        this.vahitenPelattuProsentit = 0;
        this.moodi = m;
    }

    /**
     * Päivittää luokkamuuttujaan pelaajan vähiten pelaaman käden
     */
    private void paivitaVahitenPelattuKasi() {
        // 0=Kivi, 1=Paperi, ... 3=Lisko, 4=Spock
        int kadet[] = new int[5];
        for (int i = 0; i < 5; i++) {
            kadet[i] = 0;
        }

        if (!this.statistiikka.getPelatutKadet().isEmpty()) {
            for (Kasi k : this.statistiikka.getPelatutKadet()) {
                if (k.getNimi().equals("KIVI")) {
                    kadet[0]++;
                } else if (k.getNimi().equals("PAPERI")) {
                    kadet[1]++;
                } else if (k.getNimi().equals("SAKSET")) {
                    kadet[2]++;
                } else if (k.getNimi().equals("LISKO")) {
                    kadet[3]++;
                } else {
                    kadet[4]++;
                }
            }
        }

        int pienin = Integer.MAX_VALUE;

        // moodin tarkistus
        if (this.moodi == 1) {
            for (int i = 0; i < 3; i++) {
                if (kadet[i] < pienin) {
                    pienin = kadet[i];
                }
            }
        } else {
            for (int i = 0; i < 5; i++) {
                if (kadet[i] < pienin) {
                    pienin = kadet[i];
                }
            }
        }

        if (pienin == 0) {
            this.vahitenPelattuKasi = new Kasi("KIVI");
        } else if (pienin == 1) {
            this.vahitenPelattuKasi = new Kasi("PAPERI");
        } else if (pienin == 2) {
            this.vahitenPelattuKasi = new Kasi("SAKSET");
        } else if (pienin == 3) {
            this.vahitenPelattuKasi = new Kasi("LISKO");
        } else {
            this.vahitenPelattuKasi = new Kasi("SPOCK");
        }
    }

    /**
     * Päivittää luokkamuuttujaan vähiten pelatun käden prosentit. Esim. jos 10
     * kierroksen aikana on pelattu kättä x vain yksi kerta, päivitetään
     * prosenteiksi 10. Laskenta pyöristää prosentit alaspäin.
     */
    private void paivitaVahitenPelatunKadenProsentit() {
        int maara = 0;

        for (Kasi k : this.statistiikka.getPelatutKadet()) {
            if (k.getNimi().equals(this.vahitenPelattuKasi.getNimi())) {
                maara++;
            }
        }
        double prosentit = maara / this.statistiikka.getKierrokset();
        prosentit = prosentit * 100;
        prosentit = Math.ceil(prosentit);
        this.vahitenPelattuProsentit = (int) prosentit;
    }

    /**
     * Päivittää luokkamuuttujaan seuraavan käden argumenttina annetusta kädestä
     * logiikalla kivi-paperi-sakset-lisko-spock
     *
     * @param k Käsi josta tehdään rotaatio
     */
    private void paivitaSeuraavaRotaationKasi(Kasi k) {

        if (this.moodi == 1) {
            if (k.getNimi().equals("KIVI")) {
                this.seuraavaKasiRotaatiossa = new Kasi("PAPERI");
            } else if (k.getNimi().equals("PAPERI")) {
                this.seuraavaKasiRotaatiossa = new Kasi("SAKSET");
            } else {
                this.seuraavaKasiRotaatiossa = new Kasi("KIVI");
            }
        } else {
            if (k.getNimi().equals("KIVI")) {
                this.seuraavaKasiRotaatiossa = new Kasi("PAPERI");
            } else if (k.getNimi().equals("PAPERI")) {
                this.seuraavaKasiRotaatiossa = new Kasi("SAKSET");
            } else if (k.getNimi().equals("SAKSET")) {
                this.seuraavaKasiRotaatiossa = new Kasi("LISKO");
            } else if (k.getNimi().equals("LISKO")) {
                this.seuraavaKasiRotaatiossa = new Kasi("SPOCK");
            } else {
                this.seuraavaKasiRotaatiossa = new Kasi("KIVI");
            }
        }
    }
    
    /**
     * Palauttaa tekoälyn mielestä parhaimman käden
     * 
     * @return Käsi 
     */
    public Kasi annaKasi() {
        // ensimmäinen kierros
        if (this.statistiikka.getPelatutKadet().isEmpty()) {
            return new Kasi("PAPERI");
        }
        // tallenna pelaajan viimeisin käsi
        Kasi edellinen = this.statistiikka.getPelatutKadet().get(this.statistiikka.getKierrokset() - 1);
        // jos pelaaja voitti, käytä pelaajan voittanutta kättä
        if (this.statistiikka.voittikoPelaajaViimeksi()) {
            return edellinen;
        }

        // jos pelaaja hävisi, oleta pelaajan seuraavan rotaatiota
        paivitaSeuraavaRotaationKasi(edellinen);

        return voittavaKasi(this.seuraavaKasiRotaatiossa);
    }

    /**
     * Palauttaa käden joka voittaa argumenttina annetun käden
     * 
     * @param voitettava Käsi joka tulee voittaa
     * @return Käsi jolla voitto tapahtuu
     */
    private Kasi voittavaKasi(Kasi voitettava) {
        Kasi ulos = null;

        if (this.moodi == 1) {
            if (voitettava.getNimi().equals("KIVI")) {
                ulos = new Kasi("PAPERI");
            } else if (voitettava.getNimi().equals(("PAPERI"))) {
                ulos = new Kasi("SAKSET");
            } else {
                ulos = new Kasi("KIVI");
            }
        } else {
            if (voitettava.getNimi().equals("KIVI")) {
                ulos = new Kasi("SPOCK");
            } else if (voitettava.getNimi().equals("PAPERI")) {
                ulos = new Kasi("LISKO");
            } else if (voitettava.getNimi().equals("SAKSET")) {
                ulos = new Kasi("SPOCK");
            } else if (voitettava.getNimi().equals("LISKO")) {
                ulos = new Kasi("KIVI");
            } else {
                ulos = new Kasi("PAPERI");
            }
        }
        return ulos;
    }
}
