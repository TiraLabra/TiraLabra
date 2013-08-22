package OhjelmaLogiikka.Pakkaaja;

import OhjelmaLogiikka.BittiUtility;
import Tiedostokasittely.ITiedostoKirjoittaja;
import Tiedostokasittely.ITiedostoLukija;
import Tietorakenteet.TiedostoBlokki;
import Tietorakenteet.HuffmanKoodi;
import Tietorakenteet.OmaMap;
import java.io.IOException;

/**
 * Luokka joka vastaa itse tiedoston tiivistyksestä
 *
 */
public class Tiivistaja {

    private final int BLOKIN_KOKO;
    private long ulosTiedostonKoko;

    /**
     * Konstruktori. Ottaa parametrina blokin koon
     *
     * @param blokinKoko Blokin koko
     */
    public Tiivistaja(int blokinKoko) {
        BLOKIN_KOKO = blokinKoko;
    }

    /**
     * Kertoo kuinka suuri tiivistetystä tiedostosta tuli. Ei sisällä headeria
     *
     * @return Tiivistetyn tiedoston koko
     */
    public long haeTiedostonKoko() {
        return ulosTiedostonKoko;
    }

    /**
     * Tiivistää annetun tiedoston ja kirjoittaa lopputuloksen toiseen annettuun
     * tiedostoon.
     *
     * @param lukija Lähdetiedosto
     * @param kirjoittaja Kohdetiedosto
     * @param koodit Taulu joka sisältää blokki-koodi-parit
     * @return montako bittiä on merkitseviä viimeisessä tavussa
     * @throws IOException Jos jotakin menee vikaan lukemisessa tai
     * kirjotuksessa
     * @throws NullPointerException Jos blokille ei löytynyt sopivaa koodia.
     * kirjoituksessa
     */
    public int tiivista(ITiedostoLukija lukija, ITiedostoKirjoittaja kirjoittaja, OmaMap<TiedostoBlokki, HuffmanKoodi> koodit) throws IOException, NullPointerException {
        int merkitseviaBittejaViimeisessaTavussa;
        try {
            lukija.avaaTiedosto();
            kirjoittaja.avaaTiedosto();

            merkitseviaBittejaViimeisessaTavussa = kirjoitaKoodit(lukija, kirjoittaja, koodit);


        } finally {
            lukija.suljeTiedosto();
            kirjoittaja.suljeTiedosto();
            ulosTiedostonKoko = kirjoittaja.koko();
        }

        return merkitseviaBittejaViimeisessaTavussa;
    }

    /**
     * Lukee lähdetiedostosta blokit ja kirjoittaa kohdetiedostoon
     * huffman-koodit
     *
     * @param lukija lähdetiedosto
     * @param kirjoittaja kohdetiedosto
     * @param koodit Taulu joka sisältää blokki-koodi-parit
     * @return montako bittiä on merkitseviä viimeisessa tavussa
     * @throws IOException Jos luku\kirjoitusoperaatioissa menee jotakin pieleen
     * @throws NullPointerException Jos blokille ei löytynyt sopivaa koodia.
     */
    private int kirjoitaKoodit(ITiedostoLukija lukija, ITiedostoKirjoittaja kirjoittaja, OmaMap<TiedostoBlokki, HuffmanKoodi> koodit) throws IOException, NullPointerException {
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

    /**
     * Käsittelee viimeisen tavun jonka on voinut jäädä kesken jos se ei ole
     * tasan 8 bittiä pitkä
     *
     * @param bittejaKaytetty Montako bittiä on käytetty tavusta tähän mennessä
     * @param kirjoittaja kohdetiedosto
     * @param puskuri yhden tavun taulukko jossa käsiteltävät bitit majailevat
     * @return montako tavua on merkitseviä viimeisessä
     * @throws IOException jos kirjoitusoperaatio epäonnistuu
     *
     */
    private int hoidaViimeinenTavu(int bittejaKaytetty, ITiedostoKirjoittaja kirjoittaja, byte[] puskuri) throws IOException {
        if (bittejaKaytetty != 0) { // viimeisen tavun käsittely on kesken - tallennetaan tiedostoon
            kirjoittaja.kirjoita(puskuri);
        } else {
            bittejaKaytetty = 8; // viimeisessä tavussa on kaikki tavut merkitseviä jos koko tavu on kirjoitettu
        }
        return bittejaKaytetty;
    }

    /**
     * Käsittelee luetun blokin ja tallentaa tiedostoon. Jos viimeinen luettu
     * blokki on pienempi kuin blokin koko, jää sen käsittely kesken
     *
     * @param luettu monta tavua luettu
     * @param luetutTavut tavut jotka luettu
     * @param koodit Taulu joka sisältää blokki-koodi-parit
     * @param puskuri Yhden tavun puskuri johonka huffman-koodeja tallennetaan.
     * Kun 8 bittiä käsitelty, tallennetaan tiedostoon
     * @param bittejaKaytetty Monta bittiä on käytetty tähän mennessä puskurista
     * @param kirjoittaja Kohdetiedosto
     * @return Monta bittiä on käytetty puskurista
     * @throws IOException Jos kirjoitusoperaatio epäonnistuu
     * @throws NullPointerException Jos blokille ei löytynyt sopivaa koodia.
     */
    private int kasitteleBlokki(int luettu, byte[] luetutTavut, OmaMap<TiedostoBlokki, HuffmanKoodi> koodit, byte[] puskuri, int bittejaKaytetty, ITiedostoKirjoittaja kirjoittaja) throws IOException, NullPointerException {
        TiedostoBlokki blokki = new TiedostoBlokki();
        blokki.byteTaulukko = new byte[luettu];
        // koska Tiedstoblokkia käytetään avaimena, kopioidaan tavut sinne
        // Myöskin kopioitava koska saatetaan lukea vähemmän kuin blokilla on kokoa (viimeinen lukukerta tiedostosta, voi lukea mitä tahansa väliltä 1 - BLOKIN_KOKO -tavua
        for (int i = 0; i < luettu; ++i) {
            blokki.byteTaulukko[i] = luetutTavut[i];
        }
        // käytetään ByteWrapper-objektia hashmapin avaimena ja haetaan sitä vastaava huffman-pakkauskoodi
        HuffmanKoodi pakkausKoodi = koodit.get(blokki);
        if (pakkausKoodi == null) {
            throw new NullPointerException("Blokille ei löytynyt sopivaa koodia!");
        }

        bittejaKaytetty = kopioiTavuunPakkausKoodienBittejaJaKirjoita(pakkausKoodi, puskuri, bittejaKaytetty, kirjoittaja);
        return bittejaKaytetty;
    }

    /**
     * Kopioi tavupuskuriin nykyisen koodin bitit ja tarvittaessa kirjoittaa
     * tiedostoon jos kaikki bitit käytetty
     *
     * @param pakkausKoodi huffman-koodi
     * @param puskuri tavupuskuri
     * @param bittejaKaytetty monta bittiä käytetty puskurista
     * @param kirjoittaja Kohdetiedosto
     * @return monta bittiä käytetty
     * @throws IOException Jos kirjoitus epäonnistuu
     */
    private int kopioiTavuunPakkausKoodienBittejaJaKirjoita(HuffmanKoodi pakkausKoodi, byte[] puskuri, int bittejaKaytetty, ITiedostoKirjoittaja kirjoittaja) throws IOException {
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
