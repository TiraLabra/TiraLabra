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
    //private final ArrayList<Solmu> lisaysehdokkaat;
    private final Lista<Solmu> lis;

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
        //lisaysehdokkaat = new ArrayList<>();
        lis = new Lista<>();
        snj = new Random();
    }

    @Override
    public void labyrintita() {
        Solmu lisatty = solmut[0][0];
        kayty(lisatty);
        Lista<Solmu> kaymattomatNaapurit = kaymattomatNaapurit(lisatty);
//        lisaysehdokkaat.addAll(Arrays.asList(kaymattomatNaapurit));
        for (Solmu solmu : kaymattomatNaapurit) {
            //lisaysehdokkaat.add(solmu);
            lis.lisaa(solmu);
        }
        do {
            final int rand = snj.nextInt(lis.getKoko());//lisaysehdokkaat.size());
            Solmu lisattava = lis.get(rand);//lisaysehdokkaat.get(rand);
            lis.poista(rand);
            //lisaysehdokkaat.remove(rand);
            Lista<Solmu> kaydytNaapurit = kaydytNaapurit(lisattava);
            Solmu valittuKayty;
            if (kaydytNaapurit.getKoko() > 1) {
                valittuKayty = kaydytNaapurit.get(snj.nextInt(kaydytNaapurit.getKoko()));
            } else {
                valittuKayty = kaydytNaapurit.get(0);//kaydytNaapurit[0];
            }
            luoNaapuruudet(lisattava, valittuKayty);
            kayty(lisattava);
            lisatty = lisattava;
            kaymattomatNaapurit = kaymattomatNaapurit(lisatty);
//            lisaysehdokkaat.addAll(Arrays.asList(kaymattomatNaapurit));
            for (Solmu solmu : kaymattomatNaapurit) {
                //lisaysehdokkaat.add(solmu);
                lis.lisaa(solmu);
            }

        } while (!lis.tyhja());//!lisaysehdokkaat.isEmpty());
    }

    /**
     * Käymättömät naapurit
     *
     * @param s
     * @return
     */
    private Lista<Solmu> kaymattomatNaapurit(Solmu s) {
        Lista<Solmu> naapurit = naapurit(s);
//        for (int i = 0; i < naapurit.length; i++) {
//            Solmu solmu = naapurit[i];
//            if (onkoKayty(solmu) || lisaysehdokkaat.contains(solmu)) {
//                naapurit[i] = null;
//            }
//        }
        Iterator<Solmu> iterator = naapurit.iterator();
        while (iterator.hasNext()) {
            Solmu solmu = iterator.next();
            if (onkoKayty(solmu)) {
                iterator.remove();
            } else if (lis.contains(solmu)) {
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
    private Lista<Solmu> kaydytNaapurit(Solmu s) {
        Lista<Solmu> naapurit = naapurit(s);
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
