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
        OmaMap<TiedostoBlokki, Integer> esiintymisTiheydet = laskeEsiintymisTiheydet(lukuTiedosto);
        // prioriteettijono esiintymistiheyksien pohjalta
        OmaQueue<OmaTreeNode<Integer, TiedostoBlokki>> jono = muodostaPrioriteettiJono(esiintymisTiheydet);
        // rakennetaan puu prioriteettijonon avulla
        OmaTreeNode<Integer, TiedostoBlokki> puu = muodostaHuffmanPuu(jono);
        // lasketaan koodit puusta ja tehdään lista blokki-koodi-pareista 
        // avain on koodin pituus, arvo lista koodeista joilla on avaimen pituus
        // nopeuttaa kanonisoidun koodin lukemista
        OmaMap<Integer, OmaList<Pari<TiedostoBlokki, HuffmanKoodi>>> koodiLista = kooditPuusta(puu);
        // kanonisoidaan koodit jotta header-tiedosto olisi pienempi
        kanonisoija = new Kanonisoija();
        return kanonisoija.kanonisoi(koodiLista);
    }

    /**
     * Muodostaa prioriteettijonon esiintymistiheyksistä kasvavan tiheyden
     * mukaan (ensimmäinen alkio harvinaisin, viimeinen yleisin). Alkiot
     * tallennettu puunodeihin koska huffman-puu rakennetaan jonoa käyttäen
     *
     * @param esiintymisTiheydet Taulu jossa avaimena blokki ja arvona kuinka
     * monta kertaa blokki esiintynyt tiedostossa.
     * @return Prioriteettijono joka sisältää puun silmuissa järjestettynä
     * kasvavaan järjestykseen esiintymistiheyden mukaan
     */
    private OmaQueue<OmaTreeNode<Integer, TiedostoBlokki>> muodostaPrioriteettiJono(OmaMap<TiedostoBlokki, Integer> esiintymisTiheydet) {
        OmaQueue<OmaTreeNode<Integer, TiedostoBlokki>> jono = luoPrioriteettiJono();

        OmaList<TiedostoBlokki> avaimet = esiintymisTiheydet.avaimet();

        for (int i = 0; i < avaimet.size(); ++i) {
            OmaTreeNode<Integer, TiedostoBlokki> node = new OmaTreeNode<Integer, TiedostoBlokki>(esiintymisTiheydet.get(avaimet.get(i)), avaimet.get(i));
            jono.push(node);
        }

        return jono;
    }

    /**
     * Muodostaa huffman-puun annetusta jonosta.
     *
     * @param jono Jono joka sisältää esiintymistiheydet ja blokit tallennettuna
     * puun nodeihin kasvavassa esiintymistiheysjärjestyksessä
     * @return Muodostetun huffman-puun juuri
     */
    private OmaTreeNode<Integer, TiedostoBlokki> muodostaHuffmanPuu(OmaQueue<OmaTreeNode<Integer, TiedostoBlokki>> jono) {
        while (jono.size() > 1) {
            OmaTreeNode<Integer, TiedostoBlokki> node1 = jono.pop();
            OmaTreeNode<Integer, TiedostoBlokki> node2 = jono.pop();
            OmaTreeNode<Integer, TiedostoBlokki> uusiNode = new OmaTreeNode<Integer, TiedostoBlokki>(node1.haeAvain() + node2.haeAvain(), null, node1, node2);
            jono.push(uusiNode);
        }

        OmaTreeNode<Integer, TiedostoBlokki> puu = jono.pop();

        assert (jono.isEmpty());

        return puu;
    }

    /**
     * Muodostaa taulun jossa avaimena koodin pituus ja arvona lista
     * blokki-koodipareista joiden koodien pituus on avaimena. Tämä nopeuttaa
     * kanonisointia; ennen antoi vain listan joka oli järjestettävä pituuden
     * mukaan -> O(n log n)-operaatio joka suurilla parien määrillä hidasti
     * pakkausta. Nyt koodit on jo järjestetty pituuksien mukaan.
     *
     * @param puu Huffman-puun juuri
     * @return Taulu jossa avaimena koodin pituus ja arvona lista
     * blokki-koodipareista blokki-koodipareista joiden koodien pituus on
     * avaimena
     */
    private OmaMap<Integer, OmaList<Pari<TiedostoBlokki, HuffmanKoodi>>> kooditPuusta(OmaTreeNode<Integer, TiedostoBlokki> puu) {
        OmaMap<Integer, OmaList<Pari<TiedostoBlokki, HuffmanKoodi>>> koodit = new OmaHashMap<Integer, OmaList<Pari<TiedostoBlokki, HuffmanKoodi>>>((int) Math.pow(2, 14));

        kayPuuLapiJaLuoKooditRekursiivisesti(puu, koodit, 0, 0);

        return koodit;
    }

    /**
     * Rekursiometodi puun läpi käymiseen
     *
     * @param node käsiteltävä puun silmu
     * @param koodit Taulu jossa avaimena koodin pituus ja arvona lista
     * blokki-koodipareista
     * @param koodi tähän asti muodostettu huffman-koodi
     * @param paikka paikka puussa, tarvitaan huffman-koodin muodostamista
     * varten
     */
    private void kayPuuLapiJaLuoKooditRekursiivisesti(OmaTreeNode<Integer, TiedostoBlokki> node, OmaMap<Integer, OmaList<Pari<TiedostoBlokki, HuffmanKoodi>>> koodit, long koodi, int paikka) {
        // puun pitäisi olla täysi
        assert (node != null);

        if (node.onLehti()) {
            lisaaListaanTiedot(koodi, paikka, node, koodit);
            return;
        }

        kayPuuLapiJaLuoKooditRekursiivisesti(node.vasenLapsi(), koodit, koodi, paikka + 1);

        koodi = BittiUtility.tallennaBitinArvoPaikalle(koodi, 1, paikka);
        kayPuuLapiJaLuoKooditRekursiivisesti(node.oikeaLapsi(), koodit, koodi, paikka + 1);
    }

    /**
     * Laskee annetusta tiedostosta blokkien esiintymistiheydet
     *
     * @param lukija ITiedostoLukija-rajapinnan toeuttava objekti. Tästä luetaan
     * blokit
     * @return palauttaa Taulun joka sisältää blokit ja niiden
     * esiintymistiheydet
     * @throws IOException Jos lukeminen epäonnistuu
     */
    private OmaMap<TiedostoBlokki, Integer> laskeEsiintymisTiheydet(ITiedostoLukija lukija) throws IOException {
        lukija.avaaTiedosto();

        OmaMap<TiedostoBlokki, Integer> esiintymisTiheydet = new OmaHashMap<TiedostoBlokki, Integer>(8192);

        byte[] luetutTavut = new byte[BLOKIN_KOKO];
        int luettu;

        while ((luettu = lukija.lue(luetutTavut)) > 0) {
            kasitteleBlokki(luettu, luetutTavut, esiintymisTiheydet);
        }

        return esiintymisTiheydet;
    }

    /**
     * Käsittelee yhden blokin
     *
     * @param luettu montako tavua luettu tiedostosta
     * @param luetutTavut luetut tavut
     * @param esiintymisTiheydet Taulu blokki-esiintymistiheyksistä
     */
    private void kasitteleBlokki(int luettu, byte[] luetutTavut, OmaMap<TiedostoBlokki, Integer> esiintymisTiheydet) {
        TiedostoBlokki blokki = new TiedostoBlokki();

        blokki.byteTaulukko = new byte[luettu];

        for (int i = 0; i < luettu; ++i) {
            blokki.byteTaulukko[i] = luetutTavut[i];
        }

        Integer arvo = esiintymisTiheydet.get(blokki);
        if (arvo == null) {
            esiintymisTiheydet.put(blokki, 1);
        } else {
            esiintymisTiheydet.put(blokki, arvo + 1);
        }
    }

    private void lisaaListaanTiedot(long koodi, int paikka, OmaTreeNode<Integer, TiedostoBlokki> node, OmaMap<Integer, OmaList<Pari<TiedostoBlokki, HuffmanKoodi>>> koodit) {
        HuffmanKoodi k = new HuffmanKoodi();
        k.koodi = koodi;
        k.pituus = paikka;

        Pari pari = new Pari();
        pari.ensimmainen = node.haeArvo();
        pari.toinen = k;

        OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> lista = koodit.get(k.pituus);
        if (lista == null) {
            lista = new OmaArrayList<Pari<TiedostoBlokki, HuffmanKoodi>>(32);
            lista.add(pari);
            koodit.put(k.pituus, lista);
        } else {
            lista.add(pari);
        }
    }

    private OmaQueue<OmaTreeNode<Integer, TiedostoBlokki>> luoPrioriteettiJono() {
        return new OmaMinimiPriorityQueue<OmaTreeNode<Integer, TiedostoBlokki>>(new Comparator<OmaTreeNode<Integer, TiedostoBlokki>>() {
            @Override
            public int compare(OmaTreeNode<Integer, TiedostoBlokki> o1, OmaTreeNode<Integer, TiedostoBlokki> o2) {
                return o1.haeAvain() - o2.haeAvain();
            }
        });
    }
}
