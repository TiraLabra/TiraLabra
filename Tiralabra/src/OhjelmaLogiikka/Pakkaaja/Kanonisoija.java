package OhjelmaLogiikka.Pakkaaja;

import OhjelmaLogiikka.KanonisoidunKoodinMuodostaja;
import Tietorakenteet.TiedostoBlokki;
import Tietorakenteet.HuffmanKoodi;
import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import Tietorakenteet.OmaMinimiPriorityQueue;
import Tietorakenteet.Pari;
import java.util.Comparator;

/**
 * Luokka joka vastaa kanonisten koodien luomisesta olemassaolevista
 * huffman-koodeista
 *
 */
public class Kanonisoija {

    private OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> kooditJarjestyksesaHeaderiaVarten;

    /**
     * Palauttaa listan kanonisista koodeista pituusjärjestyksessä (ensimmäisenä
     * lyhyet). Headerin luonti tarvitsee tämän koska koodit on kirjoitettava
     * oikeassa järjestyksessä purkamisen onnistumiseksi
     *
     * @return listan blokki-koodipareista kasvavassa pituusjärjestyksessä
     */
    public OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> haeKooditJarjestettyna() {
        return kooditJarjestyksesaHeaderiaVarten;
    }

    /**
     * Kanonisoi annetut koodit. Saa parametrikseen taulun jossa avaimena koodin
     * pituus, arvona lista jossa on blokkikoodi-parit joilla sama pituus kuin
     * avaimella. Tällä säästetään O(n log n)-sorttaus kun halutaan lukea parit
     * koodin pituusjärjestyksessä (vain avainluettelo on sortattava, ei
     * koodi-blokki-parit)
     *
     * @param blokkiKoodiLista Taulu koodeista joista halutaan muodostaa
     * kanoniset koodit.
     * @return Taulu jossa blokki avaimena ja kanoninen koodi avaimena
     */
    public OmaMap<TiedostoBlokki, HuffmanKoodi> kanonisoi(OmaMap<Integer, OmaList<Pari<TiedostoBlokki, HuffmanKoodi>>> blokkiKoodiLista) {
        int koko = laskeHashMapilleKoko(blokkiKoodiLista.size());

        OmaMap<TiedostoBlokki, HuffmanKoodi> kanonisoidutKoodit = new OmaHashMap<TiedostoBlokki, HuffmanKoodi>(koko);
        kooditJarjestyksesaHeaderiaVarten = new OmaArrayList<Pari<TiedostoBlokki, HuffmanKoodi>>(koko);

        KanonisoidunKoodinMuodostaja muodostaja = new KanonisoidunKoodinMuodostaja();
        kasitteleTaulu(blokkiKoodiLista, muodostaja, kanonisoidutKoodit);

        return kanonisoidutKoodit;
    }

    /**
     * Käy taulun läpi ja kanonisoi listojen koodit.
     *
     * @param normaalitKoodit Koodit jotka halutaan kanonisoida
     * @param muodostaja objekti joka muodostaa kanoniset koodit
     * @param kanonisoidutKoodit Kanonisoidut koodit
     */
    private void kasitteleTaulu(OmaMap<Integer, OmaList<Pari<TiedostoBlokki, HuffmanKoodi>>> normaalitKoodit,
            KanonisoidunKoodinMuodostaja muodostaja, OmaMap<TiedostoBlokki, HuffmanKoodi> kanonisoidutKoodit) {

        OmaList<Integer> avaimet = normaalitKoodit.avaimet();
        avaimet = sorttaaAvaimet(avaimet);

        for (int i = 0; i < avaimet.size(); ++i) {
            OmaList<Pari<TiedostoBlokki, HuffmanKoodi>> lista = normaalitKoodit.get(avaimet.get(i));

            for (int j = 0; j < lista.size(); ++j) {
                Pari<TiedostoBlokki, HuffmanKoodi> pari = lista.get(j);
                pari.toinen.koodi = muodostaja.muodostaKanoninenHuffmanKoodi(pari.toinen.pituus);
                kooditJarjestyksesaHeaderiaVarten.add(pari);
                kanonisoidutKoodit.put(pari.ensimmainen, pari.toinen);
            }
        }
    }

    /**
     * Sorttaa heapin avulla avaimet pienimmästä suurimpaan
     *
     * @param avaimet Lista avaimista
     * @return Avaimet järjestyksessä
     */
    private OmaList<Integer> sorttaaAvaimet(OmaList<Integer> avaimet) {
        OmaList<Integer> paluu = new OmaArrayList<Integer>();

        OmaMinimiPriorityQueue<Integer> heapsort = luoHeap();

        for (int i = 0; i < avaimet.size(); ++i) {
            heapsort.push(avaimet.get(i));
        }

        while (!heapsort.isEmpty()) {
            paluu.add(heapsort.pop());
        }


        return paluu;
    }
    /**
     * Apumetodi. Laskee hashmapille koon
     * @param koko koko josta lasketaan kahden potenssi taulukkoa varten
     * @return 
     */
    private int laskeHashMapilleKoko(int koko) {
        int kerroin = (int) (Math.log(koko) / Math.log(2));
        return (int) Math.pow(2, kerroin);
    }
    /**
     * Apumetodi. Luo minimiprioriteettijonon
     * @return Minimiprioriteettijono
     */
    private OmaMinimiPriorityQueue<Integer> luoHeap() {
        return new OmaMinimiPriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
    }
}
