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

/**
 *
 * @author Arvoitusmies
 */
public class Astar {

    /**
     * Käytettävä heuristiikka
     */
    private final Heuristiikka heuristiikka;

    /**
     * Joukko jossa käsitellyt solmut
     */
    private final Set<Solmu> kasitelty;

    /**
     * Lyhin reitti alkusolmusta avaimena olevaan solmuun kulkee arvona olevan
     * solmun kautta.
     */
    private final Map<Solmu, Solmu> reitti;

    /**
     * Keko.
     */
    private final Keko<AstarKekoEntry> keko;

    /**
     * Maalisolmu
     */
    private final Solmu maali;

    /**
     * Alkusolmu
     */
    private final Solmu alku;

    /**
     * Matka alkusolmu-avain välillä
     */
    private final Map<Solmu, Double> matka;

    /**
     * Alustaa.
     *
     * @param alku
     * @param maali
     * @param heuristiikka
     */
    public Astar(Solmu alku, Solmu maali, Heuristiikka heuristiikka) {
        this.maali = maali;
        this.heuristiikka = heuristiikka;
        kasitelty = new HashSet<>(8);
        reitti = new HashMap<>(8);
        matka = new HashMap<>(8);
        keko = new Keko<>(new PriorityComparator());
        this.alku = alku;
    }

    /**
     * Getteri
     *
     * @return
     */
    public Map<Solmu, Solmu> getReitti() {
        return Collections.unmodifiableMap(reitti);
    }

    /**
     * Suorittaa A* algoritmin alkusolmusta kunnes löydetään maalisolmu tai keko
     * on tyhjä.
     *
     * @return
     */
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
        for (int i = 0; i < naapuriSolmut.getKoko(); i++) {
            kasitteleNaapuri(naapuriSolmut, i, solmu, naapuripainot);
        }
        return false;
    }

    /**
     * Käsittelee naapurin
     *
     * @param naapuriSolmut
     * @param i
     * @param solmu
     * @param naapuripainot
     */
    protected void kasitteleNaapuri(Lista<Solmu> naapuriSolmut, int i, Solmu solmu, Lista<Double> naapuripainot) {
        final Solmu naapuri = naapuriSolmut.get(i);
        if (kasitelty.contains(naapuri)) {
            return;
        }
        Double matkaNaapuriin = matka.get(naapuri);
        if (matkaNaapuriin == null) {
            matkaNaapuriin = Double.POSITIVE_INFINITY;
        }
        Double matkaTamanKautta = matka.get(solmu) + naapuripainot.get(i);
        final boolean onKeossa = keko.contains(new AstarKekoEntry(naapuri, 0.0));
        if (!onKeossa || matkaTamanKautta < matkaNaapuriin) {
            reittiJaMatka(naapuri, solmu, matkaTamanKautta);
            paivitaTaiLuoKekoentry(naapuri, matkaTamanKautta, onKeossa);
        }
    }

    /**
     * Päivittää reitti ja matka hajautustaulut.
     *
     * @param naapuri
     * @param solmu
     * @param matkaTamanKautta
     */
    protected void reittiJaMatka(final Solmu naapuri, Solmu solmu, Double matkaTamanKautta) {
        reitti.put(naapuri, solmu);
        matka.put(naapuri, matkaTamanKautta);
    }

    /**
     * Päivittää tai luo keko-olion solmulle
     *
     * @param solmu
     * @param matka
     * @param muutetaan
     */
    protected void paivitaTaiLuoKekoentry(final Solmu solmu, Double matka, final boolean muutetaan) {
        final AstarKekoEntry naapuriKekoEntry = new AstarKekoEntry(solmu,
                matka + heuristiikkaMaaliin(solmu));
        if (muutetaan) {
            keko.muuta(new AstarKekoEntry(solmu, 0.0), naapuriKekoEntry);
        } else {
            keko.lisaa(naapuriKekoEntry);
        }
    }

    /**
     * Hauristiikka arvo solmusta maalisolmuun
     *
     * @param solmu
     * @return
     */
    private Double heuristiikkaMaaliin(final Solmu solmu) {
        return heuristiikka.dist(solmu, maali);
    }
}
