package OhjelmaLogiikka.Pakkaaja;

import OhjelmaLogiikka.BittiUtility;
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

public class Kanonisoija {

    private OmaList<Pari<ByteWrapper, Koodi>> kooditJarjestyksesaHeaderiaVarten;

    public OmaList<Pari<ByteWrapper, Koodi>> haeKooditJarjestettyna() {
        return kooditJarjestyksesaHeaderiaVarten;
    }

    public OmaMap<ByteWrapper, Koodi> kanonisoi(OmaList<Pari<ByteWrapper, Koodi>> blokkiKoodiLista) {

        return kanonisoidutKoodit(jarjestaKoodit(blokkiKoodiLista));
    }
    // käytännössä heap sort

    private OmaQueue<Pari<ByteWrapper, Koodi>> jarjestaKoodit(OmaList<Pari<ByteWrapper, Koodi>> blokkiKoodiLista) {
        OmaMinimiPriorityQueue<Pari<ByteWrapper, Koodi>> koodiJono = luoJono();

        for (int i = 0; i < blokkiKoodiLista.size(); ++i) {
            koodiJono.push(blokkiKoodiLista.get(i));
        }

        return koodiJono;
    }

    private OmaMap<ByteWrapper, Koodi> kanonisoidutKoodit(OmaQueue<Pari<ByteWrapper, Koodi>> koodiJono) {
        OmaMap<ByteWrapper, Koodi> koodit = new OmaHashMap<ByteWrapper, Koodi>();
        kooditJarjestyksesaHeaderiaVarten = new OmaArrayList<Pari<ByteWrapper, Koodi>>();

        KanonisoidunKoodinMuodostaja muodostaja = new KanonisoidunKoodinMuodostaja();
        while (!koodiJono.isEmpty()) {
            Pari<ByteWrapper, Koodi> pari = koodiJono.pop();

            pari.toinen.koodi = muodostaja.muodostaKoodi(pari.toinen.pituus);
           
            kooditJarjestyksesaHeaderiaVarten.add(pari);
            koodit.put(pari.ensimmainen, pari.toinen);
        }

        return koodit;
    }

    private long vaihdaBittiJarjestys(Long koodi, int pituus) {
        long uusiKoodi = 0L;

        for (int i = 0; i < pituus; ++i) {
            uusiKoodi = BittiUtility.tallennaBitinArvoPaikalle(uusiKoodi, BittiUtility.haeBitinArvoPaikasta(koodi, i), pituus - 1 - i);
        }
        return uusiKoodi;
    }

    private OmaMinimiPriorityQueue<Pari<ByteWrapper, Koodi>> luoJono() {
        return new OmaMinimiPriorityQueue<Pari<ByteWrapper, Koodi>>(new Comparator<Pari<ByteWrapper, Koodi>>() {
            @Override
            public int compare(Pari<ByteWrapper, Koodi> o1, Pari<ByteWrapper, Koodi> o2) {
                long paluu = o1.toinen.pituus - o2.toinen.pituus;

                if (paluu == 0) {
                    int pos = 0;
                    if (o1.ensimmainen.byteTaulukko.length == o2.ensimmainen.byteTaulukko.length) {
                        while (paluu == 0 && pos < o1.ensimmainen.byteTaulukko.length) {
                            paluu = o1.ensimmainen.byteTaulukko[pos] - o2.ensimmainen.byteTaulukko[pos];
                            ++pos;
                        }
                    } else {
                        paluu = o1.ensimmainen.byteTaulukko.length - o2.ensimmainen.byteTaulukko.length;
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
/*
public int compare(OmaTreeNode<Integer, ByteWrapper> o1, OmaTreeNode<Integer, ByteWrapper> o2) {
                return o1.haeAvain() - o2.haeAvain();
            }*/