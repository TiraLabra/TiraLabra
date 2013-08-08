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

    public TiedostoLukija(String nimi) {
        TIEDOSTO = new File(nimi);
    }

    public OmaList<Byte> lueTiedosto() throws FileNotFoundException, IOException {
        BufferedInputStream stream = new BufferedInputStream(new FileInputStream(TIEDOSTO));
        try {
            OmaList<Byte> luettuData = lue(stream);
            return luettuData;
        } finally {
            stream.close();
        }

    }


    private OmaList<Byte> lue(BufferedInputStream stream) throws IOException {

        OmaList luettuData = new OmaArrayList<Byte>();
        byte[] puskuri = new byte[1];

        int luettu = stream.read(puskuri);

        while (luettu != -1) {
            luettuData.add(puskuri[0]);
            luettu = stream.read(puskuri);
        }

        return luettuData;
    }

    
}
