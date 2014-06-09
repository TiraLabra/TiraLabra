package Apuvalineet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Luokka toimii "FileWriter" -oliona, jolla ei ole tarkoitus muuta kuin kirjoittaa valittu tiedosto.
 */

public class Kirjoittaja {
    private Writer kirjoittaja;
    
    public Kirjoittaja(String polku) throws IOException {
        this.kirjoittaja = new OutputStreamWriter(new FileOutputStream(polku), "UTF-8");
    }
    
    public void kirjoita(String teksti) throws IOException {
        kirjoittaja.write(teksti);
        kirjoittaja.close();
    }
}
