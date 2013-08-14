package OhjelmaLogiikka.Purkaja;

import OhjelmaLogiikka.KanonisoidunKoodinMuodostaja;
import OhjelmaLogiikka.BittiUtility;
import Tiedostokasittely.ITiedostoLukija;
import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.Koodi;
import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaMap;
import Tietorakenteet.Pari;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Luokka jonka vastuulla on muodostaa annetusta headerista huffman-koodit
 *
 */
public class HeaderLukija {

    private final int OFFSET = 128;
    private int blokinPituus;

    /**
     * Lukee annetusta tiedostosta headerin tiedot. Headerin formaatin tiedot
     * löytyy tiedostoformaatti.txt-tiedostosta dokumentaatiohakemistosta.
     *
     * @param headerLukija ITiedostoLukijan toteuttava objekti. Tästä luetaan
     * tarvittavat tiedot
     * @return Parin joka sisältää Integerin joka kertoo montako bittiä on
     * merkitseviä viimeisessä tavussa ja Mapin joka sisältää koodi -
     * blokki-parit.
     * @throws FileNotFoundException jos annettua tiedostoa ei löydy
     * @throws IOException Jos tapahtuu IO-virhe
     */
    public Pari<Integer, OmaMap<Koodi, byte[]>> lueHeader(ITiedostoLukija headerLukija) throws FileNotFoundException, IOException {

        headerLukija.avaaTiedosto();

        Pari<Integer, OmaMap<Koodi, byte[]>> paluu = new Pari<Integer, OmaMap<Koodi, byte[]>>();
        lueHeaderinAlku(headerLukija, paluu);

        paluu.toinen = lueKoodiBlokkiParit(headerLukija);
        headerLukija.suljeTiedosto();

        return paluu;
    }
    /**
     * Lukee headerin alusta viimeisen tavun merkitsevien bittien määrän ja headerin blokkikoon
     * @param headerLukija header
     * @param paluu pari joka sisältää muuttujan johonka merkitsevien bittien määrä tallennetaan
     * @throws IOException Jos lukemisessa tapahtuu virhe
     */
    private void lueHeaderinAlku(ITiedostoLukija headerLukija, Pari<Integer, OmaMap<Koodi, byte[]>> paluu) throws IOException {
        byte[] headerAlku = new byte[2];
        if (headerLukija.lue(headerAlku) != 2) {
            throw new IOException("Header-tiedoston korruptoitunut - ensimmäisen kahden tavun luku epäonnistui");
        }

        paluu.ensimmainen = headerAlku[0] + OFFSET;
        blokinPituus = headerAlku[1] + OFFSET;
    }

    /**
     * Lukee headerista tarvittavat tiedot koodi-blokki-parin muodostamiseen
     * @param headerLukija header
     * @return Taulu jossa on koodi - blokkiparit.
     * @throws IOException 
     */    
    private OmaMap<Koodi, byte[]> lueKoodiBlokkiParit(ITiedostoLukija headerLukija) throws IOException {
        OmaMap<Koodi, byte[]> koodit = new OmaHashMap<Koodi, byte[]>();
        // generoidut koodit riippuvat edellisistä koodeista, luotava täällä jotta hengissä koko lukutapahtuman ajan
        KanonisoidunKoodinMuodostaja muodostaja = new KanonisoidunKoodinMuodostaja(); 
        while (lueYksiKoodiBlokkiPari(headerLukija, koodit, muodostaja)) {
        }

        return koodit;
    }
    /**
     * Lukee yhden blokki-koodiparin tiedot ja lisää ne tauluun
     * @param headerLukija header
     * @param koodit taulu johonko koodi-blokkiparit tallennetaan
     * @param muodostaja KanonisoidunKoodinMuodostaja-objekti joka muodostaa koodin joita tarvitaan purkuvaiheessa
     * @return onko tiedosto luettu loppuun.
     * @throws IOException 
     */
    private boolean lueYksiKoodiBlokkiPari(ITiedostoLukija headerLukija, OmaMap<Koodi, byte[]> koodit, KanonisoidunKoodinMuodostaja muodostaja) throws IOException {

        byte[] lukuPuskuri = new byte[1];
        
        int pituus = blokinPituus;
        
        if (headerLukija.lue(lukuPuskuri) == -1) {
            return false; // tiedoston loppu
        }
        int koodinPituusBiteissa = lukuPuskuri[0] + OFFSET;
                
        // poikkeava blokki - onkin tallennettu blokin pituus koska se on poikkeava (viimeisen tiedostosta luetun blokin pituus ei välttämättä ole sama kuin ilmoitettu blokkipituus)
        if (koodinPituusBiteissa == 0) {
            lueTavu(headerLukija, lukuPuskuri);

            pituus = lukuPuskuri[0] + OFFSET;
            lueTavu(headerLukija, lukuPuskuri);

            koodinPituusBiteissa = lukuPuskuri[0] + OFFSET;
        }

        if (koodinPituusBiteissa < 1 || koodinPituusBiteissa > 64) {
            throw new IOException("Koodin koko virheellinen header-tiedostossa: " + koodinPituusBiteissa);
        }


        byte[] blokki = lueBlokki(headerLukija, pituus);
        Koodi koodi = new Koodi();

        koodi.koodi = muodostaja.muodostaKoodi(koodinPituusBiteissa);
        koodi.pituus = koodinPituusBiteissa;

        koodit.put(koodi, blokki);
        return true;
    }
    /**
     * Lukee yhden blokin tiedot tiedostosta ja palauttaa sen
     * @param headerLukija header
     * @param pituus blokin pituus
     * @return blokin tavut
     * @throws IOException jos lukeminen epäonnistuu tai headerin tila ei ole oletettu (annettu blokin pituus on pidempi kuin tiedostossa on tavuja)
     */
    private byte[] lueBlokki(ITiedostoLukija headerLukija, int pituus) throws IOException {

        byte[] puskuri = new byte[pituus];

        if ( headerLukija.lue(puskuri) != pituus) {
            throw new IOException("Header-tiedoston korruptoitunut - blokin lukeminen epäonnistui");
        }
        return puskuri;
    }
    /**
     * Lukee yhden tavun tiedostosta. Apumetodi
     * @param headerLukija header
     * @param lukuPuskuri puskuri johonka tavu tallennetaan
     * @throws IOException Jos lukeminen epäonnistuu tai jos headerin tila ei ole oletettu
     */
    private void lueTavu(ITiedostoLukija headerLukija, byte[] lukuPuskuri) throws IOException {
        if (headerLukija.lue(lukuPuskuri) == 0) {
            throw new IOException("Header-tiedoston korruptoitunut - tavun lukeminen epäonnistui");
        }
    }
}
