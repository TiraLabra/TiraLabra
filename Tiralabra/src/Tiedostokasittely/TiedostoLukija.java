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

public class TiedostoLukija {

    private BufferedInputStream lukuStream;
    private InputStream inputStream;
    private final File TIEDOSTO;
    private final int KOKO;
 

    public TiedostoLukija(String nimi, int alkionKokoTavuissa) {
        TIEDOSTO = new File(nimi);
        KOKO = alkionKokoTavuissa;        
    }

    public OmaList<OmaList<Byte>> lueTiedosto() throws FileNotFoundException, IOException {
       
            avaaStream();
            OmaList luetutAlkiot = new OmaArrayList<OmaList<Byte>>();
            
            byte [] puskuri = new byte[KOKO];
            int luettu  = lukuStream.read(puskuri);
            
            while (luettu != -1) {
                
                OmaList<Byte> luetutList = new OmaArrayList<Byte>();
                                
                for (int i = 0; i < luettu; ++i) {
                    luetutList.add(puskuri[i]);
                }
                
                luetutAlkiot.add(luetutList);
                
                luettu = lukuStream.read(puskuri);
            }
                        
            suljeStream();
            return luetutAlkiot;
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
