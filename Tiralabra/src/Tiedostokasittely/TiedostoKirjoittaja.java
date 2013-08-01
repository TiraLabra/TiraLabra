package Tiedostokasittely;

import Tietorakenteet.OmaList;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TiedostoKirjoittaja {

    private BufferedOutputStream kirjoitusStream;
    private OutputStream inputStream;
    private final File TIEDOSTO;

    public TiedostoKirjoittaja(String nimi) {
        TIEDOSTO = new File(nimi);

    }

    public void kirjoitaTiedosto(OmaList<OmaList<Byte>> tieto) throws IOException {

        avaaStream();


        for (int i = 0; i < tieto.size(); ++i) {
            OmaList<Byte> tavut = tieto.get(i);
            byte [] taulu = new byte[tavut.size()];
            
            for (int j = 0; j < tavut.size(); ++j) {
                taulu[j] = tavut.get(j);
            }
            
            kirjoitusStream.write(taulu);
                    
        }


        suljeStream();

    }

    private void avaaStream() throws FileNotFoundException {
        inputStream = new FileOutputStream(TIEDOSTO);
        kirjoitusStream = new BufferedOutputStream(inputStream);
    }

    private void suljeStream() throws IOException {
        kirjoitusStream.close();
        inputStream.close();
    }
}
