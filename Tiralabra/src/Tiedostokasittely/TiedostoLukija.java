package Tiedostokasittely;

import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaList;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Luokka joka lukee tavuja annetusta tiedostosta. Tarkempi dokumentaatio tulee
 * kun olen varma etten muokkaa luokkaa hirveästi enää
 */
public class TiedostoLukija {

    private final File TIEDOSTO;
    private BufferedInputStream stream;


    public TiedostoLukija(String nimi) {
        TIEDOSTO = new File(nimi);      
        stream = null;
    }

    public void avaaTiedosto() throws FileNotFoundException {
        stream = new BufferedInputStream(new FileInputStream(TIEDOSTO));
    }

    public void suljeTiedosto() throws IOException {
        if (stream != null) {
            stream.close();
        }
        stream = null;
    }

    public int lue(byte [] puskuri) throws IOException {
      
        return stream.read(puskuri);

    }
}
