package com.mycompany.ui;

import com.mycompany.logiikka.Logiikka;
import com.mycompany.logiikka.Statistiikka;
import java.util.Scanner;

/**
 * Pelin tekstikäyttöliittymä
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
        this.logiikka = new Logiikka();
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
        System.out.println("Kivi-paperi-sakset BETA");
        System.out.println("Valitse kätesi:");
        System.out.println("[k] = Kivi");
        System.out.println("[p] = Paperi");
        System.out.println("[s] = Sakset");
        System.out.println("[t] = Statistiikka");
        System.out.println("[x] = Lopeta");
        System.out.print("?: ");
        komento = this.scanner.nextLine();
        while (true) {
            if (validoiMoodiYksi(komento)) {
                char c = komento.charAt(0);
                switch(c) {
                    case 'k':
                        System.out.println("ki");
                        break;
                    case 'p':
                        System.out.println("pa");
                        break;
                }
                break;
            } else {
                break;
            }
        }
    }
    
    /**
     * Normaalin pelin päävalikon käskyjen validointi
     * 
     * @param komento validoitava käsky
     * @return true, jos käsky validi
     */
    private boolean validoiMoodiYksi(String komento) {
        if(komento.equals("k") || komento.equals("p")
                || komento.equals("s") || komento.equals("t")
                || komento.equals("x")) {
            return true;
        }
        return false;
    }

    /**
     * Laajennetun pelin päävalikko
     */
    private void moodiKaksiPaavalikko() {
        String komento;
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
        komento = this.scanner.nextLine();
        while (!komento.equals("x")) {
            if (validoiMoodiKaksi(komento)) {
                break;
            } else {
                run();
            }
        }
    }
    
    /**
     * Laajennetun pelin päävalikon käskyn validointi
     * 
     * @param komento validoitava käsky
     * @return true, jos käsky validi
     */
    private boolean validoiMoodiKaksi(String komento) {
        if(komento.equals("k") || komento.equals("p")
                || komento.equals("s") || komento.equals("t")
                || komento.equals("l") || komento.equals("o")) {
            return true;
        }
        return false;
    }
}
