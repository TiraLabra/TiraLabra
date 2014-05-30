/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package polunetsinta;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import util.Keko;
import util.Lista;
import verkko.Solmu;

public class Astar {

    private final Heuristiikka h;
    private final Set<Solmu> kasitelty;
    private final Map<Solmu, Solmu> reitti;
    private final Keko<AstarKekoEntry> keko;
    private final Solmu maali;
    private final Map<Solmu, Double> matka;

    public Astar(Solmu alku, Solmu maali, Heuristiikka heuristiikka) {
        this.maali = maali;
        h = heuristiikka;
        kasitelty = new HashSet<>(8);
        reitti = new HashMap<>(8);
        matka = new HashMap<>(8);
        keko = new Keko<>(new PriorityComparator());
        suorita(alku);
    }

    public Map<Solmu, Solmu> getReitti() {
        return Collections.unmodifiableMap(reitti);
    }

    private void suorita(Solmu alku) {
        matka.put(alku, 0.0);
        keko.lisaa(new AstarKekoEntry(alku, h.dist(alku, maali)));
        while (!keko.isEmpty()) {
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

    public void printtaaReittiSolmutVaarinpain(Solmu maali) {
        Solmu s = maali;
        while (true) {
            s = reitti.get(s);
            if (s == null) {
                break;
            }
            System.out.println(s);
        }
    }
}
