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

    private final Heuristiikka heuristiikka;
    private final Set<Solmu> kasitelty;
    private final Map<Solmu, Solmu> reitti;
    private final Keko<AstarKekoEntry> keko;
    private final Solmu maali;
    private final Solmu alku;
    private final Map<Solmu, Double> matka;

    public Astar(Solmu alku, Solmu maali, Heuristiikka heuristiikka) {
        this.maali = maali;
        this.heuristiikka = heuristiikka;
        kasitelty = new HashSet<>(8);
        reitti = new HashMap<>(8);
        matka = new HashMap<>(8);
        keko = new Keko<>(new PriorityComparator());
        this.alku = alku;
    }

    public Map<Solmu, Solmu> getReitti() {
        return Collections.unmodifiableMap(reitti);
    }

    public boolean suorita() {
        matka.put(alku, 0.0);
        keko.lisaa(new AstarKekoEntry(alku, heuristiikkaMaaliin(alku)));
        boolean onnistuks = false;
        do {
            Solmu seuraava = keko.poista().getSolmu();
            if (kasittele(seuraava)) {
                onnistuks = true;
                break;
            }
        } while (!keko.isEmpty());
        return onnistuks;
    }

    /**
     * "Käsittelee" solmun, eli merkkaa sen käsitellyksi ja laskee matkan
     * pituuden naapureihin tämän solmun kautta ja lisää naapurit kekoon yms.
     *
     * @param solmu
     * @return
     */
    protected boolean kasittele(Solmu solmu) {
        kasitelty.add(solmu);
        if (solmu == maali) {
            return true;
        }
        Lista<Solmu> naapuriSolmut = solmu.getNaapuriSolmut();
        Lista<Double> naapuripainot = solmu.getNaapuripainot();
        for (int i = 0; i < naapuriSolmut.koko(); i++) {
            final Solmu naapuri = naapuriSolmut.get(i);
            if (kasitelty.contains(naapuri)) {
                continue;
            }
            Double matkaNaapuriin = matka.get(naapuri);
            if (matkaNaapuriin == null) {
                matkaNaapuriin = Double.POSITIVE_INFINITY;
            }
            Double matkaTamanKautta = matka.get(solmu) + naapuripainot.get(i);
            final boolean onKeossa = keko.contains(new AstarKekoEntry(naapuri, 0.0));
            if (!onKeossa || matkaTamanKautta < matkaNaapuriin) {
                reitti.put(naapuri, solmu);
                matka.put(naapuri, matkaTamanKautta);
                final AstarKekoEntry naapuriKekoEntry = new AstarKekoEntry(naapuri,
                        matkaTamanKautta + heuristiikkaMaaliin(naapuri));
                if (onKeossa) {
                    keko.muuta(new AstarKekoEntry(naapuri, 0.0), naapuriKekoEntry);
                } else {
                    keko.lisaa(naapuriKekoEntry);
                }
            }

        }
        return false;
    }

    /**
     *
     * @param naapuri
     * @return
     */
    private Double heuristiikkaMaaliin(final Solmu naapuri) {
        return heuristiikka.dist(naapuri, maali);
    }
}
