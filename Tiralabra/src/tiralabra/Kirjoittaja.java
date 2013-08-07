/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Kirjoittaa uuden tiedoston.
 *
 * @author Joonas
 */
public class Kirjoittaja {

    FileOutputStream fileout;

    /**
     * Alustaa uuden kirjoittajan parametrinä tiedoston nimi.
     *
     * @param tiedosto
     */
    public Kirjoittaja(String tiedosto) {
        try {
            fileout = new FileOutputStream(new File(tiedosto));
        } catch (Exception e) {
            System.out.println("Ei onnistu" + e);
        }
    }
    
    /**
     * Kirjoittaa tiedostoon yhden merkin x kerrallaan.
     * @param x 
     */
    
    public void kirjoita(int x) {
        try {
            fileout.write(x);
        } catch (Exception e) {
            System.out.println("Kirjoittaminen ei toimi" + e);
        }
    }
    
    /**
     * Sulkee kirjoittajan.
     */
    
    public void sulje() {
        try {
            fileout.close();
        } catch (Exception e) {
            System.out.println("Sulkeminen ei onnistunut");
        }
    }
}
