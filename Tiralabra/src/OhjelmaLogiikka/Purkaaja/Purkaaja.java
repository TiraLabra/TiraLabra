package OhjelmaLogiikka.Purkaaja;

import Tiedostokasittely.TiedostoKirjoittaja;
import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.OmaMap;
import Tietorakenteet.Pari;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Purkaaja {

    private final int BLOKIN_KOKO;
    private long tiedostonKoko;
    public Purkaaja(int blokinKoko) {
        BLOKIN_KOKO = blokinKoko;
    }

    public void pura(String sisaanTiedosto, String ulosTiedosto) {
        try {
            long aika = System.nanoTime();
            
            Pari<Integer, OmaMap<String, byte [] >> paluu = (new HeaderLukija()).lueHeader(sisaanTiedosto);
            OmaMap<String, byte [] > koodit = paluu.toinen;

            int viimeisessaTavussaMerkitseviaBitteja = paluu.ensimmainen;

            puraData(sisaanTiedosto, ulosTiedosto, koodit, viimeisessaTavussaMerkitseviaBitteja);
            
            aika = (System.nanoTime() - aika);
            System.out.println("Puretun tiedoston koko: " + (double)tiedostonKoko /1024/1024 + " megatavua");
            System.out.println("Purkamiseen kului " + aika/1000000 + " ms");
            System.out.println("Käsiteltiin " + ((double)tiedostonKoko/1024/1024 / (aika/1000000000)) + " megatavua/sekunti");
            
        } catch (Exception ex) {
            System.out.println("Jotain meni pieleen: " + ex.getMessage());

        }
    }

    private void puraData(String sisaanTiedosto, String ulosTiedosto, OmaMap<String, byte []> koodit, int viimeisessaTavussaMerkitseviaBitteja) throws FileNotFoundException, IOException {
        TiedostoLukija lukija = new TiedostoLukija(sisaanTiedosto);
        TiedostoKirjoittaja kirjoittaja = new TiedostoKirjoittaja(ulosTiedosto);
        lukija.avaaTiedosto();
        kirjoittaja.avaaTiedosto();
 
        kasitteleTiedosto(lukija, kirjoittaja, viimeisessaTavussaMerkitseviaBitteja, koodit);
        tiedostonKoko = kirjoittaja.koko();
        lukija.suljeTiedosto();
        kirjoittaja.suljeTiedosto();
    }

    private void kasitteleTiedosto(TiedostoLukija lukija, TiedostoKirjoittaja kirjoittaja, int viimeisessaTavussaMerkitseviaBitteja, OmaMap<String, byte []> koodit) throws IOException {
        String koodi = "";

        byte [] puskuri = new byte[2];
        byte [] luku = new byte[1];
        byte kasiteltavaTavu;

        
        lukija.lue(puskuri);
        boolean viimeinenTavu = false;
        while (true) {
            
            kasiteltavaTavu = puskuri[0];
            puskuri[0] = puskuri[1];


            int maks = 8;
            if (viimeinenTavu) {
                maks = viimeisessaTavussaMerkitseviaBitteja;
            }
            for (int j = 0; j < maks; ++j) {
                int luettuArvo = kasiteltavaTavu & (1 << j);
                luettuArvo = luettuArvo >> j;

                if (luettuArvo == 1) {
                    koodi += "1";
                } else {
                    koodi += "0";
                }
                byte [] array = koodit.get(koodi);
                if (array != null) {                  
                    kirjoittaja.kirjoita(array);
                    koodi = "";
                }
            }
            
            if (viimeinenTavu)
            {
                break;
            }
            
            if (lukija.lue(luku) == -1) {
                viimeinenTavu = true;
            }
            else {
                puskuri[1] = luku[0];
            }

        }
    }
}
