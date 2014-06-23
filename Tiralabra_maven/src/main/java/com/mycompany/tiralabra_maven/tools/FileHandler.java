/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.tools;

import com.mycompany.tiralabra_maven.domain.Laatikko;
import com.mycompany.tiralabra_maven.domain.Laatikkotyyppi;
import com.mycompany.tiralabra_maven.domain.Sijainti;
import com.mycompany.tiralabra_maven.structures.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Tämä luokka hoitaa tiedostojen käsittelyn
 *
 * @author Sami
 */
public class FileHandler {

//    private PrintWriter writer;
//    private BufferedReader reader;

    public FileHandler() {

    }

    /**
     * Tämä kirjoittaa annettuun tiedostoon annetut laatikot järkevässä muodossa
     *
     * @param tiedosto Tiedosto, johon kirjoitetaan
     * @param laatikot Laatikot, jotka kirjoitetaan
     * @throws FileNotFoundException
     */
    public static void kirjoitaLaatikot(File tiedosto, List<Laatikkotyyppi> laatikot) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(tiedosto);

        String teksti = "";
        for (int j = 0; j < laatikot.size(); j++) {
            Laatikkotyyppi tyyppi = laatikot.get(j);
            teksti += tyyppi.getX() + " " + tyyppi.getY() + " " + tyyppi.getZ() + " " + tyyppi.getLaatikoita() + "\n";
        }
        writer.print(teksti);
        writer.close();

    }

    /**
     * Tämä lukee tiedostosta oikeassa muodossa olevat laatikot ja palauttaa
     * listan laatikkotyypeistä
     *
     * @param tiedosto Tiedosto, josta luetaan
     * @return Lista laatikkotyypeistä (ja laatikoista)
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static List<Laatikkotyyppi> lueLaatikot(File tiedosto) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(tiedosto));
        List<Laatikkotyyppi> laatikot = new List<Laatikkotyyppi>();

        String rivi = reader.readLine();
        while (rivi != null) {
            String[] arvot = rivi.split(" ");
            Laatikkotyyppi tyyppi = new Laatikkotyyppi(Integer.parseInt(arvot[0]), Integer.parseInt(arvot[1]), Integer.parseInt(arvot[2]));
            tyyppi.setLaatikoita(Integer.parseInt(arvot[3]));
//            for (int i = 0; i < Integer.parseInt(arvot[3]); i++) {
//                Laatikko laatikko = new Laatikko(tyyppi, new Sijainti(), 0);
//                tyyppi.getLaatikot().add(laatikko);
//            }
            laatikot.add(tyyppi);
            rivi = reader.readLine();
        }
        reader.close();
        return laatikot;
    }

    /**
     * Tämä kirjoittaa annettuun tiedostoon annetun tekstin
     * @param tiedosto Tiedosto, johon tallennetaan
     * @param teksti Tallennettava teksti
     * @throws FileNotFoundException 
     */
    public static void kirjoitaString(File tiedosto, String teksti) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(tiedosto);

        writer.print(teksti);
        writer.close();
    }

}
