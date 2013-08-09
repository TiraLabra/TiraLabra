package OhjelmaLogiikka.Purkaaja;

import Tiedostokasittely.TiedostoKirjoittaja;
import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import Tietorakenteet.Pari;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Purkaaja {

    private final int BLOKIN_KOKO;

    public Purkaaja(int blokinKoko) {
        BLOKIN_KOKO = blokinKoko;
    }

    public void pura(String pakattu, String kohde) {
        try {

            TiedostoKirjoittaja kirjoittaja = new TiedostoKirjoittaja(kohde);


            Pari<Integer, OmaMap<String, OmaList<Byte>>> paluu = (new HeaderLukija()).lueHeader(pakattu);
            OmaMap<String, OmaList<Byte>> koodit = paluu.toinen;


            int viimeisessaTavussaMerkitseviaBitteja = paluu.ensimmainen;

            OmaList<Byte> purettuData = puraData(pakattu, koodit, viimeisessaTavussaMerkitseviaBitteja);

            System.out.println("purettudata koko: " + purettuData.size());
            kirjoittaja.kirjoitaTiedosto(purettuData);


        } catch (Exception ex) {
            System.out.println("Jotain meni pieleen: " + ex.getMessage());

        }
    }

    private OmaList<Byte> puraData(String tiedosto, OmaMap<String, OmaList<Byte>> koodit, int viimeisessaTavussaMerkitseviaBitteja) throws FileNotFoundException, IOException {
        TiedostoLukija lukija = new TiedostoLukija(tiedosto);
        lukija.avaaTiedosto();

        OmaList<Byte> puretut = new OmaArrayList<Byte>();
        kasitteleTiedosto(lukija, viimeisessaTavussaMerkitseviaBitteja, koodit, puretut);

        lukija.suljeTiedosto();
        return puretut;
    }

    private void kasitteleTiedosto(TiedostoLukija lukija, int viimeisessaTavussaMerkitseviaBitteja, OmaMap<String, OmaList<Byte>> koodit, OmaList<Byte> puretut) throws IOException {
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

                if (koodit.containsKey(koodi)) {
                    puretut.addAll(koodit.get(koodi));
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
