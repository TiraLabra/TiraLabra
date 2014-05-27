/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package verkko;

import util.Lista;

public class Solmu {

    /**
     * Naapurit avaimena, painot arvona.
     */
    //private HashMap<Solmu,Double> naapurit;
    private Lista<Solmu> naapuriSolmut;
    private Lista<Double> naapuripainot;

    /**
     * Alustaa hajautustaulun naapureille
     */
    public Solmu() {
        naapuriSolmut = new Lista<>();
        naapuripainot = new Lista<>();
        //naapurit = new HashMap<>();
    }

    /**
     * Lisää solmun painolla paino naapureihin. Huom: ei lisää tätä solmua
     * solmun s naapureihin!
     *
     * @param s lisättävä solmu
     * @param paino
     */
    public void lisaaNaapuri(Solmu s, Double paino) {
        //naapurit.put(s, paino);
        naapuriSolmut.lisaa(s);
        naapuripainot.lisaa(paino);
    }

    /**
     * Kyselee onko annettu solmu naapuri
     *
     * @param s
     * @return
     */
    public Boolean onkoNaapuri(Solmu s) {
        //return naapurit.containsKey(s);
        return naapuriSolmut.onxNagyny(s);
    }

    public Lista<Solmu> getNaapuriSolmut() {
        return naapuriSolmut;
    }

    public Lista<Double> getNaapuripainot() {
        return naapuripainot;
    }

}
