package OhjelmaLogiikka.Purkaaja;

import OhjelmaLogiikka.BittiUtility;
import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.Koodi;
import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaMap;
import Tietorakenteet.Pari;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HeaderLukija {

    private final int OFFSET = 128;

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
    public Pari<Integer, OmaMap<Koodi, byte[]>> lueHeader(String sisaan) throws FileNotFoundException, IOException {
        TiedostoLukija headerLukija = new TiedostoLukija(sisaan + ".header");
        headerLukija.avaaTiedosto();


        Pari<Integer, OmaMap<Koodi, byte[]>> paluu = new Pari<Integer, OmaMap<Koodi, byte[]>>();

        byte[] headerAlku = new byte[1];
        if (headerLukija.lue(headerAlku) != 1) {
            throw new IOException("Header-tiedoston korruptoitunut - ensimmäisen viiden tavun luku epäonnistui");
        }

        paluu.ensimmainen = headerAlku[0] + OFFSET;

        paluu.toinen = lueKoodiBlokkiParit(headerLukija);
        headerLukija.suljeTiedosto();

        return paluu;
    }

    private OmaMap<Koodi, byte[]> lueKoodiBlokkiParit(TiedostoLukija headerLukija) throws IOException {
        OmaMap<Koodi, byte[]> koodit = new OmaHashMap<Koodi, byte[]>();

        while (lueYksiKoodiBlokkiPari(headerLukija, koodit)) {
        }


        for (int i = 0; i < 8; ++i) {
            Koodi koodi = new Koodi();
            koodi.pituus = 3;
            koodi.koodi = i;

        }
        return koodit;
    }

    private boolean lueYksiKoodiBlokkiPari(TiedostoLukija headerLukija, OmaMap<Koodi, byte[]> koodit) throws IOException {

        byte[] lukuPuskuri = new byte[1];

        if (headerLukija.lue(lukuPuskuri) == -1) {
            return false; // tiedoston loppu
        }

        int blokinPituus = lukuPuskuri[0] + OFFSET;
        assert (blokinPituus >= 1 && blokinPituus <= 255);

        if (headerLukija.lue(lukuPuskuri) == 0) {
            throw new IOException("Header-tiedoston korruptoitunut - koodiblokin koon lukeminen epäonnistui");
        }

        int koodinPituusBiteissa = lukuPuskuri[0] + OFFSET;
        assert (koodinPituusBiteissa >= 1 && koodinPituusBiteissa <= 64);


        byte[] blokki = lueBlokki(blokinPituus, headerLukija);
        Koodi koodi = lueKoodi(koodinPituusBiteissa, headerLukija);

        koodit.put(koodi, blokki);
        return true;
    }

    private byte[] lueBlokki(int blokinPituus, TiedostoLukija headerLukija) throws IOException {


        byte[] puskuri = new byte[blokinPituus];


        if (headerLukija.lue(puskuri) != blokinPituus) {
            throw new IOException("Header-tiedoston korruptoitunut - blokin luku epäonnistui");
        }

        return puskuri;
    }
    // koodin pituus - bittejä

    private Koodi lueKoodi(int koodinPituus, TiedostoLukija headerLukija) throws IOException {
        byte[] lukuPuskuri;
        lukuPuskuri = new byte[koodinPituus / 8 + 1];

        if (headerLukija.lue(lukuPuskuri) != lukuPuskuri.length) {
            throw new IOException("Header-tiedoston korruptoitunut - koodi lukeminen epäonnistu");
        }
        Koodi koodi = new Koodi();
        koodi.pituus = koodinPituus;

        int paikka = 0;
        int puskurinPaikka = 0;
        for (int i = 0; i < koodinPituus; ++i) {

            int luettuArvo = BittiUtility.haeBitinArvoPaikasta(lukuPuskuri[puskurinPaikka], paikka);
            ++paikka;

            if (paikka == 8) {
                paikka = 0;
                ++puskurinPaikka;
            }
            assert (luettuArvo == 1 || luettuArvo == 0);

            // koodi.koodi = koodi.koodi | (luettuArvo << i);

            koodi.koodi = BittiUtility.tallennaBitinArvoPaikalle(koodi, luettuArvo, i);
        }

        return koodi;
    }
}
