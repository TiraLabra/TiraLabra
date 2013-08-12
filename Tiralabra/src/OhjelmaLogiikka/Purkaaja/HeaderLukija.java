package OhjelmaLogiikka.Purkaaja;

import OhjelmaLogiikka.KanonisoidunKoodinMuodostaja;
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
        KanonisoidunKoodinMuodostaja muodostaja = new KanonisoidunKoodinMuodostaja();
        while (lueYksiKoodiBlokkiPari(headerLukija, koodit, muodostaja)) {
        }

        return koodit;
    }

    private boolean lueYksiKoodiBlokkiPari(TiedostoLukija headerLukija, OmaMap<Koodi, byte[]> koodit, KanonisoidunKoodinMuodostaja muodostaja) throws IOException {

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
        Koodi koodi = new Koodi();
        
        koodi.koodi = muodostaja.muodostaKoodi(koodinPituusBiteissa);
        koodi.pituus = koodinPituusBiteissa;
        
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
}
