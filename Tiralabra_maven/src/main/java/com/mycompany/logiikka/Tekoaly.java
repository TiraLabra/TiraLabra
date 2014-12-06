package com.mycompany.logiikka;

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
     * Palauttaa tekoälyn mielestä parhaimman käden joka koneen tulisi
     * pelata seuraavaksi
     * 
     * @return paras koneen käsi 
     */
    public int getKoneenKasi() {
        this.kierroksia++;
        if (this.kierroksia == 0) {
            return 1;
        }
        if (this.kierroksia < 5) {
            return valitseVoittaja(pelaajanSeuraavaKasiRotaatiossa(this.moodi));
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
     * Luokan sisäinen metodi. Palauttaa rotaatiossa annettua kättä
     * seuraavan käden.
     * 
     * @param kasi mistä rotaatiota haetaan
     * @return parametria seuraava käsi
     */
    private int pelaajanSeuraavaKasiRotaatiossa(int kasi) {
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
        switch(pelaajanOletettuKasi) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 0;
            case 3:
                return 0;
            case 4:
                return 3;
        }
        return -2; // should not get here!
    }
}
