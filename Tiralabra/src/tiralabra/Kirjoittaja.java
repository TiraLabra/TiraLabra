/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author Joonas
 */
public class Kirjoittaja {
    
     FileOutputStream fileout;

    public Kirjoittaja(String tiedosto) {
        try {
            fileout = new FileOutputStream(new File(tiedosto));
        } catch (Exception e) {
            System.out.println("Tiedostoa " + e +  " ei löydy");
        }
    }

    public void kirjoita(int x) {
        try {
            fileout.write(x);
        } catch (Exception e) {
            System.out.println("Kirjoittaminen ei toimi" + e);
        }
    }

    public void sulje() {
        try {
            fileout.close();
        } catch (Exception e) {
            System.out.println("Sulkeminen ei onnistunut");
        }
    }
    
}
