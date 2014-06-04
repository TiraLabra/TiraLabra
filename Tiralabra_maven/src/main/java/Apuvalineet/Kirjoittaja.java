package Apuvalineet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
