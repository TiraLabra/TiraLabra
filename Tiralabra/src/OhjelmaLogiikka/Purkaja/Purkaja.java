package OhjelmaLogiikka.Purkaja;

import Poikkeukset.PurkuException;
import Tiedostokasittely.TiedostoKirjoittaja;
import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.HuffmanKoodi;
import Tietorakenteet.OmaMap;
import Tietorakenteet.Pari;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Luokka joka vastaa tiedoston purkamisesta
 *
 */
public class Purkaja {

    private long tiedostonKoko;

    /**
     * Konstruktori
     */
    public Purkaja() {
    }

    /**
     * Purkaa annetun tiedoston annettuun tiedostoon.
     *
     * @param sisaanTiedosto Tiedosto joka puretaan
     * (tiedostonimi.header-tiedoston oltava myös olemassa)
     * @param ulosTiedosto Tiedosto johonka puretaan
     * @throws FileNotFoundException Jos sisaanTiedostoa ei löydy
     * @throws IOException Jos tiedostokäsittelyssä tapahtuu virhe
     * @throws PurkuException Jos purkamisessa tapahtuu virhe
     */
    public void pura(String sisaanTiedosto, String ulosTiedosto) throws FileNotFoundException, IOException,  PurkuException {
            long aika = System.nanoTime();

            Pari<Integer, OmaMap<HuffmanKoodi, byte[]>> paluu = (new HeaderLukija()).lueHeader(new TiedostoLukija(sisaanTiedosto + ".header"));

            OmaMap<HuffmanKoodi, byte[]> koodit = paluu.toinen;
            int viimeisessaTavussaMerkitseviaBitteja = paluu.ensimmainen;

            puraData(sisaanTiedosto, ulosTiedosto, koodit, viimeisessaTavussaMerkitseviaBitteja);
            tulostaStatsit(aika);        
    }

    /**
     * Apumetodi. Avaa ja sulkee tiedostot ja välittää eteenpäin
     * kasitteleTiedosto()-metodille. Tallentaa myös puretun tiedoston koon.
     *
     * @param sisaanTiedosto Tiedosto joka puretaan
     * @param ulosTiedosto Tiedosto johonka tallennetaan puretut blokit
     * @param koodit Taulu joka sisältää koodi - blokki - parit
     * @param viimeisessaTavussaMerkitseviaBitteja Montako bittiä viimeisessä
     * luettavassa tavussa on merkitseviä
     * @throws FileNotFoundException Jos luettavaa tiedostoa ei löydy
     * @throws IOException Jos jotakin menee pieleen lukemisessa tai
     * @throws PurkuException jos purkamisessa tapahtuu virhe
     * kirjoituksesa
     */
    private void puraData(String sisaanTiedosto, String ulosTiedosto, OmaMap<HuffmanKoodi, byte[]> koodit, int viimeisessaTavussaMerkitseviaBitteja) throws FileNotFoundException, IOException, PurkuException {
        TiedostoLukija lukija = new TiedostoLukija(sisaanTiedosto);
        TiedostoKirjoittaja kirjoittaja = new TiedostoKirjoittaja(ulosTiedosto);
        lukija.avaaTiedosto();
        kirjoittaja.avaaTiedosto();

        (new PurkuKoodi()).kasitteleTiedosto(lukija, kirjoittaja, viimeisessaTavussaMerkitseviaBitteja, koodit);
        
        lukija.suljeTiedosto();
        kirjoittaja.suljeTiedosto();
        tiedostonKoko = kirjoittaja.koko();
    }

    private void tulostaStatsit(long aika) {
        aika = (System.nanoTime() - aika);
        System.out.println("Puretun tiedoston koko: " + (double) tiedostonKoko / 1024 / 1024 + " megatavua");
        System.out.println("Purkamiseen kului " + (double)aika / 1000000 + " ms");
        System.out.println("Käsiteltiin " + ((double) tiedostonKoko / 1024 / 1024 / ((double) aika / 1000000000)) + " megatavua/sekunti");
    }
}
