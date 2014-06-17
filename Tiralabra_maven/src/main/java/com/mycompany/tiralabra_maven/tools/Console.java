/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.tools;

import java.util.Scanner;

/**
 * Luokka konsolin toteuttamista varten, joka toteuttaa rajapinnan Io
 * @author Sami
 */
public class Console implements Io {

    private Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }
/**
 * Lukee käyttäjältä merkkijonomuotoisen rivin
 * @param viesti Käyttäjälle tulostettava viesti
 * @return Palauttaa käyttäjän syötteen
 */
    public String lueRivi(String viesti) {
        sout(viesti);
        return scanner.nextLine();
    }

    /**
     * Lukee käyttäjältä integer-luvun
     * @param viesti Käyttäjälle tulostettava viesti
     * @return Luku
     */
    public int lueLuku(String viesti) {
        while (true) {
            String intAsString = lueRivi(viesti);
            try {
                return Integer.parseInt(intAsString);
            } catch (NumberFormatException exception) {
                sout(intAsString + " is not a valid number. Please try again.\n");
            }
        }
    }
/**
 * Tulostaa parametrin viestin
 * @param line Tulostettava viesti
 */
    public void sout(String line) {
        System.out.println(line);
    }

}
