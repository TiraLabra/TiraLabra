

package OhjelmaLogiikka.Purkaja;

import OhjelmaLogiikka.BittiUtility;
import Tiedostokasittely.ITiedostoKirjoittaja;
import Tiedostokasittely.ITiedostoLukija;
import Tietorakenteet.Koodi;
import Tietorakenteet.OmaMap;
import java.io.IOException;

/**
 * Varsinainen purkukoodi tässä luokassa; helpottaa yksikkötestausta
 * 
 */
public class PurkuKoodi {


    /**
     * Purkaa annetun tiedoston annettuun tiedostoon huffman-koodien perusteella
     *
     * @param lukija lähdetiedosto
     * @param kirjoittaja kohdetiedosto
     * @param viimeisessaTavussaMerkitseviaBitteja Montako bittiä on merkitseviä
     * viimeisessä tavussa
     * @param koodit Koodi-blokkiparit
     * @throws IOException Jos lukeminen\kirjoitus epäonnistuu
     * @throws IllegalArgumentException jos viimeisessaTavussaMerkitseviaBitteja alle 1 tai yli 8
     */
    public void kasitteleTiedosto(ITiedostoLukija lukija, ITiedostoKirjoittaja kirjoittaja, int viimeisessaTavussaMerkitseviaBitteja, OmaMap<Koodi, byte[]> koodit) throws IOException {
        
        if (viimeisessaTavussaMerkitseviaBitteja < 1 || viimeisessaTavussaMerkitseviaBitteja > 8) {
            throw new IllegalArgumentException("Virheellinen bittimäärä viimeiselle tavulle: " + viimeisessaTavussaMerkitseviaBitteja);
        }
        
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
    private void kasitteleYksiTavu(int merkitseviaBitteja, byte kasiteltavaTavu, Koodi koodi, OmaMap<Koodi, byte[]> koodit, ITiedostoKirjoittaja kirjoittaja) throws IOException {
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
