package OhjelmaLogiikka.Purkaaja;

import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import Tietorakenteet.Pari;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HeaderLukija {

    private final int OFFSET = 127;

    /**
     * Lukee annetusta tiedostosta headerin tiedot
     *
     * @param sisaan Tiedostonimi
     * @return Parin joka sisältää Integerin joka kertoo montako bittiä on
     * merkitseviä viimeisessä tavussa ja Mapin joka sisältää koodi -
     * blokki-parit. Koodit on Stringeinä, blokit tavulistoina
     * @throws FileNotFoundException jos annettua tiedostoa ei löydy
     * @throws IOException Jos tapahtuu IO-virhe
     */
    public Pari<Integer, OmaMap<String, OmaList<Byte>>> lueHeader(String sisaan) throws FileNotFoundException, IOException {
        TiedostoLukija headerLukija = new TiedostoLukija(sisaan + ".header");

        OmaList<Byte> tavut = headerLukija.lueTiedosto();
     
        return lueHeaderinTiedot(tavut);
    }

    private Pari<Integer, OmaMap<String, OmaList<Byte>>> lueHeaderinTiedot(OmaList<Byte> tiedosto) {
        Pari<Integer, OmaMap<String, OmaList<Byte>>> paluu = new Pari<Integer, OmaMap<String, OmaList<Byte>>>();

        paluu.ensimmainen = tiedosto.get(0) + OFFSET;

        // koska kirjoitushetkellä header on eri tiedostossa kuin data, ei headerin pituudella tehdä vielä mitään
        // jätetään siis tavut 1 - 4 huomioimatta





        paluu.toinen = lueKoodiBlokkiParit(tiedosto);


        return paluu;
    }

    private OmaMap<String, OmaList<Byte>> lueKoodiBlokkiParit(OmaList<Byte> tiedosto) {
        OmaMap<String, OmaList<Byte>> koodit = new OmaHashMap<String, OmaList<Byte>>();
        // käsitellyt tavut - 
        int paikka = 5;
        while (paikka < tiedosto.size()) {
            paikka += lueYksiKoodiBlokkiPari(paikka, tiedosto, koodit);
        }

        assert (paikka == tiedosto.size());

        return koodit;
    }

    private int lueYksiKoodiBlokkiPari(int paikka, OmaList<Byte> tiedosto, OmaMap<String, OmaList<Byte>> koodit) {

        int kasiteltyjaTavuja = 3; // blokin pituus, koodin pituus, koodin viimeisessä tavussa merkitseviä bittejä = 3 tavua dataa heti alkuun

        // luetaan kirjanpitotieto
        int blokinPituus = tiedosto.get(paikka) + OFFSET;
        ++paikka;
        assert (blokinPituus >= 1 && blokinPituus <= 255);

        int koodinPituus = tiedosto.get(paikka) + OFFSET;
        ++paikka;
        assert (koodinPituus >= 1 && koodinPituus <= 255);

        int merkitseviaBitteja = tiedosto.get(paikka) + OFFSET;
        ++paikka;
        assert (koodinPituus >= 1 && koodinPituus <= 8);

        kasiteltyjaTavuja += blokinPituus + koodinPituus;

        OmaList<Byte> blokki = lueBlokki(blokinPituus, tiedosto, paikka);
        paikka += blokinPituus;

        String koodi = "";
        // optimointia - käytä string builderia
        for (int i = 0; i < koodinPituus; ++i) {
            byte tavu = tiedosto.get(paikka + i);


            for (int j = 0; j < 8; ++j) {
                if (i == koodinPituus - 1 && j == merkitseviaBitteja) {
                    break;
                }
                int luettuArvo = tavu & 1 << j;
                luettuArvo = luettuArvo >> j;

                assert (luettuArvo == 1 || luettuArvo == 0);
                if (luettuArvo == 1) {
                    koodi += "1";
                } else {
                    koodi += "0";
                }

            }
        }

        byte[] foo = new byte[blokki.size()];

        for (int i = 0; i < blokki.size(); ++i) {
            foo[i] = blokki.get(i);
        }

        koodit.put(koodi, blokki);
        return kasiteltyjaTavuja;
    }

    private OmaList<Byte> lueBlokki(int blokinPituus, OmaList<Byte> tiedosto, int paikka) {
        // luetaan blokki
        OmaList<Byte> blokki = new OmaArrayList<Byte>();
        for (int i = 0; i < blokinPituus; ++i) {
            blokki.add(tiedosto.get(paikka + i));

        }
        return blokki;
    }
}
