package Tiedostokasittely;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Luokka joka lukee tavuja annetusta tiedostosta. 
 */
public class TiedostoLukija implements ITiedostoLukija {

    private final File TIEDOSTO;
    private BufferedInputStream stream;

    /**
     * Konstruktori. Parametrina tiedoston nimi josta luetaan
     * @param nimi Tiedoston nimi
     */
    public TiedostoLukija(String nimi) {
        TIEDOSTO = new File(nimi);      
        stream = null;
    }
    
    /**
     * Avaa tiedoston lukemista varten
     * @throws FileNotFoundException Jos tiedostoa ei löydy
     */
    @Override
    public void avaaTiedosto() throws FileNotFoundException {
        stream = new BufferedInputStream(new FileInputStream(TIEDOSTO));
    }
    /**
     * Sulkee tiedoston
     * @throws IOException Jos jokin menee pieleen 
     */
    @Override
    public void suljeTiedosto() throws IOException {
        if (stream != null) {
            stream.close();
        }
        stream = null;
    }
    /**
     * Lukee tiedostosta tavuja, maksimissaan puskurin koon verran. 
     * @param puskuri Lukupuskuri
     * @return Montako tavua luettiin
     * @throws IOException Jos jotakin meni pieleen
     */
    @Override
    public int lue(byte [] puskuri) throws IOException {
      
        return stream.read(puskuri);
    }
    /**
     * Kertoo tiedoston koko
     * @return Tiedoston koko
     */
    @Override
    public long koko() {
        return TIEDOSTO.length();
    }
}
