package OhjelmaLogiikka.Pakkaaja;

import OhjelmaLogiikka.BittiUtility;
import Tiedostokasittely.TiedostoKirjoittaja;
import Tietorakenteet.ByteWrapper;
import Tietorakenteet.Koodi;
import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import Tietorakenteet.Pari;
import java.io.IOException;

public class HeaderMuodostaja {

    private final int OFFSET = 128;

    /**
     * Muodostaa headerin. Formaatin yksityiskohdat löytyy
     * tiedostoformaatti.txt-tiedostosta. Huom: kaikki yhden bitin pituuskentät
     * tallennetaan offsetin kanssa koska java käyttää vain etumerkillisiä
     * lukuja ja 255 -> 127 on liian suuri tiputus (eli -128 = 0, 127 = 255)
     *
     * @param koodit Blokki-koodiparit
     * @param viimeisessaTavussaMerkitseviaBitteja montako bittiä viimeisessä
     * tavussa on merkitseviä dataosiossa merkitsee mitään
     * @return tavut jotka kirjoitetaan tiedostoon.
     */
    public long muodostaHeader(String header, OmaMap<ByteWrapper, Koodi> koodit, int viimeisessaTavussaMerkitseviaBitteja) {
        try {
            TiedostoKirjoittaja kirjoittaja = new TiedostoKirjoittaja(header);
            kirjoittaja.avaaTiedosto();

            alustaHeader(viimeisessaTavussaMerkitseviaBitteja, kirjoittaja);

            OmaList<ByteWrapper> blokit = koodit.avaimet();

            for (int i = 0; i < blokit.size(); ++i) {
                muodostaBlokkiAvainPari(blokit.get(i), koodit.get(blokit.get(i)), kirjoittaja);
            }

            kirjoittaja.suljeTiedosto();
            return kirjoittaja.koko();
        } catch (Exception ex) {
            System.out.println("Jotain meni pieleen headerin luomisessa " + ex.getMessage());
        }
        return 0;
    }

    /**
     * Tallentaa taulukkoon merkitsevien bittien määrän headeriin
     *
     * @param viimeisessaTavussaMerkitseviaBitteja montako bittiä viimeisessä
     * tavussa on merkitseviä
     * @param kirjoittaja TiedostoKirjoittaja johonka kirjoitetaan
     * @throws IllegalArgumentException jos viimeisessaTavussaMerkitseviaBitteja
     * on alle 1 tai suurempi kuin 8
     * @throws IOException jos jotakin menee vikaan tiedoston kirjoituksessa
     */
    private void alustaHeader(Integer viimeisessaTavussaMerkitseviaBitteja, TiedostoKirjoittaja kirjoittaja) throws IllegalArgumentException, IOException {
        if (viimeisessaTavussaMerkitseviaBitteja > 8 || viimeisessaTavussaMerkitseviaBitteja < 1) {
            throw new IllegalArgumentException("Tavussa oltava vähintään yksi ja korkeintaan 8 merkitsevää bittiä - annettu arvo: " + viimeisessaTavussaMerkitseviaBitteja);
        }

        byte[] puskuri = new byte[1];
        puskuri[0] = (byte) (viimeisessaTavussaMerkitseviaBitteja - OFFSET);
        kirjoittaja.kirjoita(puskuri);
    }

    /**
     * Muodostaa yhden blokki-koodi-parin ja kirjoittaa sen headeriin headeriin
     *
     * @param blokki Koodattava blokki
     * @param koodi blokin koodi
     * @param kirjoittaja TiedostoKirjoittaja johonka kirjoitetaan
     * @throws IllegalArgumentException Jos blokin pituus on suurempi kuin 255
     * tavua - pituuden on mahduttava yhteen tavuun, tai jos koodin pituus on
     * suurempi kuin 64 - koodin on mahduttava long-muuttujaan
     * @throws IOException Jos jotakin menee vikaan kirjoitettaessa tietoa headeriin
     */
    private void muodostaBlokkiAvainPari(ByteWrapper blokki, Koodi koodi, TiedostoKirjoittaja kirjoittaja) throws IllegalArgumentException, IOException {
        if (blokki.size() > 255) {
            throw new IllegalArgumentException("Annetun blokin pituus on suurempi kuin header-formaattiin on varattu tilaa: Pituus: " + blokki.size());
        }
        byte[] puskuri = new byte[1];

        // tallennettavan bittiblokin pituus tavuissa
        puskuri[0] = (byte) (blokki.size() - OFFSET);
        kirjoittaja.kirjoita(puskuri);

        if (koodi.pituus > 64) {
            throw new IllegalArgumentException("Koodin pituus on suurempi kuin header-formaattiin on varattu tilaa: Pituus: " + koodi.pituus);
        }

        byte[] koodiTavuina = muunnaKoodiTavuiksi(koodi);

        puskuri[0] = (byte) (koodi.pituus - OFFSET);
        kirjoittaja.kirjoita(puskuri); // tallennetaan merkitsevien bittien määrä  - max 64 bittiä koska bittien on mahduttava long-muuttujaan
        kirjoittaja.kirjoita(blokki.byteTaulukko);
        kirjoittaja.kirjoita(koodiTavuina);

    }

    /**
     * Muuntaa annetun Koodi-objektin koodi-kentän byte[]-taulukoksi.
     *
     * @param koodi Muunnettava koodi
     * @return byte [] koodin bitit tallennettuna byte-taulukkoon.
     */
    private byte[] muunnaKoodiTavuiksi(Koodi koodi) {
        // muunnetaan tallennettava koodi String -> byteihin pakatut bitit
        byte[] muunnettuKoodi = new byte[koodi.pituus / 8 + 1];

        int paikka = 0;
        int tavunPaikka = 0;
        for (int i = 0; i < koodi.pituus; ++i) {
            int arvo = BittiUtility.haeBitinArvoPaikasta(koodi, i);
            muunnettuKoodi[tavunPaikka] = BittiUtility.tallennaBitinArvoPaikalle(muunnettuKoodi[tavunPaikka], arvo, paikka);

            ++paikka;
            if (paikka == 8) {
                paikka = 0;
                ++tavunPaikka;
            }
        }

        assert (tavunPaikka * 8 + paikka == koodi.pituus);
        return muunnettuKoodi;
    }
}
