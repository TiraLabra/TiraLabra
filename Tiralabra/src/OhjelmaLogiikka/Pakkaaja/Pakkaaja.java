package OhjelmaLogiikka.Pakkaaja;

import Tiedostokasittely.ITiedostoLukija;
import Tiedostokasittely.TiedostoKirjoittaja;
import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.HuffmanKoodi;
import Tietorakenteet.OmaMap;
import Tietorakenteet.TiedostoBlokki;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Pakkaaja-luokka. Pakkaa annetun tiedoston huffman-koodien avulla.
 *
 */
public class Pakkaaja {

    private final int BLOKIN_KOKO;
    private double pakkausSuhde;

    /**
     * Konstruktori. Ottaa parametrina tiedostosta luettavien blokkien koon
     * tavuina.
     *
     * @param blokinKoko Blokkien koko tavuina.
     */
    public Pakkaaja(int blokinKoko) {
        BLOKIN_KOKO = blokinKoko;
    }

    /**
     * Miten tiiviiksi tiedosto saatiin pakattua. 1 = tiedosto tiivistyi
     * olemattomaksi, 0 = tiedosto ei tiivistynyt ollenkaan, alle 0 -
     * tiedostokoko kasvoi
     *
     * @return miten tiiviisti tiedosto pakkautui
     */
    public double haePakkausSuhde() {
        return pakkausSuhde;
    }

    /**
     * Pakkaa annetun tiedoston. Luo kaksi tiedostoa, ulos-stringin nimeämä
     * tiedosto jossa data pakattuna + ulos + ".header" missä huffman-puu
     * tallennettuna
     *
     * @param sisaan Tiedosto joka halutaan pakata
     * @param ulos Tiedosto johonka tallennetaan pakattu tieto
     * @throws FileNotFoundException Jos annettua tiedostoa ei löytynyt
     * @throws IOException Jos tapahtuu io-virhe
     */
    public void pakkaa(String sisaan, String ulos) throws FileNotFoundException, IOException {

        long aika = System.nanoTime();
        System.out.println("Aloitetaan pakkaaminen");


        ITiedostoLukija lukija = new TiedostoLukija(sisaan);
        long sisaanTiedostonKoko = lukija.koko();
        System.out.println("Pakattavan tiedoston koko " + (double) sisaanTiedostonKoko / 1024 / 1024 + " megatavua (" + (double) sisaanTiedostonKoko / 1024 + " kilotavua)");

        KoodiMuodostaja koodiMuodostaja = new KoodiMuodostaja(BLOKIN_KOKO);
        OmaMap<TiedostoBlokki, HuffmanKoodi> koodit = koodiMuodostaja.muodostaKoodit(lukija);

        Tiivistaja tiivistaja = new Tiivistaja(BLOKIN_KOKO);
        int bittejaKaytetty = tiivistaja.tiivista(new TiedostoLukija(sisaan), new TiedostoKirjoittaja(ulos), koodit);

        long ulosTiedostonKoko = tiivistaja.haeTiedostonKoko();

        ulosTiedostonKoko += (new HeaderMuodostaja()).muodostaHeader(new TiedostoKirjoittaja(ulos + ".header"), koodiMuodostaja.haeKooditJarjestettyna(), bittejaKaytetty, BLOKIN_KOKO);
        tulostaStatsit(aika, sisaanTiedostonKoko, ulosTiedostonKoko);
    }

    /**
     * Tulostaa statseja
     *
     * @param aika aika nanosekunneissa pakkauksen aloittamisesta
     * @param sisaanTiedostonKoko Pakattavan tiedoston koko tavuissa
     * @param ulosTiedostonKoko Pakattujen tiedostojen koko tavuissa (tiedosto +
     * header)
     */
    private void tulostaStatsit(long aika, long sisaanTiedostonKoko, long ulosTiedostonKoko) {
        aika = (System.nanoTime() - aika);
        System.out.println("Pakkaamiseen kului " + aika / 1000000 + " ms");
        System.out.println("Käsiteltiin " + ((double) sisaanTiedostonKoko / 1024 / 1024 / ((double) aika / 1000000000)) + " megatavua/sekunti");
        System.out.println("Pakatun tiedoston koko: " + (double) ulosTiedostonKoko / 1024 / 1024 + " megatavua " + "(" + (double) ulosTiedostonKoko / 1024 + "kilotavua)");
        pakkausSuhde = 1.0 - (double) ulosTiedostonKoko / (double) sisaanTiedostonKoko;
        System.out.println("Tiivistysprosentti: " + (pakkausSuhde) * 100.0 + "%");
        System.out.print("\n\n");
    }
}
