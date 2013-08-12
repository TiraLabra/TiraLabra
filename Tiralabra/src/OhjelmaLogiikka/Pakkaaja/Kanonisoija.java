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
     * @param blokkiKoodiLista lista blokki-koodipareista
     * @return Taulu jossa blokki avaimena ja kanoninen koodi avaimena
     */
    public OmaMap<ByteWrapper, Koodi> kanonisoi(OmaList<Pari<ByteWrapper, Koodi>> blokkiKoodiLista) {

        return kanonisoidutKoodit(asetaKooditJonoon(blokkiKoodiLista));
    }

    /**
     * Luo minimiprioriteettijonon annetuista blokki-koodipareista.
     * Minimiprioriteettijono on sisäisesti heap -> heap sort kun seuraavassa
     * metodissa popataan, O(n log n) missä n on blokkien määrä. Suurilla
     * tiedostoilla tämä voi olla raskas operaatio koska n on
     * tiedostonkoko/blokkikoko -> esim. 70 megan tiedostolla ja 8 blokkikoolla
     * n ~ 9 miljoonaa
     *
     * @param blokkiKoodiLista lista blokki-koodipareista
     * @return minimiprioriteettijono blokeille
     */
    private OmaQueue<Pari<ByteWrapper, Koodi>> asetaKooditJonoon(OmaList<Pari<ByteWrapper, Koodi>> blokkiKoodiLista) {
        OmaMinimiPriorityQueue<Pari<ByteWrapper, Koodi>> koodiJono = luoJono();

        for (int i = 0; i < blokkiKoodiLista.size(); ++i) {
            koodiJono.push(blokkiKoodiLista.get(i));
        }

        return koodiJono;
    }

    /**
     * Luo kanonisoidut koodit annetun prioriteettijonon avulla. Tämän +
     * asetakooditJonoon-metodin aikavaatimus O(n log n). 
     *
     * @param koodiJono Minimiprioriteettijono jossa koodi-blokkiparit
     * @return Taulu jossa blokki avaimena ja kanoninen koodi avaimena
     */
    private OmaMap<ByteWrapper, Koodi> kanonisoidutKoodit(OmaQueue<Pari<ByteWrapper, Koodi>> koodiJono) {
        
        // luetaan parit pituusjärjestyksessä, korvataan koodi uudella kanonisella koodilla, palautetaan lista
        int kerroin = (int)(Math.log(koodiJono.size())/Math.log(2)); 
        int koko = (int)Math.pow(2, kerroin);
        OmaMap<ByteWrapper, Koodi> koodit = new OmaHashMap<ByteWrapper, Koodi>(koko);
        kooditJarjestyksesaHeaderiaVarten = new OmaArrayList<Pari<ByteWrapper, Koodi>>(koko);
        
        KanonisoidunKoodinMuodostaja muodostaja = new KanonisoidunKoodinMuodostaja();
        while (!koodiJono.isEmpty()) {
            Pari<ByteWrapper, Koodi> pari = koodiJono.pop();

            pari.toinen.koodi = muodostaja.muodostaKoodi(pari.toinen.pituus);

            kooditJarjestyksesaHeaderiaVarten.add(pari);
            koodit.put(pari.ensimmainen, pari.toinen);
        }

        return koodit;
    }

    /**
     * Apumetodi joka luo prioriteettijonon. Piilotettu omaan metodiin suuren
     * Comparator-blokin takia
     *
     * @return Prioriteettijono
     */
    private OmaMinimiPriorityQueue<Pari<ByteWrapper, Koodi>> luoJono() {
        return new OmaMinimiPriorityQueue<Pari<ByteWrapper, Koodi>>(new Comparator<Pari<ByteWrapper, Koodi>>() {
            @Override
            public int compare(Pari<ByteWrapper, Koodi> o1, Pari<ByteWrapper, Koodi> o2) {
                long paluu = o1.toinen.pituus - o2.toinen.pituus;

                if (paluu == 0) {
                    int pos = 0;
                    if (o1.ensimmainen.size() == o2.ensimmainen.size()) {
                        while (paluu == 0 && pos < o1.ensimmainen.size()) {
                            paluu = o1.ensimmainen.byteTaulukko[pos] - o2.ensimmainen.byteTaulukko[pos];
                            ++pos;
                        }
                    } else {
                        paluu = o1.ensimmainen.size() - o2.ensimmainen.size();
                    }


                }

                if (paluu > 0) {
                    return 1;
                } else if (paluu == 0) {
                    return 0;
                }
                return -1;
            }
        });
    }
}