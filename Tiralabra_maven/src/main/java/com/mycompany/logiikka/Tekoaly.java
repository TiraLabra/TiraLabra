package com.mycompany.logiikka;

import java.util.Random;

/**
 * Luokka toimii tekoälynä ja antaa koneen seuraavan käden peliin
 */
public class Tekoaly {

    private int moodi;
    private Heuristiikka heuristiikka;
    private int kierroksia;

    /**
     * Konstruktori alustaa luokkamuuttujat
     *
     * @param moodi pelimoodi (1=normaali, 2=laajennus)
     */
    public Tekoaly(int moodi) {
        this.moodi = moodi;
        this.heuristiikka = new Heuristiikka();
        this.kierroksia = -1;
    }

    /**
     * Palauttaa tekoälyn mielestä parhaimman käden joka koneen tulisi pelata
     * seuraavaksi
     *
     * @return paras koneen käsi
     */
    public int getKoneenKasi() {
        this.kierroksia++;
        if (this.kierroksia == 0) {
            // pelaaja pelaa yleensä ensimmäisenä kiven
            return valitseVoittaja(0);
        }
        if (this.kierroksia < 6) {
            return valitseVoittaja(pelaajanSeuraavaKasiRotaatiossa());
        }

        return valitseVoittaja(this.heuristiikka.pelaajaTuleePelaamaan());
    }

    /**
     * Päivittää heuristiikan (historiatiedot)
     *
     * @param pKasi pelaajan käsi
     * @param kKasi tietokoneen käsi
     */
    public void paivitaHeuristiikka(int pKasi, int kKasi) {
        this.heuristiikka.paivitaHeuristiikka(pKasi, kKasi);
    }

    /**
     * Luokan sisäinen metodi. Palauttaa rotaatiossa annettua kättä seuraavan
     * käden.
     *
     * @param kasi mistä rotaatiota haetaan
     * @return parametria seuraava käsi
     */
    private int pelaajanSeuraavaKasiRotaatiossa() {
        int pelaaja = this.heuristiikka.getViimeisinKasipari().getPelaajanKasi();
        if (this.moodi == 1) {
            if (pelaaja < 2) {
                pelaaja++;
            } else {
                pelaaja = 0;
            }
        } else {
            if (pelaaja < 4) {
                pelaaja++;
            } else {
                pelaaja = 0;
            }
        }
        return pelaaja;
    }

    /**
     * Luokan sisäinen metodi. Palauttaa annettun käden voittavan käden
     *
     * @param pelaajanOletettuKasi pelaajan oletettu käsi
     * @return käsi joka voittaa annetun käden
     */
    private int valitseVoittaja(int pelaajanOletettuKasi) {
        // normaali peli, vain yksi käsi voittaa
        switch (pelaajanOletettuKasi) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 0;
        }

        // laajennettu peli
        Random r = new Random();
        int satunnaisuus = r.nextInt(2);
        switch (pelaajanOletettuKasi) {
            case 0:
                if (satunnaisuus == 0) {
                    return 4;
                } else {
                    return 1;
                }
            case 1:
                if (satunnaisuus == 0) {
                    return 3;
                } else {
                    return 2;
                }
            case 2:
                if (satunnaisuus == 0) {
                    return 4;
                } else {
                    return 0;
                }
            case 3:
                if (satunnaisuus == 0) {
                    return 0;
                } else {
                    return 2;
                }
            case 4:
                if (satunnaisuus == 0) {
                    return 1;
                } else {
                    return 3;
                }
        }

        return -2; // should not get here!
    }
}
