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

    public void kirjoitaTiedosto(OmaList<Byte> tieto) throws IOException {

        avaaStream();
        kirjoita(tieto);
        suljeStream();
    }

    private void avaaStream() throws FileNotFoundException  {
        inputStream = new FileOutputStream(TIEDOSTO);
        kirjoitusStream = new BufferedOutputStream(inputStream);
    }

    private void suljeStream() throws IOException {
        kirjoitusStream.close();
        inputStream.close();
    }

    private void kirjoita(OmaList<Byte> tieto) throws IOException {
        byte[] taulu = new byte[1];
        
        for (int i = 0, j = 0; i < tieto.size(); ++i) {
            taulu[j] = tieto.get(i);
            kirjoitusStream.write(taulu);
        }
    }
}
