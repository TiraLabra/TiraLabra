package OhjelmaLogiikka.Purkaaja;

import OhjelmaLogiikka.BittiUtility;
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

            aika = (System.nanoTime() - aika);
            System.out.println("Puretun tiedoston koko: " + (double) tiedostonKoko / 1024 / 1024 + " megatavua");
            System.out.println("Purkamiseen kului " + aika / 1000000 + " ms");
            System.out.println("Käsiteltiin " + ((double) tiedostonKoko / 1024 / 1024 / ((double) aika / 1000000000)) + " megatavua/sekunti");

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

        kasitteleTiedosto(lukija, kirjoittaja, viimeisessaTavussaMerkitseviaBitteja, koodit);
        tiedostonKoko = kirjoittaja.koko();
        lukija.suljeTiedosto();
        kirjoittaja.suljeTiedosto();
    }

    /**
     * Purkaa annetun tiedoston annettuun tiedostoon huffman-koodien perusteella
     *
     * @param lukija lähdetiedosto
     * @param kirjoittaja kohdetiedosto
     * @param viimeisessaTavussaMerkitseviaBitteja Montako bittiä on merkitseviä
     * viimeisessä tavussa
     * @param koodit Koodi-blokkiparit
     * @throws IOException Jos lukeminen\kirjoitus epäonnistuu
     */
    private void kasitteleTiedosto(TiedostoLukija lukija, TiedostoKirjoittaja kirjoittaja, int viimeisessaTavussaMerkitseviaBitteja, OmaMap<Koodi, byte[]> koodit) throws IOException {


        byte[] puskuri = new byte[2];
        byte[] luku = new byte[1];
        byte kasiteltavaTavu;

        // luetaan kaksi tavua tietoa - oletus: pakatussa tiedostossa ainakin kaksi tavua!
        lukija.lue(puskuri);

        boolean viimeinenTavu = false;

        Koodi koodi = new Koodi();

        while (true) {
            // kahden tavun puskuri - luetaan aina luupin lopussa yksi tavu lisää - jos epäonnistuu niin vielä yksi kierros viimeisen tavun käsittelyyn ja sitten ulos luupista
            kasiteltavaTavu = puskuri[0];
            puskuri[0] = puskuri[1];


            int merkitseviaBitteja = 8;
            if (viimeinenTavu) { // jos viimeinen tavu, otetaan huomioon että ei välttämättä ole kahdeksaa merkitsevää bittiä
                merkitseviaBitteja = viimeisessaTavussaMerkitseviaBitteja;
            }
            kasitteleYksiTavu(merkitseviaBitteja, kasiteltavaTavu, koodi, koodit, kirjoittaja);

            if (viimeinenTavu) {
                break;
            }

            // luetaan yksi tavu lisää
            if (lukija.lue(luku) == -1) { // viimeinen tavu
                viimeinenTavu = true;
            } else { // siirretään lukupuskurissa tavuja
                puskuri[1] = luku[0];
            }

        }
    }
    /**
     * Käsitellään yksi tavullinen dataa - puretaan koodeja koodi-objektiin kunnes koodit-taulusta löytyy vastaavuus -> kirjoitetaan tiedostoon ja jatketaan alusta
     * @param merkitseviaBitteja montako bittiä on merkitseviä käsiteltävässä tavussa
     * @param kasiteltavaTavu tiedostosta luettu tavu, puretaan tätä bitti kerrallaan
     * @param koodi Koodi-objekti jota rakennetaan kunnes löytyy koodit-taulusta vastaavuus
     * @param koodit Koodi - blokki-objektit
     * @param kirjoittaja Tiedosto johonka kirjoitetaan 
     * @throws IOException Jos kirjoitus epäonnistuu
     */
    private void kasitteleYksiTavu(int merkitseviaBitteja, byte kasiteltavaTavu, Koodi koodi, OmaMap<Koodi, byte[]> koodit, TiedostoKirjoittaja kirjoittaja) throws IOException {
        for (int j = 0; j < merkitseviaBitteja; ++j) {
            int luettuArvo = BittiUtility.haeBitinArvoPaikasta(kasiteltavaTavu, j);
            koodi.koodi = BittiUtility.tallennaBitinArvoPaikalle(koodi, luettuArvo, koodi.pituus);

            koodi.pituus++; 
            byte[] array = koodit.get(koodi); // katsotaan onko tälle avaimelle vastaavaa arvoa
            if (array != null) { // jos on, tallennetaan tiedostoon & aloitetaan alusta

                kirjoittaja.kirjoita(array);

                koodi.koodi = 0;
                koodi.pituus = 0;
            }


        }
    }
}
