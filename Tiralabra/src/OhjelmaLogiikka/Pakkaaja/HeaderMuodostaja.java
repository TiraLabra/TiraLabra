package OhjelmaLogiikka.Pakkaaja;

import Tiedostokasittely.ITiedostoKirjoittaja;
import Tietorakenteet.HuffmanKoodi;
import Tietorakenteet.OmaList;
import Tietorakenteet.Pari;
import Tietorakenteet.TiedostoBlokki;
import java.io.IOException;

/**
 * Luokka joka vastaa header-tiedoston kirjoittamisesta.
 *
 */
public class HeaderMuodostaja {

    private final int OFFSET = 128;
    private int blokinPituus;

    /**
     * Luo header-tiedoston annettujen parametrien perusteella.
     * Tiedostoformaatista enemmän tiedostoformaatti.txt-tiedostossa.
     *
     * @param headerKirjoittaja ITiedostoKirjoittaja-rajapinnan toteuttava
     * objekti
     * @param koodit Lista blokki - koodipareista
     * @param viimeisessaTavussaMerkitseviaBitteja montako merkitsevää bittiä
     * pakatun tiedoston viimeisessä tavussa on
     * @param blokinPituus - pakattaessa käyteyn blokin pituus
     * @return Headerin koko tavuissa
     * @throws IOException jos kirjoitus\lukuoperaatoissa vikaa
     * @throws IllegalArgumentException jos merkitsevien bittien määrä on alle 1
     * tai suurempi kuin 8; tai jos blokin pituus yli 255 tai alle 0 tai jos
     * listassa TiedostoBlokin koko yli 255 tai jos koodin pituus alle 1 tai
     * suurempi kuin 64
     */
    public long muodostaHeader(ITiedostoKirjoittaja headerKirjoittaja, OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> koodit, int viimeisessaTavussaMerkitseviaBitteja, int blokinPituus) throws IllegalArgumentException, IOException {
        headerKirjoittaja.avaaTiedosto();
        this.blokinPituus = blokinPituus;
        alustaHeader(viimeisessaTavussaMerkitseviaBitteja, headerKirjoittaja);

        for (int i = 0; i < koodit.size(); ++i) {
            tallennaBlokinTiedot(koodit.get(i).ensimmainen, koodit.get(i).toinen, headerKirjoittaja);
        }

        headerKirjoittaja.suljeTiedosto();
        return headerKirjoittaja.koko();
    }

    /**
     * Kirjoittaa headerin alkuun tietoja ennen varsinaisten huffman-koodien
     * tallennusta
     *
     * @param viimeisessaTavussaMerkitseviaBitteja Montako bittiä on merkitseviä
     * tallennetun tiedoston viimeisessä tavussa
     * @param kirjoittaja TiedostoKirjoittaja-objekti joka vastaa
     * tiedostoonkirjoituksesta
     * @throws IllegalArgumentException Jos Viimeisessä tavussa on alle 1 tai
     * yli 8 merkitsevää bittiä. Alle 1 tarkoittaa että viimeinen tavu ei olisi
     * merkitsevä ollenkaan ja tavussa on max. 8 bittiä. Jos blokin pituus yli
     * 255 tai alle 1 myös aiheuttaa tämän poikkeuksen
     * @throws IOException Jos tiedostoonkirjoituksessa meni jotakin vikaan
     */
    private void alustaHeader(Integer viimeisessaTavussaMerkitseviaBitteja, ITiedostoKirjoittaja kirjoittaja) throws IllegalArgumentException, IOException {
        if (viimeisessaTavussaMerkitseviaBitteja > 8 || viimeisessaTavussaMerkitseviaBitteja < 1) {
            throw new IllegalArgumentException("Tavussa oltava vähintään yksi ja korkeintaan 8 merkitsevää bittiä - annettu arvo: " + viimeisessaTavussaMerkitseviaBitteja);
        }

        if (blokinPituus > 255 || blokinPituus < 1) {
            throw new IllegalArgumentException("Blokin pituus virheellinen: " + blokinPituus);
        }

        byte[] puskuri = new byte[1];
        puskuri[0] = (byte) (viimeisessaTavussaMerkitseviaBitteja - OFFSET);
        kirjoittaja.kirjoita(puskuri);

        puskuri[0] = (byte) (blokinPituus - OFFSET);
        kirjoittaja.kirjoita(puskuri);
    }

    /**
     * Tallentaa headeriin blokin tiedot. Katso dokumentaatiosta
     * tiedostoformaatti.txt formaatin selventämiseksi
     *
     * @param blokki headeriin tallennettava blokki
     * @param koodi blokkia vastaava koodi - tarvitaan vain tämän pituus koska
     * itse koodi voidaan rakentaa paikkatiedon avulla purkuvaiheessa
     * @param kirjoittaja TiedostoKirjoittaja-objekti joka vastaa
     * tiedostoonkirjoituksesta
     * @throws IllegalArgumentException jos blokin koko on yli 255 (ei mahdu
     * yhteen tavuun tieto) tai jos koodin pituus on yli 64 (purkuvaiheessa ei
     * mahdu long-muuttujaan)
     * @throws IOException Jos kirjoituksessa menee jotakin vikaan
     */
    private void tallennaBlokinTiedot(TiedostoBlokki blokki, HuffmanKoodi koodi, ITiedostoKirjoittaja kirjoittaja) throws IllegalArgumentException, IOException {
        if (blokki.size() > 255) {
            throw new IllegalArgumentException("Annetun blokin pituus on suurempi kuin header-formaattiin on varattu tilaa: Pituus: " + blokki.size());
        }
        byte[] puskuri = new byte[1];
        // jos bittiblokin pituus eroaa blokkipituudesta, kirjoitetaan erikoisblokki
        
        if (blokki.size() != blokinPituus) {
            kirjoitaErikoisBlokki(puskuri, kirjoittaja, blokki);
        }

        //   max 64 bittiä koska bittien on mahduttava long-muuttujaan purkuvaiheessa
        if (koodi.pituus > 64 || koodi.pituus <= 0) {
            throw new IllegalArgumentException("Koodin pituus on virheellinen: " + koodi.pituus);
        }

        puskuri[0] = (byte) (koodi.pituus - OFFSET);

        kirjoittaja.kirjoita(puskuri);
        kirjoittaja.kirjoita(blokki.byteTaulukko);

    }

    private void kirjoitaErikoisBlokki(byte[] puskuri, ITiedostoKirjoittaja kirjoittaja, TiedostoBlokki blokki) throws IOException {
        // merkitään tämä 0:llä että purkuvaiheessa tiedetään että on poikkeava blokki (normaalisti arvo välillä 1 - 64)
        puskuri[0] = (byte) (0 - OFFSET);
        kirjoittaja.kirjoita(puskuri);

        // tallennettavan bittiblokin pituus tavuissa
        puskuri[0] = (byte) (blokki.size() - OFFSET);
        kirjoittaja.kirjoita(puskuri);
    }
}
