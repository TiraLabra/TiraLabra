package OhjelmaLogiikka.Purkaaja;

import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaHashMap2;
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
    public Pari<Integer, OmaMap<String, byte [] >> lueHeader(String sisaan) throws FileNotFoundException, IOException {
        TiedostoLukija headerLukija = new TiedostoLukija(sisaan + ".header");
        headerLukija.avaaTiedosto();


        Pari<Integer, OmaMap<String, byte [] >> paluu = new Pari<Integer, OmaMap<String, byte [] >>();

        byte[] headerAlku = new byte[1];
        if (headerLukija.lue(headerAlku) != 1) {
            throw new IOException("Header-tiedoston korruptoitunut - ensimmäisen viiden tavun luku epäonnistui");
        }

        paluu.ensimmainen = headerAlku[0] + OFFSET;

        paluu.toinen = lueKoodiBlokkiParit(headerLukija);
        headerLukija.suljeTiedosto();

        return paluu;
    }

    private OmaMap<String, byte []> lueKoodiBlokkiParit(TiedostoLukija headerLukija) throws IOException {
        OmaMap<String, byte []> koodit = new OmaHashMap<String, byte []>();

        while (lueYksiKoodiBlokkiPari(headerLukija, koodit)) {
        }

        return koodit;
    }

    private boolean lueYksiKoodiBlokkiPari(TiedostoLukija headerLukija, OmaMap<String, byte [] > koodit) throws IOException {

        byte[] lukuPuskuri = new byte[1];

        if (headerLukija.lue(lukuPuskuri) == -1) {
            return false; // tiedoston loppu
        }

        int blokinPituus = lukuPuskuri[0] + OFFSET;
        assert (blokinPituus >= 1 && blokinPituus <= 255);

        if (headerLukija.lue(lukuPuskuri) == 0) {
            throw new IOException("Header-tiedoston korruptoitunut - koodiblokin koon lukeminen epäonnistui");
        }

        int koodinPituus = lukuPuskuri[0] + OFFSET;
        assert (koodinPituus >= 1 && koodinPituus <= 255);

        if (headerLukija.lue(lukuPuskuri) == 0) {
            throw new IOException("Header-tiedoston korruptoitunut - merkitsevien bittien koon lukeminen epäonnistui");
        }

        int merkitseviaBitteja = lukuPuskuri[0] + OFFSET;
        assert (merkitseviaBitteja >= 1 && merkitseviaBitteja <= 8);

        byte [] blokki = lueBlokki(blokinPituus, headerLukija);
        String koodi = lueKoodi(koodinPituus, headerLukija, merkitseviaBitteja);

        koodit.put(koodi, blokki);
        return true;
    }

    private byte [] lueBlokki(int blokinPituus, TiedostoLukija headerLukija) throws IOException {

        byte[] puskuri = new byte[blokinPituus];

       
        if (headerLukija.lue(puskuri) != blokinPituus) {
            throw new IOException("Header-tiedoston korruptoitunut - blokin luku epäonnistui");
        }
        
        return puskuri;
    }

    private String lueKoodi(int koodinPituus, TiedostoLukija headerLukija, int merkitseviaBitteja) throws IOException {
        byte[] lukuPuskuri;
        lukuPuskuri = new byte[koodinPituus];
        
        if (headerLukija.lue(lukuPuskuri) != koodinPituus) {
            throw new IOException("Header-tiedoston korruptoitunut - koodi lukeminen epäonnistu");
        }
        
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < koodinPituus; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (i == koodinPituus - 1 && j == merkitseviaBitteja) {
                    break;
                }

                int luettuArvo = lukuPuskuri[i] & 1 << j;
                luettuArvo = luettuArvo >> j;

                assert (luettuArvo == 1 || luettuArvo == 0);
                if (luettuArvo == 1) {
                    stringBuilder.append("1");
                } else {
                    stringBuilder.append("0");
                }

            }
        }
        String koodi = stringBuilder.toString();
        return koodi;
    }
}
