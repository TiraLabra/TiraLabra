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
            TiedostoLukija lukija = new TiedostoLukija(pakattu);
            TiedostoKirjoittaja kirjoittaja = new TiedostoKirjoittaja(kohde);


            Pari<Integer, OmaMap<String, OmaList<Byte>>> paluu = (new HeaderLukija()).lueHeader(pakattu);
            OmaMap<String, OmaList<Byte>> koodit = paluu.toinen;

            int viimeisessaTavussaMerkitseviaBitteja = paluu.ensimmainen;
            OmaList<Byte> purettuData = puraData(lukija.lueTiedosto(), koodit, viimeisessaTavussaMerkitseviaBitteja);
            System.out.println("purettudata koko: " + purettuData.size());
            kirjoittaja.kirjoitaTiedosto(purettuData);


        } catch (Exception ex) {
            System.out.println("Jotain meni pieleen: " + ex.getMessage());

        }
    }

    private Pari<String, OmaList<Byte>> parseroiRivi(String rivi) {
        int rajanPaikka = rivi.length() - 1;
        while (rajanPaikka > 0) {
            if (rivi.charAt(rajanPaikka) == ' ') {
                break;
            }
            --rajanPaikka;
        }
        Pari paluu = new Pari();
        paluu.ensimmainen = rivi.substring(rajanPaikka + 1);

        byte[] merkki = rivi.substring(0, rajanPaikka).getBytes();
        OmaList<Byte> merkkiTavuina = new OmaArrayList<Byte>();

        for (int i = 0; i < merkki.length; ++i) {
            merkkiTavuina.add(merkki[i]);
        }

        paluu.toinen = merkkiTavuina;
        return paluu;
    }

    private OmaList<Byte> puraData(OmaList<Byte> pakattuData, OmaMap<String, OmaList<Byte>> koodit, int viimeisessaTavussaMerkitseviaBitteja) {
        OmaList<Byte> puretut = new OmaArrayList<Byte>();
        Byte kasiteltavaTavu = 0;
        String koodi = "";
        for (int i = 0; i < pakattuData.size(); ++i) {
            kasiteltavaTavu = pakattuData.get(i);

            int maks = 8;
            if (i == pakattuData.size() - 1) {
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


        }
        return puretut;
    }
}
