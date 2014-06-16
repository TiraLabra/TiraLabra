package KayttamattomatLuokat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TekstinKirjoittaja {
    private FileWriter kirjoittaja;
    private String polku;
    
    public TekstinKirjoittaja(String polku) throws IOException {
        this.polku = polku;
        this.kirjoittaja = new FileWriter(new File(polku));
    }
    
    public void kirjoita(String teksti) throws IOException {
        kirjoittaja.write(teksti);
        kirjoittaja.close();
    }
}