package OhjelmaLogiikka.Purkaja;

import OhjelmaLogiikka.BittiUtility;
import Poikkeukset.PurkuException;
import Tiedostokasittely.ITiedostoKirjoittaja;
import Tiedostokasittely.ITiedostoLukija;
import Tietorakenteet.HuffmanKoodi;
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
     * @throws IllegalArgumentException jos viimeisessaTavussaMerkitseviaBitteja
     * alle 1 tai yli 8
     * @throws PurkuException Jos sopivaa koodia ei löydy purettaessa tai jää käsittelemätöntä dataa
     */
    public void kasitteleTiedosto(ITiedostoLukija lukija, ITiedostoKirjoittaja kirjoittaja, int viimeisessaTavussaMerkitseviaBitteja, OmaMap<HuffmanKoodi, byte[]> koodit) throws IOException, PurkuException {
        tarkistaArgumentti(viimeisessaTavussaMerkitseviaBitteja);

        byte[] puskuri = new byte[2];
        // luetaan kaksi tavua tietoa - oletus: pakatussa tiedostossa ainakin kaksi tavua!
        // pidetään kahden tavun puskuria koska on ennakolta tiedettävä milloin on luettu viimeinen tavu
        // viimeisen tavun käsittely poikkeaa koska siinä voi olla täytebittejä
        // jos luettaisiin tavu kerrallaan, tieto siitä että ollaan tiedoston lopussa tulisi liian myöhään
        lukija.lue(puskuri);
        
        
        boolean viimeinenTavu = false;
        HuffmanKoodi koodi = new HuffmanKoodi();

        while (true) {
            // kahden tavun puskuri - luetaan aina luupin lopussa yksi tavu lisää - jos epäonnistuu niin vielä yksi kierros viimeisen tavun käsittelyyn ja sitten ulos luupista.
            // Käsitellään aina edellistä lukukertaa edeltävän lukukerran tavu. Kun saavutetaan loppu, on vielä yksi käsiteltävä tavu puskurissa.
            byte kasiteltavaTavu = puskuri[0];
            puskuri[0] = puskuri[1];

            int merkitseviaBitteja = asetaMerkitsevienBittienMaara(viimeinenTavu, viimeisessaTavussaMerkitseviaBitteja);
            kasitteleYksiTavu(merkitseviaBitteja, kasiteltavaTavu, koodi, koodit, kirjoittaja);

            // jos käsittelyssä viimeinen tavu, voidaan lopettaa nyt
            if (viimeinenTavu) {
                break;
            }

            viimeinenTavu = lueTavuja(lukija, puskuri);
        }
        
        if (koodi.pituus != 0 || koodi.koodi != 0) {
            throw new PurkuException("Käsittelemätön koodiblokki purettaessa - korruptoitunut tiedosto");
        }
    }

    /**
     * Käsitellään yksi tavullinen dataa - puretaan koodeja koodi-objektiin
     * kunnes koodit-taulusta löytyy vastaavuus -> kirjoitetaan tiedostoon ja
     * jatketaan alusta
     *
     * @param merkitseviaBitteja montako bittiä on merkitseviä käsiteltävässä
     * tavussa
     * @param kasiteltavaTavu tiedostosta luettu tavu, puretaan tätä bitti
     * kerrallaan
     * @param koodi Koodi-objekti jota rakennetaan kunnes löytyy koodit-taulusta
     * vastaavuus
     * @param koodit Koodi - blokki-objektit
     * @param kirjoittaja Tiedosto johonka kirjoitetaan
     * @throws IOException Jos kirjoitus epäonnistuu
     * @throws PurkuException Jos sopivaa koodia ei löydy purettaessa
     */
    private void kasitteleYksiTavu(int merkitseviaBitteja, byte kasiteltavaTavu, HuffmanKoodi koodi, OmaMap<HuffmanKoodi, byte[]> koodit, ITiedostoKirjoittaja kirjoittaja) throws IOException, PurkuException {
        for (int j = 0; j < merkitseviaBitteja; ++j) {
            int luettuArvo = BittiUtility.haeBitinArvoPaikasta(kasiteltavaTavu, j);
            koodi.koodi = BittiUtility.tallennaBitinArvoPaikalle(koodi, luettuArvo, koodi.pituus);

            koodi.pituus++;
            if (koodi.pituus > 64) {
                throw new PurkuException("Koodin pituus liian pitkä purettaessa - tiedostovirhe");
            }
            
            byte[] blokki = koodit.get(koodi); // katsotaan onko tälle avaimelle vastaavaa arvoa
            if (blokki != null) { // jos on, tallennetaan tiedostoon & aloitetaan alusta

                kirjoittaja.kirjoita(blokki);

                koodi.koodi = 0;
                koodi.pituus = 0;
            }
        }
    }

    /**
     * Tarkistaa argumentin oikeellisuuden. Apumetodi. Pilkotaan alkuperäistä
     * metodia pienemmäksi.
     *
     * @param viimeisessaTavussaMerkitseviaBitteja Tarkistettava arvo
     * @throws IllegalArgumentException Jos arvo pienempi kuin 1 tai suurempi
     * kuin 8.
     */
    private void tarkistaArgumentti(int viimeisessaTavussaMerkitseviaBitteja) throws IllegalArgumentException {
        if (viimeisessaTavussaMerkitseviaBitteja < 1 || viimeisessaTavussaMerkitseviaBitteja > 8) {
            throw new IllegalArgumentException("Virheellinen bittimäärä viimeiselle tavulle: " + viimeisessaTavussaMerkitseviaBitteja);
        }
    }

    /**
     * Lukee yhden tavun tiedostosta ja asettaa sen lukupuskuriin. Palauttaa
     * true jos tiedosto on luettu loppuun ja edellinen luettu tavu on
     * viimeinen.
     *
     * @param lukija Tiedosto josta luetaan
     * @param puskuri Puskuri johonka tallennetaan luettu tavu.
     * @return Onko viimeinen tavu kyseessä.
     * @throws IOException Jos lukeminen epäonnistuu.
     */
    private boolean lueTavuja(ITiedostoLukija lukija, byte[] puskuri) throws IOException {
        byte[] luku = new byte[1];
        boolean viimeinenTavu = false;
        // luetaan yksi tavu lisää
        if (lukija.lue(luku) == -1) { // viimeinen tavu
            viimeinenTavu = true;
        } else { // siirretään lukupuskurissa tavuja
            puskuri[1] = luku[0];
        }
        return viimeinenTavu;
    }

    /**
     * Asettaa montako bittiä on merkitseviä käsiteltävässä tavussa. Normaalisti
     * 8 merkitsevää bittiä (eli kaikki). Poikkeuksena se jos on viimeinen
     * luettava tavu kyseessä jolloin voi olla alle 8 merkitsevää bittiä.
     *
     * @param viimeinenTavu Onko kyseessä viimeinen tavu
     * @param viimeisessaTavussaMerkitseviaBitteja Montako bittiä on merkitseviä
     * viimeisessä tavussa
     * @return Montako bittiä on merkitseviä käsiteltävässä tavussa.
     */
    private int asetaMerkitsevienBittienMaara(boolean viimeinenTavu, int viimeisessaTavussaMerkitseviaBitteja) {
        int merkitseviaBitteja = 8;
        if (viimeinenTavu) { // jos viimeinen tavu, otetaan huomioon että ei välttämättä ole kahdeksaa merkitsevää bittiä
            merkitseviaBitteja = viimeisessaTavussaMerkitseviaBitteja;
        }
        return merkitseviaBitteja;
    }
}
