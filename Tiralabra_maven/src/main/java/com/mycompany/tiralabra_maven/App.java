package com.mycompany.tiralabra_maven;

import com.mycompany.ui.Cli;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        while (true) {
            System.out.println("Pelimoodit:");
            System.out.println("1. Kivi-Paperi-Sakset");
            System.out.println("2. Kivi-Paperi-Sakset-Lisko-Spock");
            System.out.print("> ");
            Scanner lukija = new Scanner(System.in);
            String komento = lukija.nextLine();
            if (komento.equals("1")) {
                Cli c = new Cli(1);
                c.run();
                break;
            } else if (komento.equals("2")) {
                Cli c = new Cli(2);
                c.run();
                break;
            }
        }
    }
}
