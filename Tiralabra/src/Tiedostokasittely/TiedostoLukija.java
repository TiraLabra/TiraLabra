package Tiedostokasittely;

import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaList;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 Luokka joka lukee tavuja annetusta tiedostosta. Tarkempi dokumentaatio tulee kun olen varma etten muokkaa luokkaa hirveästi enää
 */

public class TiedostoLukija {

    private BufferedInputStream lukuStream;
    private InputStream inputStream;
    private final File TIEDOSTO;
    private final int KOKO;
 

    public TiedostoLukija(String nimi) {
        TIEDOSTO = new File(nimi);
        KOKO = 4;        
    }

    public OmaList<Byte> lueTiedosto() throws FileNotFoundException, IOException {
       
            avaaStream();
            OmaList luettuData = new OmaArrayList<OmaList<Byte>>();
            
            byte [] puskuri = new byte[KOKO];
            int luettu  = lukuStream.read(puskuri);
            
            while (luettu != -1) {
                
                for (int i = 0; i < luettu; ++i) {
                    luettuData.add(puskuri[i]);
                }
                luettu = lukuStream.read(puskuri);
            }
                        
            suljeStream();
            return luettuData;
    }

    private void avaaStream() throws FileNotFoundException {
        inputStream = new FileInputStream(TIEDOSTO);
        lukuStream = new BufferedInputStream(inputStream);
    }

    private void suljeStream() throws IOException {
        lukuStream.close();
        inputStream.close();
    }
}
