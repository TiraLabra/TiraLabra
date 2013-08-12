package OhjelmaLogiikka.Pakkaaja;

import OhjelmaLogiikka.BittiUtility;
import Tiedostokasittely.TiedostoKirjoittaja;
import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.ByteWrapper;
import Tietorakenteet.Koodi;
import Tietorakenteet.OmaMap;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Tiivistaja {

    private final int BLOKIN_KOKO;
    private long ulosTiedostonKoko;

    public Tiivistaja(int blokinKoko) {
        BLOKIN_KOKO = blokinKoko;
    }

    public long haeTiedostonKoko() {
        return ulosTiedostonKoko;
    }

    public int tiivista(String sisaan, String ulos, OmaMap<ByteWrapper, Koodi> koodit) throws FileNotFoundException, IOException {

        TiedostoLukija lukija = new TiedostoLukija(sisaan);
        lukija.avaaTiedosto();

        TiedostoKirjoittaja kirjoittaja = new TiedostoKirjoittaja(ulos);
        kirjoittaja.avaaTiedosto();

        int bittejaKaytetty = kirjoitaBlokit(lukija, koodit, kirjoittaja);

        lukija.suljeTiedosto();
        kirjoittaja.suljeTiedosto();
        ulosTiedostonKoko = kirjoittaja.koko();
        return bittejaKaytetty;
    }

    private int kirjoitaBlokit(TiedostoLukija lukija, OmaMap<ByteWrapper, Koodi> koodit, TiedostoKirjoittaja kirjoittaja) throws IOException {
        byte[] puskuri = new byte[1];
        byte[] luetutTavut = new byte[BLOKIN_KOKO];
        int luettu;
        int bittejaKaytetty = 0;

        while ((luettu = lukija.lue(luetutTavut)) > 0) {
            bittejaKaytetty = kasitteleBlokki(luettu, luetutTavut, koodit, puskuri, bittejaKaytetty, kirjoittaja);
        }

        bittejaKaytetty = hoidaViimeinenTavu(bittejaKaytetty, kirjoittaja, puskuri);
        return bittejaKaytetty;
    }

    private int hoidaViimeinenTavu(int bittejaKaytetty, TiedostoKirjoittaja kirjoittaja, byte[] puskuri) throws IOException {
        if (bittejaKaytetty != 0) {
            kirjoittaja.kirjoita(puskuri);
        } else {
            bittejaKaytetty = 8; // viimeisessä tavussa on kaikki tavut merkitseviä jos koko tavu on kirjoitettu
        }
        return bittejaKaytetty;
    }

    private int kasitteleBlokki(int luettu, byte[] luetutTavut, OmaMap<ByteWrapper, Koodi> koodit, byte[] puskuri, int bittejaKaytetty, TiedostoKirjoittaja kirjoittaja) throws IOException {
        ByteWrapper blokki = new ByteWrapper();
        blokki.byteTaulukko = new byte[luettu];
        for (int i = 0; i < luettu; ++i) {
            blokki.byteTaulukko[i] = luetutTavut[i];
        }
        Koodi pakkausKoodi = koodit.get(blokki);
        bittejaKaytetty = kopioiTavuunPakkausKoodienBitteja(pakkausKoodi, puskuri, bittejaKaytetty, kirjoittaja);
        return bittejaKaytetty;
    }

    private int kopioiTavuunPakkausKoodienBitteja(Koodi pakkausKoodi, byte[] puskuri, int bittejaKaytetty, TiedostoKirjoittaja kirjoittaja) throws IOException {
        for (int j = 0; j < pakkausKoodi.pituus; ++j) {
            int arvo = BittiUtility.haeBitinArvoPaikasta(pakkausKoodi, j);

            puskuri[0] = BittiUtility.tallennaBitinArvoPaikalle(puskuri[0], arvo, bittejaKaytetty);

            ++bittejaKaytetty;
            if (bittejaKaytetty == 8) {

                kirjoittaja.kirjoita(puskuri);
                puskuri[0] = 0;
                bittejaKaytetty = 0;
            }
        }
        return bittejaKaytetty;
    }


}
