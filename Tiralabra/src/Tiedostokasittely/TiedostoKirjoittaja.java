package Tiedostokasittely;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Luokka joka kirjoittaa tietoa annettuun tiedostoon. 
 */
public class TiedostoKirjoittaja implements ITiedostoKirjoittaja {

    private BufferedOutputStream kirjoitusStream;
    private OutputStream inputStream;
    private final File TIEDOSTO;
    /**
     * Konstruktori. Parametrina tiedoston nimi johonka kirjoitetaan
     * @param nimi tiedoston nimi
     */
    public TiedostoKirjoittaja(String nimi) {
        TIEDOSTO = new File(nimi);

    }
    /**
     * Kirjoittaa annetut tavut tiedostoon
     * @param kirjoitusPuskuri Kirjoitettavat tavut
     * @throws IOException Jos jokin menee pieleen
     */
    @Override
    public void kirjoita(byte[] kirjoitusPuskuri) throws IOException {
        kirjoitusStream.write(kirjoitusPuskuri);
    }
    /**
     * Avaa tiedoston kirjoitusta varten
     * @throws FileNotFoundException Jos tiedostoa ei löydy
     */
    @Override
    public void avaaTiedosto() throws FileNotFoundException {
        inputStream = new FileOutputStream(TIEDOSTO);
        kirjoitusStream = new BufferedOutputStream(inputStream);
    }
    /**
     * Sulkee tiedoston
     * @throws IOException Jos jokin menee pieleen
     */
    @Override
    public void suljeTiedosto() throws IOException {
        kirjoitusStream.close();
        inputStream.close();
    }
    /**
     * Antaa tiedoston koon
     * @return Tiedoston koko tavuissa
     */
    @Override
    public long koko() {
        return TIEDOSTO.length();
    }
}
