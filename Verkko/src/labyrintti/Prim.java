/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package labyrintti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
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
        lisaysehdokkaat = new ArrayList<>();
        snj = new Random();
    }

    @Override
    public void labyrintita() {
        Solmu lisatty = solmut[0][0];
        kayty(lisatty);
        kaymattomatNaapuritLisaysehdokkaisiin(lisatty);
        do {
            final int rand = snj.nextInt(lisaysehdokkaat.size());
            Solmu lisattava = lisaysehdokkaat.get(rand);
            lisaysehdokkaat.remove(rand);
            Solmu[] kaydytNaapurit = kaydytNaapurit(lisattava);
            Solmu valittuKayty;
            if (kaydytNaapurit.length > 1) {
                valittuKayty = kaydytNaapurit[snj.nextInt(kaydytNaapurit.length)];
            } else {
                valittuKayty = kaydytNaapurit[0];
            }
            luoNaapuruudet(lisattava, valittuKayty);
            kayty(lisattava);
            lisatty = lisattava;
            kaymattomatNaapuritLisaysehdokkaisiin(lisatty);

        } while (!lisaysehdokkaat.isEmpty());
    }

    /**
     * Kuvaava nimi, eikö?
     * @param s
     */
    private void kaymattomatNaapuritLisaysehdokkaisiin(Solmu s) {
        Solmu[] naapurit;
        naapurit = naapurit(s);
        for (Solmu solmu : naapurit) {
            if (!onkoKayty(solmu) && !lisaysehdokkaat.contains(solmu)) {
                lisaysehdokkaat.add(solmu);
            }
        }
    }

    /**
     * Palauttaa annetun solmun käydyt naapurit.
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

}
