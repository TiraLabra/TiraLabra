package com.mycompany.ui;

import com.mycompany.logiikka.Logiikka;
import com.mycompany.logiikka.Statistiikka;
import java.util.Scanner;

/**
 * Pelin käyttöliittymä
 */
public class Cli {

    private int moodi;
    private Scanner scanner;
    private Statistiikka statistiikka;
    private Logiikka logiikka;

    /**
     * Konstruktori alustaa luokkamuuttujat ja määrittelee pelin tyypin.
     *
     * @param mode 1=normaali peli, 2=laajennus
     */
    public Cli(int mode) {
        this.moodi = mode;
        this.scanner = new Scanner(System.in);
        this.statistiikka = new Statistiikka();
        this.logiikka = new Logiikka(this.statistiikka, this.moodi);
    }

    /**
     * Pelin käynnistys
     */
    public void run() {
        System.out.println("Kivi-paperi-sakset v.2.0");
        String komento = "";
        while (true) {
            nautaMoodiYksi();
            if (this.moodi == 1) {
                nautaLoput();
            } else {
                nautaMoodiKaksi();
                nautaLoput();
            }
            komento = this.scanner.nextLine();
            if (komento.equals("x")) {
                break;
            }
            if (validoiKomento(komento)) {
                char c = komento.charAt(0);
                if (c == 't') {
                    System.out.println(this.statistiikka);
                } else {
                    this.logiikka.asetaPelaajanKasi(asetaKasi(c));
                    this.logiikka.pelaaKierros();
                }
            }
        }
    }

    /**
     * Muuttaa kättä kuvaavan kirjaimen numeroksi
     * 
     * @param KasiKomento kättä kuvaava kirjain
     * @return kättä vastaava numero
     */
    private int asetaKasi(char KasiKomento) {
        switch (KasiKomento) {
            case 'k':
                return 0;
            case 'p':
                return 1;
            case 's':
                return 2;
            case 'l':
                return 3;
            case 'o':
                return 4;
        }
        return -1; // should not get here!!!
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
     * Moodin yksi päävalikkon käsien tulostus
     */
    private void nautaMoodiYksi() {
        System.out.println("");
        System.out.println("Valitse kätesi:");
        System.out.println("[k] = Kivi");
        System.out.println("[p] = Paperi");
        System.out.println("[s] = Sakset");
    }
    
    /**
     * Moodin kaksi päävalikon käsien lisätulostus
     */
    private void nautaMoodiKaksi() {
        System.out.println("[l] = Lisko");
        System.out.println("[o] = Spock");
    }
    
    /**
     * Päävalikon lopun tulostus käsien jälkeen
     */
    private void nautaLoput() {
        System.out.println("[t] = Statistiikka");
        System.out.println("[x] = Lopeta");
        System.out.print("?: ");
    }
}
