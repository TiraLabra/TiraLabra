package Toteutus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class PakkauksenLukija {
    private Reader lukija;
    private StringBuilder teksti;
    
    public PakkauksenLukija(String polku) throws FileNotFoundException, UnsupportedEncodingException {
        this.lukija = new InputStreamReader(new FileInputStream(polku), "UTF-8");
        this.teksti = new StringBuilder();
    }
    
    public String getTeksti() {
        return this.teksti.toString();
    }
    
    public void luePakkaus() throws IOException {
        while (true) {
            int arvo = lukija.read();
            if (arvo != -1) {
                teksti.append((char) arvo);
            }
            else {
                break;
            }
        }
    }
}
