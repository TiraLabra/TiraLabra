package apuneuvot;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * MatriisienLukija-luokka, jonka avulla voidaan tekstitiedostosta lukea
 * matriisi. Matriisin täytyy olla talletettu tiedostoon MatrixMarket array
 * formaatissa, jossa ensimmäisellä rivillä on matriisin koko ja seuraavilla
 * riveillä on alkiot yksitellen sarakkeittain vasemmalt oikealle ylhäältä alas.
 *
 * @author Eversor
 */
public class MatriisienLukija {

    /**
     * Metodi, joka lukee parametrina annetusta tiedostosta matriisin ja
     * palauttaa sen. Matriisin täytyy olla talletettu tiedostoon MatrixMarket
     * array formaatissa, jossa ensimmäisellä rivillä on matriisin koko ja
     * seuraavilla riveillä on alkiot yksitellen sarakkeittain vasemmalt
     * oikealle ylhäältä alas. Esim. 2x2 yksikkömatriisi:
     *
     * 2 2
     * 1
     * 0
     * 0
     * 1
     *
     * @param tiedosto Tiedosto, josta halutaan lukea matriisi
     * @return Palauttaa luetun matriisin muotoa m x n
     */
    public double[][] lue(String tiedosto) {
        File file = new File(tiedosto);
        try {
            Scanner lukija = new Scanner(file);
            if (file.length() >= 3) {
                double[][] luettu = lue(lukija);
                lukija.close();
                return luettu;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Metodi, joka lukee matriisin parametrina annetun Scanner-luokan
     * ilmentymän kautta ja palauttaa luetun matriisin.
     * 
     * @param lukija Scanner-luokan ilmentymä, joka lukee aina seuraavan luvun
     * @return Palauttaa luetun matriisin muotoa m x n
     */
    private double[][] lue(Scanner lukija) {
        int m = Integer.parseInt(lukija.next());
        int n = Integer.parseInt(lukija.next());
        double[][] matriisi = new double[m][n];
        for (int sarake = 0; sarake < n; sarake++) {
            for (int rivi = 0; rivi < m; rivi++) {
                if (lukija.hasNext()) {
                    matriisi[rivi][sarake] = Double.parseDouble(lukija.next());
                }
            }
        }
        return matriisi;
    }
}