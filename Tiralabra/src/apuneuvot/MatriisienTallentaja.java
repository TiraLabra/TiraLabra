package apuneuvot;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * MatriisienTallentaja-luokka, jonka avulla voidaan tallentaa matriiseja
 * MatrixMarket array formaattiin, jossa ensimmäisellä rivillä on matriisin koko 
 * ja seuraavilla riveillä on alkiot yksitellen sarakkeittain vasemmalt oikealle 
 * ylhäältä alas.
 * 
 * @author Eversor
 */
public class MatriisienTallentaja {

    /**
     * Metodi, joka tallettaa parametrina annetun matriisin parametrina
     * annettuun tiedostonimeen. Tarkastaa aluksi ettei matriisi tai tiedosto-
     * nimi ole null tai tyhjä, jonka jälkeen luo uuden kirjoittajan ja 
     * tallentaa matriisin tiedostoksi. Huom! metodi ylikirjoittaa edellisen 
     * samannimisen tiedoston.
     * 
     * @param matriisi Matriisi, joka halutaan tallentaa, muotoa m x n
     * @param tiedostonimi Tiedostonimi, joksi halutaan tallentaa
     */
    public void tallenna(double[][] matriisi, String tiedostonimi) {
        tarkasta(matriisi, tiedostonimi);
        System.out.println(tiedostonimi);
        BufferedWriter kirjoittaja = null;

        try {
            kirjoittaja = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(tiedostonimi), "utf-8"));
            kirjoitaMatriisi(matriisi, kirjoittaja);
        } catch (IOException e) {
        } finally {
            try {
                kirjoittaja.close();
            } catch (Exception e) {
            }
        }
    }
    
    /**
     * Metodi, joka tarkastaa ettei parametrina annettu matriisi tai 
     * tiedostonimi ole null tai tyhjä. Heittää poikkeuksen, jos näin on.
     * 
     * @param matriisi Matriisi, joka tarkastetaan, muotoa m x n
     * @param tiedostonimi Merkkijono, joka tarkastetaan
     */
    private void tarkasta(double[][] matriisi, String tiedostonimi) {
        if(matriisi == null) {
            throw new IllegalArgumentException("Matriisi on null, joten sitä ei"
                    + "tallenneta");
        } else if(tiedostonimi == null || tiedostonimi.trim().length() == 0){
            throw new IllegalArgumentException("Tiedostonimi on null, joten"
                    + "sitä ei tallenneta");
        }
    }

    /**
     * Metodi, joka parametrina annetun kirjoittajan avulla tallentaa
     * parametrina annetun matriisin tiedostoksi.
     * 
     * @param matriisi Matriisi, joka halutaan tallentaa, muotoa m x n
     * @param kirjoittaja Kirjoittaja, jolla tallennetaan matriisi tiedostoon
     */
    private void kirjoitaMatriisi(double[][] matriisi,
                                  BufferedWriter kirjoittaja) {
        try {
            kirjoittaja.write("" + matriisi.length + " " + matriisi[0].length);
            kirjoittaja.newLine();
            for (int sarakkeet = 0; sarakkeet < matriisi[0].length; sarakkeet++) {
                for (int rivit = 0; rivit < matriisi.length; rivit++) {
                    kirjoittaja.write(""+matriisi[rivit][sarakkeet]);
                    kirjoittaja.newLine();
                }
            }
        } catch (IOException e) {
        }
    }
}