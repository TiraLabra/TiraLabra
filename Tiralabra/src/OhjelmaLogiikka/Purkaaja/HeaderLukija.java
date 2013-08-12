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
    private int blokinPituus;

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

        byte[] headerAlku = new byte[2];
        if (headerLukija.lue(headerAlku) != 2) {
            throw new IOException("Header-tiedoston korruptoitunut - ensimmäisen  tavun luku epäonnistui");
        }
        
        
       
        paluu.ensimmainen = headerAlku[0] + OFFSET;
        
        blokinPituus = headerAlku[1] + OFFSET;
         
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

        int pituus = blokinPituus;
        int koodinPituusBiteissa = lukuPuskuri[0] + OFFSET;
        // poikkeava blokki - onkin tallennettu blokin pituus koska se on poikkeava (viimeinen blokki)
       
        if (koodinPituusBiteissa == 0) {
            if (headerLukija.lue(lukuPuskuri) == 0) {
                throw new IOException("Header-tiedoston korruptoitunut - koodiblokin koon lukeminen epäonnistui");
            }
            
            pituus = lukuPuskuri[0] + OFFSET;
            
            if (headerLukija.lue(lukuPuskuri) == 0) {
                throw new IOException("Header-tiedoston korruptoitunut - koodiblokin koon lukeminen epäonnistui");
            }
            
            koodinPituusBiteissa = lukuPuskuri[0] + OFFSET;
        }

        assert (koodinPituusBiteissa >= 1 && koodinPituusBiteissa <= 64);


        byte[] blokki = lueBlokki(headerLukija, pituus);
        Koodi koodi = new Koodi();

        koodi.koodi = muodostaja.muodostaKoodi(koodinPituusBiteissa);
        koodi.pituus = koodinPituusBiteissa;

        koodit.put(koodi, blokki);
        return true;
    }

    private byte[] lueBlokki(TiedostoLukija headerLukija, int pituus) throws IOException {


        byte[] puskuri = new byte[pituus];

        int luettu = headerLukija.lue(puskuri);
        assert (luettu == pituus);
        
        return puskuri;
    }
}
