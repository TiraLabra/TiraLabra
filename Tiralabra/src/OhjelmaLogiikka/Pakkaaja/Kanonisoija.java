package OhjelmaLogiikka.Pakkaaja;

import OhjelmaLogiikka.KanonisoidunKoodinMuodostaja;
import Tietorakenteet.ByteWrapper;
import Tietorakenteet.Koodi;
import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import Tietorakenteet.OmaMinimiPriorityQueue;
import Tietorakenteet.OmaQueue;
import Tietorakenteet.Pari;
import java.util.Comparator;

/**
 * Luokka joka vastaa kanonisten koodien luomisesta olemassaolevista
 * huffman-koodeista
 *
 */
public class Kanonisoija {
    
    private OmaList<Pari<ByteWrapper, Koodi>> kooditJarjestyksesaHeaderiaVarten;

    /**
     * Palauttaa listan kanonisista koodeista pituusjärjestyksessä (ensimmäisenä
     * lyhyet). Headerin luonti tarvitsee tämän koska koodit on kirjoitettava
     * oikeassa järjestyksessä purkamisen onnistumiseksi
     *
     * @return listan blokki-koodipareista kasvavassa pituusjärjestyksessä
     */
    public OmaList<Pari<ByteWrapper, Koodi>> haeKooditJarjestettyna() {
        return kooditJarjestyksesaHeaderiaVarten;
    }

    /**
     * Kanonisoi annetut koodit
     *
     * @param blokkiKoodiLista Taulu koodeista joista halutaan muodostaa kanoniset koodit
     * @return Taulu jossa blokki avaimena ja kanoninen koodi avaimena
     */
    public OmaMap<ByteWrapper, Koodi> kanonisoi(OmaMap<Integer, OmaList<Pari<ByteWrapper, Koodi>>> blokkiKoodiLista) {

        return kanonisoidutKoodit(blokkiKoodiLista);
    }

    /**
     * Luo kanonisoidut koodit annetusta taulusta jossa avaimena koodien pituus ja arvona lista blokki-koodi-pareista joiden koodien pituus on avaimena
     *
     * @param normaalitKoodit Taulu koodeista joista halutaan muodostaa kanoniset koodit
     * @return Taulu jossa blokki avaimena ja kanoninen koodi arvona
     */
    private OmaMap<ByteWrapper, Koodi> kanonisoidutKoodit(OmaMap<Integer, OmaList<Pari<ByteWrapper, Koodi>>> normaalitKoodit) {

        // luetaan parit pituusjärjestyksessä, korvataan koodi uudella kanonisella koodilla, palautetaan lista
        int kerroin = (int) (Math.log(normaalitKoodit.size()) / Math.log(2));
        int koko = (int) Math.pow(2, kerroin);

        OmaMap<ByteWrapper, Koodi> kanonisoidutKoodit = new OmaHashMap<ByteWrapper, Koodi>(koko);
        kooditJarjestyksesaHeaderiaVarten = new OmaArrayList<Pari<ByteWrapper, Koodi>>(koko);

        KanonisoidunKoodinMuodostaja muodostaja = new KanonisoidunKoodinMuodostaja();
        kasitteleTaulu(normaalitKoodit, muodostaja, kanonisoidutKoodit);

        return kanonisoidutKoodit;
    }
    /**
     * Käy taulun läpi ja kanonisoi listojen koodit.
     * @param normaalitKoodit Koodit jotka halutaan kanonisoida
     * @param muodostaja objekti joka muodostaa kanoniset koodit
     * @param kanonisoidutKoodit Kanonisoidut koodit
     */
    private void kasitteleTaulu(OmaMap<Integer, OmaList<Pari<ByteWrapper, Koodi>>> normaalitKoodit,
            KanonisoidunKoodinMuodostaja muodostaja, OmaMap<ByteWrapper, Koodi> kanonisoidutKoodit) {

        int indeksi = 0;
        int kasiteltyja = 0;
        while (kasiteltyja < normaalitKoodit.size()) {
            OmaList<Pari<ByteWrapper, Koodi>> lista = normaalitKoodit.get(indeksi);
            ++indeksi;

            if (lista == null) {
                continue;
            }

            for (int i = 0; i < lista.size(); ++i) {
                Pari<ByteWrapper, Koodi> pari = lista.get(i);
                pari.toinen.koodi = muodostaja.muodostaKoodi(pari.toinen.pituus);
                kooditJarjestyksesaHeaderiaVarten.add(pari);
                kanonisoidutKoodit.put(pari.ensimmainen, pari.toinen);
            }
            ++kasiteltyja;
        }
    }
}