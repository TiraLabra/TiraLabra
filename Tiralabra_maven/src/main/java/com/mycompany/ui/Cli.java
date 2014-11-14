package com.mycompany.ui;

import com.mycompany.domain.Kasi;
import com.mycompany.logiikka.Logiikka;
import com.mycompany.logiikka.Statistiikka;
import com.mycompany.logiikka.Tekoaly;
import java.util.Scanner;

/**
 * Pelin tekstikäyttöliittymä
 */
public class Cli {

    private int moodi;
    private Scanner scanner;
    private Statistiikka statistiikka;
    private Tekoaly tekoAly;
    private Logiikka logiikka;

    /**
     * Konstruktori alustaa luokkamuuttujat ja määrittelee pelin tyypin.
     *
     * @param mode 1=normaali peli, 2=laajennus
     */
    public Cli(int mode) {
        this.moodi = mode;
        this.scanner = new Scanner(System.in);
        this.statistiikka = new Statistiikka(this.moodi);
        this.logiikka = new Logiikka();
        this.tekoAly = new Tekoaly(this.moodi, this.statistiikka, this.logiikka);
        
    }

    /**
     * Pelin käynnistys
     */
    public void run() {
        String komento = "";
        if (this.moodi == 1) {
            moodiYksiPaavalikko();
        } else {
            moodiKaksiPaavalikko();
        }
    }

    /**
     * Normaalin pelin päävalikko
     */
    private void moodiYksiPaavalikko() {
        String komento;
        nautaMoodiYksiPaavalikko();
        komento = this.scanner.nextLine();
        while (!komento.equals("x")) {
            if (validoiKomento(komento)) {
                char c = komento.charAt(0);
                if (c == 't') {
                    System.out.println(this.statistiikka);
                } else {
                    
                    asetaTekoalynKasi();
                    asetaKasi(c);
                    pelaaKierros();
                }
            }
            nautaMoodiYksiPaavalikko();
            komento = this.scanner.nextLine();

        }
        System.out.println("loppu");
    }

    private void pelaaKierros() {
        int apu = this.logiikka.pelaajaVoittaaKierroksen();
        if (apu == 1) {
            this.statistiikka.lisaaPelaajanVoitto();
            System.out.println("Pelaaja voitti!");
        } else if (apu == 0) {
            this.statistiikka.asetaTasapeli();
            System.out.println("tasapeli");
        } else if (apu == -1) {
            System.out.println("tekoäly voitti");
        } else {
            System.out.println("should not get here!");
        }
    }

    private void asetaTekoalynKasi() {
        this.logiikka.setTekoalynKasi(this.tekoAly.tekoalynTarjoamaKasi());
    }

    private void asetaKasi(char KasiKomento) {
        switch (KasiKomento) {
            case 'k':
                Kasi k = new Kasi("KIVI");
                this.logiikka.setPelaajanKasi(k);
                this.statistiikka.paivitaKierros(k);
                break;
            case 'p':
                Kasi p = new Kasi("PAPERI");
                this.logiikka.setPelaajanKasi(p);
                this.statistiikka.paivitaKierros(p);
                break;
            case 's':
                Kasi s = new Kasi("SAKSET");
                this.logiikka.setPelaajanKasi(s);
                this.statistiikka.paivitaKierros(s);
                break;
            case 'l':
                Kasi l = new Kasi("LISKO");
                this.logiikka.setPelaajanKasi(l);
                this.statistiikka.paivitaKierros(l);
                break;
            case 'o':
                Kasi o = new Kasi("SPOCK");
                this.logiikka.setPelaajanKasi(o);
                this.statistiikka.paivitaKierros(o);
                break;
        }

    }

    /**
     * Moodin yksi päävalikkon tulostus
     */
    private void nautaMoodiYksiPaavalikko() {
        System.out.println("Kivi-paperi-sakset BETA");
        System.out.println("Valitse kätesi:");
        System.out.println("[k] = Kivi");
        System.out.println("[p] = Paperi");
        System.out.println("[s] = Sakset");
        System.out.println("[t] = Statistiikka");
        System.out.println("[x] = Lopeta");
        System.out.print("?: ");
    }

    /**
     * Pelin päävalikon käskyjen validointi
     *
     * @param komento validoitava käsky
     * @return true, jos käsky validi
     */
    private boolean validoiKomento(String komento) {
        if (this.moodi == 1) {
            if (komento.equals("k") || komento.equals("p")
                    || komento.equals("s") || komento.equals("t")
                    || komento.equals("x")) {
                return true;
            }
            return false;
        } else if (this.moodi == 2) {
            if (komento.equals("k") || komento.equals("p")
                    || komento.equals("s") || komento.equals("t")
                    || komento.equals("l") || komento.equals("o")) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Laajennetun pelin päävalikko
     */
    private void moodiKaksiPaavalikko() {
        String komento;
        nautaMoodiKaksiPaavalikko();
        komento = this.scanner.nextLine();
        while (!komento.equals("x")) {
            if (validoiKomento(komento)) {
                break;
            }
            nautaMoodiKaksiPaavalikko();
            komento = this.scanner.nextLine();
        }
    }

    private void nautaMoodiKaksiPaavalikko() {
        System.out.println("Kivi-paperi-sakset BETA");
        System.out.println("Valitse kätesi:");
        System.out.println("[k] = Kivi");
        System.out.println("[p] = Paperi");
        System.out.println("[s] = Sakset");
        System.out.println("[l] = Lisko");
        System.out.println("[o] = Spock");
        System.out.println("[t] = Statistiikka");
        System.out.println("[x] = Lopeta");
        System.out.print("?: ");
    }

//    /**
//     * Laajennetun pelin päävalikon käskyn validointi
//     *
//     * @param komento validoitava käsky
//     * @return true, jos käsky validi
//     */
//    private boolean validoiMoodiKaksi(String komento) {
//        if (komento.equals("k") || komento.equals("p")
//                || komento.equals("s") || komento.equals("t")
//                || komento.equals("l") || komento.equals("o")) {
//            return true;
//        }
//        return false;
//    }
}
