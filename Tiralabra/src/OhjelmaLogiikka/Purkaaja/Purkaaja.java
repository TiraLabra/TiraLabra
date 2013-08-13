package OhjelmaLogiikka.Purkaaja;

import Tiedostokasittely.TiedostoKirjoittaja;
import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.Koodi;
import Tietorakenteet.OmaMap;
import Tietorakenteet.Pari;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Luokka joka vastaa tiedoston purkamisesta
 *
 */
public class Purkaaja {

    private long tiedostonKoko;

    /**
     * Konstruktori
     */
    public Purkaaja() {
    }

    /**
     * Purkaa annetun tiedoston annettuun tiedostoon.
     *
     * @param sisaanTiedosto Tiedosto joka puretaan
     * (tiedostonimi.header-tiedoston oltava myös olemassa)
     * @param ulosTiedosto Tiedosto johonka puretaan
     */
    public void pura(String sisaanTiedosto, String ulosTiedosto) {
        try {
            long aika = System.nanoTime();

            Pari<Integer, OmaMap<Koodi, byte[]>> paluu = (new HeaderLukija()).lueHeader(new TiedostoLukija(sisaanTiedosto + ".header"));

            OmaMap<Koodi, byte[]> koodit = paluu.toinen;
            int viimeisessaTavussaMerkitseviaBitteja = paluu.ensimmainen;

            puraData(sisaanTiedosto, ulosTiedosto, koodit, viimeisessaTavussaMerkitseviaBitteja);
            tulostaStatsit(aika);

        } catch (Exception ex) {
            System.out.println("Jotain meni pieleen: " + ex.getMessage());
        }
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
     * kirjoituksesa
     */
    private void puraData(String sisaanTiedosto, String ulosTiedosto, OmaMap<Koodi, byte[]> koodit, int viimeisessaTavussaMerkitseviaBitteja) throws FileNotFoundException, IOException {
        TiedostoLukija lukija = new TiedostoLukija(sisaanTiedosto);
        TiedostoKirjoittaja kirjoittaja = new TiedostoKirjoittaja(ulosTiedosto);
        lukija.avaaTiedosto();
        kirjoittaja.avaaTiedosto();

        (new PurkuKoodi()).kasitteleTiedosto(lukija, kirjoittaja, viimeisessaTavussaMerkitseviaBitteja, koodit);
        tiedostonKoko = kirjoittaja.koko();
        lukija.suljeTiedosto();
        kirjoittaja.suljeTiedosto();
    }

    private void tulostaStatsit(long aika) {
        aika = (System.nanoTime() - aika);
        System.out.println("Puretun tiedoston koko: " + (double) tiedostonKoko / 1024 / 1024 + " megatavua");
        System.out.println("Purkamiseen kului " + aika / 1000000 + " ms");
        System.out.println("Käsiteltiin " + ((double) tiedostonKoko / 1024 / 1024 / ((double) aika / 1000000000)) + " megatavua/sekunti");
    }
}
