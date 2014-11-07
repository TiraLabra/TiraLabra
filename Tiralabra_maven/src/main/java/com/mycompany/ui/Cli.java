package com.mycompany.ui;

import java.util.Scanner;

/**
 * Pelin tekstikäyttöliittymä
 */
public class Cli {

    private int moodi;
    private Scanner scanner;

    /**
     * Konstruktori alustaa luokkamuuttujat ja määrittelee pelin tyypin.
     *
     * @param mode 1=normaali peli, 2=laajennus
     */
    public Cli(int mode) {
        this.moodi = mode;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Pelin käynnistys
     */
    public void run() {
        String komento = "";
        
        if (this.moodi == 1) {
            System.out.println("Kivi-paperi-sakset BETA");
            System.out.println("Valitse kätesi:");
            System.out.println("[k] = Kivi");
            System.out.println("[p] = Paperi");
            System.out.println("[s] = Sakset");
            System.out.println("[t] = Statistiikka");
            System.out.println("[x] = Lopeta");
            System.out.print("?: ");
            komento = this.scanner.nextLine();
            while (!komento.equals("x")) {
                if (validoi(komento)) {
                    
                } else {
                    run();
                }
            }
        }
    }
    
    private boolean validoi(String komento) {
        if(komento.equals("k") || komento.equals("p")
                || komento.equals("s") || komento.equals("t")) {
            return true;
        }
        return false;
    }
}
