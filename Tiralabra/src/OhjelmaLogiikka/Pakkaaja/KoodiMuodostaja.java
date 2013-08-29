package OhjelmaLogiikka.Pakkaaja;

import OhjelmaLogiikka.BittiUtility;
import Tiedostokasittely.ITiedostoLukija;
import Tietorakenteet.HuffmanKoodi;
import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import Tietorakenteet.OmaMinimiPriorityQueue;
import Tietorakenteet.OmaQueue;
import Tietorakenteet.OmaTreeNode;
import Tietorakenteet.Pari;
import Tietorakenteet.TiedostoBlokki;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;

/**
 * Luokka joka muodostaa huffman-koodit annetusta tiedostosta
 */
public class KoodiMuodostaja {

    private final int BLOKIN_KOKO;
    private Kanonisoija kanonisoija;

    /**
     * Palauttaa listan kanonisista koodeista järjestettynä koodin pituuksien
     * mukaan. Headerin kirjoitus tarvitsee tätä
     *
     * @return Lista kanonisoiduista koodeista järjestettynä pituuden mukaan.
     */
    public OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> haeKooditJarjestettyna() {
        return kanonisoija.haeKooditJarjestettyna();
    }

    /**
     * Konstruktori. Saa parametrikseen blokin koon. Blokin koko = montako tavua
     * luetaan tiedostosta kerralla. Blokeista luodaan huffman-koodit
     *
     * @param blokinKoko Blokin koko tavuissa
     */
    public KoodiMuodostaja(int blokinKoko) {
        BLOKIN_KOKO = blokinKoko;
    }

    /**
     * Muodostaa koodit annetusta tiedostosta. Tämä metodi avaa tiedoston joten
     * tiedoston tulee olla suljettuna kun se välitetään metodille.
     *
     * @param lukuTiedosto ITiedostoLukija-rajapinnan toteuttava objekti
     * @return Taulu joka sisältää Blokki-koodiparit. Koodit ovat kanoniset.
     * @throws FileNotFoundException Jos annettua tiedostoa ei löydy
     * @throws IOException Jos jotakin menee pieleen lukuvaiheessa
     */
    public OmaMap<TiedostoBlokki, HuffmanKoodi> muodostaKoodit(ITiedostoLukija lukuTiedosto) throws FileNotFoundException, IOException {

        // lasketaan blokkien esiintymistiheydet
        OmaMap<TiedostoBlokki, Integer> esiintymisTiheydet = (new EsiintymisTiheysLaskija()).laskeEsiintymisTiheydet(lukuTiedosto, BLOKIN_KOKO);
          
        /**
         * Muodostetaan huffman-koodit
         */
        OmaMap<Integer, OmaList<Pari<TiedostoBlokki, HuffmanKoodi>>> koodiLista = (new HuffmanKoodiMuodostaja()).muodostaHuffmanKoodit(esiintymisTiheydet);
        // kanonisoidaan koodit jotta header-tiedosto olisi pienempi
        kanonisoija = new Kanonisoija();
        return kanonisoija.kanonisoi(koodiLista);
    }

}
