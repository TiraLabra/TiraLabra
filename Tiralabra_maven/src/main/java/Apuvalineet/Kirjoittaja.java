package Apuvalineet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Luokka toimii "FileWriter" -oliona, jolla ei ole tarkoitus muuta kuin kirjoittaa valittu tiedosto.
 */

public class Kirjoittaja {
    private FileWriter kirjoittaja;
    
    public Kirjoittaja(String polku) throws IOException {
        this.kirjoittaja = new FileWriter(new File(polku));
    }
    
    public void kirjoita(String teksti) throws IOException {
        kirjoittaja.append(teksti);
        kirjoittaja.close();
    }
}
