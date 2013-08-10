package Tiedostokasittely;

import Tietorakenteet.OmaList;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Luokka joka kirjoittaa tietoa annettuun tiedostosta. Tarkempi dokumentaatio
 * tulee kun olen varma etten muokkaa luokkaa hirveästi enää
 */
public class TiedostoKirjoittaja {

    private BufferedOutputStream kirjoitusStream;
    private OutputStream inputStream;
    private final File TIEDOSTO;

    public TiedostoKirjoittaja(String nimi) {
        TIEDOSTO = new File(nimi);

    }

    public void kirjoita(byte[] kirjoitusPuskuri) throws IOException {
        kirjoitusStream.write(kirjoitusPuskuri);
    }

    public void avaaTiedosto() throws FileNotFoundException {
        inputStream = new FileOutputStream(TIEDOSTO);
        kirjoitusStream = new BufferedOutputStream(inputStream);
    }

    public void suljeTiedosto() throws IOException {
        kirjoitusStream.close();
        inputStream.close();
    }
    
    public long koko() {
        return TIEDOSTO.length();
    }
}
