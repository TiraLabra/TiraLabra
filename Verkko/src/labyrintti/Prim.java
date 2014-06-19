/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package labyrintti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import util.Lista;
import util.Taulukko;
import verkko.Solmu;

/**
 * Primin algoritmi labyrintittää.
 *
 * @author Arvoitusmies
 */
public class Prim extends Labyrintitin {

    /**
     * Ei-käydyt solmut, jotka ovat jo käyneiden naapureita, ja jotka voidaan
     * liittää labyrinttiin.
     */
    private final ArrayList<Solmu> lisaysehdokkaat;

    /**
     * SatunnaisNumero jumala
     */
    private final Random snj;

    /**
     *
     * @param solmut
     */
    public Prim(Solmu[][] solmut) {
        super(solmut);
        lisaysehdokkaat = new ArrayList<>(solmut.length + solmut[0].length);
        snj = new Random();
    }

    @Override
    public void labyrintita() {
        Solmu lisatty = solmut[0][0];
        kayty(lisatty);
        Lista<Solmu> kaymattomatNaapurit = kaymattomatNaapuritLista(lisatty);
//        lisaysehdokkaat.addAll(Arrays.asList(kaymattomatNaapurit));
        for (Solmu solmu : kaymattomatNaapurit) {
            lisaysehdokkaat.add(solmu);
        }
        do {
            final int rand = snj.nextInt(lisaysehdokkaat.size());
            Solmu lisattava = lisaysehdokkaat.get(rand);
            lisaysehdokkaat.remove(rand);
            Lista<Solmu> kaydytNaapurit = kaydytNaapuritLista(lisattava);
            Solmu valittuKayty;
            if (kaydytNaapurit.getKoko() > 1) {
                valittuKayty = kaydytNaapurit.get(snj.nextInt(kaydytNaapurit.getKoko()));
            } else {
                valittuKayty = kaydytNaapurit.get(0);//kaydytNaapurit[0];
            }
            luoNaapuruudet(lisattava, valittuKayty);
            kayty(lisattava);
            lisatty = lisattava;
            kaymattomatNaapurit = kaymattomatNaapuritLista(lisatty);
//            lisaysehdokkaat.addAll(Arrays.asList(kaymattomatNaapurit));
            for (Solmu solmu : kaymattomatNaapurit) {
                lisaysehdokkaat.add(solmu);
            }

        } while (!lisaysehdokkaat.isEmpty());
    }

    /**
     * Käymättömät naapurit
     *
     * @param s
     * @return
     */
    private Solmu[] kaymattomatNaapurit(Solmu s) {
        Solmu[] naapurit = naapurit(s);
        for (int i = 0; i < naapurit.length; i++) {
            Solmu solmu = naapurit[i];
            if (onkoKayty(solmu) || lisaysehdokkaat.contains(solmu)) {
                naapurit[i] = null;
            }
        }
        final Object[] poistaNullit = Taulukko.poistaNullit(naapurit);
        naapurit = Arrays.copyOf(poistaNullit, poistaNullit.length, Solmu[].class);
        return naapurit;
    }

    /**
     * Käymättömät naapurit
     *
     * @param s
     * @return
     */
    private Lista<Solmu> kaymattomatNaapuritLista(Solmu s) {
        Lista<Solmu> naapurit = naapuritLista(s);
//        for (int i = 0; i < naapurit.length; i++) {
//            Solmu solmu = naapurit[i];
//            if (onkoKayty(solmu) || lisaysehdokkaat.contains(solmu)) {
//                naapurit[i] = null;
//            }
//        }
        Iterator<Solmu> iterator = naapurit.iterator();
        while (iterator.hasNext()) {
            Solmu solmu = iterator.next();
            if (onkoKayty(solmu) || lisaysehdokkaat.contains(solmu)) {
                iterator.remove();
            }
        }
        return naapurit;
    }

    /**
     * Palauttaa annetun solmun käydyt naapurit.
     *
     * @param s
     * @return
     */
    private Solmu[] kaydytNaapurit(Solmu s) {
        Solmu[] naapurit = naapurit(s);
        for (int i = 0; i < naapurit.length; i++) {
            if (!onkoKayty(naapurit[i])) {
                naapurit[i] = null;
            }
        }
        final Object[] poistaNullit = Taulukko.poistaNullit(naapurit);
        naapurit = Arrays.copyOf(poistaNullit, poistaNullit.length, Solmu[].class);
        return naapurit;
    }

    /**
     * Palauttaa annetun solmun käydyt naapurit.
     *
     * @param s
     * @return
     */
    private Lista<Solmu> kaydytNaapuritLista(Solmu s) {
        Lista<Solmu> naapurit = naapuritLista(s);
//        for (int i = 0; i < naapurit.length; i++) {
//            if (!onkoKayty(naapurit[i])) {
//                naapurit[i] = null;
//            }
//        }
        Iterator<Solmu> iterator = naapurit.iterator();
        while (iterator.hasNext()) {
            Solmu solmu = iterator.next();
            if (!onkoKayty(solmu)) {
                iterator.remove();
            }
        }
//        final Object[] poistaNullit = Taulukko.poistaNullit(naapurit);
//        naapurit = Arrays.copyOf(poistaNullit, poistaNullit.length, Solmu[].class);
        return naapurit;
    }

}
