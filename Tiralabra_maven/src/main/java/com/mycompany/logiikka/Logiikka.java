package com.mycompany.logiikka;

/**
 * Luokka huolehtii pelin voittologiikasta. Syottämällä luokalle pelaajan ja
 * tekoälyn kädet luokka tarkistaa kuka voittaa
 *
 */
public class Logiikka {

    private Statistiikka statistiikka;
    private Tekoaly tekoaly;
    private int pelaajanKasi;

    /**
     * Konstruktori alustaa luokkamuuttujat
     * 
     * @param statistiikka viite statistiikkaolioon
     * @param moodi pelimoodi
     */
    public Logiikka(Statistiikka statistiikka, int moodi) {
        this.statistiikka = statistiikka;
        this.tekoaly = new Tekoaly(moodi);
        this.pelaajanKasi = -1;
    }

    /**
     * Asettaa pelaajan käden logiikalle. Tämä käsi on todellinen
     * pelin käsi. Älä käytä metodia pelaajan käden tarkastuksiin
     * 
     * @param kasi pelaajan käsi
     */
    public void asetaPelaajanKasi(int kasi) {
        this.pelaajanKasi = kasi;
    }

    /**
     * Metodi pelaa kierroksen peliä asetetun pelaajan käden mukaisesti.
     * Metodi tulostaa kieroksen jälkeen pelatut kädet ja julistaa voittajan
     * tai tasapelin
     * 
     * @see com.mycompany.logiikka.Logiikka.asetaPelaajanKasi(int)
     */
    public void pelaaKierros() {
        // pyydä koneen käsi tekoälyltä
        int koneenKasi = this.tekoaly.getKoneenKasi();
        // päivitä heuristiikka
        this.tekoaly.paivitaHeuristiikka(this.pelaajanKasi, koneenKasi);
        // tässä pelataan oikea kierros ja tulostetaan kierroksen tilanne
        int voittaja = selvitaVoittaja(this.pelaajanKasi, koneenKasi);
        this.statistiikka.update(voittaja);
        if (voittaja == -1) {
            System.out.println("Tasapeli!");
        } else {
            System.out.print("Pelaaja: " + asString(this.pelaajanKasi)
                        + ", kone: " + asString(koneenKasi));
            if (voittaja == 0) {
                System.out.print(", voittaja on pelaaja");
            } else {
                System.out.print(", voittaja on kone");
            }
        }
        System.out.println("");
    }
    
    /**
     * Luokan sisäinen metodi joka muuttaa annetun käden (int) tekstiksi
     * 
     * @param kasi muutettava käsi
     * @return käsi tekstinä
     */
    private String asString(int kasi) {
        switch(kasi) {
            case 0:
                return "KIVI";
            case 1:
                return "PAPERI";
            case 2:
                return "SAKSET";
            case 3:
                return "LISKO";
            case 4:
                return "SPOCK";
        }
        return "VIRHE!!!";
    }
    
    /**
     * Luokan sisäinen metodi. Selvittää kuka voittaa kierroksen vai
     * onko kyseessä tasapeli
     * 
     * @param pKasi pelaajan käsi
     * @param kKasi tietokoneen käsi
     * @return voittaja:
     * <ul>
     * <li> 1 voittaja on tietokone
     * <li> 0 voittaja on pelaaja
     * <li> -1 tasapeli
     * <ul>
     */
    private int selvitaVoittaja(int pKasi, int kKasi) {
        if (pKasi == kKasi) {
            return -1;
        }
        switch(pKasi) {
            case 0:
                if (kKasi == 4 || kKasi == 1) {
                    return 1;
                } else {
                    return 0;
                }
            case 1:
                if (kKasi == 2 || kKasi == 3) {
                    return 1;
                } else {
                    return 0;
                }
            case 2:
                if (kKasi == 0 || kKasi == 4) {
                    return 1;
                } else {
                    return 0;
                }
            case 3:
                if (kKasi == 0 || kKasi == 2) {
                    return 1;
                } else {
                    return 0;
                }
            case 4:
                if (kKasi == 1 || kKasi == 3) {
                    return 1;
                } else {
                    return 0;
                }
        }
        return -2; //should not get here!!!
    }
}
