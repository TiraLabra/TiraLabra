/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package verkko;

import util.Lista;

public class Solmu {

    /**
     * Naapurit
     */
    private Lista<Solmu> naapuriSolmut;
    /**
     * Naapureja vastaavat painot
     */
    private Lista<Double> naapuripainot;

    /**
     * Alustaa Listat naapureille
     */
    public Solmu() {
        naapuriSolmut = new Lista<>();
        naapuripainot = new Lista<>();
    }

    /**
     * Lisää solmun painolla paino naapureihin. Huom: ei lisää tätä solmua
     * solmun s naapureihin!
     *
     * @param s lisättävä solmu
     * @param paino
     */
    public void lisaaNaapuri(Solmu s, Double paino) {
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

        return naapuriSolmut.onxNagyny(s);
    }

    /**
     * getteri
     *
     * @return
     */
    public Lista<Solmu> getNaapuriSolmut() {
        return naapuriSolmut;
    }

    /**
     * getteri
     *
     * @return
     */
    public Lista<Double> getNaapuripainot() {
        return naapuripainot;
    }

}
