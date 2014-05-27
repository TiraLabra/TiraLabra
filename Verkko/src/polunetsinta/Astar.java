/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package polunetsinta;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import util.Keko;
import util.Lista;
import verkko.Solmu;

public class Astar {

    private Heuristiikka h;
    private Set<Solmu> kasitelty;
    private Map<Solmu, Solmu> reitti;
    private Keko<AstarKekoEntry> keko;
    private final Solmu maali;
    private Map<Solmu, Double> matka;

    public Astar(Solmu alku, Solmu maali, Heuristiikka heuristiikka) {
        this.maali = maali;
        h = heuristiikka;
        kasitelty = new HashSet<>();
        reitti = new HashMap<>();
        matka = new HashMap<>();
        keko = new Keko<>(new Comparator<AstarKekoEntry>() {

            @Override
            public int compare(AstarKekoEntry o1, AstarKekoEntry o2) {
                if (o1.getPriority() < o2.getPriority()) {
                    return -1;
                } else if (o1.getPriority() > o2.getPriority()) {
                    return 1;
                }
                return 0;
            }
        });
        suorita(alku);
    }

    public Map<Solmu, Solmu> getReitti() {
        return reitti;
    }

    private void suorita(Solmu alku) {
        matka.put(alku, 0.0);
        keko.lisaa(new AstarKekoEntry(alku, h.dist(alku, maali)));
        while (!keko.empty()) {
            Solmu kasiteltavana = keko.poista().getSolmu();
            if (kasiteltavana == maali) {
                return;
            }
            kasitelty.add(kasiteltavana);
            Lista<Solmu> naapuriSolmut = kasiteltavana.getNaapuriSolmut();
            Lista<Double> naapuripainot = kasiteltavana.getNaapuripainot();
            for (int i = 0; i < naapuriSolmut.koko(); i++) {
                final Solmu naapuri = naapuriSolmut.get(i);
                if (kasitelty.contains(naapuri)) {
                    continue;
                }
                Double matkaNaapuriin = matka.get(naapuri);
                if (matkaNaapuriin == null) {
                    matkaNaapuriin = Double.POSITIVE_INFINITY;
                }
                Double matkaTamanKautta = matka.get(kasiteltavana) + naapuripainot.get(i);
                if (!keko.contains(new AstarKekoEntry(naapuri, 0.0)) || matkaTamanKautta < matkaNaapuriin) {
                    reitti.put(naapuri, kasiteltavana);
                    matka.put(naapuri, matkaTamanKautta);
                    if (keko.contains(new AstarKekoEntry(naapuri, 0.0))) {
                        keko.poista(new AstarKekoEntry(naapuri, 0.0));
                    }
                    keko.lisaa(new AstarKekoEntry(naapuri, matkaTamanKautta + h.dist(naapuri, maali)));
                }

            }
        }
    }

    public void printtaaReitti(Solmu maali) {
        while (true) {
            maali = reitti.get(maali);
            if (maali == null) {
                break;
            }
            System.out.println(maali);
        }
    }
}
