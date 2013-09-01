package OhjelmaLogiikka.Pakkaaja;

import OhjelmaLogiikka.BittiUtility;
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
import java.util.Comparator;

/**
 * Muodostaa huffman-puun. Omana luokkana yksikkötestauksen mahdollistamiseksi.
 *
 */
public class HuffmanKoodiMuodostaja {

    public OmaMap<Integer, OmaList<Pari<TiedostoBlokki, HuffmanKoodi>>> muodostaHuffmanKoodit(OmaMap<TiedostoBlokki, Integer> esiintymisTiheydet) {
        return kooditPuusta(muodostaHuffmanPuu(muodostaPrioriteettiJono(esiintymisTiheydet)));
    }

    /**
     * Muodostaa huffman-puun annetusta jonosta.
     *
     * @param jono Jono joka sisältää esiintymistiheydet ja blokit tallennettuna
     * puun nodeihin kasvavassa esiintymistiheysjärjestyksessä
     * @return Muodostetun huffman-puun juuri
     */
    protected OmaTreeNode<Integer, TiedostoBlokki> muodostaHuffmanPuu(OmaQueue<OmaTreeNode<Integer, TiedostoBlokki>> jono) {
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
     * Lisää annettuun karttaan joko uuden listan ja koodin jos ei vielä luotu, tai olemassaolevaan listaan koodin
     * @param koodi Koodi joka halutaan lisätä
     * @param paikka koodin pituus
     * @param node Puun solmu joka sisältää tiedostoblokin johonka koodi on luotu
     * @param koodit Kartta joka sisältää pituus - lista pareista -parit
     */
    private void lisaaListaanTiedot(long koodi, int pituus, OmaTreeNode<Integer, TiedostoBlokki> node, OmaMap<Integer, OmaList<Pari<TiedostoBlokki, HuffmanKoodi>>> koodit) {
        HuffmanKoodi k = new HuffmanKoodi();
        k.koodi = koodi;
        k.pituus = pituus;

        Pari<TiedostoBlokki, HuffmanKoodi> pari = new Pari<TiedostoBlokki, HuffmanKoodi>();
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
    protected OmaQueue<OmaTreeNode<Integer, TiedostoBlokki>> muodostaPrioriteettiJono(OmaMap<TiedostoBlokki, Integer> esiintymisTiheydet) {
        OmaQueue<OmaTreeNode<Integer, TiedostoBlokki>> jono = luoPrioriteettiJono();

        OmaList<TiedostoBlokki> avaimet = esiintymisTiheydet.avaimet();

        for (int i = 0; i < avaimet.size(); ++i) {
            OmaTreeNode<Integer, TiedostoBlokki> node = new OmaTreeNode<Integer, TiedostoBlokki>(esiintymisTiheydet.get(avaimet.get(i)), avaimet.get(i));
            jono.push(node);
        }

        return jono;
    }
    
    
    /**
     * Apumetodi. Luo prioriteettijonon.
     * @return Luotu prioriteettijono
     */
    private OmaQueue<OmaTreeNode<Integer, TiedostoBlokki>> luoPrioriteettiJono() {
        return new OmaMinimiPriorityQueue<OmaTreeNode<Integer, TiedostoBlokki>>(new Comparator<OmaTreeNode<Integer, TiedostoBlokki>>() {
            @Override
            public int compare(OmaTreeNode<Integer, TiedostoBlokki> o1, OmaTreeNode<Integer, TiedostoBlokki> o2) {
                return o1.haeAvain() - o2.haeAvain();
            }
        });
    }
}
